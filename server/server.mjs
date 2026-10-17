import http from 'node:http';
import fs from 'node:fs';
import path from 'node:path';
import crypto from 'node:crypto';
import { initDatabase, hashPassword, hashCode } from './init_db.js';

const PORT = (process.env.PORT && process.env.PORT !== "8080") ? Number(process.env.PORT) : 3000;
const db = initDatabase();

// In-memory rate limiter
const rateLimits = new Map();
function checkRateLimit(key, maxRequests = 10, windowMs = 60000) {
  const now = Date.now();
  const record = rateLimits.get(key) || { count: 0, resetAt: now + windowMs };
  if (now > record.resetAt) {
    record.count = 0;
    record.resetAt = now + windowMs;
  }
  record.count += 1;
  rateLimits.set(key, record);
  return record.count <= maxRequests;
}

// Helper: parse JSON request body safely
function parseJsonBody(req) {
  return new Promise((resolve, reject) => {
    let body = '';
    req.on('data', chunk => {
      body += chunk;
      if (body.length > 1e6) {
        req.destroy();
        reject(new Error('Payload too large'));
      }
    });
    req.on('end', () => {
      try {
        resolve(body ? JSON.parse(body) : {});
      } catch (err) {
        reject(err);
      }
    });
    req.on('error', reject);
  });
}

// Helper: set HttpOnly secure session cookie
function setSessionCookie(req, res, token) {
  const isSecure = req.headers['x-forwarded-proto'] === 'https' || (req.socket && req.socket.encrypted);
  const cookieFlags = [
    `session_token=${token}`,
    'Path=/',
    'HttpOnly',
    'SameSite=Lax',
    `Max-Age=${30 * 24 * 60 * 60}` // 30 days
  ];
  if (isSecure) {
    cookieFlags.push('Secure');
  }
  res.setHeader('Set-Cookie', cookieFlags.join('; '));
}

// Helper: clear session cookie
function clearSessionCookie(req, res) {
  const isSecure = req.headers['x-forwarded-proto'] === 'https' || (req.socket && req.socket.encrypted);
  const cookieFlags = [
    'session_token=',
    'Path=/',
    'HttpOnly',
    'SameSite=Lax',
    'Max-Age=0'
  ];
  if (isSecure) {
    cookieFlags.push('Secure');
  }
  res.setHeader('Set-Cookie', cookieFlags.join('; '));
}

// Helper: send JSON responses
function sendJson(res, statusCode, data) {
  res.writeHead(statusCode, {
    'Content-Type': 'application/json; charset=utf-8',
    'Cache-Control': 'no-store, no-cache, must-revalidate, proxy-revalidate'
  });
  res.end(JSON.stringify(data));
}

// Helper: authenticate request using Cookie (primary) or Bearer header (fallback)
function getAuthUser(req) {
  let token = '';
  const cookieHeader = req.headers['cookie'] || '';
  const match = cookieHeader.match(/session_token=([^;]+)/);
  if (match) {
    token = match[1].trim();
  }

  if (!token) {
    const authHeader = req.headers['authorization'] || '';
    if (authHeader.startsWith('Bearer ')) {
      token = authHeader.slice(7).trim();
    }
  }

  if (!token) return null;

  const session = db.prepare('SELECT * FROM sessions WHERE token = ?').get(token);
  if (!session) return null;

  const now = new Date();
  if (new Date(session.expires_at) < now) {
    db.prepare('DELETE FROM sessions WHERE token = ?').run(token);
    return null;
  }

  const user = db.prepare('SELECT * FROM users WHERE uid = ?').get(session.user_id);
  if (!user) return null;

  evaluateUserAccess(user, now);

  // Check single-device session match if deviceId is provided
  const reqDevId = (req.headers['x-device-id'] || '').trim();
  if (reqDevId && session.device_id && session.device_id !== reqDevId) {
    // Session belongs to another active device
    return null;
  }

  return user;
}

// Helper: dynamically check trial / annual expirations against trusted server time
function evaluateUserAccess(user, now = new Date()) {
  let changed = false;
  if (user.access_type === 'TRIAL' && user.access_status === 'ACTIVE') {
    if (user.trial_expires_at && new Date(user.trial_expires_at) <= now) {
      user.access_status = 'TRIAL_EXPIRED';
      db.prepare("UPDATE users SET access_status = 'TRIAL_EXPIRED' WHERE uid = ?").run(user.uid);
      db.prepare("INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata) VALUES (?, 'TRIAL_EXPIRED', ?, ?)")
        .run(user.uid, now.toISOString(), JSON.stringify({ expiredAt: user.trial_expires_at }));
      changed = true;
    }
  } else if (user.access_type === 'ANNUAL' && user.access_status === 'ACTIVE') {
    if (user.annual_expires_at && new Date(user.annual_expires_at) <= now) {
      user.access_status = 'EXPIRED';
      db.prepare("UPDATE users SET access_status = 'EXPIRED' WHERE uid = ?").run(user.uid);
      db.prepare("INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata) VALUES (?, 'ANNUAL_EXPIRED', ?, ?)")
        .run(user.uid, now.toISOString(), JSON.stringify({ expiredAt: user.annual_expires_at }));
      changed = true;
    }
  }
  return changed;
}

// Helper: create new cryptographically secure session
function getClientDeviceId(req, body = {}) {
  let devId = (body && (body.deviceId || body.device_id)) || req.headers["x-device-id"] || "";
  if (typeof devId === "string" && devId.trim()) {
    return devId.trim().slice(0, 128);
  }
  const ua = req.headers["user-agent"] || "unknown-client";
  const ip = req.headers["x-forwarded-for"] || (req.socket && req.socket.remoteAddress) || "127.0.0.1";
  return "fp_" + crypto.createHash("sha256").update(ua + ":" + ip).digest("hex").slice(0, 32);
}

function createSession(userId, deviceId = "") {
  // Enforce single-device session: invalidate any older active sessions for this user
  try {
    db.prepare("DELETE FROM sessions WHERE user_id = ?").run(userId);
  } catch (e) {}

  const token = crypto.randomBytes(32).toString("hex");
  const now = new Date();
  const expiresAt = new Date(now.getTime() + 30 * 24 * 60 * 60 * 1000); // 30 days
  db.prepare("INSERT INTO sessions (token, user_id, created_at, expires_at, device_id) VALUES (?, ?, ?, ?, ?)")
    .run(token, userId, now.toISOString(), expiresAt.toISOString(), deviceId);
  return token;
}

// Helper: format safe user object without sensitive secrets
function formatSafeUser(user) {
  return {
    uid: user.uid,
    name: user.name,
    username: user.username,
    role: user.role,
    createdAt: user.created_at,
    trialUsed: Boolean(user.trial_used),
    trialStartedAt: user.trial_started_at,
    trialExpiresAt: user.trial_expires_at,
    accessType: user.access_type,
    accessStatus: user.access_status,
    annualActivatedAt: user.annual_activated_at,
    annualExpiresAt: user.annual_expires_at,
    boundDeviceId: user.bound_device_id || ""
  };
}

process.on('uncaughtException', (err) => {
  console.error('Unhandled Exception in server process:', err);
});
process.on('unhandledRejection', (reason, promise) => {
  console.error('Unhandled Rejection at:', promise, 'reason:', reason);
});

const server = http.createServer(async (req, res) => {
  try {
    const parsedUrl = new URL(req.url, `http://${req.headers.host || 'localhost'}`);
    const pathname = parsedUrl.pathname;
    const clientIp = req.headers['x-forwarded-for'] || req.socket.remoteAddress || 'unknown';

    // CORS & Security Headers
    const reqOrigin = req.headers.origin || '';
    const isAllowedOrigin = !reqOrigin || 
      reqOrigin.startsWith('http://localhost') || 
      reqOrigin.startsWith('http://127.0.0.1') || 
      reqOrigin.includes('github.io') ||
      reqOrigin.includes('run.app') ||
      reqOrigin.includes('aistudio') ||
      reqOrigin.includes('googleusercontent.com');

    if (isAllowedOrigin && reqOrigin) {
      res.setHeader('Access-Control-Allow-Origin', reqOrigin);
      res.setHeader('Access-Control-Allow-Credentials', 'true');
    } else if (!reqOrigin) {
      res.setHeader('Access-Control-Allow-Origin', '*');
    }
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization, X-Device-Id');
    res.setHeader('X-Content-Type-Options', 'nosniff');
    res.setHeader('X-Frame-Options', 'SAMEORIGIN');

    if (req.method === 'OPTIONS') {
      res.writeHead(204);
      res.end();
      return;
    }

    // -------------------------------------------------------------
    // PHASE 3.1: SIMPLIFIED STUDENT AUTHENTICATION & ACCESS API
    // -------------------------------------------------------------

    // 1. STUDENT LOGIN / FIRST ACTIVATION (Username + Activation Code)
    if ((pathname === '/api/student/login' || pathname === '/api/auth/login') && req.method === 'POST') {
      if (!checkRateLimit(`stud_login_${clientIp}`, 20, 60000)) {
        return sendJson(res, 429, { success: false, message: 'محاولات دخول كثيرة جداً، يرجى الانتظار قليلاً.' });
      }

      try {
        const body = await parseJsonBody(req);
        const rawUsername = String(body.username || body.loginIdentifier || '').trim();
        const rawCode = String(body.code || body.activationCode || '').trim();

        if (!rawUsername || !rawCode) {
          return sendJson(res, 400, {
            success: false,
            message: 'يرجى إدخال اسم المستخدم وكود التفعيل.'
          });
        }

        const normUsername = rawUsername.toLowerCase();
        const cleanCode = rawCode.toUpperCase();
        const codeHash = hashCode(cleanCode);

        // Lookup code hash
        const codeRow = db.prepare('SELECT * FROM activation_codes WHERE code_hash = ?').get(codeHash);

        if (!codeRow) {
          // Do not reveal if username or code was wrong
          return sendJson(res, 401, {
            success: false,
            message: 'اسم المستخدم أو كود التفعيل غير صحيح.'
          });
        }

        // CASE 1: UNUSED CODE -> FIRST PAID ACTIVATION (Creates account & associates code permanently)
        if (codeRow.status === 'UNUSED') {
          // Check username availability
          const existingUser = db.prepare('SELECT uid FROM users WHERE username = ?').get(normUsername);
          if (existingUser) {
            return sendJson(res, 409, {
              success: false,
              message: 'اسم المستخدم مسجل مسبقاً، يرجى اختيار اسم مستخدم آخر.'
            });
          }

          const uid = 'std_' + crypto.randomBytes(8).toString('hex');
          const now = new Date();
          const annualExpiresAt = new Date(now.getTime() + 365 * 24 * 60 * 60 * 1000); // 365 days

          db.exec('BEGIN IMMEDIATE');
          try {
            // Re-verify code still UNUSED inside transaction
            const freshCode = db.prepare('SELECT status FROM activation_codes WHERE id = ?').get(codeRow.id);
            if (freshCode.status !== 'UNUSED') {
              db.exec('ROLLBACK');
              return sendJson(res, 400, {
                success: false,
                message: 'تم استخدام هذا الكود للتو بواسطة مستخدم آخر.'
              });
            }

            const clientDevId = getClientDeviceId(req, body);
            // Create student account with bound device
            db.prepare(`
              INSERT INTO users (uid, name, username, role, created_at, access_type, access_status, annual_activated_at, annual_expires_at, activation_code_id, password_hash, password_salt, bound_device_id)
              VALUES (?, ?, ?, 'student', ?, 'ANNUAL', 'ACTIVE', ?, ?, ?, '', '', ?)
            `).run(uid, rawUsername, normUsername, now.toISOString(), now.toISOString(), annualExpiresAt.toISOString(), codeRow.id, clientDevId);

            // Permanently associate code with student account
            db.prepare(`
              UPDATE activation_codes
              SET status = 'ACTIVE',
                  activated_at = ?,
                  expires_at = ?,
                  assigned_user_id = ?
              WHERE id = ?
            `).run(now.toISOString(), annualExpiresAt.toISOString(), uid, codeRow.id);

            // Log access audit
            db.prepare(`
              INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata)
              VALUES (?, 'FIRST_ACTIVATION', ?, ?)
            `).run(uid, now.toISOString(), JSON.stringify({ codeId: codeRow.id, expiresAt: annualExpiresAt.toISOString() }));

            db.exec('COMMIT');

            const token = createSession(uid, clientDevId);
            setSessionCookie(req, res, token);

            const newUser = db.prepare('SELECT * FROM users WHERE uid = ?').get(uid);
            return sendJson(res, 200, {
              success: true,
              user: formatSafeUser(newUser),
              message: 'تم تفعيل الحساب والاشتراك السنوي بنجاح! 🎉'
            });
          } catch (txErr) {
            db.exec('ROLLBACK');
            throw txErr;
          }
        }

        // CASE 2: ACTIVE CODE -> FUTURE LOGIN (Verify assigned student account & device)
        if (codeRow.status === 'ACTIVE') {
          const user = db.prepare('SELECT * FROM users WHERE uid = ? AND username = ?').get(codeRow.assigned_user_id, normUsername);
          if (!user) {
            // Code belongs to another student, or username mismatch -> Reject with generic message
            return sendJson(res, 401, {
              success: false,
              message: 'اسم المستخدم أو كود التفعيل غير صحيح.'
            });
          }
          evaluateUserAccess(user);
          if (user.access_status === 'SUSPENDED') {
            return sendJson(res, 403, {
              success: false,
              message: 'تم إيقاف هذا الحساب من قبل الإدارة.'
            });
          }

          const clientDevId = getClientDeviceId(req, body);
          // Single-device policy: If bound to a device, must match unless admin reset
          if (user.bound_device_id && clientDevId && user.bound_device_id !== clientDevId) {
            return sendJson(res, 403, {
              success: false,
              code: 'DEVICE_UNAUTHORIZED',
              message: 'هذا الحساب مفعل ومرتبط بجهاز آخر. يُسمح بجهاز واحد فقط لكل اشتراك. يرجى التواصل مع الإدارة لإعادة تعيين الجهاز.'
            });
          } else if (!user.bound_device_id && clientDevId) {
            // First device binding
            db.prepare('UPDATE users SET bound_device_id = ? WHERE uid = ?').run(clientDevId, user.uid);
            user.bound_device_id = clientDevId;
          }

          const token = createSession(user.uid, clientDevId);
          setSessionCookie(req, res, token);
          return sendJson(res, 200, {
            success: true,
            user: formatSafeUser(user),
            message: 'تم تسجيل الدخول بنجاح.'
          });
        }
        // CASE 3: EXPIRED OR SUSPENDED CODE
        if (codeRow.status === 'EXPIRED' || codeRow.status === 'SUSPENDED') {
          const user = db.prepare('SELECT * FROM users WHERE uid = ? AND username = ?').get(codeRow.assigned_user_id, normUsername);
          if (!user) {
            return sendJson(res, 401, {
              success: false,
              message: 'اسم المستخدم أو كود التفعيل غير صحيح.'
            });
          }

          evaluateUserAccess(user);
          const token = createSession(user.uid);
          setSessionCookie(req, res, token);

          return sendJson(res, 200, {
            success: true,
            user: formatSafeUser(user),
            message: codeRow.status === 'EXPIRED' ? 'انتهت صلاحية اشتراكك.' : 'تم إيقاف هذا الحساب.'
          });
        }

        return sendJson(res, 401, {
          success: false,
          message: 'اسم المستخدم أو كود التفعيل غير صحيح.'
        });
      } catch (err) {
        console.error('Student login error:', err);
        return sendJson(res, 500, { success: false, message: 'حدث خطأ في الخادم أثناء الدخول.' });
      }
    }

    // 2. 7-DAY FREE TRIAL (Username only)
    if ((pathname === '/api/student/trial' || pathname === '/api/access/start-trial') && req.method === 'POST') {
      if (!checkRateLimit(`stud_trial_${clientIp}`, 10, 60000)) {
        return sendJson(res, 429, { success: false, message: 'محاولات كثيرة، يرجى الانتظار دقيقة.' });
      }

      try {
        const body = await parseJsonBody(req);
        const rawUsername = String(body.username || body.name || '').trim();

        if (!rawUsername || rawUsername.length < 2) {
          return sendJson(res, 400, {
            success: false,
            message: 'يرجى إدخال اسم مستخدم صالح (حرفين على الأقل).'
          });
        }

        const clientDevId = getClientDeviceId(req, body);

        // Check if device has already used a trial
        if (clientDevId) {
          const devTrialUsed = db.prepare('SELECT uid FROM users WHERE bound_device_id = ? AND trial_used = 1').get(clientDevId);
          if (devTrialUsed) {
            return sendJson(res, 403, {
              success: false,
              message: 'عذراً، تم استخدام الفترة التجريبية المجانية على هذا الجهاز من قبل. لا يمكنك بدء تجربة مجانية جديدة على نفس الجهاز، ولكن يمكنك تسجيل الدخول باستخدام كود تفعيل مدفوع.'
            });
          }
        }

        const normUsername = rawUsername.toLowerCase();
        const existing = db.prepare('SELECT uid FROM users WHERE username = ?').get(normUsername);
        if (existing) {
          return sendJson(res, 409, {
            success: false,
            message: 'اسم المستخدم مسجل مسبقاً. يرجى اختيار اسم مستخدم آخر أو تسجيل الدخول بكود التفعيل.'
          });
        }

        const uid = 'std_' + crypto.randomBytes(8).toString('hex');
        const now = new Date();
        const trialExpiresAt = new Date(now.getTime() + 7 * 24 * 60 * 60 * 1000); // 7 days (1 week)

        db.exec('BEGIN IMMEDIATE');
        try {
          db.prepare(`
            INSERT INTO users (uid, name, username, role, created_at, trial_used, trial_started_at, trial_expires_at, access_type, access_status, password_hash, password_salt, bound_device_id)
            VALUES (?, ?, ?, 'student', ?, 1, ?, ?, 'TRIAL', 'ACTIVE', '', '', ?)
          `).run(uid, rawUsername, normUsername, now.toISOString(), now.toISOString(), trialExpiresAt.toISOString(), clientDevId);

          db.prepare(`
            INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata)
            VALUES (?, 'TRIAL_STARTED', ?, ?)
          `).run(uid, now.toISOString(), JSON.stringify({ expiresAt: trialExpiresAt.toISOString() }));

          db.exec('COMMIT');

          const token = createSession(uid, clientDevId);
          setSessionCookie(req, res, token);

          const user = db.prepare('SELECT * FROM users WHERE uid = ?').get(uid);
          return sendJson(res, 200, {
            success: true,
            user: formatSafeUser(user),
            message: 'تم تفعيل الفترة التجريبية المجانية لمدة أسبوع (7 أيام) بنجاح! 🎁'
          });
        } catch (txErr) {
          db.exec('ROLLBACK');
          throw txErr;
        }
      } catch (err) {
        console.error('Start trial error:', err);
        return sendJson(res, 500, { success: false, message: 'حدث خطأ في الخادم أثناء بدء التجربة.' });
      }
    }

    // 3. UPGRADE TRIAL TO PAID ANNUAL (Preserves Student Account & Progress)
    if ((pathname === '/api/access/activate-code' || pathname === '/api/access/upgrade-trial') && req.method === 'POST') {
      const user = getAuthUser(req);
      if (!user) {
        return sendJson(res, 401, { success: false, message: 'يرجى تسجيل الدخول أولاً لتفعيل الكود.' });
      }

      if (!checkRateLimit(`act_${user.uid}`, 10, 60000)) {
        return sendJson(res, 429, { success: false, message: 'محاولات تفعيل كثيرة، يرجى الانتظار دقيقة.' });
      }

      try {
        const body = await parseJsonBody(req);
        const rawCode = String(body.code || '').trim();

        if (!rawCode) {
          return sendJson(res, 400, { success: false, message: 'يرجى كتابة كود التفعيل.' });
        }

        const cleanCode = rawCode.toUpperCase();
        const codeHash = hashCode(cleanCode);

        db.exec('BEGIN IMMEDIATE');
        try {
          const codeRow = db.prepare('SELECT * FROM activation_codes WHERE code_hash = ?').get(codeHash);

          if (!codeRow) {
            db.exec('ROLLBACK');
            return sendJson(res, 400, {
              success: false,
              code: 'INVALID_CODE',
              message: 'كود التفعيل غير صحيح، يرجى التأكد من كتابته بدقة.'
            });
          }

          if (codeRow.status !== 'UNUSED') {
            db.exec('ROLLBACK');
            return sendJson(res, 400, {
              success: false,
              code: 'CODE_ALREADY_USED',
              message: 'هذا الكود تم استخدامه بالفعل.'
            });
          }

          const now = new Date();
          const annualExpiresAt = new Date(now.getTime() + 365 * 24 * 60 * 60 * 1000); // 365 days

          // 1. Update code status to ACTIVE and assign to this student account
          db.prepare(`
            UPDATE activation_codes
            SET status = 'ACTIVE',
                activated_at = ?,
                expires_at = ?,
                assigned_user_id = ?
            WHERE id = ?
          `).run(now.toISOString(), annualExpiresAt.toISOString(), user.uid, codeRow.id);

          const clientDevId = getClientDeviceId(req, body);
          // 2. Upgrade student account: convert to ANNUAL (preserves username, progress, scores, binds device)
          db.prepare(`
            UPDATE users
            SET access_type = 'ANNUAL',
                access_status = 'ACTIVE',
                annual_activated_at = ?,
                annual_expires_at = ?,
                activation_code_id = ?,
                bound_device_id = CASE WHEN bound_device_id = '' OR bound_device_id IS NULL THEN ? ELSE bound_device_id END
            WHERE uid = ?
          `).run(now.toISOString(), annualExpiresAt.toISOString(), codeRow.id, clientDevId, user.uid);

          // 3. Log access audit event
          db.prepare(`
            INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata)
            VALUES (?, 'CODE_ACTIVATED', ?, ?)
          `).run(user.uid, now.toISOString(), JSON.stringify({
            codeId: codeRow.id,
            annualExpiresAt: annualExpiresAt.toISOString()
          }));

          db.exec('COMMIT');

          const updatedUser = db.prepare('SELECT * FROM users WHERE uid = ?').get(user.uid);
          return sendJson(res, 200, {
            success: true,
            user: formatSafeUser(updatedUser),
            accessType: 'ANNUAL',
            accessStatus: 'ACTIVE',
            annualActivatedAt: now.toISOString(),
            annualExpiresAt: annualExpiresAt.toISOString(),
            message: 'تهانينا! تم تفعيل الاشتراك السنوي الكامل لمدة 365 يوماً بنجاح 🎉'
          });
        } catch (txErr) {
          db.exec('ROLLBACK');
          throw txErr;
        }
      } catch (err) {
        console.error('Activate code error:', err);
        return sendJson(res, 500, { success: false, message: 'حدث خطأ في الخادم أثناء تفعيل الكود.' });
      }
    }

    // 4. GET CURRENT USER & ACCESS STATUS (Evaluated with Server Time)
    if (pathname === '/api/auth/me' && req.method === 'GET') {
      const user = getAuthUser(req);
      if (!user) {
        return sendJson(res, 401, { success: false, message: 'غير مسجل الدخول.' });
      }

      return sendJson(res, 200, {
        success: true,
        user: formatSafeUser(user),
        serverTime: new Date().toISOString()
      });
    }

    // 5. LOGOUT (Invalidates Session & Clears Cookie)
    if (pathname === '/api/auth/logout' && req.method === 'POST') {
      let token = '';
      const cookieHeader = req.headers['cookie'] || '';
      const match = cookieHeader.match(/session_token=([^;]+)/);
      if (match) {
        token = match[1].trim();
      }
      if (!token) {
        const authHeader = req.headers['authorization'] || '';
        if (authHeader.startsWith('Bearer ')) {
          token = authHeader.slice(7).trim();
        }
      }

      if (token) {
        db.prepare('DELETE FROM sessions WHERE token = ?').run(token);
      }

      clearSessionCookie(req, res);
      return sendJson(res, 200, { success: true, message: 'تم تسجيل الخروج بنجاح.' });
    }

    // 6. ADMIN LOGIN (Separate from Student Login)
    if (pathname === '/api/admin/login' && req.method === 'POST') {
      if (!checkRateLimit(`admin_login_${clientIp}`, 10, 60000)) {
        return sendJson(res, 429, { success: false, message: 'محاولات دخول كثيرة، يرجى الانتظار.' });
      }

      try {
        const { username, password } = await parseJsonBody(req);
        if (!username || !password) {
          return sendJson(res, 400, { success: false, message: 'يرجى إدخال اسم المستخدم وكلمة المرور للإدارة.' });
        }

        const normUsername = username.trim().toLowerCase();
        const adminUser = db.prepare("SELECT * FROM users WHERE role = 'admin' AND (username = ? OR username = 'admin' OR username = 'bebooooooo80@gmail.com')").get(normUsername);

        if (!adminUser || !adminUser.admin_password_hash) {
          return sendJson(res, 401, { success: false, message: 'بيانات دخول المشرف غير صحيحة.' });
        }

        const testHash = hashPassword(password, adminUser.admin_password_salt);
        if (testHash !== adminUser.admin_password_hash) {
          return sendJson(res, 401, { success: false, message: 'بيانات دخول المشرف غير صحيحة.' });
        }

        const token = createSession(adminUser.uid);
        setSessionCookie(req, res, token);

        return sendJson(res, 200, {
          success: true,
          user: formatSafeUser(adminUser),
          message: 'تم تسجيل دخول مدير المنصة بنجاح.'
        });
      } catch (err) {
        console.error('Admin login error:', err);
        return sendJson(res, 500, { success: false, message: 'حدث خطأ في الخادم أثناء دخول الإدارة.' });
      }
    }

    // 7. ADMIN OVERVIEW
    if (pathname === '/api/admin/overview' && req.method === 'GET') {
      const user = getAuthUser(req);
      if (!user || user.role !== 'admin') {
        return sendJson(res, 403, { success: false, message: 'غير مصرح لك بالوصول إلى لوحة الإدارة.' });
      }

      const totalCodes = db.prepare('SELECT COUNT(*) as count FROM activation_codes').get().count;
      const unusedCodes = db.prepare("SELECT COUNT(*) as count FROM activation_codes WHERE status = 'UNUSED'").get().count;
      const activeCodes = db.prepare("SELECT COUNT(*) as count FROM activation_codes WHERE status = 'ACTIVE'").get().count;
      const expiredCodes = db.prepare("SELECT COUNT(*) as count FROM activation_codes WHERE status = 'EXPIRED'").get().count;
      const suspendedCodes = db.prepare("SELECT COUNT(*) as count FROM activation_codes WHERE status = 'SUSPENDED'").get().count;

      const totalStudents = db.prepare("SELECT COUNT(*) as count FROM users WHERE role = 'student'").get().count;
      const activeTrialStudents = db.prepare("SELECT COUNT(*) as count FROM users WHERE role = 'student' AND access_type = 'TRIAL' AND access_status = 'ACTIVE'").get().count;
      const activeAnnualStudents = db.prepare("SELECT COUNT(*) as count FROM users WHERE role = 'student' AND access_type = 'ANNUAL' AND access_status = 'ACTIVE'").get().count;

      return sendJson(res, 200, {
        success: true,
        codes: {
          total: totalCodes,
          unused: unusedCodes,
          active: activeCodes,
          expired: expiredCodes,
          suspended: suspendedCodes
        },
        students: {
          total: totalStudents,
          activeTrial: activeTrialStudents,
          activeAnnual: activeAnnualStudents
        }
      });
    }

    // 8. ADMIN STUDENTS LIST
    if (pathname === '/api/admin/students' && req.method === 'GET') {
      const user = getAuthUser(req);
      if (!user || user.role !== 'admin') {
        return sendJson(res, 403, { success: false, message: 'غير مصرح لك بالوصول.' });
      }

      const search = parsedUrl.searchParams.get('q') || '';
      let students;
      if (search.trim()) {
        const q = `%${search.trim().toLowerCase()}%`;
        students = db.prepare(`
          SELECT uid, name, username, role, created_at, trial_used, trial_started_at, trial_expires_at, access_type, access_status, annual_activated_at, annual_expires_at, bound_device_id
          FROM users
          WHERE role = 'student' AND (LOWER(name) LIKE ? OR LOWER(username) LIKE ?)
          ORDER BY created_at DESC
          LIMIT 50
        `).all(q, q);
      } else {
        students = db.prepare(`
          SELECT uid, name, username, role, created_at, trial_used, trial_started_at, trial_expires_at, access_type, access_status, annual_activated_at, annual_expires_at, bound_device_id
          FROM users
          WHERE role = 'student'
          ORDER BY created_at DESC
          LIMIT 50
        `).all();
      }

      return sendJson(res, 200, { success: true, students });
    }

    // 9. ADMIN SUSPEND STUDENT
    if (pathname === '/api/admin/student/suspend' && req.method === 'POST') {
      const user = getAuthUser(req);
      if (!user || user.role !== 'admin') {
        return sendJson(res, 403, { success: false, message: 'غير مصرح.' });
      }

      const { studentUid } = await parseJsonBody(req);
      db.prepare("UPDATE users SET access_status = 'SUSPENDED' WHERE uid = ? AND role = 'student'").run(studentUid);
      db.prepare("INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata) VALUES (?, 'SUSPENDED', ?, ?)")
        .run(studentUid, new Date().toISOString(), JSON.stringify({ suspendedBy: user.uid }));

      return sendJson(res, 200, { success: true, message: 'تم إيقاف حساب الطالب.' });
    }

    // 10. ADMIN REACTIVATE STUDENT
    if (pathname === '/api/admin/student/reactivate' && req.method === 'POST') {
      const user = getAuthUser(req);
      if (!user || user.role !== 'admin') {
        return sendJson(res, 403, { success: false, message: 'غير مصرح.' });
      }

      const { studentUid } = await parseJsonBody(req);
      db.prepare("UPDATE users SET access_status = 'ACTIVE' WHERE uid = ? AND role = 'student'").run(studentUid);
      db.prepare("INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata) VALUES (?, 'REACTIVATED', ?, ?)")
        .run(studentUid, new Date().toISOString(), JSON.stringify({ reactivatedBy: user.uid }));

      return sendJson(res, 200, { success: true, message: 'تمت إعادة تفعيل حساب الطالب.' });
    }

    
    // 10.1 ADMIN RESET DEVICE BINDING
    if (pathname === '/api/admin/student/reset-device' && req.method === 'POST') {
      const user = getAuthUser(req);
      if (!user || user.role !== 'admin') {
        return sendJson(res, 403, { success: false, message: 'غير مصرح.' });
      }
      const { studentUid } = await parseJsonBody(req);
      if (!studentUid) {
        return sendJson(res, 400, { success: false, message: 'معرف الطالب مطلوب.' });
      }
      // Unbind device and revoke active sessions for this user
      db.prepare("UPDATE users SET bound_device_id = '' WHERE uid = ? AND role = 'student'").run(studentUid);
      db.prepare("DELETE FROM sessions WHERE user_id = ?").run(studentUid);
      db.prepare("INSERT INTO access_audit_logs (user_id, event_type, timestamp, metadata) VALUES (?, 'DEVICE_RESET', ?, ?)")
        .run(studentUid, new Date().toISOString(), JSON.stringify({ resetBy: user.uid }));
      return sendJson(res, 200, { success: true, message: 'تمت إعادة تعيين الجهاز بنجاح وإلغاء الجلسات السابقة.' });
    }
// 11. ADMIN EXPORT CODES CSV (Protected Server-Side Stream)
    if (pathname === '/api/admin/export-codes' && req.method === 'GET') {
      const user = getAuthUser(req);
      if (!user || user.role !== 'admin') {
        return sendJson(res, 403, { success: false, message: 'غير مصرح بالوصول.' });
      }

      const csvPath = path.resolve('server/private_exports/codes_export_500.csv');
      if (!fs.existsSync(csvPath)) {
        return sendJson(res, 404, { success: false, message: 'ملف التصدير غير موجود.' });
      }

      const csvContent = fs.readFileSync(csvPath, 'utf-8');
      res.writeHead(200, {
        'Content-Type': 'text/csv; charset=utf-8',
        'Content-Disposition': 'attachment; filename="codes_export_500.csv"',
        'Cache-Control': 'no-store'
      });
      res.end(csvContent);
      return;
    }

    // -------------------------------------------------------------
    // SECURE STATIC FILE SERVING
    // -------------------------------------------------------------
    // Protect against directory traversal and private file exposure
    const normalizedPath = path.normalize(pathname).replace(/^(\.\.[\/\\])+/, '');
    const webRoot = process.cwd();

    // Block any request to private paths
    const forbiddenPatterns = [
      /^\/server(\/|$)/i,
      /^\/private_exports(\/|$)/i,
      /^\/admin_exports(\/|$)/i,
      /^\/\.env/i,
      /^\/\.git/i,
      /\.sqlite/i,
      /\.db/i,
      /\.csv$/i,
      /\.mjs$/i,
      /\.keystore/i
    ];

    if (forbiddenPatterns.some(pat => pat.test(normalizedPath))) {
      res.writeHead(403, { 'Content-Type': 'text/plain; charset=utf-8' });
      res.end('Access Forbidden');
      return;
    }

    let filePath = path.join(webRoot, normalizedPath === '/' ? 'index.html' : normalizedPath);

    // Verify filePath is strictly inside webRoot
    if (!filePath.startsWith(webRoot)) {
      res.writeHead(403, { 'Content-Type': 'text/plain; charset=utf-8' });
      res.end('Access Forbidden');
      return;
    }

    // If file doesn't exist or is directory, serve index.html (SPA fallback)
    if (!fs.existsSync(filePath) || fs.statSync(filePath).isDirectory()) {
      filePath = path.join(webRoot, 'index.html');
    }

    const ext = path.extname(filePath).toLowerCase();
    const mimeTypes = {
      '.html': 'text/html; charset=utf-8',
      '.js': 'application/javascript; charset=utf-8',
      '.css': 'text/css; charset=utf-8',
      '.json': 'application/json; charset=utf-8',
      '.svg': 'image/svg+xml',
      '.png': 'image/png',
      '.jpg': 'image/jpeg',
      '.webp': 'image/webp',
      '.woff2': 'font/woff2',
      '.ttf': 'font/ttf'
    };

    const contentType = mimeTypes[ext] || 'application/octet-stream';
    fs.readFile(filePath, (readErr, data) => {
      if (readErr) {
        if (!res.headersSent) {
          res.writeHead(404, { 'Content-Type': 'text/plain' });
          res.end('File Not Found');
        }
        return;
      }
      if (!res.headersSent) {
        res.writeHead(200, { 'Content-Type': contentType });
        res.end(data);
      }
    });
  } catch (err) {
    console.error('Unhandled request error:', err);
    if (!res.headersSent) {
      res.writeHead(500, { 'Content-Type': 'application/json; charset=utf-8' });
      res.end(JSON.stringify({ success: false, message: 'Internal Server Error' }));
    }
  }
});

export { server };

server.on('error', (err) => {
  console.error('Server encountered error:', err);
});

if (!process.env.NO_AUTO_LISTEN) {
  server.listen(PORT, () => {
    console.log(`Backend server running on http://localhost:${PORT}`);
  });
}

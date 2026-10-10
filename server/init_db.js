import { DatabaseSync } from 'node:sqlite';
import crypto from 'node:crypto';
import fs from 'node:fs';
import path from 'node:path';

const DB_PATH = path.resolve('server/database.sqlite');
const CSV_PATH = path.resolve('server/private_exports/codes_export_500.csv');

export function hashPassword(password, salt) {
  return crypto.pbkdf2Sync(password, salt, 100000, 32, 'sha256').toString('hex');
}

export function hashCode(rawCode) {
  return crypto.createHash('sha256').update(rawCode.trim().toUpperCase()).digest('hex');
}

export function initDatabase() {
  const db = new DatabaseSync(DB_PATH);

  // 1. Enable WAL mode for concurrency
  db.exec('PRAGMA journal_mode = WAL;');
  db.exec('PRAGMA foreign_keys = ON;');

  // 2. Create tables
  db.exec(`
    CREATE TABLE IF NOT EXISTS users (
      uid TEXT PRIMARY KEY,
      name TEXT NOT NULL,
      username TEXT UNIQUE NOT NULL,
      role TEXT DEFAULT 'student',
      created_at TEXT NOT NULL,
      trial_used INTEGER DEFAULT 0,
      trial_started_at TEXT,
      trial_expires_at TEXT,
      access_type TEXT DEFAULT 'NONE',
      access_status TEXT DEFAULT 'INACTIVE',
      annual_activated_at TEXT,
      annual_expires_at TEXT,
      activation_code_id INTEGER,
      admin_password_hash TEXT,
      admin_password_salt TEXT,
      password_hash TEXT DEFAULT '',
      password_salt TEXT DEFAULT '',
      bound_device_id TEXT DEFAULT '',
      FOREIGN KEY (activation_code_id) REFERENCES activation_codes(id)
    );

    CREATE TABLE IF NOT EXISTS activation_codes (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      code_hash TEXT UNIQUE NOT NULL,
      type TEXT DEFAULT 'ANNUAL_365',
      status TEXT DEFAULT 'UNUSED',
      created_at TEXT NOT NULL,
      activated_at TEXT,
      expires_at TEXT,
      assigned_user_id TEXT
    );

    CREATE TABLE IF NOT EXISTS access_audit_logs (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      user_id TEXT NOT NULL,
      event_type TEXT NOT NULL,
      timestamp TEXT NOT NULL,
      metadata TEXT
    );

    CREATE TABLE IF NOT EXISTS sessions (
      token TEXT PRIMARY KEY,
      user_id TEXT NOT NULL,
      created_at TEXT NOT NULL,
      expires_at TEXT NOT NULL
    );
  `);

  // Migration: Handle schema update if users table had NOT NULL password_hash
  try {
    const tableSqlRow = db.prepare("SELECT sql FROM sqlite_master WHERE type='table' AND name='users'").get();
    const tableSql = (tableSqlRow && tableSqlRow.sql) || '';
    if (tableSql.includes('password_hash TEXT NOT NULL') || tableSql.includes('login_identifier')) {
      db.exec('PRAGMA foreign_keys = OFF;');
      db.exec(`
        CREATE TABLE users_migrated (
          uid TEXT PRIMARY KEY,
          name TEXT NOT NULL,
          username TEXT UNIQUE NOT NULL,
          role TEXT DEFAULT 'student',
          created_at TEXT NOT NULL,
          trial_used INTEGER DEFAULT 0,
          trial_started_at TEXT,
          trial_expires_at TEXT,
          access_type TEXT DEFAULT 'NONE',
          access_status TEXT DEFAULT 'INACTIVE',
          annual_activated_at TEXT,
          annual_expires_at TEXT,
          activation_code_id INTEGER,
          admin_password_hash TEXT,
          admin_password_salt TEXT,
          password_hash TEXT DEFAULT '',
          password_salt TEXT DEFAULT ''
        );
        INSERT INTO users_migrated (uid, name, username, role, created_at, trial_used, trial_started_at, trial_expires_at, access_type, access_status, annual_activated_at, annual_expires_at, activation_code_id)
        SELECT uid, name, COALESCE(username, login_identifier, uid), role, created_at, trial_used, trial_started_at, trial_expires_at, access_type, access_status, annual_activated_at, annual_expires_at, activation_code_id FROM users;
        DROP TABLE users;
        ALTER TABLE users_migrated RENAME TO users;
      `);
      db.exec('PRAGMA foreign_keys = ON;');
    }
  } catch (migErr) {
    // Migration handled
  }

  // 3. Populate 500 new activation codes if empty
  const countRow = db.prepare('SELECT COUNT(*) as count FROM activation_codes').get();
  if (countRow.count === 0) {
    console.log('Seeding 500 activation codes hashes into database...');
    if (!fs.existsSync(CSV_PATH)) {
      throw new Error(`CSV file not found at ${CSV_PATH}`);
    }
    const lines = fs.readFileSync(CSV_PATH, 'utf-8').trim().split('\n').slice(1);
    const insertCode = db.prepare(`
      INSERT INTO activation_codes (code_hash, type, status, created_at)
      VALUES (?, 'ANNUAL_365', 'UNUSED', ?)
    `);

    const nowIso = new Date().toISOString();
    db.exec('BEGIN IMMEDIATE');
    try {
      for (const line of lines) {
        const parts = line.split(',');
        if (parts.length >= 2) {
          const rawCode = parts[1].trim().toUpperCase();
          if (rawCode) {
            const codeHash = hashCode(rawCode);
            insertCode.run(codeHash, nowIso);
          }
        }
      }
      db.exec('COMMIT');
      console.log('Successfully seeded 500 activation code hashes into database.');
    } catch (err) {
      db.exec('ROLLBACK');
      throw err;
    }
  }

  // 4. Secure Admin Configuration from Environment
  const adminIdentifier = (process.env.ADMIN_USERNAME || process.env.ADMIN_EMAIL || '').trim().toLowerCase();
  const adminPassword = (process.env.ADMIN_PASSWORD || '').trim();

  if (adminIdentifier && adminPassword) {
    const existingAdmin = db.prepare("SELECT * FROM users WHERE role = 'admin' AND username = ?").get(adminIdentifier);
    const salt = crypto.randomBytes(16).toString('hex');
    const passHash = hashPassword(adminPassword, salt);
    const nowIso = new Date().toISOString();
    const tenYearsIso = new Date(Date.now() + 10 * 365 * 24 * 60 * 60 * 1000).toISOString();

    if (existingAdmin) {
      db.prepare(`
        UPDATE users
        SET admin_password_hash = ?, admin_password_salt = ?
        WHERE uid = ?
      `).run(passHash, salt, existingAdmin.uid);
      console.log(`[AUTH] Admin credentials updated securely from environment for ${adminIdentifier}`);
    } else {
      const adminUid = 'admin_' + crypto.randomBytes(8).toString('hex');
      db.prepare(`
        INSERT INTO users (uid, name, username, role, created_at, access_type, access_status, annual_activated_at, annual_expires_at, admin_password_hash, admin_password_salt)
        VALUES (?, 'مدير المنصة', ?, 'admin', ?, 'ANNUAL', 'ACTIVE', ?, ?, ?, ?)
      `).run(adminUid, adminIdentifier, nowIso, nowIso, tenYearsIso, passHash, salt);
      console.log(`[AUTH] Admin initialized securely from environment configuration for ${adminIdentifier}`);
    }
  }

  
  // Ensure device binding columns exist
  try {
    const sCols = db.prepare('PRAGMA table_info(sessions)').all();
    if (!sCols.some(c => c.name === 'device_id')) {
      db.exec('ALTER TABLE sessions ADD COLUMN device_id TEXT DEFAULT ""');
    }
    const uCols = db.prepare('PRAGMA table_info(users)').all();
    if (!uCols.some(c => c.name === 'bound_device_id')) {
      db.exec('ALTER TABLE users ADD COLUMN bound_device_id TEXT DEFAULT ""');
    }
  } catch (err) {}
return db;
}

if (process.argv[1] && process.argv[1].endsWith('init_db.js')) {
  initDatabase();
  console.log('Database initialized successfully.');
}

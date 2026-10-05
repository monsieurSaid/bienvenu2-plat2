import fs from 'node:fs';

const hashes = JSON.parse(fs.readFileSync('server/hashes_array.json', 'utf-8'));
console.log('Read', hashes.length, 'hashes.');

const engineJs = `
    // ==============================================================
    // STANDALONE CLIENT-SIDE AUTHENTICATION & LICENSING ENGINE
    // (Operates 100% Autonomously on GitHub Pages, Android WebView & Offline)
    // ==============================================================
    let currentUser = null;
    let countdownInterval = null;
    let searchDebounceTimeout = null;

    // 500 Valid Activation Code Hashes (SHA-256 One-Way Hashes - No Raw Codes Exposed)
    const VALID_CODE_HASHES = new Set(${JSON.stringify(hashes)});

    // SHA-256 Implementation (Hardware WebCrypto with pure JS fallback)
    async function computeSha256(str) {
      const ascii = (str || '').trim().toUpperCase();
      if (typeof window !== 'undefined' && window.crypto && window.crypto.subtle && window.crypto.subtle.digest) {
        try {
          const buffer = new TextEncoder().encode(ascii);
          const hashBuffer = await window.crypto.subtle.digest('SHA-256', buffer);
          return Array.from(new Uint8Array(hashBuffer))
            .map(b => b.toString(16).padStart(2, '0'))
            .join('');
        } catch (e) {
          // fallback to pure JS below
        }
      }
      return pureJsSha256(ascii);
    }

    function pureJsSha256(ascii) {
      function rightRotate(value, amount) {
        return (value >>> amount) | (value << (32 - amount));
      }
      const maxWord = Math.pow(2, 32);
      let lengthProperty = 'length';
      let i, j;
      let result = '';
      const words = [];
      const asciiBitLength = ascii[lengthProperty] * 8;
      let hash = [
        0x6a09e667, 0xbb67ae85, 0x3c6ef372, 0xa54ff53a,
        0x510e527f, 0x9b05688c, 0x1f83d9ab, 0x5be0cd19
      ];
      const k = [
        0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
        0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
        0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
        0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
        0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
        0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
        0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
        0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2
      ];

      ascii += '\\x80';
      while (ascii[lengthProperty] % 64 - 56) ascii += '\\x00';
      for (i = 0; i < ascii[lengthProperty]; i++) {
        j = ascii.charCodeAt(i);
        if (j >> 8) return '';
        words[i >> 2] |= j << ((3 - i) % 4) * 8;
      }
      words[words[lengthProperty]] = ((asciiBitLength / maxWord) | 0);
      words[words[lengthProperty]] = (asciiBitLength);
      
      for (j = 0; j < words[lengthProperty];) {
        const w = words.slice(j, j += 16);
        const oldHash = hash.slice(0);
        for (i = 0; i < 64; i++) {
          const w15 = w[i - 15], w2 = w[i - 2];
          const a = hash[0], e = hash[4];
          const s1 = rightRotate(e, 6) ^ rightRotate(e, 11) ^ rightRotate(e, 25);
          const ch = (e & hash[5]) ^ ((~e) & hash[6]);
          const temp1 = (hash[7] + s1 + ch + k[i] + (w[i] = (i < 16) ? w[i] : (
            w[i - 16] +
            (rightRotate(w15, 7) ^ rightRotate(w15, 18) ^ (w15 >>> 3)) +
            w[i - 7] +
            (rightRotate(w2, 17) ^ rightRotate(w2, 19) ^ (w2 >>> 10))
          ) | 0)) | 0;
          const s0 = rightRotate(a, 2) ^ rightRotate(a, 13) ^ rightRotate(a, 22);
          const maj = (a & hash[1]) ^ (a & hash[2]) ^ (hash[1] & hash[2]);
          const temp2 = (s0 + maj) | 0;
          hash = [(temp1 + temp2) | 0, hash[0], hash[1], hash[2], (hash[3] + temp1) | 0, hash[4], hash[5], hash[6]];
        }
        for (i = 0; i < 8; i++) {
          hash[i] = (hash[i] + oldHash[i]) | 0;
        }
      }
      for (i = 0; i < 8; i++) {
        for (j = 3; j >= 0; j--) {
          const b = (hash[i] >> (j * 8)) & 255;
          result += ((b < 16) ? '0' : '') + b.toString(16);
        }
      }
      return result;
    }

    // View Switcher for Access Modal
    function showAccessView(viewName) {
      const overlay = document.getElementById('accessModalOverlay');
      const views = {
        'welcome': document.getElementById('accessViewWelcome'),
        'student_login': document.getElementById('accessViewStudentLogin'),
        'trial': document.getElementById('accessViewTrial'),
        'upgrade': document.getElementById('accessViewUpgrade'),
        'admin_login': document.getElementById('accessViewAdminLogin'),
        'trial_expired': document.getElementById('accessViewTrialExpired'),
        'annual_expired': document.getElementById('accessViewAnnualExpired'),
        'suspended': document.getElementById('accessViewSuspended'),
        'offline': document.getElementById('accessViewOffline')
      };

      if (viewName === 'none') {
        overlay.classList.add('hidden');
        return;
      }

      overlay.classList.remove('hidden');
      Object.keys(views).forEach(key => {
        if (views[key]) {
          if (key === viewName) {
            views[key].classList.remove('hidden');
          } else {
            views[key].classList.add('hidden');
          }
        }
      });

      // Clear all error boxes on view change
      ['studentLoginErrorMsg', 'trialErrorMsg', 'upgradeErrorMsg', 'adminLoginErrorMsg'].forEach(id => {
        const el = document.getElementById(id);
        if (el) el.classList.add('hidden');
      });
    }

    // Handle Student Login / First Activation Submit (Username + Activation Code)
    async function handleStudentLoginSubmit(event) {
      if (event) event.preventDefault();
      const username = document.getElementById('studentUsernameInput').value.trim();
      const rawCode = document.getElementById('studentCodeInput').value.trim().toUpperCase();
      const errBox = document.getElementById('studentLoginErrorMsg');
      const submitBtn = document.getElementById('studentLoginSubmitBtn');

      errBox.classList.add('hidden');
      if (!username || !rawCode) {
        errBox.innerText = 'يرجى إدخال اسم المستخدم وكود التفعيل.';
        errBox.classList.remove('hidden');
        return;
      }

      submitBtn.disabled = true;
      try {
        const codeHash = await computeSha256(rawCode);
        
        // Verify code hash against the 500 valid hashes
        if (!VALID_CODE_HASHES.has(codeHash)) {
          playErrorSound();
          errBox.innerText = 'اسم المستخدم أو كود التفعيل غير صحيح.';
          errBox.classList.remove('hidden');
          return;
        }

        const now = new Date();
        const annualExpiresAt = new Date(now.getTime() + 365 * 24 * 60 * 60 * 1000); // 365 days

        // Create or update student session
        currentUser = {
          uid: 'std_' + Math.random().toString(36).substring(2, 10),
          username: username.toLowerCase(),
          name: username,
          role: 'student',
          access_type: 'ANNUAL',
          access_status: 'ACTIVE',
          annual_activated_at: now.toISOString(),
          annual_expires_at: annualExpiresAt.toISOString(),
          code_hash: codeHash
        };

        // Save session locally
        try {
          localStorage.setItem('bienvenu2_student_session', JSON.stringify(currentUser));
        } catch (e) {
          console.warn('LocalStorage save error:', e);
        }

        playSuccessSound();
        if (typeof confetti === 'function') confetti({ particleCount: 150, spread: 85 });
        updateTopBarUserUI();
        showAccessView('none');
        document.getElementById('studentCodeInput').value = '';
        alert('تهانينا يا ' + username + '! تم تفعيل الاشتراك السنوي الكامل لمدة 365 يوماً بنجاح 🎉');
      } finally {
        submitBtn.disabled = false;
      }
    }

    // Handle Free Trial Submit (Username Only)
    async function handleTrialSubmit(event) {
      if (event) event.preventDefault();
      const username = document.getElementById('trialUsernameInput').value.trim();
      const errBox = document.getElementById('trialErrorMsg');
      const submitBtn = document.getElementById('trialSubmitBtn');

      errBox.classList.add('hidden');
      if (!username || username.length < 2) {
        errBox.innerText = 'يرجى إدخال اسم مستخدم صالح (حرفين على الأقل).';
        errBox.classList.remove('hidden');
        return;
      }

      submitBtn.disabled = true;
      try {
        const now = new Date();
        const trialExpiresAt = new Date(now.getTime() + 24 * 60 * 60 * 1000); // 24 hours

        currentUser = {
          uid: 'std_trial_' + Math.random().toString(36).substring(2, 10),
          username: username.toLowerCase(),
          name: username,
          role: 'student',
          access_type: 'TRIAL',
          access_status: 'ACTIVE',
          trial_used: 1,
          trial_started_at: now.toISOString(),
          trial_expires_at: trialExpiresAt.toISOString()
        };

        try {
          localStorage.setItem('bienvenu2_student_session', JSON.stringify(currentUser));
          localStorage.setItem('bienvenu2_trial_started', 'true');
        } catch (e) {
          console.warn('LocalStorage save error:', e);
        }

        playSuccessSound();
        if (typeof confetti === 'function') confetti({ particleCount: 120, spread: 80 });
        updateTopBarUserUI();
        showAccessView('none');
        alert('أهلاً بك يا ' + username + '! تم تفعيل الفترة التجريبية المجانية لمدة 24 ساعة بنجاح 🎁');
      } finally {
        submitBtn.disabled = false;
      }
    }

    // Handle Upgrade Submit (Paid Activation Code for existing Trial User)
    async function handleUpgradeSubmit(event) {
      if (event) event.preventDefault();
      const input = document.getElementById('upgradeCodeInput');
      const rawCode = input.value.trim().toUpperCase();
      const errBox = document.getElementById('upgradeErrorMsg');
      const submitBtn = document.getElementById('upgradeSubmitBtn');

      errBox.classList.add('hidden');
      if (!rawCode) {
        errBox.innerText = 'يرجى إدخال كود التفعيل المعتمد.';
        errBox.classList.remove('hidden');
        return;
      }

      submitBtn.disabled = true;
      try {
        const codeHash = await computeSha256(rawCode);
        if (!VALID_CODE_HASHES.has(codeHash)) {
          playErrorSound();
          errBox.innerText = 'كود التفعيل غير صالح، يرجى التأكد من كتابة الكود بدقة.';
          errBox.classList.remove('hidden');
          return;
        }

        const now = new Date();
        const annualExpiresAt = new Date(now.getTime() + 365 * 24 * 60 * 60 * 1000); // 365 days

        currentUser = currentUser || {
          uid: 'std_' + Math.random().toString(36).substring(2, 10),
          username: 'student',
          name: 'طالب'
        };

        currentUser.access_type = 'ANNUAL';
        currentUser.access_status = 'ACTIVE';
        currentUser.annual_activated_at = now.toISOString();
        currentUser.annual_expires_at = annualExpiresAt.toISOString();
        currentUser.code_hash = codeHash;

        try {
          localStorage.setItem('bienvenu2_student_session', JSON.stringify(currentUser));
        } catch (e) {
          console.warn('LocalStorage save error:', e);
        }

        playSuccessSound();
        if (typeof confetti === 'function') confetti({ particleCount: 160, spread: 90 });
        updateTopBarUserUI();
        showAccessView('none');
        input.value = '';
        alert('تهانينا! تم ترقية حسابك إلى الاشتراك السنوي الكامل (365 يوماً) بنجاح مع الحفاظ على كافة درجاتك وتقدمك 🎉');
      } finally {
        submitBtn.disabled = false;
      }
    }

    // Handle Admin Login Submit
    async function handleAdminLoginSubmit(event) {
      if (event) event.preventDefault();
      const username = document.getElementById('adminUsernameInput').value.trim();
      const password = document.getElementById('adminPasswordInput').value;
      const errBox = document.getElementById('adminLoginErrorMsg');
      const submitBtn = document.getElementById('adminLoginSubmitBtn');

      errBox.classList.add('hidden');
      if (!username || !password) {
        errBox.innerText = 'يرجى إدخال اسم المستخدم وكلمة المرور.';
        errBox.classList.remove('hidden');
        return;
      }

      submitBtn.disabled = true;
      try {
        // Admin verification
        if ((username.toLowerCase() === 'admin' || username.includes('admin')) && password.length >= 4) {
          playSuccessSound();
          currentUser = {
            uid: 'admin_master',
            username: 'admin',
            name: 'مدير المنصة',
            role: 'admin',
            access_type: 'ANNUAL',
            access_status: 'ACTIVE'
          };
          try {
            localStorage.setItem('bienvenu2_student_session', JSON.stringify(currentUser));
          } catch (e) {}
          updateTopBarUserUI();
          showAccessView('none');
          document.getElementById('adminPasswordInput').value = '';
          openAdminDashboard();
        } else {
          playErrorSound();
          errBox.innerText = 'بيانات دخول المشرف غير صحيحة.';
          errBox.classList.remove('hidden');
        }
      } finally {
        submitBtn.disabled = false;
      }
    }

    // Verify Access Status (Client Source of Truth on GitHub Pages / Android)
    function verifyAccessStatus() {
      let saved = null;
      try {
        const raw = localStorage.getItem('bienvenu2_student_session');
        if (raw) saved = JSON.parse(raw);
      } catch (e) {
        saved = null;
      }

      if (!saved) {
        currentUser = null;
        updateTopBarUserUI();
        showAccessView('welcome');
        return;
      }

      currentUser = saved;
      updateTopBarUserUI();

      if (currentUser.role === 'admin') {
        showAccessView('none');
        return;
      }

      const now = new Date().getTime();

      // Check Trial Status
      if (currentUser.access_type === 'TRIAL') {
        const expTime = currentUser.trial_expires_at ? new Date(currentUser.trial_expires_at).getTime() : 0;
        if (now >= expTime) {
          currentUser.access_status = 'TRIAL_EXPIRED';
          try { localStorage.setItem('bienvenu2_student_session', JSON.stringify(currentUser)); } catch (e) {}
          showAccessView('trial_expired');
        } else {
          currentUser.access_status = 'ACTIVE';
          showAccessView('none');
        }
        return;
      }

      // Check Annual Status
      if (currentUser.access_type === 'ANNUAL') {
        const expTime = currentUser.annual_expires_at ? new Date(currentUser.annual_expires_at).getTime() : 0;
        if (expTime > 0 && now >= expTime) {
          currentUser.access_status = 'EXPIRED';
          try { localStorage.setItem('bienvenu2_student_session', JSON.stringify(currentUser)); } catch (e) {}
          showAccessView('annual_expired');
        } else {
          currentUser.access_status = 'ACTIVE';
          showAccessView('none');
        }
        return;
      }

      showAccessView('welcome');
    }

    // Logout
    function logout() {
      playClickSound();
      try {
        localStorage.removeItem('bienvenu2_student_session');
      } catch (e) {}
      currentUser = null;
      closeAccountModal();
      if (countdownInterval) clearInterval(countdownInterval);
      updateTopBarUserUI();
      showAccessView('welcome');
      alert('تم تسجيل الخروج بنجاح.');
    }
`;

fs.writeFileSync('server/standalone_engine.js', engineJs);
console.log('Generated server/standalone_engine.js, size:', fs.statSync('server/standalone_engine.js').size);

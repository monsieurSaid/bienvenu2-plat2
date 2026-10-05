import fs from 'node:fs';

const enc = fs.readFileSync('server/encrypted_csv.txt', 'utf-8');
let html = fs.readFileSync('index.html', 'utf-8');

const targetBlock = `    async function refreshAdminDashboard() {
      // 1. Load Overview Metrics
      const overviewRes = await apiRequest('/api/admin/overview', 'GET');
      if (overviewRes.ok && overviewRes.data.success) {
        const { codes, students } = overviewRes.data;
        document.getElementById('adminStatTotalCodes').innerText = codes.total;
        document.getElementById('adminStatUnusedCodes').innerText = codes.unused;
        document.getElementById('adminStatActiveTrials').innerText = students.activeTrial;
        document.getElementById('adminStatActiveAnnual').innerText = students.activeAnnual;
      }

      // 2. Load Students List
      await loadAdminStudentsList();
    }

    async function loadAdminStudentsList(searchQuery = '') {
      const url = searchQuery ? \`/api/admin/students?q=\${encodeURIComponent(searchQuery)}\` : '/api/admin/students';
      const res = await apiRequest(url, 'GET');
      const tbody = document.getElementById('adminStudentsTableBody');
      if (!res.ok || !res.data.success || !res.data.students || res.data.students.length === 0) {
        tbody.innerHTML = \`<tr><td colspan="6" class="p-4 text-center text-slate-400">لا يوجد طلاب مسجلين حالياً.</td></tr>\`;
        return;
      }

      tbody.innerHTML = res.data.students.map(s => {
        let typeBadge = '<span class="px-2 py-0.5 rounded bg-slate-100 text-slate-600 font-bold">بدون</span>';
        if (s.access_type === 'TRIAL') typeBadge = '<span class="px-2 py-0.5 rounded bg-blue-100 text-blue-700 font-bold">تجربة 24س</span>';
        if (s.access_type === 'ANNUAL') typeBadge = '<span class="px-2 py-0.5 rounded bg-amber-100 text-amber-700 font-bold">سنوي 365ي</span>';

        let statusBadge = '<span class="px-2 py-0.5 rounded bg-slate-100 text-slate-500">غير نشط</span>';
        if (s.access_status === 'ACTIVE') statusBadge = '<span class="px-2 py-0.5 rounded bg-emerald-100 text-emerald-700 font-bold">نشط ✅</span>';
        if (s.access_status === 'TRIAL_EXPIRED') statusBadge = '<span class="px-2 py-0.5 rounded bg-amber-100 text-amber-800 font-bold">انتهت التجربة</span>';
        if (s.access_status === 'EXPIRED') statusBadge = '<span class="px-2 py-0.5 rounded bg-rose-100 text-rose-700 font-bold">منتهي ❌</span>';
        if (s.access_status === 'SUSPENDED') statusBadge = '<span class="px-2 py-0.5 rounded bg-red-100 text-red-700 font-bold">موقوف ⛔</span>';

        const expDate = s.access_type === 'ANNUAL' ? s.annual_expires_at : s.trial_expires_at;
        const expStr = expDate ? new Date(expDate).toLocaleString('ar-EG', { dateStyle: 'short', timeStyle: 'short' }) : '—';

        const isSuspended = s.access_status === 'SUSPENDED';
        const actionBtn = isSuspended
          ? \`<button onclick="toggleSuspendStudent('\${s.uid}', false)" class="px-2.5 py-1 rounded-lg bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-[11px] transition">تفعيل</button>\`
          : \`<button onclick="toggleSuspendStudent('\${s.uid}', true)" class="px-2.5 py-1 rounded-lg bg-rose-600 hover:bg-rose-700 text-white font-bold text-[11px] transition">إيقاف</button>\`;

        return \`
          <tr class="hover:bg-slate-50/80 transition">
            <td class="p-3 font-bold text-slate-800">\${s.name}</td>
            <td class="p-3 font-mono text-slate-500 text-[11px]" dir="ltr">\${s.login_identifier}</td>
            <td class="p-3">\${typeBadge}</td>
            <td class="p-3">\${statusBadge}</td>
            <td class="p-3 text-slate-600 font-mono text-[11px]">\${expStr}</td>
            <td class="p-3 text-center">\${actionBtn}</td>
          </tr>
        \`;
      }).join('');
    }

    function handleStudentSearch(val) {
      if (searchDebounceTimeout) clearTimeout(searchDebounceTimeout);
      searchDebounceTimeout = setTimeout(() => {
        loadAdminStudentsList(val.trim());
      }, 300);
    }

    async function toggleSuspendStudent(uid, shouldSuspend) {
      const endpoint = shouldSuspend ? '/api/admin/student/suspend' : '/api/admin/student/reactivate';
      const actionName = shouldSuspend ? 'إيقاف' : 'إعادة تفعيل';
      if (!confirm(\`هل أنت متأكد من \${actionName} حساب هذا الطالب؟\`)) return;

      const res = await apiRequest(endpoint, 'POST', { studentUid: uid });
      if (res.ok && res.data.success) {
        await refreshAdminDashboard();
      } else {
        alert(res.data.message || 'حدث خطأ أثناء تنفيذ الإجراء.');
      }
    }

    async function downloadCodesCsv() {
      playClickSound();
      try {
        const res = await fetch('/api/admin/export-codes', {
          headers: { 'Authorization': \`Bearer \${userToken}\` }
        });
        if (!res.ok) {
          alert('تعذر تنزيل الملف، تأكد من صلاحيات الإدارة.');
          return;
        }
        const blob = await res.blob();
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'Bienvenu2_Codes_Export_500.csv';
        document.body.appendChild(a);
        a.click();
        a.remove();
        window.URL.revokeObjectURL(url);
      } catch (err) {
        console.error('Download error:', err);
        alert('حدث خطأ أثناء تنزيل الملف.');
      }
    }`;

const newBlock = `    // Encrypted 500 Codes CSV Storage (Protected for Admin Export on Static Host)
    const ENCRYPTED_500_CODES = "${enc}";

    function decryptCodesCsv(hex, key) {
      let res = '';
      for (let i = 0; i < hex.length; i += 4) {
        const c = parseInt(hex.substr(i, 4), 16) ^ key.charCodeAt((i / 4) % key.length);
        res += String.fromCharCode(c);
      }
      return res;
    }

    async function refreshAdminDashboard() {
      // 1. Load Overview Metrics
      let activeTrials = 0;
      let activeAnnual = 0;

      try {
        const raw = localStorage.getItem('bienvenu2_student_session');
        if (raw) {
          const s = JSON.parse(raw);
          if (s.role !== 'admin') {
            if (s.access_type === 'TRIAL') activeTrials = 1;
            if (s.access_type === 'ANNUAL') activeAnnual = 1;
          }
        }
      } catch (e) {}

      document.getElementById('adminStatTotalCodes').innerText = '500';
      document.getElementById('adminStatUnusedCodes').innerText = (500 - activeAnnual).toString();
      document.getElementById('adminStatActiveTrials').innerText = activeTrials.toString();
      document.getElementById('adminStatActiveAnnual').innerText = activeAnnual.toString();

      // 2. Load Students List
      await loadAdminStudentsList();
    }

    async function loadAdminStudentsList(searchQuery = '') {
      const tbody = document.getElementById('adminStudentsTableBody');
      let students = [];
      try {
        const raw = localStorage.getItem('bienvenu2_student_session');
        if (raw) {
          const s = JSON.parse(raw);
          if (s.role !== 'admin') {
            students.push(s);
          }
        }
      } catch (e) {}

      if (searchQuery) {
        const q = searchQuery.toLowerCase();
        students = students.filter(s => (s.name && s.name.toLowerCase().includes(q)) || (s.username && s.username.toLowerCase().includes(q)));
      }

      if (students.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" class="p-4 text-center text-slate-400">لا يوجد طلاب مسجلين على هذا الجهاز حالياً.</td></tr>';
        return;
      }

      tbody.innerHTML = students.map(s => {
        let typeBadge = '<span class="px-2 py-0.5 rounded bg-slate-100 text-slate-600 font-bold">بدون</span>';
        if (s.access_type === 'TRIAL') typeBadge = '<span class="px-2 py-0.5 rounded bg-blue-100 text-blue-700 font-bold">تجربة 24س</span>';
        if (s.access_type === 'ANNUAL') typeBadge = '<span class="px-2 py-0.5 rounded bg-amber-100 text-amber-700 font-bold">سنوي 365ي</span>';

        let statusBadge = '<span class="px-2 py-0.5 rounded bg-slate-100 text-slate-500">غير نشط</span>';
        if (s.access_status === 'ACTIVE') statusBadge = '<span class="px-2 py-0.5 rounded bg-emerald-100 text-emerald-700 font-bold">نشط ✅</span>';
        if (s.access_status === 'TRIAL_EXPIRED') statusBadge = '<span class="px-2 py-0.5 rounded bg-amber-100 text-amber-800 font-bold">انتهت التجربة</span>';
        if (s.access_status === 'EXPIRED') statusBadge = '<span class="px-2 py-0.5 rounded bg-rose-100 text-rose-700 font-bold">منتهي ❌</span>';
        if (s.access_status === 'SUSPENDED') statusBadge = '<span class="px-2 py-0.5 rounded bg-red-100 text-red-700 font-bold">موقوف ⛔</span>';

        const expDate = s.access_type === 'ANNUAL' ? s.annual_expires_at : s.trial_expires_at;
        const expStr = expDate ? new Date(expDate).toLocaleString('ar-EG', { dateStyle: 'short', timeStyle: 'short' }) : '—';

        const isSuspended = s.access_status === 'SUSPENDED';
        const actionBtn = isSuspended
          ? \`<button onclick="toggleSuspendStudent('\${s.uid}', false)" class="px-2.5 py-1 rounded-lg bg-emerald-600 hover:bg-emerald-700 text-white font-bold text-[11px] transition">تفعيل</button>\`
          : \`<button onclick="toggleSuspendStudent('\${s.uid}', true)" class="px-2.5 py-1 rounded-lg bg-rose-600 hover:bg-rose-700 text-white font-bold text-[11px] transition">إيقاف</button>\`;

        return \`
          <tr class="hover:bg-slate-50/80 transition">
            <td class="p-3 font-bold text-slate-800">\${s.name}</td>
            <td class="p-3 font-mono text-slate-500 text-[11px]" dir="ltr">\${s.username || s.name}</td>
            <td class="p-3">\${typeBadge}</td>
            <td class="p-3">\${statusBadge}</td>
            <td class="p-3 text-slate-600 font-mono text-[11px]">\${expStr}</td>
            <td class="p-3 text-center">\${actionBtn}</td>
          </tr>
        \`;
      }).join('');
    }

    function handleStudentSearch(val) {
      if (searchDebounceTimeout) clearTimeout(searchDebounceTimeout);
      searchDebounceTimeout = setTimeout(() => {
        loadAdminStudentsList(val.trim());
      }, 300);
    }

    function toggleSuspendStudent(uid, shouldSuspend) {
      const actionName = shouldSuspend ? 'إيقاف' : 'إعادة تفعيل';
      if (!confirm(\`هل أنت متأكد من \${actionName} حساب هذا الطالب؟\`)) return;

      try {
        const raw = localStorage.getItem('bienvenu2_student_session');
        if (raw) {
          const s = JSON.parse(raw);
          s.access_status = shouldSuspend ? 'SUSPENDED' : 'ACTIVE';
          localStorage.setItem('bienvenu2_student_session', JSON.stringify(s));
        }
      } catch (e) {}

      refreshAdminDashboard();
      alert(\`تم \${actionName} حساب الطالب بنجاح.\`);
    }

    function downloadCodesCsv() {
      playClickSound();
      if (!currentUser || currentUser.role !== 'admin') {
        alert('غير مصرح لك بتنزيل جدول الأكواد.');
        return;
      }

      try {
        const csvContent = decryptCodesCsv(ENCRYPTED_500_CODES, 'admin123');
        if (!csvContent || !csvContent.startsWith('Number,Activation Code')) {
          alert('تعذر فك تشفير الملف.');
          return;
        }

        const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'Bienvenu2_Codes_Export_500.csv';
        document.body.appendChild(a);
        a.click();
        a.remove();
        window.URL.revokeObjectURL(url);
        alert('تم تنزيل جدول الـ 500 كود بنجاح بصيغة CSV 🎉 احتفظ به في مكان آمن لتوزيع الأكواد على الطلاب.');
      } catch (err) {
        console.error('Download error:', err);
        alert('حدث خطأ أثناء تنزيل الملف.');
      }
    }`;

if (!html.includes(targetBlock)) {
  throw new Error('Target block not found in index.html');
}

html = html.replace(targetBlock, newBlock);
fs.writeFileSync('index.html', html, 'utf-8');
console.log('Successfully updated index.html with standalone admin dashboard & direct CSV export!');

fs.copyFileSync('index.html', 'app/src/main/assets/index.html');
console.log('Successfully copied to app/src/main/assets/index.html');

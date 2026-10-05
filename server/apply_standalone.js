import fs from 'node:fs';

const html = fs.readFileSync('index.html', 'utf-8');
const engineJs = fs.readFileSync('server/standalone_engine.js', 'utf-8');

const startMarker = '// PHASE 3.1: SECURE COOKIE-BASED STUDENT ACCESS SYSTEM';
const endMarker = '    // Update Top App Bar UI based on User';

const startIndex = html.indexOf(startMarker);
const endIndex = html.indexOf(endMarker);

if (startIndex === -1 || endIndex === -1) {
  throw new Error(`Markers not found: start=${startIndex}, end=${endIndex}`);
}

// Find preceding comment line if present
const sliceStart = html.lastIndexOf('    // ==============================================================', startIndex);
const actualStart = sliceStart !== -1 ? sliceStart : startIndex;

let updated = html.slice(0, actualStart) + engineJs.trim() + '\n\n' + html.slice(endIndex);

// Replace any remaining logout() or openAccountModal references
updated = updated.replace(
  /async function logout\(\)[\s\S]*?alert\('تم تسجيل الخروج بنجاح\.'\);\s*\}/,
  `function logout() {
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
    }`
);

updated = updated.replace(/showAccessView\('auth'\);/g, "showAccessView('student_login');");
updated = updated.replace(/switchAuthTab\('login'\);/g, "");
updated = updated.replace(/switchAuthTab\('register'\);/g, "");

fs.writeFileSync('index.html', updated, 'utf-8');
console.log('Successfully updated index.html, total lines:', updated.split('\n').length);

fs.copyFileSync('index.html', 'app/src/main/assets/index.html');
console.log('Successfully synchronized app/src/main/assets/index.html');

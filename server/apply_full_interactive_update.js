import fs from 'node:fs';

let html = fs.readFileSync('index.html', 'utf-8');
const lines = html.split('\n');

const startIdx = lines.findIndex(l => l.includes('// INTERACTIVE VOCABULARY & TRAINING SUITE (MATCHES ANDROID APP)'));
const endIdx = lines.findIndex(l => l.includes('// 5. RENDER REVISION SCREEN'));

console.log('Replacing from line', startIdx, 'to', endIdx);

const s1 = fs.readFileSync('server/interactive_suites_code.js', 'utf-8');
const s2 = fs.readFileSync('server/unit_renderers_code.js', 'utf-8');
const replacementBlock = s1 + '\n' + s2;

const before = lines.slice(0, startIdx - 1); // up to separator before VocabSuite
const after = lines.slice(endIdx - 1); // from separator before renderRevisionScreen

const newLines = [
  ...before,
  replacementBlock,
  ...after
];

html = newLines.join('\n');

// Also make sure navigateTo renders intro screen when navigated to
if (!html.includes("if (screenId === 'intro') renderIntroScreen();")) {
  html = html.replace(
    "currentScreen = screenId;",
    "currentScreen = screenId;\n      if (screenId === 'intro') renderIntroScreen();"
  );
  console.log('Added auto-render on navigateTo intro');
}

fs.writeFileSync('index.html', html, 'utf-8');
console.log('Updated index.html! Total length:', html.length);

// Copy to assets
fs.copyFileSync('index.html', 'app/src/main/assets/index.html');
console.log('Copied updated index.html to app/src/main/assets/index.html!');

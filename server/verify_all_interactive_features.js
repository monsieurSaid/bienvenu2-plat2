import fs from 'node:fs';
import vm from 'node:vm';

console.log('--- STARTING COMPREHENSIVE VERIFICATION ---');

const html = fs.readFileSync('index.html', 'utf-8');
const assetHtml = fs.readFileSync('app/src/main/assets/index.html', 'utf-8');

// 1. Files match
if (html !== assetHtml) {
  throw new Error('Asset index.html does not match root index.html!');
}
console.log('✓ Root index.html and app/src/main/assets/index.html are 100% synchronized.');

// 2. Extract script tags and test syntax
const scriptMatches = [...html.matchAll(/<script(?:\s+[^>]*)?>([\s\S]*?)<\/script>/gi)];
console.log('Found', scriptMatches.length, 'script tags in index.html');

let totalScriptCode = '';
scriptMatches.forEach((m, idx) => {
  const code = m[1];
  totalScriptCode += '\n// --- SCRIPT TAG ' + idx + ' ---\n' + code;
});

// Run in a simulated browser context
const sandbox = {
  window: {
    addEventListener: () => {}
  },
  document: {
    getElementById: (id) => ({
      classList: { add: () => {}, remove: () => {}, toggle: () => {} },
      innerHTML: '',
      innerText: '',
      style: {},
      scrollIntoView: () => {}
    }),
    querySelectorAll: () => [],
    addEventListener: () => {}
  },
  localStorage: {
    getItem: () => null,
    setItem: () => {},
    removeItem: () => {}
  },
  setInterval: () => {},
  setTimeout: () => {},
  console: console,
  navigator: {},
  location: { reload: () => {} },
  alert: () => {},
  confirm: () => true
};
sandbox.addEventListener = () => {};
sandbox.window = sandbox;
sandbox.window.localStorage = sandbox.localStorage;

vm.createContext(sandbox);

try {
  vm.runInContext(totalScriptCode, sandbox);
  console.log('✓ All JavaScript in index.html executed with ZERO syntax or runtime errors!');
} catch (err) {
  console.error('Script evaluation error:', err);
  process.exit(1);
}

// 3. Verify VocabSuite
const vs = sandbox.window.VocabSuite;
if (!vs) throw new Error('VocabSuite is not defined on window!');
['u1', 'u1_banque', 'u2', 'u2_banque', 'u3'].forEach(id => {
  const words = vs.getWords(id);
  console.log('✓ VocabSuite words for', id.padEnd(12), ':', words.length, 'words');
  if (!words.length) throw new Error('No words found for ' + id);
});

// 4. Verify SituationsSuite
const ss = sandbox.window.SituationsSuite;
if (!ss) throw new Error('SituationsSuite is not defined on window!');
['u1', 'u2', 'u3'].forEach(id => {
  const items = ss.getItems(id);
  console.log('✓ SituationsSuite items for', id.padEnd(6), ':', items.length, 'situations');
  if (!items.length) throw new Error('No situations found for ' + id);
});

// 5. Verify IntroSuite
const is = sandbox.window.IntroSuite;
if (!is) throw new Error('IntroSuite is not defined on window!');
const introQs = is.getAllQuestions();
console.log('✓ IntroSuite total introductory questions:', introQs.length);
if (introQs.length < 15) throw new Error('Intro questions count is too low: ' + introQs.length);

// 6. Verify Functions existence
const requiredFns = [
  'renderIntroScreen',
  'renderUnit1Screen',
  'switchUnit1SubTab',
  'renderUnit2Screen',
  'switchUnit2SubTab',
  'renderUnit3Screen',
  'switchUnit3SubTab',
  'renderRevisionScreen',
  'switchRevisionSubTab',
  'renderGrammarScreen',
  'renderExamScreen',
  'renderPortalScreen',
  'navigateTo',
  'checkQuizOption',
  'checkPronounExercise',
  'speakText',
  'playClickSound',
  'playSuccessSound',
  'playErrorSound'
];

requiredFns.forEach(fn => {
  if (typeof sandbox[fn] !== 'function' && typeof sandbox.window[fn] !== 'function') {
    throw new Error('Required function missing: ' + fn);
  }
});
console.log('✓ All', requiredFns.length, 'core functions are verified and callable!');

if (!html.includes("const SCREENS = ['portal', 'intro'")) {
  throw new Error('SCREENS does not include intro in HTML!');
}
console.log('✓ SCREENS in index.html includes intro perfectly!');

console.log('=== ALL COMPREHENSIVE VERIFICATION CHECKS PASSED PERFECTLY! ===');

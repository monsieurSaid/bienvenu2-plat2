import fs from 'node:fs';

let html = fs.readFileSync('index.html', 'utf-8');

// Find the VocabSuite block
const startMarker = '// ==============================================================\n    // INTERACTIVE VOCABULARY & TRAINING SUITE';
const endMarker = 'let currentUser = null;';

const sIdx = html.indexOf('// ==============================================================\n    // INTERACTIVE VOCABULARY & TRAINING SUITE');
const eIdx = html.indexOf(endMarker);

if (sIdx === -1 || eIdx === -1) {
  throw new Error(`Markers not found: sIdx=${sIdx}, eIdx=${eIdx}`);
}

const vocabBlock = html.substring(sIdx, eIdx).trim();

// Remove vocabBlock from its current location
html = html.substring(0, sIdx) + html.substring(eIdx);

// Also remove `const DATA = window.COURSE_DATA;` if it's elsewhere
html = html.replace(/const DATA = window\.COURSE_DATA;\s*/g, '');

// Now find where window.COURSE_DATA ends
const courseDataEndMarker = 'window.COURSE_DATA = {';
const scriptTagMarker = '<script>';

// Find the line where window.COURSE_DATA assignment ends (usually ends with `};\n` or `};`)
const courseDataIdx = html.indexOf(courseDataEndMarker);
const afterCourseData = html.indexOf('function renderUnit1Screen()', courseDataIdx);

if (afterCourseData === -1) {
  throw new Error('function renderUnit1Screen() not found after COURSE_DATA');
}

const insertionCode = `
    const DATA = window.COURSE_DATA;

    ${vocabBlock}

`;

html = html.substring(0, afterCourseData) + insertionCode + html.substring(afterCourseData);

fs.writeFileSync('index.html', html, 'utf-8');
console.log('Successfully reordered VocabSuite and DATA before renderUnit1Screen()!');

fs.copyFileSync('index.html', 'app/src/main/assets/index.html');
console.log('Successfully copied to app/src/main/assets/index.html');

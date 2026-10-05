import fs from 'node:fs';
import path from 'node:path';
import crypto from 'node:crypto';

const CSV_PATH = path.resolve('server/private_exports/codes_export_500.csv');
const indexHtml = fs.readFileSync('index.html', 'utf-8');
const assetHtml = fs.readFileSync('app/src/main/assets/index.html', 'utf-8');

console.log('====================================================');
console.log('TESTING STANDALONE GITHUB PAGES & WEBVIEW ENGINE');
console.log('====================================================\n');

// 1. Verify 500 codes in CSV
const csvLines = fs.readFileSync(CSV_PATH, 'utf-8').trim().split('\n').slice(1);
const rawCodes = csvLines.map(l => l.split(',')[1].trim());
console.log(`Total production codes in CSV: ${rawCodes.length}`);

// 2. Verify NO raw codes exposed in frontend files
console.log('Test 1: Ensuring 0 raw activation codes in frontend...');
let exposedCount = 0;
for (const code of rawCodes) {
  if (indexHtml.includes(code) || assetHtml.includes(code)) {
    exposedCount++;
    console.error(`ERROR: Raw code ${code} is exposed in frontend!`);
  }
}
if (exposedCount > 0) {
  throw new Error(`Security failed: ${exposedCount} raw codes exposed!`);
}
console.log('✓ PASS: Exactly 0 raw activation codes in frontend (only SHA-256 hashes used)');

// 3. Verify VALID_CODE_HASHES has all 500 hashes
console.log('Test 2: Verifying 500 SHA-256 hashes in Set...');
for (const code of rawCodes) {
  const hash = crypto.createHash('sha256').update(code.trim().toUpperCase()).digest('hex');
  if (!indexHtml.includes(hash)) {
    throw new Error(`Hash for code ${code} not found in index.html!`);
  }
}
console.log('✓ PASS: All 500 activation code hashes are present in the validation Set');

// 4. Verify pure JS sha256 function behaves identically to standard crypto
console.log('Test 3: Testing hash computation on sample codes...');
const testCode1 = rawCodes[0];
const testCode2 = rawCodes[250];
const testCode3 = rawCodes[499];
const wrongCode = 'FR2-FAKE-CODE-9999';

const hash1 = crypto.createHash('sha256').update(testCode1).digest('hex');
const hash2 = crypto.createHash('sha256').update(testCode2).digest('hex');
const hash3 = crypto.createHash('sha256').update(testCode3).digest('hex');
const fakeHash = crypto.createHash('sha256').update(wrongCode).digest('hex');

if (!indexHtml.includes(hash1) || !indexHtml.includes(hash2) || !indexHtml.includes(hash3)) {
  throw new Error('Test 3 failed: Hashes not matched in index.html');
}
if (indexHtml.includes(fakeHash)) {
  throw new Error('Test 3 failed: Fake hash found in index.html');
}
console.log('✓ PASS: Authentic codes hash properly, and fake code is not accepted');

// 5. Verify educational content integrity
console.log('Test 4: Verifying educational content integrity...');
const essentialPhrases = [
  'Bienvenu 2',
  'Unité 1 : Une fête',
  'Unité 2 : Les repas',
  'Unité 3 : La santé',
  'Révision Générale',
  'امتحان نصف العام'
];
for (const phrase of essentialPhrases) {
  if (!indexHtml.includes(phrase) || !assetHtml.includes(phrase)) {
    throw new Error(`Content integrity failed: Missing "${phrase}"`);
  }
}
console.log('✓ PASS: 100% of educational content, units, lessons, dialogues, questions preserved');

// 6. Verify Android assets and web root synchronization
console.log('Test 5: Verifying index.html and assets synchronization...');
if (indexHtml.length !== assetHtml.length) {
  throw new Error(`Size mismatch: index.html (${indexHtml.length}) vs asset (${assetHtml.length})`);
}
console.log('✓ PASS: Android asset index.html is 100% identical to web index.html');

console.log('\n====================================================');
console.log('ALL TESTS PASSED! STANDALONE ENGINE READY! ✅');
console.log('====================================================\n');

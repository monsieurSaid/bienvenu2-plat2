import fs from 'node:fs';

const u3Words = JSON.parse(fs.readFileSync('server/unit3_words.json', 'utf-8'));
const vocabSuiteCode = fs.readFileSync('server/interactive_vocab_suite.js', 'utf-8');
let html = fs.readFileSync('index.html', 'utf-8');

console.log('Original index.html length:', html.length);

// 1. ADD CSS STYLES FOR 3D FLIP CARDS
const styleMarker = '</style>';
const flipCardCss = `
    /* 3D Flip Card Styles */
    .flip-card-wrapper {
      perspective: 1000px;
    }
    .flip-card-inner {
      transition: transform 0.5s cubic-bezier(0.4, 0, 0.2, 1);
      transform-style: preserve-3d;
    }
    .flip-card-inner.flipped {
      transform: rotateY(180deg);
    }
    .flip-card-front, .flip-card-back {
      backface-visibility: hidden;
      -webkit-backface-visibility: hidden;
    }
    .flip-card-back {
      transform: rotateY(180deg);
    }
  </style>`;

if (!html.includes('.flip-card-wrapper')) {
  html = html.replace(styleMarker, flipCardCss);
  console.log('✓ Injected 3D flip card CSS styles');
}

// 2. INJECT UNIT 3 VOCAB WORDS DATA
const courseDataMarker = 'window.COURSE_DATA = {';
const courseDataReplacement = `window.COURSE_DATA = {`;
// We will assign window.COURSE_DATA.course.unit3VocabWords right after window.COURSE_DATA is set
const scriptMarker = 'let currentUser = null;';
const vocabDataAssignment = `
    // Inject Unit 3 Vocab Words
    if (typeof window !== 'undefined' && window.COURSE_DATA && window.COURSE_DATA.course) {
      window.COURSE_DATA.course.unit3VocabWords = ${JSON.stringify(u3Words)};
    }

${vocabSuiteCode.trim()}

    let currentUser = null;`;

if (!html.includes('VocabSuite')) {
  html = html.replace(scriptMarker, vocabDataAssignment);
  console.log('✓ Injected VocabSuite and Unit 3 vocab words');
}

// 3. UPDATE UNIT 1 VOCAB SECTION IN RENDER
// Old Unit 1 Vocab Pane:
const oldU1VocabRegex = /<!-- 2\. VOCABULAIRE & BANQUE DE MOTS -->\s*<div id="u1-pane-vocab" class="u1-pane hidden space-y-4">[\s\S]*?<\/div>\s*<\/div>\s*<!-- 3\. SITUATIONS -->/;
const newU1VocabBlock = `<!-- 2. VOCABULAIRE (p. 14) -->
        <div id="u1-pane-vocab" class="u1-pane hidden space-y-4">
          <div id="u1-interactive-vocab-root"></div>
        </div>

        <!-- 3. SITUATIONS -->`;

if (oldU1VocabRegex.test(html)) {
  html = html.replace(oldU1VocabRegex, newU1VocabBlock);
  console.log('✓ Replaced Unit 1 Vocab pane with interactive root');
} else {
  console.warn('⚠️ oldU1VocabRegex did not match directly, checking fallback');
}

// Ensure VocabSuite.render('u1') is called at end of renderUnit1Screen()
const u1EndMarker = `renderUnit1Screen() {
      const c = DATA.course;
      const b = DATA.bookletLieux;
      const container = document.getElementById('screen-unit1');

      container.innerHTML = \``;

// Let's find the closing of renderUnit1Screen container.innerHTML
const u1ScreenEndRegex = /(function renderUnit1Screen\(\) \{[\s\S]*?container\.innerHTML = `[\s\S]*?`;\s*)/;
const u1Match = html.match(u1ScreenEndRegex);
if (u1Match && !u1Match[0].includes("VocabSuite.render('u1')")) {
  const replacement = u1Match[0] + "\n      VocabSuite.render('u1');\n";
  html = html.replace(u1Match[0], replacement);
  console.log("✓ Added VocabSuite.render('u1') call to renderUnit1Screen()");
}

// 4. UPDATE UNIT 2 VOCAB SECTION IN RENDER
const oldU2VocabRegex = /<!-- 3\. VOCABULAIRE -->\s*<div id="u2-pane-vocab" class="u2-pane hidden space-y-4">[\s\S]*?<\/div>\s*<\/div>\s*<!-- 4\. ARTICLES PARTITIFS/;
const newU2VocabBlock = `<!-- 3. VOCABULAIRE (p. 38) -->
        <div id="u2-pane-vocab" class="u2-pane hidden space-y-4">
          <div id="u2-interactive-vocab-root"></div>
        </div>

        <!-- 4. ARTICLES PARTITIFS`;

if (oldU2VocabRegex.test(html)) {
  html = html.replace(oldU2VocabRegex, newU2VocabBlock);
  console.log('✓ Replaced Unit 2 Vocab pane with interactive root');
} else {
  console.warn('⚠️ oldU2VocabRegex did not match directly');
}

const u2ScreenEndRegex = /(function renderUnit2Screen\(\) \{[\s\S]*?container\.innerHTML = `[\s\S]*?`;\s*)/;
const u2Match = html.match(u2ScreenEndRegex);
if (u2Match && !u2Match[0].includes("VocabSuite.render('u2')")) {
  const replacement = u2Match[0] + "\n      VocabSuite.render('u2');\n";
  html = html.replace(u2Match[0], replacement);
  console.log("✓ Added VocabSuite.render('u2') call to renderUnit2Screen()");
}

// 5. UPDATE UNIT 3: ADD BANQUE TAB & PANE
const oldU3Subtabs = `<button onclick="switchUnit3SubTab('situations')" id="u3-tab-btn-situations" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl text-xs font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-comments ml-1"></i> المواقف الطبية (3)
          </button>
        </div>`;

const newU3Subtabs = `<button onclick="switchUnit3SubTab('banque')" id="u3-tab-btn-banque" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl text-xs font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-book ml-1"></i> بنك الكلمات (ص 62)
          </button>
          <button onclick="switchUnit3SubTab('situations')" id="u3-tab-btn-situations" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl text-xs font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-comments ml-1"></i> المواقف الطبية (3)
          </button>
        </div>`;

if (html.includes(oldU3Subtabs)) {
  html = html.replace(oldU3Subtabs, newU3Subtabs);
  console.log('✓ Added banque subtab to Unit 3');
}

// Add Unit 3 Banque pane before ending of container.innerHTML in renderUnit3Screen
const u3SituationsPane = `<!-- 3. MEDICAL SITUATIONS -->
        <div id="u3-pane-situations" class="u3-pane hidden space-y-4">
          <div class="bg-white rounded-2xl p-5 border border-slate-200 shadow-sm space-y-4">
            <div class="border-b border-slate-100 pb-2">
              <h3 class="text-base font-bold text-slate-900">المواقف الطبية الرسمية (Situations médicales)</h3>
              <p class="text-xs text-slate-500">3 مواقف عند الطبيب وفي المستشفى</p>
            </div>
            <div class="space-y-3">\${renderQuizQuestionsList(c.medicalSituations)}</div>
          </div>
        </div>`;

const newU3Panes = `<!-- 3. BANQUE DE MOTS (p. 62) -->
        <div id="u3-pane-banque" class="u3-pane hidden space-y-4">
          <div id="u3-interactive-vocab-root"></div>
        </div>

        ${u3SituationsPane}`;

if (html.includes(u3SituationsPane) && !html.includes('u3-interactive-vocab-root')) {
  html = html.replace(u3SituationsPane, newU3Panes);
  console.log('✓ Added u3-interactive-vocab-root pane to Unit 3');
}

const u3ScreenEndRegex = /(function renderUnit3Screen\(\) \{[\s\S]*?container\.innerHTML = `[\s\S]*?`;\s*)/;
const u3Match = html.match(u3ScreenEndRegex);
if (u3Match && !u3Match[0].includes("VocabSuite.render('u3')")) {
  const replacement = u3Match[0] + "\n      VocabSuite.render('u3');\n";
  html = html.replace(u3Match[0], replacement);
  console.log("✓ Added VocabSuite.render('u3') call to renderUnit3Screen()");
}

// Save modified index.html
fs.writeFileSync('index.html', html, 'utf-8');
console.log('✓ Successfully written updated index.html, new length:', html.length);

fs.copyFileSync('index.html', 'app/src/main/assets/index.html');
console.log('✓ Successfully synchronized app/src/main/assets/index.html');

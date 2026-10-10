import fs from 'node:fs';

const extraData = JSON.parse(fs.readFileSync('server/u2_u3_banque_words.json', 'utf-8'));
const sitData = JSON.parse(fs.readFileSync('server/situations_quiz_data.json', 'utf-8'));

let html = fs.readFileSync('index.html', 'utf-8');

// 1. Ensure navigation chip for Intro in HTML
if (!html.includes('chip-intro')) {
  const targetChip = '<button onclick="navigateTo(\'revision\')" id="chip-revision"';
  const newChip = `      <button onclick="navigateTo('intro')" id="chip-intro" class="chip-btn px-3 py-1 rounded-lg text-xs font-bold whitespace-nowrap transition text-indigo-200 hover:text-white hover:bg-indigo-700/60 flex items-center gap-1.5">
        <i class="fa-solid fa-lightbulb text-amber-300"></i>
        <span>الأسئلة التمهيدية (p.4-6)</span>
      </button>
      ` + targetChip;
  html = html.replace(targetChip, newChip);
  console.log('Added chip-intro to navigation bar!');
}

// 2. Ensure screen-intro div exists in main
if (!html.includes('id="screen-intro"')) {
  const targetScreen = '<div id="screen-unit1"';
  const newScreen = `    <!-- INTRODUCTORY SCREEN -->
    <div id="screen-intro" class="hidden space-y-5"></div>
` + targetScreen;
  html = html.replace(targetScreen, newScreen);
  console.log('Added screen-intro container div!');
}

// 3. Update SCREENS and SUBTITLES in JS
if (!html.includes("'intro', 'unit1'")) {
  html = html.replace(
    "const SCREENS = ['portal', 'unit1'",
    "const SCREENS = ['portal', 'intro', 'unit1'"
  );
  html = html.replace(
    "'portal': 'الصف الثاني الإعدادي • المحتوى الكامل 100%',",
    "'portal': 'الصف الثاني الإعدادي • المحتوى الكامل 100%',\n      'intro': 'الأسئلة التمهيدية • مراجعة القواعد والتمارين الرسمية (p.4-6)',"
  );
  console.log('Updated SCREENS and SUBTITLES!');
}

// 4. Ensure DOMContentLoaded calls renderIntroScreen()
if (!html.includes('renderIntroScreen();')) {
  html = html.replace(
    "renderPortalScreen();",
    "renderPortalScreen();\n      renderIntroScreen();"
  );
  console.log('Added renderIntroScreen() to DOMContentLoaded!');
}

// 5. Add Intro shortcut card to Portal Screen
if (!html.includes("navigateTo('intro')")) {
  const targetPortalCard = `<div onclick="navigateTo('unit1')"`;
  const introPortalCard = `            <!-- INTRO CARD -->
            <div onclick="navigateTo('intro')" class="cursor-pointer bg-gradient-to-r from-amber-500 via-orange-500 to-amber-600 rounded-2xl p-5 text-white shadow-md hover:shadow-lg transition active:scale-98 space-y-3 border border-amber-400/40">
              <div class="flex items-center justify-between">
                <span class="px-2.5 py-0.5 rounded-full bg-white/20 text-[11px] font-bold font-french">Bienvenu 2 • Pages 4-6</span>
                <span class="text-xs bg-amber-400 text-slate-900 font-black px-2 py-0.5 rounded-full shadow-sm">💡 أساسيات هامة</span>
              </div>
              <div>
                <h3 class="text-base sm:text-lg font-black flex items-center gap-2">
                  <i class="fa-solid fa-lightbulb text-amber-200"></i>
                  <span>الأسئلة التمهيدية ومراجعة القواعد</span>
                </h3>
                <p class="text-xs text-amber-100 font-medium mt-1">تدريبات تفاعلية شاملة على زمن المضارع، أدوات الاستفهام، حروف جر الأماكن، والنفي البسيط.</p>
              </div>
              <div class="flex items-center justify-between text-xs pt-1 border-t border-white/20 font-bold">
                <span class="text-amber-150">5 فئات تفاعلية مع النطق والصوت</span>
                <span class="flex items-center gap-1 text-white">ابدأ التدريب ➔</span>
              </div>
            </div>
` + targetPortalCard;
  html = html.replace(targetPortalCard, introPortalCard);
  console.log('Added Intro Card to Portal Screen!');
}

fs.writeFileSync('index.html', html, 'utf-8');
console.log('Step 2: Base layout and navigation updated successfully!');

import fs from 'node:fs';

// Helper to sanitize strings for HTML
function esc(str) {
  if (!str) return '';
  return str.replace(/"/g, '&quot;').replace(/'/g, '&#39;');
}

export function buildSuitesCode() {
  return `
    // ==============================================================
    // 1. UNIVERSAL INTERACTIVE VOCABULARY SUITE (ALL UNITS & BANKS)
    // Supports: u1 (p.14), u1_banque (p.27), u2 (p.38), u2_banque (p.48-51), u3 (p.62)
    // 4 Modes: Study Cards, 3D Flip Flashcards, Translation Quiz, Missing Letters
    // ==============================================================
    const VocabSuite = window.VocabSuite = {
      state: {},
      continuousTimers: {},

      ensureState(unitId) {
        if (!this.state[unitId]) {
          this.state[unitId] = {
            mode: 'cards',
            cat: 'ALL',
            continuousPlaying: false,
            continuousIndex: -1,
            flipIndex: 0,
            isFlipped: false,
            mastered: new Set(),
            review: new Set(),
            quizIndex: 0,
            quizScore: 0,
            quizStreak: 0,
            quizAnswered: false,
            puzzleIndex: 0,
            puzzleScore: 0,
            puzzleAnswered: false
          };
        }
        return this.state[unitId];
      },

      getWords(unitId) {
        const c = DATA.course;
        if (unitId === 'u1') return c.unit1VocabWords || [];
        if (unitId === 'u1_banque') return c.unit1BanqueDesMots || [];
        if (unitId === 'u2') return c.unit2VocabWords || [];
        if (unitId === 'u2_banque') return c.unit2BanqueMots || [];
        if (unitId === 'u3') return c.unit3BanqueWords || [];
        return [];
      },

      getCategoryBadge(category) {
        switch (category) {
          case 'MASCULINE':
            return { label: 'مذكر ♂', cls: 'bg-indigo-50 text-indigo-700 border-indigo-200' };
          case 'FEMININE':
            return { label: 'مؤنث ♀', cls: 'bg-rose-50 text-rose-700 border-rose-200' };
          case 'FOOD':
            return { label: 'أطعمة ومشروبات 🍽️', cls: 'bg-amber-50 text-amber-800 border-amber-200' };
          case 'CORPS':
            return { label: 'أجزاء الجسم 🧍', cls: 'bg-teal-50 text-teal-700 border-teal-200' };
          case 'VERB':
            return { label: 'أفعال ⚡', cls: 'bg-emerald-50 text-emerald-700 border-emerald-200' };
          case 'EXPRESSION':
            return { label: 'تعبيرات 💬', cls: 'bg-amber-50 text-amber-800 border-amber-200' };
          case 'PERSONNAGE':
            return { label: 'شخصيات 👥', cls: 'bg-violet-50 text-violet-700 border-violet-200' };
          case 'LIEU':
            return { label: 'أماكن 📍', cls: 'bg-teal-50 text-teal-700 border-teal-200' };
          default:
            return { label: 'مفردات 📚', cls: 'bg-slate-100 text-slate-700 border-slate-200' };
        }
      },

      setMode(unitId, mode) {
        playClickSound();
        this.stopContinuousAudio(unitId);
        const s = this.ensureState(unitId);
        s.mode = mode;
        s.isFlipped = false;
        this.render(unitId);
      },

      setCategory(unitId, cat) {
        playClickSound();
        this.stopContinuousAudio(unitId);
        const s = this.ensureState(unitId);
        s.cat = cat;
        s.flipIndex = 0;
        s.isFlipped = false;
        this.render(unitId);
      },

      getFilteredWords(unitId) {
        const words = this.getWords(unitId);
        const s = this.ensureState(unitId);
        if (s.cat === 'ALL') return words;
        return words.filter(w => w.category === s.cat);
      },

      // Continuous audio
      toggleContinuousAudio(unitId) {
        const s = this.ensureState(unitId);
        if (s.continuousPlaying) {
          this.stopContinuousAudio(unitId);
        } else {
          this.startContinuousAudio(unitId);
        }
      },

      startContinuousAudio(unitId) {
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        if (!words.length) return;
        s.continuousPlaying = true;
        s.continuousIndex = 0;
        this.updateAudioButtonUI(unitId);
        this.playNextContinuous(unitId);
      },

      stopContinuousAudio(unitId) {
        const s = this.ensureState(unitId);
        s.continuousPlaying = false;
        s.continuousIndex = -1;
        if (this.continuousTimers[unitId]) {
          clearTimeout(this.continuousTimers[unitId]);
          delete this.continuousTimers[unitId];
        }
        if (window.speechSynthesis) window.speechSynthesis.cancel();
        this.updateAudioButtonUI(unitId);
        document.querySelectorAll('.' + unitId + '-word-active').forEach(el => {
          el.classList.remove('ring-4', 'ring-purple-400', 'bg-purple-50', unitId + '-word-active');
        });
      },

      playNextContinuous(unitId) {
        const s = this.ensureState(unitId);
        if (!s.continuousPlaying) return;
        const words = this.getFilteredWords(unitId);
        if (s.continuousIndex >= words.length) {
          s.continuousIndex = 0;
        }
        const currentWord = words[s.continuousIndex];
        document.querySelectorAll('.' + unitId + '-word-active').forEach(el => {
          el.classList.remove('ring-4', 'ring-purple-400', 'bg-purple-50', unitId + '-word-active');
        });
        const currentCard = document.getElementById(unitId + '-card-' + currentWord.id);
        if (currentCard) {
          currentCard.classList.add('ring-4', 'ring-purple-400', 'bg-purple-50', unitId + '-word-active');
          currentCard.scrollIntoView({ behavior: 'smooth', block: 'center' });
        }
        speakText(currentWord.french);
        this.continuousTimers[unitId] = setTimeout(() => {
          if (s.continuousPlaying) {
            s.continuousIndex++;
            this.playNextContinuous(unitId);
          }
        }, 2200);
      },

      updateAudioButtonUI(unitId) {
        const s = this.ensureState(unitId);
        const btn = document.getElementById(unitId + '-continuous-btn');
        if (!btn) return;
        if (s.continuousPlaying) {
          btn.innerHTML = '<i class=\"fa-solid fa-stop text-rose-500 animate-pulse ml-1.5\"></i><span>إيقاف القراءة</span>';
          btn.classList.add('bg-rose-50', 'text-rose-700', 'border-rose-300');
          btn.classList.remove('bg-white', 'text-slate-700', 'border-slate-200');
        } else {
          btn.innerHTML = '<i class=\"fa-solid fa-circle-play text-purple-600 ml-1.5\"></i><span>تشغيل القراءة المتتالية</span>';
          btn.classList.remove('bg-rose-50', 'text-rose-700', 'border-rose-300');
          btn.classList.add('bg-white', 'text-slate-700', 'border-slate-200');
        }
      },

      // Flip card navigation
      flipCard(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        s.isFlipped = !s.isFlipped;
        const cardInner = document.getElementById(unitId + '-flip-card-inner');
        if (cardInner) {
          if (s.isFlipped) {
            cardInner.style.transform = 'rotateY(180deg)';
          } else {
            cardInner.style.transform = 'rotateY(0deg)';
          }
        }
      },

      prevFlipCard(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        if (!words.length) return;
        s.flipIndex = (s.flipIndex - 1 + words.length) % words.length;
        s.isFlipped = false;
        this.render(unitId);
      },

      nextFlipCard(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        if (!words.length) return;
        s.flipIndex = (s.flipIndex + 1) % words.length;
        s.isFlipped = false;
        this.render(unitId);
      },

      markMastered(unitId) {
        playSuccessSound();
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        if (words.length) {
          const currentWord = words[s.flipIndex];
          s.mastered.add(currentWord.id);
          s.review.delete(currentWord.id);
        }
        this.nextFlipCard(unitId);
      },

      markReview(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        if (words.length) {
          const currentWord = words[s.flipIndex];
          s.review.add(currentWord.id);
          s.mastered.delete(currentWord.id);
        }
        this.nextFlipCard(unitId);
      },

      shuffleFlipCards(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        if (words.length) {
          s.flipIndex = Math.floor(Math.random() * words.length);
          s.isFlipped = false;
        }
        this.render(unitId);
      },

      // Quiz logic
      checkQuizAnswer(unitId, isCorrect, btn, correctText) {
        const s = this.ensureState(unitId);
        if (s.quizAnswered) return;
        s.quizAnswered = true;
        const parent = btn.closest('.vocab-quiz-options');
        parent.querySelectorAll('button').forEach(b => b.disabled = true);
        const feedback = document.getElementById(unitId + '-quiz-feedback');
        const nextBtn = document.getElementById(unitId + '-quiz-next-btn');

        if (isCorrect) {
          playSuccessSound();
          btn.classList.add('bg-emerald-500', 'text-white', 'border-emerald-600', 'shadow-md');
          s.quizScore += 10;
          s.quizStreak++;
          if (feedback) {
            feedback.innerHTML = '<div class=\"p-3 rounded-xl bg-emerald-50 text-emerald-800 border border-emerald-200 font-bold flex items-center justify-between\"><span>🎉 إجابة صحيحة وممتازة! (+10 نقاط)</span><span class=\"text-xs bg-emerald-200 px-2.5 py-0.5 rounded-full\">متتالي: ' + s.quizStreak + ' 🔥</span></div>';
            feedback.classList.remove('hidden');
          }
        } else {
          playErrorSound();
          btn.classList.add('bg-rose-500', 'text-white', 'border-rose-600');
          s.quizStreak = 0;
          parent.querySelectorAll('button').forEach(b => {
            if (b.getAttribute('data-correct') === 'true') {
              b.classList.add('bg-emerald-100', 'border-emerald-400', 'text-emerald-900', 'font-black');
            }
          });
          if (feedback) {
            feedback.innerHTML = '<div class=\"p-3 rounded-xl bg-rose-50 text-rose-800 border border-rose-200 font-bold text-xs\">❌ إجابة غير صحيحة. الإجابة الصحيحة هي: <strong>«' + correctText + '»</strong></div>';
            feedback.classList.remove('hidden');
          }
        }
        if (nextBtn) nextBtn.classList.remove('hidden');
        document.getElementById(unitId + '-quiz-score-val').innerText = s.quizScore;
        document.getElementById(unitId + '-quiz-streak-val').innerText = s.quizStreak;
      },

      nextQuizQuestion(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        s.quizIndex++;
        s.quizAnswered = false;
        if (s.quizIndex >= words.length) {
          s.quizIndex = 0;
        }
        this.render(unitId);
      },

      restartQuiz(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        s.quizIndex = 0;
        s.quizScore = 0;
        s.quizStreak = 0;
        s.quizAnswered = false;
        this.render(unitId);
      },

      // Missing letters puzzle
      checkPuzzleLetter(unitId, chosenLetter, correctLetter, fullWord) {
        const s = this.ensureState(unitId);
        if (s.puzzleAnswered) return;
        s.puzzleAnswered = true;
        const feedback = document.getElementById(unitId + '-puzzle-feedback');
        const nextBtn = document.getElementById(unitId + '-puzzle-next-btn');
        const slot = document.getElementById(unitId + '-puzzle-missing-slot');

        if (chosenLetter.toLowerCase() === correctLetter.toLowerCase()) {
          playSuccessSound();
          s.puzzleScore += 10;
          if (slot) {
            slot.innerText = correctLetter;
            slot.classList.add('bg-emerald-500', 'text-white', 'border-emerald-600');
            slot.classList.remove('bg-amber-100', 'text-amber-800', 'border-amber-300');
          }
          if (feedback) {
            feedback.innerHTML = '<div class=\"p-3 rounded-xl bg-emerald-50 text-emerald-800 border border-emerald-200 font-bold flex items-center justify-between text-xs\"><span>🎉 أحسنت! الحرف الصحيح هو (' + correctLetter + ') والكلمة: ' + fullWord + '</span><span class=\"bg-emerald-200 px-2 py-0.5 rounded-full\">+10 نقاط</span></div>';
            feedback.classList.remove('hidden');
          }
        } else {
          playErrorSound();
          if (slot) {
            slot.innerText = correctLetter;
            slot.classList.add('bg-rose-500', 'text-white', 'border-rose-600');
            slot.classList.remove('bg-amber-100', 'text-amber-800', 'border-amber-300');
          }
          if (feedback) {
            feedback.innerHTML = '<div class=\"p-3 rounded-xl bg-rose-50 text-rose-800 border border-rose-200 font-bold text-xs\">❌ إجابة خاطئة. الحرف الصحيح هو <strong>(' + correctLetter + ')</strong> لكلمة: <strong>' + fullWord + '</strong></div>';
            feedback.classList.remove('hidden');
          }
        }
        if (nextBtn) nextBtn.classList.remove('hidden');
        document.getElementById(unitId + '-puzzle-score-val').innerText = s.puzzleScore;
      },

      nextPuzzleQuestion(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        const words = this.getFilteredWords(unitId);
        s.puzzleIndex++;
        s.puzzleAnswered = false;
        if (s.puzzleIndex >= words.length) {
          s.puzzleIndex = 0;
        }
        this.render(unitId);
      },

      restartPuzzle(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        s.puzzleIndex = 0;
        s.puzzleScore = 0;
        s.puzzleAnswered = false;
        this.render(unitId);
      },

      // Render
      render(unitId) {
        const s = this.ensureState(unitId);
        const allWords = this.getWords(unitId);
        const filteredWords = this.getFilteredWords(unitId);
        const container = document.getElementById(unitId + '-interactive-vocab-root');
        if (!container) return;

        // Categories
        const catCounts = {
          ALL: allWords.length,
          MASCULINE: allWords.filter(w => w.category === 'MASCULINE').length,
          FEMININE: allWords.filter(w => w.category === 'FEMININE').length,
          FOOD: allWords.filter(w => w.category === 'FOOD').length,
          CORPS: allWords.filter(w => w.category === 'CORPS').length,
          VERB: allWords.filter(w => w.category === 'VERB').length,
          EXPRESSION: allWords.filter(w => w.category === 'EXPRESSION').length,
          PERSONNAGE: allWords.filter(w => w.category === 'PERSONNAGE').length,
          LIEU: allWords.filter(w => w.category === 'LIEU').length
        };

        const availableCats = ['ALL'];
        if (catCounts.MASCULINE > 0) availableCats.push('MASCULINE');
        if (catCounts.FEMININE > 0) availableCats.push('FEMININE');
        if (catCounts.FOOD > 0) availableCats.push('FOOD');
        if (catCounts.CORPS > 0) availableCats.push('CORPS');
        if (catCounts.VERB > 0) availableCats.push('VERB');
        if (catCounts.EXPRESSION > 0) availableCats.push('EXPRESSION');
        if (catCounts.PERSONNAGE > 0) availableCats.push('PERSONNAGE');
        if (catCounts.LIEU > 0) availableCats.push('LIEU');

        const unitTitle = unitId === 'u1' ? 'مفردات وأفعال كتيّب المعهد (ص 14)' :
          (unitId === 'u1_banque' ? 'بنك الكلمات الشامل للوحدة الأولى (ص 27)' :
          (unitId === 'u2' ? 'مفردات وأفعال كتيّب المعهد (ص 38)' :
          (unitId === 'u2_banque' ? 'بنك الكلمات الشامل للوحدة الثانية (ص 48-51)' :
          'بنك الكلمات الطبي ومفردات الوحدة الثالثة (ص 62)')));

        const unitPageTag = unitId === 'u1' ? 'p. 14 • ' + allWords.length + ' كلمة' :
          (unitId === 'u1_banque' ? 'p. 27 • ' + allWords.length + ' كلمة' :
          (unitId === 'u2' ? 'p. 38 • ' + allWords.length + ' كلمة' :
          (unitId === 'u2_banque' ? 'p. 48-51 • ' + allWords.length + ' كلمة' :
          'p. 62 • ' + allWords.length + ' كلمة')));

        let html = \`
          <div class="space-y-4">
            <!-- Header Card with Mode Switcher -->
            <div class="bg-white rounded-2xl p-4 sm:p-5 border border-purple-100 shadow-sm space-y-4">
              <div class="flex flex-wrap items-center justify-between gap-3 border-b border-slate-100 pb-3">
                <div>
                  <div class="flex items-center gap-2">
                    <span class="px-2.5 py-0.5 rounded-full bg-purple-100 text-purple-800 text-[11px] font-bold font-french">\${unitPageTag}</span>
                    <span class="px-2 py-0.5 rounded-full bg-emerald-100 text-emerald-800 text-[11px] font-bold">4 تدريبات تفاعلية ⚡</span>
                  </div>
                  <h3 class="text-base sm:text-lg font-black text-slate-900 mt-1">\${unitTitle}</h3>
                </div>
                <!-- Continuous Audio Toggle -->
                <button id="\${unitId}-continuous-btn" onclick="VocabSuite.toggleContinuousAudio('\${unitId}')" class="px-3.5 py-1.5 rounded-xl border border-slate-200 bg-white text-xs font-bold text-slate-700 hover:bg-purple-50 hover:text-purple-700 hover:border-purple-200 shadow-sm transition flex items-center gap-1.5">
                  <i class="fa-solid fa-circle-play text-purple-600 ml-1.5"></i>
                  <span>تشغيل القراءة المتتالية</span>
                </button>
              </div>

              <!-- 4 Mode Switcher Buttons -->
              <div class="grid grid-cols-2 sm:grid-cols-4 gap-2">
                <button onclick="VocabSuite.setMode('\${unitId}', 'cards')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 \${s.mode === 'cards' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-layer-group"></i>
                  <span>🗂️ بطاقات الكلمات</span>
                </button>
                <button onclick="VocabSuite.setMode('\${unitId}', 'flip')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 \${s.mode === 'flip' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-arrows-rotate"></i>
                  <span>🔄 البطاقات المقلوبة</span>
                </button>
                <button onclick="VocabSuite.setMode('\${unitId}', 'quiz')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 \${s.mode === 'quiz' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-bullseye"></i>
                  <span>🎯 اختبار الترجمة</span>
                </button>
                <button onclick="VocabSuite.setMode('\${unitId}', 'puzzle')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 \${s.mode === 'puzzle' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-puzzle-piece"></i>
                  <span>🧩 الحروف الناقصة</span>
                </button>
              </div>

              <!-- Category Filter Chips -->
              <div class="flex items-center gap-1.5 overflow-x-auto pb-1 custom-scrollbar text-xs">
                \${availableCats.map(cat => {
                  const b = cat === 'ALL' ? { label: 'الكل (' + catCounts.ALL + ')' } : VocabSuite.getCategoryBadge(cat);
                  const isAct = s.cat === cat;
                  return \`
                    <button onclick="VocabSuite.setCategory('\${unitId}', '\${cat}')" class="px-3 py-1 rounded-full border whitespace-nowrap transition font-bold \${isAct ? 'bg-purple-700 text-white border-purple-700 shadow-sm' : 'bg-white text-slate-600 border-slate-200 hover:bg-slate-50'}">
                      \${b.label} \${cat !== 'ALL' ? '(' + catCounts[cat] + ')' : ''}
                    </button>
                  \`;
                }).join('')}
              </div>
            </div>
        \`;

        // MODE 1: STUDY CARDS
        if (s.mode === 'cards') {
          html += \`
            <div class="space-y-3">
              <!-- Search Box -->
              <div class="relative">
                <i class="fa-solid fa-search absolute right-3.5 top-3.5 text-slate-400 text-xs"></i>
                <input type="text" id="\${unitId}-vocab-search" oninput="VocabSuite.filterCards('\${unitId}')" placeholder="ابحث في الكلمات بالفرنسية أو العربية..." class="w-full pr-9 pl-4 py-2.5 rounded-2xl bg-white border border-slate-200 text-xs text-slate-800 placeholder-slate-400 focus:outline-none focus:border-purple-500 shadow-sm">
              </div>

              <!-- Words Grid -->
              <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-2.5" id="\${unitId}-cards-container">
                \${filteredWords.map((w, idx) => {
                  const badge = VocabSuite.getCategoryBadge(w.category);
                  return \`
                    <div id="\${unitId}-card-\${w.id}" data-fr="\${w.french.toLowerCase()}" data-ar="\${w.arabic}" class="\${unitId}-vocab-card p-3 rounded-2xl bg-white border border-slate-200 hover:border-purple-300 shadow-sm hover:shadow transition flex items-center justify-between gap-3">
                      <div class="min-w-0 flex-1 space-y-1">
                        <div class="flex items-center gap-1.5 flex-wrap">
                          <span class="text-xs sm:text-sm font-black text-indigo-950 font-french tracking-tight">\${w.french}</span>
                          <span class="text-[10px] px-2 py-0.5 rounded-full border font-bold \${badge.cls}">\${badge.label}</span>
                        </div>
                        <p class="text-xs text-slate-600 font-sans truncate">\${w.arabic}</p>
                      </div>
                      <button onclick="speakText('\${w.french.replace(/'/g, "\\\\'")}')" class="w-9 h-9 rounded-full bg-purple-50 hover:bg-purple-100 text-purple-700 flex items-center justify-center flex-shrink-0 transition active:scale-95 shadow-sm">
                        <i class="fa-solid fa-volume-high text-xs"></i>
                      </button>
                    </div>
                  \`;
                }).join('')}
              </div>
            </div>
          \`;
        }

        // MODE 2: 3D FLIP CARDS
        else if (s.mode === 'flip') {
          if (!filteredWords.length) {
            html += '<div class=\"p-8 text-center text-slate-400 text-xs\">لا توجد كلمات في هذا القسم.</div>';
          } else {
            const currentWord = filteredWords[s.flipIndex];
            const badge = VocabSuite.getCategoryBadge(currentWord.category);
            const isMastered = s.mastered.has(currentWord.id);
            const isReview = s.review.has(currentWord.id);

            html += \`
              <div class="space-y-4 max-w-xl mx-auto">
                <!-- Status & Counter -->
                <div class="flex items-center justify-between text-xs font-bold text-slate-600 bg-white p-3 rounded-2xl border border-slate-200 shadow-sm">
                  <div class="flex items-center gap-2">
                    <span class="text-purple-700 font-french font-black text-sm">البطاقة \${s.flipIndex + 1} من \${filteredWords.length}</span>
                    <span class="px-2 py-0.5 rounded-full border \${badge.cls} text-[10px]">\${badge.label}</span>
                  </div>
                  <div class="flex items-center gap-2">
                    <span class="px-2 py-0.5 rounded-full bg-emerald-100 text-emerald-800 text-[10px]">أتقنتها: \${s.mastered.size}</span>
                    <span class="px-2 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[10px]">للمراجعة: \${s.review.size}</span>
                  </div>
                </div>

                <!-- 3D Flip Card Container -->
                <div class="flip-card-wrapper h-64 sm:h-72 w-full cursor-pointer select-none" style="perspective: 1000px;" onclick="VocabSuite.flipCard('\${unitId}')">
                  <div id="\${unitId}-flip-card-inner" class="relative w-full h-full transition-transform duration-500 rounded-3xl shadow-lg border border-purple-200" style="transform-style: preserve-3d; transition: transform 0.6s;">
                    <!-- Front (French) -->
                    <div class="absolute inset-0 w-full h-full rounded-3xl bg-gradient-to-br from-indigo-900 via-purple-900 to-indigo-950 text-white p-6 flex flex-col justify-between items-center text-center" style="backface-visibility: hidden;">
                      <div class="w-full flex items-center justify-between text-xs text-purple-200">
                        <span>انقر على البطاقة للقلب 🔄</span>
                        <button onclick="event.stopPropagation(); speakText('\${currentWord.french.replace(/'/g, "\\\\'")}')" class="px-3 py-1 rounded-full bg-white/20 hover:bg-white/30 text-white flex items-center gap-1.5 transition">
                          <i class="fa-solid fa-volume-high"></i> <span>استمع</span>
                        </button>
                      </div>
                      <div class="space-y-2">
                        <span class="text-2xl sm:text-3xl font-black font-french tracking-wide text-white drop-shadow">\${currentWord.french}</span>
                        <p class="text-xs text-purple-300 font-french">Français • انقر لعرض المعنى العربي</p>
                      </div>
                      <div class="text-[11px] text-purple-300">
                        \${isMastered ? '<span class=\"text-emerald-400 font-bold\">✓ تم إتقان هذه الكلمة</span>' : (isReview ? '<span class=\"text-amber-400 font-bold\">⏳ موضوعة للمراجعة</span>' : 'انقر على البطاقة لرؤية الترجمة')}
                      </div>
                    </div>

                    <!-- Back (Arabic) -->
                    <div class="absolute inset-0 w-full h-full rounded-3xl bg-gradient-to-br from-emerald-800 via-teal-800 to-emerald-950 text-white p-6 flex flex-col justify-between items-center text-center" style="backface-visibility: hidden; transform: rotateY(180deg);">
                      <div class="w-full flex items-center justify-between text-xs text-emerald-200">
                        <span>الترجمة العربية 🇫🇷 ➔ 🇪🇬</span>
                        <button onclick="event.stopPropagation(); speakText('\${currentWord.french.replace(/'/g, "\\\\'")}')" class="px-3 py-1 rounded-full bg-white/20 hover:bg-white/30 text-white flex items-center gap-1.5 transition">
                          <i class="fa-solid fa-volume-high"></i>
                        </button>
                      </div>
                      <div class="space-y-2">
                        <span class="text-2xl sm:text-3xl font-black text-amber-200 drop-shadow">\${currentWord.arabic}</span>
                        <p class="text-sm font-bold font-french text-emerald-100">\${currentWord.french}</p>
                      </div>
                      <div class="text-[11px] text-emerald-200">
                        انقر للعودة إلى الكلمة بالفرنسية 🔄
                      </div>
                    </div>
                  </div>
                </div>

                <!-- Navigation & Action Buttons -->
                <div class="flex items-center justify-between gap-2 pt-2">
                  <button onclick="VocabSuite.prevFlipCard('\${unitId}')" class="px-4 py-2.5 rounded-2xl bg-white border border-slate-200 text-slate-700 text-xs font-bold hover:bg-slate-50 transition flex items-center gap-1.5 shadow-sm">
                    <i class="fa-solid fa-arrow-right"></i> <span>السابق</span>
                  </button>
                  <div class="flex items-center gap-2">
                    <button onclick="VocabSuite.markReview('\${unitId}')" class="px-3 py-2 rounded-2xl bg-amber-50 border border-amber-200 text-amber-800 text-xs font-bold hover:bg-amber-100 transition shadow-sm">
                      ⏳ أراجعها
                    </button>
                    <button onclick="VocabSuite.markMastered('\${unitId}')" class="px-3 py-2 rounded-2xl bg-emerald-50 border border-emerald-200 text-emerald-800 text-xs font-bold hover:bg-emerald-100 transition shadow-sm">
                      ✓ أتقنتها
                    </button>
                    <button onclick="VocabSuite.shuffleFlipCards('\${unitId}')" class="px-3 py-2 rounded-2xl bg-purple-50 border border-purple-200 text-purple-800 text-xs font-bold hover:bg-purple-100 transition shadow-sm" title="عشوائي">
                      <i class="fa-solid fa-shuffle"></i>
                    </button>
                  </div>
                  <button onclick="VocabSuite.nextFlipCard('\${unitId}')" class="px-4 py-2.5 rounded-2xl bg-white border border-slate-200 text-slate-700 text-xs font-bold hover:bg-slate-50 transition flex items-center gap-1.5 shadow-sm">
                    <span>التالي</span> <i class="fa-solid fa-arrow-left"></i>
                  </button>
                </div>
              </div>
            \`;
          }
        }

        // MODE 3: TRANSLATION QUIZ
        else if (s.mode === 'quiz') {
          if (!filteredWords.length) {
            html += '<div class=\"p-8 text-center text-slate-400 text-xs\">لا توجد كلمات في هذا القسم.</div>';
          } else {
            const currentWord = filteredWords[s.quizIndex % filteredWords.length];
            const badge = VocabSuite.getCategoryBadge(currentWord.category);

            // Generate 4 options
            const otherWords = allWords.filter(w => w.id !== currentWord.id);
            const shuffledOthers = [...otherWords].sort(() => 0.5 - Math.random()).slice(0, 3);
            const choices = [currentWord, ...shuffledOthers].sort(() => 0.5 - Math.random());

            html += \`
              <div class="space-y-4 max-w-xl mx-auto">
                <!-- Score & Streak Bar -->
                <div class="bg-gradient-to-r from-purple-700 to-indigo-800 rounded-2xl p-4 text-white shadow-sm flex items-center justify-between">
                  <div class="space-y-0.5">
                    <span class="text-[11px] text-purple-200 font-bold">اختبار الترجمة السريع</span>
                    <h4 class="text-sm font-black">السؤال \${(s.quizIndex % filteredWords.length) + 1} من \${filteredWords.length}</h4>
                  </div>
                  <div class="flex items-center gap-3">
                    <div class="text-center bg-white/10 px-3 py-1 rounded-xl">
                      <span class="block text-[10px] text-purple-200">النقاط</span>
                      <span id="\${unitId}-quiz-score-val" class="font-black text-amber-300 text-sm">\${s.quizScore}</span>
                    </div>
                    <div class="text-center bg-white/10 px-3 py-1 rounded-xl">
                      <span class="block text-[10px] text-purple-200">المتتالي</span>
                      <span id="\${unitId}-quiz-streak-val" class="font-black text-emerald-300 text-sm">\${s.quizStreak} 🔥</span>
                    </div>
                  </div>
                </div>

                <!-- Question Card -->
                <div class="bg-white rounded-3xl p-6 border border-slate-200 shadow-sm text-center space-y-4">
                  <div class="flex items-center justify-between text-xs">
                    <span class="px-2.5 py-0.5 rounded-full border \${badge.cls} font-bold">\${badge.label}</span>
                    <button onclick="speakText('\${currentWord.french.replace(/'/g, "\\\\'")}')" class="px-3 py-1 rounded-full bg-purple-50 text-purple-700 hover:bg-purple-100 flex items-center gap-1.5 transition text-xs font-bold">
                      <i class="fa-solid fa-volume-high"></i> <span>استمع للكلمة</span>
                    </button>
                  </div>

                  <div class="py-2">
                    <p class="text-xs text-slate-500 font-medium mb-1">ما هي الترجمة الصحيحة للكلمة الفرنسية التالية؟</p>
                    <span class="text-2xl sm:text-3xl font-black font-french text-indigo-950">\${currentWord.french}</span>
                  </div>

                  <!-- 4 Choices -->
                  <div class="grid grid-cols-1 sm:grid-cols-2 gap-2.5 vocab-quiz-options pt-2">
                    \${choices.map(c => \`
                      <button data-correct="\${c.id === currentWord.id}" onclick="VocabSuite.checkQuizAnswer('\${unitId}', \${c.id === currentWord.id}, this, '\${currentWord.arabic.replace(/'/g, "\\\\'")}')" class="p-3.5 rounded-2xl border border-slate-200 bg-slate-50 hover:bg-purple-50 hover:border-purple-300 text-slate-800 text-xs sm:text-sm font-bold text-center transition active:scale-98">
                        \${c.arabic}
                      </button>
                    \`).join('')}
                  </div>

                  <!-- Feedback & Next Button -->
                  <div id="\${unitId}-quiz-feedback" class="hidden pt-2"></div>
                  <div class="flex items-center justify-between gap-2 pt-2">
                    <button onclick="VocabSuite.restartQuiz('\${unitId}')" class="px-3 py-2 rounded-xl text-slate-500 hover:text-slate-800 text-xs font-bold transition">
                      <i class="fa-solid fa-rotate-right ml-1"></i> إعادة من البداية
                    </button>
                    <button id="\${unitId}-quiz-next-btn" onclick="VocabSuite.nextQuizQuestion('\${unitId}')" class="hidden px-5 py-2.5 rounded-2xl bg-purple-600 hover:bg-purple-700 text-white text-xs font-bold shadow-md transition flex items-center gap-1.5">
                      <span>السؤال التالي</span> <i class="fa-solid fa-arrow-left"></i>
                    </button>
                  </div>
                </div>
              </div>
            \`;
          }
        }

        // MODE 4: MISSING LETTERS PUZZLE
        else if (s.mode === 'puzzle') {
          if (!filteredWords.length) {
            html += '<div class=\"p-8 text-center text-slate-400 text-xs\">لا توجد كلمات في هذا القسم.</div>';
          } else {
            const currentWord = filteredWords[s.puzzleIndex % filteredWords.length];
            const frClean = currentWord.french.replace(/^un |^une |^le |^la |^l'|^des |^du |^de la |^de l'/i, '').trim();
            const letters = frClean.split('');
            const validIndices = [];
            for (let i = 0; i < letters.length; i++) {
              if (/[a-zA-Zàâäéèêëîïôöùûüç]/i.test(letters[i])) {
                validIndices.push(i);
              }
            }
            const missingIdx = validIndices.length ? validIndices[Math.floor(validIndices.length / 2)] : 0;
            const correctChar = letters[missingIdx] || 'e';

            // Candidates
            const alphaPool = ['a', 'e', 'i', 'o', 'u', 'é', 'è', 's', 't', 'r', 'n', 'l', 'c'];
            const wrongChars = alphaPool.filter(c => c.toLowerCase() !== correctChar.toLowerCase()).sort(() => 0.5 - Math.random()).slice(0, 3);
            const puzzleOptions = [correctChar, ...wrongChars].sort(() => 0.5 - Math.random());

            html += \`
              <div class="space-y-4 max-w-xl mx-auto">
                <div class="bg-gradient-to-r from-teal-700 to-emerald-800 rounded-2xl p-4 text-white shadow-sm flex items-center justify-between">
                  <div>
                    <span class="text-[11px] text-teal-200 font-bold">تحدي الحروف والتهجئة الصحيحة</span>
                    <h4 class="text-sm font-black">الكلمة \${(s.puzzleIndex % filteredWords.length) + 1} من \${filteredWords.length}</h4>
                  </div>
                  <div class="text-center bg-white/10 px-3 py-1 rounded-xl">
                    <span class="block text-[10px] text-teal-200">النقاط</span>
                    <span id="\${unitId}-puzzle-score-val" class="font-black text-amber-300 text-sm">\${s.puzzleScore}</span>
                  </div>
                </div>

                <div class="bg-white rounded-3xl p-6 border border-slate-200 shadow-sm text-center space-y-5">
                  <div class="space-y-1">
                    <span class="text-xs text-slate-500 font-medium">الترجمة بالعربية:</span>
                    <h3 class="text-xl font-black text-slate-900">\${currentWord.arabic}</h3>
                  </div>

                  <!-- Word Display with missing slot -->
                  <div class="flex items-center justify-center gap-1.5 flex-wrap py-3" dir="ltr">
                    \${letters.map((char, i) => {
                      if (i === missingIdx) {
                        return \`<span id="\${unitId}-puzzle-missing-slot" class="w-8 h-10 sm:w-10 sm:h-12 rounded-xl bg-amber-100 border-2 border-dashed border-amber-400 text-amber-900 text-lg sm:text-xl font-black flex items-center justify-center font-french animate-pulse">?</span>\`;
                      } else {
                        return \`<span class="w-8 h-10 sm:w-10 sm:h-12 rounded-xl bg-slate-100 border border-slate-200 text-slate-800 text-lg sm:text-xl font-black flex items-center justify-center font-french">\${char}</span>\`;
                      }
                    }).join('')}
                  </div>

                  <p class="text-xs text-slate-600 font-bold">اختر الحرف الناقص لإكمال الكلمة بشكل صحيح:</p>

                  <!-- 4 Letter Options -->
                  <div class="flex items-center justify-center gap-3">
                    \${puzzleOptions.map(char => \`
                      <button onclick="VocabSuite.checkPuzzleLetter('\${unitId}', '\${char}', '\${correctChar}', '\${currentWord.french.replace(/'/g, "\\\\'")}')" class="w-12 h-12 sm:w-14 sm:h-14 rounded-2xl bg-white border-2 border-slate-200 hover:border-teal-500 hover:bg-teal-50 text-slate-800 text-lg sm:text-xl font-black font-french shadow-sm transition active:scale-95 flex items-center justify-center">
                        \${char}
                      </button>
                    \`).join('')}
                  </div>

                  <div id="\${unitId}-puzzle-feedback" class="hidden pt-2"></div>
                  <div class="flex items-center justify-between gap-2 pt-2">
                    <button onclick="VocabSuite.restartPuzzle('\${unitId}')" class="px-3 py-2 rounded-xl text-slate-500 hover:text-slate-800 text-xs font-bold transition">
                      <i class="fa-solid fa-rotate-right ml-1"></i> إعادة من البداية
                    </button>
                    <button id="\${unitId}-puzzle-next-btn" onclick="VocabSuite.nextPuzzleQuestion('\${unitId}')" class="hidden px-5 py-2.5 rounded-2xl bg-teal-600 hover:bg-teal-700 text-white text-xs font-bold shadow-md transition flex items-center gap-1.5">
                      <span>الكلمة التالية</span> <i class="fa-solid fa-arrow-left"></i>
                    </button>
                  </div>
                </div>
              </div>
            \`;
          }
        }

        html += '</div>';
        container.innerHTML = html;
      },

      filterCards(unitId) {
        const q = (document.getElementById(unitId + '-vocab-search')?.value || '').trim().toLowerCase();
        document.querySelectorAll('.' + unitId + '-vocab-card').forEach(card => {
          const fr = card.getAttribute('data-fr') || '';
          const ar = card.getAttribute('data-ar') || '';
          const matches = !q || fr.includes(q) || ar.includes(q);
          card.style.display = matches ? 'flex' : 'none';
        });
      }
    };
  `;
}

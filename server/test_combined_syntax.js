const DATA = { course: {}, bookletLieux: {} }; function playClickSound(){} function playSuccessSound(){} function playErrorSound(){} function speakText(){} function navigateTo(){} function renderQuizQuestionsList(){}

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
          btn.innerHTML = '<i class="fa-solid fa-stop text-rose-500 animate-pulse ml-1.5"></i><span>إيقاف القراءة</span>';
          btn.classList.add('bg-rose-50', 'text-rose-700', 'border-rose-300');
          btn.classList.remove('bg-white', 'text-slate-700', 'border-slate-200');
        } else {
          btn.innerHTML = '<i class="fa-solid fa-circle-play text-purple-600 ml-1.5"></i><span>تشغيل القراءة المتتالية</span>';
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
            feedback.innerHTML = '<div class="p-3 rounded-xl bg-emerald-50 text-emerald-800 border border-emerald-200 font-bold flex items-center justify-between"><span>🎉 إجابة صحيحة وممتازة! (+10 نقاط)</span><span class="text-xs bg-emerald-200 px-2.5 py-0.5 rounded-full">متتالي: ' + s.quizStreak + ' 🔥</span></div>';
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
            feedback.innerHTML = '<div class="p-3 rounded-xl bg-rose-50 text-rose-800 border border-rose-200 font-bold text-xs">❌ إجابة غير صحيحة. الإجابة الصحيحة هي: <strong>«' + correctText + '»</strong></div>';
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
            feedback.innerHTML = '<div class="p-3 rounded-xl bg-emerald-50 text-emerald-800 border border-emerald-200 font-bold flex items-center justify-between text-xs"><span>🎉 أحسنت! الحرف الصحيح هو (' + correctLetter + ') والكلمة: ' + fullWord + '</span><span class="bg-emerald-200 px-2 py-0.5 rounded-full">+10 نقاط</span></div>';
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
            feedback.innerHTML = '<div class="p-3 rounded-xl bg-rose-50 text-rose-800 border border-rose-200 font-bold text-xs">❌ إجابة خاطئة. الحرف الصحيح هو <strong>(' + correctLetter + ')</strong> لكلمة: <strong>' + fullWord + '</strong></div>';
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

        let html = `
          <div class="space-y-4">
            <!-- Header Card with Mode Switcher -->
            <div class="bg-white rounded-2xl p-4 sm:p-5 border border-purple-100 shadow-sm space-y-4">
              <div class="flex flex-wrap items-center justify-between gap-3 border-b border-slate-100 pb-3">
                <div>
                  <div class="flex items-center gap-2">
                    <span class="px-2.5 py-0.5 rounded-full bg-purple-100 text-purple-800 text-[11px] font-bold font-french">${unitPageTag}</span>
                    <span class="px-2 py-0.5 rounded-full bg-emerald-100 text-emerald-800 text-[11px] font-bold">4 تدريبات تفاعلية ⚡</span>
                  </div>
                  <h3 class="text-base sm:text-lg font-black text-slate-900 mt-1">${unitTitle}</h3>
                </div>
                <!-- Continuous Audio Toggle -->
                <button id="${unitId}-continuous-btn" onclick="VocabSuite.toggleContinuousAudio('${unitId}')" class="px-3.5 py-1.5 rounded-xl border border-slate-200 bg-white text-xs font-bold text-slate-700 hover:bg-purple-50 hover:text-purple-700 hover:border-purple-200 shadow-sm transition flex items-center gap-1.5">
                  <i class="fa-solid fa-circle-play text-purple-600 ml-1.5"></i>
                  <span>تشغيل القراءة المتتالية</span>
                </button>
              </div>

              <!-- 4 Mode Switcher Buttons -->
              <div class="grid grid-cols-2 sm:grid-cols-4 gap-2">
                <button onclick="VocabSuite.setMode('${unitId}', 'cards')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 ${s.mode === 'cards' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-layer-group"></i>
                  <span>🗂️ بطاقات الكلمات</span>
                </button>
                <button onclick="VocabSuite.setMode('${unitId}', 'flip')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 ${s.mode === 'flip' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-arrows-rotate"></i>
                  <span>🔄 البطاقات المقلوبة</span>
                </button>
                <button onclick="VocabSuite.setMode('${unitId}', 'quiz')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 ${s.mode === 'quiz' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-bullseye"></i>
                  <span>🎯 اختبار الترجمة</span>
                </button>
                <button onclick="VocabSuite.setMode('${unitId}', 'puzzle')" class="p-2.5 rounded-xl border text-xs font-bold transition flex items-center justify-center gap-1.5 ${s.mode === 'puzzle' ? 'bg-purple-600 text-white border-purple-600 shadow-md scale-[1.02]' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-purple-50'}">
                  <i class="fa-solid fa-puzzle-piece"></i>
                  <span>🧩 الحروف الناقصة</span>
                </button>
              </div>

              <!-- Category Filter Chips -->
              <div class="flex items-center gap-1.5 overflow-x-auto pb-1 custom-scrollbar text-xs">
                ${availableCats.map(cat => {
                  const b = cat === 'ALL' ? { label: 'الكل (' + catCounts.ALL + ')' } : VocabSuite.getCategoryBadge(cat);
                  const isAct = s.cat === cat;
                  return `
                    <button onclick="VocabSuite.setCategory('${unitId}', '${cat}')" class="px-3 py-1 rounded-full border whitespace-nowrap transition font-bold ${isAct ? 'bg-purple-700 text-white border-purple-700 shadow-sm' : 'bg-white text-slate-600 border-slate-200 hover:bg-slate-50'}">
                      ${b.label} ${cat !== 'ALL' ? '(' + catCounts[cat] + ')' : ''}
                    </button>
                  `;
                }).join('')}
              </div>
            </div>
        `;

        // MODE 1: STUDY CARDS
        if (s.mode === 'cards') {
          html += `
            <div class="space-y-3">
              <!-- Search Box -->
              <div class="relative">
                <i class="fa-solid fa-search absolute right-3.5 top-3.5 text-slate-400 text-xs"></i>
                <input type="text" id="${unitId}-vocab-search" oninput="VocabSuite.filterCards('${unitId}')" placeholder="ابحث في الكلمات بالفرنسية أو العربية..." class="w-full pr-9 pl-4 py-2.5 rounded-2xl bg-white border border-slate-200 text-xs text-slate-800 placeholder-slate-400 focus:outline-none focus:border-purple-500 shadow-sm">
              </div>

              <!-- Words Grid -->
              <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-2.5" id="${unitId}-cards-container">
                ${filteredWords.map((w, idx) => {
                  const badge = VocabSuite.getCategoryBadge(w.category);
                  return `
                    <div id="${unitId}-card-${w.id}" data-fr="${w.french.toLowerCase()}" data-ar="${w.arabic}" class="${unitId}-vocab-card p-3 rounded-2xl bg-white border border-slate-200 hover:border-purple-300 shadow-sm hover:shadow transition flex items-center justify-between gap-3">
                      <div class="min-w-0 flex-1 space-y-1">
                        <div class="flex items-center gap-1.5 flex-wrap">
                          <span class="text-xs sm:text-sm font-black text-indigo-950 font-french tracking-tight">${w.french}</span>
                          <span class="text-[10px] px-2 py-0.5 rounded-full border font-bold ${badge.cls}">${badge.label}</span>
                        </div>
                        <p class="text-xs text-slate-600 font-sans truncate">${w.arabic}</p>
                      </div>
                      <button onclick="speakText('${w.french.replace(/'/g, "\\'")}')" class="w-9 h-9 rounded-full bg-purple-50 hover:bg-purple-100 text-purple-700 flex items-center justify-center flex-shrink-0 transition active:scale-95 shadow-sm">
                        <i class="fa-solid fa-volume-high text-xs"></i>
                      </button>
                    </div>
                  `;
                }).join('')}
              </div>
            </div>
          `;
        }

        // MODE 2: 3D FLIP CARDS
        else if (s.mode === 'flip') {
          if (!filteredWords.length) {
            html += '<div class="p-8 text-center text-slate-400 text-xs">لا توجد كلمات في هذا القسم.</div>';
          } else {
            const currentWord = filteredWords[s.flipIndex];
            const badge = VocabSuite.getCategoryBadge(currentWord.category);
            const isMastered = s.mastered.has(currentWord.id);
            const isReview = s.review.has(currentWord.id);

            html += `
              <div class="space-y-4 max-w-xl mx-auto">
                <!-- Status & Counter -->
                <div class="flex items-center justify-between text-xs font-bold text-slate-600 bg-white p-3 rounded-2xl border border-slate-200 shadow-sm">
                  <div class="flex items-center gap-2">
                    <span class="text-purple-700 font-french font-black text-sm">البطاقة ${s.flipIndex + 1} من ${filteredWords.length}</span>
                    <span class="px-2 py-0.5 rounded-full border ${badge.cls} text-[10px]">${badge.label}</span>
                  </div>
                  <div class="flex items-center gap-2">
                    <span class="px-2 py-0.5 rounded-full bg-emerald-100 text-emerald-800 text-[10px]">أتقنتها: ${s.mastered.size}</span>
                    <span class="px-2 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[10px]">للمراجعة: ${s.review.size}</span>
                  </div>
                </div>

                <!-- 3D Flip Card Container -->
                <div class="flip-card-wrapper h-64 sm:h-72 w-full cursor-pointer select-none" style="perspective: 1000px;" onclick="VocabSuite.flipCard('${unitId}')">
                  <div id="${unitId}-flip-card-inner" class="relative w-full h-full transition-transform duration-500 rounded-3xl shadow-lg border border-purple-200" style="transform-style: preserve-3d; transition: transform 0.6s;">
                    <!-- Front (French) -->
                    <div class="absolute inset-0 w-full h-full rounded-3xl bg-gradient-to-br from-indigo-900 via-purple-900 to-indigo-950 text-white p-6 flex flex-col justify-between items-center text-center" style="backface-visibility: hidden;">
                      <div class="w-full flex items-center justify-between text-xs text-purple-200">
                        <span>انقر على البطاقة للقلب 🔄</span>
                        <button onclick="event.stopPropagation(); speakText('${currentWord.french.replace(/'/g, "\\'")}')" class="px-3 py-1 rounded-full bg-white/20 hover:bg-white/30 text-white flex items-center gap-1.5 transition">
                          <i class="fa-solid fa-volume-high"></i> <span>استمع</span>
                        </button>
                      </div>
                      <div class="space-y-2">
                        <span class="text-2xl sm:text-3xl font-black font-french tracking-wide text-white drop-shadow">${currentWord.french}</span>
                        <p class="text-xs text-purple-300 font-french">Français • انقر لعرض المعنى العربي</p>
                      </div>
                      <div class="text-[11px] text-purple-300">
                        ${isMastered ? '<span class="text-emerald-400 font-bold">✓ تم إتقان هذه الكلمة</span>' : (isReview ? '<span class="text-amber-400 font-bold">⏳ موضوعة للمراجعة</span>' : 'انقر على البطاقة لرؤية الترجمة')}
                      </div>
                    </div>

                    <!-- Back (Arabic) -->
                    <div class="absolute inset-0 w-full h-full rounded-3xl bg-gradient-to-br from-emerald-800 via-teal-800 to-emerald-950 text-white p-6 flex flex-col justify-between items-center text-center" style="backface-visibility: hidden; transform: rotateY(180deg);">
                      <div class="w-full flex items-center justify-between text-xs text-emerald-200">
                        <span>الترجمة العربية 🇫🇷 ➔ 🇪🇬</span>
                        <button onclick="event.stopPropagation(); speakText('${currentWord.french.replace(/'/g, "\\'")}')" class="px-3 py-1 rounded-full bg-white/20 hover:bg-white/30 text-white flex items-center gap-1.5 transition">
                          <i class="fa-solid fa-volume-high"></i>
                        </button>
                      </div>
                      <div class="space-y-2">
                        <span class="text-2xl sm:text-3xl font-black text-amber-200 drop-shadow">${currentWord.arabic}</span>
                        <p class="text-sm font-bold font-french text-emerald-100">${currentWord.french}</p>
                      </div>
                      <div class="text-[11px] text-emerald-200">
                        انقر للعودة إلى الكلمة بالفرنسية 🔄
                      </div>
                    </div>
                  </div>
                </div>

                <!-- Navigation & Action Buttons -->
                <div class="flex items-center justify-between gap-2 pt-2">
                  <button onclick="VocabSuite.prevFlipCard('${unitId}')" class="px-4 py-2.5 rounded-2xl bg-white border border-slate-200 text-slate-700 text-xs font-bold hover:bg-slate-50 transition flex items-center gap-1.5 shadow-sm">
                    <i class="fa-solid fa-arrow-right"></i> <span>السابق</span>
                  </button>
                  <div class="flex items-center gap-2">
                    <button onclick="VocabSuite.markReview('${unitId}')" class="px-3 py-2 rounded-2xl bg-amber-50 border border-amber-200 text-amber-800 text-xs font-bold hover:bg-amber-100 transition shadow-sm">
                      ⏳ أراجعها
                    </button>
                    <button onclick="VocabSuite.markMastered('${unitId}')" class="px-3 py-2 rounded-2xl bg-emerald-50 border border-emerald-200 text-emerald-800 text-xs font-bold hover:bg-emerald-100 transition shadow-sm">
                      ✓ أتقنتها
                    </button>
                    <button onclick="VocabSuite.shuffleFlipCards('${unitId}')" class="px-3 py-2 rounded-2xl bg-purple-50 border border-purple-200 text-purple-800 text-xs font-bold hover:bg-purple-100 transition shadow-sm" title="عشوائي">
                      <i class="fa-solid fa-shuffle"></i>
                    </button>
                  </div>
                  <button onclick="VocabSuite.nextFlipCard('${unitId}')" class="px-4 py-2.5 rounded-2xl bg-white border border-slate-200 text-slate-700 text-xs font-bold hover:bg-slate-50 transition flex items-center gap-1.5 shadow-sm">
                    <span>التالي</span> <i class="fa-solid fa-arrow-left"></i>
                  </button>
                </div>
              </div>
            `;
          }
        }

        // MODE 3: TRANSLATION QUIZ
        else if (s.mode === 'quiz') {
          if (!filteredWords.length) {
            html += '<div class="p-8 text-center text-slate-400 text-xs">لا توجد كلمات في هذا القسم.</div>';
          } else {
            const currentWord = filteredWords[s.quizIndex % filteredWords.length];
            const badge = VocabSuite.getCategoryBadge(currentWord.category);

            // Generate 4 options
            const otherWords = allWords.filter(w => w.id !== currentWord.id);
            const shuffledOthers = [...otherWords].sort(() => 0.5 - Math.random()).slice(0, 3);
            const choices = [currentWord, ...shuffledOthers].sort(() => 0.5 - Math.random());

            html += `
              <div class="space-y-4 max-w-xl mx-auto">
                <!-- Score & Streak Bar -->
                <div class="bg-gradient-to-r from-purple-700 to-indigo-800 rounded-2xl p-4 text-white shadow-sm flex items-center justify-between">
                  <div class="space-y-0.5">
                    <span class="text-[11px] text-purple-200 font-bold">اختبار الترجمة السريع</span>
                    <h4 class="text-sm font-black">السؤال ${(s.quizIndex % filteredWords.length) + 1} من ${filteredWords.length}</h4>
                  </div>
                  <div class="flex items-center gap-3">
                    <div class="text-center bg-white/10 px-3 py-1 rounded-xl">
                      <span class="block text-[10px] text-purple-200">النقاط</span>
                      <span id="${unitId}-quiz-score-val" class="font-black text-amber-300 text-sm">${s.quizScore}</span>
                    </div>
                    <div class="text-center bg-white/10 px-3 py-1 rounded-xl">
                      <span class="block text-[10px] text-purple-200">المتتالي</span>
                      <span id="${unitId}-quiz-streak-val" class="font-black text-emerald-300 text-sm">${s.quizStreak} 🔥</span>
                    </div>
                  </div>
                </div>

                <!-- Question Card -->
                <div class="bg-white rounded-3xl p-6 border border-slate-200 shadow-sm text-center space-y-4">
                  <div class="flex items-center justify-between text-xs">
                    <span class="px-2.5 py-0.5 rounded-full border ${badge.cls} font-bold">${badge.label}</span>
                    <button onclick="speakText('${currentWord.french.replace(/'/g, "\\'")}')" class="px-3 py-1 rounded-full bg-purple-50 text-purple-700 hover:bg-purple-100 flex items-center gap-1.5 transition text-xs font-bold">
                      <i class="fa-solid fa-volume-high"></i> <span>استمع للكلمة</span>
                    </button>
                  </div>

                  <div class="py-2">
                    <p class="text-xs text-slate-500 font-medium mb-1">ما هي الترجمة الصحيحة للكلمة الفرنسية التالية؟</p>
                    <span class="text-2xl sm:text-3xl font-black font-french text-indigo-950">${currentWord.french}</span>
                  </div>

                  <!-- 4 Choices -->
                  <div class="grid grid-cols-1 sm:grid-cols-2 gap-2.5 vocab-quiz-options pt-2">
                    ${choices.map(c => `
                      <button data-correct="${c.id === currentWord.id}" onclick="VocabSuite.checkQuizAnswer('${unitId}', ${c.id === currentWord.id}, this, '${currentWord.arabic.replace(/'/g, "\\'")}')" class="p-3.5 rounded-2xl border border-slate-200 bg-slate-50 hover:bg-purple-50 hover:border-purple-300 text-slate-800 text-xs sm:text-sm font-bold text-center transition active:scale-98">
                        ${c.arabic}
                      </button>
                    `).join('')}
                  </div>

                  <!-- Feedback & Next Button -->
                  <div id="${unitId}-quiz-feedback" class="hidden pt-2"></div>
                  <div class="flex items-center justify-between gap-2 pt-2">
                    <button onclick="VocabSuite.restartQuiz('${unitId}')" class="px-3 py-2 rounded-xl text-slate-500 hover:text-slate-800 text-xs font-bold transition">
                      <i class="fa-solid fa-rotate-right ml-1"></i> إعادة من البداية
                    </button>
                    <button id="${unitId}-quiz-next-btn" onclick="VocabSuite.nextQuizQuestion('${unitId}')" class="hidden px-5 py-2.5 rounded-2xl bg-purple-600 hover:bg-purple-700 text-white text-xs font-bold shadow-md transition flex items-center gap-1.5">
                      <span>السؤال التالي</span> <i class="fa-solid fa-arrow-left"></i>
                    </button>
                  </div>
                </div>
              </div>
            `;
          }
        }

        // MODE 4: MISSING LETTERS PUZZLE
        else if (s.mode === 'puzzle') {
          if (!filteredWords.length) {
            html += '<div class="p-8 text-center text-slate-400 text-xs">لا توجد كلمات في هذا القسم.</div>';
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

            html += `
              <div class="space-y-4 max-w-xl mx-auto">
                <div class="bg-gradient-to-r from-teal-700 to-emerald-800 rounded-2xl p-4 text-white shadow-sm flex items-center justify-between">
                  <div>
                    <span class="text-[11px] text-teal-200 font-bold">تحدي الحروف والتهجئة الصحيحة</span>
                    <h4 class="text-sm font-black">الكلمة ${(s.puzzleIndex % filteredWords.length) + 1} من ${filteredWords.length}</h4>
                  </div>
                  <div class="text-center bg-white/10 px-3 py-1 rounded-xl">
                    <span class="block text-[10px] text-teal-200">النقاط</span>
                    <span id="${unitId}-puzzle-score-val" class="font-black text-amber-300 text-sm">${s.puzzleScore}</span>
                  </div>
                </div>

                <div class="bg-white rounded-3xl p-6 border border-slate-200 shadow-sm text-center space-y-5">
                  <div class="space-y-1">
                    <span class="text-xs text-slate-500 font-medium">الترجمة بالعربية:</span>
                    <h3 class="text-xl font-black text-slate-900">${currentWord.arabic}</h3>
                  </div>

                  <!-- Word Display with missing slot -->
                  <div class="flex items-center justify-center gap-1.5 flex-wrap py-3" dir="ltr">
                    ${letters.map((char, i) => {
                      if (i === missingIdx) {
                        return `<span id="${unitId}-puzzle-missing-slot" class="w-8 h-10 sm:w-10 sm:h-12 rounded-xl bg-amber-100 border-2 border-dashed border-amber-400 text-amber-900 text-lg sm:text-xl font-black flex items-center justify-center font-french animate-pulse">?</span>`;
                      } else {
                        return `<span class="w-8 h-10 sm:w-10 sm:h-12 rounded-xl bg-slate-100 border border-slate-200 text-slate-800 text-lg sm:text-xl font-black flex items-center justify-center font-french">${char}</span>`;
                      }
                    }).join('')}
                  </div>

                  <p class="text-xs text-slate-600 font-bold">اختر الحرف الناقص لإكمال الكلمة بشكل صحيح:</p>

                  <!-- 4 Letter Options -->
                  <div class="flex items-center justify-center gap-3">
                    ${puzzleOptions.map(char => `
                      <button onclick="VocabSuite.checkPuzzleLetter('${unitId}', '${char}', '${correctChar}', '${currentWord.french.replace(/'/g, "\\'")}')" class="w-12 h-12 sm:w-14 sm:h-14 rounded-2xl bg-white border-2 border-slate-200 hover:border-teal-500 hover:bg-teal-50 text-slate-800 text-lg sm:text-xl font-black font-french shadow-sm transition active:scale-95 flex items-center justify-center">
                        ${char}
                      </button>
                    `).join('')}
                  </div>

                  <div id="${unitId}-puzzle-feedback" class="hidden pt-2"></div>
                  <div class="flex items-center justify-between gap-2 pt-2">
                    <button onclick="VocabSuite.restartPuzzle('${unitId}')" class="px-3 py-2 rounded-xl text-slate-500 hover:text-slate-800 text-xs font-bold transition">
                      <i class="fa-solid fa-rotate-right ml-1"></i> إعادة من البداية
                    </button>
                    <button id="${unitId}-puzzle-next-btn" onclick="VocabSuite.nextPuzzleQuestion('${unitId}')" class="hidden px-5 py-2.5 rounded-2xl bg-teal-600 hover:bg-teal-700 text-white text-xs font-bold shadow-md transition flex items-center gap-1.5">
                      <span>الكلمة التالية</span> <i class="fa-solid fa-arrow-left"></i>
                    </button>
                  </div>
                </div>
              </div>
            `;
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

    // ==============================================================
    // 2. UNIVERSAL SITUATIONS SUITE (ALL UNITS)
    // Supports: u1 (p.15), u2 (p.52-54), u3 (p.63)
    // 3 Modes: 📝 حل المواقف الرسمية, 🎯 اختبار المواقف التفاعلي, 🔑 القاعدة الذهبية
    // ==============================================================
    const SituationsSuite = window.SituationsSuite = {
      state: {},

      ensureState(unitId) {
        if (!this.state[unitId]) {
          this.state[unitId] = {
            mode: 'quiz', // default to interactive quiz
            score: 0,
            userAnswers: {},
            showExplanation: {}
          };
        }
        return this.state[unitId];
      },

      getItems(unitId) {
        const c = DATA.course;
        if (unitId === 'u1') return c.unit1Situations || [];
        if (unitId === 'u2') return c.unit2SituationsItems || [];
        if (unitId === 'u3') return c.unit3SituationsItems || [];
        return [];
      },

      setMode(unitId, mode) {
        playClickSound();
        const s = this.ensureState(unitId);
        s.mode = mode;
        this.render(unitId);
      },

      selectOption(unitId, qId, chosenKey, correctKey, explanation) {
        const s = this.ensureState(unitId);
        if (s.userAnswers[qId] !== undefined) return;
        s.userAnswers[qId] = chosenKey;
        s.showExplanation[qId] = true;

        const isCorrect = chosenKey === correctKey;
        if (isCorrect) {
          playSuccessSound();
          s.score += 5;
        } else {
          playErrorSound();
        }
        this.render(unitId);
      },

      resetQuiz(unitId) {
        playClickSound();
        const s = this.ensureState(unitId);
        s.score = 0;
        s.userAnswers = {};
        s.showExplanation = {};
        this.render(unitId);
      },

      render(unitId) {
        const s = this.ensureState(unitId);
        const items = this.getItems(unitId);
        const container = document.getElementById(unitId + '-interactive-situations-root');
        if (!container) return;

        const unitTitle = unitId === 'u1' ? 'مواقف الوحدة الأولى الرسمية (ص 15-16)' :
          (unitId === 'u2' ? 'مواقف الوجبات والمطعم الرسمية (ص 52-54)' :
          'المواقف الطبية والصحية الرسمية (ص 63)');

        const totalPoints = items.length * 5;

        let html = `
          <div class="space-y-4">
            <!-- Header & Mode Switcher -->
            <div class="bg-white rounded-3xl p-5 border border-slate-200 shadow-sm space-y-4">
              <div class="flex flex-wrap items-center justify-between gap-3 border-b border-slate-100 pb-3">
                <div>
                  <div class="flex items-center gap-2">
                    <span class="px-2.5 py-0.5 rounded-full bg-indigo-100 text-indigo-800 text-[11px] font-bold font-french">${items.length} مواقف رسمية</span>
                    <span class="px-2 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[11px] font-bold">اختبار + حلول + قواعد</span>
                  </div>
                  <h3 class="text-base sm:text-lg font-black text-slate-900 mt-1">${unitTitle}</h3>
                </div>
                <div class="flex items-center gap-2 bg-indigo-50 px-3.5 py-1.5 rounded-2xl border border-indigo-100">
                  <span class="text-xs font-bold text-indigo-800">مجموع درجاتك:</span>
                  <span class="text-base font-black text-indigo-700">${s.score} / ${totalPoints}</span>
                </div>
              </div>

              <!-- 3 Modes Tabs -->
              <div class="grid grid-cols-1 sm:grid-cols-3 gap-2">
                <button onclick="SituationsSuite.setMode('${unitId}', 'quiz')" class="p-2.5 rounded-2xl border text-xs font-bold transition flex items-center justify-center gap-1.5 ${s.mode === 'quiz' ? 'bg-indigo-700 text-white border-indigo-700 shadow-md' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-indigo-50'}">
                  <i class="fa-solid fa-bullseye"></i>
                  <span>🎯 الاختبار التفاعلي الذكي</span>
                </button>
                <button onclick="SituationsSuite.setMode('${unitId}', 'official')" class="p-2.5 rounded-2xl border text-xs font-bold transition flex items-center justify-center gap-1.5 ${s.mode === 'official' ? 'bg-indigo-700 text-white border-indigo-700 shadow-md' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-indigo-50'}">
                  <i class="fa-solid fa-book-open"></i>
                  <span>📝 حل الكتيّب الرسمي النموذجي</span>
                </button>
                <button onclick="SituationsSuite.setMode('${unitId}', 'rules')" class="p-2.5 rounded-2xl border text-xs font-bold transition flex items-center justify-center gap-1.5 ${s.mode === 'rules' ? 'bg-indigo-700 text-white border-indigo-700 shadow-md' : 'bg-slate-50 text-slate-700 border-slate-200 hover:bg-indigo-50'}">
                  <i class="fa-solid fa-key text-amber-300"></i>
                  <span>🔑 القاعدة الذهبية لحل المواقف</span>
                </button>
              </div>
            </div>
        `;

        // MODE 1: INTERACTIVE QUIZ
        if (s.mode === 'quiz') {
          html += `
            <div class="space-y-4">
              <div class="flex items-center justify-between text-xs text-slate-600 bg-white p-3 rounded-2xl border border-slate-200">
                <span>اختر الإجابة الصحيحة لكل موقف، وسيقوم النظام بالتصحيح الفوري وشرح القاعدة.</span>
                <button onclick="SituationsSuite.resetQuiz('${unitId}')" class="px-3 py-1 rounded-xl bg-slate-100 hover:bg-slate-200 text-slate-700 font-bold transition flex items-center gap-1">
                  <i class="fa-solid fa-rotate-right"></i> <span>إعادة الاختبار</span>
                </button>
              </div>

              <div class="space-y-4">
                ${items.map((it, idx) => {
                  const qId = it.id || (idx + 1);
                  const pFr = it.promptFr || it.situationFr || it.frenchPrompt || '';
                  const pAr = it.promptAr || it.situationAr || it.arabicPrompt || '';
                  const opts = it.options || [];
                  const cKey = (it.correctKey || it.correctOption || 'c').toString().toLowerCase().trim();
                  const exp = it.explanation || it.explanationAr || it.ruleTip || '';
                  const userChoice = s.userAnswers[qId];
                  const hasAnswered = userChoice !== undefined;
                  const isUserCorrect = hasAnswered && (userChoice.toLowerCase() === cKey);

                  return `
                    <div class="bg-white rounded-3xl p-5 border border-slate-200 shadow-sm space-y-3.5 transition">
                      <div class="flex items-center justify-between gap-2 border-b border-slate-100 pb-2.5">
                        <span class="px-2.5 py-0.5 rounded-full bg-indigo-50 text-indigo-700 font-bold text-xs">الموقف ${idx + 1}</span>
                        <button onclick="speakText('${pFr.replace(/'/g, "\\'")}')" class="px-3 py-1 rounded-full bg-slate-100 hover:bg-indigo-50 text-slate-700 hover:text-indigo-700 flex items-center gap-1.5 transition text-xs font-bold font-french">
                          <i class="fa-solid fa-volume-high"></i> <span>نطق الموقف</span>
                        </button>
                      </div>

                      <div class="space-y-1">
                        <h4 class="text-xs sm:text-sm font-black font-french text-slate-900 leading-snug" dir="ltr">${pFr}</h4>
                        <p class="text-xs text-slate-500 font-sans">${pAr}</p>
                      </div>

                      <!-- Options -->
                      <div class="space-y-2 pt-1" dir="ltr">
                        ${opts.map((opt, oIdx) => {
                          const oKey = (opt.key || String.fromCharCode(97 + oIdx)).toLowerCase();
                          const oFr = opt.french || opt.textFr || opt.frenchText || opt;
                          const oAr = opt.arabic || opt.textAr || opt.arabicText || '';
                          const isThisCorrect = oKey === cKey;
                          const isThisChosen = userChoice === oKey;

                          let btnCls = 'border-slate-200 bg-slate-50/70 hover:bg-indigo-50/60 hover:border-indigo-300 text-slate-800';
                          if (hasAnswered) {
                            if (isThisChosen && isThisCorrect) {
                              btnCls = 'border-emerald-500 bg-emerald-500 text-white shadow-md font-bold';
                            } else if (isThisChosen && !isThisCorrect) {
                              btnCls = 'border-rose-500 bg-rose-500 text-white shadow-md font-bold';
                            } else if (isThisCorrect) {
                              btnCls = 'border-emerald-400 bg-emerald-50 text-emerald-900 font-bold';
                            } else {
                              btnCls = 'border-slate-100 bg-slate-50 text-slate-400 opacity-60';
                            }
                          }

                          return `
                            <button ${hasAnswered ? 'disabled' : ''} onclick="SituationsSuite.selectOption('${unitId}', ${qId}, '${oKey}', '${cKey}', '${exp.replace(/'/g, "\\'")}')" class="w-full p-3 rounded-2xl border text-left text-xs transition flex items-start gap-2.5 ${btnCls}">
                              <span class="w-6 h-6 rounded-full ${isThisChosen && isThisCorrect ? 'bg-white text-emerald-700' : (isThisChosen && !isThisCorrect ? 'bg-white text-rose-700' : 'bg-slate-200 text-slate-700')} flex items-center justify-center font-bold font-french shrink-0 text-xs uppercase">${oKey}</span>
                              <div class="flex-1 space-y-0.5">
                                <p class="font-french font-bold">${oFr}</p>
                                ${oAr ? `<p class="text-[11px] ${isThisChosen ? 'text-white/90' : 'text-slate-500'} font-sans" dir="rtl">${oAr}</p>` : ''}
                              </div>
                            </button>
                          `;
                        }).join('')}
                      </div>

                      <!-- Feedback & Explanation -->
                      ${hasAnswered ? `
                        <div class="mt-3 p-3 rounded-2xl border text-xs leading-relaxed ${isUserCorrect ? 'bg-emerald-50 border-emerald-200 text-emerald-900' : 'bg-rose-50 border-rose-200 text-rose-900'}">
                          <div class="flex items-center gap-1.5 font-black mb-1">
                            <span>${isUserCorrect ? '✓ إجابة صحيحة وممتازة! (+5 نقاط) 🎉' : '❌ إجابة غير صحيحة، انتبه للتفسير: 💡'}</span>
                          </div>
                          ${exp ? `<p class="text-slate-700 mt-1 font-sans"><strong>الشرح:</strong> ${exp}</p>` : ''}
                        </div>
                      ` : ''}
                    </div>
                  `;
                }).join('')}
              </div>
            </div>
          `;
        }

        // MODE 2: OFFICIAL MODEL ANSWERS
        else if (s.mode === 'official') {
          html += `
            <div class="space-y-4">
              <div class="p-3 bg-amber-50 rounded-2xl border border-amber-200 text-xs text-amber-900 flex items-center gap-2">
                <i class="fa-solid fa-circle-check text-amber-600"></i>
                <span>جميع المواقف التالية مأخوذة حرفياً من كتيّب الأنشطة المعتمد مع حلها النموذجي.</span>
              </div>
              <div class="grid grid-cols-1 md:grid-cols-2 gap-3">
                ${items.map((it, idx) => {
                  const pFr = it.promptFr || it.situationFr || it.frenchPrompt || '';
                  const pAr = it.promptAr || it.situationAr || it.arabicPrompt || '';
                  const ansFr = it.correctAnswerFr || it.modelAnswerFr || (it.options ? (it.options.find(o => (o.key || '').toLowerCase() === (it.correctKey || '').toLowerCase())?.french || '') : '');
                  const ansAr = it.correctAnswerAr || it.modelAnswerAr || (it.options ? (it.options.find(o => (o.key || '').toLowerCase() === (it.correctKey || '').toLowerCase())?.arabic || '') : '');
                  const exp = it.explanation || it.explanationAr || '';

                  return `
                    <div class="bg-white rounded-3xl p-4 sm:p-5 border border-slate-200 shadow-sm space-y-3 flex flex-col justify-between">
                      <div class="space-y-2">
                        <div class="flex items-center justify-between border-b border-slate-100 pb-2">
                          <span class="px-2.5 py-0.5 rounded-full bg-indigo-50 text-indigo-700 font-bold text-xs">موقف ${idx + 1}</span>
                          <button onclick="speakText('${pFr.replace(/'/g, "\\'")}')" class="w-8 h-8 rounded-full bg-slate-100 hover:bg-indigo-50 text-slate-700 hover:text-indigo-700 flex items-center justify-center transition">
                            <i class="fa-solid fa-volume-high text-xs"></i>
                          </button>
                        </div>
                        <p class="font-french font-black text-xs sm:text-sm text-slate-900" dir="ltr">${pFr}</p>
                        <p class="text-xs text-slate-500">${pAr}</p>
                      </div>

                      <div class="p-3 bg-emerald-50 rounded-2xl border border-emerald-200 text-xs space-y-1">
                        <span class="font-bold text-emerald-800 block text-[11px]">➔ الحل النموذجي المعتمد:</span>
                        <p class="font-french font-black text-emerald-950" dir="ltr">${ansFr}</p>
                        <p class="text-emerald-800 font-sans text-[11px]">${ansAr}</p>
                        ${exp ? `<p class="text-slate-600 border-t border-emerald-200/60 pt-1 text-[11px]">💡 ${exp}</p>` : ''}
                      </div>
                    </div>
                  `;
                }).join('')}
              </div>
            </div>
          `;
        }

        // MODE 3: GOLDEN FORMULA
        else if (s.mode === 'rules') {
          html += `
            <div class="space-y-4">
              <!-- Universal Golden Rule -->
              <div class="bg-gradient-to-r from-amber-500 via-orange-500 to-amber-600 rounded-3xl p-5 sm:p-6 text-white shadow-md space-y-3">
                <div class="flex items-center gap-2">
                  <span class="w-8 h-8 rounded-full bg-white/20 flex items-center justify-center text-amber-200 text-base">🔑</span>
                  <h4 class="text-base sm:text-lg font-black">القاعدة الذهبية لحل سؤال المواقف (Formule Magique)</h4>
                </div>
                <p class="text-xs sm:text-sm text-amber-100 leading-relaxed font-medium">
                  انتبه دائماً لبداية الجملة ونهايتها، فالبداية تحدد من يسأل والنهاية تحدد المطلوب (سؤال أم إجابة):
                </p>
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-2 pt-2 text-xs">
                  <div class="p-3 bg-white/15 rounded-2xl backdrop-blur-sm border border-white/20 space-y-0.5">
                    <span class="font-bold text-amber-200 block">Tu demandes ... tu dis</span>
                    <span class="text-white font-medium">أنت تسأل ... أنت تقول ➔ المطلوب <strong>سؤال ❓</strong></span>
                  </div>
                  <div class="p-3 bg-white/15 rounded-2xl backdrop-blur-sm border border-white/20 space-y-0.5">
                    <span class="font-bold text-emerald-200 block">Tu demandes ... il dit</span>
                    <span class="text-white font-medium">أنت تسأل ... هو يقول ➔ المطلوب <strong>إجابة 💬</strong></span>
                  </div>
                  <div class="p-3 bg-white/15 rounded-2xl backdrop-blur-sm border border-white/20 space-y-0.5">
                    <span class="font-bold text-blue-200 block">Ton ami te demande ... tu dis</span>
                    <span class="text-white font-medium">صديقك يسألك ... أنت تقول ➔ المطلوب <strong>إجابة 💬</strong></span>
                  </div>
                  <div class="p-3 bg-white/15 rounded-2xl backdrop-blur-sm border border-white/20 space-y-0.5">
                    <span class="font-bold text-purple-200 block">Tu t'excuses ... tu dis</span>
                    <span class="text-white font-medium">أنت تعتذر ... أنت تقول ➔ <strong>(Pardon ! / Désolé !) 🙏</strong></span>
                  </div>
                </div>
              </div>

              ${unitId === 'u3' ? `
                <!-- Medical Roles for Unit 3 -->
                <div class="bg-white rounded-3xl p-5 border border-slate-200 shadow-sm space-y-3">
                  <h4 class="text-sm font-black text-teal-800 flex items-center gap-2">
                    <i class="fa-solid fa-user-doctor text-teal-600"></i>
                    <span>مهام الفريق الطبي وسيارات الإسعاف (ص 59-63)</span>
                  </h4>
                  <div class="space-y-2 text-xs">
                    <div class="p-3 rounded-2xl bg-slate-50 border border-slate-200 space-y-1">
                      <strong class="text-teal-900 font-french">L'ambulancier (المسعف) :</strong>
                      <p class="text-slate-700 font-french" dir="ltr">Il transporte les personnes malades ou blessées à l'hôpital.</p>
                      <p class="text-slate-500">ينقل الأشخاص المرضى أو المصابين إلى المستشفى.</p>
                    </div>
                    <div class="p-3 rounded-2xl bg-slate-50 border border-slate-200 space-y-1">
                      <strong class="text-teal-900 font-french">Le médecin (الطبيب) :</strong>
                      <p class="text-slate-700 font-french" dir="ltr">Il examine les malades, fait des ordonnances et donne des conseils.</p>
                      <p class="text-slate-500">يفحص المرضى، يكتب الروشتات ويقدم النصائح الطبية.</p>
                    </div>
                    <div class="p-3 rounded-2xl bg-slate-50 border border-slate-200 space-y-1">
                      <strong class="text-teal-900 font-french">L'infirmière (الممرضة) :</strong>
                      <p class="text-slate-700 font-french" dir="ltr">Elle aide le médecin, donne les piqûres et prend la température.</p>
                      <p class="text-slate-500">تساعد الطبيب، تعطي الحقن وتقيس درجة الحرارة.</p>
                    </div>
                  </div>
                </div>
              ` : ''}
            </div>
          `;
        }

        html += '</div>';
        container.innerHTML = html;
      }
    };

    // ==============================================================
    // 3. INTRODUCTORY QUESTIONS SUITE (ص 4-6)
    // 5 Categories: All, Présent, Interrogatifs, Prépositions, Négation, Examen
    // ==============================================================
    const IntroSuite = window.IntroSuite = {
      activeCategory: 'ALL',
      score: 0,
      userAnswers: {},
      translated: {},

      getAllQuestions() {
        const c = DATA.course;
        return [
          ...(c.revisionEx1MetsAuPresent || []).map(q => ({ ...q, category: 'PRESENT', catLabel: 'المضارع (ص 4)' })),
          ...(c.revisionEx3Interrogatifs || []).map(q => ({ ...q, category: 'INTERROGATIF', catLabel: 'الاستفهام (ص 4)' })),
          ...(c.revisionEx4ArticlesLieux || []).map(q => ({ ...q, category: 'LIEUX', catLabel: 'حروف الجر (ص 5)' })),
          ...(c.revisionEx5Negation || []).map(q => ({ ...q, category: 'NEGATION', catLabel: 'النفي (ص 5)' })),
          ...(c.revisionEx2ChoisisBonneReponse || []).map(q => ({ ...q, category: 'EXAMEN', catLabel: 'شامل (ص 6)' }))
        ];
      },

      setCategory(cat) {
        playClickSound();
        this.activeCategory = cat;
        renderIntroScreen();
      },

      toggleTranslate(qId) {
        this.translated[qId] = !this.translated[qId];
        renderIntroScreen();
      },

      answerQuestion(qId, oIdx, isCorrect, explanation) {
        if (this.userAnswers[qId] !== undefined) return;
        this.userAnswers[qId] = oIdx;
        if (isCorrect) {
          playSuccessSound();
          this.score += 5;
        } else {
          playErrorSound();
        }
        renderIntroScreen();
      },

      resetAll() {
        playClickSound();
        this.score = 0;
        this.userAnswers = {};
        this.translated = {};
        renderIntroScreen();
      }
    };

    function renderIntroScreen() {
      const container = document.getElementById('screen-intro');
      if (!container) return;

      const allQuestions = IntroSuite.getAllQuestions();
      const cat = IntroSuite.activeCategory;
      const filtered = cat === 'ALL' ? allQuestions : allQuestions.filter(q => q.category === cat);

      const catCounts = {
        ALL: allQuestions.length,
        PRESENT: allQuestions.filter(q => q.category === 'PRESENT').length,
        INTERROGATIF: allQuestions.filter(q => q.category === 'INTERROGATIF').length,
        LIEUX: allQuestions.filter(q => q.category === 'LIEUX').length,
        NEGATION: allQuestions.filter(q => q.category === 'NEGATION').length,
        EXAMEN: allQuestions.filter(q => q.category === 'EXAMEN').length
      };

      container.innerHTML = `
        <!-- Intro Screen Banner -->
        <div class="rounded-3xl bg-gradient-to-r from-amber-600 via-orange-600 to-amber-700 text-white p-5 sm:p-6 shadow-lg border border-amber-500/30">
          <div class="flex items-center justify-between gap-3 mb-2">
            <span class="px-3 py-1 rounded-full bg-white/20 text-xs font-bold font-french">Bienvenu 2 • Pages 4-6</span>
            <div class="flex items-center gap-2 bg-black/20 px-3 py-1.5 rounded-xl border border-white/20 text-xs font-bold">
              <span>النقاط:</span>
              <span class="font-black text-amber-300 text-sm">${IntroSuite.score}</span>
            </div>
          </div>
          <h2 class="text-xl sm:text-2xl font-black mb-1">الأسئلة التمهيدية ومراجعة أساسيات المنهج (ص 4-6)</h2>
          <p class="text-xs sm:text-sm text-amber-100 font-medium">تدريبات تفاعلية شاملة مطابقة لكتاب Bienvenu 2 لمراجعة المضارع، أدوات الاستفهام، حروف جر الدول والأماكن، والنفي البسيط قبل البدء في الوحدات.</p>
        </div>

        <!-- Filter Chips -->
        <div class="bg-white p-2 rounded-2xl border border-slate-200 shadow-sm flex overflow-x-auto custom-scrollbar gap-1 text-xs">
          <button onclick="IntroSuite.setCategory('ALL')" class="px-3.5 py-1.5 rounded-xl font-bold whitespace-nowrap transition ${cat === 'ALL' ? 'bg-amber-600 text-white shadow-sm' : 'text-slate-600 hover:bg-slate-100'}">
            الكل (${catCounts.ALL})
          </button>
          <button onclick="IntroSuite.setCategory('PRESENT')" class="px-3.5 py-1.5 rounded-xl font-bold whitespace-nowrap transition ${cat === 'PRESENT' ? 'bg-amber-600 text-white shadow-sm' : 'text-slate-600 hover:bg-slate-100'}">
            1. المضارع (${catCounts.PRESENT})
          </button>
          <button onclick="IntroSuite.setCategory('INTERROGATIF')" class="px-3.5 py-1.5 rounded-xl font-bold whitespace-nowrap transition ${cat === 'INTERROGATIF' ? 'bg-amber-600 text-white shadow-sm' : 'text-slate-600 hover:bg-slate-100'}">
            2. الاستفهام (${catCounts.INTERROGATIF})
          </button>
          <button onclick="IntroSuite.setCategory('LIEUX')" class="px-3.5 py-1.5 rounded-xl font-bold whitespace-nowrap transition ${cat === 'LIEUX' ? 'bg-amber-600 text-white shadow-sm' : 'text-slate-600 hover:bg-slate-100'}">
            3. حروف الجر (${catCounts.LIEUX})
          </button>
          <button onclick="IntroSuite.setCategory('NEGATION')" class="px-3.5 py-1.5 rounded-xl font-bold whitespace-nowrap transition ${cat === 'NEGATION' ? 'bg-amber-600 text-white shadow-sm' : 'text-slate-600 hover:bg-slate-100'}">
            4. النفي (${catCounts.NEGATION})
          </button>
          <button onclick="IntroSuite.setCategory('EXAMEN')" class="px-3.5 py-1.5 rounded-xl font-bold whitespace-nowrap transition ${cat === 'EXAMEN' ? 'bg-amber-600 text-white shadow-sm' : 'text-slate-600 hover:bg-slate-100'}">
            5. شامل ص 6 (${catCounts.EXAMEN})
          </button>
        </div>

        <!-- Questions List -->
        <div class="space-y-3.5">
          ${filtered.map((q, idx) => {
            const qId = q.id || ('intro_' + idx);
            const userChoice = IntroSuite.userAnswers[qId];
            const hasAnswered = userChoice !== undefined;
            const isCorrect = hasAnswered && (userChoice === q.correctIndex);
            const isTrans = !!IntroSuite.translated[qId];
            const qFr = q.question || q.questionFr || '';
            const qAr = q.arabicTranslation || q.questionAr || '';
            const exp = q.explanation || q.explanationAr || '';

            return `
              <div class="bg-white rounded-3xl p-5 border border-slate-200 shadow-sm space-y-3 transition">
                <div class="flex items-center justify-between border-b border-slate-100 pb-2.5">
                  <div class="flex items-center gap-2">
                    <span class="px-2.5 py-0.5 rounded-full bg-amber-50 text-amber-800 font-bold text-xs">${q.catLabel || 'تمهيدي'}</span>
                    <span class="text-[11px] text-slate-400 font-french">${q.pageReference || 'p. 4-6'}</span>
                  </div>
                  <div class="flex items-center gap-1.5">
                    <button onclick="IntroSuite.toggleTranslate('${qId}')" class="px-2.5 py-1 rounded-full text-xs font-bold ${isTrans ? 'bg-indigo-100 text-indigo-800' : 'bg-slate-100 text-slate-600 hover:bg-slate-200'} transition">
                      ${isTrans ? 'إخفاء الترجمة' : 'ترجمة 👁️'}
                    </button>
                    <button onclick="speakText('${qFr.replace(/'/g, "\\'")}')" class="w-8 h-8 rounded-full bg-slate-100 hover:bg-amber-50 text-slate-700 hover:text-amber-700 flex items-center justify-center transition">
                      <i class="fa-solid fa-volume-high text-xs"></i>
                    </button>
                  </div>
                </div>

                <div class="space-y-1">
                  <h4 class="text-xs sm:text-sm font-black font-french text-slate-900" dir="ltr">${qFr}</h4>
                  ${isTrans && qAr ? `<p class="text-xs text-indigo-700 font-sans font-medium">${qAr}</p>` : ''}
                </div>

                <!-- Option Buttons -->
                <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-2" dir="ltr">
                  ${(q.options || []).map((opt, oIdx) => {
                    const isThisCorrect = oIdx === q.correctIndex;
                    const isThisChosen = userChoice === oIdx;

                    let btnCls = 'border-slate-200 bg-slate-50/70 hover:bg-amber-50/60 hover:border-amber-300 text-slate-800';
                    if (hasAnswered) {
                      if (isThisChosen && isThisCorrect) {
                        btnCls = 'border-emerald-500 bg-emerald-500 text-white font-bold shadow-md';
                      } else if (isThisChosen && !isThisCorrect) {
                        btnCls = 'border-rose-500 bg-rose-500 text-white font-bold shadow-md';
                      } else if (isThisCorrect) {
                        btnCls = 'border-emerald-400 bg-emerald-50 text-emerald-900 font-bold';
                      } else {
                        btnCls = 'border-slate-100 bg-slate-50 text-slate-400 opacity-60';
                      }
                    }

                    return `
                      <button ${hasAnswered ? 'disabled' : ''} onclick="IntroSuite.answerQuestion('${qId}', ${oIdx}, ${isThisCorrect}, '${exp.replace(/'/g, "\\'")}')" class="p-3 rounded-2xl border text-left text-xs font-french font-semibold transition ${btnCls}">
                        ${opt}
                      </button>
                    `;
                  }).join('')}
                </div>

                <!-- Feedback -->
                ${hasAnswered ? `
                  <div class="p-3 rounded-2xl border text-xs leading-relaxed ${isCorrect ? 'bg-emerald-50 border-emerald-200 text-emerald-900' : 'bg-rose-50 border-rose-200 text-rose-900'}">
                    <strong>${isCorrect ? '✓ إجابة صحيحة وممتازة! (+5 نقاط) 🎉' : '❌ إجابة غير صحيحة 💡'}</strong>
                    ${exp ? `<p class="text-slate-700 mt-1 font-sans"><strong>تفسير القاعدة:</strong> ${exp}</p>` : ''}
                  </div>
                ` : ''}
              </div>
            `;
          }).join('')}
        </div>
      `;
    }


    // Helper: render pronoun 15 booklet exercises interactively
    function renderPronounBookletExercises(exercises) {
      if (!exercises || !exercises.length) return '';
      return exercises.map((ex, idx) => `
        <div class="quiz-card p-4 rounded-2xl border border-slate-200 bg-white space-y-3 shadow-sm">
          <div class="flex items-center justify-between border-b border-slate-100 pb-2">
            <div class="flex items-center gap-2">
              <span class="w-6 h-6 rounded-full bg-indigo-100 text-indigo-800 text-xs font-black flex items-center justify-center font-french">${ex.number || (idx + 1)}</span>
              <span class="px-2 py-0.5 rounded-full bg-purple-50 text-purple-700 text-[10px] font-bold border border-purple-200">${ex.ruleTag || 'Pronoms'}</span>
            </div>
            <span class="text-[11px] text-slate-400 font-french font-bold">${ex.pageReference || 'p. 23'}</span>
          </div>

          <div class="space-y-1">
            <p class="text-xs sm:text-sm font-black text-slate-900 font-french" dir="ltr">
              ${ex.fullSentenceFr.replace(ex.underlinedPart, '<u class="text-indigo-600 decoration-indigo-400 decoration-2 font-black">' + ex.underlinedPart + '</u>')}
            </p>
            <p class="text-xs text-slate-500 font-sans">${ex.sentenceAr || ''}</p>
          </div>

          <p class="text-[11px] text-indigo-900 font-bold">اختر الضمير المناسب لاستبدال الجزء المحدد بخط:</p>

          <!-- Options -->
          <div class="grid grid-cols-3 gap-2" dir="ltr">
            ${(ex.options || []).map((opt) => `
              <button onclick="checkPronounExercise(this, ${opt.toLowerCase() === (ex.targetPronoun || '').toLowerCase()}, '${(ex.transformedSentenceFr || '').replace(/'/g, "\\'")}', '${(ex.explanationAr || '').replace(/'/g, "\\'")}')" class="p-2.5 rounded-xl border border-slate-200 bg-slate-50 hover:bg-indigo-50 hover:border-indigo-300 text-xs font-french font-bold text-slate-800 text-center transition">
                ${opt}
              </button>
            `).join('')}
          </div>

          <div class="pronoun-feedback hidden text-xs font-bold mt-2"></div>
        </div>
      `).join('');
    }

    function checkPronounExercise(btn, isCorrect, transformedSentence, explanation) {
      const parent = btn.closest('.quiz-card');
      const allButtons = parent.querySelectorAll('button');
      const feedback = parent.querySelector('.pronoun-feedback');
      allButtons.forEach(b => b.disabled = true);

      if (isCorrect) {
        btn.classList.add('bg-emerald-500', 'text-white', 'border-emerald-600', 'shadow-md');
        playSuccessSound();
        if (feedback) {
          feedback.innerHTML = '<div class="p-3 rounded-xl bg-emerald-50 text-emerald-900 border border-emerald-200 space-y-1 font-sans"><div>✓ أحسنت! الإجابة صحيحة.</div><div class="font-french font-black text-indigo-900" dir="ltr">➔ ' + transformedSentence + '</div><div class="text-[11px] text-slate-600 mt-0.5">💡 ' + explanation + '</div></div>';
          feedback.classList.remove('hidden');
        }
      } else {
        btn.classList.add('bg-rose-500', 'text-white', 'border-rose-600');
        playErrorSound();
        if (feedback) {
          feedback.innerHTML = '<div class="p-3 rounded-xl bg-rose-50 text-rose-900 border border-rose-200 space-y-1 font-sans"><div>❌ إجابة خاطئة. الجملة الصحيحة هي:</div><div class="font-french font-black text-indigo-900" dir="ltr">➔ ' + transformedSentence + '</div><div class="text-[11px] text-slate-600 mt-0.5">💡 ' + explanation + '</div></div>';
          feedback.classList.remove('hidden');
        }
      }
    }

    // ==============================================================
    // 4. RENDER UNIT 1 SCREEN (10 FULL INTERACTIVE TABS)
    // ==============================================================
    function renderUnit1Screen() {
      const c = DATA.course;
      const b = DATA.bookletLieux || {};
      const container = document.getElementById('screen-unit1');
      if (!container) return;

      container.innerHTML = `
        <!-- Unit 1 Header -->
        <div class="rounded-3xl bg-gradient-to-r from-blue-700 via-indigo-700 to-blue-800 text-white p-5 sm:p-6 shadow-lg border border-blue-500/30">
          <div class="flex items-center justify-between gap-3 mb-2">
            <span class="px-3 py-1 rounded-full bg-white/20 text-xs font-bold font-french">Unité 1 • Pages 11-34</span>
            <button onclick="speakText('Unité un : Une fête, l invitation')" class="px-3 py-1 rounded-full bg-white/15 hover:bg-white/25 active:scale-95 text-xs font-bold font-french flex items-center gap-1.5 transition">
              <i class="fa-solid fa-volume-high"></i>
              <span>Écouter</span>
            </button>
          </div>
          <h2 class="text-xl sm:text-2xl font-black mb-1">Unité 1 : Une fête (حفلة عيد الميلاد والدعوة)</h2>
          <p class="text-xs sm:text-sm text-blue-100 font-medium">النص والوثائق، بنك الكلمات الكامل، المواقف، القواعد والضمائر، تكوين الجمل، تدريبات ص 26، وامتحان الوحدة.</p>
        </div>

        <!-- 10 Sub-tabs Navigation -->
        <div class="bg-white p-1.5 rounded-2xl border border-slate-200 shadow-sm flex overflow-x-auto custom-scrollbar gap-1 text-xs">
          <button onclick="switchUnit1SubTab('texte')" id="u1-tab-btn-texte" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition tab-active">
            <i class="fa-solid fa-file-lines ml-1"></i> النص وتدريبات الفهم
          </button>
          <button onclick="switchUnit1SubTab('vocab')" id="u1-tab-btn-vocab" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-book ml-1"></i> مفردات ص 14 (${c.unit1VocabWords.length})
          </button>
          <button onclick="switchUnit1SubTab('banque')" id="u1-tab-btn-banque" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-layer-group ml-1"></i> بنك الكلمات ص 27 (${c.unit1BanqueDesMots.length})
          </button>
          <button onclick="switchUnit1SubTab('situations')" id="u1-tab-btn-situations" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-comments ml-1"></i> المواقف الرسمية (${c.unit1Situations.length})
          </button>
          <button onclick="switchUnit1SubTab('grammar')" id="u1-tab-btn-grammar" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-ruler-combined ml-1"></i> القواعد والضمائر (34)
          </button>
          <button onclick="switchUnit1SubTab('phrases')" id="u1-tab-btn-phrases" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-puzzle-piece ml-1"></i> تكوين الجمل (ص 21)
          </button>
          <button onclick="switchUnit1SubTab('lieux')" id="u1-tab-btn-lieux" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-users ml-1"></i> الأماكن والشخصيات
          </button>
          <button onclick="switchUnit1SubTab('exercices26')" id="u1-tab-btn-exercices26" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-list-check ml-1"></i> تدريبات ص 26
          </button>
          <button onclick="switchUnit1SubTab('composition')" id="u1-tab-btn-composition" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-envelope-open-text ml-1"></i> دعوة الحفلة (ص 17)
          </button>
          <button onclick="switchUnit1SubTab('examen')" id="u1-tab-btn-examen" class="u1-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-trophy ml-1 text-amber-500"></i> امتحان الوحدة 1
          </button>
        </div>

        <!-- 1. TEXTE & COMPREHENSION -->
        <div id="u1-pane-texte" class="u1-pane space-y-4">
          <!-- Callout to Intro Questions -->
          <div class="bg-gradient-to-r from-indigo-50 to-purple-50 p-4 rounded-2xl border border-indigo-100 shadow-sm flex items-center justify-between gap-3">
            <div class="space-y-0.5">
              <span class="text-xs font-black text-indigo-950 flex items-center gap-1.5">
                <i class="fa-solid fa-lightbulb text-amber-500"></i>
                <span>💡 قبل البدء في دراسة الوحدة الأولى:</span>
              </span>
              <p class="text-xs text-slate-600">حل الأسئلة التمهيدية (ص 4-6) وراجع قواعد المضارع وحروف الجر والنفي.</p>
            </div>
            <button onclick="navigateTo('intro')" class="px-3.5 py-1.5 rounded-xl bg-indigo-700 hover:bg-indigo-800 text-white text-xs font-bold shadow-sm transition whitespace-nowrap">
              التمهيدي ➔
            </button>
          </div>

          <!-- Story Text Card -->
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="flex items-center justify-between border-b border-slate-100 pb-3">
              <div>
                <span class="px-2.5 py-0.5 rounded-full bg-blue-100 text-blue-800 text-[11px] font-bold font-french">Texte Officiel • Pages 11-13</span>
                <h3 class="text-base font-bold text-slate-900 mt-1 font-french">L'invitation chez Gamal</h3>
              </div>
              <button onclick="speakText('${c.unit1FullText.replace(/'/g, "\\'")}')" class="px-3.5 py-1.5 rounded-xl bg-blue-50 text-blue-700 hover:bg-blue-100 text-xs font-bold font-french flex items-center gap-1.5 transition">
                <i class="fa-solid fa-volume-high"></i> <span>استمع للنص</span>
              </button>
            </div>
            <div class="bg-slate-50 p-4 rounded-2xl text-xs sm:text-sm font-french leading-relaxed text-slate-800 border border-slate-200/80" dir="ltr">
              ${c.unit1FullText}
            </div>
            <div class="p-3 bg-blue-50/50 rounded-xl text-xs text-blue-950 leading-relaxed border border-blue-100">
              <strong>الترجمة المعتمدة :</strong> ${c.unit1FullTextArabic}
            </div>

            <!-- Dialogues -->
            <div class="space-y-2 pt-2">
              <span class="text-xs font-bold text-slate-700 block">💬 محادثات الوحدة مع النطق الفردي :</span>
              ${(c.unit1Dialogues || []).map(d => `
                <div class="p-3 rounded-2xl bg-slate-50 border border-slate-200/70 flex items-center justify-between gap-3 text-xs">
                  <div class="space-y-0.5">
                    <strong class="text-indigo-900 font-french block">${d.speaker} :</strong>
                    <p class="text-slate-800 font-french" dir="ltr">${d.text}</p>
                    <p class="text-slate-500 font-sans text-[11px]">${d.arabicNote}</p>
                  </div>
                  <button onclick="speakText('${d.text.replace(/'/g, "\\'")}')" class="w-8 h-8 rounded-full bg-white border border-slate-200 hover:bg-blue-50 text-blue-700 flex items-center justify-center shrink-0 transition">
                    <i class="fa-solid fa-volume-high text-xs"></i>
                  </button>
                </div>
              `).join('')}
            </div>
          </div>

          <!-- Interactive Comprehension Quizzes -->
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <h3 class="text-base font-black text-slate-900 flex items-center gap-2 border-b border-slate-100 pb-3">
              <i class="fa-solid fa-clipboard-check text-blue-600"></i>
              <span>تدريبات الفهم والاستيعاب التفاعلية (ص 12-13)</span>
            </h3>

            <!-- Ex 2: Reponds aux questions -->
            <div class="space-y-2">
              <span class="text-xs font-bold text-blue-900 block font-french">2. Réponds aux questions d'après le texte :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit1Ex2RepondsAuxQuestions)}</div>
            </div>

            <!-- Ex 3: Vrai ou Faux -->
            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-blue-900 block font-french">3. Mets (Vrai) ou (Faux) d'après le texte :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit1Ex3VraiOuFaux)}</div>
            </div>

            <!-- Ex 4: Associe -->
            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-blue-900 block font-french">4. Associe (A) avec (B) :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit1Ex4Associe)}</div>
            </div>

            <!-- Ex 5: Complete -->
            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-blue-900 block font-french">5. Complète avec les mots du texte :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit1Ex5Complete)}</div>
            </div>
          </div>
        </div>

        <!-- 2. VOCABULARY (p.14) -->
        <div id="u1-pane-vocab" class="u1-pane hidden space-y-4">
          <div id="u1-interactive-vocab-root"></div>
        </div>

        <!-- 3. BANQUE DES MOTS (p.27) -->
        <div id="u1-pane-banque" class="u1-pane hidden space-y-4">
          <div id="u1_banque-interactive-vocab-root"></div>
        </div>

        <!-- 4. SITUATIONS (p.15-16) -->
        <div id="u1-pane-situations" class="u1-pane hidden space-y-4">
          <div id="u1-interactive-situations-root"></div>
        </div>

        <!-- 5. GRAMMAR (p.18-20 & Pronouns Booklet) -->
        <div id="u1-pane-grammar" class="u1-pane hidden space-y-5">
          <!-- 1. Possessive Adjectives -->
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="flex items-center justify-between border-b border-slate-100 pb-3">
              <div>
                <span class="px-2.5 py-0.5 rounded-full bg-indigo-100 text-indigo-800 text-[11px] font-bold font-french">Grammaire • Page 17</span>
                <h3 class="text-base font-black text-indigo-950 mt-1">صفات الملكية (Les adjectifs possessifs)</h3>
              </div>
            </div>
            <!-- Possessive Table -->
            <div class="overflow-x-auto">
              <table class="w-full text-xs text-center border-collapse">
                <thead>
                  <tr class="bg-indigo-50 text-indigo-900 font-bold border-b border-indigo-200">
                    <th class="p-2">المالك (Sujet)</th>
                    <th class="p-2">مفرد مذكر (Masculin)</th>
                    <th class="p-2">مفرد مؤنث (Féminin)</th>
                    <th class="p-2">جمع بنوعيه (Pluriel)</th>
                  </tr>
                </thead>
                <tbody class="divide-y divide-slate-100 font-french font-semibold">
                  <tr><td class="p-2 font-bold font-sans">Je (أنا)</td><td class="p-2 text-indigo-700">mon</td><td class="p-2 text-rose-700">ma / mon*</td><td class="p-2 text-emerald-700">mes</td></tr>
                  <tr><td class="p-2 font-bold font-sans">Tu (أنتَ / أنتِ)</td><td class="p-2 text-indigo-700">ton</td><td class="p-2 text-rose-700">ta / ton*</td><td class="p-2 text-emerald-700">tes</td></tr>
                  <tr><td class="p-2 font-bold font-sans">Il / Elle (هو / هي)</td><td class="p-2 text-indigo-700">son</td><td class="p-2 text-rose-700">sa / son*</td><td class="p-2 text-emerald-700">ses</td></tr>
                  <tr><td class="p-2 font-bold font-sans">Nous (نحن)</td><td class="p-2 text-indigo-700">notre</td><td class="p-2 text-rose-700">notre</td><td class="p-2 text-emerald-700">nos</td></tr>
                  <tr><td class="p-2 font-bold font-sans">Vous (أنتم / حضرتك)</td><td class="p-2 text-indigo-700">votre</td><td class="p-2 text-rose-700">votre</td><td class="p-2 text-emerald-700">vos</td></tr>
                  <tr><td class="p-2 font-bold font-sans">Ils / Elles (هم / هن)</td><td class="p-2 text-indigo-700">leur</td><td class="p-2 text-rose-700">leur</td><td class="p-2 text-emerald-700">leurs</td></tr>
                </tbody>
              </table>
            </div>
            <!-- Possessive Questions -->
            <div class="space-y-2 pt-2">
              <span class="text-xs font-bold text-slate-800 block">🎯 تدريب تفاعلي على صفات الملكية (9 أسئلة) :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit1PossessiveExercises)}</div>
            </div>
          </div>

          <!-- 2. Pronom On -->
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-purple-100 text-purple-800 text-[11px] font-bold font-french">Grammaire • Pages 18-19</span>
              <h3 class="text-base font-black text-purple-950 mt-1">الضمير النكرة (Le pronom indéfini « On »)</h3>
              <p class="text-xs text-slate-500 mt-1">يساوي (Nous) في المعنى، ويصرف الفعل معه مثل (Il / Elle) في المفرد دائماً.</p>
            </div>
            <div class="space-y-3">
              <div class="space-y-2">
                <span class="text-xs font-bold text-slate-800 block">أ) اختر الإجابة الصحيحة مع الضمير On :</span>
                <div class="space-y-2">${renderQuizQuestionsList(c.unit1PronomOnChoisisExercises)}</div>
              </div>
              <div class="space-y-2 pt-2 border-t border-slate-100">
                <span class="text-xs font-bold text-slate-800 block">ب) استبدل الضمير Nous بالضمير On :</span>
                <div class="space-y-2">${renderQuizQuestionsList(c.unit1PronomOnReplaceExercises)}</div>
              </div>
            </div>
          </div>

          <!-- 3. Booklet Pronouns 15 Exercises -->
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-emerald-100 text-emerald-800 text-[11px] font-bold font-french">Livret d'activités • Page 23</span>
              <h3 class="text-base font-black text-slate-900 mt-1">ضمائر المفعول المباشر وغير المباشر (C.O.D & C.O.I - 15 تمريناً رسمياً)</h3>
              <p class="text-xs text-slate-500 mt-1">تمارين كتيّب الأنشطة الرسمي كاملة مع التحديد الفوري للجملة الناتجة والتصحيح التفاعلي.</p>
            </div>
            <div class="space-y-3">
              ${renderPronounBookletExercises(c.bookletPronounsOfficial15Exercises)}
            </div>
          </div>
        </div>

        <!-- 6. FAIS DES PHRASES (p.21) -->
        <div id="u1-pane-phrases" class="u1-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[11px] font-bold font-french">Production • Page 21</span>
              <h3 class="text-base font-black text-slate-900 mt-1">تكوين الجمل (Fais des phrases avec les mots suivants)</h3>
              <p class="text-xs text-slate-500">4 تمارين رسمية لتكوين جمل فرنسية سليمة من كلمات كتيّب المعهد.</p>
            </div>
            <div class="space-y-3">
              ${(c.unit1FaisDesPhrasesList || []).map((item, idx) => `
                <div class="p-4 rounded-2xl bg-slate-50 border border-slate-200 space-y-2">
                  <div class="flex items-center justify-between">
                    <span class="px-2.5 py-0.5 rounded-full bg-amber-100 text-amber-900 font-bold text-xs">تمرين ${item.number || (idx + 1)}</span>
                    <button onclick="speakText('${item.modelSentenceFr.replace(/'/g, "\\'")}')" class="w-8 h-8 rounded-full bg-white border border-slate-200 hover:bg-amber-50 text-amber-800 flex items-center justify-center transition">
                      <i class="fa-solid fa-volume-high text-xs"></i>
                    </button>
                  </div>
                  <div>
                    <strong class="font-french text-sm font-black text-indigo-950 block" dir="ltr">${item.promptFr}</strong>
                    <p class="text-xs text-slate-500">${item.promptAr}</p>
                  </div>
                  <div class="p-3 bg-emerald-50 rounded-xl border border-emerald-200 text-xs space-y-1">
                    <span class="font-bold text-emerald-800 block text-[11px]">الجملة النموذجية المعتمدة:</span>
                    <p class="font-french font-black text-emerald-950 text-sm" dir="ltr">➔ ${item.modelSentenceFr}</p>
                    <p class="text-emerald-800 font-sans text-xs">${item.modelSentenceAr}</p>
                    <p class="text-slate-600 border-t border-emerald-200/60 pt-1 text-[11px]">💡 ${item.grammarTipAr}</p>
                  </div>
                </div>
              `).join('')}
            </div>
          </div>
        </div>

        <!-- 7. LIEUX & PERSONNAGES (p.22) -->
        <div id="u1-pane-lieux" class="u1-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-violet-100 text-violet-800 text-[11px] font-bold font-french">Vocabulaire • Page 22</span>
              <h3 class="text-base font-black text-slate-900 mt-1">الشخصيات والأماكن الرسمية للوحدة الأولى</h3>
            </div>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-3">
              ${(b.personnagesList || []).map(p => `
                <div class="p-3.5 rounded-2xl bg-slate-50 border border-slate-200 space-y-1 text-xs">
                  <strong class="text-indigo-900 font-french font-bold text-sm block">${p.nameFr} (${p.nameAr})</strong>
                  <p class="text-slate-700 font-french" dir="ltr">${p.roleFr}</p>
                  <p class="text-slate-500 font-sans">${p.roleAr}</p>
                </div>
              `).join('')}
            </div>
            <div class="grid grid-cols-1 md:grid-cols-2 gap-3 pt-2">
              ${(b.lieuxList || []).map(l => `
                <div class="p-3.5 rounded-2xl bg-indigo-50/50 border border-indigo-100 space-y-1 text-xs">
                  <strong class="text-indigo-950 font-french font-bold text-sm block">📍 ${l.nameFr} (${l.nameAr})</strong>
                  <p class="text-slate-700 font-french" dir="ltr">${l.descriptionFr}</p>
                  <p class="text-slate-500 font-sans">${l.descriptionAr}</p>
                </div>
              `).join('')}
            </div>
          </div>
        </div>

        <!-- 8. EXERCICES PAGE 26 -->
        <div id="u1-pane-exercices26" class="u1-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-blue-100 text-blue-800 text-[11px] font-bold font-french">Exercices • Page 26</span>
              <h3 class="text-base font-black text-slate-900 mt-1">تدريبات كتيّب الأنشطة المعتمد (ص 26)</h3>
            </div>

            <!-- 1. Où vas-tu pour... -->
            <div class="space-y-2">
              <span class="text-xs font-bold text-slate-800 block">1. Où vas-tu pour... ? (إلى أين تذهب لكي... ؟) :</span>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-2 text-xs">
                ${(b.ouVasTuItems || []).map(item => `
                  <div class="p-3 rounded-2xl bg-slate-50 border border-slate-200 space-y-1">
                    <p class="font-french font-bold text-slate-900" dir="ltr">Pour ${item.actionFr} ➔</p>
                    <p class="text-indigo-700 font-french font-black" dir="ltr">Je vais ${item.placeFr}</p>
                    <p class="text-slate-400 text-[11px]">${item.actionAr} ➔ ${item.placeAr}</p>
                  </div>
                `).join('')}
              </div>
            </div>

            <!-- 2. Qui parle ? -->
            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-slate-800 block">2. Qui parle ? (من المتحدث ؟) :</span>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-2 text-xs">
                ${(b.quiParleItems || []).map(item => `
                  <div class="p-3 rounded-2xl bg-purple-50/50 border border-purple-100 space-y-1">
                    <p class="font-french font-bold text-slate-900" dir="ltr">« ${item.quoteFr} »</p>
                    <p class="text-purple-800 font-french font-black" dir="ltr">➔ C'est : ${item.speakerFr}</p>
                    <p class="text-slate-500 text-[11px]">${item.quoteAr} (المتحدث: ${item.speakerAr})</p>
                  </div>
                `).join('')}
              </div>
            </div>

            <!-- 3. Qui fait ce travail ? -->
            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-slate-800 block">3. Qui fait ce travail ? (من يقوم بهذا العمل ؟) :</span>
              <div class="grid grid-cols-1 sm:grid-cols-2 gap-2 text-xs">
                ${(b.quiFaitCeTravailItems || []).map(item => `
                  <div class="p-3 rounded-2xl bg-emerald-50/50 border border-emerald-100 space-y-1">
                    <p class="font-french font-bold text-slate-900" dir="ltr">${item.taskFr}</p>
                    <p class="text-emerald-800 font-french font-black" dir="ltr">➔ C'est : ${item.workerFr}</p>
                    <p class="text-slate-500 text-[11px]">${item.taskAr} (القائم بالعمل: ${item.workerAr})</p>
                  </div>
                `).join('')}
              </div>
            </div>
          </div>
        </div>

        <!-- 9. COMPOSITION (p.17) -->
        <div id="u1-pane-composition" class="u1-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-blue-100 text-blue-800 text-[11px] font-bold font-french">Production écrite • Page 17</span>
              <h3 class="text-base font-black text-slate-900 mt-1">موضوع الإنشاء الرسمي: دعوة لحفلة عيد ميلاد</h3>
              <p class="text-xs text-slate-500 mt-1">اكتب خطاباً تدعو فيه صديقك لحفلة عيد ميلادك (المكان، الزمان، الأنشطة).</p>
            </div>
            <div class="p-4 rounded-2xl bg-slate-50 border border-slate-200 font-french text-xs sm:text-sm text-slate-800 leading-relaxed space-y-2" dir="ltr">
              <p class="text-right text-slate-500 font-sans">Le Caire, le 15 octobre 2026</p>
              <p>Cher ami Ali,</p>
              <p>Je t'invite à la fête de mon anniversaire vendredi prochain à 19 heures chez moi. Nous allons chanter, danser et écouter de la musique. Ma mère va préparer un grand gâteau.</p>
              <p>J'attends ta venue avec impatience !</p>
              <p class="font-bold">Ton ami, Jean</p>
            </div>
            <div class="p-3 bg-blue-50 rounded-xl text-xs text-blue-900 space-y-1">
              <span class="font-bold block">العناصر الأساسية المطلوبة في الامتحان:</span>
              <ul class="list-disc list-inside space-y-0.5 text-slate-700">
                <li>كتابة التاريخ والمكان في أعلى اليمين (Le Caire, le...)</li>
                <li>افتتاح الخطاب بـ (Cher ami / Chère amie)</li>
                <li>تحديد موعد الحفلة (vendredi prochain à 19 h) ومكانها (chez moi)</li>
                <li>ذكر أنشطة الحفلة (chanter, danser, manger du gâteau)</li>
                <li>الختام بـ (Ton ami / Ton amie) واسم الراسل.</li>
              </ul>
            </div>
          </div>
        </div>

        <!-- 10. EXAMEN UNITE 1 (p.29) -->
        <div id="u1-pane-examen" class="u1-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="flex items-center justify-between border-b border-slate-100 pb-3">
              <div>
                <span class="px-2.5 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[11px] font-bold font-french">Examen Unité 1 • Pages 29-33</span>
                <h3 class="text-base font-black text-slate-900 mt-1">الامتحان الشامل المعتمد للوحدة الأولى (20 درجة)</h3>
              </div>
              <span class="px-3 py-1 rounded-full bg-indigo-50 text-indigo-700 text-xs font-bold font-french">Complet 20/20</span>
            </div>
            <p class="text-xs text-slate-600">يتضمن امتحان الوحدة: وثيقة فهم (6 درجات)، مواقف (4 درجات)، قواعد (6 درجات)، وإنتاج لغوي (4 درجات).</p>
            <div class="space-y-3">
              <div class="p-4 bg-slate-50 rounded-2xl border border-slate-200 text-xs space-y-2">
                <span class="font-bold text-indigo-900 block font-french">I. Compréhension (6 Marks)</span>
                <p class="text-slate-800 font-french" dir="ltr">« Vendredi dernier, c'était l'anniversaire de Marc. Ses parents ont organisé une grande fête. Il a reçu beaucoup de cadeaux. »</p>
                <div class="space-y-2 pt-2">
                  ${renderQuizQuestionsList([
                    { question: "La fête a eu lieu quel jour ?", options: ["Vendredi dernier", "Samedi", "Dimanche"], correctIndex: 0, explanation: "النص يذكر بوضوح: Vendredi dernier." },
                    { question: "C'était l'anniversaire de qui ?", options: ["Marc", "Pierre", "Ali"], correctIndex: 0, explanation: "النص: c'était l'anniversaire de Marc." },
                    { question: "Marc a reçu :", options: ["Beaucoup de cadeaux", "Rien", "Un seul livre"], correctIndex: 0, explanation: "النص: Il a reçu beaucoup de cadeaux." }
                  ])}
                </div>
              </div>
            </div>
          </div>
        </div>
      `;

      // Render default sub-components
      VocabSuite.render('u1');
      VocabSuite.render('u1_banque');
      SituationsSuite.render('u1');
    }

    function switchUnit1SubTab(tabId) {
      playClickSound();
      document.querySelectorAll('.u1-pane').forEach(el => el.classList.add('hidden'));
      document.querySelectorAll('.u1-nav-btn').forEach(el => el.classList.remove('tab-active'));
      const p = document.getElementById('u1-pane-' + tabId);
      const b = document.getElementById('u1-tab-btn-' + tabId);
      if (p) p.classList.remove('hidden');
      if (b) b.classList.add('tab-active');
      if (tabId === 'vocab') VocabSuite.render('u1');
      if (tabId === 'banque') VocabSuite.render('u1_banque');
      if (tabId === 'situations') SituationsSuite.render('u1');
    }

    // ==============================================================
    // 5. RENDER UNIT 2 SCREEN (8 FULL INTERACTIVE TABS)
    // ==============================================================
    function renderUnit2Screen() {
      const c = DATA.course;
      const container = document.getElementById('screen-unit2');
      if (!container) return;

      container.innerHTML = `
        <!-- Unit 2 Header -->
        <div class="rounded-3xl bg-gradient-to-r from-amber-600 via-orange-600 to-amber-700 text-white p-5 sm:p-6 shadow-lg border border-amber-500/30">
          <div class="flex items-center justify-between gap-3 mb-2">
            <span class="px-3 py-1 rounded-full bg-white/20 text-xs font-bold font-french">Unité 2 • Pages 35-58</span>
            <button onclick="speakText('Unité deux : Les repas, au restaurant')" class="px-3 py-1 rounded-full bg-white/15 hover:bg-white/25 active:scale-95 text-xs font-bold font-french flex items-center gap-1.5 transition">
              <i class="fa-solid fa-volume-high"></i>
              <span>Écouter</span>
            </button>
          </div>
          <h2 class="text-xl sm:text-2xl font-black mb-1">Unité 2 : Les repas (الوجبات وفي المطعم)</h2>
          <p class="text-xs sm:text-sm text-amber-100 font-medium">النص والتمارين، الوجبات والأطعمة (28 صنفاً)، كويز الوجبات، أدوات التجزئة، الاستفهام، بنك الكلمات، ومواقف المطعم.</p>
        </div>

        <!-- 8 Sub-tabs Navigation -->
        <div class="bg-white p-1.5 rounded-2xl border border-slate-200 shadow-sm flex overflow-x-auto custom-scrollbar gap-1 text-xs">
          <button onclick="switchUnit2SubTab('texte')" id="u2-tab-btn-texte" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition tab-active">
            <i class="fa-solid fa-utensils ml-1"></i> النص وحوار المطعم
          </button>
          <button onclick="switchUnit2SubTab('repas')" id="u2-tab-btn-repas" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-bowl-food ml-1"></i> الوجبات والأطعمة (${c.unit2RepasItems.length})
          </button>
          <button onclick="switchUnit2SubTab('vocab')" id="u2-tab-btn-vocab" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-book ml-1"></i> المفردات ص 38 (${c.unit2VocabWords.length})
          </button>
          <button onclick="switchUnit2SubTab('banque')" id="u2-tab-btn-banque" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-layer-group ml-1"></i> بنك الكلمات ص 48-51 (${(c.unit2BanqueMots || []).length})
          </button>
          <button onclick="switchUnit2SubTab('partitifs')" id="u2-tab-btn-partitifs" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-wine-glass ml-1"></i> أدوات التجزئة (ص 43-45)
          </button>
          <button onclick="switchUnit2SubTab('interrogatifs')" id="u2-tab-btn-interrogatifs" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-circle-question ml-1"></i> أدوات الاستفهام (ص 46-47)
          </button>
          <button onclick="switchUnit2SubTab('situations')" id="u2-tab-btn-situations" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-comments ml-1"></i> مواقف المطعم (7)
          </button>
          <button onclick="switchUnit2SubTab('production')" id="u2-tab-btn-production" class="u2-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-pen-to-square ml-1"></i> الإنتاج والجمل (ص 40)
          </button>
        </div>

        <!-- 1. TEXTE & RESTAURANT DIALOGUES -->
        <div id="u2-pane-texte" class="u2-pane space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="flex items-center justify-between border-b border-slate-100 pb-3">
              <div>
                <span class="px-2.5 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[11px] font-bold font-french">Texte Officiel • Pages 35-37</span>
                <h3 class="text-base font-bold text-slate-900 mt-1 font-french">Le repas au restaurant</h3>
              </div>
              <button onclick="speakText('${c.unit2FullText.replace(/'/g, "\\'")}')" class="px-3.5 py-1.5 rounded-xl bg-amber-50 text-amber-700 hover:bg-amber-100 text-xs font-bold font-french flex items-center gap-1.5 transition">
                <i class="fa-solid fa-volume-high"></i> <span>استمع للنص</span>
              </button>
            </div>
            <div class="bg-slate-50 p-4 rounded-2xl text-xs sm:text-sm font-french leading-relaxed text-slate-800 border border-slate-200/80" dir="ltr">
              ${c.unit2FullText}
            </div>
            <div class="p-3 bg-amber-50/50 rounded-xl text-xs text-amber-950 leading-relaxed border border-amber-100">
              <strong>الترجمة المعتمدة :</strong> ${c.unit2FullTextArabic}
            </div>

            <!-- Dialogues -->
            <div class="space-y-2 pt-2">
              <span class="text-xs font-bold text-slate-700 block">💬 محادثات المطعم مع النادل (Garçon) :</span>
              ${(c.unit2Dialogues || []).map(d => `
                <div class="p-3 rounded-2xl bg-slate-50 border border-slate-200/70 flex items-center justify-between gap-3 text-xs">
                  <div class="space-y-0.5">
                    <strong class="text-amber-900 font-french block">${d.speaker} :</strong>
                    <p class="text-slate-800 font-french" dir="ltr">${d.text}</p>
                    <p class="text-slate-500 font-sans text-[11px]">${d.arabicNote}</p>
                  </div>
                  <button onclick="speakText('${d.text.replace(/'/g, "\\'")}')" class="w-8 h-8 rounded-full bg-white border border-slate-200 hover:bg-amber-50 text-amber-700 flex items-center justify-center shrink-0 transition">
                    <i class="fa-solid fa-volume-high text-xs"></i>
                  </button>
                </div>
              `).join('')}
            </div>
          </div>

          <!-- Interactive Comprehension Quizzes -->
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <h3 class="text-base font-black text-slate-900 flex items-center gap-2 border-b border-slate-100 pb-3">
              <i class="fa-solid fa-clipboard-check text-amber-600"></i>
              <span>تدريبات الفهم والاستيعاب التفاعلية (ص 37)</span>
            </h3>

            <div class="space-y-2">
              <span class="text-xs font-bold text-amber-900 block font-french">1. Choisis le bon groupe :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit2Ex1BonGroupe)}</div>
            </div>

            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-amber-900 block font-french">2. Mets (Vrai) ou (Faux) d'après le texte :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit2Ex2VraiOuFaux)}</div>
            </div>

            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-amber-900 block font-french">3. Réponds aux questions prises du texte :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit2Ex3RepondsPrisDuTexte)}</div>
            </div>

            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-amber-900 block font-french">4. Complète avec les mots du texte :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit2Ex4Complete)}</div>
            </div>
          </div>
        </div>

        <!-- 2. REPAS & ALIMENTS (p.41) -->
        <div id="u2-pane-repas" class="u2-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[11px] font-bold font-french">Page 41 • ${c.unit2RepasItems.length} صنفاً</span>
              <h3 class="text-base sm:text-lg font-black text-slate-900 mt-1">الوجبات اليومية والأطعمة (Les trois repas)</h3>
            </div>

            <!-- 3 Meals Guide -->
            <div class="grid grid-cols-1 sm:grid-cols-3 gap-3">
              ${(c.unit2RepasList || []).map(r => `
                <div class="p-4 rounded-2xl bg-amber-50/70 border border-amber-200 space-y-1 text-center">
                  <span class="text-2xl block">${r.icon}</span>
                  <strong class="font-french font-black text-amber-950 text-sm block">${r.nameFr}</strong>
                  <p class="text-xs text-amber-800 font-sans">${r.nameAr}</p>
                  <span class="text-[11px] text-slate-500 font-french font-semibold block pt-1">${r.timeFr}</span>
                </div>
              `).join('')}
            </div>

            <!-- Food Items Grid with Audio -->
            <div class="space-y-2 pt-2">
              <span class="text-xs font-bold text-slate-800 block">🍽️ قائمة الأطعمة والمشروبات بالفرنسية مع الصوت :</span>
              <div class="grid grid-cols-2 sm:grid-cols-4 gap-2 text-xs">
                ${(c.unit2RepasItems || []).map(item => `
                  <div class="p-2.5 rounded-xl bg-slate-50 border border-slate-200 hover:border-amber-300 flex items-center justify-between gap-1 transition">
                    <div class="min-w-0">
                      <span class="font-french font-bold text-slate-900 block truncate">${item.icon || '🍴'} ${item.french}</span>
                      <span class="text-slate-500 text-[10px] truncate block">${item.arabic}</span>
                    </div>
                    <button onclick="speakText('${item.french.replace(/'/g, "\\'")}')" class="w-7 h-7 rounded-full bg-white text-amber-700 hover:bg-amber-100 flex items-center justify-center shrink-0 shadow-sm transition">
                      <i class="fa-solid fa-volume-high text-[10px]"></i>
                    </button>
                  </div>
                `).join('')}
              </div>
            </div>

            <!-- Interactive Food Quiz -->
            <div class="space-y-3 pt-3 border-t border-slate-100">
              <span class="text-xs font-black text-amber-900 block">🎯 كويز الوجبات والأطعمة التفاعلي (10 أسئلة) :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit2RepasQuizList)}</div>
            </div>
          </div>
        </div>

        <!-- 3. VOCABULARY (p.38) -->
        <div id="u2-pane-vocab" class="u2-pane hidden space-y-4">
          <div id="u2-interactive-vocab-root"></div>
        </div>

        <!-- 4. BANQUE DES MOTS (p.48-51) -->
        <div id="u2-pane-banque" class="u2-pane hidden space-y-4">
          <div id="u2_banque-interactive-vocab-root"></div>
        </div>

        <!-- 5. PARTITIVES (p.43-45) -->
        <div id="u2-pane-partitifs" class="u2-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[11px] font-bold font-french">Grammaire • Pages 43-45</span>
              <h3 class="text-base font-black text-slate-900 mt-1">أدوات التجزئة والضمير (En)</h3>
              <p class="text-xs text-slate-500 mt-1">تستخدم أدوات التجزئة (du, de la, de l', des) للتعبير عن كمية غير محددة من الأطعمة والمشروبات وتتحول إلى (de) عند النفي والكميات.</p>
            </div>
            <!-- Rules Table -->
            <div class="grid grid-cols-2 sm:grid-cols-4 gap-2 text-xs text-center">
              <div class="p-3 bg-indigo-50 rounded-xl border border-indigo-100">
                <span class="font-bold text-indigo-900 font-french text-sm block">du</span>
                <span class="text-slate-600">مفرد مذكر يبدأ بساكن (du poisson, du thé)</span>
              </div>
              <div class="p-3 bg-rose-50 rounded-xl border border-rose-100">
                <span class="font-bold text-rose-900 font-french text-sm block">de la</span>
                <span class="text-slate-600">مفرد مؤنث يبدأ بساكن (de la viande, de la salade)</span>
              </div>
              <div class="p-3 bg-amber-50 rounded-xl border border-amber-100">
                <span class="font-bold text-amber-900 font-french text-sm block">de l'</span>
                <span class="text-slate-600">مفرد بنوعيه يبدأ بمتحرك (de l'eau)</span>
              </div>
              <div class="p-3 bg-emerald-50 rounded-xl border border-emerald-100">
                <span class="font-bold text-emerald-900 font-french text-sm block">des</span>
                <span class="text-slate-600">جمع بنوعيه (des fruits, des légumes)</span>
              </div>
            </div>
            <!-- Interactive Partitives Quiz -->
            <div class="space-y-3 pt-2">
              <span class="text-xs font-black text-amber-900 block">🎯 تمارين أدوات التجزئة التفاعلية (8 أسئلة) :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.partitiveQuestions)}</div>
            </div>
          </div>
        </div>

        <!-- 6. INTERROGATIFS (p.46-47) -->
        <div id="u2-pane-interrogatifs" class="u2-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-indigo-100 text-indigo-800 text-[11px] font-bold font-french">Grammaire • Pages 46-47</span>
              <h3 class="text-base font-black text-slate-900 mt-1">أدوات الاستفهام (Les mots interrogatifs)</h3>
            </div>
            <!-- Interactive Interrogatives Quiz -->
            <div class="space-y-3">
              <span class="text-xs font-black text-indigo-900 block">🎯 تمارين أدوات الاستفهام التفاعلية (10 أسئلة) :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.interrogativeQuestions)}</div>
            </div>
          </div>
        </div>

        <!-- 7. SITUATIONS (p.52-54) -->
        <div id="u2-pane-situations" class="u2-pane hidden space-y-4">
          <div id="u2-interactive-situations-root"></div>
        </div>

        <!-- 8. PRODUCTION & PHRASES (p.40) -->
        <div id="u2-pane-production" class="u2-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-amber-100 text-amber-800 text-[11px] font-bold font-french">Production • Page 40</span>
              <h3 class="text-base font-black text-slate-900 mt-1">الإنتاج اللغوي وتكوين الجمل في المطعم</h3>
            </div>

            <!-- Qui parle ? À qui ? Où ? -->
            <div class="space-y-2">
              <span class="text-xs font-bold text-slate-800 block">1. Qui parle ? À qui ? Où ? :</span>
              <div class="space-y-2">
                ${(c.unit2QuiParleItems || []).map((item, idx) => `
                  <div class="p-3.5 rounded-2xl bg-slate-50 border border-slate-200 text-xs space-y-1">
                    <p class="font-french font-black text-slate-900" dir="ltr">« ${item.quoteFr} »</p>
                    <p class="text-slate-500 font-sans">${item.quoteAr}</p>
                    <div class="flex items-center gap-2 pt-1 font-french text-indigo-700 text-[11px] font-bold">
                      <span>➔ Qui parle : ${item.speakerFr}</span> |
                      <span>À qui : ${item.toWhomFr}</span> |
                      <span>Où : ${item.placeFr}</span>
                    </div>
                  </div>
                `).join('')}
              </div>
            </div>

            <!-- Fais des phrases -->
            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-slate-800 block">2. Fais des phrases avec les mots suivants :</span>
              <div class="space-y-2">
                ${(c.unit2FaisDesPhrasesItems || []).map(item => `
                  <div class="p-3.5 rounded-2xl bg-amber-50/50 border border-amber-200 text-xs space-y-1">
                    <strong class="font-french font-black text-amber-950 block" dir="ltr">${item.wordsFr}</strong>
                    <p class="text-emerald-800 font-french font-bold" dir="ltr">➔ ${item.modelSentenceFr}</p>
                    <p class="text-slate-600 font-sans text-[11px]">${item.modelSentenceAr}</p>
                  </div>
                `).join('')}
              </div>
            </div>
          </div>
        </div>
      `;

      // Render default sub-components
      VocabSuite.render('u2');
      VocabSuite.render('u2_banque');
      SituationsSuite.render('u2');
    }

    function switchUnit2SubTab(tabId) {
      playClickSound();
      document.querySelectorAll('.u2-pane').forEach(el => el.classList.add('hidden'));
      document.querySelectorAll('.u2-nav-btn').forEach(el => el.classList.remove('tab-active'));
      const p = document.getElementById('u2-pane-' + tabId);
      const b = document.getElementById('u2-tab-btn-' + tabId);
      if (p) p.classList.remove('hidden');
      if (b) b.classList.add('tab-active');
      if (tabId === 'vocab') VocabSuite.render('u2');
      if (tabId === 'banque') VocabSuite.render('u2_banque');
      if (tabId === 'situations') SituationsSuite.render('u2');
    }

    // ==============================================================
    // 6. RENDER UNIT 3 SCREEN (5 FULL INTERACTIVE TABS)
    // ==============================================================
    function renderUnit3Screen() {
      const c = DATA.course;
      const container = document.getElementById('screen-unit3');
      if (!container) return;

      container.innerHTML = `
        <!-- Unit 3 Header -->
        <div class="rounded-3xl bg-gradient-to-r from-emerald-600 via-teal-600 to-emerald-700 text-white p-5 sm:p-6 shadow-lg border border-emerald-500/30">
          <div class="flex items-center justify-between gap-3 mb-2">
            <span class="px-3 py-1 rounded-full bg-white/20 text-xs font-bold font-french">Unité 3 • Pages 59-67</span>
            <button onclick="speakText('Unité trois : La santé, chez le médecin')" class="px-3 py-1 rounded-full bg-white/15 hover:bg-white/25 active:scale-95 text-xs font-bold font-french flex items-center gap-1.5 transition">
              <i class="fa-solid fa-volume-high"></i>
              <span>Écouter</span>
            </button>
          </div>
          <h2 class="text-xl sm:text-2xl font-black mb-1">Unité 3 : La santé (الصحة وزيارة الطبيب)</h2>
          <p class="text-xs sm:text-sm text-emerald-100 font-medium">نصوص ص 59 وص 60، محادثات العيادة، أجزاء الجسم والتعبير عن الألم، بنك الكلمات الطبي، والمواقف الطبية.</p>
        </div>

        <!-- 5 Sub-tabs Navigation -->
        <div class="bg-white p-1.5 rounded-2xl border border-slate-200 shadow-sm flex overflow-x-auto custom-scrollbar gap-1 text-xs">
          <button onclick="switchUnit3SubTab('texte')" id="u3-tab-btn-texte" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition tab-active">
            <i class="fa-solid fa-stethoscope ml-1"></i> النصوص والمحادثات
          </button>
          <button onclick="switchUnit3SubTab('banque')" id="u3-tab-btn-banque" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-book-medical ml-1"></i> بنك الكلمات الطبي ص 62 (${(c.unit3BanqueWords || []).length})
          </button>
          <button onclick="switchUnit3SubTab('situations')" id="u3-tab-btn-situations" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-comments ml-1"></i> المواقف الطبية (8)
          </button>
          <button onclick="switchUnit3SubTab('corps')" id="u3-tab-btn-corps" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-child ml-1"></i> أجزاء الجسم والألم (ص 64)
          </button>
          <button onclick="switchUnit3SubTab('hopital')" id="u3-tab-btn-hopital" class="u3-nav-btn flex-1 py-2 px-3 rounded-xl font-bold whitespace-nowrap transition text-slate-600 hover:bg-slate-100">
            <i class="fa-solid fa-hospital ml-1"></i> في المستشفى (ص 68)
          </button>
        </div>

        <!-- 1. TEXTE & COMPREHENSION -->
        <div id="u3-pane-texte" class="u3-pane space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="flex items-center justify-between border-b border-slate-100 pb-3">
              <div>
                <span class="px-2.5 py-0.5 rounded-full bg-emerald-100 text-emerald-800 text-[11px] font-bold font-french">Textes • Pages 59-60</span>
                <h3 class="text-base font-bold text-slate-900 mt-1 font-french">L'accident de Samir & Chez le médecin</h3>
              </div>
              <button onclick="speakText('${c.unit3Page59Text.replace(/'/g, "\\'")}')" class="px-3.5 py-1.5 rounded-xl bg-emerald-50 text-emerald-700 hover:bg-emerald-100 text-xs font-bold font-french flex items-center gap-1.5 transition">
                <i class="fa-solid fa-volume-high"></i> <span>استمع للنص</span>
              </button>
            </div>
            <div class="bg-slate-50 p-4 rounded-2xl text-xs sm:text-sm font-french leading-relaxed text-slate-800 border border-slate-200/80" dir="ltr">
              ${c.unit3Page59Text}
            </div>
            <div class="p-3 bg-emerald-50/50 rounded-xl text-xs text-emerald-950 leading-relaxed border border-emerald-100">
              <strong>الترجمة المعتمدة :</strong> ${c.unit3Page59TextArabic}
            </div>

            <!-- Page 60 Dialogues -->
            <div class="space-y-2 pt-2">
              <span class="text-xs font-bold text-slate-700 block">💬 محادثات العيادة مع الطبيب والممرضة (ص 60) :</span>
              ${(c.unit3Dialogues || []).map(d => `
                <div class="p-3 rounded-2xl bg-slate-50 border border-slate-200/70 flex items-center justify-between gap-3 text-xs">
                  <div class="space-y-0.5">
                    <strong class="text-teal-900 font-french block">${d.speaker} :</strong>
                    <p class="text-slate-800 font-french" dir="ltr">${d.text}</p>
                    <p class="text-slate-500 font-sans text-[11px]">${d.arabicNote}</p>
                  </div>
                  <button onclick="speakText('${d.text.replace(/'/g, "\\'")}')" class="w-8 h-8 rounded-full bg-white border border-slate-200 hover:bg-emerald-50 text-emerald-700 flex items-center justify-center shrink-0 transition">
                    <i class="fa-solid fa-volume-high text-xs"></i>
                  </button>
                </div>
              `).join('')}
            </div>
          </div>

          <!-- Interactive Comprehension Quizzes -->
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <h3 class="text-base font-black text-slate-900 flex items-center gap-2 border-b border-slate-100 pb-3">
              <i class="fa-solid fa-clipboard-check text-emerald-600"></i>
              <span>تدريبات الفهم والاستيعاب التفاعلية (ص 61)</span>
            </h3>

            <div class="space-y-2">
              <span class="text-xs font-bold text-emerald-900 block font-french">1. Mets (Vrai) ou (Faux) d'après le texte :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit3Ex1VraiOuFaux)}</div>
            </div>

            <div class="space-y-2 pt-2 border-t border-slate-100">
              <span class="text-xs font-bold text-emerald-900 block font-french">2. Réponds aux questions :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.unit3Ex2QuestionsReponses)}</div>
            </div>
          </div>
        </div>

        <!-- 2. BANQUE DES MOTS (p.62) -->
        <div id="u3-pane-banque" class="u3-pane hidden space-y-4">
          <div id="u3-interactive-vocab-root"></div>
        </div>

        <!-- 3. SITUATIONS (p.63) -->
        <div id="u3-pane-situations" class="u3-pane hidden space-y-4">
          <div id="u3-interactive-situations-root"></div>
        </div>

        <!-- 4. CORPS & DOULEUR (p.64-67) -->
        <div id="u3-pane-corps" class="u3-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-5">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-teal-100 text-teal-800 text-[11px] font-bold font-french">Le Corps & La Douleur • Pages 64-67</span>
              <h3 class="text-base font-black text-slate-900 mt-1">أعضاء جسم الإنسان وقاعدة التعبير عن الألم (Avoir mal à...)</h3>
            </div>
            <!-- Rule Card -->
            <div class="grid grid-cols-2 sm:grid-cols-4 gap-2 text-xs text-center">
              <div class="p-3 bg-indigo-50 rounded-xl border border-indigo-100">
                <span class="font-bold text-indigo-900 font-french text-sm block">au</span>
                <span class="text-slate-600">مفرد مذكر (au bras, au ventre, au pied)</span>
              </div>
              <div class="p-3 bg-rose-50 rounded-xl border border-rose-100">
                <span class="font-bold text-rose-900 font-french text-sm block">à la</span>
                <span class="text-slate-600">مفرد مؤنث (à la tête, à la jambe)</span>
              </div>
              <div class="p-3 bg-amber-50 rounded-xl border border-amber-100">
                <span class="font-bold text-amber-900 font-french text-sm block">à l'</span>
                <span class="text-slate-600">مفرد يبدأ بمتحرك (à l'oeil, à l'estomac)</span>
              </div>
              <div class="p-3 bg-emerald-50 rounded-xl border border-emerald-100">
                <span class="font-bold text-emerald-900 font-french text-sm block">aux</span>
                <span class="text-slate-600">جمع بنوعيه (aux yeux, aux dents)</span>
              </div>
            </div>
            <!-- Interactive Pain Quiz -->
            <div class="space-y-3 pt-2">
              <span class="text-xs font-black text-teal-900 block">🎯 تمارين التعبير عن الألم التفاعلية (8 أسئلة) :</span>
              <div class="space-y-2">${renderQuizQuestionsList(c.bodyPainQuestions)}</div>
            </div>
          </div>
        </div>

        <!-- 5. HOPITAL (p.68) -->
        <div id="u3-pane-hopital" class="u3-pane hidden space-y-4">
          <div class="bg-white rounded-3xl p-5 sm:p-6 border border-slate-200 shadow-sm space-y-4">
            <div class="border-b border-slate-100 pb-3">
              <span class="px-2.5 py-0.5 rounded-full bg-teal-100 text-teal-800 text-[11px] font-bold font-french">À l'hôpital • Page 68</span>
              <h3 class="text-base font-black text-slate-900 mt-1">في المستشفى ونماذج النصائح الطبية</h3>
            </div>
            <div class="space-y-2 text-xs">
              <div class="p-3.5 rounded-2xl bg-slate-50 border border-slate-200 space-y-1">
                <strong class="text-teal-900 font-bold block">💡 نصائح الطبيب للمريض (Conseils du médecin) :</strong>
                <ul class="list-disc list-inside space-y-1 text-slate-700 font-french" dir="ltr">
                  <li>« Prends tes médicaments à l'heure ! » (تناول أدويتك في موعدها)</li>
                  <li>« Repose-toi bien au lit ! » (استرح جيداً في السرير)</li>
                  <li>« Fais une radiographie ! » (قم بعمل أشعة)</li>
                  <li>« Ne mange pas d'aliments gras ! » (لا تأكل أطعمة دسمة)</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      `;

      // Render default sub-components
      VocabSuite.render('u3');
      SituationsSuite.render('u3');
    }

    function switchUnit3SubTab(tabId) {
      playClickSound();
      document.querySelectorAll('.u3-pane').forEach(el => el.classList.add('hidden'));
      document.querySelectorAll('.u3-nav-btn').forEach(el => el.classList.remove('tab-active'));
      const p = document.getElementById('u3-pane-' + tabId);
      const b = document.getElementById('u3-tab-btn-' + tabId);
      if (p) p.classList.remove('hidden');
      if (b) b.classList.add('tab-active');
      if (tabId === 'banque') VocabSuite.render('u3');
      if (tabId === 'situations') SituationsSuite.render('u3');
    }

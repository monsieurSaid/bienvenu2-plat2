
    // ==============================================================
    // INTERACTIVE VOCABULARY & TRAINING SUITE (MATCHES ANDROID APP)
    // 4 Modes: Study Cards, 3D Flip Cards, Translation Quiz, Missing Letters
    // ==============================================================

    const VocabSuite = {
      state: {
        u1: { mode: 'cards', cat: 'ALL', continuousPlaying: false, continuousIndex: -1, flipIndex: 0, isFlipped: false, mastered: new Set(), review: new Set(), quizIndex: 0, quizScore: 0, quizStreak: 0, quizAnswered: false, puzzleIndex: 0, puzzleScore: 0, puzzleAnswered: false },
        u2: { mode: 'cards', cat: 'ALL', continuousPlaying: false, continuousIndex: -1, flipIndex: 0, isFlipped: false, mastered: new Set(), review: new Set(), quizIndex: 0, quizScore: 0, quizStreak: 0, quizAnswered: false, puzzleIndex: 0, puzzleScore: 0, puzzleAnswered: false },
        u3: { mode: 'cards', cat: 'ALL', continuousPlaying: false, continuousIndex: -1, flipIndex: 0, isFlipped: false, mastered: new Set(), review: new Set(), quizIndex: 0, quizScore: 0, quizStreak: 0, quizAnswered: false, puzzleIndex: 0, puzzleScore: 0, puzzleAnswered: false }
      },
      continuousTimers: {},

      getWords(unitId) {
        const c = DATA.course;
        if (unitId === 'u1') {
          return c.unit1VocabWords || [];
        } else if (unitId === 'u2') {
          return c.unit2VocabWords || [];
        } else if (unitId === 'u3') {
          return c.unit3VocabWords || [];
        }
        return [];
      },

      getCategoryBadge(category) {
        switch (category) {
          case 'MASCULINE':
            return { label: 'مذكر ♂', cls: 'bg-indigo-50 text-indigo-700 border-indigo-200' };
          case 'FEMININE':
            return { label: 'مؤنث ♀', cls: 'bg-rose-50 text-rose-700 border-rose-200' };
          case 'VERB':
            return { label: 'أفعال ⚡', cls: 'bg-emerald-50 text-emerald-700 border-emerald-200' };
          case 'EXPRESSION':
            return { label: 'تعبيرات 💬', cls: 'bg-amber-50 text-amber-800 border-amber-200' };
          case 'PERSONNAGE':
            return { label: 'شخصيات 👥', cls: 'bg-violet-50 text-violet-700 border-violet-200' };
          case 'LIEU':
            return { label: 'أماكن 📍', cls: 'bg-teal-50 text-teal-700 border-teal-200' };
          case 'CORPS':
            return { label: 'أجزاء الجسم 🧍', cls: 'bg-teal-50 text-teal-700 border-teal-200' };
          default:
            return { label: 'مفردات', cls: 'bg-slate-100 text-slate-700 border-slate-200' };
        }
      },

      setMode(unitId, mode) {
        playClickSound();
        this.stopContinuousAudio(unitId);
        this.state[unitId].mode = mode;
        this.state[unitId].isFlipped = false;
        this.render(unitId);
      },

      setCategory(unitId, cat) {
        playClickSound();
        this.stopContinuousAudio(unitId);
        this.state[unitId].cat = cat;
        this.state[unitId].flipIndex = 0;
        this.state[unitId].isFlipped = false;
        this.render(unitId);
      },

      getFilteredWords(unitId) {
        const words = this.getWords(unitId);
        const cat = this.state[unitId].cat;
        if (cat === 'ALL') return words;
        return words.filter(w => w.category === cat);
      },

      // --- 1. CONTINUOUS AUDIO PLAYER ---
      toggleContinuousAudio(unitId) {
        const s = this.state[unitId];
        if (s.continuousPlaying) {
          this.stopContinuousAudio(unitId);
        } else {
          this.startContinuousAudio(unitId);
        }
      },

      startContinuousAudio(unitId) {
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        if (!words.length) return;

        s.continuousPlaying = true;
        s.continuousIndex = 0;
        this.updateAudioButtonUI(unitId);
        this.playNextContinuous(unitId);
      },

      stopContinuousAudio(unitId) {
        const s = this.state[unitId];
        if (!s) return;
        s.continuousPlaying = false;
        s.continuousIndex = -1;
        if (this.continuousTimers[unitId]) {
          clearTimeout(this.continuousTimers[unitId]);
          delete this.continuousTimers[unitId];
        }
        if (typeof window !== 'undefined' && window.speechSynthesis) {
          window.speechSynthesis.cancel();
        }
        this.updateAudioButtonUI(unitId);
        this.clearAudioHighlights(unitId);
      },

      playNextContinuous(unitId) {
        const s = this.state[unitId];
        if (!s.continuousPlaying) return;

        const words = this.getFilteredWords(unitId);
        if (s.continuousIndex >= words.length) {
          this.stopContinuousAudio(unitId);
          return;
        }

        const currentWord = words[s.continuousIndex];
        this.highlightActiveWord(unitId, s.continuousIndex);
        speakText(currentWord.french);

        this.continuousTimers[unitId] = setTimeout(() => {
          if (s.continuousPlaying) {
            s.continuousIndex++;
            this.playNextContinuous(unitId);
          }
        }, 2600);
      },

      updateAudioButtonUI(unitId) {
        const btn = document.getElementById(unitId + '-continuous-audio-btn');
        if (!btn) return;
        const s = this.state[unitId];
        if (s.continuousPlaying) {
          btn.innerHTML = '<i class="fa-solid fa-stop ml-1"></i> إيقاف التشغيل';
          btn.className = 'px-3 py-1.5 rounded-xl bg-rose-600 hover:bg-rose-700 text-white font-bold text-xs flex items-center gap-1.5 shadow-sm transition';
        } else {
          btn.innerHTML = '<i class="fa-solid fa-play ml-1"></i> استمع للكل بالتتابع';
          btn.className = 'px-3 py-1.5 rounded-xl bg-indigo-600 hover:bg-indigo-700 text-white font-bold text-xs flex items-center gap-1.5 shadow-sm transition';
        }
      },

      highlightActiveWord(unitId, index) {
        this.clearAudioHighlights(unitId);
        const el = document.getElementById(unitId + '-word-card-' + index);
        if (el) {
          el.classList.add('ring-4', 'ring-amber-400', 'bg-amber-50');
          el.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
        }
      },

      clearAudioHighlights(unitId) {
        document.querySelectorAll('.' + unitId + '-vocab-card').forEach(c => {
          c.classList.remove('ring-4', 'ring-amber-400', 'bg-amber-50');
        });
      },

      // --- 2. 3D FLIP CARDS ---
      flipCard(unitId) {
        playClickSound();
        const s = this.state[unitId];
        s.isFlipped = !s.isFlipped;
        const cardInner = document.getElementById(unitId + '-flip-card-inner');
        if (cardInner) {
          cardInner.classList.toggle('flipped', s.isFlipped);
        }
      },

      nextFlipCard(unitId) {
        playClickSound();
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        if (s.flipIndex < words.length - 1) {
          s.flipIndex++;
        } else {
          s.flipIndex = 0;
        }
        s.isFlipped = false;
        this.renderFlipCardContent(unitId);
      },

      prevFlipCard(unitId) {
        playClickSound();
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        if (s.flipIndex > 0) {
          s.flipIndex--;
        } else {
          s.flipIndex = words.length - 1;
        }
        s.isFlipped = false;
        this.renderFlipCardContent(unitId);
      },

      shuffleFlipCards(unitId) {
        playClickSound();
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        s.flipIndex = Math.floor(Math.random() * words.length);
        s.isFlipped = false;
        this.renderFlipCardContent(unitId);
      },

      markMastered(unitId) {
        playSuccessSound();
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        const w = words[s.flipIndex];
        if (w) {
          s.mastered.add(w.id || w.french);
          s.review.delete(w.id || w.french);
        }
        if (typeof confetti === 'function') confetti({ particleCount: 50, spread: 60 });
        this.nextFlipCard(unitId);
      },

      markReview(unitId) {
        playClickSound();
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        const w = words[s.flipIndex];
        if (w) {
          s.review.add(w.id || w.french);
          s.mastered.delete(w.id || w.french);
        }
        this.nextFlipCard(unitId);
      },

      renderFlipCardContent(unitId) {
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        const w = words[s.flipIndex];
        if (!w) return;

        const badge = this.getCategoryBadge(w.category);
        const cardInner = document.getElementById(unitId + '-flip-card-inner');
        if (cardInner) {
          cardInner.classList.remove('flipped');
        }

        // Front Face
        const frontFr = document.getElementById(unitId + '-flip-front-fr');
        const frontBadge = document.getElementById(unitId + '-flip-front-badge');
        const frontPage = document.getElementById(unitId + '-flip-front-page');
        if (frontFr) frontFr.innerText = w.french;
        if (frontBadge) {
          frontBadge.innerText = badge.label;
          frontBadge.className = 'px-2.5 py-0.5 rounded-full text-[11px] font-bold border ' + badge.cls;
        }
        if (frontPage) frontPage.innerText = w.pageReference || ('ص ' + (w.page || '14'));

        // Back Face
        const backAr = document.getElementById(unitId + '-flip-back-ar');
        const backEx = document.getElementById(unitId + '-flip-back-ex');
        if (backAr) backAr.innerText = w.arabic;
        if (backEx) {
          if (w.exampleFr) {
            backEx.innerHTML = `<span class="block font-bold text-slate-800">💡 ${w.exampleFr}</span>` +
              (w.exampleAr ? `<span class="block text-slate-500 text-[11px] mt-0.5">${w.exampleAr}</span>` : '');
          } else {
            backEx.innerHTML = '';
          }
        }

        // Indicator
        const counter = document.getElementById(unitId + '-flip-counter');
        if (counter) counter.innerText = `بطاقة ${s.flipIndex + 1} من ${words.length}`;

        // Update counts
        const mCount = document.getElementById(unitId + '-flip-mastered-count');
        const rCount = document.getElementById(unitId + '-flip-review-count');
        if (mCount) mCount.innerText = s.mastered.size;
        if (rCount) rCount.innerText = s.review.size;
      },

      // --- 3. TRANSLATION QUIZ ---
      renderQuizQuestion(unitId) {
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        if (!words.length) return;

        s.quizAnswered = false;
        const currentWord = words[s.quizIndex % words.length];

        // Prepare 4 choices (1 correct + 3 random distractors)
        const distractors = words.filter(w => w.arabic !== currentWord.arabic);
        const shuffledDistractors = [...distractors].sort(() => 0.5 - Math.random()).slice(0, 3);
        const choices = [currentWord, ...shuffledDistractors].sort(() => 0.5 - Math.random());

        const container = document.getElementById(unitId + '-quiz-container');
        if (!container) return;

        const badge = this.getCategoryBadge(currentWord.category);

        container.innerHTML = `
          <div class="bg-white rounded-2xl p-5 border border-indigo-100 shadow-sm space-y-4 max-w-xl mx-auto">
            <!-- Header bar -->
            <div class="flex items-center justify-between border-b border-slate-100 pb-3">
              <span class="px-2.5 py-1 rounded-lg bg-indigo-50 text-indigo-700 font-bold text-xs">
                السؤال ${s.quizIndex + 1} من ${Math.min(15, words.length)}
              </span>
              <div class="flex items-center gap-2">
                <span class="px-2.5 py-1 rounded-lg bg-amber-50 text-amber-800 font-bold text-xs flex items-center gap-1">
                  <i class="fa-solid fa-star text-amber-500"></i>
                  <span>النقاط: ${s.quizScore}</span>
                </span>
                <span class="px-2 py-1 rounded-lg bg-emerald-50 text-emerald-700 font-bold text-xs">
                  🔥 تتابع: ${s.quizStreak}
                </span>
              </div>
            </div>

            <!-- Question prompt -->
            <div class="text-center py-3 bg-gradient-to-br from-indigo-50/50 to-purple-50/40 rounded-xl border border-indigo-100/60 space-y-2">
              <span class="px-2.5 py-0.5 rounded-full text-[11px] font-bold border ${badge.cls}">${badge.label}</span>
              <p class="text-xs text-slate-500 font-bold">ما هو المعنى الصحيح باللغة العربية لكلمة :</p>
              <div class="flex items-center justify-center gap-2">
                <span class="text-2xl font-black text-indigo-900 font-french" dir="ltr">${currentWord.french}</span>
                <button onclick="speakText('${currentWord.french.replace(/['"]/g, ' ')}')" class="w-8 h-8 rounded-full bg-indigo-100 text-indigo-700 hover:bg-indigo-200 flex items-center justify-center transition">
                  <i class="fa-solid fa-volume-high text-xs"></i>
                </button>
              </div>
            </div>

            <!-- Choices Grid -->
            <div class="grid grid-cols-1 sm:grid-cols-2 gap-2.5 pt-1" id="${unitId}-quiz-choices">
              ${choices.map((c, i) => `
                <button onclick="VocabSuite.checkQuizAnswer('${unitId}', ${c.arabic === currentWord.arabic}, this, '${currentWord.arabic.replace(/['"]/g, ' ')}')" class="quiz-choice-btn p-3.5 rounded-xl border border-slate-200 bg-slate-50/60 hover:bg-indigo-50 hover:border-indigo-300 font-bold text-sm text-slate-800 transition text-center shadow-sm">
                  ${c.arabic}
                </button>
              `).join('')}
            </div>

            <!-- Feedback & Next Button -->
            <div id="${unitId}-quiz-feedback" class="hidden text-center py-2 text-xs font-bold"></div>
            <div id="${unitId}-quiz-next-box" class="hidden text-center pt-2">
              <button onclick="VocabSuite.nextQuizQuestion('${unitId}')" class="px-5 py-2 rounded-xl bg-indigo-600 hover:bg-indigo-700 active:scale-95 text-white font-bold text-xs flex items-center gap-2 mx-auto shadow-md transition">
                <span>السؤال التالي</span>
                <i class="fa-solid fa-arrow-left"></i>
              </button>
            </div>
          </div>
        `;
      },

      checkQuizAnswer(unitId, isCorrect, btn, correctArabic) {
        const s = this.state[unitId];
        if (s.quizAnswered) return;
        s.quizAnswered = true;

        const parent = document.getElementById(unitId + '-quiz-choices');
        const feedback = document.getElementById(unitId + '-quiz-feedback');
        const nextBox = document.getElementById(unitId + '-quiz-next-box');

        if (parent) {
          parent.querySelectorAll('.quiz-choice-btn').forEach(b => {
            b.disabled = true;
            if (b.innerText.trim() === correctArabic.trim()) {
              b.classList.remove('bg-slate-50/60', 'hover:bg-indigo-50');
              b.classList.add('bg-emerald-500', 'text-white', 'border-emerald-600');
            }
          });
        }

        if (isCorrect) {
          playSuccessSound();
          s.quizScore += 10;
          s.quizStreak++;
          btn.classList.add('bg-emerald-500', 'text-white', 'border-emerald-600');
          if (feedback) {
            feedback.innerHTML = '<span class="text-emerald-600 text-sm font-black">✓ أحسنت! إجابة صحيحة (+10 نقاط) 🎉</span>';
            feedback.classList.remove('hidden');
          }
        } else {
          playErrorSound();
          s.quizStreak = 0;
          btn.classList.add('bg-rose-500', 'text-white', 'border-rose-600');
          if (feedback) {
            feedback.innerHTML = `<span class="text-rose-600 text-sm font-bold">✗ إجابة غير صحيحة. المعنى الصحيح هو: <strong>${correctArabic}</strong></span>`;
            feedback.classList.remove('hidden');
          }
        }

        if (nextBox) nextBox.classList.remove('hidden');
      },

      nextQuizQuestion(unitId) {
        playClickSound();
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        const maxQ = Math.min(15, words.length);

        if (s.quizIndex < maxQ - 1) {
          s.quizIndex++;
          this.renderQuizQuestion(unitId);
        } else {
          // Finished Quiz
          if (typeof confetti === 'function') confetti({ particleCount: 150, spread: 80 });
          playSuccessSound();
          const container = document.getElementById(unitId + '-quiz-container');
          if (container) {
            container.innerHTML = `
              <div class="bg-white rounded-2xl p-6 border border-emerald-200 shadow-lg text-center space-y-4 max-w-md mx-auto">
                <div class="w-16 h-16 rounded-full bg-emerald-100 text-emerald-600 flex items-center justify-center text-3xl mx-auto shadow-inner">
                  🏆
                </div>
                <h3 class="text-lg font-black text-slate-900">أحسنت! أكملت اختبار المفردات بنجاح</h3>
                <div class="p-4 bg-emerald-50 rounded-xl border border-emerald-100 text-sm font-bold text-emerald-800">
                  مجموع نقاطك النهائية : ${s.quizScore} نقطة من ${maxQ * 10}
                </div>
                <button onclick="VocabSuite.restartQuiz('${unitId}')" class="px-5 py-2.5 rounded-xl bg-indigo-600 hover:bg-indigo-700 text-white font-bold text-xs flex items-center gap-2 mx-auto shadow-md transition">
                  <i class="fa-solid fa-rotate-right"></i>
                  <span>إعادة الاختبار من جديد</span>
                </button>
              </div>
            `;
          }
        }
      },

      restartQuiz(unitId) {
        playClickSound();
        const s = this.state[unitId];
        s.quizIndex = 0;
        s.quizScore = 0;
        s.quizStreak = 0;
        s.quizAnswered = false;
        this.renderQuizQuestion(unitId);
      },

      // --- 4. MISSING LETTERS PUZZLE ---
      renderPuzzleQuestion(unitId) {
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        if (!words.length) return;

        s.puzzleAnswered = false;
        const currentWord = words[s.puzzleIndex % words.length];
        const cleanFr = currentWord.french.toLowerCase();

        // Find a candidate letter to hide (vowel or consonant between index 1 and length-2)
        const candidates = [];
        for (let i = 1; i < cleanFr.length - 1; i++) {
          const char = cleanFr[i];
          if (/[a-z]/.test(char) && char !== ' ') {
            candidates.push({ char, index: i });
          }
        }

        const picked = candidates.length > 0 ? candidates[Math.floor(Math.random() * candidates.length)] : { char: 'e', index: 1 };
        const displayWord = cleanFr.substring(0, picked.index) + ' _ ' + cleanFr.substring(picked.index + 1);

        // Options: picked char + 3 random letters
        const alphabet = 'abcdefghijklmnopqrstuvwxyz'.split('').filter(c => c !== picked.char);
        const distractors = [...alphabet].sort(() => 0.5 - Math.random()).slice(0, 3);
        const options = [picked.char, ...distractors].sort(() => 0.5 - Math.random());

        const container = document.getElementById(unitId + '-puzzle-container');
        if (!container) return;

        container.innerHTML = `
          <div class="bg-white rounded-2xl p-5 border border-purple-100 shadow-sm space-y-4 max-w-xl mx-auto">
            <!-- Header bar -->
            <div class="flex items-center justify-between border-b border-slate-100 pb-3">
              <span class="px-2.5 py-1 rounded-lg bg-purple-50 text-purple-700 font-bold text-xs">
                لغز الحروف ${s.puzzleIndex + 1} من ${Math.min(15, words.length)}
              </span>
              <span class="px-2.5 py-1 rounded-lg bg-amber-50 text-amber-800 font-bold text-xs flex items-center gap-1">
                <i class="fa-solid fa-star text-amber-500"></i>
                <span>النقاط: ${s.puzzleScore}</span>
              </span>
            </div>

            <!-- Puzzle word display -->
            <div class="text-center py-4 bg-gradient-to-br from-purple-50/60 to-pink-50/40 rounded-xl border border-purple-100/80 space-y-2">
              <p class="text-xs text-slate-500 font-bold">المعنى بالعربية : <span class="text-purple-700 font-black">${currentWord.arabic}</span></p>
              <div class="text-2xl sm:text-3xl font-black text-slate-900 font-french tracking-widest my-2" dir="ltr" id="${unitId}-puzzle-display">
                ${displayWord}
              </div>
              <p class="text-[11px] text-slate-400">اختر الحرف الصحيح الناقص لإكمال الكلمة</p>
            </div>

            <!-- Letter Options -->
            <div class="flex justify-center gap-2 sm:gap-3 py-2" id="${unitId}-puzzle-options">
              ${options.map(letter => `
                <button onclick="VocabSuite.checkPuzzleLetter('${unitId}', '${letter}', '${picked.char}', '${currentWord.french.replace(/['"]/g, ' ')}', this)" class="puzzle-letter-btn w-12 h-12 rounded-xl border-2 border-purple-200 bg-white hover:bg-purple-600 hover:text-white font-black text-lg text-purple-900 transition flex items-center justify-center shadow-sm active:scale-95">
                  ${letter.toUpperCase()}
                </button>
              `).join('')}
            </div>

            <!-- Feedback & Next Button -->
            <div id="${unitId}-puzzle-feedback" class="hidden text-center py-2 text-xs font-bold"></div>
            <div id="${unitId}-puzzle-next-box" class="hidden text-center pt-2">
              <button onclick="VocabSuite.nextPuzzleQuestion('${unitId}')" class="px-5 py-2 rounded-xl bg-purple-600 hover:bg-purple-700 active:scale-95 text-white font-bold text-xs flex items-center gap-2 mx-auto shadow-md transition">
                <span>الكلمة التالية</span>
                <i class="fa-solid fa-arrow-left"></i>
              </button>
            </div>
          </div>
        `;
      },

      checkPuzzleLetter(unitId, chosen, correct, fullWord, btn) {
        const s = this.state[unitId];
        if (s.puzzleAnswered) return;

        const feedback = document.getElementById(unitId + '-puzzle-feedback');
        const nextBox = document.getElementById(unitId + '-puzzle-next-box');
        const display = document.getElementById(unitId + '-puzzle-display');

        if (chosen === correct) {
          s.puzzleAnswered = true;
          s.puzzleScore += 10;
          playSuccessSound();
          if (typeof confetti === 'function') confetti({ particleCount: 60, spread: 60 });
          btn.classList.remove('bg-white', 'text-purple-900');
          btn.classList.add('bg-emerald-500', 'text-white', 'border-emerald-600');
          if (display) display.innerHTML = `<span class="text-emerald-600 animate-pulse">${fullWord}</span>`;
          speakText(fullWord);
          if (feedback) {
            feedback.innerHTML = '<span class="text-emerald-600 text-sm font-black">✓ ممتاز! إجابة صحيحة (+10 نقاط) 🌟</span>';
            feedback.classList.remove('hidden');
          }
          if (nextBox) nextBox.classList.remove('hidden');
        } else {
          playErrorSound();
          btn.classList.add('bg-rose-500', 'text-white', 'border-rose-600');
          if (feedback) {
            feedback.innerHTML = '<span class="text-rose-500 text-xs">حاول مجدداً! الحرف غير صحيح.</span>';
            feedback.classList.remove('hidden');
          }
        }
      },

      nextPuzzleQuestion(unitId) {
        playClickSound();
        const s = this.state[unitId];
        const words = this.getFilteredWords(unitId);
        const maxQ = Math.min(15, words.length);

        if (s.puzzleIndex < maxQ - 1) {
          s.puzzleIndex++;
          this.renderPuzzleQuestion(unitId);
        } else {
          // Finished Puzzle
          if (typeof confetti === 'function') confetti({ particleCount: 140, spread: 80 });
          playSuccessSound();
          const container = document.getElementById(unitId + '-puzzle-container');
          if (container) {
            container.innerHTML = `
              <div class="bg-white rounded-2xl p-6 border border-purple-200 shadow-lg text-center space-y-4 max-w-md mx-auto">
                <div class="w-16 h-16 rounded-full bg-purple-100 text-purple-600 flex items-center justify-center text-3xl mx-auto shadow-inner">
                  🧩
                </div>
                <h3 class="text-lg font-black text-slate-900">رائع! أتممت لغز الحروف الناقصة</h3>
                <div class="p-4 bg-purple-50 rounded-xl border border-purple-100 text-sm font-bold text-purple-800">
                  مجموع نقاطك : ${s.puzzleScore} نقطة
                </div>
                <button onclick="VocabSuite.restartPuzzle('${unitId}')" class="px-5 py-2.5 rounded-xl bg-purple-600 hover:bg-purple-700 text-white font-bold text-xs flex items-center gap-2 mx-auto shadow-md transition">
                  <i class="fa-solid fa-rotate-right"></i>
                  <span>إعادة اللعبة من جديد</span>
                </button>
              </div>
            `;
          }
        }
      },

      restartPuzzle(unitId) {
        playClickSound();
        const s = this.state[unitId];
        s.puzzleIndex = 0;
        s.puzzleScore = 0;
        s.puzzleAnswered = false;
        this.renderPuzzleQuestion(unitId);
      },

      // --- MAIN SECTION RENDERER ---
      render(unitId) {
        const s = this.state[unitId];
        const allWords = this.getWords(unitId);
        const filteredWords = this.getFilteredWords(unitId);
        const container = document.getElementById(unitId + '-interactive-vocab-root');
        if (!container) return;

        // Categories with counts
        const catCounts = {
          ALL: allWords.length,
          MASCULINE: allWords.filter(w => w.category === 'MASCULINE').length,
          FEMININE: allWords.filter(w => w.category === 'FEMININE').length,
          VERB: allWords.filter(w => w.category === 'VERB').length,
          EXPRESSION: allWords.filter(w => w.category === 'EXPRESSION').length,
          CORPS: allWords.filter(w => w.category === 'CORPS').length,
          PERSONNAGE: allWords.filter(w => w.category === 'PERSONNAGE').length,
          LIEU: allWords.filter(w => w.category === 'LIEU').length
        };

        const availableCats = ['ALL'];
        if (catCounts.MASCULINE > 0) availableCats.push('MASCULINE');
        if (catCounts.FEMININE > 0) availableCats.push('FEMININE');
        if (catCounts.VERB > 0) availableCats.push('VERB');
        if (catCounts.EXPRESSION > 0) availableCats.push('EXPRESSION');
        if (catCounts.CORPS > 0) availableCats.push('CORPS');
        if (catCounts.PERSONNAGE > 0) availableCats.push('PERSONNAGE');
        if (catCounts.LIEU > 0) availableCats.push('LIEU');

        const unitTitle = unitId === 'u1' ? 'مفردات وأفعال كتيّب المعهد (ص 14)' :
          (unitId === 'u2' ? 'مفردات وأفعال كتيّب المعهد (ص 38)' : 'بنك الكلمات الطبي ومفردات الوحدة (ص 62)');
        const unitPageTag = unitId === 'u1' ? 'p. 14 • ' + allWords.length + ' كلمة' :
          (unitId === 'u2' ? 'p. 38 • ' + allWords.length + ' كلمة' : 'p. 62 • ' + allWords.length + ' كلمة');

        let html = `
          <div class="space-y-4">
            <!-- Header Card with Mode Switcher -->
            <div class="bg-white rounded-2xl p-4 sm:p-5 border border-purple-100 shadow-sm space-y-3.5">
              <div class="flex flex-wrap items-center justify-between gap-3 border-b border-slate-100 pb-3">
                <div>
                  <h3 class="text-base font-black text-indigo-900 flex items-center gap-2">
                    <i class="fa-solid fa-book-open text-purple-600"></i>
                    <span>📖 Vocabulaire & Verbes</span>
                  </h3>
                  <p class="text-xs text-purple-700 font-bold">${unitTitle}</p>
                </div>
                <span class="px-3 py-1 rounded-lg bg-purple-700 text-white font-black text-xs font-french shadow-sm">
                  ${unitPageTag}
                </span>
              </div>

              <!-- Mode Selector Tabs -->
              <div class="flex flex-wrap gap-2 pt-1">
                <button onclick="VocabSuite.setMode('${unitId}', 'cards')" class="px-3.5 py-2 rounded-xl text-xs font-bold transition flex items-center gap-1.5 ${s.mode === 'cards' ? 'bg-purple-700 text-white shadow-md' : 'bg-slate-100 text-slate-700 hover:bg-slate-200'}">
                  <i class="fa-solid fa-table-cells"></i>
                  <span>📁 بطاقات الكلمات</span>
                </button>
                <button onclick="VocabSuite.setMode('${unitId}', 'flip')" class="px-3.5 py-2 rounded-xl text-xs font-bold transition flex items-center gap-1.5 ${s.mode === 'flip' ? 'bg-purple-700 text-white shadow-md' : 'bg-slate-100 text-slate-700 hover:bg-slate-200'}">
                  <i class="fa-solid fa-arrows-rotate"></i>
                  <span>🔄 البطاقات المقلوبة</span>
                </button>
                <button onclick="VocabSuite.setMode('${unitId}', 'quiz')" class="px-3.5 py-2 rounded-xl text-xs font-bold transition flex items-center gap-1.5 ${s.mode === 'quiz' ? 'bg-purple-700 text-white shadow-md' : 'bg-slate-100 text-slate-700 hover:bg-slate-200'}">
                  <i class="fa-solid fa-bullseye"></i>
                  <span>🎯 اختبار الترجمة</span>
                </button>
                <button onclick="VocabSuite.setMode('${unitId}', 'puzzle')" class="px-3.5 py-2 rounded-xl text-xs font-bold transition flex items-center gap-1.5 ${s.mode === 'puzzle' ? 'bg-purple-700 text-white shadow-md' : 'bg-slate-100 text-slate-700 hover:bg-slate-200'}">
                  <i class="fa-solid fa-puzzle-piece"></i>
                  <span>🧩 الحروف الناقصة</span>
                </button>
              </div>
            </div>
        `;

        // 1. CARDS MODE (Flashcards List + Continuous Audio + Search)
        if (s.mode === 'cards') {
          html += `
            <!-- Continuous Audio Bar & Filter Chips -->
            <div class="bg-purple-50/70 rounded-2xl p-4 border border-purple-100 space-y-3">
              <div class="flex flex-wrap items-center justify-between gap-3 border-b border-purple-200/50 pb-3">
                <span class="text-xs font-bold text-purple-900 flex items-center gap-1.5">
                  <i class="fa-solid fa-headphones text-purple-700"></i>
                  <span>🎧 استماع تلقائي متتابع :</span>
                </span>
                <button id="${unitId}-continuous-audio-btn" onclick="VocabSuite.toggleContinuousAudio('${unitId}')" class="px-3 py-1.5 rounded-xl bg-indigo-600 hover:bg-indigo-700 text-white font-bold text-xs flex items-center gap-1.5 shadow-sm transition">
                  <i class="fa-solid fa-play ml-1"></i> استمع للكل بالتتابع
                </button>
              </div>

              <!-- Category Filter Chips with counts -->
              <div class="flex flex-wrap gap-1.5 items-center">
                ${availableCats.map(cat => {
                  const b = VocabSuite.getCategoryBadge(cat);
                  const isSel = s.cat === cat;
                  const label = cat === 'ALL' ? `الكل (${catCounts.ALL})` : `${b.label} (${catCounts[cat] || 0})`;
                  return `
                    <button onclick="VocabSuite.setCategory('${unitId}', '${cat}')" class="px-3 py-1.5 rounded-xl text-xs font-bold transition ${isSel ? 'bg-indigo-700 text-white shadow-sm' : 'bg-white text-slate-700 hover:bg-slate-100 border border-slate-200'}">
                      ${label}
                    </button>
                  `;
                }).join('')}
              </div>

              <!-- Search input -->
              <div class="pt-1">
                <input type="text" id="${unitId}-vocab-search" oninput="VocabSuite.filterCards('${unitId}')" placeholder="🔍 ابحث في مفردات الوحدة (بالفرنسية أو العربية)..." class="w-full px-3.5 py-2 text-xs rounded-xl border border-purple-200 focus:outline-none focus:ring-2 focus:ring-purple-500 bg-white shadow-inner">
              </div>
            </div>

            <!-- Words List Grid -->
            <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-3" id="${unitId}-vocab-cards-grid">
              ${filteredWords.map((w, idx) => {
                const b = VocabSuite.getCategoryBadge(w.category);
                return `
                  <div id="${unitId}-word-card-${idx}" class="${unitId}-vocab-card p-4 rounded-2xl bg-white border border-slate-200/90 shadow-sm hover:shadow-md hover:border-purple-300 transition flex items-start justify-between gap-3" data-fr="${(w.french||'').toLowerCase()}" data-ar="${w.arabic||''}">
                    <div class="space-y-1.5 overflow-hidden flex-1">
                      <div class="flex flex-wrap items-center gap-2">
                        <span class="text-sm font-black text-slate-900 font-french" dir="ltr">${w.french}</span>
                        <span class="px-2 py-0.5 rounded-md text-[10px] font-bold border ${b.cls}">${b.label}</span>
                      </div>
                      <p class="text-xs font-bold text-purple-700">${w.arabic}</p>
                      ${w.exampleFr ? `
                        <div class="pt-1 border-t border-slate-100 text-[11px] text-slate-600 space-y-0.5">
                          <p class="font-french" dir="ltr">💡 ${w.exampleFr}</p>
                          ${w.exampleAr ? `<p class="text-[10px] text-slate-400">${w.exampleAr}</p>` : ''}
                        </div>
                      ` : ''}
                    </div>
                    <button onclick="speakText('${(w.french||'').replace(/['"]/g, ' ')}')" class="w-9 h-9 rounded-full bg-purple-50 hover:bg-purple-100 active:scale-95 text-purple-700 flex items-center justify-center shrink-0 transition shadow-inner">
                      <i class="fa-solid fa-volume-high text-xs"></i>
                    </button>
                  </div>
                `;
              }).join('')}
            </div>
          `;
        }

        // 2. FLIP CARDS MODE (3D Flip Animation)
        else if (s.mode === 'flip') {
          const currentWord = filteredWords[s.flipIndex] || filteredWords[0];
          const badge = this.getCategoryBadge(currentWord?.category);

          html += `
            <div class="space-y-4 max-w-lg mx-auto">
              <!-- Flip Card 3D Container -->
              <div class="flip-card-wrapper w-full h-64 sm:h-72 cursor-pointer select-none" onclick="VocabSuite.flipCard('${unitId}')">
                <div id="${unitId}-flip-card-inner" class="flip-card-inner w-full h-full relative rounded-3xl shadow-lg border border-purple-100 ${s.isFlipped ? 'flipped' : ''}">
                  
                  <!-- FRONT FACE (French) -->
                  <div class="flip-card-front absolute inset-0 w-full h-full bg-white rounded-3xl p-6 flex flex-col justify-between items-center text-center border-2 border-purple-200">
                    <div class="flex items-center justify-between w-full">
                      <span id="${unitId}-flip-front-badge" class="px-2.5 py-0.5 rounded-full text-[11px] font-bold border ${badge.cls}">${badge.label}</span>
                      <span id="${unitId}-flip-front-page" class="text-xs font-bold text-slate-400 font-french">${currentWord?.pageReference || 'p. 14'}</span>
                    </div>

                    <div class="space-y-3 my-auto">
                      <h4 id="${unitId}-flip-front-fr" class="text-2xl sm:text-3xl font-black text-indigo-900 font-french tracking-wide" dir="ltr">${currentWord?.french || ''}</h4>
                      <button onclick="event.stopPropagation(); speakText('${(currentWord?.french||'').replace(/['"]/g, ' ')}')" class="px-3.5 py-1.5 rounded-full bg-purple-50 hover:bg-purple-100 text-purple-700 font-bold text-xs flex items-center gap-1.5 mx-auto transition">
                        <i class="fa-solid fa-volume-high"></i>
                        <span>استمع للنطق</span>
                      </button>
                    </div>

                    <p class="text-[11px] text-purple-600 font-bold flex items-center gap-1">
                      <i class="fa-solid fa-hand-pointer"></i>
                      <span>انقر على البطاقة لقلبها ومعرفة الترجمة 👆</span>
                    </p>
                  </div>

                  <!-- BACK FACE (Arabic) -->
                  <div class="flip-card-back absolute inset-0 w-full h-full bg-gradient-to-br from-purple-50 to-indigo-50 rounded-3xl p-6 flex flex-col justify-between items-center text-center border-2 border-indigo-200">
                    <div class="flex items-center justify-between w-full">
                      <span class="px-2.5 py-0.5 rounded-full bg-indigo-100 text-indigo-800 font-bold text-[11px]">المعنى والمثال</span>
                      <span class="text-xs font-bold text-slate-400">العربية</span>
                    </div>

                    <div class="space-y-2 my-auto">
                      <h4 id="${unitId}-flip-back-ar" class="text-2xl sm:text-3xl font-black text-purple-900">${currentWord?.arabic || ''}</h4>
                      <div id="${unitId}-flip-back-ex" class="text-xs text-slate-600 max-w-xs mx-auto">
                        ${currentWord?.exampleFr ? `<p class="font-french font-bold text-indigo-900" dir="ltr">💡 ${currentWord.exampleFr}</p>` : ''}
                        ${currentWord?.exampleAr ? `<p class="text-[11px] text-slate-500 mt-0.5">${currentWord.exampleAr}</p>` : ''}
                      </div>
                    </div>

                    <p class="text-[11px] text-indigo-600 font-bold flex items-center gap-1">
                      <i class="fa-solid fa-rotate"></i>
                      <span>انقر للرجوع إلى الوجه الفرنسي 🔄</span>
                    </p>
                  </div>

                </div>
              </div>

              <!-- Navigation Controls -->
              <div class="flex items-center justify-between gap-2 bg-white p-3 rounded-2xl border border-slate-200 shadow-sm">
                <button onclick="VocabSuite.prevFlipCard('${unitId}')" class="px-3.5 py-2 rounded-xl bg-slate-100 hover:bg-slate-200 text-slate-700 font-bold text-xs flex items-center gap-1.5 transition">
                  <i class="fa-solid fa-arrow-right"></i>
                  <span>السابق</span>
                </button>

                <span id="${unitId}-flip-counter" class="text-xs font-black text-indigo-900 px-3 py-1 rounded-lg bg-indigo-50">
                  بطاقة ${s.flipIndex + 1} من ${filteredWords.length}
                </span>

                <button onclick="VocabSuite.nextFlipCard('${unitId}')" class="px-3.5 py-2 rounded-xl bg-slate-100 hover:bg-slate-200 text-slate-700 font-bold text-xs flex items-center gap-1.5 transition">
                  <span>التالي</span>
                  <i class="fa-solid fa-arrow-left"></i>
                </button>
              </div>

              <!-- Mastery Feedback Buttons -->
              <div class="grid grid-cols-2 gap-3">
                <button onclick="VocabSuite.markReview('${unitId}')" class="p-3 rounded-xl bg-amber-50 hover:bg-amber-100 text-amber-900 font-bold text-xs border border-amber-200 flex items-center justify-center gap-1.5 transition shadow-sm">
                  <i class="fa-solid fa-rotate-left text-amber-600"></i>
                  <span>أحتاج مراجعتها (<span id="${unitId}-flip-review-count">${s.review.size}</span>)</span>
                </button>
                <button onclick="VocabSuite.markMastered('${unitId}')" class="p-3 rounded-xl bg-emerald-50 hover:bg-emerald-100 text-emerald-900 font-bold text-xs border border-emerald-200 flex items-center justify-center gap-1.5 transition shadow-sm">
                  <i class="fa-solid fa-check text-emerald-600"></i>
                  <span>أعرفها جيداً (<span id="${unitId}-flip-mastered-count">${s.mastered.size}</span>)</span>
                </button>
              </div>

              <div class="text-center">
                <button onclick="VocabSuite.shuffleFlipCards('${unitId}')" class="text-xs font-bold text-slate-500 hover:text-indigo-600 flex items-center gap-1 mx-auto transition">
                  <i class="fa-solid fa-shuffle"></i>
                  <span>خلط البطاقات عشوائياً</span>
                </button>
              </div>
            </div>
          `;
        }

        // 3. QUIZ MODE
        else if (s.mode === 'quiz') {
          html += `<div id="${unitId}-quiz-container" class="py-2"></div>`;
        }

        // 4. PUZZLE MODE
        else if (s.mode === 'puzzle') {
          html += `<div id="${unitId}-puzzle-container" class="py-2"></div>`;
        }

        html += `</div>`;
        container.innerHTML = html;

        // Post-render initialization for Quiz or Puzzle
        if (s.mode === 'quiz') {
          this.renderQuizQuestion(unitId);
        } else if (s.mode === 'puzzle') {
          this.renderPuzzleQuestion(unitId);
        }
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

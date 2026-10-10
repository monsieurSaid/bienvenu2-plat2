
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

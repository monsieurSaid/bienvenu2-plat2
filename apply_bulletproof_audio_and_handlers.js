const fs = require('fs');

let html = fs.readFileSync('index.html', 'utf-8');

// 1. Fix Audio Functions to be completely safe
const oldAudioBlock = `function initAudio() {
      if (!audioCtx) {
        audioCtx = new (window.AudioContext || window.webkitAudioContext)();
      }
    }

    function playSuccessSound() {
      if (!isSoundEnabled) return;
      initAudio();
      try {
        const now = audioCtx.currentTime;
        const osc = audioCtx.createOscillator();
        const gain = audioCtx.createGain();
        osc.connect(gain);
        gain.connect(audioCtx.destination);
        osc.type = 'triangle';
        osc.frequency.setValueAtTime(523.25, now);
        osc.frequency.setValueAtTime(659.25, now + 0.1);
        osc.frequency.setValueAtTime(783.99, now + 0.2);
        osc.frequency.setValueAtTime(1046.50, now + 0.3);
        gain.gain.setValueAtTime(0.15, now);
        gain.gain.exponentialRampToValueAtTime(0.001, now + 0.5);
        osc.start(now);
        osc.stop(now + 0.5);
      } catch (e) {}
    }

    function playErrorSound() {
      if (!isSoundEnabled) return;
      initAudio();
      try {
        const now = audioCtx.currentTime;
        const osc = audioCtx.createOscillator();
        const gain = audioCtx.createGain();
        osc.connect(gain);
        gain.connect(audioCtx.destination);
        osc.type = 'sawtooth';
        osc.frequency.setValueAtTime(180, now);
        osc.frequency.linearRampToValueAtTime(120, now + 0.25);
        gain.gain.setValueAtTime(0.15, now);
        gain.gain.exponentialRampToValueAtTime(0.001, now + 0.3);
        osc.start(now);
        osc.stop(now + 0.3);
      } catch (e) {}
    }

    function playClickSound() {
      if (!isSoundEnabled) return;
      initAudio();
      try {
        const now = audioCtx.currentTime;
        const osc = audioCtx.createOscillator();
        const gain = audioCtx.createGain();
        osc.connect(gain);
        gain.connect(audioCtx.destination);
        osc.type = 'sine';
        osc.frequency.setValueAtTime(800, now);
        gain.gain.setValueAtTime(0.08, now);
        gain.gain.exponentialRampToValueAtTime(0.001, now + 0.05);
        osc.start(now);
        osc.stop(now + 0.05);
      } catch (e) {}
    }`;

const newAudioBlock = `function initAudio() {
      try {
        if (!audioCtx && typeof window !== 'undefined' && (window.AudioContext || window.webkitAudioContext)) {
          const AudioCtxClass = window.AudioContext || window.webkitAudioContext;
          audioCtx = new AudioCtxClass();
        }
        if (audioCtx && audioCtx.state === 'suspended') {
          audioCtx.resume().catch(() => {});
        }
      } catch (e) {
        audioCtx = null;
      }
    }

    function playSuccessSound() {
      if (!isSoundEnabled) return;
      try {
        initAudio();
        if (!audioCtx) return;
        const now = audioCtx.currentTime;
        const osc = audioCtx.createOscillator();
        const gain = audioCtx.createGain();
        osc.connect(gain);
        gain.connect(audioCtx.destination);
        osc.type = 'triangle';
        osc.frequency.setValueAtTime(523.25, now);
        osc.frequency.setValueAtTime(659.25, now + 0.1);
        osc.frequency.setValueAtTime(783.99, now + 0.2);
        osc.frequency.setValueAtTime(1046.50, now + 0.3);
        gain.gain.setValueAtTime(0.15, now);
        gain.gain.exponentialRampToValueAtTime(0.001, now + 0.5);
        osc.start(now);
        osc.stop(now + 0.5);
      } catch (e) {}
    }

    function playErrorSound() {
      if (!isSoundEnabled) return;
      try {
        initAudio();
        if (!audioCtx) return;
        const now = audioCtx.currentTime;
        const osc = audioCtx.createOscillator();
        const gain = audioCtx.createGain();
        osc.connect(gain);
        gain.connect(audioCtx.destination);
        osc.type = 'sawtooth';
        osc.frequency.setValueAtTime(180, now);
        osc.frequency.linearRampToValueAtTime(120, now + 0.25);
        gain.gain.setValueAtTime(0.15, now);
        gain.gain.exponentialRampToValueAtTime(0.001, now + 0.3);
        osc.start(now);
        osc.stop(now + 0.3);
      } catch (e) {}
    }

    function playClickSound() {
      if (!isSoundEnabled) return;
      try {
        initAudio();
        if (!audioCtx) return;
        const now = audioCtx.currentTime;
        const osc = audioCtx.createOscillator();
        const gain = audioCtx.createGain();
        osc.connect(gain);
        gain.connect(audioCtx.destination);
        osc.type = 'sine';
        osc.frequency.setValueAtTime(800, now);
        gain.gain.setValueAtTime(0.08, now);
        gain.gain.exponentialRampToValueAtTime(0.001, now + 0.05);
        osc.start(now);
        osc.stop(now + 0.05);
      } catch (e) {}
    }`;

if (html.includes('function playSuccessSound()')) {
  // Replace audio section
  const startIdx = html.indexOf('function initAudio()');
  const endIdx = html.indexOf('// --- Speech Engine ---', startIdx);
  if (startIdx !== -1 && endIdx !== -1) {
    html = html.substring(0, startIdx) + newAudioBlock + '\n\n    ' + html.substring(endIdx);
    console.log('Audio block updated safely!');
  }
}

// 2. Make SituationsSuite.selectOption 100% fail-proof with try-finally
const newSituationsSelectOption = `selectOption(unitId, qId, oIdx) {
        try {
          const s = this.ensureState(unitId);
          const qKey = String(qId);
          if (s.userAnswers[qKey] !== undefined || s.userAnswers[qId] !== undefined) return;

          const sitList = this.getItems(unitId);
          const item = sitList.find((it, idx) => String(it.id || it.number || (idx + 1)) === qKey);
          if (!item || !item.options || !item.options[oIdx]) return;

          const optObj = item.options[oIdx];
          const chosenKey = (optObj.key || String.fromCharCode(97 + oIdx)).toLowerCase();
          const correctKey = (item.correctKey || item.correctOption || 'c').toString().toLowerCase().trim();

          s.userAnswers[qKey] = chosenKey;
          s.userAnswers[qId] = chosenKey;
          s.showExplanation[qKey] = true;

          const isCorrect = chosenKey === correctKey;
          if (isCorrect) {
            try { playSuccessSound(); } catch (e) {}
            s.score += 5;
          } else {
            try { playErrorSound(); } catch (e) {}
          }
        } catch (err) {
          console.error('Error in SituationsSuite.selectOption:', err);
        } finally {
          this.render(unitId);
        }
      },`;

// Replace selectOption in SituationsSuite
const sitSelStart = html.indexOf('selectOption(unitId, qId, oIdx) {');
const sitSelEnd = html.indexOf('resetQuiz(unitId) {', sitSelStart);
if (sitSelStart !== -1 && sitSelEnd !== -1) {
  html = html.substring(0, sitSelStart) + newSituationsSelectOption + '\n\n      ' + html.substring(sitSelEnd);
  console.log('SituationsSuite selectOption updated with try-finally!');
}

fs.writeFileSync('index.html', html, 'utf-8');
fs.writeFileSync('app/src/main/assets/index.html', html, 'utf-8');

console.log('Successfully saved updated index.html and assets/index.html');

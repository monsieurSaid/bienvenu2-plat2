import fs from 'node:fs';

const extraData = JSON.parse(fs.readFileSync('server/u2_u3_banque_words.json', 'utf-8'));
const sitData = JSON.parse(fs.readFileSync('server/situations_quiz_data.json', 'utf-8'));

let html = fs.readFileSync('index.html', 'utf-8');

// 1. Update line 716 (COURSE_DATA JSON)
const lines = html.split('\n');
const line716 = lines[715];
const jsonStr = line716.slice('window.COURSE_DATA = '.length).replace(/;\s*$/, '');
const cd = JSON.parse(jsonStr);

// Inject extra words
cd.course.unit2BanqueMots = extraData.u2Words;
cd.course.unit3BanqueWords = extraData.u3Words;
cd.course.unit2SituationsItems = sitData.u2Situations;
cd.course.unit3SituationsItems = sitData.u3Situations;

lines[715] = 'window.COURSE_DATA = ' + JSON.stringify(cd) + ';';
html = lines.join('\n');
console.log('Updated COURSE_DATA with extra words and situations!');

fs.writeFileSync('index.html', html, 'utf-8');
console.log('Saved index.html with updated COURSE_DATA');

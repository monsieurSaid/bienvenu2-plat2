import fs from 'node:fs';

const extraData = JSON.parse(fs.readFileSync('server/u2_u3_banque_words.json', 'utf-8'));
const sitData = JSON.parse(fs.readFileSync('server/situations_quiz_data.json', 'utf-8'));

console.log('Extra words:', extraData.u2Words.length, extraData.u3Words.length);
console.log('Situations:', sitData.u1Situations.length, sitData.u2Situations.length, sitData.u3Situations.length);

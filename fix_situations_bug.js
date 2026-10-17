const fs = require('fs');

let html = fs.readFileSync('index.html', 'utf-8');

// Replace this.getSituationsList(unitId) with this.getItems(unitId)
html = html.replace(
  'const sitList = this.getSituationsList(unitId);',
  'const sitList = this.getItems(unitId);'
);

// Improve finding item in sitList
html = html.replace(
  'const item = sitList.find(it => String(it.id || it.number) === qKey);',
  'const item = sitList.find((it, idx) => String(it.id || it.number || (idx + 1)) === qKey);'
);

// Also add getSituationsList alias just in case
html = html.replace(
  'getItems(unitId) {',
  'getSituationsList(unitId) {\n        return this.getItems(unitId);\n      },\n      getItems(unitId) {'
);

fs.writeFileSync('index.html', html, 'utf-8');
fs.writeFileSync('app/src/main/assets/index.html', html, 'utf-8');

console.log('Successfully fixed SituationsSuite getItems/getSituationsList bug!');

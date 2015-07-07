var mex = require('./mexapi.js');

console.log('name:' + mex.getApplicationContextName());
if (mex.parseMEX()) {
	console.log('ueba');
}

console.log( 'The area of a circle of radius 4 is '
           + mex.parseMEX());
mex.setApplicationContextEmail('esteves@informatik.uni-leipzig.de');
console.log(mex.parseMEX());

console.log('name:' + mex.getApplicationContextEmail());
mex.generateMEX();
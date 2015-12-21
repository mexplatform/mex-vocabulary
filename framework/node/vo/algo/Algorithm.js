/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function Algorithm(ind, className) {
    this._individualName = ind;
    this._className = className;
}
// class methods
Algorithm.prototype.getIndividualName = function() {
    return this._individualName;
};
Algorithm.prototype.getClassName = function() {
    return this._className;
};
Algorithm.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
Algorithm.prototype.setClassName = function(value) {
    this._className = value;
};

// export the class
module.exports = Algorithm;

/**
 * Created by esteves on 11.07.15.
 */
var util = require('../../util/mexconstant.js');

// constructor
function Context(className) {
    this._individualName = util.DEF_INDIVIDUALS.CONTEXT;
    this._className = className;
}
// class methods
Context.prototype.getIndividualName = function() {
    return this._individualName;
};
Context.prototype.getClassName = function() {
    return this._className;
};
Context.prototype.setClassName = function(value) {
    this._className = value;
};
// export the class
module.exports = Context;

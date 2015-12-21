/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function HyperParameter() {
    this._individualName = '';
    this._className = Util.DEF_CLASSES.MEX_ALGO.HYPERPARAMETER;
    this._id = '';
    this._value = '';
}
// class methods
HyperParameter.prototype.getIndividualName = function() {
    return this._individualName;
};
HyperParameter.prototype.getClassName = function() {
    return this._className;
};
HyperParameter.prototype.getIdentification = function() {
    return this._id;
};
HyperParameter.prototype.getValue = function() {
    return this._value;
};

HyperParameter.prototype.setIdentification = function(value) {
    this._id = value;
};
HyperParameter.prototype.setValue = function(value) {
    this._value = value;
};
HyperParameter.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = HyperParameter;

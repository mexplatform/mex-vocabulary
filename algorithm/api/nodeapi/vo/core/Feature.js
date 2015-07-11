/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function Feature() {
    this._individualName = '';
    this._className = Util.DEF_CLASSES.MEX_CORE.FEATURE;
    this._id = '';
    this._value = '';
}
// class methods
Feature.prototype.getIndividualName = function() {
    return this._individualName;
};
Feature.prototype.getClassName = function() {
    return this._className;
};
Feature.prototype.getIdentification = function() {
    return this._id;
};
Feature.prototype.getValue = function() {
    return this._value;
};

Feature.prototype.setIdentification = function(value) {
    this._id = value;
};
Feature.prototype.setValue = function(value) {
    this._value = value;
};
Feature.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = Feature;

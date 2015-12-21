/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function Feature(ind, feature) {
    this._individualName = ind;
    this._className = Util.DEF_CLASSES.MEX_CORE.FEATURE;
    this._id = feature;
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

Feature.prototype.setIdentification = function(value) {
    this._id = value;
};
Feature.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = Feature;

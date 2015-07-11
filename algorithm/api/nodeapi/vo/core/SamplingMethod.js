/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function SamplingMethod() {
    this._individualName = '';
    this._className = Util.DEF_CLASSES.MEX_CORE.FEATURE;
    this._trainSize = '';
    this._testSize = '';
}
// class methods
SamplingMethod.prototype.getIndividualName = function() {
    return this._individualName;
};
SamplingMethod.prototype.getClassName = function() {
    return this._className;
};
SamplingMethod.prototype.getTrainSize = function() {
    return this._trainSize;
};
SamplingMethod.prototype.getTestSize = function() {
    return this._testSize;
};

SamplingMethod.prototype.setIdentification = function(value) {
    this._id = value;
};
SamplingMethod.prototype.setTrainSize = function(value) {
    this._trainSize = value;
};
SamplingMethod.prototype.setTestSize = function(value) {
    this._testSize = value;
};
SamplingMethod.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = SamplingMethod;



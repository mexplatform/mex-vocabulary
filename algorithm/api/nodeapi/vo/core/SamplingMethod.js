/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function SamplingMethod() {
    this._individualName = '';
    this._className = '';
    this._trainSize = '';
    this._testSize = '';
    this._folds = '';
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
SamplingMethod.prototype.getFolds = function() {
    return this._folds;
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
SamplingMethod.prototype.setFolds = function(value) {
    this._folds = value;
};
SamplingMethod.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
SamplingMethod.prototype.setClassName = function(value) {
    this._className = value;
};
// export the class
module.exports = SamplingMethod;



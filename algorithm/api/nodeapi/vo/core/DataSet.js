/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function DataSet() {
    this._individualName = '';
    this._className = Util.DEF_CLASSES.MEX_CORE.DATASET;
    this._id = '';
    this._name = '';
}
// class methods
DataSet.prototype.getIndividualName = function() {
    return this._individualName;
};
DataSet.prototype.getClassName = function() {
    return this._className;
};
DataSet.prototype.getIdentification = function() {
    return this._id;
};
DataSet.prototype.getName = function() {
    return this._name;
};

DataSet.prototype.setIdentification = function(value) {
    this._id = value;
};
DataSet.prototype.setName = function(value) {
    this._name = value;
};
DataSet.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = DataSet;

/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function DataSet() {
    this._individualName = '';
    this._className = Util.DEF_CLASSES.MEX_CORE.DATASET;
    this._title = '';
    this._description = '';
    this._landingPage = '';
}
// class methods
DataSet.prototype.getIndividualName = function() {
    return this._individualName;
};
DataSet.prototype.getClassName = function() {
    return this._className;
};
DataSet.prototype.getTitle = function() {
    return this._title;
};
DataSet.prototype.getDescription = function() {
    return this._description;
};
DataSet.prototype.getLandingPage = function() {
    return this._landingPage;
};

DataSet.prototype.setTitle = function(value) {
    this._title = value;
};
DataSet.prototype.setDescription = function(value) {
    this._description = value;
};
DataSet.prototype.setLandingPage = function(value) {
    this._landingPage = value;
};

DataSet.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
// export the class
module.exports = DataSet;

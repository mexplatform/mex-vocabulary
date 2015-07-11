/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function Experiment() {
    this._individualName = Util.DEF_INDIVIDUALS.EXP;
    this._className = Util.DEF_CLASSES.MEX_CORE.EXP;
    this._id = '';
    this._date = new Date();
    this._description = '';
}
// class methods
Experiment.prototype.getIndividualName = function() {
    return this._individualName;
};
Experiment.prototype.getClassName = function() {
    return this._className;
};
Experiment.prototype.getIdentification = function() {
    return this._id;
};
Experiment.prototype.getDate = function() {
    return this._date;
};
Experiment.prototype.getDescription = function() {
    return this._description;
};


Experiment.prototype.setIdentification = function(value) {
    this._id = value;
};
Experiment.prototype.setDate = function(value) {
    this._date = value;
};
Experiment.prototype.setDescription = function(value) {
    this._description = value;
};
// export the class
module.exports = Experiment;

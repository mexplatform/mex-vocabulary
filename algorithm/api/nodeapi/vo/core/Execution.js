/**
 * Created by esteves on 11.07.15.
 */
var Util = require('../../util/mexconstant.js');

// constructor
function Execution(individualName, className, phaseID, algorithmID) {
    this._individualName = individualName;
    this._className = className;
    this._phaseID = phaseID;
    this._algorithmID = algorithmID;
    this._hyperparamIDList = [];
}
// class methods
Execution.prototype.getIndividualName = function() {
    return this._individualName;
};
Execution.prototype.getClassName = function() {
    return this._className;
};
Execution.prototype.getAlgorithmIndividual = function() {
    return this._algorithmID;
};
Execution.prototype.getPhase = function() {
    return this._phaseID;
};
Execution.prototype.getHyperParamters = function() {
    return this._hyperparamIDList;
};

Execution.prototype.addHyperParameterIndividual = function(value) {
    this._hyperparamIDList.push(value);
};
Execution.prototype.setAlgorithmIndividual = function(value) {
    this._algorithmID = value;
};
Execution.prototype.setPhase = function(value) {
    this._phaseID = value;
};
Execution.prototype.setIndividualName = function(value) {
    this._individualName = value;
};
Execution.prototype.setClassName = function(value) {
    this._className = value;
};
// export the class
module.exports = Execution;

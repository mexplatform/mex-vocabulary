var clsDataSet = require('./DataSet.js');
var clsSampling = require('./SamplingMethod.js');
var clsSoftwareImplementation = require('./../algo/Implementation.js');
var clsFeature = require('./Feature.js');
var clsHardware = require('./Hardware.js');
var clsAlgorithm = require('./../algo/Algorithm.js');
var clsHyperParameter = require('./../algo/HyperParameter.js');
var clsExecution = require('./Execution.js');
var clsUtil = require('../../util/mexconstant.js');

// constructor
function ExperimentConfiguration(instanceName, identification, description) {
    this._individualName = instanceName;
    this._className = clsUtil.DEF_CLASSES.MEX_CORE.EXP_CONFIGURATION;
    this._identification = identification;
    this._description = description;
    this._dataset = new clsDataSet();
    this._sampling = new clsSampling();
    this._hardware = new clsHardware();
    this._implementation = new clsSoftwareImplementation();
    this._featureList = [];
    this._algorithmList = [];
    this._executionList = [];
    this._hyperparamList = [];
}
// class methods
ExperimentConfiguration.prototype.getIndividualName = function() {
    return this._individualName;
};
ExperimentConfiguration.prototype.getClassName = function() {
    return this._className;
};
ExperimentConfiguration.prototype.setClassName = function(value) {
    this._className = value;
};
ExperimentConfiguration.prototype.getIdentification = function() {
    return this._identification;
};
ExperimentConfiguration.prototype.getDescription = function() {
    return this._description;
};
ExperimentConfiguration.prototype.getDataSet = function() {
    return this._dataset;
};
ExperimentConfiguration.prototype.getImplementation = function() {
    return this._implementation;
};
ExperimentConfiguration.prototype.getSamplingMethod = function() {
    return this._sampling;
};
ExperimentConfiguration.prototype.getHardware = function() {
    return this._hardware;
};
ExperimentConfiguration.prototype.getFeatures = function() {
    return this._featureList;
};
ExperimentConfiguration.prototype.getFeature = function(id) {
    for (var i = 0; i < this._featureList.length; i++) {
        if(this._featureList[i].get_id() == id){
            return this._featureList[i];
        }
    }
    return null;
};
ExperimentConfiguration.prototype.addFeature = function(id) {
    var instance = clsUtil.DEF_INDIVIDUALS.FEATURE + (this._featureList.length + 1);
    var f = new clsFeature(instance, id);
    this._featureList.push(f);
};
ExperimentConfiguration.prototype.addAlgorithm = function(className) {
    var instance = clsUtil.DEF_INDIVIDUALS.ALGORITHM + (this._algorithmList.length + 1);
    var a = new clsAlgorithm(instance, className);
    this._algorithmList.push(a);
    return instance;
};
ExperimentConfiguration.prototype.addExecution = function(executiontype, phase, algorithmID, hyperparam) {
    var instance = clsUtil.DEF_INDIVIDUALS.EXEC + (this._executionList.length + 1);
    var className = executiontype;
    var a = new clsExecution(instance, className, executiontype, phase);
    this._executionList.push(a);
};
ExperimentConfiguration.prototype.addHyperParameter = function(id, value) {
    var instance = clsUtil.DEF_INDIVIDUALS.HYPERPARAMETER + (this._hyperparamList.length + 1);
    var className = clsUtil.DEF_CLASSES.MEX_ALGO.HYPERPARAMETER;
    var a = new clsHyperParameter(instance, className, id, value);
    this._hyperparamList.push(a);
    return instance;
};


// export the class
module.exports = ExperimentConfiguration;


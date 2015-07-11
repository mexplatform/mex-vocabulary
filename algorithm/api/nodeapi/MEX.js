/**
 * Created by esteves on 11.07.15.
 */
var clsAppContext = require('./vo/core/ApplicationContext.js');
var clsExperiment = require('./vo/core/Experiment.js');
var clsConfiguration = require('./vo/core/experimentconfiguration.js');

// Constructor
function MEX() {
    this.myApplicationContext = new clsAppContext();
    this.myExperiment = new clsExperiment();
    this.myConfigurations = [];
}
// class methods

/* myApplicationContext */
MEX.prototype.AppContext_setUserName = function(value) {
    this.myApplicationContext.setUserName(value);
};
MEX.prototype.AppContext_setUserEmail = function(value) {
    this.myApplicationContext.setUserEmail(value);
};
MEX.prototype.AppContext_setContext = function(value) {
    this.myApplicationContext.getContext().setClassName(value);
};
MEX.prototype.AppContext_getUserName = function() {
    return this.myApplicationContext.getUserName();
};
MEX.prototype.AppContext_getUserEmail = function() {
    return this.myApplicationContext.getUserEmail();
};
MEX.prototype.AppContext_getContext = function() {
    return this.myApplicationContext.getContext().getClassName();
};
/* myExperiment */
MEX.prototype.Experiment_setId = function(value) {
    this.myExperiment.setIdentification(value);
};
MEX.prototype.Experiment_setDescription = function(value) {
    this.myExperiment.setDescription(value);
};
MEX.prototype.Experiment_setDate = function(value) {
    this.myExperiment.setDate(value);
};
MEX.prototype.Experiment_getId = function() {
    return this.myExperiment.getIdentification();
};
MEX.prototype.Experiment_getDescription = function() {
    return this.myExperiment.getDescription();
};
MEX.prototype.Experiment_getDate = function() {
    return this.myExperiment.getDate();
};
/* myConfigurations */
function getExperimentConfigurationIndex(id){
    for (var i = 0; i < this.myConfigurations.length; i++) {
        if(this.myConfigurations[i].get_individualName() == id){
            return i;
        }
    }
    return -1;
}
MEX.prototype.Configuration_add = function() {
    var instanceName;
    try{
        instanceName = util.DEF_INDIVIDUALS.EXP_CONFIGURATION + (this.myConfigurations.length + 1);
        var expconf = new clsConfiguration(instanceName);
        this.myConfigurations.push(expconf);
    }catch(e){
        console.log('error addConfiguration: ' + e);
    }
    return instanceName;
};

// export the class
module.exports = MEX;
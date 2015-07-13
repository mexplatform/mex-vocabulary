/**
 * Created by esteves on 11.07.15.
 */
var clsUtil = require('./util/mexconstant.js');
var clsAppContext = require('./vo/core/ApplicationContext.js');
var clsExperiment = require('./vo/core/Experiment.js');
var clsConfiguration = require('./vo/core/ExperimentConfiguration.js');

// Constructor
function MEX() {
    this.myApplicationContext = new clsAppContext();
    this.myExperiment = new clsExperiment();
    this.myConfigurations = []; //clsExperimentConfiguration -> SamplingMethod, Hardware, DataSet, Feature(s), Execution(s), Implementation
    //Execution -> Model, Phase, Example(s), Algorithm, Algorithm Parameter(s)
    //Performance -> Execution
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
function getExperimentConfigurationIndex(id, configurations){
    for (var i = 0; i < configurations.length; i++) {
        if(configurations[i].getIdentification() == id){
            return i;
        }
    }
    return -1;
}
MEX.prototype.Configuration_add = function(description) {
    var instanceName, identification;
    try{
        instanceName = clsUtil.DEF_INDIVIDUALS.EXP_CONFIGURATION + (this.myConfigurations.length + 1);
        identification = clsUtil.DEF_IDENTIFIERS.EXP_CONFIGURATION + (this.myConfigurations.length + 1);
        var ec = new clsConfiguration(instanceName, identification, description);
        this.myConfigurations.push(ec);
    }catch(e){
        console.log('error addConfiguration: ' + e);
    }
    return identification;
};

MEX.prototype.Configuration_setDataSet = function(idConfiguration, dsTitle, dsDescription, dsLandingPage) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration, this.myConfigurations);
        if (index!=-1){
            this.myConfigurations[index].getDataSet().setTitle(dsTitle);
            this.myConfigurations[index].getDataSet().setDescription(dsDescription);
            this.myConfigurations[index].getDataSet().setLandingPage(dsLandingPage);
            this.myConfigurations[index].getDataSet().setIndividualName(clsUtil.DEF_INDIVIDUALS.DATASET + idConfiguration);

        }
    }catch (e){
        console.log('error setDataSetName: ' + e);
    }
};

// export the class
module.exports = MEX;
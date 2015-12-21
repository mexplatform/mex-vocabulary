/* MEX */
var AppContextClass = require('./vo/core/ApplicationContext.js');
var ExperimentClass = require('./vo/core/Experiment.js');
var ExperimentConf  = require('./vo/core/ExperimentConfiguration.js');
var util            = require('./util/mexconstant.js');
var N3              = require('n3');
var app             = new AppContextClass();
var exp             = new ExperimentClass();
var expConfList     = [];
var valid           = false;
/* others */
var rdfsuri         = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
var provuri         = 'http://www.w3.org/ns/prov#';
var thisuri         = 'http://mex.aksw.org/examples/001#';
var dcturi          = 'http://purl.org/dc/terms/';
var foafuri         = 'http://xmlns.com/foaf/0.1/';
var mexcoreuri      = 'http://mex.aksw.org/mex-core#';
var mexalgouri      = 'http://mex.aksw.org/mex-algo#';
var mexperfuri      = 'http://mex.aksw.org/mex-perf#';
var owluri          = 'http://www.w3.org/2002/07/owl#';
var xsduri          = 'http://www.w3.org/2001/XMLSchema#';
var doapuri         = 'http://usefulinc.com/ns/doap#';
var dcaturi         = 'http://www.w3.org/ns/dcat#';
var dcuri           ='http://purl.org/dc/elements/1.1/';

/****************************** MEX-CORE ******************************/

//APP-CONTEXT
exports.setAuthorName = function(value) {
    app.set_name(value)
};
exports.setContext = function(value) {
    var c = app.get_context;
    c.className = value;
};
exports.setAuthorEmail = function(value) {
    app.set_email(value)
};
//EXPERIMENT
exports.setExperimentIdentification = function(value) {
    exp.set_id(value)
};
exports.setExperimentDescription = function(value) {
    exp.set_description(value)
};
exports.setExperimentDate = function(value) {
    exp.set_date(value)
};

function getExperimentConfigurationIndex(id){
    for (var i = 0; i < expConfList.length; i++) {
        if(expConfList[i].get_individualName() == id){
            return i;
        }
    }
    return -1;
}
exports.addExecution = function(idConfiguration, executiontype, phase, algorithm, hyperparam) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            expConfList[index].add_execution(executiontype, phase, algorithm, hyperparam);
        }
    }catch (e){
        console.log('error addExecution: ' + e);
    }
};
exports.addHyperParameter = function(idConfiguration, id, value) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            return expConfList[index].add_hyperparameter(id, value);
        }
    }catch (e){
        console.log('error addHyperParameter: ' + e);
    }
};
exports.addAlgorithm = function(idConfiguration, algorithm) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            return expConfList[index].add_algorithm(algorithm);
        }
    }catch (e){
        console.log('error addAlgorithm: ' + e);
    }
};
exports.addFeature = function(idConfiguration, id, value) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            expConfList[index].add_feature(id, value);
        }
    }catch (e){
        console.log('error addFeature: ' + e);
    }
};
exports.addFeatures = function(idConfiguration, values) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            for (var i = 0; i < values.length; i++) {
                expConfList[index].add_feature(expConfList[index].get_features.length + 1, values[i]);
            }

        }
    }catch (e){
        console.log('error addFeatures: ' + e);
    }
};
exports.setDataSetName = function(idConfiguration, dsName) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            expConfList[index].get_dataset.set_name = dsName;
        }
    }catch (e){
        console.log('error setDataSetName: ' + e);
    }
};
exports.setSoftwareImplementation = function(idConfiguration, software, version) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            expConfList[index].get_implementation.set_name = software;
            expConfList[index].get_implementation.set_revision = version;
        }
    }catch (e){
        console.log('error setSoftwareImplementation: ' + e);
    }
};
exports.setSamplingMethod = function(idConfiguration, samplingMethod, train, test) {
    var index;
    try{
        index = getExperimentConfigurationIndex(idConfiguration);
        if (index!=-1){
            expConfList[index].get_sampling.set_name = samplingMethod;
            expConfList[index].get_sampling.set_train_size = train;
            expConfList[index].get_sampling.set_test_size = test;
        }
    }catch (e){
        console.log('error setSamplingMethod: ' + e);
    }
};
exports.addConfiguration = function() {
    var instance;
    try{
        instance = util.DEF_INDIVIDUALS.EXP_CONFIGURATION + (expConfList.length + 1);
        var expconf = new ExperimentConf(instance);
        expConfList.push(expconf);
    }catch(e){
        console.log('error addConfiguration: ' + e);
    }
    return instance;
};



/****************************** MEX-ALGO ******************************/



/****************************** MEX-PERF ******************************/



/****************************** URIs ******************************/
var writer = N3.Writer({ prefixes: {'prov':provuri,
    'mexalgo':mexalgouri,
    'mexcore':mexcoreuri,
    'mexperf':mexperfuri,
    'this': thisuri,
    'owl': owluri,
    'xsd': xsduri,
    'dct': dcturi,
    'doap':doapuri,
    'dcat':dcaturi,
    'foaf':foafuri,
    'dc':dcuri,
    'rdfs':rdfsuri} });

exports.getApplicationContextEmail = function() {
    return app.get_email();};

exports.getListOfContext = function() {
    return enumContext};

exports.getApplicationContextName = function() {
    return app.get_name();};

/* UTIL */
function parse() {
    console.log('---------------------------------------------------------');
    console.log('              checking the mex file...                   ');
    console.log('---------------------------------------------------------');
    if (!app.get_name()) {
        console.log('parsing error: please inform the name of the author');
        return;
    }
    if (!app.get_context.get_className()) {
        console.log('parsing error: please set the context');
        return;
    }
    valid = true;
    console.log('valid!');
}

exports.generateMEX = function() {
    parse(this);
	if (valid){
		console.log('---------------------------------------------------------');
		console.log('           starting the mex file generation...           ');
		console.log('---------------------------------------------------------');

		/* variables */


		var sAppCont = thisuri + util.DEF_INDIVIDUALS.CONTEXT;
		var today = new Date();

		/************************************************
							MEX-CORE
		*************************************************/
		/* app context */
		writer.addTriple(thisuri + app.get_individualName(), rdfsuri + 'type', mexcoreuri + util.DEF_CLASSES.MEX_CORE.APPLICATION_CONTEXT);
		writer.addTriple(thisuri + app.get_individualName(), rdfsuri + 'type', provuri + util.DEF_CLASSES.PROV.PERSON);
		writer.addTriple(thisuri + app.get_individualName(), rdfsuri + 'type', provuri + util.DEF_CLASSES.PROV.AGENT);
		writer.addTriple(thisuri + app.get_individualName(), rdfsuri + 'type', provuri + util.DEF_CLASSES.PROV.ORGANIZATION);

		writer.addTriple(thisuri + app.get_individualName(), dcturi + 'dateCopyrighted', '"' + today + '"');
		if (app.get_name()){writer.addTriple(thisuri + app.get_individualName(), foafuri + 'givenName', '"' + app.get_name() + '"');}
		if (app.get_email()){writer.addTriple(thisuri + app.get_individualName(), foafuri + 'mbox', '"' + app.get_email() + '"');}
		
		/* context */
		writer.addTriple(sAppCont, rdfsuri + 'type', mexcoreuri + app.get_context.get_className());
		writer.addTriple(sAppCont, rdfsuri + 'type', provuri + util.DEF_CLASSES.PROV.ENTITY);

		/************************************************
							MEX-ALGO
		*************************************************/

		/* serialize */
		writer.end(function (error, result) { console.log(result); });

		console.log('---------------------------------------------------------');
		console.log('           			 done!                            ');
		console.log('---------------------------------------------------------');

	}
};
/**
 * Created by esteves on 11.07.15.
 * The simplest example for MEX using the Node.JS Library
 */
/* variables */
var Util = require('./util/mexconstant.js');
var MEX = require('./MEX.js');
var MEXParser = require('./MEXSerializer.js');
var myMEX           = new MEX();
var mexSerializer   = new MEXParser();

try{
    /* 01 - the basic information for your context */
    myMEX.AppContext_setUserName('D.Esteves');
    myMEX.AppContext_setUserEmail('esteves@informatik.uni-leipzig.de');
    myMEX.AppContext_setContext(Util.DEF_CLASSES.MEX_CORE.CONTEXT.FINANCE);
    myMEX.Experiment_setId('EX001');
    myMEX.Experiment_setDescription('example 001');
    myMEX.Experiment_setDate(new Date('02-06-2014'));

    /* 02 - grouping your executions into logical sets */
    var idconf1 = myMEX.Configuration_add('my logical group 001');
    var idconf2 = myMEX.Configuration_add('my logical group 002');

    myMEX.Configuration_setDataSet(idconf1, 'dsIBOV-2012-2013', 'this ds covers...', 'http://www.bovespa.com.br/ds001');
    //continuar aqui
    myMEX.Configuration_setSoftwareImplementation(idconf1, Util.DEF_CLASSES.MEX_ALGO.IMPLEMENTATION.WEKA, '3.6.6');
    myMEX.Configuration_setSamplingMethod(idconf1, Util.DEF_CLASSES.MEX_CORE.SAMPLING_METHOD.CROSS_VALIDATION, 10, 0.8, 0.2);
    myMEX.Configuration_setHardware(idconf1, 'ubuntu x64 14.04', 'i7', '16GB', 'SSD', '3MB');
    myMEX.Configuration_addFeatures(idconf1, 'f1', 'f2', 'f3', 'f4');

    var model1 = myMEX.Configuration_addAlgorithm(idconf1, Util.DEF_CLASSES.MEX_ALGO.ALGORITHM.SVM_REGRESSION);
    myMEX.Configuration_addAlgorithmParameter(idconf1, model1, 'C', '10^-3');
    myMEX.Configuration_addAlgorithmParameter(idconf1, model1, 'alpha', '0.002');

    /* 03 - executions */
    var idexec1 = myMEX.Execution.add(idconf1, Util.DEF_CLASSES.MEX_CORE.EXECUTION.OVERALL, model1, 1233, 1577);
    myMEX.Execution.setStartDate(idexec1, new Date());
        // your model's call here...
    myMEX.Execution.setEndDate(idexec1, new Date());

    /* 04 - execution's performance */
    myMEX.Execution.addPerformance(idexec1, Util.DEF_IDENTIFIERS.MEX_PERF.ACCURACY, 0.96);
    myMEX.Execution.addPerformance(idexec1, Util.DEF_IDENTIFIERS.MEX_PERF.ERROR, 0.04);

    /* 05 - export your data! */
    mexSerializer.generateMEX(myMEX);

}catch(e){
    console.log('error: ' + e.toString());
}
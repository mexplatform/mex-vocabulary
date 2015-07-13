var mex = require('./mexapi.js');
var util = require('./util/mexconstant.js');

try {

    mex.setAuthorName('D.Esteves');
    mex.setAuthorEmail('esteves@informatik.uni-leipzig.de');
    mex.setContext(util.DEF_CLASSES.MEX_CORE.CONTEXT.FINANCE);

    mex.setExperimentIdentification('001');
    mex.setExperimentDescription('nodejs wrapper example');
    mex.setExperimentDate(new Date('2014-10-13'));

    var idconf1 = mex.addConfiguration();
    mex.setDataSetName(idconf1, 'mydataset1');
    mex.addFeatures(idconf1, ['open', 'close', 'min', 'max']);
    mex.setSamplingMethod(idconf1, util.DEF_CLASSES.MEX_CORE.SAMPLING_METHOD.HOLDOUT);
    mex.setSoftwareImplementation(idconf1,util.DEF_CLASSES.MEX_ALGO.IMPLEMENTATION.WEKA, '3.6.6');

    var idalgo1 = mex.addAlgorithm(idconf1, util.DEF_CLASSES.MEX_ALGO.ALGORITHM.SVM_CLASSIFICATION);
    var idalgo2 = mex.addAlgorithm(idconf1, util.DEF_CLASSES.MEX_ALGO.ALGORITHM.BAYESTHEORY);

    var idh1 = mex.addHyperParameter(idconf1, 'C', '10^3');
    var idh2 = mex.addHyperParameter(idconf1, 'alpha', '0.2');

    var exec1 = mex.addExecution(idconf1, util.DEF_CLASSES.MEX_CORE.EXECUTION.OVERALL, util.DEF_INDIVIDUALS.EXEC.PHASE_TEST, idalgo1, [idh1, idh2]);
    var exec2 = mex.addExecution(idconf1, util.DEF_CLASSES.MEX_CORE.EXECUTION.OVERALL, util.DEF_INDIVIDUALS.EXEC.PHASE_TEST, idalgo2);

    mex.generateMEX();

}catch(err){
    console.log(err);
}





var mex = require('./mexapi.js');
var enums = require('./mexapi_enum.js');

try {

    mex.setAuthorName('D.Esteves');
    mex.setAuthorEmail('esteves@informatik.uni-leipzig.de');
    mex.setContext(enums.MEX_CORE_CONTEXT.Finance);

    mex.setExperimentIdentification('001');
    mex.setExperimentDescription('nodejs wrapper example');
    mex.setExperimentDate(new Date('2014-10-13'));

    mex.generateMEX();

}catch(err){
    console.log('error: ' + err);
}





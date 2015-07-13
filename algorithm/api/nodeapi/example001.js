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

/* basic information for your context */
myMEX.AppContext_setUserName('D.Esteves');
myMEX.AppContext_setUserEmail('esteves@informatik.uni-leipzig.de');
myMEX.AppContext_setContext(Util.DEF_CLASSES.MEX_CORE.CONTEXT.FINANCE);
myMEX.Experiment_setId('EX001');
myMEX.Experiment_setDescription('example 001');
myMEX.Experiment_setDate(new Date('02-06-2014'));

/* grouping your executions into logical sets */
var idconf1 = myMEX.Configuration_add('my logical group 001');
var idconf2 = myMEX.Configuration_add('my logical group 002');

myMEX.Configuration_setDataSetName(idconf1, 'mydataset');



mexSerializer.generateMEX(myMEX);

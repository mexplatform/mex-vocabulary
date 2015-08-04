var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var fs = require('fs');
var N3 = require('n3');

var parser = N3.Parser();

var routes = require('./routes/index');
var makeLatexTable = require('./routes/makeLatexTable');
var file = require('./routes/file');

var app = express();

var multer  = require('multer')

// view engine setup
 app.set('views', path.join(__dirname, 'views'));
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'ejs');



app.use(multer({ dest: './uploads/',
  onFileUploadComplete: function (file, req, res) {
    console.log(file.fieldname + ' uploaded to  ' + file.path);
  loadTriples(file.path, function(data){
    console.log(JSON.stringify(data));
    res.send(JSON.stringify(data));
    store = {};
    store = N3.Store();
  }
  );
  

}}))


// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(express.static(process.cwd() + '/public'));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
// app.use(multer({ dest: './uploads/',
// onFileUploadComplete: function (file, req, res) {
//   console.log(file.fieldname + ' uploaded to  ' + file.path);
// }}))

app.use('/', routes);
app.use('/makeLatexTable', makeLatexTable);
app.use('/file', file);





























var store = N3.Store();


var mexCoreURI = "http://mex.aksw.org/mex-core#";
var mexPerf = "http://mex.aksw.org/mex-perf#";
var provURI  = "http://www.w3.org/ns/prov#";
var rdfsURI = "http://www.w3.org/2000/01/rdf-schema#"; 
var dctURI = "http://purl.org/dc/terms/";
var rdfURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

var maxPerfMeasures = [mexPerf+"ClassificationMeasure", 
  mexPerf+"PerformanceMeasure", 
  mexPerf+"RegressionMeasure", 
  mexPerf+"ClusteringMeasure", 
  mexPerf+"StatisticalMeasure" ]; 
  
var measuresProp = [
  mexPerf+"pearsonCorrelation",
  mexPerf+  "chiSquare",
  mexPerf+"error",
  mexPerf+"kolmogorovSmirnov",
  mexPerf+"mean",
  mexPerf+"nemenyi",
  mexPerf+"standardDeviation",
  mexPerf+"wilCoxon",
  mexPerf+"variance",
  mexPerf+"friedman",
  mexPerf+"kappaStatistics",
  mexPerf+"median",
  mexPerf+"mode",
  mexPerf+"L2norm",
  mexPerf+"L1norm",
  mexPerf+"Linfnorm",
// ];

// var regressionMeasure = [
  mexPerf+"meanAbsoluteDeviation",
  mexPerf+"meanSquareError",
  mexPerf+"residual",
  mexPerf+"totalError",
  mexPerf+"medianAbsoluteDeviation",
  mexPerf+"relativeAbsoluteError",
  mexPerf+"rootRelativeSquaredError",
  mexPerf+"rootMeanSquaredError",
  mexPerf+"correlationCoefficient",
// ];

// var clusteringMeasure = [
  mexPerf+"chebyschevDistance",
  mexPerf+"hammingDistance",
  mexPerf+"euclideanDistance",
  mexPerf+"manhattanDistance",
  mexPerf+"genSimilarityCoerfficient",
// ];

// var classificationMeasure = [
  mexPerf+"falseNegative",
  mexPerf+"falsePositive",
  mexPerf+"truePositive",
  mexPerf+"trueNegative",
  mexPerf+ "precision",
  mexPerf+"fmeasure",
  mexPerf+"recall",
  mexPerf+"roc",
  mexPerf+"sensivity",
  mexPerf+"specificity",
  mexPerf+"falseNegativeRate",
  mexPerf+"falsePositiveRate",
  mexPerf+"trueNegativeRate",
  mexPerf+"truePositiveRate",
  mexPerf+"accuracy",
];
  
  

var experimentConfigurationURI = mexCoreURI+ "ExperimentConfiguration";
var algorithmURI = mexCoreURI+"Algorithm";
var wasInformedByURI = provURI + "wasInformedBy";
var usedURI = provURI + "used";
var identifierURI = dctURI + "identifier";

var aURI = rdfURI+"type";
var labelURI = rdfsURI+"label";
var wasGeneratedByURI = provURI+"wasGeneratedBy"; 






var RDFFilePath;
var tableTitle;


function getSubjects(triples){
  var subjects = [];
  for (var i in triples){
    subjects.push(triples[i].subject);
  }
  return subjects;
}

function getObjects(triples){
  var objects = [];
  for (var i in triples){
    objects.push(triples[i].object);
  }
  return objects;
}


function loadTriples(fileName, callback){
   fs.readFile(fileName, "utf8", function (err, data) {
      if (err) throw err;
      parser.parse(data,
          function (error, triple, prefixes) {
            if (triple){
              store.addTriple(triple.subject, triple.predicate, triple.object);
            }
            else{
              return callback(loadRDF2());
            }
          });
             
       
    }); 
}


//=============================

var loadRDF2 = function(){
  
  
  var experimentsConfiguration = []; 
  for (var experimentConfigurationURL in getExperimentsConfiguration()){
    var experimentConfiguration = {};
    experimentConfiguration.URL=getExperimentsConfiguration()[experimentConfigurationURL];
    experimentConfiguration.label = getObjects(store.find(experimentConfiguration.URL, labelURI, null))[0].replace(/\"/g, ""); 
    experimentConfiguration.executions = [];
    
    for(var executionOfExperimentURL in getExecutionsOfExperiment(experimentConfiguration.URL)){
      var executionExperiment = {}; 
      executionExperiment.executionPerformance = getSubjects(store.find(null, wasInformedByURI, getExecutionsOfExperiment(experimentConfiguration.URL)[executionOfExperimentURL]))[0]; 
      executionExperiment.label = getObjects(store.find(getExecutionsOfExperiment(experimentConfiguration.URL)[executionOfExperimentURL], labelURI,null))[0].replace(/\"/g, ""); 
      executionExperiment.measureURI = getSubjects(store.find(null, wasGeneratedByURI , executionExperiment.executionPerformance))[0]; 
      // executionExperiment.label = getObjects(store.find(null, wasGeneratedByURI , executionExperiment.executionPerformance))[0]; 
      executionExperiment.measures = [];
      
      for(var measure in measuresProp){
        var measureTMP = getObjects(store.find(executionExperiment.measureURI,measuresProp[measure], null))[0]; 
        if (typeof measureTMP != 'undefined'){
        // console.log(measureTMP);
          var measureTMP2 = {};
          measureTMP2.prop = measuresProp[measure].replace("http://mex.aksw.org/mex-perf#", ""); 
          measureTMP2.val = measureTMP.replace("^^http://www.w3.org/2001/XMLSchema#float","");
          executionExperiment.measures.push(measureTMP2);
        }
      }
      experimentConfiguration.executions.push(executionExperiment);
       
    }
    
    
    experimentsConfiguration.push(experimentConfiguration);
    
  }
  
  return experimentsConfiguration; 
  
  
};




function getExecutionsOfExperiment(experiment){
  return getSubjects(store.find(null, wasInformedByURI, experiment)); 
};


function getExperimentsConfiguration(){
  return getSubjects(store.find(null, aURI, experimentConfigurationURI)); 
}; 


module.exports = app;

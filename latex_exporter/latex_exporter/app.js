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
  mexPerf+  "precision",
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
  
  

var experimentConfigurationURI = "ExperimentConfiguration";
var algorithmURI = mexCoreURI+"Algorithm";
var wasInformedByURI = provURI + "wasInformedBy";
var usedURI = provURI + "used";
var identifierURI = dctURI + "identifier";

var aURI = rdfsURI+"type";






var RDFFilePath;
var tableTitle;



function loadRDF(){
  
  var algorithms;
var executions = []; 
var measures = [];
var wasInformedBy = [];

var line = {};
  
  // get all measures
   measures = (getSubjects(findTriplesBasedOnObjects(maxPerfMeasures)));
   
   for(var m in measures){
     
     var measureObj = findTriplesBasedOnSubjectAndPredicates(measures[m], measuresProp);
     // var measureObjValues = [];
     var measureObjValues;
     
     // store measure values
     for(var mv in measureObj){
       var measureObjValuesTmp = {};
       measureObjValuesTmp.prop = measureObj[mv].predicate;
       measureObjValuesTmp.val = measureObj[mv].object;
       // measureObjValues.push(measureObjValuesTmp);
       measureObjValues =measureObjValuesTmp;
     }
     
     executions = getExecutionOfMeasure(measures[m]); 
     
     for (var e in executions){ 
       
       algorithms = getAlgorithmsOfExecution(executions[e]);
       
       for (var a in algorithms){
         if(typeof line[algorithms[a]] != 'undefined'){
           line[algorithms[a]].measures.push (measureObjValues);
         }
         else{
           line[algorithms[a]] = {}; 
           line[algorithms[a]].name = algorithms[a];
           line[algorithms[a]].identifier = getAlgorithmIdentifier(algorithms[a]);
           line[algorithms[a]].measures = [];
           
         }
       } 
     }
   }
   return line;
}


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

function findTriplesBasedOnObjects(objects){
  var triples = [];
  var triplesTMP;
  for (var i in objects){
    triplesTMP = store.find(null, null , objects[i]);
    for(var v in triplesTMP){
      triples.push(triplesTMP[v]);
    }
  }
  return triples; 
}

function findTriplesBasedOnSubjectAndPredicates(subject, predicates){
  var triples = [];
  var triplesTMP;
  for (var i in predicates){
    triplesTMP = store.find(subject, predicates[i] , null);
    for(var v in triplesTMP){
      triples.push(triplesTMP[v]);
    }
  }
  return triples; 
}

function getExecutionOfMeasure(measure){
  return getObjects(store.find(measure, wasInformedByURI , null));
}

function getAlgorithmsOfExecution(execution){
  return getObjects(store.find(execution, usedURI , null));
}

function getAlgorithmIdentifier(algorithm){
  return getObjects(store.find(algorithm, identifierURI , null));
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
              return callback(loadRDF());
            }
          });
             
       
    }); 
}


























module.exports = app;

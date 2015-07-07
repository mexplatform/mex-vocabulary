/* MEX */
var AppContext = require('./core-appcontext.js');
var app = new AppContext('Esteves', '');

/* others */
var N3 = require('n3');

var mexcoreuri = 'http://mex.aksw.org/mex-core#';
var rdfsuri = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';
var provuri = 'http://www.w3.org/ns/prov#';
var thisuri = 'http://mex.aksw.org/examples/001#';
var dcturi = 'http://purl.org/dc/terms/';
var foafuri = 'http://xmlns.com/foaf/0.1/';

var writer = N3.Writer({ prefixes: {'prov':provuri,
                                    'mexalgo':'http://mex.aksw.org/mex-algo#',
                                    'mexcore':mexcoreuri,
                                    'mexperf':'http://mex.aksw.org/mex-perf#',
                                    'this': thisuri,
                                    'owl':'http://www.w3.org/2002/07/owl#',
                                    'xsd':'http://www.w3.org/2001/XMLSchema#',
                                    'dct': dcturi,
                                    'doap':'http://usefulinc.com/ns/doap#',
                                    'dcat':'http://www.w3.org/ns/dcat#',
                                    'foaf':foafuri,
                                    'dc':'http://purl.org/dc/elements/1.1/',
                                    'rdfs':rdfsuri} });

exports.getApplicationContextName = function() {
    return app.get_name();
};

exports.setApplicationContextName = function(value) {
    app.set_name(value);
};

exports.getApplicationContextEmail = function() {
    return app.get_email();
};

exports.setApplicationContextEmail = function(value) {
    app.set_email(value);
};

exports.parseMEX = function() {
	return true;
};


exports.generateMEX = function() {
	/* app context */
	var sApp = thisuri + 'app';
	var today = new Date();
	writer.addTriple(sApp, rdfsuri + 'type', mexcoreuri + 'ApplicatonContext');
	writer.addTriple(sApp, rdfsuri + 'type', provuri + 'Person');
	writer.addTriple(sApp, rdfsuri + 'type', provuri + 'Agent');
	writer.addTriple(sApp, rdfsuri + 'type', provuri + 'Organization');
	writer.addTriple({
	    subject:   sApp,
	    predicate: dcturi + 'dateCopyrighted',
	    object:    '"' + today + '"'
	});
	writer.addTriple(sApp, foafuri + 'givenName', '"' + app.get_name() + '"');
	writer.end(function (error, result) { console.log(result); });
};
/**
 * Created by esteves on 10.07.15.
 */
var clsMEX          = require('./MEX.js');
var N3              = require('n3');
var Util            = require('./util/mexconstant.js');
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
var dcuri           = 'http://purl.org/dc/elements/1.1/';

// Constructor
function MEXSerializer() {
    this.mex = new clsMEX();
}
// class methods
function validateMEX(mex) {
    console.log(mex.AppContext_getUserName());
    console.log(mex.AppContext_getUserEmail());
    console.log(mex.AppContext_getContext());
    console.log(mex.Experiment_getId());
    console.log(mex.Experiment_getDescription());
    console.log(mex.Experiment_getDate());
    console.log(mex.Experiment_getDate());
    return true;
}

MEXSerializer.prototype.generateMEX = function(mex) {

    try{
        if (validateMEX(mex)){
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
        console.log('---------------------------------------------------------');
        console.log('           starting the mex file generation...           ');
        console.log('---------------------------------------------------------');

        var today = new Date();
        /************************************************
         MEX-CORE
         *************************************************/
        /* app context */
        writer.addTriple(thisuri + mex.myApplicationContext.getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myApplicationContext.getClassName());
        writer.addTriple(thisuri + mex.myApplicationContext.getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.PERSON);
        writer.addTriple(thisuri + mex.myApplicationContext.getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.AGENT);
        writer.addTriple(thisuri + mex.myApplicationContext.getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ORGANIZATION);

        writer.addTriple(thisuri + mex.myApplicationContext.getIndividualName(), dcturi + 'dateCopyrighted', '"' + today + '"');
        if (mex.myApplicationContext.getUserName()){
            writer.addTriple(thisuri + mex.myApplicationContext.getIndividualName(), foafuri + 'givenName', '"' + mex.myApplicationContext.getUserName() + '"');}
        if (mex.myApplicationContext.getUserEmail()){
            writer.addTriple(thisuri + mex.myApplicationContext.getIndividualName(), foafuri + 'mbox', '"' + mex.myApplicationContext.getUserEmail() + '"');}

        /* context */
        if (mex.myApplicationContext.getContext().getIndividualName()) {
            writer.addTriple(thisuri + mex.myApplicationContext.getContext().getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myApplicationContext.getContext().getClassName());
            writer.addTriple(thisuri + mex.myApplicationContext.getContext().getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ENTITY);
            writer.addTriple(thisuri + mex.myApplicationContext.getContext().getIndividualName(), provuri + 'wasAttributedTo', thisuri + mex.myApplicationContext.getIndividualName());
        }
        /* experiment */
        if (mex.myExperiment.getIndividualName()){
            writer.addTriple(thisuri + mex.myExperiment.getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myExperiment.getClassName());
            writer.addTriple(thisuri + mex.myExperiment.getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ENTITY);
            if (mex.myExperiment.getIdentification()){
                writer.addTriple(thisuri + mex.myExperiment.getIndividualName(), dcturi + 'identifier', '"' + mex.myExperiment.getIdentification() + '"');}
            if (mex.myExperiment.getDate()){
                writer.addTriple(thisuri + mex.myExperiment.getIndividualName(), dcturi + 'date', '"' + mex.myExperiment.getDate() + '"');}
            if (mex.myExperiment.getDescription()){
                writer.addTriple(thisuri + mex.myExperiment.getIndividualName(), dcturi + 'description', '"' + mex.myExperiment.getDescription() + '"');}
            writer.addTriple(thisuri + mex.myExperiment.getIndividualName(), provuri + 'wasAttributedTo', thisuri + mex.myApplicationContext.getIndividualName());
        }
        /* configuration */
        if (mex.myConfigurations != null){
            for (i= 0 ; i < mex.myConfigurations.length; i++){
                writer.addTriple(thisuri + mex.myConfigurations[i].getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myConfigurations[i].getClassName());
                writer.addTriple(thisuri + mex.myConfigurations[i].getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ACTIVITY);
                if (mex.myConfigurations[i].getIdentification()){
                    writer.addTriple(thisuri + mex.myConfigurations[i].getIndividualName(), dcturi + 'identifier', '"' + mex.myConfigurations[i].getIdentification() + '"');}
                if (mex.myConfigurations[i].getDescription()){
                    writer.addTriple(thisuri + mex.myConfigurations[i].getIndividualName(), dcturi + 'description', '"' + mex.myConfigurations[i].getDescription() + '"');}
                /* sampling method */
                if (mex.myConfigurations[i].getSamplingMethod() && mex.myConfigurations[i].getSamplingMethod().getIndividualName()){
                    writer.addTriple(thisuri + mex.myConfigurations[i].getSamplingMethod().getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myConfigurations[i].getSamplingMethod().getClassName());
                    writer.addTriple(thisuri + mex.myConfigurations[i].getSamplingMethod().getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ENTITY);
                }
                /* hardware */
                if (mex.myConfigurations[i].getHardware() && mex.myConfigurations[i].getHardware().getIndividualName()){
                    writer.addTriple(thisuri + mex.myConfigurations[i].getHardware().getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myConfigurations[i].getHardware().getClassName());
                    writer.addTriple(thisuri + mex.myConfigurations[i].getHardware().getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ENTITY);
                }
                /* features */
                if (mex.myConfigurations[i].getFeatures()){
                    for (j= 0 ; j < mex.myConfigurations[i].getFeatures().length; j++){
                        writer.addTriple(thisuri + mex.myConfigurations[i].getFeature(j).getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myConfigurations[i].getFeature().getClassName());
                        writer.addTriple(thisuri + mex.myConfigurations[i].getFeature(j).getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ENTITY);
                    }
                }
                /* dataset */
                if (mex.myConfigurations[i].getDataSet() && mex.myConfigurations[i].getDataSet().getIndividualName()){
                    writer.addTriple(thisuri + mex.myConfigurations[i].getDataSet().getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myConfigurations[i].getDataSet().getClassName());
                    writer.addTriple(thisuri + mex.myConfigurations[i].getDataSet().getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ENTITY);

                    if (mex.myConfigurations[i].getDataSet().getTitle()){
                        writer.addTriple(thisuri + mex.myConfigurations[i].getDataSet().getIndividualName(), dcaturi + 'title', '"' + mex.myConfigurations[i].getDataSet().getTitle() + '"');}
                    if (mex.myConfigurations[i].getDataSet().getDescription()){
                        writer.addTriple(thisuri + mex.myConfigurations[i].getDataSet().getIndividualName(), dcturi + 'description', '"' + mex.myConfigurations[i].getDataSet().getDescription() + '"');}
                    if (mex.myConfigurations[i].getDataSet().getLandingPage()){
                        writer.addTriple(thisuri + mex.myConfigurations[i].getDataSet().getIndividualName(), dcaturi + 'landingPage', '"' + mex.myConfigurations[i].getDataSet().getLandingPage() + '"');}

                }
                /* implementation */
                if (mex.myConfigurations[i].getImplementation() && mex.myConfigurations[i].getImplementation().getIndividualName()){
                    writer.addTriple(thisuri + mex.myConfigurations[i].getImplementation().getIndividualName(), rdfsuri + 'type', mexcoreuri + mex.myConfigurations[i].getImplementation().getClassName());
                    writer.addTriple(thisuri + mex.myConfigurations[i].getImplementation().getIndividualName(), rdfsuri + 'type', provuri + Util.DEF_CLASSES.PROV.ENTITY);
                }
            }
        }
        /* executions */



        /************************************************
         MEX-ALGO
         *************************************************/

        /* serialize */
        writer.end(function (error, result) { console.log(result); });

        console.log('---------------------------------------------------------');
        console.log('        mex file has been successfully generated!        ');
        console.log('---------------------------------------------------------');



    }
    }catch(e){
        console.log('error addConfiguration: ' + e);
    }

};
// export the class
module.exports = MEXSerializer;
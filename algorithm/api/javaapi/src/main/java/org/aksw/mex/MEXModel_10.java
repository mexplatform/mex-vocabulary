package org.aksw.mex;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.*;
import org.aksw.mex.core.ExperimentConfigurationVO;
import org.aksw.mex.util.Constants;
import org.aksw.mex.util.ontology.*;
import org.aksw.mex.util.ontology.mex.MEXALGO_10;
import org.aksw.mex.util.ontology.mex.MEXCORE_10;
import org.aksw.mex.util.ontology.mex.MEXPERF_10;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by esteves on 25.06.15.
 */
public class MEXModel_10 {
    private boolean _valid;

    private static MEXModel_10 instance = null;
    protected MEXModel_10() {
        _valid=false;
    }
    public static MEXModel_10 getInstance() {
        if(instance == null) {
            instance = new MEXModel_10();
        }
        return instance;
    }
    public boolean parse(){
        if (1==1) {
            _valid = true;
        }
        return _valid;
    }

    public void saveToDisk(String filename, String URIbase, MyMEX_10 mex){
        if (_valid){
            writeJena(filename, URIbase, mex);
        }
    }

    private void setHeaders(Model model, String URIbase){
        //setting the headers
        model.setNsPrefix(PROVO.PREFIX, PROVO.NS);
        model.setNsPrefix(MEXCORE_10.PREFIX, MEXCORE_10.NS);
        model.setNsPrefix(MEXPERF_10.PREFIX, MEXPERF_10.NS);
        model.setNsPrefix(MEXALGO_10.PREFIX, MEXALGO_10.NS);
        model.setNsPrefix("dc", DC_11.NS);
        model.setNsPrefix("owl", OWL.NS);
        model.setNsPrefix("this", URIbase);
        model.setNsPrefix("foaf", FOAF.NS);
        model.setNsPrefix("rdfs", RDFS.getURI());
        model.setNsPrefix("xsd", XSD.getURI());
        model.setNsPrefix("dct", DCTerms.NS);
        model.setNsPrefix("doap", DOAP.getURI());
        model.setNsPrefix("dcat", DCAT.getURI());
    }

    private void writeJena(String filename, String URIbase, MyMEX_10 mex){

        Model model = ModelFactory.createDefaultModel();
        setHeaders(model, URIbase);

        //common resources
        Resource provAgent = model.createResource(PROVO.NS + PROVO.ClasseTypes.AGENT);
        Resource provPerson = model.createResource(PROVO.NS + PROVO.ClasseTypes.PERSON);
        Resource provOrganization = model.createResource(PROVO.NS + PROVO.ClasseTypes.ORGANIZATION);
        Resource provEntity = model.createResource(PROVO.NS + PROVO.ClasseTypes.ENTITY);
        Resource provActivity = model.createResource(PROVO.NS + PROVO.ClasseTypes.ACTIVITY);

        Resource mexcore_APC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT);
        Resource mexcore_EXP_HEADER = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT);
        Resource mexcore_EXP_CONF = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT_CONFIGURATION);
        Resource mexcore_MODEL = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.MODEL);

        Resource mexcore_HARDWARE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.HARDWARE_CONFIGURATION);
        Resource mexcore_DATASET = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.DATASET);
        Resource mexcore_EXEC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXECUTION);
        Resource mexcore_FEATURE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.FEATURE);
        Resource mexcore_PHASE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.PHASE);

        Resource mexalgo_IMPLEMENTATION = model.createResource(MEXCORE_10.NS + MEXALGO_10.ClasseTypes.IMPLEMENTATION);
        Resource mexalgo_ALGO = model.createResource(MEXCORE_10.NS + MEXALGO_10.ClasseTypes.ALGORITHM);
        Resource mexalgo_ALGO_PARAM = model.createResource(MEXCORE_10.NS + MEXALGO_10.ClasseTypes.ALGORITHM_PARAMETER);


        //mex-core
        Resource _application = null;
        Resource _context;
        Resource _organization;
        Resource _expHeader;

        //gets
        if (mex.getApplicationContext() != null){
            _application = model.createResource(URIbase + "application")
                    .addProperty(RDF.type, provAgent)
                    .addProperty(RDF.type, provPerson)
                    .addProperty(RDF.type, provOrganization)
                    .addProperty(RDF.type, mexcore_APC)
                    .addProperty(DCTerms.dateCopyrighted, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(mex.getApplicationContext().get_fileDate()));

            if (mex.getApplicationContext().get_givenName() != null) {
                _application.addProperty(FOAF.givenName, mex.getApplicationContext().get_givenName());}
            if (mex.getApplicationContext().get_mbox() != null) {
                _application.addProperty(FOAF.mbox, mex.getApplicationContext().get_mbox());}

            if (mex.getApplicationContext().get_homepage() != null) {
                _application.addProperty(DOAP.homepage, mex.getApplicationContext().get_givenName());}
            if (mex.getApplicationContext().get_description() != null) {
                _application.addProperty(DOAP.description, mex.getApplicationContext().get_mbox());}
            if (mex.getApplicationContext().get_category() != null) {
                _application.addProperty(DOAP.category, mex.getApplicationContext().get_givenName());}
            if (mex.getApplicationContext().get_location() != null) {
                _application.addProperty(DOAP.location, mex.getApplicationContext().get_mbox());}

            if (mex.getApplicationContext().get_trustyURI() != null) {
                _application.addProperty(MEXCORE_10.trustyURI, mex.getApplicationContext().get_mbox());}

            if (mex.getApplicationContext().get_organization() != null) {
                Resource mexcore_ORG = model.createResource(MEXCORE_10.NS + mex.getApplicationContext().get_organization());
                _organization = model.createResource(URIbase + "organization")
                        .addProperty(RDF.type, provAgent)
                        .addProperty(RDF.type, provOrganization)
                        .addProperty(FOAF.givenName, mex.getApplicationContext().get_organization());}

            if (mex.getApplicationContext().getContext() != null) {
                Resource mexcore_CON = model.createResource(MEXCORE_10.NS + mex.getApplicationContext().getContext().get_context());
                _context = model.createResource(URIbase+ "context")
                        .addProperty(RDF.type, provEntity)
                        .addProperty(RDF.type, mexcore_CON)
                        .addProperty(PROVO.wasAttributedTo, _application);}
        }

        //EXPERIMENT
        if (mex.getExperiment() != null) {
            _expHeader = model.createResource(URIbase + "experiment-header")
                    .addProperty(RDF.type, provEntity)
                    .addProperty(RDF.type, mexcore_EXP_HEADER)
                    .addProperty(DCTerms.identifier, mex.getExperiment().get_id())
                    .addProperty(DCTerms.title,mex.getExperiment().get_title())
                    .addProperty(DCTerms.date, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(mex.getExperiment().get_date()))
                    .addProperty(DCTerms.description, mex.getExperiment().get_description());

            if (mex.getApplicationContext() != null) {
                _expHeader.addProperty(PROVO.wasAttributedTo, _application);
            }

        }
        //EXPERIMENT CONFIGURATION
        if (mex.getExperimentConfigurations() != null) {
            int aux = 1;
            for(Iterator<ExperimentConfigurationVO> i = mex.getExperimentConfigurations().iterator(); i.hasNext(); ) {
                ExperimentConfigurationVO item = i.next();

                Resource _expConfiguration = model.createResource(URIbase + "configuration" + aux)
                        .addProperty(RDF.type, provActivity)
                        .addProperty(RDF.type, mexcore_EXP_CONF)
                        .addProperty(DCTerms.identifier, item.getId())
                        .addProperty(DCTerms.description, item.getDescription());

                //MODEL
                if (item.Model() != null) {
                    Resource _model = model.createResource(URIbase + "model")
                            .addProperty(RDF.type, provEntity)
                            .addProperty(RDF.type, mexcore_MODEL);

                    if (StringUtils.isNotBlank(item.Model().get_id()) &&
                            StringUtils.isNotEmpty(item.Model().get_id())) {
                                _model.addProperty(DCTerms.identifier, item.Model().get_id());}

                    if (StringUtils.isNotBlank(item.Model().get_description()) &&
                            StringUtils.isNotEmpty(item.Model().get_description())) {
                                _model.addProperty(DCTerms.description, item.Model().get_description());}

                    if (item.Model().get_date() != null) {
                        _model.addProperty(DCTerms.date, item.Model().get_date().toString());}

                    _expConfiguration.addProperty(PROVO.used, _model);
                }
                //SAMPLING METHOD
                if (item.SamplingMethod() != null) {
                    Resource mexcore_SAMPLING_METHOD = model.createResource(MEXCORE_10.NS + item.SamplingMethod().getIndividualName());

                    Resource _sampling = model.createResource(URIbase + "sampling")
                            .addProperty(RDF.type, provEntity)
                            .addProperty(RDF.type, mexcore_SAMPLING_METHOD);

                    if (StringUtils.isNotBlank(item.SamplingMethod().getFolds().toString()) &&
                            StringUtils.isNotEmpty(item.SamplingMethod().getFolds().toString())) {
                        _sampling.addProperty(MEXCORE_10.folds, item.SamplingMethod().getFolds().toString());}

                    if (StringUtils.isNotBlank(item.SamplingMethod().getSequential().toString()) &&
                            StringUtils.isNotEmpty(item.SamplingMethod().getSequential().toString())) {
                        _sampling.addProperty(MEXCORE_10.sequential, item.SamplingMethod().getSequential().toString());
                    }

                    if (StringUtils.isNotBlank(item.SamplingMethod().getTrainSize().toString()) &&
                            StringUtils.isNotEmpty(item.SamplingMethod().getTrainSize().toString())) {
                        _sampling.addProperty(MEXCORE_10.trainSize, item.SamplingMethod().getTrainSize().toString());}

                    if (StringUtils.isNotBlank(item.SamplingMethod().getTestSize().toString()) &&
                            StringUtils.isNotEmpty(item.SamplingMethod().getTestSize().toString())) {
                        _sampling.addProperty(MEXCORE_10.testSize, item.SamplingMethod().getTestSize().toString());}

                    _expConfiguration.addProperty(PROVO.used, _sampling);
                }

                aux++;

            }

        }




        //mex-algo


        //mex-perf

       /* Resource context2 = model.createResource(URIbase + "context");

        Resource john = model.createResource( URIbase + "John" );
        //Property hasSurname = model.createProperty( PROVO.NS + "hasSurname" );
        model.add( john, PROVO.wasAttributedTo, context2 );

        final Resource clsApplicationContext = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT );
        model.add(clsApplicationContext, PROVO.wasAttributedTo,"teste");

        Resource appcontext = model.createResource(URIbase + "xxx")
                .addProperty(DC.title, "dddddd")
                .addProperty(PROVO.wasAttributedTo, context2)
                .addProperty(DC.description, "zzzzzzz");
                */


        FileWriter out;
        try {

            out = new FileWriter(filename);
            model.write( out, Constants.EnumRDFFormat.TURTLE );
            out.close();
        }
        catch (Exception e) {
            System.out.println("error: " + e.toString());
        }

    }
}
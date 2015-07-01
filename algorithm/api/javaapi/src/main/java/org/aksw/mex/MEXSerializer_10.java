package org.aksw.mex;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.*;
import org.aksw.mex.algo.AlgorithmParameterVO;
import org.aksw.mex.core.*;
import org.aksw.mex.perf.overall.*;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXEnum;
import org.aksw.mex.util.ontology.*;
import org.aksw.mex.util.ontology.mex.MEXALGO_10;
import org.aksw.mex.util.ontology.mex.MEXCORE_10;
import org.aksw.mex.util.ontology.mex.MEXPERF_10;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by esteves on 25.06.15.
 */
public class MEXSerializer_10 {
    private boolean _valid;

    private static MEXSerializer_10 instance = null;
    protected MEXSerializer_10() {
        _valid=false;
    }
    public static MEXSerializer_10 getInstance() {
        if(instance == null) {
            instance = new MEXSerializer_10();
        }
        return instance;
    }
    public boolean parse(MyMEX_10 mex) throws Exception{
        _valid=false;

            List<ExperimentConfigurationVO> configurations = mex.getExperimentConfigurations();
            for (int i = 0; i < configurations.size(); i++) {

                if (configurations.get(i).SamplingMethod() != null &&
                        (configurations.get(i).SamplingMethod().getTestSize() == null || configurations.get(i).SamplingMethod().getTrainSize() == null)) {
                    throw new Exception("missing parameters for sampling: inform the train and test size for sampling methods!");
                }

                //minimal set of classes to be implemented
                if (configurations.get(i).getFeatures() == null
                        || configurations.get(i).getFeatures().size() == 0){
                    throw new Exception("missing feature(s)!");
                }

                if (configurations.get(i).getAlgorithms() == null
                        || configurations.get(i).getAlgorithms().size() == 0){
                    throw new Exception("missing algorithm(s)!");
                }

                if (configurations.get(i).getExecutions() == null
                        || configurations.get(i).getExecutions().size() == 0){
                    throw new Exception("missing execution(s)!");
                }

                for (int j = 0; j < configurations.get(i).getExecutions().size(); j++) {
                    if (configurations.get(i).getExecutions().get(j).getPerformances() == null ||
                            configurations.get(i).getExecutions().get(j).getPerformances().size() == 0){
                                throw new Exception("missing execution performance for the execution index " + String.valueOf(j));
                            }
                }

            }

            _valid=true;


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

    private void writeJena(String filename, String URIbase, MyMEX_10 mex) {

        Model model = ModelFactory.createDefaultModel();
        setHeaders(model, URIbase);

        //common resources
        Resource provAgent = model.createResource(PROVO.NS + PROVO.ClasseTypes.AGENT);
        Resource provPerson = model.createResource(PROVO.NS + PROVO.ClasseTypes.PERSON);
        Resource provOrganization = model.createResource(PROVO.NS + PROVO.ClasseTypes.ORGANIZATION);
        Resource provEntity = model.createResource(PROVO.NS + PROVO.ClasseTypes.ENTITY);
        Resource provActivity = model.createResource(PROVO.NS + PROVO.ClasseTypes.ACTIVITY);
        Resource provCollection = model.createResource(PROVO.NS + PROVO.ClasseTypes.COLLECTION);

        Resource mexcore_APC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT);
        Resource mexcore_EXP_HEADER = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT);
        Resource mexcore_EXP_CONF = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT_CONFIGURATION);
        Resource mexcore_MODEL = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.MODEL);

        Resource mexcore_HARDWARE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.HARDWARE_CONFIGURATION);
        Resource mexcore_DATASET = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.DATASET);
        Resource mexcore_EXEC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXECUTION);
        Resource mexcore_FEATURE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.FEATURE);

        Resource mexcore_EXAMPLE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXAMPLE);
        Resource mexcore_EXAMPLE_COLLECTION = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXAMPLE_COLLECTION);

        Resource mexalgo_IMPLEMENTATION = model.createResource(MEXCORE_10.NS + MEXALGO_10.ClasseTypes.IMPLEMENTATION);

        Resource mexalgo_ALGO_PARAM = model.createResource(MEXCORE_10.NS + MEXALGO_10.ClasseTypes.ALGORITHM_PARAMETER);


        //mex-core
        Resource _application = null;
        Resource _context;
        Resource _organization;
        Resource _expHeader;

        //gets
        if (mex.getApplicationContext() != null) {
            _application = model.createResource(URIbase + "application")
                    .addProperty(RDF.type, provAgent)
                    .addProperty(RDF.type, provPerson)
                    .addProperty(RDF.type, provOrganization)
                    .addProperty(RDF.type, mexcore_APC)
                    .addProperty(DCTerms.dateCopyrighted, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(mex.getApplicationContext().get_fileDate()));

            if (mex.getApplicationContext().get_givenName() != null) {
                _application.addProperty(FOAF.givenName, mex.getApplicationContext().get_givenName());
            }
            if (mex.getApplicationContext().get_mbox() != null) {
                _application.addProperty(FOAF.mbox, mex.getApplicationContext().get_mbox());
            }

            if (mex.getApplicationContext().get_homepage() != null) {
                _application.addProperty(DOAP.homepage, mex.getApplicationContext().get_givenName());
            }
            if (mex.getApplicationContext().get_description() != null) {
                _application.addProperty(DOAP.description, mex.getApplicationContext().get_mbox());
            }
            if (mex.getApplicationContext().get_category() != null) {
                _application.addProperty(DOAP.category, mex.getApplicationContext().get_givenName());
            }
            if (mex.getApplicationContext().get_location() != null) {
                _application.addProperty(DOAP.location, mex.getApplicationContext().get_mbox());
            }

            if (mex.getApplicationContext().get_trustyURI() != null) {
                _application.addProperty(MEXCORE_10.trustyURI, mex.getApplicationContext().get_mbox());
            }

            if (mex.getApplicationContext().get_organization() != null) {
                Resource mexcore_ORG = model.createResource(MEXCORE_10.NS + mex.getApplicationContext().get_organization());
                _organization = model.createResource(URIbase + "organization")
                        .addProperty(RDF.type, provAgent)
                        .addProperty(RDF.type, provOrganization)
                        .addProperty(FOAF.givenName, mex.getApplicationContext().get_organization());
            }

            if (mex.getApplicationContext().getContext() != null) {
                Resource mexcore_CON = model.createResource(MEXCORE_10.NS + mex.getApplicationContext().getContext().get_context());
                _context = model.createResource(URIbase + "context")
                        .addProperty(RDF.type, provEntity)
                        .addProperty(RDF.type, mexcore_CON)
                        .addProperty(PROVO.wasAttributedTo, _application);
            }
        }

        //EXPERIMENT
        if (mex.getExperiment() != null) {
            _expHeader = model.createResource(URIbase + "experiment-header")
                    .addProperty(RDF.type, provEntity)
                    .addProperty(RDF.type, mexcore_EXP_HEADER);
                    if(StringUtils.isNotEmpty(mex.getExperiment().get_id()) && StringUtils.isNotBlank(mex.getExperiment().get_id())) {
                        _expHeader.addProperty(DCTerms.identifier, mex.getExperiment().get_id());
                    }
                    if(StringUtils.isNotEmpty(mex.getExperiment().get_title()) && StringUtils.isNotBlank(mex.getExperiment().get_title())) {
                        _expHeader.addProperty(DCTerms.title, mex.getExperiment().get_title());
                    }
                    if(mex.getExperiment().get_date() != null) {
                        DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                        try{
                            df.setLenient(false);
                            df.parse(mex.getExperiment().get_date().toString());
                            _expHeader.addProperty(DCTerms.date, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(mex.getExperiment().get_date()));
                        }catch (ParseException e) {}
                    }
                    if(StringUtils.isNotEmpty(mex.getExperiment().get_description()) && StringUtils.isNotBlank(mex.getExperiment().get_description())) {
                        _expHeader.addProperty(DCTerms.description, mex.getExperiment().get_description());
                    }

            if (mex.getApplicationContext() != null) {
                _expHeader.addProperty(PROVO.wasAttributedTo, _application);
            }

        }
        //EXPERIMENT CONFIGURATION
        if (mex.getExperimentConfigurations() != null) {
            int aux = 1;
            for (Iterator<ExperimentConfigurationVO> i = mex.getExperimentConfigurations().iterator(); i.hasNext(); ) {
                ExperimentConfigurationVO item = i.next();

                Resource _expConfiguration = model.createResource(URIbase + "configuration" + aux)
                        .addProperty(RDF.type, provActivity)
                        .addProperty(RDF.type, mexcore_EXP_CONF);
                if(StringUtils.isNotEmpty(item.getId()) && StringUtils.isNotBlank(item.getId())) {
                    _expConfiguration.addProperty(DCTerms.identifier, item.getId());
                }
                if(StringUtils.isNotEmpty(item.getDescription()) && StringUtils.isNotBlank(item.getDescription())) {
                    _expConfiguration.addProperty(DCTerms.description, item.getDescription());
                }


                //MODEL
                if (item.Model() != null) {
                    boolean atLeastOne = false;

                    Resource _model = model.createResource(URIbase + "model")
                            .addProperty(RDF.type, provEntity)
                            .addProperty(RDF.type, mexcore_MODEL);

                    if (StringUtils.isNotBlank(item.Model().get_id()) &&
                            StringUtils.isNotEmpty(item.Model().get_id())) {
                        _model.addProperty(DCTerms.identifier, item.Model().get_id());
                        atLeastOne=true;
                    }

                    if (StringUtils.isNotBlank(item.Model().get_description()) &&
                            StringUtils.isNotEmpty(item.Model().get_description())) {
                        _model.addProperty(DCTerms.description, item.Model().get_description());
                        atLeastOne=true;
                    }

                    if (item.Model().get_date() != null) {
                        _model.addProperty(DCTerms.date, item.Model().get_date().toString());
                        atLeastOne=true;
                    }

                    if (atLeastOne) {
                        _expConfiguration.addProperty(PROVO.used, _model);

                    }

                }
                //IMPLEMENTATION
                if (item.Implementation() != null) {
                    if (StringUtils.isNotBlank(item.Implementation().getName()) && StringUtils.isNotEmpty(item.Implementation().getName())) {
                        Resource _imp = model.createResource(URIbase + "implementation")
                                .addProperty(RDF.type, provEntity)
                                .addProperty(RDF.type, MEXCORE_10.NS + item.Implementation().getName());
                        _expConfiguration.addProperty(PROVO.used, _imp);}
                }
                //SAMPLING METHOD
                if (item.SamplingMethod() != null) {
                    Resource mexcore_SAMPLING_METHOD = model.createResource(MEXCORE_10.NS + item.SamplingMethod().getIndividualName());

                    Resource _sampling = model.createResource(URIbase + "sampling")
                            .addProperty(RDF.type, provEntity)
                            .addProperty(RDF.type, mexcore_SAMPLING_METHOD);

                    if (item.SamplingMethod().getFolds() != null && StringUtils.isNotBlank(item.SamplingMethod().getFolds().toString()) && StringUtils.isNotEmpty(item.SamplingMethod().getFolds().toString())) {
                        _sampling.addProperty(MEXCORE_10.folds, item.SamplingMethod().getFolds().toString());
                    }

                    if (item.SamplingMethod().getSequential() != null && StringUtils.isNotBlank(item.SamplingMethod().getSequential().toString()) && StringUtils.isNotEmpty(item.SamplingMethod().getSequential().toString())) {
                        _sampling.addProperty(MEXCORE_10.sequential, item.SamplingMethod().getSequential().toString());
                    }

                    if (item.SamplingMethod().getTrainSize() != null && StringUtils.isNotBlank(item.SamplingMethod().getTrainSize().toString()) && StringUtils.isNotEmpty(item.SamplingMethod().getTrainSize().toString())) {
                        _sampling.addProperty(MEXCORE_10.trainSize, item.SamplingMethod().getTrainSize().toString());
                    }

                    if (item.SamplingMethod().getTestSize() != null && StringUtils.isNotBlank(item.SamplingMethod().getTestSize().toString()) && StringUtils.isNotEmpty(item.SamplingMethod().getTestSize().toString())) {
                        _sampling.addProperty(MEXCORE_10.testSize, item.SamplingMethod().getTestSize().toString());
                    }

                    _expConfiguration.addProperty(PROVO.used, _sampling);
                }

                //HARDWARE CONFIGURATION
                if (item.HardwareConfiguration() != null) {

                    Resource _hardware = model.createResource(URIbase + "hardware")
                            .addProperty(RDF.type, provEntity)
                            .addProperty(RDF.type, mexcore_HARDWARE);

                    if (StringUtils.isNotBlank(item.HardwareConfiguration().getOs()) && StringUtils.isNotEmpty(item.HardwareConfiguration().getOs())) {
                        _hardware.addProperty(DOAP.os, item.HardwareConfiguration().getOs());
                    }

                    if (StringUtils.isNotBlank(item.HardwareConfiguration().getCache()) && StringUtils.isNotEmpty(item.HardwareConfiguration().getCache())) {
                        _hardware.addProperty(MEXCORE_10.cache, item.HardwareConfiguration().getCache());
                    }

                    if (StringUtils.isNotBlank(item.HardwareConfiguration().getCPU()) && StringUtils.isNotEmpty(item.HardwareConfiguration().getCPU())) {
                        _hardware.addProperty(MEXCORE_10.cpu, item.HardwareConfiguration().getCPU());
                    }

                    if (StringUtils.isNotBlank(item.HardwareConfiguration().getMemory()) && StringUtils.isNotEmpty(item.HardwareConfiguration().getMemory())) {
                        _hardware.addProperty(MEXCORE_10.memory, item.HardwareConfiguration().getMemory());
                    }

                    if (StringUtils.isNotBlank(item.HardwareConfiguration().getHD()) && StringUtils.isNotEmpty(item.HardwareConfiguration().getHD())) {
                        _hardware.addProperty(MEXCORE_10.hd, item.HardwareConfiguration().getHD());
                    }

                    if (StringUtils.isNotBlank(item.HardwareConfiguration().getVideo()) && StringUtils.isNotEmpty(item.HardwareConfiguration().getVideo())) {
                        _hardware.addProperty(MEXCORE_10.video, item.HardwareConfiguration().getVideo());
                    }

                    _expConfiguration.addProperty(PROVO.used, _hardware);
                }

                //DATASET
                if (item.DataSet() != null) {

                    Resource _dataset = model.createResource(URIbase + "dataset")
                            .addProperty(RDF.type, provEntity)
                            .addProperty(RDF.type, mexcore_DATASET);

                    if (StringUtils.isNotBlank(item.DataSet().getName()) && StringUtils.isNotEmpty(item.DataSet().getName())) {
                        _dataset.addProperty(DCTerms.title, item.DataSet().getName());
                    }
                    if (StringUtils.isNotBlank(item.DataSet().getDescription()) && StringUtils.isNotEmpty(item.DataSet().getDescription())) {
                        _dataset.addProperty(DCTerms.description, item.DataSet().getDescription());
                    }
                    if (StringUtils.isNotBlank(item.DataSet().getURI()) && StringUtils.isNotEmpty(item.DataSet().getURI())) {
                        _dataset.addProperty(DCAT.landingPage, item.DataSet().getURI());
                    }

                    _expConfiguration.addProperty(PROVO.used, _dataset);
                }

                //FEATURE
                int auxf = 1;
                for (Iterator<FeatureVO> ifeature = item.getFeatures().iterator(); ifeature.hasNext(); ) {
                    FeatureVO f = ifeature.next();
                    if (f != null) {
                        Resource _feature = model.createResource(URIbase + "feature" + String.valueOf(auxf))
                                .addProperty(RDF.type, provEntity)
                                .addProperty(RDF.type, mexcore_FEATURE);

                        if (StringUtils.isNotBlank(f.getId()) && StringUtils.isNotEmpty(f.getId())) {
                            _feature.addProperty(DCTerms.identifier, f.getId());
                        }
                        if (StringUtils.isNotBlank(f.getName()) && StringUtils.isNotEmpty(f.getName())) {
                            _feature.addProperty(RDFS.label, f.getName());
                        }

                        _expConfiguration.addProperty(PROVO.used, _feature);
                        auxf++;
                    }
                }

                //EXECUTIONS (e)
                if (item.getExecutions()!= null && item.getExecutions().size() >0){
                    int auxe = 1;
                    for (Iterator<Execution> iexec = item.getExecutions().iterator(); iexec.hasNext(); ) {
                        Execution e = iexec.next();
                        Resource _exec = null;
                        if (e != null) {

                            //EXECUTION
                            _exec = model.createResource(URIbase + "execution" + String.valueOf(auxe))
                                    .addProperty(RDF.type, provActivity)
                                    .addProperty(RDF.type, mexcore_EXEC)
                                    .addProperty(PROVO.id, e.getId());
                            if (e.getStartedAtTime() != null) {
                                _exec.addProperty(PROVO.startedAtTime, e.getStartedAtTime().toString());
                            }
                            if (e.getEndedAtTime() != null) {
                                _exec.addProperty(PROVO.endedAtTime, e.getEndedAtTime().toString());
                            }

                            if (e instanceof ExecutionSetVO) {
                                ExecutionSetVO temp = (ExecutionSetVO) e;
                                if (temp.getStartsAtPosition() != null) {
                                    _exec.addProperty(MEXCORE_10.startsAtPosition, temp.getStartsAtPosition());
                                }
                                if (temp.getEndsAtPosition() != null) {
                                    _exec.addProperty(MEXCORE_10.endsAtPosition, temp.getEndsAtPosition());
                                }
                                _exec.addProperty(MEXCORE_10.group, "true");
                            }else{
                                _exec.addProperty(MEXCORE_10.group, "false");
                            }

                            //PHASE
                            if (e.getPhase() != null){
                                Resource mexcore_PHASE = model.createResource(MEXCORE_10.NS + e.getPhase().getName());
                                if (StringUtils.isNotBlank(e.getPhase().getName()) && StringUtils.isNotEmpty(e.getPhase().getName())) {
                                Resource _phase = model.createResource(URIbase + "phase" + e.getPhase().getName())
                                        .addProperty(RDF.type, provEntity)
                                        .addProperty(RDF.type, mexcore_PHASE);
                                _exec.addProperty(PROVO.used, _phase);}
                            }

                            //EXAMPLE (the set of examples)
                            if (e.getExamples() != null && e.getExamples().size() > 0) {
                                Integer auxex = 1;
                                for (Iterator<ExampleVO> iexample = e.getExamples().iterator(); iexample.hasNext(); ) {
                                    ExampleVO example = iexample.next();
                                    if (example != null) {
                                        Resource _ex = model.createResource(URIbase + "example" + String.valueOf(auxex))
                                                .addProperty(RDF.type, provEntity)
                                                .addProperty(RDF.type, mexcore_EXAMPLE)
                                                .addProperty(DCTerms.identifier, example.getId())
                                                .addProperty(PROVO.value, example.getValue());

                                        _exec.addProperty(PROVO.used, _ex);
                                        auxex++;
                                    }
                                }
                            }
                            //ALGORITHM
                            if (e.getAlgorithm() != null) {
                                Resource mexalgo_ALGO = model.createResource(MEXCORE_10.NS + e.getAlgorithm().getIndividualName());

                                    Resource _alg = model.createResource(URIbase + "alg" + e.getAlgorithm().getIndividualName())
                                            .addProperty(RDF.type, provEntity)
                                            .addProperty(RDF.type, mexalgo_ALGO);
                                    _exec.addProperty(PROVO.used, _alg);
                                if (StringUtils.isNotBlank(e.getAlgorithm().getIdentifier()) && StringUtils.isNotEmpty(e.getAlgorithm().getIdentifier())) {
                                    _alg.addProperty(DCTerms.identifier, e.getAlgorithm().getAcroynm());}
                                if (StringUtils.isNotBlank(e.getAlgorithm().getDescription()) && StringUtils.isNotEmpty(e.getAlgorithm().getDescription())) {
                                    _alg.addProperty(DCTerms.description, e.getAlgorithm().getDescription());}
                                if (StringUtils.isNotBlank(e.getAlgorithm().getAcroynm()) && StringUtils.isNotEmpty(e.getAlgorithm().getAcroynm())) {
                                    _alg.addProperty(MEXALGO_10.acronym, e.getAlgorithm().getAcroynm());}

                                _exec.addProperty(PROVO.used, _alg);

                                //ALGORITHM PARAMETER
                                if (e.getAlgorithm().getParameters() != null && e.getAlgorithm().getParameters().size() > 0) {
                                    Integer auxparam = 1;
                                    for(Iterator<AlgorithmParameterVO> iparam = e.getAlgorithm().getParameters().iterator(); iparam.hasNext(); ) {
                                        AlgorithmParameterVO algoParam = iparam.next();
                                        if (algoParam != null) {
                                            Resource _algoParam = null;
                                            _algoParam = model.createResource(URIbase + "param" + String.valueOf(auxparam))
                                                    .addProperty(RDF.type, provEntity)
                                                    .addProperty(RDF.type, mexalgo_ALGO_PARAM)
                                                    .addProperty(PROVO.value, algoParam.getValue())
                                                    .addProperty(DCTerms.identifier, algoParam.getIdentifier());

                                            _alg.addProperty(MEXALGO_10.hasParameter,_algoParam);
                                            _exec.addProperty(PROVO.used, _alg);
                                            auxparam++;}
                                    }

                                }
                            }
                            //PERFORMANCES
                            if (e.getPerformances() != null && e.getPerformances().size() > 0) {
                                Integer auxmea = 1;
                                for(Iterator<Measure> imea = e.getPerformances().iterator(); imea.hasNext(); ) {
                                    Measure mea = imea.next();
                                    if (mea != null) {
                                        Resource _mea = null;

                                        Resource mexperf;
                                        String auxType = null;

                                        if (mea instanceof ClassificationMeasureVO){
                                            auxType=MEXPERF_10.ClasseTypes.CLASSIFICATION_MEASURE;
                                        }else if (mea instanceof RegressionMeasureVO){
                                            auxType=MEXPERF_10.ClasseTypes.REGRESSION_MEASURE;
                                        }else if (mea instanceof ClusteringMeasureVO){
                                            auxType=MEXPERF_10.ClasseTypes.CLUSTERING_MEASURE;
                                        }else if (mea instanceof StatisticalMeasureVO){
                                            auxType=MEXPERF_10.ClasseTypes.STATISTICAL_MEASURE;
                                        }

                                        mexperf = model.createResource(MEXPERF_10.NS + auxType);

                                        _mea = model.createResource(URIbase + "measure" + String.valueOf(auxe) + "_" + String.valueOf(auxmea))
                                                .addProperty(RDF.type, provEntity)
                                                .addProperty(RDF.type, mexperf)
                                                .addProperty(PROVO.value, String.valueOf(mea.getValue()))
                                                .addProperty(DCTerms.identifier, mea.getName());

                                        _mea.addProperty(PROVO.wasInformedBy, _exec);
                                        auxmea++;}
                                }

                            }



                            /*if (e.getExampleCollection() != null) {
                                Resource _excollection = model.createResource(URIbase + "example_collection")
                                        .addProperty(RDF.type, provActivity)
                                        .addProperty(RDF.type, mexcore_EXAMPLE_COLLECTION);

                                if (StringUtils.isNotBlank(e.getExampleCollection().getStartIndex().toString()) &&
                                        StringUtils.isNotEmpty(e.getExampleCollection().getStartIndex().toString())) {
                                    _excollection.addProperty(MEXCORE_10.startsAtPosition, e.getExampleCollection().getStartIndex().toString());
                                }
                                if (StringUtils.isNotBlank(e.getExampleCollection().getEndIndex().toString()) &&
                                        StringUtils.isNotEmpty(e.getExampleCollection().getEndIndex().toString())) {
                                    _excollection.addProperty(MEXCORE_10.endsAtPosition, e.getExampleCollection().getEndIndex().toString());
                                }
                                int auxexample = 1;
                                //EXAMPLES (individual representation for given run)
                                for(Iterator<ExampleVO> iexample = e.getExampleCollection().getExamples().iterator(); iexample.hasNext(); ) {
                                    ExampleVO example = iexample.next();
                                    if (example != null) {
                                        Resource _example = null;
                                        _example = model.createResource(URIbase + "example" + String.valueOf(auxexample))
                                                .addProperty(RDF.type, provEntity)
                                                .addProperty(RDF.type, mexcore_EXAMPLE)
                                                .addProperty(PROVO.value, example.getValue())
                                                .addProperty(DCTerms.identifier, example.getId());

                                        _excollection.addProperty(PROVO.hadMember,_example);
                                        _exec.addProperty(PROVO.used, _example);
                                        auxexample++;}
                                }
                                _exec.addProperty(PROVO.used, _excollection);
                            }

                            */


                        }
                        //next execution
                        _exec.addProperty(PROVO.wasInformedBy, _expConfiguration);
                        auxe++;
                    }
                }

                    //next experiment configuration
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
                model.write(out, MEXConstant.EnumRDFFormat.TURTLE);
                out.close();
            } catch (Exception e) {
                System.out.println("error: " + e.toString());
            }

        }
    }
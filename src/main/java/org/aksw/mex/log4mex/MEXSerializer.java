package org.aksw.mex.log4mex;

import com.google.common.collect.Collections2;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.*;
import org.aksw.mex.log4mex.algo.AlgorithmParameterVO;
import org.aksw.mex.log4mex.core.*;
import org.aksw.mex.log4mex.perf.overall.*;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.ontology.DCAT;
import org.aksw.mex.util.ontology.DOAP;
import org.aksw.mex.util.ontology.FOAF;
import org.aksw.mex.util.ontology.PROVO;
import org.aksw.mex.util.ontology.mex.MEXALGO_10;
import org.aksw.mex.util.ontology.mex.MEXCORE_10;
import org.aksw.mex.util.ontology.mex.MEXPERF_10;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by esteves on 25.06.15.
 */
public class MEXSerializer {
    private boolean _valid;

    private static MEXSerializer instance = null;
    protected MEXSerializer() {
        _valid=false;
    }
    public static MEXSerializer getInstance() {
        if(instance == null) {
            instance = new MEXSerializer();
        }
        return instance;
    }
    public boolean parse(MyMEXVO mex) throws Exception{
        _valid=false;

            /* minimal set of classes to be implemented */

            if (mex.getApplicationContext() == null ||
                    (StringUtils.isEmpty(mex.getApplicationContext().get_givenName()) ||
                    StringUtils.isBlank(mex.getApplicationContext().get_givenName()))) {
                throw new Exception("[APPLICATION_CONTEXT]: missing the author name!");
            }

            List<ExperimentConfigurationVO> configurations = mex.getExperimentConfigurations();
            for (int i = 0; i < configurations.size(); i++) {


                //dataset
                if (configurations.get(i).DataSet() == null
                        || (StringUtils.isBlank(configurations.get(i).DataSet().getName()) ||
                            StringUtils.isEmpty(configurations.get(i).DataSet().getName()))){
                    throw new Exception("[EXPERIMENT_CONFIGURATION]: missing basic dataset information: inform at least the dataset/file name!");
                }

                //sampling method
                if (configurations.get(i).SamplingMethod() != null &&
                        (configurations.get(i).SamplingMethod().getTestSize() == null || configurations.get(i).SamplingMethod().getTrainSize() == null)) {
                    throw new Exception("[EXPERIMENT_CONFIGURATION]: missing parameters for sampling: inform the train and test size for sampling methods!");
                }

                //minimal set of classes to be implemented
                if (configurations.get(i).getFeatures() == null
                        || configurations.get(i).getFeatures().size() == 0){
                    throw new Exception("[EXPERIMENT_CONFIGURATION]: missing feature(s)!");
                }

                if (configurations.get(i).getAlgorithms() == null
                        || configurations.get(i).getAlgorithms().size() == 0){
                    throw new Exception("[EXPERIMENT_CONFIGURATION]: missing algorithm(s)!");
                }

                if (configurations.get(i).getExecutions() == null
                        || configurations.get(i).getExecutions().size() == 0){
                    throw new Exception("[EXPERIMENT_CONFIGURATION]: missing execution(s)!");
                }

                for (int j = 0; j < configurations.get(i).getExecutions().size(); j++) {
                    if (configurations.get(i).getExecutions().get(j).getPerformances() == null ||
                            configurations.get(i).getExecutions().get(j).getPerformances().size() == 0){
                                throw new Exception("[PERFORMANCE]: missing execution's performance for the execution index " + String.valueOf(j));
                            }
                }

            }

            _valid=true;


        return _valid;

    }

    public void saveToDisk(String filename, String URIbase, MyMEXVO mex){
        try{
            if (_valid){
                writeJena(filename, URIbase, mex);
            }
        }catch (Exception e){
            System.out.println(e.toString());
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


    //go back here later...
    private void cleanUpTheResources(MyMEXVO mex) {

        //EXPERIMENT CONFIGURATION
        if (mex.getExperimentConfigurations() != null) {
            int aux = 1;
            for (Iterator<ExperimentConfigurationVO> i = mex.getExperimentConfigurations().iterator(); i.hasNext(); ) {
                ExperimentConfigurationVO item = i.next();

                //SAMPLING
                String samplingClass = item.SamplingMethod().getIndividualName();
                String  samplingName   = item.SamplingMethod().getIndividualName();
                Double  samplingTrain  = item.SamplingMethod().getTrainSize();
                Double  samplingTest   = item.SamplingMethod().getTestSize();
                Integer samplingFolds  = item.SamplingMethod().getFolds();

                SamplingMethodVO tempSampling = new SamplingMethodVO(samplingClass, samplingName,samplingTrain,samplingTest);
                tempSampling.setFolds(samplingFolds);

                Collection<ExperimentConfigurationVO> t =
                        Collections2.filter(mex.getExperimentConfigurations(),
                                p -> p.SamplingMethod().getFolds().equals(samplingFolds) &&
                                        p.SamplingMethod().getIndividualName().equals(samplingName) &&
                                        p.SamplingMethod().getTrainSize().equals(samplingTrain) &&
                                        p.SamplingMethod().getTestSize().equals(samplingTest));
                if (t != null && t.size() > 0){
                    item = null;
                }


            }

        }
    }


    private void writeJena(String filename, String URIbase, MyMEXVO mex) throws Exception{

        //cleanUpTheResources(mex);

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
                    Resource mexcore_SAMPLING_METHOD = model.createResource(MEXCORE_10.NS + item.SamplingMethod().getClassName());

                    Resource _sampling = model.createResource(URIbase + item.SamplingMethod().getIndividualName())
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
                            _exec = model.createResource(URIbase + "execution_" + String.valueOf(e.getId()))
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
                                        Resource _ex = model.createResource(URIbase + "examples" + String.valueOf(auxex))
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
                                Resource mexalgo_ALGO = model.createResource(MEXCORE_10.NS + e.getAlgorithm().getClassName());

                                    Resource _alg = model.createResource(URIbase + e.getAlgorithm().getIndividualName())
                                            .addProperty(RDF.type, provEntity)
                                            .addProperty(RDF.type, mexalgo_ALGO);
                                    _exec.addProperty(PROVO.used, _alg);
                                if (StringUtils.isNotBlank(e.getAlgorithm().getIdentifier()) && StringUtils.isNotEmpty(e.getAlgorithm().getIdentifier())) {
                                    _alg.addProperty(DCTerms.identifier, e.getAlgorithm().getIdentifier());}
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

                                        _mea = model.createResource(URIbase + "measure" + String.valueOf(e.getId()) + "_" + String.valueOf(auxmea))
                                                .addProperty(RDF.type, provEntity)
                                                .addProperty(RDF.type, mexperf);

                                        Property mexprop = null;


                                        if (mea.getName().toLowerCase().equals("accuracy")){
                                            mexprop = MEXPERF_10.accuracy;
                                        }else if (mea.getName().toLowerCase().equals("fMeasure")) {
                                            mexprop = MEXPERF_10.fMeasure;
                                        }else if (mea.getName().toLowerCase().equals("precision")) {
                                            mexprop = MEXPERF_10.precision;
                                        }else if (mea.getName().toLowerCase().equals("recall")) {
                                            mexprop = MEXPERF_10.recall;
                                        }else if (mea.getName().toLowerCase().equals("roc")) {
                                            mexprop = MEXPERF_10.roc;
                                        }else if (mea.getName().toLowerCase().equals("sensitivity")) {
                                            mexprop = MEXPERF_10.sensitivity;
                                        }else if (mea.getName().toLowerCase().equals("specificity")) {
                                            mexprop = MEXPERF_10.specificity;
                                        }else if (mea.getName().toLowerCase().equals("trueNegative")) {
                                            mexprop = MEXPERF_10.trueNegative;
                                        }else if (mea.getName().toLowerCase().equals("truePositive")) {
                                            mexprop = MEXPERF_10.truePositive;
                                        }else if (mea.getName().toLowerCase().equals("falseNegative")) {
                                            mexprop = MEXPERF_10.falseNegative;
                                        }else if (mea.getName().toLowerCase().equals("falsePositive")) {
                                            mexprop = MEXPERF_10.falsePositive;
                                        }else if (mea.getName().toLowerCase().equals("falseNegativeRate")) {
                                            mexprop = MEXPERF_10.falseNegativeRate;
                                        }else if (mea.getName().toLowerCase().equals("falsePositiveRate")) {
                                            mexprop = MEXPERF_10.falsePositiveRate;
                                        }else if (mea.getName().toLowerCase().equals("trueNegativeRate")) {
                                            mexprop = MEXPERF_10.trueNegativeRate;
                                        }else if (mea.getName().toLowerCase().equals("truePositiveRate")) {
                                            mexprop = MEXPERF_10.truePositiveRate;
                                        }else if (mea.getName().toLowerCase().equals("meanAbsoluteDeviation")){
                                            mexprop = MEXPERF_10.meanAbsoluteDeviation;
                                        }else if (mea.getName().toLowerCase().equals("meanSquareError")) {
                                            mexprop = MEXPERF_10.meanSquareError;
                                        }else if (mea.getName().toLowerCase().equals("residual")) {
                                            mexprop = MEXPERF_10.residual;
                                        }else if (mea.getName().toLowerCase().equals("totalError")) {
                                            mexprop = MEXPERF_10.totalError;
                                        }else if (mea.getName().toLowerCase().equals("relativeAbsoluteError")) {
                                            mexprop = MEXPERF_10.relativeAbsoluteError;
                                        }else if (mea.getName().toLowerCase().equals("rootRelativeSquaredError")) {
                                            mexprop = MEXPERF_10.rootRelativeSquaredError;
                                        }else if (mea.getName().toLowerCase().equals("rootMeanSquaredError")) {
                                            mexprop = MEXPERF_10.rootMeanSquaredError;
                                        }else if (mea.getName().toLowerCase().equals("correlationCoefficient")) {
                                            mexprop = MEXPERF_10.correlationCoefficient;
                                        }else if  (mea.getName().toLowerCase().equals("pearsonCorrelation")){
                                            mexprop = MEXPERF_10.pearsonCorrelation;
                                        }else if (mea.getName().toLowerCase().equals("chiSquare")) {
                                            mexprop = MEXPERF_10.chiSquare;
                                        }else if (mea.getName().toLowerCase().equals("error")) {
                                            mexprop = MEXPERF_10.error;
                                        }else if (mea.getName().toLowerCase().equals("kolmogorovSmirnov")) {
                                            mexprop = MEXPERF_10.kolmogorovSmirnov;
                                        }else if (mea.getName().toLowerCase().equals("mean")) {
                                            mexprop = MEXPERF_10.mean;
                                        }else if (mea.getName().toLowerCase().equals("nemenyi")) {
                                            mexprop = MEXPERF_10.nemenyi;
                                        }else if (mea.getName().toLowerCase().equals("standardDeviation")) {
                                            mexprop = MEXPERF_10.standardDeviation;
                                        }else if (mea.getName().toLowerCase().equals("wilcoxon")) {
                                            mexprop = MEXPERF_10.wilcoxon;
                                        }else if (mea.getName().toLowerCase().equals("variance")) {
                                            mexprop = MEXPERF_10.variance;
                                        }else if (mea.getName().toLowerCase().equals("friedman")) {
                                            mexprop = MEXPERF_10.friedman;
                                        }else if (mea.getName().toLowerCase().equals("median")) {
                                            mexprop = MEXPERF_10.median;
                                        }else if (mea.getName().toLowerCase().equals("kappaStatistics")) {
                                            mexprop = MEXPERF_10.kappaStatistics;
                                        }else if (mea.getName().toLowerCase().equals("mode")) {
                                            mexprop = MEXPERF_10.mode;
                                        }else if (mea.getName().toLowerCase().equals("L2norm")) {
                                            mexprop = MEXPERF_10.L2norm;
                                        }else if (mea.getName().toLowerCase().equals("L1norm")) {
                                            mexprop = MEXPERF_10.L1norm;
                                        }else if (mea.getName().toLowerCase().equals("Linfnorm")) {
                                            mexprop = MEXPERF_10.Linfnorm;
                                        } else if (mea.getName().toLowerCase().equals("chebyschevDistance")){
                                            mexprop = MEXPERF_10.chebyschevDistance;
                                        }else if (mea.getName().toLowerCase().equals("hammingDistance")) {
                                            mexprop = MEXPERF_10.hammingDistance;
                                        }else if (mea.getName().toLowerCase().equals("euclideanDistance")) {
                                            mexprop = MEXPERF_10.euclideanDistance;
                                        }else if (mea.getName().toLowerCase().equals("manhattanDistance")) {
                                            mexprop = MEXPERF_10.manhattanDistance;
                                        }else if (mea.getName().toLowerCase().equals("genSimilarityCoefficient")) {
                                            mexprop = MEXPERF_10.genSimilarityCoerfficient;
                                        }else{
                                            throw new Exception("measure has not been founded");
                                        }

                                        _mea.addProperty(mexprop, mea.getValue().toString());

                                                /*
                                                create a mapping and implement it later...
                                                try{

                                                    for (Field f : mea.getClass().getFields()) {
                                                        f.setAccessible(true);
                                                        if (f.get(mea) != null) {
                                                            f.
                                                            //f.set(mea, getDefaultValueForType(f.getType()));
                                                        }
                                                    }

                                                }catch (Exception e){

                                                }
                                                */


                                                //.addProperty(PROVO.value, String.valueOf(mea.getValue()))
                                                //.addProperty(DCTerms.identifier, mea.getName());

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
package org.aksw.mex.log4mex;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.*;
import org.aksw.mex.log4mex.algo.AlgorithmParameterVO;
import org.aksw.mex.log4mex.core.*;
import org.aksw.mex.log4mex.perf.example.ExamplePerformanceMeasureVO;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by esteves on 25.06.15.
 */
public class MEXSerializer {

    private static       MEXSerializer instance = null;
    private static final Logger          LOGGER = LoggerFactory.getLogger(MEXSerializer.class);

    protected MEXSerializer() {}

    public static MEXSerializer getInstance() {
        if(instance == null) {
            instance = new MEXSerializer();
        }
        return instance;
    }

    private boolean parse(MyMEX mex) throws Exception {

        try{

            /* minimal set of classes to be implemented */

            if (!mex.getApplicationContext().hasValue()) {
                LOGGER.warn("[APPLICATION_CONTEXT]: missing the author name!");
                return false;
            }

            List<ExperimentConfigurationVO> configurations = mex.getExperimentConfigurations();
            for (int i = 0; i < configurations.size(); i++) {


                //dataset
                if (!configurations.get(i).DataSet().hasValue()) {
                    LOGGER.warn("[EXPERIMENT_CONFIGURATION]: missing parameters for dataset!");
                    return false;
                }

                /*
                //sampling method
                if (!configurations.get(i).SamplingMethod().hasValue()) {
                    LOGGER.warn("[EXPERIMENT_CONFIGURATION]: missing parameters for sampling method!");
                    return false;
                }
                */

                if (configurations.get(i).getAlgorithms() == null
                        || configurations.get(i).getAlgorithms().size() == 0){
                    LOGGER.warn("[EXPERIMENT_CONFIGURATION]: missing algorithm(s)!");
                    return false;
                }

                if (configurations.get(i).getExecutions() == null
                        || configurations.get(i).getExecutions().size() == 0){
                    LOGGER.warn("[EXPERIMENT_CONFIGURATION]: missing execution(s)!");
                    return false;
                }

                for (int j = 0; j < configurations.get(i).getExecutions().size(); j++) {
                    if (configurations.get(i).getExecutions().get(j).getPerformances() == null ||
                            configurations.get(i).getExecutions().get(j).getPerformances().size() == 0){
                                LOGGER.warn("[PERFORMANCE]: missing execution's performance for the execution index " + String.valueOf(j));
                                return false;
                            }
                }

            }

        }  catch (Exception e){
            LOGGER.error(e.toString());
            throw(e);
        }

        return true;

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

    private void writeJena(String filename, String URIbase, MyMEX mex, MEXConstant.EnumRDFFormats format) throws Exception{

        //cleanUpTheResources(mex);

        Model model = ModelFactory.createDefaultModel();


        try {

            setHeaders(model, URIbase);

            //common resources
            Resource provAgent = model.createResource(PROVO.NS + PROVO.ClasseTypes.AGENT);
            Resource provPerson = model.createResource(PROVO.NS + PROVO.ClasseTypes.PERSON);
            Resource provOrganization = model.createResource(PROVO.NS + PROVO.ClasseTypes.ORGANIZATION);
            Resource provEntity = model.createResource(PROVO.NS + PROVO.ClasseTypes.ENTITY);
            Resource provActivity = model.createResource(PROVO.NS + PROVO.ClasseTypes.ACTIVITY);
            Resource provCollection = model.createResource(PROVO.NS + PROVO.ClasseTypes.COLLECTION);

            //MEX-CORE
            Resource mexcore_APC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT);
            Resource mexcore_EXP_HEADER = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT);
            Resource mexcore_EXP_CONF = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT_CONFIGURATION);
            Resource mexcore_MODEL = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.MODEL);
            Resource mexcore_HARDWARE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.HARDWARE_CONFIGURATION);
            Resource mexcore_DATASET = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.DATASET);


            Resource mexcore_FEATURE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.FEATURE);
            Resource mexcore_EXAMPLE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXAMPLE);
            Resource mexcore_EXAMPLE_COLLECTION = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXAMPLE_COLLECTION);

            //MEX-ALGO
            Resource mexalgo_ALGO_PARAM = model.createResource(MEXALGO_10.NS + MEXALGO_10.ClasseTypes.ALGORITHM_PARAMETER);
            Resource mexalgo_ALGO = model.createResource(MEXALGO_10.NS + MEXALGO_10.ClasseTypes.ALGORITHM);

            //MEX-PERF
            Resource mexperf_EXECUTION_PERFORMANCE = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.EXECUTION_PERFORMANCE);


            //mex-core
            Resource _application = null;
            Resource _context;
            Resource _version;
            Resource _organization;
            Resource _expHeader;

            //gets
            if (mex.getApplicationContext() != null) {
                _application = model.createResource(URIbase + "application")
                        //.addProperty(RDF.type, provAgent)
                        //.addProperty(RDF.type, provPerson)
                        //.addProperty(RDF.type, provOrganization)
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

                    mex.getApplicationContext().getContext().setLabel(setLabelSplitingTerms(mex.getApplicationContext().getContext().get_context().toString()));

                    Resource mexcore_CON = model.createResource(MEXCORE_10.NS + mex.getApplicationContext().getContext().get_context());
                    _context = model.createResource(URIbase + "context")
                            //.addProperty(RDF.type, provEntity)
                            .addProperty(RDF.type, mexcore_CON)
                            .addProperty(RDFS.label, mex.getApplicationContext().getContext().getLabel())
                            .addProperty(PROVO.wasAttributedTo, _application);
                }


                Resource mex_version = model.createResource(MEXCORE_10.NS + "version");
                _version = model.createResource(URIbase + "version")
                        .addProperty(DCTerms.hasVersion, this.getVersion());

            }

            //EXPERIMENT
            if (mex.getExperiment() != null) {
                _expHeader = model.createResource(URIbase + "experiment-header")
                        //.addProperty(RDF.type, provEntity)
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
                int auxExpConf = 1;
                for (Iterator<ExperimentConfigurationVO> i = mex.getExperimentConfigurations().iterator(); i.hasNext(); ) {
                    ExperimentConfigurationVO item = i.next();

                    Resource _expConfiguration = model.createResource(URIbase + "exp_config_" + auxExpConf)
                            //.addProperty(RDF.type, provActivity)
                            .addProperty(RDF.type, mexcore_EXP_CONF)
                            .addProperty(RDFS.label, item.getLabel());
                    if(StringUtils.isNotEmpty(item.getId()) && StringUtils.isNotBlank(item.getId())) {
                        _expConfiguration.addProperty(DCTerms.identifier, item.getId());
                    }
                    if(StringUtils.isNotEmpty(item.getDescription()) && StringUtils.isNotBlank(item.getDescription())) {
                        _expConfiguration.addProperty(DCTerms.description, item.getDescription());
                    }


                    //MODEL
                    if (item.Model() != null && item.Model().hasValue()) {
                        boolean atLeastOne = false;

                        Resource _model = model.createResource(URIbase + "model" + "_conf_" + auxExpConf)
                                //.addProperty(RDF.type, provEntity)
                                .addProperty(RDF.type, mexcore_MODEL);

                        if (StringUtils.isNotBlank(item.Model().getId()) &&
                                StringUtils.isNotEmpty(item.Model().getId())) {
                            _model.addProperty(DCTerms.identifier, item.Model().getId());
                            atLeastOne=true;
                        }

                        if (StringUtils.isNotBlank(item.Model().getDescription()) &&
                                StringUtils.isNotEmpty(item.Model().getDescription())) {
                            _model.addProperty(DCTerms.description, item.Model().getDescription());
                            atLeastOne=true;
                        }

                        if (item.Model().getDate() != null) {
                            _model.addProperty(DCTerms.date, item.Model().getDate().toString());
                            atLeastOne=true;
                        }

                        if (atLeastOne) {
                            _expConfiguration.addProperty(PROVO.used, _model);

                        }

                    }
                    //TOOL
                    if (item.Tool() != null && item.Tool().hasValue()) {
                        if (StringUtils.isNotBlank(item.Tool().getName()) && StringUtils.isNotEmpty(item.Tool().getName())) {

                            Resource _tool = model.createResource(MEXALGO_10.NS + item.Tool().getName());
                            Resource _imp = model.createResource(URIbase + "tool" + "_conf_" + auxExpConf)
                                    //.addProperty(RDF.type, provEntity)
                                    .addProperty(RDF.type, _tool);
                            _expConfiguration.addProperty(PROVO.used, _imp);}
                    }
                    //SAMPLING METHOD
                    if (item.SamplingMethod() != null && item.SamplingMethod().hasValue()) {

                        item.SamplingMethod().setLabel(setLabelSplitingTerms(item.SamplingMethod().getClassName()));

                        Resource mexcore_SAMPLING_METHOD = model.createResource(MEXCORE_10.NS + item.SamplingMethod().getClassName());

                        Resource _sampling = model.createResource(URIbase + "sampling_method" + "_conf_" + auxExpConf)
                                //.addProperty(RDF.type, provEntity)
                                .addProperty(RDFS.label, item.SamplingMethod().getLabel())
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
                    if (item.HardwareConfiguration() != null && item.HardwareConfiguration().hasValue()) {

                        Resource _hardware = model.createResource(URIbase + "hardware" + "_conf_" + auxExpConf)
                                //.addProperty(RDF.type, provEntity)
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
                        _hardware.addProperty(RDFS.label, "Hardware configuration");

                        _expConfiguration.addProperty(PROVO.used, _hardware);
                    }

                    //DATASET
                    if (item.DataSet() != null && item.DataSet().hasValue()) {

                        Resource _dataset = model.createResource(URIbase + "dataset" + "_conf_" + auxExpConf)
                                //.addProperty(RDF.type, provEntity)
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
                            Resource _feature = model.createResource(URIbase + "feature_" + String.valueOf(auxf) + "_conf_" + auxExpConf)
                                    //.addProperty(RDF.type, provEntity)
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
                    if (item.getExecutions()!= null && item.getExecutions().size() > 0){

                        int auxe = 1;
                        for (Iterator<Execution> iexec = item.getExecutions().iterator(); iexec.hasNext(); ) {
                            Execution e = iexec.next();
                            Resource _exec = null;
                            if (e != null) {

                                _exec = model.createResource(URIbase + "exec_" + String.valueOf(e.getId().toLowerCase()) + "_conf_" + auxExpConf);

                                //EXECUTION
                                Resource mexcore_EXEC = null;

                                if (iexec instanceof ExecutionIndividualVO){
                                    mexcore_EXEC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXECUTION_SINGLE);
                                    _exec.addProperty(RDF.type, mexcore_EXEC);
                                    _exec.addProperty(RDFS.label, "It represents an execution over a single example of given dataset");
                                }else{
                                    mexcore_EXEC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXECUTION_OVERALL);
                                    _exec.addProperty(RDF.type, mexcore_EXEC);
                                    _exec.addProperty(RDFS.label, "It represents a set of executions over some examples of given dataset");
                                }

                                _exec.addProperty(PROVO.id, e.getId());


                                if (e.getStartedAtTime() != null) {
                                    _exec.addProperty(PROVO.startedAtTime, e.getStartedAtTime().toString());
                                }
                                if (e.getEndedAtTime() != null) {
                                    _exec.addProperty(PROVO.endedAtTime, e.getEndedAtTime().toString());
                                }
                                if (e.getTargetClass() != null && StringUtils.isNotEmpty(e.getTargetClass())) {
                                    _exec.addProperty(MEXCORE_10.targetClass, e.getTargetClass());
                                }

                                if (e instanceof ExecutionSetVO) {
                                    ExecutionSetVO temp = (ExecutionSetVO) e;
                                    if (temp.getStartsAtPosition() != null) {
                                        _exec.addProperty(MEXCORE_10.startsAtPosition, temp.getStartsAtPosition());
                                    }
                                    if (temp.getEndsAtPosition() != null) {
                                        _exec.addProperty(MEXCORE_10.endsAtPosition, temp.getEndsAtPosition());
                                    }
                                    _exec.addProperty(MEXCORE_10.group, model.createTypedLiteral("true", XSDDatatype.XSDboolean));
                                }else{
                                    _exec.addProperty(MEXCORE_10.group, model.createTypedLiteral("false", XSDDatatype.XSDboolean));
                                }



                                //EXECUTION PERFORMANCE
                                Resource _execPerformance = model.createResource(URIbase + "execPerformance_" + auxe + "_conf_" + auxExpConf)
                                        //.addProperty(RDF.type, provActivity)
                                        .addProperty(RDF.type, mexperf_EXECUTION_PERFORMANCE)
                                        .addProperty(RDFS.label, "The performance measures generated by an execution")
                                        .addProperty(PROVO.wasInformedBy, _exec);



                                //PHASE
                                if (e.getPhase() != null){
                                    Resource mexcore_PHASE = model.createResource(MEXCORE_10.NS + e.getPhase().getName());
                                    if (StringUtils.isNotBlank(e.getPhase().getName().toString()) && StringUtils.isNotEmpty(e.getPhase().getName().toString())) {
                                    Resource _phase = model.createResource(URIbase + "phase_" + e.getPhase().getName() + "_conf_" + auxExpConf)
                                            //.addProperty(RDF.type, provEntity)
                                            .addProperty(RDFS.label, e.getPhase().getLabel())
                                            .addProperty(RDF.type, mexcore_PHASE);
                                    _exec.addProperty(PROVO.used, _phase);}
                                }

                                //EXAMPLE (the set of examples)
                                if (e.getExamples() != null && e.getExamples().size() > 0) {
                                    Integer auxex = 1;
                                    for (Iterator<ExampleVO> iexample = e.getExamples().iterator(); iexample.hasNext(); ) {
                                        ExampleVO example = iexample.next();
                                        if (example != null) {
                                            Resource _ex = model.createResource(URIbase + "example_" + String.valueOf(auxex) + "_conf_" + auxExpConf)
                                                    //.addProperty(RDF.type, provEntity)
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

                                    Resource _alg = model.createResource(URIbase + e.getAlgorithm().getIndividualName() + "_conf_" + auxExpConf)
                                            //.addProperty(RDF.type, provEntity)
                                            .addProperty(RDF.type, mexalgo_ALGO)
                                            .addProperty(RDFS.label, e.getAlgorithm().getLabel());

                                    if (StringUtils.isNotBlank(e.getAlgorithm().getClassName()) && StringUtils.isNotEmpty(e.getAlgorithm().getClassName())) {
                                        Resource _algClass = model.createResource(MEXALGO_10.NS + e.getAlgorithm().getClassName());
                                        _alg.addProperty(MEXALGO_10.hasAlgorithmClass,_algClass );
                                    }


                                        _exec.addProperty(PROVO.used, _alg);

                                    //id
                                    if (StringUtils.isNotBlank(e.getAlgorithm().getIdentifier()) && StringUtils.isNotEmpty(e.getAlgorithm().getIdentifier())) {
                                        _alg.addProperty(DCTerms.identifier, e.getAlgorithm().getIdentifier());}
                                    //label
                                    if (StringUtils.isNotBlank(e.getAlgorithm().getLabel()) && StringUtils.isNotEmpty(e.getAlgorithm().getLabel())) {
                                        _alg.addProperty(RDFS.label, e.getAlgorithm().getLabel());}
                                    //uri
                                    if (e.getAlgorithm().getURI() != null && StringUtils.isNotBlank(e.getAlgorithm().getURI().toURL().toString()) && StringUtils.isNotEmpty(e.getAlgorithm().getURI().toURL().toString())) {
                                        _alg.addProperty(DCAT.landingPage, e.getAlgorithm().getURI().toURL().toString());}
                                    //acronym
                                    if (StringUtils.isNotBlank(e.getAlgorithm().getAcronym()) && StringUtils.isNotEmpty(e.getAlgorithm().getAcronym())) {
                                        _alg.addProperty(MEXALGO_10.acronym, e.getAlgorithm().getAcronym());}


                                    _exec.addProperty(PROVO.used, _alg);

                                    //ALGORITHM PARAMETER
                                    if (e.getAlgorithm().getParameters() != null && e.getAlgorithm().getParameters().size() > 0) {
                                        Integer auxparam = 1;
                                        for(Iterator<AlgorithmParameterVO> iparam = e.getAlgorithm().getParameters().iterator(); iparam.hasNext(); ) {
                                            AlgorithmParameterVO algoParam = iparam.next();
                                            if (algoParam != null) {
                                                Resource _algoParam = null;
                                                _algoParam = model.createResource(URIbase + "hyper_param_" + String.valueOf(auxparam) + "_conf_" + auxExpConf)
                                                        //.addProperty(RDF.type, provEntity)
                                                        .addProperty(RDF.type, mexalgo_ALGO_PARAM)
                                                        .addProperty(RDFS.label, algoParam.getLabel())
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
                                    //Integer auxmea = 1;

                                    for(Iterator<Measure> imea = e.getPerformances().iterator(); imea.hasNext(); ) {
                                        Measure mea = imea.next();
                                        if (mea != null) {

                                            Resource mexperf_klass = null, mexperf_cla = null,     mexperf_reg = null,     mexperf_clu = null,     mexperf_sta = null, mexperf_custom = null , mexperf_example = null;
                                            Resource mexperf_ins = null,   mexperf_cla_ins = null, mexperf_reg_ins = null, mexperf_clu_ins = null, mexperf_sta_ins = null, mexperf_custom_ins = null, mexperf_example_ins = null;

                                            if (mea instanceof ClassificationMeasureVO){

                                                if (mexperf_cla == null){
                                                    mexperf_cla_ins = model.createResource(URIbase + "measure_classification_" + String.valueOf(e.getId()) + "_conf_" + auxExpConf); //+ "_" + String.valueOf(auxmea));
                                                    mexperf_cla = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.CLASSIFICATION_MEASURE);
                                                }

                                                mexperf_klass = mexperf_cla;
                                                mexperf_ins = mexperf_cla_ins;

                                            }else if (mea instanceof RegressionMeasureVO){

                                                if (mexperf_reg == null){
                                                    mexperf_reg_ins = model.createResource(URIbase + "measure_regression_" + String.valueOf(e.getId()) + "_conf_" + auxExpConf); // + "_" + String.valueOf(auxmea));
                                                    mexperf_reg = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.REGRESSION_MEASURE);
                                                }

                                                mexperf_klass = mexperf_reg;
                                                mexperf_ins = mexperf_reg_ins;

                                            }else if (mea instanceof ClusteringMeasureVO){

                                                if (mexperf_clu == null){
                                                    mexperf_clu_ins = model.createResource(URIbase + "measure_clustering_" + String.valueOf(e.getId()) + "_conf_" + auxExpConf); // + "_" + String.valueOf(auxmea));
                                                    mexperf_clu = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.CLUSTERING_MEASURE);
                                                }

                                                mexperf_klass = mexperf_clu;
                                                mexperf_ins = mexperf_clu_ins;

                                            }else if (mea instanceof StatisticalMeasureVO){

                                                if (mexperf_sta == null){
                                                    mexperf_sta_ins = model.createResource(URIbase + "measure_statistical_" + String.valueOf(e.getId()) + "_conf_" + auxExpConf); // + "_" + String.valueOf(auxmea));
                                                    mexperf_sta = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.STATISTICAL_MEASURE);
                                                }

                                                mexperf_klass = mexperf_sta;
                                                mexperf_ins = mexperf_sta_ins;

                                            }else if (mea instanceof UserDefinedMeasureVO){

                                                if (mexperf_custom == null){
                                                    mexperf_custom_ins = model.createResource(URIbase + "measure_custom_" + String.valueOf(e.getId()) + "_conf_" + auxExpConf); // + "_" + String.valueOf(auxmea));
                                                    mexperf_custom = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.USER_DEFINED_MEASURE);
                                                }

                                                mexperf_klass = mexperf_custom;
                                                mexperf_ins = mexperf_custom_ins;

                                            }
                                            else if (mea instanceof ExamplePerformanceMeasureVO){

                                                if (mexperf_example == null){
                                                    mexperf_example_ins = model.createResource(URIbase + "measure_example_" + String.valueOf(e.getId()) + "_conf_" + auxExpConf); // + "_" + String.valueOf(auxmea));
                                                    mexperf_example = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.USER_DEFINED_MEASURE);
                                                }

                                                mexperf_klass = mexperf_example;
                                                mexperf_ins = mexperf_example_ins;

                                            }

                                            //mexperf_ins.addProperty(RDF.type, provEntity);
                                            mexperf_ins.addProperty(RDF.type, mexperf_klass);
                                            mexperf_ins.addProperty(RDFS.label, mea.getLabel());
                                            mexperf_ins.addProperty(model.createProperty(MEXPERF_10.NS + mea.getName()), model.createTypedLiteral(mea.getValue()));
                                            mexperf_ins.addProperty(PROVO.wasInformedBy, _exec);

                                            _execPerformance.addProperty(PROVO.generated, mexperf_ins);

                                            //auxmea++;
                                        }
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
                    auxExpConf++;

                    }

                }

                FileWriter out;
                try {

                    out = new FileWriter(filename + "." + format.toString().toLowerCase().replace("/","").replace(".","").replace("-",""));

                    model.write(out, format.toString());

                    out.close();

                } catch (Exception e) {

                    LOGGER.error(e.toString());

                    throw(e);
                }

        }catch (Exception e){

            LOGGER.error(e.toString());

            throw(e);
        }

    }

    private String getVersion() {
        String path = "/version.prop";
        InputStream stream = getClass().getClass().getResourceAsStream(path);
        if (stream == null)
            return "UNKNOWN";
        Properties props = new Properties();
        try {
            props.load(stream);
            stream.close();
            return (String) props.get("version");
        } catch (IOException e) {
            return "UNKNOWN";
        }
    }

    private String setLabelSplitingTerms(String value){

        String auxLabel = "";

        if (!value.isEmpty()) {

            auxLabel = value.substring(0,1);
            for (int i = 1; i < value.length(); i++) {

                if (!value.substring(i, i+1).toString().isEmpty() &&
                        value.substring(i, i+1).toString().toUpperCase().equals(value.substring(i, i+1).toString())) {
                    auxLabel += " " + value.substring(i, i+1).toString() ;
                } else {
                    auxLabel += value.substring(i, i+1).toString();
                }
            }
        }

        return auxLabel;
    }

    public void saveToDisk(String filename, String URIbase, MyMEX mex, MEXConstant.EnumRDFFormats format) throws Exception {
        try{
            if (parse(mex)){
                writeJena(filename, URIbase, mex, format);
            }else{
                throw new Exception("The MEX file could not be generated. Please see the log for more details");
            }
        }catch (Exception e){
            LOGGER.error(e.toString());
            throw(e);
        }
    }

}
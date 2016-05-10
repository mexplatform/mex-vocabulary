package org.aksw.mex.log4mex;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.*;
import org.aksw.mex.log4mex.algo.HyperParameterVO;
import org.aksw.mex.log4mex.algo.ToolParameterVO;
import org.aksw.mex.log4mex.core.ExampleVO;
import org.aksw.mex.log4mex.core.ExecutionIndividualVO;
import org.aksw.mex.log4mex.core.ExecutionSetVO;
import org.aksw.mex.log4mex.core.FeatureVO;
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
import java.util.Random;

/**
 * Created by esteves on 25.06.15.
 */
public class MEXSerializer {

    private static       MEXSerializer instance = null;
    private static final Logger          LOGGER = LoggerFactory.getLogger(MEXSerializer.class);
    private static String userHash;


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

            if (mex.getExperiment() == null || StringUtils.isEmpty(mex.getExperiment().getId()) ||  StringUtils.isBlank(mex.getExperiment().getId())) {
                LOGGER.warn("[EXPERIMENT]: missing experiment id!");
                return false;
            }

            if (!mex.getApplicationContext().hasValue()) {
                LOGGER.warn("[APPLICATION_CONTEXT]: missing author name and email!");
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

            Random rand = new Random();
            int  n = rand.nextInt(31999) + 1;
            this.userHash  = String.valueOf(String.valueOf((mex.getApplicationContext().get_mbox()) +
                    String.valueOf(mex.getApplicationContext().get_givenName()) + mex.getExperiment().getId() + n).hashCode());

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
            Resource provOrganization = model.createResource(PROVO.NS + PROVO.ClasseTypes.ORGANIZATION);


            //MEX-CORE
            Resource mexcore_APC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.APPLICATION_CONTEXT);
            Resource mexcore_EXP_HEADER = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT);
            Resource mexcore_EXP_CONF = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXPERIMENT_CONFIGURATION);
            Resource mexcore_MODEL = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.MODEL);
            Resource mexcore_HARDWARE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.HARDWARE_CONFIGURATION);
            Resource mexcore_DATASET = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.DATASET);
            Resource mexcore_FEATURE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.FEATURE);
            Resource mexcore_FEATURE_COLLECTION = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.FEATURE_COLLECTION);
            Resource mexcore_EXAMPLE = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXAMPLE);
            Resource mexcore_EXAMPLE_COLLECTION = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXAMPLE_COLLECTION);



            //MEX-ALGO
            Resource mexalgo_ALGO = model.createResource(MEXALGO_10.NS + MEXALGO_10.ClasseTypes.ALGORITHM);
            Resource mexalgo_HYPER_PARAM = model.createResource(MEXALGO_10.NS + MEXALGO_10.ClasseTypes.HYPER_PARAMETER);
            Resource mexalgo_HYPER_PARAM_COLLECTION = model.createResource(MEXALGO_10.NS + MEXALGO_10.ClasseTypes.HYPER_PARAMETER_COLLECTION);

            Resource mexalgo_TOOL_PARAM = model.createResource(MEXALGO_10.NS + MEXALGO_10.ClasseTypes.TOOL_PARAMETER);
            Resource mexalgo_TOOL_PARAM_COLLECTION = model.createResource(MEXALGO_10.NS + MEXALGO_10.ClasseTypes.TOOL_PARAMETER_COLLECTION);

            //MEX-PERF
            Resource mexperf_EXAMPLE_PERFORMANCE_COLLECTION = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.EXAMPLE_PERFORMANCE_COLLECTION);
            Resource mexperf_EXAMPLE_PERFORMANCE_MEASURE = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.EXAMPLE_PERFORMANCE_MEASURE);

            Resource mexperf_USER_DEFINED_PERFORMANCE_COLLECTION = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.USER_DEFINED_MEASURE_COLLECTION);
            Resource mexperf_USER_DEFINED_PERFORMANCE_MEASURE = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.USER_DEFINED_MEASURE);


            //mex-core
            Resource _application = null;
            Resource _context;
            Resource _version;
            Resource _organization;
            Resource _expHeader = null;

            //gets
            if (mex.getApplicationContext() != null) {
                _application = model.createResource(URIbase + "app_" + this.userHash)
                        //.addProperty(RDF.type, provAgent)
                        //.addProperty(RDF.type, provPerson)
                        //.addProperty(RDF.type, provOrganization)
                        .addProperty(RDFS.label,FOAF.givenName)
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
                    _application.addProperty(MEXCORE_10.trustyURI, mex.getApplicationContext().get_trustyURI());
                }

                if (mex.getApplicationContext().get_organization() != null) {
                    //Resource mexcore_ORG = model.createResource(MEXCORE_10.NS + mex.getApplicationContext().get_organization());
                    _organization = model.createResource(URIbase + "org_" + this.userHash)
                            .addProperty(RDF.type, provOrganization)
                            .addProperty(FOAF.givenName, mex.getApplicationContext().get_organization());
                    _application.addProperty(PROVO.actedOnBehalfOf, _organization);
                }

                if (mex.getApplicationContext().getContext() != null) {

                    mex.getApplicationContext().getContext().setLabel(setLabelSplitingTerms(mex.getApplicationContext().getContext().get_context().toString()));

                    Resource mexcore_CON = model.createResource(MEXCORE_10.NS + mex.getApplicationContext().getContext().get_context());
                    _context = model.createResource(URIbase + "cxt_"  + this.userHash)
                            .addProperty(RDF.type, mexcore_CON)
                            .addProperty(RDFS.label, mex.getApplicationContext().getContext().getLabel())
                            .addProperty(PROVO.wasAttributedTo, _application);
                }

                _version = model.createResource(URIbase + "version").addProperty(DCTerms.hasVersion, this.getVersion());

            }

            //EXPERIMENT
            if (mex.getExperiment() != null) {
                _expHeader = model.createResource(URIbase + "exp_"  + this.userHash)
                        //.addProperty(RDF.type, provEntity)
                        .addProperty(RDF.type, mexcore_EXP_HEADER)
                        .addProperty(MEXCORE_10.experimentHash, this.userHash);
                        if(StringUtils.isNotEmpty(mex.getExperiment().getId()) && StringUtils.isNotBlank(mex.getExperiment().getId())) {
                            _expHeader.addProperty(DCTerms.identifier, mex.getExperiment().getId());
                            _expHeader.addProperty(RDFS.label, "Experiment: " + mex.getExperiment().getId());
                        }
                        if(StringUtils.isNotEmpty(mex.getExperiment().getTitle()) && StringUtils.isNotBlank(mex.getExperiment().getTitle())) {
                            _expHeader.addProperty(DCTerms.title, mex.getExperiment().getTitle());
                        }
                        if(mex.getExperiment().getDate() != null) {
                            DateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                            try{
                                df.setLenient(false);
                                df.parse(mex.getExperiment().getDate().toString());
                                _expHeader.addProperty(DCTerms.date, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(mex.getExperiment().getDate()));
                            }catch (ParseException e) {}
                        }
                        if(StringUtils.isNotEmpty(mex.getExperiment().getAttributeSelectionDescription()) && StringUtils.isNotBlank(mex.getExperiment().getAttributeSelectionDescription())) {
                            _expHeader.addProperty(MEXCORE_10.attributeSelectionDescription, mex.getExperiment().getAttributeSelectionDescription());
                        }
                        if(StringUtils.isNotEmpty(mex.getExperiment().getDataNormalizedDescription()) && StringUtils.isNotBlank(mex.getExperiment().getDataNormalizedDescription())) {
                            _expHeader.addProperty(MEXCORE_10.dataNormalizedDescription, mex.getExperiment().getDataNormalizedDescription());
                        }
                        if(StringUtils.isNotEmpty(mex.getExperiment().getNoiseRemovedDescription()) && StringUtils.isNotBlank(mex.getExperiment().getNoiseRemovedDescription())) {
                            _expHeader.addProperty(MEXCORE_10.noiseRemovedDescription, mex.getExperiment().getNoiseRemovedDescription());
                        }
                        if(StringUtils.isNotEmpty(mex.getExperiment().getOutliersRemovedDescription()) && StringUtils.isNotBlank(mex.getExperiment().getOutliersRemovedDescription())) {
                            _expHeader.addProperty(MEXCORE_10.outliersRemovedDescription, mex.getExperiment().getOutliersRemovedDescription());
                        }
                        if(StringUtils.isNotEmpty(mex.getExperiment().getDescription()) && StringUtils.isNotBlank(mex.getExperiment().getDescription())) {
                            _expHeader.addProperty(DCTerms.description, mex.getExperiment().getDescription());
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

                    Resource _expConfiguration = model.createResource(URIbase + "exp_cf_" + auxExpConf + "_" + this.userHash)
                            //.addProperty(RDF.type, provActivity)
                            .addProperty(RDFS.label, "Experiment Configuration")
                            .addProperty(RDF.type, mexcore_EXP_CONF)
                            .addProperty(PROVO.wasStartedBy, _expHeader)
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

                        Resource _model = model.createResource(URIbase + "mod" + "_cf_" + auxExpConf + "_"  + this.userHash)
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
                    Resource _imp = null;
                    //TOOL
                    if (item.Tool() != null && item.Tool().hasValue()) {
                        if (StringUtils.isNotBlank(item.Tool().getName()) && StringUtils.isNotEmpty(item.Tool().getName())) {

                            Resource _tool = model.createResource(MEXALGO_10.NS + item.Tool().getName());
                            _imp = model.createResource(URIbase + "tool" + "_cf_" + auxExpConf + "_"  + this.userHash)
                                    .addProperty(RDFS.label, setLabelSplitingTerms(item.Tool().getName()))
                                    .addProperty(RDF.type, _tool);
                            _expConfiguration.addProperty(PROVO.used, _imp);}

                        //TOOL PARAMETER
                        if (item.getToolParameters() != null && item.getToolParameters().size() > 0) {

                            //COLLECTION
                            Resource _toolcollection = model.createResource(URIbase + "tool_par_col_" + "cf_" + auxExpConf + "_" + this.userHash)
                                    .addProperty(RDF.type, mexalgo_TOOL_PARAM_COLLECTION)
                                    .addProperty(RDFS.label, "Tool Parameter Collection");
                            _expConfiguration.addProperty(PROVO.used, _toolcollection);

                            //TOOL PARAMETER
                            Integer auxtoolparam = 1;
                            for(Iterator<ToolParameterVO> iparam = item.getToolParameters().iterator(); iparam.hasNext(); ) {
                                ToolParameterVO toolParam = iparam.next();
                                if (toolParam != null) {
                                    Resource _toolParam = model.createResource(URIbase + "tool_par_" + String.valueOf(auxtoolparam) + "cf_" + auxExpConf + "_" + this.userHash)
                                            .addProperty(RDF.type, mexalgo_TOOL_PARAM)
                                            .addProperty(RDFS.label, toolParam.getLabel())
                                            .addProperty(PROVO.value, toolParam.getValue())
                                            .addProperty(DCTerms.identifier, toolParam.getId());

                                    _imp.addProperty(MEXALGO_10.hasToolParameter,_toolParam);
                                    _toolcollection.addProperty(PROVO.hadMember, _toolParam);
                                    auxtoolparam++;}
                            }
                        }
                    }

                    //SAMPLING METHOD
                    if (item.SamplingMethod() != null && item.SamplingMethod().hasValue()) {

                        item.SamplingMethod().setLabel(setLabelSplitingTerms(item.SamplingMethod().getClassName()));

                        Resource mexcore_SAMPLING_METHOD = model.createResource(MEXCORE_10.NS + item.SamplingMethod().getClassName());

                        Resource _sampling = model.createResource(URIbase + "sm" + "_cf_" + auxExpConf + "_" + this.userHash)
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

                        Resource _hardware = model.createResource(URIbase + "hard" + "_cf_" + auxExpConf + "_"  + this.userHash)
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

                        Resource _dataset = model.createResource(URIbase + "ds" + "_cf_" + auxExpConf + "_"  + this.userHash)
                                .addProperty(RDFS.label, "Dataset " + item.DataSet().getLabel())
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


                    //FEATURE COLLECTION
                    if (item.getFeatures() != null && item.getFeatures().size() > 0){

                        Resource _featurecollection = model.createResource(URIbase + "fea_col" + "_cf_" + auxExpConf + "_"  + this.userHash)
                                .addProperty(RDF.type, mexcore_FEATURE_COLLECTION)
                                .addProperty(RDFS.label, "Feature Collection");

                        _expConfiguration.addProperty(PROVO.used, _featurecollection);

                        //FEATURE
                        int auxf = 1;
                        for (Iterator<FeatureVO> ifeature = item.getFeatures().iterator(); ifeature.hasNext(); ) {
                            FeatureVO f = ifeature.next();
                            if (f != null) {
                                Resource _feature = model.createResource(URIbase + "fea_" + String.valueOf(auxf) + "_cf_" + auxExpConf + "_"  + this.userHash)
                                        //.addProperty(RDF.type, provEntity)
                                        .addProperty(RDF.type, mexcore_FEATURE);

                                if (StringUtils.isNotBlank(f.getId()) && StringUtils.isNotEmpty(f.getId())) {
                                    _feature.addProperty(DCTerms.identifier, f.getId());
                                }
                                if (StringUtils.isNotBlank(f.getName()) && StringUtils.isNotEmpty(f.getName())) {
                                    _feature.addProperty(RDFS.label, f.getName());
                                }

                                //_expConfiguration.addProperty(PROVO.used, _feature);
                                auxf++;

                                _featurecollection.addProperty(PROVO.hadMember, _feature);
                            }
                        }



                    }

                    //EXECUTIONS (e)
                    if (item.getExecutions()!= null && item.getExecutions().size() > 0){
                        int auxexecutions = 1;

                        for (Iterator<Execution> iexec = item.getExecutions().iterator(); iexec.hasNext(); ) {
                            Execution e = iexec.next();
                            Resource _exec = null;
                            if (e != null) {

                                _exec = model.createResource(URIbase + "exe_" + String.valueOf(auxexecutions) + "_cf_" + auxExpConf + "_"  + this.userHash);

                                //EXECUTION
                                Resource mexcore_EXEC = null;

                                if (iexec instanceof ExecutionIndividualVO){
                                    mexcore_EXEC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXECUTION_SINGLE);
                                    _exec.addProperty(RDF.type, mexcore_EXEC);
                                    _exec.addProperty(RDFS.label, "Single Execution: " + e.getId());
                                }else{
                                    mexcore_EXEC = model.createResource(MEXCORE_10.NS + MEXCORE_10.ClasseTypes.EXECUTION_OVERALL);
                                    _exec.addProperty(RDF.type, mexcore_EXEC);
                                    _exec.addProperty(RDFS.label, "Overall Execution: " + e.getId());
                                }

                                _exec.addProperty(PROVO.id, e.getId());


                                if (e.getStartedAtTime() != null) {
                                    _exec.addProperty(PROVO.startedAtTime, e.getStartedAtTime().toString());
                                }
                                if (e.getEndedAtTime() != null) {
                                    _exec.addProperty(PROVO.endedAtTime, e.getEndedAtTime().toString());
                                }
                                if (e.getTargetClass() != null && StringUtils.isNotEmpty(e.getTargetClass()) && StringUtils.isNotBlank(e.getTargetClass())) {
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



                                //PERFORMANCE MEASURE -> super class, there is no need to create it
                                /*
                                Resource _execPerformance = model.createResource(URIbase + "performanceMeasures_" + auxe + "_conf_" + auxExpConf + "_"  + this.userHash)
                                        //.addProperty(RDF.type, provActivity)
                                        .addProperty(RDF.type, mexperf_PERFORMANCE_MEASURE)
                                        .addProperty(RDFS.label, "The performance measures generated by an execution")
                                        .addProperty(PROVO.wasInformedBy, _exec);
                                */


                                //PHASE
                                if (e.getPhase() != null){
                                    Resource mexcore_PHASE = model.createResource(MEXCORE_10.NS + e.getPhase().getName());
                                    if (StringUtils.isNotBlank(e.getPhase().getName().toString()) && StringUtils.isNotEmpty(e.getPhase().getName().toString())) {
                                    Resource _phase = model.createResource(URIbase + "phase_" + e.getPhase().getName() + "cf" + auxExpConf + "_"  + this.userHash)
                                            //.addProperty(RDF.type, provEntity)
                                            .addProperty(RDFS.label, e.getPhase().getLabel())
                                            .addProperty(RDF.type, mexcore_PHASE);
                                    _exec.addProperty(PROVO.used, _phase);}
                                }


                                //EXAMPLE COLLECTION
                                if (e.getExamples() != null && e.getExamples().size() > 0) {

                                    Resource _examplecollection = model.createResource(URIbase + "exa_col_" + "_cf_" + auxExpConf + "_exe_" + String.valueOf(auxexecutions) + "_" + this.userHash)
                                            .addProperty(RDF.type, mexcore_EXAMPLE_COLLECTION)
                                            .addProperty(RDFS.label, "Example Collection");

                                    _exec.addProperty(PROVO.used, _examplecollection);

                                    //EXAMPLE
                                    Integer auxex = 1;
                                    for (Iterator<ExampleVO> iexample = e.getExamples().iterator(); iexample.hasNext(); ) {
                                        ExampleVO example = iexample.next();
                                        if (example != null) {
                                            Resource _ex = model.createResource(URIbase + "exa" + String.valueOf(auxex) + "cf" + auxExpConf + "_"  + this.userHash)
                                                    .addProperty(RDFS.label, "Dataset Example " + example.getId())
                                                    .addProperty(RDF.type, mexcore_EXAMPLE)
                                                    .addProperty(DCTerms.identifier, example.getId())
                                                    .addProperty(PROVO.value, example.getValue());

                                            if (example.getDatasetColumn() != 0) {
                                                _ex.addProperty(MEXCORE_10.datasetColumn, String.valueOf(example.getDatasetColumn()));
                                            }
                                            if (example.getDatasetRow() != 0) {
                                                _ex.addProperty(MEXCORE_10.datasetRow, String.valueOf(example.getDatasetRow()));
                                            }

                                            //_exec.addProperty(PROVO.used, _ex);
                                            _examplecollection.addProperty(PROVO.hadMember, _ex);
                                            auxex++;
                                        }
                                    }

                                }

                                //ALGORITHM
                                if (e.getAlgorithm() != null) {

                                    Resource _alg = model.createResource(URIbase + e.getAlgorithm().getIndividualName() + "cf" + auxExpConf + "_"  + this.userHash)
                                            //.addProperty(RDF.type, provEntity)
                                            .addProperty(RDF.type, mexalgo_ALGO);


                                    if (StringUtils.isNotBlank(e.getAlgorithm().getClassName()) && StringUtils.isNotEmpty(e.getAlgorithm().getClassName())) {

                                        Resource _algClass = model.createResource(MEXALGO_10.NS + e.getAlgorithm().getClassName());
                                        _algClass.addProperty(RDFS.label, setLabelSplitingTerms(e.getAlgorithm().getClassName()));

                                        _alg.addProperty(MEXALGO_10.hasAlgorithmClass, _algClass );
                                        _alg.addProperty(RDFS.label, setLabelSplitingTerms(e.getAlgorithm().getClassName()));
                                    }else{
                                        //label
                                        if (StringUtils.isNotBlank(e.getAlgorithm().getLabel()) && StringUtils.isNotEmpty(e.getAlgorithm().getLabel())) {
                                            _alg.addProperty(RDFS.label, e.getAlgorithm().getLabel());}
                                    }


                                        _exec.addProperty(PROVO.used, _alg);

                                    //id
                                    if (StringUtils.isNotBlank(e.getAlgorithm().getIdentifier()) && StringUtils.isNotEmpty(e.getAlgorithm().getIdentifier())) {
                                        _alg.addProperty(DCTerms.identifier, e.getAlgorithm().getIdentifier());}
                                    //uri
                                    if (e.getAlgorithm().getURI() != null && StringUtils.isNotBlank(e.getAlgorithm().getURI().toURL().toString()) && StringUtils.isNotEmpty(e.getAlgorithm().getURI().toURL().toString())) {
                                        _alg.addProperty(DCAT.landingPage, e.getAlgorithm().getURI().toURL().toString());}
                                    //acronym
                                    if (StringUtils.isNotBlank(e.getAlgorithm().getAcronym()) && StringUtils.isNotEmpty(e.getAlgorithm().getAcronym())) {
                                        _alg.addProperty(MEXALGO_10.acronym, e.getAlgorithm().getAcronym());}


                                    _exec.addProperty(PROVO.used, _alg);

                                    //HYPER PARAMETER
                                    if (e.getAlgorithm().getParameters() != null && e.getAlgorithm().getParameters().size() > 0) {

                                        //COLLECTION
                                        Resource _hypercollection = model.createResource(URIbase + "hyperp_col" + "cf" + auxExpConf + "_" + this.userHash)
                                                .addProperty(RDF.type, mexalgo_HYPER_PARAM_COLLECTION)
                                                .addProperty(RDFS.label, "HyperParameter Collection");

                                        _exec.addProperty(PROVO.used, _hypercollection);

                                        Integer auxparam = 1;
                                        for(Iterator<HyperParameterVO> iparam = e.getAlgorithm().getParameters().iterator(); iparam.hasNext(); ) {
                                            HyperParameterVO algoParam = iparam.next();
                                            if (algoParam != null) {
                                                Resource _algoParam = null;
                                                _algoParam = model.createResource(URIbase + "hyperp_" + String.valueOf(auxparam) + "cf" + auxExpConf + "_" + this.userHash)
                                                        .addProperty(RDF.type, mexalgo_HYPER_PARAM)
                                                        .addProperty(RDFS.label, algoParam.getLabel())
                                                        .addProperty(PROVO.value, algoParam.getValue())
                                                        .addProperty(DCTerms.identifier, algoParam.getIdentifier());

                                                _alg.addProperty(MEXALGO_10.hasHyperParameter,_algoParam);
                                                _hypercollection.addProperty(PROVO.hadMember, _algoParam);
                                                auxparam++;}
                                        }

                                    }
                                }

                                //PERFORMANCES
                                if (e.getPerformances() != null && e.getPerformances().size() > 0) {
                                    //Integer auxmea = 1;

                                    //COMMON MEASURES
                                    for(Iterator<Measure> imea = e.getPerformances().iterator(); imea.hasNext(); ) {
                                        Measure mea = imea.next();
                                        if (mea != null) {

                                            Resource mexperf_klass = null, mexperf_cla = null,     mexperf_reg = null,     mexperf_clu = null,     mexperf_sta = null, mexperf_custom = null , mexperf_example = null;
                                            Resource mexperf_ins = null,   mexperf_cla_ins = null, mexperf_reg_ins = null, mexperf_clu_ins = null, mexperf_sta_ins = null, mexperf_custom_ins = null, mexperf_example_ins = null;

                                            if (mea instanceof ClassificationMeasureVO){

                                                if (mexperf_cla == null){
                                                    mexperf_cla_ins = model.createResource(URIbase + "mea_clas_" + String.valueOf(e.getId()) + "_cf_" + auxExpConf + "_"  + this.userHash); //+ "_" + String.valueOf(auxmea));
                                                    mexperf_cla = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.CLASSIFICATION_MEASURE);
                                                }

                                                mexperf_klass = mexperf_cla;
                                                mexperf_ins = mexperf_cla_ins;

                                            }else if (mea instanceof RegressionMeasureVO){

                                                if (mexperf_reg == null){
                                                    mexperf_reg_ins = model.createResource(URIbase + "mea_reg_" + String.valueOf(e.getId()) + "_cf_" + auxExpConf + "_"  + this.userHash); // + "_" + String.valueOf(auxmea));
                                                    mexperf_reg = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.REGRESSION_MEASURE);
                                                }

                                                mexperf_klass = mexperf_reg;
                                                mexperf_ins = mexperf_reg_ins;

                                            }else if (mea instanceof ClusteringMeasureVO){

                                                if (mexperf_clu == null){
                                                    mexperf_clu_ins = model.createResource(URIbase + "mea_clu_" + String.valueOf(e.getId()) + "_cf_" + auxExpConf + "_"  + this.userHash); // + "_" + String.valueOf(auxmea));
                                                    mexperf_clu = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.CLUSTERING_MEASURE);
                                                }

                                                mexperf_klass = mexperf_clu;
                                                mexperf_ins = mexperf_clu_ins;

                                            }else if (mea instanceof StatisticalMeasureVO){

                                                if (mexperf_sta == null){
                                                    mexperf_sta_ins = model.createResource(URIbase + "mea_stat_" + String.valueOf(e.getId()) + "_cf_" + auxExpConf + "_"  + this.userHash); // + "_" + String.valueOf(auxmea));
                                                    mexperf_sta = model.createResource(MEXPERF_10.NS + MEXPERF_10.ClasseTypes.STATISTICAL_MEASURE);
                                                }

                                                mexperf_klass = mexperf_sta;
                                                mexperf_ins = mexperf_sta_ins;

                                            }

                                            //mexperf_ins.addProperty(RDF.type, provEntity);
                                            mexperf_ins.addProperty(RDF.type, mexperf_klass);
                                            mexperf_ins.addProperty(RDFS.label, mea.getLabel());
                                            mexperf_ins.addProperty(model.createProperty(MEXPERF_10.NS + mea.getName()), model.createTypedLiteral(mea.getValue()));
                                            mexperf_ins.addProperty(PROVO.wasGeneratedBy, _exec);

                                            _exec.addProperty(PROVO.generated, mexperf_ins);


                                            //auxmea++;
                                        }
                                    }

                                    //OTHER MEASURES - EXAMPLE PERFORMANCE MEASURE
                                    List<Measure> examplePerformanceList = e.getPerformances(ExamplePerformanceMeasureVO.class);
                                    if (examplePerformanceList != null && examplePerformanceList.size() > 0 ) {

                                        Resource _examplePerformanceCollection = model.createResource(URIbase + "exa_perf_col" + "_cf_" + auxExpConf + "_" + this.userHash)
                                                .addProperty(RDF.type, mexperf_EXAMPLE_PERFORMANCE_COLLECTION)
                                                .addProperty(RDFS.label, "Dataset Example Performance")
                                                .addProperty(PROVO.wasGeneratedBy, _exec);
                                        _exec.addProperty(PROVO.generated, _examplePerformanceCollection);

                                        int auxep = 1;
                                        for (Iterator<Measure> iexpm = examplePerformanceList.iterator(); iexpm.hasNext(); ) {
                                            ExamplePerformanceMeasureVO epm = (ExamplePerformanceMeasureVO) iexpm.next();
                                            if (epm != null) {
                                                Resource _examplePerf = model.createResource(URIbase + "mea_exa_perf_" + String.valueOf(auxep) + "_cf_" + auxExpConf + "_"  + this.userHash)
                                                        .addProperty(RDF.type, mexperf_EXAMPLE_PERFORMANCE_MEASURE);

                                                if (StringUtils.isNotBlank(epm.getId()) && StringUtils.isNotEmpty(epm.getId())) {
                                                    _examplePerf.addProperty(DCTerms.identifier, epm.getId());
                                                }
                                                if (StringUtils.isNotBlank(epm.getPredictedValue()) && StringUtils.isNotEmpty(epm.getPredictedValue())) {
                                                    _examplePerf.addProperty(MEXPERF_10.predictedValue, epm.getPredictedValue());
                                                }
                                                if (StringUtils.isNotBlank(epm.getRealValue()) && StringUtils.isNotEmpty(epm.getRealValue())) {
                                                    _examplePerf.addProperty(MEXPERF_10.realValue, epm.getRealValue());
                                                }
                                                auxep++;
                                                _examplePerformanceCollection.addProperty(PROVO.hadMember, _examplePerf);}}
                                    }

                                    //OTHER MEASURES - USER DEFINED MEASURE
                                    List<Measure> UserDefinedList = e.getPerformances(UserDefinedMeasureVO.class);
                                    if (UserDefinedList != null && UserDefinedList.size() > 0 ) {

                                        Resource _userDefinedCollection = model.createResource(URIbase + "userdef_perf_col" + "_cf_" + auxExpConf + "_" + this.userHash)
                                                .addProperty(RDF.type, mexperf_USER_DEFINED_PERFORMANCE_COLLECTION)
                                                .addProperty(PROVO.wasGeneratedBy, _exec);
                                        _exec.addProperty(PROVO.generated, _userDefinedCollection);

                                        int auxudf = 1;
                                        for (Iterator<Measure> iexpm = examplePerformanceList.iterator(); iexpm.hasNext(); ) {
                                            UserDefinedMeasureVO udm = (UserDefinedMeasureVO) iexpm.next();
                                            if (udm != null) {
                                                Resource _userdefPerf = model.createResource(URIbase + "mea_userdef_perf_" + String.valueOf(auxudf) + "_cf_" + auxExpConf + "_"  + this.userHash)
                                                        .addProperty(RDF.type, mexperf_USER_DEFINED_PERFORMANCE_MEASURE)
                                                        .addProperty(RDFS.label, "User Defined Measure");

                                                if (StringUtils.isNotBlank(udm.getId()) && StringUtils.isNotEmpty(udm.getId())) {
                                                    _userdefPerf.addProperty(DCTerms.identifier, udm.getId());
                                                }
                                                if (StringUtils.isNotBlank(udm.getValue().toString()) && StringUtils.isNotEmpty(udm.getValue().toString())) {
                                                    _userdefPerf.addProperty(PROVO.value, udm.getValue().toString());
                                                }
                                                if (StringUtils.isNotBlank(udm.getDescription()) && StringUtils.isNotEmpty(udm.getDescription())) {
                                                    _userdefPerf.addProperty(DCTerms.description, udm.getDescription());
                                                }
                                                if (StringUtils.isNotBlank(udm.getFormula()) && StringUtils.isNotEmpty(udm.getFormula())) {
                                                    _userdefPerf.addProperty(MEXPERF_10.formula, udm.getFormula());
                                                }
                                                auxudf++;
                                                _userDefinedCollection.addProperty(PROVO.hadMember, _userdefPerf);}}

                                    }//PERFORMANCES - FOR

                                }//PERFORMANCES - NOT NULL

                            }// EXECUTION - NOT NULL

                            auxexecutions++;
                            _exec.addProperty(PROVO.wasInformedBy, _expConfiguration);

                        }// EXECUTIONS - FOR

                    } //EXECUTIONS - NOT NULL

                    auxExpConf++;

                }//EXPERIMENT CONFIGURATION - FOR

            }//EXPERIMENT CONFIGURATION - NOT NULL

            FileWriter out;

            out = new FileWriter(filename + "." + format.toString().toLowerCase().replace("/","").replace(".","").replace("-",""));

            model.write(out, format.toString());

            out.close();

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

                    if (i!=value.length()-1 && !value.substring(i+1, i+2).toString().toUpperCase().equals(value.substring(i+1, i+2).toString())){
                        if (!value.substring(i, i+1).matches("[0-9]")){
                            auxLabel += " " + value.substring(i, i+1).toString() ;}
                        else{
                            auxLabel += value.substring(i, i+1).toString();
                        }
                    }else{
                        auxLabel += value.substring(i, i+1).toString();}
                } else {
                    auxLabel += value.substring(i, i+1).toString();}
            }
        }
        auxLabel = auxLabel.replace("_", " ");
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
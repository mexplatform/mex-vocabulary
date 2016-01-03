package org.aksw.mex.log4mex;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.aksw.mex.log4mex.algo.AlgorithmParameterVO;
import org.aksw.mex.log4mex.algo.AlgorithmVO;
import org.aksw.mex.log4mex.algo.ImplementationVO;
import org.aksw.mex.log4mex.core.*;
import org.aksw.mex.log4mex.algo.AlgorithmParameterCollectionVO;
import org.aksw.mex.log4mex.perf.ExecutionPerformance;
import org.aksw.mex.log4mex.perf.example.ExamplePerformanceCollection;
import org.aksw.mex.log4mex.perf.example.ExamplePerformanceVO;
import org.aksw.mex.log4mex.perf.overall.*;
import org.aksw.mex.util.MEXConstant;
import org.aksw.mex.util.MEXController;
import org.aksw.mex.util.MEXEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class MyMEXVO {

    //mex-core->experiment
    private ApplicationContextVO applicationContext;
    private ContextVO context;
    private ExperimentVO experiment;
    //mex-core->configuration
    private FeatureVOCollection featureCollection;
    private List<FeatureVO> featureList;
    private HardwareConfigurationVO hardwareConfiguration;
    private ModelVO model;
    private PhaseVO phase;
    private SamplingMethodVO samplingMethod;
    //mex-core->execution
    private ExampleCollection exampleCollection;
    private ExampleVO example;
    private DataSetVO dataset;
    private ExecutionIndividualVO executionIndividual;
    private ExecutionSetVO executionSet;
    private List<ExperimentConfigurationVO> experimentConfigurationList;
    //mex-algo
    private AlgorithmParameterCollectionVO algorithmParameterCollection;
    private AlgorithmParameterVO algorithmParameter;
    private AlgorithmVO algorithm;
    private ImplementationVO implementation;
    //mex-perf
    private ExamplePerformanceCollection examplePerformanceCollection;
    private ExamplePerformanceVO examplePerformance;
    private ClassificationMeasureVO classificationMeasure;
    private ClusteringMeasureVO clusteringMeasure;
    private RegressionMeasureVO regressionMeasure;
    private StatisticalMeasureVO statisticalMeasure;
    private UserDefinedMeasure userDefinedMeasure;
    private ExecutionPerformance executionPerformance;

    private boolean withoutConfiguration = false;

    //logic purpouse
    private boolean automatic;

    public MyMEXVO(){
        this.applicationContext = new ApplicationContextVO();
        this.experiment = new ExperimentVO(MEXConstant.DEFAULT_EXP_ID);
        this.experimentConfigurationList = new ArrayList<>();
        //this.experimentConfigurationList.add(new ExperimentConfigurationVO(MEXConstant.DEFAULT_EXP_CONFIGURATION_ID + "1"));
        this.featureList = new ArrayList<>();
        automatic=true;
    }

    public boolean isWithoutConfiguration(){
        return withoutConfiguration;
    }
    //MEX
    public void setAuthorName(String value){this.applicationContext.setAuthorName(value);}
    public void setAuthorEmail(String value){this.applicationContext.setMailBox(value);}
    public void setContext(String value){this.applicationContext.setContext(value);}
    public void setOrganization(String value){this.applicationContext.setOrganization(value);}
    public void setExperimentId(String value){this.experiment.setId(value);}
    public void setExperimentTitle(String value){this.experiment.set_title(value);}
    public void setExperimentDate(Date value){this.experiment.setDate(value);}
    public void setExperimentDescription(String value){this.experiment.setDescription(value);}
    public ModelVO getModel() {return model;}
    public void setModel(ModelVO model) {this.model = model;}
    public List<ExperimentConfigurationVO> getExperimentConfigurations() {return experimentConfigurationList;}
    public ExperimentVO getExperiment() {return experiment;}
    public List<FeatureVO> getFeatures() {return featureList;}
    public ApplicationContextVO getApplicationContext() {return applicationContext;}
    public ExperimentConfigurationVO Configuration(String value){
        Collection<ExperimentConfigurationVO> t
                = Collections2.filter(this.experimentConfigurationList, experimentConfigurationVO -> experimentConfigurationVO.getId().equals(value));
        if (t != null && t.size() >0){
            return Iterables.get(t, 0);
        }else {
            return null;
        }
    }
    public ExperimentConfigurationVO Configuration(){
        if (this.experimentConfigurationList.size() == 0){
            withoutConfiguration = true;
            this.experimentConfigurationList.add(
                    new ExperimentConfigurationVO(MEXConstant.DEFAULT_EXP_CONFIGURATION_ID + "1"));
        }
        Collection<ExperimentConfigurationVO> t
                = Collections2.filter(this.experimentConfigurationList, experimentConfigurationVO -> experimentConfigurationVO.getId().equals(MEXConstant.DEFAULT_EXP_CONFIGURATION_ID + "1"));
        if (t != null && t.size()>0){
            return Iterables.get(t, 0);
        }else {return null;}
    }
    private String addConf(String value) throws Exception{
        String ret="";
        String valueaux="";

        try {
            if (withoutConfiguration == true){
                throw new Exception("You can not define a new configuration for an experiment without previous configuration");
            }
            if (this.experimentConfigurationList ==null){
                throw new Exception("fatal error: experiment config list is null!");
            }
            else
            {
                /* if (StringUtils.isNotEmpty(value) || StringUtils.isNotBlank(value)) {
                    //user wants to control it, first insertion...
                    if (this.experimentConfigurationList.size() == 1){
                        automatic=false;
                        //excluding experiment configuration created automatically on start, since the user wants to controll it
                        this.experimentConfigurationList.removeIf(p -> p.getId().equals(MEXConstant.DEFAULT_EXP_CONFIGURATION_ID + "1"));
                    }
                    valueaux=value;
                }
                else
                {
                    if (automatic==false) {
                        throw new Exception("Error: please inform the Experiment Configuration ID, since you started by defining it");
                    }else{
                        valueaux = MEXConstant.DEFAULT_EXP_CONFIGURATION_ID +
                            String.valueOf(this.experimentConfigurationList.size() + 1);
                    }
                }
                */

                //automatic incrementation process
                if (StringUtils.isEmpty(value) || StringUtils.isBlank(value)) {
                    Integer nextID = 0;
                    Collection<ExperimentConfigurationVO> tdefault = Collections2.filter(
                            this.experimentConfigurationList, p -> p.getId().contains(MEXConstant.DEFAULT_EXP_CONFIGURATION_ID));
                    if (tdefault != null) {
                        nextID = tdefault.size() + 1;
                    }
                    valueaux = MEXConstant.DEFAULT_EXP_CONFIGURATION_ID + String.valueOf(nextID);

                }
                //manual incrementation process
                else
                {
                    valueaux=value;
                }
                //checking the existing
                final String check = valueaux;
                Collection<ExperimentConfigurationVO> t =
                        Collections2.filter(this.experimentConfigurationList, p -> p.getId().equals(check));
                if (t != null && t.size() > 0){
                    throw new Exception("Error: Experiment Configuration ID " + check + " already assigned");
                }

                //adding the code
                if (this.experimentConfigurationList.add(new ExperimentConfigurationVO(check))==false){
                    throw new Exception("Error when including the item in the list");
                }

            }

        }catch (Exception e){
            System.out.println(e.toString());
        }
        return valueaux;
    }
    public String addConfiguration() throws Exception{
        String ret;
        try
        {
            if (withoutConfiguration == true){
                throw new Exception("You can not define a new configuration for an experiment without previous configuration");
            }

            ret=addConf(StringUtils.EMPTY);
            MEXController.getInstance().addExperimentConfigurationCounter();
        }catch (Exception e){
            throw new Exception(e);
        }
        return ret;
    }
    public String addConfiguration(String value) throws Exception{
        String ret;
        try
        {
            if (withoutConfiguration == true){
                throw new Exception("You can not define a new configuration for an experiment without previous configuration");
            }

            ret=addConf(value);
        }catch (Exception e){
           throw new Exception(e);
        }
        return ret;
    }
    public ContextVO getContext() {
        return context;
    }
    public void setApplicationContext(ApplicationContextVO applicationContext) {
        this.applicationContext = applicationContext;
    }
    public ExampleCollection getExampleCollection() {
        return exampleCollection;
    }
    public void setExampleCollection(ExampleCollection exampleCollection) {
        this.exampleCollection = exampleCollection;
    }
    public ExampleVO getExample() {
        return example;
    }
    public void setExample(ExampleVO example) {
        this.example = example;
    }
    public DataSetVO getDataset() {
        return dataset;
    }
    public void setDataset(DataSetVO dataset) {
        this.dataset = dataset;
    }
    public ExecutionIndividualVO getExecutionIndividual() {
        return executionIndividual;
    }
    public void setExecutionIndividual(ExecutionIndividualVO executionIndividual) {
        this.executionIndividual = executionIndividual;
    }
    public ExecutionSetVO getExecutionSet() {
        return executionSet;
    }
    public void setExecutionSet(ExecutionSetVO executionSet) {
        this.executionSet = executionSet;
    }
    public void setExperiment(ExperimentVO experiment) {
        this.experiment = experiment;
    }
    public FeatureVOCollection getFeatureCollection() {
        return featureCollection;
    }
    public void setFeatureCollection(FeatureVOCollection featureCollection) {
        this.featureCollection = featureCollection;
    }
    public HardwareConfigurationVO getHardwareConfiguration() {
        return hardwareConfiguration;
    }
    public void setHardwareConfiguration(HardwareConfigurationVO hardwareConfiguration) {
        this.hardwareConfiguration = hardwareConfiguration;
    }
    public PhaseVO getPhase() {
        return phase;
    }
    public void setPhase(PhaseVO phase) {
        this.phase = phase;
    }
    public SamplingMethodVO getSamplingMethod() {
        if (samplingMethod==null){
            this.samplingMethod = new SamplingMethodVO("sm1", MEXEnum.EnumSamplingMethod.Holdout);
        }
        return samplingMethod;
    }
    public void setSamplingMethod(SamplingMethodVO samplingMethod) {
        this.samplingMethod = samplingMethod;
    }
    public AlgorithmParameterCollectionVO getAlgorithmParameterCollection() {
        return algorithmParameterCollection;
    }
    public void setAlgorithmParameterCollection(AlgorithmParameterCollectionVO algorithmParameterCollection) {
        this.algorithmParameterCollection = algorithmParameterCollection;
    }
    public AlgorithmParameterVO getAlgorithmParameter() {
        return algorithmParameter;
    }
    public void setAlgorithmParameter(AlgorithmParameterVO algorithmParameter) {
        this.algorithmParameter = algorithmParameter;
    }
    public AlgorithmVO getAlgorithm() {
        return algorithm;
    }
    public void setAlgorithm(AlgorithmVO algorithm) {
        this.algorithm = algorithm;
    }
    public ImplementationVO getImplementation() {
        return implementation;
    }
    public void setImplementation(ImplementationVO implementation) {
        this.implementation = implementation;
    }
    public ExamplePerformanceCollection getExamplePerformanceCollection() {
        return examplePerformanceCollection;
    }
    public void setExamplePerformanceCollection(ExamplePerformanceCollection examplePerformanceCollection) {
        this.examplePerformanceCollection = examplePerformanceCollection;
    }
    public ExamplePerformanceVO getExamplePerformance() {
        return examplePerformance;
    }
    public void setExamplePerformance(ExamplePerformanceVO examplePerformance) {
        this.examplePerformance = examplePerformance;
    }
    public ClassificationMeasureVO getClassificationMeasure() {
        return classificationMeasure;
    }
    public void setClassificationMeasure(ClassificationMeasureVO classificationMeasure) {
        this.classificationMeasure = classificationMeasure;
    }
    public ClusteringMeasureVO getClusteringMeasure() {
        return clusteringMeasure;
    }
    public void setClusteringMeasure(ClusteringMeasureVO clusteringMeasure) {
        this.clusteringMeasure = clusteringMeasure;
    }
    public RegressionMeasureVO getRegressionMeasure() {
        return regressionMeasure;
    }
    public void setRegressionMeasure(RegressionMeasureVO regressionMeasure) {
        this.regressionMeasure = regressionMeasure;
    }
    public StatisticalMeasureVO getStatisticalMeasure() {
        return statisticalMeasure;
    }
    public void setStatisticalMeasure(StatisticalMeasureVO statisticalMeasure) {
        this.statisticalMeasure = statisticalMeasure;
    }
    public UserDefinedMeasure getUserDefinedMeasure() {
        return userDefinedMeasure;
    }
    public void setUserDefinedMeasure(UserDefinedMeasure userDefinedMeasure) {
        this.userDefinedMeasure = userDefinedMeasure;
    }
    public ExecutionPerformance getExecutionPerformance() {
        return executionPerformance;
    }
    public void setExecutionPerformance(ExecutionPerformance executionPerformance) {
        this.executionPerformance = executionPerformance;
    }

    /****************************************************************************************
     * Lightweight Component - No need to set up a configuration
     ****************************************************************************************/
    /*public String addExecutionLWC(String type, String value) throws Exception{
        try{
            withoutConfiguration = true;
            if (this.experimentConfigurationList == null){
                this.addConf("");
            }
            String ret = this.experimentConfigurationList.get(0).addExecution(type, value);
            return ret;
        }catch (Exception e){
            throw (e);
        }
    }
    public String addAlgorithmLWC(String algorithmClass) throws Exception{
        try{
            withoutConfiguration = true;
            if (this.experimentConfigurationList == null){
                this.addConf("");
            }
            String ret = this.experimentConfigurationList.get(0).addAlgorithm(algorithmClass);
            return ret;
        }catch (Exception e){
            throw (e);
        }
    }
    public void addSamplingMethodLWC(String className, Double train, Double test) throws Exception{
        try{
            withoutConfiguration = true;
            if (this.experimentConfigurationList == null){
                this.addConf("");
            }
            this.experimentConfigurationList.get(0).addSamplingMethod(className, train, test);
        }catch (Exception e){
            throw (e);
        }
    }
    public void addSamplingMethodLWC(String className, Integer folds) throws Exception{
        try{
            withoutConfiguration = true;
            if (this.experimentConfigurationList == null){
                this.addConf("");
            }
            this.experimentConfigurationList.get(0).addSamplingMethod(className, folds);
        }catch (Exception e){
            throw (e);
        }
    }
    public void addFeatureLWC(String[] featuresName) throws Exception{
        try{
            withoutConfiguration = true;
            if (this.experimentConfigurationList == null){
                this.addConf("");
            }
            this.experimentConfigurationList.get(0).addFeature(featuresName);
        }catch (Exception e){
            throw (e);
        }
    }
    public void addFeatureLWC(String featuresName) throws Exception{
        try{
            withoutConfiguration = true;
            if (this.experimentConfigurationList == null){
                this.addConf("");
            }
            this.experimentConfigurationList.get(0).addFeature(featuresName);
        }catch (Exception e){
            throw (e);
        }
    }
    */

}
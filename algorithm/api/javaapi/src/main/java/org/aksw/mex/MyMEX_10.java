package org.aksw.mex;

import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import org.aksw.mex.algo.AlgorithmParameterVO;
import org.aksw.mex.algo.AlgorithmVO;
import org.aksw.mex.algo.ImplementationVO;
import org.aksw.mex.core.*;
import org.aksw.mex.algo.AlgorithmParameterCollectionVO;
import org.aksw.mex.perf.ExecutionPerformance;
import org.aksw.mex.perf.example.ExamplePerformanceCollection;
import org.aksw.mex.perf.example.ExamplePerformanceVO;
import org.aksw.mex.perf.overall.*;
import org.aksw.mex.util.Global;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by esteves on 26.06.15.
 */
public class MyMEX_10 {

    public MyMEX_10(){
        this.applicationContext = new ApplicationContextVO();
        this.experiment = new ExperimentVO();
        this.experimentConfigurationList = new ArrayList<>();
    }


    public ApplicationContextVO getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContextVO applicationContext) {
        this.applicationContext = applicationContext;
    }

    //mex-core
    private ApplicationContextVO applicationContext;
    private ContextVO context;
    private DataSetAttributeVO attribute;
    private DataSetExampleCollection exampleCollection;
    private DataSetExampleVO example;
    private DataSetVO dataset;
    private ExecutionIndividualVO executionIndividual;
    private ExecutionSetVO executionSet;
    private ExperimentConfigurationVO experimentConfiguration;
    private List<ExperimentConfigurationVO> experimentConfigurationList;
    private ExperimentVO experiment;
    private FeatureVO feature;
    private FeatureVOCollection featureCollection;
    private HardwareConfigurationVO hardwareConfiguration;
    private ModelVO model;
    private PhaseVO phase;
    private SamplingMethodVO samplingMethod;
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

    public ContextVO getContext() {
        return context;
    }

    //public void setContext(ContextVO context) {
    //    this.context = context;
    //}

    public DataSetAttributeVO getAttribute() {
        return attribute;
    }

    public void setAttribute(DataSetAttributeVO attribute) {
        this.attribute = attribute;
    }

    public DataSetExampleCollection getExampleCollection() {
        return exampleCollection;
    }

    public void setExampleCollection(DataSetExampleCollection exampleCollection) {
        this.exampleCollection = exampleCollection;
    }

    public DataSetExampleVO getExample() {
        return example;
    }

    public void setExample(DataSetExampleVO example) {
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

    public ExperimentConfigurationVO getExperimentConfiguration() {
        return experimentConfiguration;
    }

    public void setExpConf_iguration(ExperimentConfigurationVO experimentConfiguration) {
        this.experimentConfiguration = experimentConfiguration;
    }

    public ExperimentVO getExperiment() {
        return experiment;
    }

    public void setExperiment(ExperimentVO experiment) {
        this.experiment = experiment;
    }

    public FeatureVO getFeature() {
        return feature;
    }

    public void setFeature(FeatureVO feature) {
        this.feature = feature;
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

    public ModelVO getModel() {
        return model;
    }

    public void setModel(ModelVO model) {
        this.model = model;
    }

    public PhaseVO getPhase() {
        return phase;
    }

    public void setPhase(PhaseVO phase) {
        this.phase = phase;
    }

    public SamplingMethodVO getSamplingMethod() {
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



    //MEX
    public void setAuthorName(String value){
        this.applicationContext.setAuthorName(value);
    }
    public void setAuthorEmail(String value){
        this.applicationContext.setMailBox(value);
    }
    public void setContext(String value){this.applicationContext.setContext(value);}
    public void setOrganization(String value){this.applicationContext.setOrganization(value);}

    public void setExperimentId(String value){this.experiment.setId(value);}
    public void setExperimentTitle(String value){this.experiment.set_title(value);}
    public void setExperimentDate(Date value){this.experiment.setDate(value);}
    public void setExperimentDescription(String value){this.experiment.setDescription(value);}

    public void setExpConf_Id(String value){this.experimentConfiguration.set_id(value);}
    public void setExpConf_Description(String value){this.experimentConfiguration.setDescription(value);}
    public void setExpConf_Model_Id(String value){
        if (this.experimentConfiguration.Model() == null) {
         this.experimentConfiguration.setModel(new ModelVO());
        }
        this.experimentConfiguration.Model().setId(value);
    }
    public void setExpConf_Model_Description(String value){
        if (this.experimentConfiguration.Model() == null) {
            this.experimentConfiguration.setModel(new ModelVO());
        }
        this.experimentConfiguration.Model().setDescription(value);
    }
    public void setExpConf_Model_Date(Date value){
        if (this.experimentConfiguration.Model() == null) {
            this.experimentConfiguration.setModel(new ModelVO());
        }
        this.experimentConfiguration.Model().setDate(value);
    }

    public void setExpConf_SamplingMethod(String value){
        this.experimentConfiguration.setSamplingMethod(new SamplingMethodVO(value));
    }

    public void setExpConf_SamplingMethod_TrainSize(double value){
        if (this.experimentConfiguration.SamplingMethod() == null) {
            this.experimentConfiguration.setSamplingMethod(new SamplingMethodVO(Global.EnumSamplingMethod.Holdout));
        }
        this.experimentConfiguration.SamplingMethod().setTrainSize(value);
    }

    public void setExpConf_SamplingMethod_TestSize(double value){
        if (this.experimentConfiguration.SamplingMethod() == null) {
            this.experimentConfiguration.setSamplingMethod(new SamplingMethodVO(Global.EnumSamplingMethod.Holdout));
        }
        this.experimentConfiguration.SamplingMethod().setTestSize(value);
    }

    public void setExpConf_SamplingMethod_NumberOfFolds(Integer value){
        if (this.experimentConfiguration.SamplingMethod() == null) {
            this.experimentConfiguration.setSamplingMethod(new SamplingMethodVO(Global.EnumSamplingMethod.Holdout));
        }
        this.experimentConfiguration.SamplingMethod().setFolds(value);
    }

    public void setExpConf_SamplingMethod_IsSequential(boolean value){
        if (this.experimentConfiguration.SamplingMethod() == null) {
            this.experimentConfiguration.setSamplingMethod(new SamplingMethodVO(Global.EnumSamplingMethod.Holdout));
        }
        this.experimentConfiguration.SamplingMethod().setSequential(value);
    }

    public void setExpConf_Hardware_OS(String value){
        if (this.experimentConfiguration.HardwareConfiguration() == null) {
            this.experimentConfiguration.setHardwareConfiguration(new HardwareConfigurationVO());
        }
        this.experimentConfiguration.HardwareConfiguration().setOperationalSystem(value);
    }

    public void setExpConf_Hardware_CPU(String value){
        if (this.experimentConfiguration.HardwareConfiguration() == null) {
            this.experimentConfiguration.setHardwareConfiguration(new HardwareConfigurationVO());
        }
        this.experimentConfiguration.HardwareConfiguration().setCPU(value);
    }

    public void setExpConf_Hardware_MB(String value){
        if (this.experimentConfiguration.HardwareConfiguration() == null) {
            this.experimentConfiguration.setHardwareConfiguration(new HardwareConfigurationVO());
        }
        this.experimentConfiguration.HardwareConfiguration().setMemory(value);
    }

    public void setExpConf_Hardware_HD(String value){
        if (this.experimentConfiguration.HardwareConfiguration() == null) {
            this.experimentConfiguration.setHardwareConfiguration(new HardwareConfigurationVO());
        }
        this.experimentConfiguration.HardwareConfiguration().setHD(value);
    }

    public void setExpConf_Hardware_CPUCache(String value){
        if (this.experimentConfiguration.HardwareConfiguration() == null) {
            this.experimentConfiguration.setHardwareConfiguration(new HardwareConfigurationVO());
        }
        this.experimentConfiguration.HardwareConfiguration().setCache(value);
    }

    public void setExpConf_DataSet_Title(String value){
        if (this.experimentConfiguration.DataSet() == null) {
            this.experimentConfiguration.setDataSet(new DataSetVO());
        }
        this.experimentConfiguration.DataSet().setName(value);
    }

    public void setExpConf_DataSet_Description(String value){
        if (this.experimentConfiguration.DataSet() == null) {
            this.experimentConfiguration.setDataSet(new DataSetVO());
        }
        this.experimentConfiguration.DataSet().setDescription(value);
    }

    public void setExpConf_DataSet_LandingPage(String value){
        if (this.experimentConfiguration.DataSet() == null) {
            this.experimentConfiguration.setDataSet(new DataSetVO());
        }
        this.experimentConfiguration.DataSet().setURI(value);
    }

    /*public void addExpConf_Feature(String label){
        int i=0;
        if (this.experimentConfiguration.getFeatures() != null)
        {
            i=this.experimentConfiguration.getFeatures().size();
        }
        this.experimentConfiguration.addFeature(new FeatureVO(String.valueOf(i+1),label));
    }
    */

    public ExperimentConfigurationVO Configuration(String value){
        Collection<ExperimentConfigurationVO> t
                = Collections2.filter(this.experimentConfigurationList, experimentConfigurationVO -> experimentConfigurationVO.get_id().equals(value));
        if (t != null){
            return Iterables.get(t, 0);
        }else {return null;}
    }

    public void addConfiguration(String value){
        try
        {
            Collection<ExperimentConfigurationVO> t
                    = Collections2.filter(this.experimentConfigurationList, p -> p.get_id().equals(value));
            if (t != null && t.size() > 0){
                throw new Exception("Experiment Configuration ID already assigned");
            }else
            {
                this.experimentConfigurationList.add(new ExperimentConfigurationVO(value));}
        }catch (Exception e){
            System.out.println(e.toString());
        }

    }

}

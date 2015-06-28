package org.aksw.mex;

import org.aksw.mex.algo.AlgorithmParameterVO;
import org.aksw.mex.algo.AlgorithmVO;
import org.aksw.mex.algo.ImplementationVO;
import org.aksw.mex.core.*;
import org.aksw.mex.algo.AlgorithmParameterCollectionVO;
import org.aksw.mex.perf.ExecutionPerformance;
import org.aksw.mex.perf.example.ExamplePerformanceCollection;
import org.aksw.mex.perf.example.ExamplePerformanceVO;
import org.aksw.mex.perf.overall.*;

/**
 * Created by esteves on 26.06.15.
 */
public class MyMEX_10 {

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

    public void setContext(ContextVO context) {
        this.context = context;
    }

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

    public void setExperimentConfiguration(ExperimentConfigurationVO experimentConfiguration) {
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

    public MyMEX_10(){
        this.applicationContext = new ApplicationContextVO();
    }


    public void setAuthorName(String value){
        this.applicationContext.setAuthorName(value);
    }
    public void setAuthorEmail(String value){
        this.applicationContext.setMailBox(value);
    }


}

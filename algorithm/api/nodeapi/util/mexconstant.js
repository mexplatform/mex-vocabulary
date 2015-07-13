/**
 * Created by esteves on 10.07.15.
 */
//prefixes for identifications
module.exports.DEF_IDENTIFIERS = {
    EXP : 'MEX_EXPERIMENT',
    EXP_CONFIGURATION : 'MEX_EXP_CONF_D',
    EXEC : 'MEX_EXEC_D',
    MEX_PERF : {
        ACCURACY : 'accuracy',
        ERROR: 'error'
    }
}
//names (or prefix in case of list) for instances
module.exports.DEF_INDIVIDUALS = {
    EXP : 'exp',
    EXP_CONFIGURATION : 'conf',
    EXEC : 'exec',
    APPLICATION_CONTEXT : 'app',
    CONTEXT : 'context',
    ALGORITHM : 'algorithm',
    FEATURE : 'feature',
    HYPERPARAMETER : 'hyperparam',
    HARDWARE : 'hard',
    PHASE_TRAINING : 'training',
    PHASE_TEST : 'test',
    DATASET : 'ds',
    IMPLEMENTATION : 'soft',
    SAMPLING_METHOD : 'sampling'
}

//names for classes
module.exports.DEF_CLASSES = {
    MEX_CORE : {
        EXP : 'Experiment',
        APPLICATION_CONTEXT : 'ApplicationContext',
        MODEL : "Model",
        EXECUTION : {
            SINGLE : "ExecutionSingle",
            OVERALL : "ExecutionOverall"
        },
        PHASE : "Phase",
        EXAMPLE : "Example",
        EXP_CONFIGURATION : "ExperimentConfiguration",
        FEATURE : "Feature",
        SAMPLING_METHOD : {
            BOOT_STRAPPING : 'Bootstrapping',
            CROSS_VALIDATION : 'CrossValidation',
            HOLDOUT : 'Holdout',
            LEAVE_ONE_OUT: 'LeaveOneOut'
        },
        HARDWARE_CONFIGURATION : "HardwareConfiguration",
        DATASET : "Dataset",
        CONTEXT : {
            ADAPTIVE_WEB_SITES : 'AdaptiveWebSites',
            LINK_DISCOVERY : 'LinkDiscovery',
            AFFECTIVE_COMPUTING : 'AffectiveComputing',
            BIO_INFORMATICS: 'Bioinformatics',
            FINANCE: 'Finance',
            FACT_PREDICTION: 'FactPrediction',
            NLP: 'NaturalLanguageProcessing',
            SENTIMENT_ANALYSIS: 'SentimentAnalysis'
        }
    },
    MEX_ALGO : {
        HYPERPARAMETER : 'AlgorithmParameter',
        IMPLEMENTATION : {
            DL_LEARNER : 'DLLearner',
            E_VIEWS : 'EViews',
            FAMA : 'FAMa',
            IBM_MINER: 'IBMMiner',
            LIBSVM: 'LibSVM',
            WEKA: 'Weka',
            MATHEMATICA: 'Mathematica',
            MATLAB: 'Matlab'
        },
        ALGORITHM : {
            ADTREE : 'ADTree',
            ADAPTATIVE_BOOST : 'AdaptativeBoost',
            ARIMA : 'AutoregressiveIntegratedMovingAverage',
            DECISION_TABLE: 'DecisionTable',
            MULTILAYER_PERCEPTRON: 'MultilayerPerceptron',
            C45: 'C45',
            BAYESTHEORY: 'BayesTheoryAlgorithms',
            SVM_CLASSIFICATION: 'C-SVM',
            SVM_REGRESSION: 'R-SVM',
            SVM_RADIAL_BASE_FUNCTION: 'RBF-SVM'
        }
    },
    MEX_PERF : {
        EXECUTION : 'ExecutionPerformance',
        EXAMPLE : 'ExamplePerformance',
        USER_DEFINED : 'UserDefinedPerformance',
        MEASURE : {
            STATISTICAL : 'StatisticalMeasure',
            REGRESSION : 'RegressionMeasure',
            CLASSIFICATION : 'ClassificationMeasure',
            CLUSTERING : 'ClusteringMeasure'
        }
    },
    PROV : {
        AGENT : 'Agent',
        PERSON : 'Person',
        ORGANIZATION : 'Organization',
        ENTITY : 'Entity',
        ACTIVITY : 'Activity'
    }
}


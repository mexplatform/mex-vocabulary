# My Machine Learning Experiment Vocabulary
## MEX Vocabulary: A Lightweight Interchange Format for Machine Learning Experiments

The MEX Vocabulary has been designed to define a **lightweight and flexible schema** for publishing machine learning metadata. 

### This repository
  * [vocabulary](https://github.com/dnes85/mexproject/tree/master/ontology)
  * [APIs](https://github.com/dnes85/mexproject/tree/master/algorithm)
  * [examples](https://github.com/dnes85/mexproject/tree/master/proof)
  * [ppt](https://github.com/dnes85/mexproject/tree/master/ppt)

### How to use MEX?

For **semantic web users** 
...

For **non-semantic web users** 
...

For **general and non-expert users**
...

Finnaly, we also aim to integrate MEX into established machine learning frameworks, such as [DL-Learner](http://dl-learner.org/), [WEKA](http://www.cs.waikato.ac.nz/ml/weka/) and [FAMa](https://github.com/duartejulio/fama). Feel free to collaborate ;-)

### The Vocabulary

The current version of the vocabulaty is described as following. We've omitted obvious information for brevity.

#### mexcore
* **Basic Informations**: stores the basic authoring information and a brief introduction for your experiment setup
  * `:ApplicationContext`
  * `:Context`: the context for the experiment, such as `:Bioinformatics`, `:ComputationalAdversiting`, `:ComputationalFinance`, `:DetectingCreditCardFrauds`, `:FactPrediction` and `:NaturalLanguageProcessing`.
  * `:Experiment`: core information for the experiment

* **Configurations**: the class `:ExperimentConfiguration` plays a important role, creating logical groups for your set of executions. In other words, if you decide run your algorithms over different hardware environments (`mexcore:HardwareConfiguration`) or datasets (`mexcore:Dataset`), for instance, you should create more than one instance of this class. The set of classes which define the logical group are listed below. Besides, each configuration is related to one specific software implementation (`mexalgo:Implementation`)
  * `:FeatureCollection`: the set of `:Feature` represent the dataset features (also defined as *attribute*)
  * `:SamplingMethod`
  * `:HardwareConfiguration`
  * `:Dataset`

* **The runs**: here the `:Execution` entity is the central class of the `core` layer and receives the spotlight on our schema definition. It is defined as a hub for linking the three layers (`core`, `algorithm` and `performance`). Concisely one instance of `:Execution` is performed on behalf of an `:Algorithm` and produces one or more `PerformanceMeasure`, represented by the `:ExecutionPerformance` class (e.g.: `:execution prov:used :algorithm` and `:executionperformance :prov:wasGeneratedBy :execution`). Below are listed the related entities. It is worth noting the abstraction level for modeling the execution process: for representing the set of runs the algorithm has performed over a dataset (or a subset), i.e., an overall execution (`:OverallExecution`) or a single execution (`:SingleExecution`) for representing the run over each `dataset example`, although less appropriate for the abstraction level required for presenting experimental results.
  * `:Model`: basic information regarding the used/generated model
  * `:Phase`: train, validation or test
  * `:ExampleCollection`: the set of `:Example` instances represent 1 `dataset example` (also defined as *instance*, or, more technically, a dataset *row*). It is useful when you're describing each execution for each dataset example (e.g.: a test dataset which contains 100 examples [100 rows] is represented by 100 `:ExampleCollection` instances) and want to store the values for some reason.

#### mexalgo
* `:Implementation`
* `:Algorithm`
* `:AlgorithmParameter`
* `:LearningMethod`
* `:LearningProblem`
* `:AlgorithmClass`

#### mexperf
* `:ExecutionPerformance`: this class links the `:Execution` and its results, i.e., it's an entity for interlinking the `mexcore` and `mexperf` layers. For each execution you have to define at least one `:PerformanceMeasure` or `ExamplePerformance` or `UserDefinedMeasure` (see details below)
* `:PerformanceMeasure`: the root for machine learning general measures, grouped into classes. Each described `:Execution` may produces one or more measures. Used for describing `:OverallExecution`. 
  * `:ClassificationMeasure`
  * `:RegressionMeasure`
  * `:ClusteringMeasure`
  * `:StatisticalMeasure`
* `:ExamplePerformanceCollection`: Useful 
* `:UserDefinedMeasureCollection`: We know reusing is a key factor for semantic web, but sometimes the number of people would reuse your metric is quite low, of even zero! So, if you have a very specific measure formula for your business and want to describe use this class! Each `:UserDefinedMeasure` stores a unknown measure value and its formula. Of course we will keep an eye on it for the `measures` updating! 

### I've not found a specific machine learning algorithm! (or any information else)
Despite efforts for keeping everything up-to-date you might not find your machine learning algorithm into `MEX`, for instance, or even one specific machine learning tool. That's bad, we know! :-( However, we are going to have the pleasure to quickly update the vocabulary to satisfy your desires, just let us know! ;-)
(feel free for contributing as well)

### MEX Vocabulary - Snapshot v0.7.0
![Experiment ER](http://dne5.com/images/mex/mexv0.7.0.png)


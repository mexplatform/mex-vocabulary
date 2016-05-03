# MEX Vocabulary

* Lightweight and Flexible Schema for Machine Learning Iterations

More than just a **machine readable way**, the `MEX` Vocabulary has been designed to define a **lightweight and flexible schema** for publishing machine learning outputs metadata, regardless technology (we have been working to provide different programming language *APIs* and integrations with different machine learning tools). We aim to provide a **free-format** for exporting and exchanging machine learning metadata, indifferent to existing **workflow systems** or **frameworks**. As a consequence, the users can benefit of the `mex format` for further analysis and integrations in an easiest way.


The definition of an `ontology` can be a complex and never-ending task, even more for a highly complex environment such as machine learning. The `MEX` vocabulary has been designed to serve as an ally in the metadata exporting process, focusing in practical and important aspects concerning the publication of the achieved results, i.e.: the needed `input parameters` for an `model` which produces `measures`. More sophisticated variables and procedures (e.g.: optimizations and feature selection) are not covered, simply because they go beyond the a logical threshold of simplicity we want to achieve here. In the end, people are more interested in find out, compare and share `methodologies` and their `performances` than to have deep understanding of performed sub tasks.

## Schema

The current version of the vocabulaty is described (per layer) as following. We've omitted obvious information for brevity.

#### mexcore
* **Basic Informations**: stores the basic authoring information and a brief introduction for your experiment setup
  * `:ApplicationContext`
  * `:Context`: the context for the experiment, such as `:Bioinformatics`, `:ComputationalAdversiting`, `:ComputationalFinance`, `:DetectingCreditCardFrauds`, `:FactPrediction` and `:NaturalLanguageProcessing`.
  * `:Experiment`: core information for the experiment

* **Configurations**: the class `:ExperimentConfiguration` plays a important role, creating logical groups for your set of executions. In other words, if you decide run your algorithms over different hardware environments (`:HardwareConfiguration`) or datasets (`:Dataset`), for instance, you should create more than one instance of this class. The set of classes which define the logical group are listed below.
  * `:FeatureCollection`: the set of `:Feature` represent the dataset features (also defined as *attribute*)
  * `:SamplingMethod`
  * `:HardwareConfiguration`
  * `:Dataset`

Besides, each configuration is related to one specific software implementation (`mexalgo:Implementation`)

* **The runs**: here the `:Execution` entity is the central class of the `core` layer and receives the spotlight on our schema definition. It is defined as a hub for linking the three layers (`mexcore`, `mexalgo` and `mexperf`). Concisely one instance of `:Execution` is performed on behalf of an `:Algorithm` and produces one or more `:PerformanceMeasure`, represented by the `:ExecutionPerformance` class (e.g.: `:ex1 prov:used :algA` and `:execperf :prov:wasGeneratedBy :exec1`). It is worth noting the abstraction level for modeling the execution process: for representing **the set of runs** the algorithm has performed over a dataset (or a subset) (i.e.: an overall execution), the appropriete class is the `:OverallExecution`, whereas for representing the run over each `dataset example` (i.e.: a single execution), the relevant class is the `:SingleExecution`, although less appropriate for the abstraction level required for presenting experimental results.
Below are listed additional related entities:
  * `:Model`: basic information regarding the used/generated model
  * `:Phase`: train, validation or test
  * `:ExampleCollection`: the set of `:Example` instances represent a single `dataset example` (also defined as *instance*, or, more technically, a dataset *row*). It is useful when you're describing each execution for each dataset example (e.g.: a test dataset which contains 100 examples [100 rows] is represented by 100 `:ExampleCollection` instances, each of these representing a *row*) and want to store the values for some reason.

#### mexalgo
* `:Implementation`: the software implementation used in the experiment
* `:Algorithm`
* `:AlgorithmParameter`: the set of hyperparamters

Additionally, three classes are defined into this layer in order to provide more formalism for the algorithm, although not crucial (and are instantiated in a transparent way to the end user) for the execution description `:LearningMethod`, `:LearningProblem` and `:AlgorithmClass`

#### mexperf
* `:ExecutionPerformance`: this class links the `:Execution` and its results, i.e., it's an entity for interlinking the `mexcore` and `mexperf` layers. For each execution you have to define at least one `:PerformanceMeasure` or `:ExamplePerformance` or `:UserDefinedMeasure` (see details below)

* `:PerformanceMeasure`: the root for machine learning general measures, grouped into classes. Each existing `:OverallExecution` yields one or more measures:
  * `:ClassificationMeasure`: classification problem measures (e.g.: `accuracy`, `fpp`, `f1`, ...)
  * `:RegressionMeasure`: regression problem measures (e.g: `residual`, `MSE`, ...)
  * `:ClusteringMeasure`: clustering problem measures (e.g.: `hammingDistance`, `manhattanDistance`, ...)
  * `:StatisticalMeasure`: generical statistical measures (e.g.: `pearsonCorrelation`, `chiSquare`, ...)

* `:ExamplePerformanceCollection`: associates each `:SingleExecution` and its **predicted value** and the **real value** (`:ExamplePerformance`)

* `:UserDefinedMeasureCollection`: We know reusing is a key factor for semantic web, but sometimes the number of people would reuse your metric is quite low, of even zero! Besides, extending a vocabulary might be complicated for **non-semantic web users** (e.g.: *how people would use `MEX` for describing a machine learning experiment without semantic web background?*).
Therefore, if you have a very specific measure formula for your business and want to describe use this class! Each `:UserDefinedMeasure` stores an unknown measure (`prov:value`) value and its formula (`mexperf:formula`).
Of course we will keep an eye on it for the `vocabulary measures` updating process! ;-)

### MEX Vocabulary - Snapshot v1.0.1
![Experiment ER](http://dne5.com/mex/diagram/mex-1.0.1.png)
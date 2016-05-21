# LOG4MEX Library

## Do you need an API to manage machine learning metadata? 

* LOG4MEX helps developers to manage ML metadata, improving interoperability and adding provenance information to the experiment.
* No need to create scripts to manage the outputs.
* No need to create wrappers to save outputs into SGBD's.
* Exporting clear information about your executions! Others do not need to guess what you did! (LOG4MEX is based on [MEX Vocabulary](https://github.com/AKSW/mexproject/tree/master/vocabulary))

![LOG4MEX Relations](http://dne5.com/mex/imagens/mex_relations_lod.png)

##Requirements
Java 8, Maven 3

##License
LOG4MEX - a MEX Project - is Open Source and licensed under the [GNU General Public License 3](http://www.gnu.org/licenses/gpl-3.0.en.html) (Copyright (c) 2014-2016, Diego Esteves).

##Demo and Documentation
* [Project Site](http://aksw.github.io/mexproject/)
* [Javadoc](http://dne5.com/mex/documentation/log4mex/)
* [SPARQL Endpoint](http://mex.aksw.org/sparql)
* [MEX Wiki: LOG4MEX Use Cases](https://github.com/AKSW/mexproject/wiki/LOG4MEX-Use-Cases)

##Bugs
Found a :bug: bug? [Open an issue](https://github.com/AKSW/fox/issues/new) 

##Changelog
### [v1.0.2]
* MEX vocabulary update
* Fixing serialization bug

### [v1.0.1]
* MEX vocabulary update
* Adding support to more semantic formats

### [v1.0.0]
* Initial version

## Usage

#### 1) The basics

**Instantiating the main class for the experiment**
```java
MyMEX mex = new MyMEX();
```
**Saving some basic provenance informations**
```java
mex.setAuthorName("James Walter");
mex.setAuthorEmail("jwalter1@example.com");
mex.setContext(EnumContexts.STOCK_MARKET_PREDICTIONS);
mex.setOrganization("Leipzig University");
mex.setExperimentTitle("My Experiment 2016");
mex.setExperimentDate(new Date());
...
```
#### 2) Configurations
**How many dimensions you want to have?** 

**You can either represent an unique configuration and access the linked objects direct from it**
```java
...
mex.Configuration().
...
```
**or group your executions by configurations, e.g.: 1) analysis for dataset A 2) analysis for dataset B, and access the objects by configuration**
```java
String c1 = mex.addConfiguration();
String c2 = mex.addConfiguration();
...
mex.Configuration(c1).
...
```

**For each configuration, you define your environment**

```java
...
mex.Configuration().setDescription("here you can explain the logical group you've created");
mex.Configuration().setModel("model8745.jd", "svm model based on alpha=0.05 and C=1", "2016-05-10");
mex.Configuration().setHardwareConfiguration("ubuntu", EnumProcessors.INTEL_COREI7, EnumRAM.SIZE_16GB, "SSD", EnumCaches.CACHE_3MB);
mex.Configuration().setDataSet("http://www.bmfbovespa.com.br/shared/iframe.aspx?idioma=pt-br&url=http://www.bmfbovespa.com.br/pt-br/cotacoes-historicas/FormSeriesHistoricas.asp", "bovespads", "bovespa");
mex.Configuration().setSamplingMethod(EnumSamplingMethods.CROSS_VALIDATION, 10);
mex.Configuration().setTool(EnumTools.WEKA, "3.6.6");
mex.Configuration().addFeature(new String[]{"openp", "closep", "minp", "maxp"}); 
...
```
**What are your algorithms? You can also refer to them in case you need to specify different hyperparameters values** 
```java
...
String alg1 = mex.Configuration().addAlgorithm("svm", EnumAlgorithmsClasses.SupportVectorMachines);
String alg2 = mex.Configuration().addAlgorithm("svm", EnumAlgorithmsClasses.SupportVectorMachines);
              mex.Configuration().addAlgorithm("nb", EnumAlgorithmsClasses.NaiveBayes);
...
mex.Configuration().Algorithm(alg1).addParameter(new String[]{"C", "10^3", "alpha", "0.2"});
mex.Configuration().Algorithm(alg2).addParameter(new String[]{"C", "10^3", "alpha", "0.4"});
...
```

**Your runs: would like to represent every single execution (``EnumExecutionsType.SINGLE``) or a summary for a set of executions (``EnumExecutionsType.OVERALL``)? Are you training (``EnumPhases.TRAIN``) or testing (``EnumPhases.TEST``)) your model?**
```java
...
String ex1 = mex.Configuration().addExecution(EnumExecutionsType.OVERALL, EnumPhases.TRAIN);

             mex.Configuration().Execution(ex1).setStartDate(new Date());
             mex.Configuration().Execution(ex1).setAlgorithm(alg1);
             mex.Configuration().Execution(ex1).setStartsAtPosition("1233");
             mex.Configuration().Execution(ex1).setEndsAtPosition("1376");
             ...
             mex.Configuration().Execution(ex1).setEndDate(new Date());
```

**Adding performance measures**

```java
...
mex.Configuration().Execution(ex1).addPerformance(EnumMeasures.ACCURACY, .96);
mex.Configuration().Execution(ex1).addPerformance(EnumMeasures.TRUEPOSITIVERATE, .70);
...
```

**Parsing and generating the mex file**
```java
MEXSerializer.getInstance().saveToDisk(<file path>, <uri>, mex, MEXConstant.EnumRDFFormats.JSON_LD);
...
```

## A Simple Example

```java      
MyMEX_10 mex = new MyMEX_10();

//basic authoring provenance
mex.setAuthorName("D Esteves");
mex.setAuthorEmail("esteves@informatik.uni-leipzig.de");
//the algorithm and features
String[] features = {"min", "max", "ope clo"};
mex.Configuration().addFeature(features);
mex.Configuration().addAlgorithm(MEXEnum.EnumAlgorithm.NaiveBayes);

// -> your model's call here !

//setting the phase for the run
String executionId = mex.Configuration().addExecutionOverall(MEXEnum.EnumPhase.TEST);

//adding the performance(s)
mex.Configuration().ExecutionOverall(executionId).setAlgorithm(mex.Configuration().Algorithm(EnumAlgorithm.NaiveBayes));
mex.Configuration().ExecutionOverall(executionId).addPerformance(EnumMeasures.ACCURACY.toString(), .96);

try{
    //parsing and saving the mex file
    MEXSerializer_10.getInstance().parse(mex);
    MEXSerializer_10.getInstance().saveToDisk("ex001.ttl","http://mex.aksw.org/examples/001/", mex);
}catch (Exception e){
    System.out.println(e.toString());
}
```

## More Examples

More basic examples are available [here](https://github.com/AKSW/mexproject/tree/master/examples/src/main/java/log4mex). You can also visit the [project wiki](https://github.com/AKSW/mexproject/wiki/MEX-Wiki---Home) in order to check recent updates and further use cases.

# LOG4MEX API

## Do you need an API to manage machine learning metadata? 

* We aim to provide an API to help developers to manage ML metadata, improving interoperability and adding provenance information to the experiment.
* No need to create scripts to manage the outputs
* No need to create wrappers to save outputs into SGBD's
* Exporting clear information about your executions! Others do not need to guess what you did! (LOG4MEX is based on [MEX Vocabulary](https://github.com/AKSW/mexproject/tree/master/vocabulary))

##Requirements
Java 8, Maven 3

##License
LOG4MEX is licensed under the [Creative Commons Non-Commercial](http://creativecommons.org/licenses/by-nc/2.0/).

##Demo and Documentation
Project Page: [http://aksw.github.io/mexproject/]

SPARQL Endpoint: [http://mex.aksw.org/sparql]

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

Instantiating the main class for the experiment:

```java
MyMEX_10 mex = new MyMEX_10();
```
Saving the basic provenance information:
```java
mex.setAuthorName("")
...
```
Saving the core for the experiment: algorithms, hyperparameters, features, ...
```java
mex.Configuration().
...
```
Parsing and generating the mex file
```java
MEXSerializer_10.getInstance().parse(mex);
MEXSerializer_10.getInstance().saveToDisk(<file name>,<uri>, mex);
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

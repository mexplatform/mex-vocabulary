# MEX APIs

* Exporting the machine learning iterations and its performances through the MEX APIs

# A Java Wrapper

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

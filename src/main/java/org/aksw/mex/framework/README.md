## [MEX Framework](http://mex.aksw.org/): A new way to create machine learning metadata for Java code!

[![Join the chat at https://gitter.im/AKSW/mexproject](https://badges.gitter.im/AKSW/mexproject.svg)](https://gitter.im/AKSW/mexproject?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/AKSW/mexproject.svg?branch=master)](https://travis-ci.org/AKSW/mexproject)

When working with ML, scientifically speaking, you may want to represent your models and everything else related with, or at least you should =), so everybody else would be able to recreate your environment and achieve the same results as you did. This is the major aim of [Reproducible Research](https://en.wikipedia.org/wiki/Reproducibility).

A more focused contribution in that sense is when developing ML scripts by importing some ML library, such as [Weka](http://weka.sourceforge.net/doc.stable/). It turns out that before exporting any kind of data, code and metadata you can imagine, you have to get your hands dirty and set up everthing to have a more automatized environment, then achieving that having less effort. I'm pretty sure that dealing with *stdouts* manually is not an option for you =) Things such as a database *wrappers* and schema definitions are (boring) good examples of extra-work! Other than that, if you start using a `ML library A` then change (or just want to compare) to a `ML library B`, you gonna have to deal with different outputs (i.e., different schemas) and more work would be needed to integrate everything again :-( Lastly (but not less important), since you aim to share your data, you have to describe it (metadata) ;-) 

**What about if you could benefit from a standard schema for exporting ML metadata in order to represent and query your output data?** 

Here is where the MEX Framework comes into play: instead of cope with boring tasks as introduced before, what about annotate your Java code a bit? Simple like that! You annotate the code a bit and the framework gives to you the metadata file...

#### Let's say we have 4 Weka classifiers (J48, PART, DecisionTable and DecisionStump), you just have to annotate the variable with @Algorithm =)
```java
  @Algorithm(algorithmType = MEXEnum.EnumAlgorithms.J48, algorithmID = "1") 
  public J48 wekaJ48;
  
  @Algorithm(algorithmType = MEXEnum.EnumAlgorithms.PART, algorithmID = "2") 
  public PART wekaPART;
  
  @Algorithm(algorithmType = MEXEnum.EnumAlgorithms.DecisionTable, algorithmID = "3") 
  public DecisionTable wekaDecisionTable;
  
  @Algorithm(algorithmType = MEXEnum.EnumAlgorithms.DecisionStump, algorithmID = "4") 
  public DecisionStump wekaDecisionStump;
```
#### In the same way, you annotate the object with keeps some measures...such as *accuracy* and *error* rates...
```java
  @Measure(idMeasure = MEXEnum.EnumMeasures.ERROR) 
  public List<Double> errors;
  
  @Measure(idMeasure = MEXEnum.EnumMeasures.ACCURACY) 
  public List<Double> accuracies;
```
#### You can also say something related with the experiment itself, such as
```java
@ExperimentInfo(createdBy = "Esteves", email = "esteves@informatik.uni-leipzig.de", title = "Weka Lib Example", tags = {"WEKA","J48", "DecisionTable", "MEX", "Iris"})
@Hardware(cpu = "Intel Core i7", memory = "8 GB", hdType = "SSD")
@SamplingMethod(klass = MEXEnum.EnumSamplingMethod.CrossValidation, trainSize = 0.8, testSize = 0.2, folds = 10)
public class Foo{
  ...
}
```
#### Then, just benefit from the automatic metadata generation process
```java
java -cp /home/user/mexframework org.aksw.mex.framework.MetaGeneration -uc IrisWekaExample.java -out mymex01.ttl
java -cp /home/user/mexframework org.aksw.mex.framework.MetaGeneration -uc IrisJSATExample.java -out mymex02.ttl
```
Now you can compare the performance of *Weka* and *JSAT* libs at once! No extra work is needed...

#### The output format is super cool (RDF/Turtle) and you can also simply query it! =) 
##### Like, "*just give me all the executions (configurations) of C-SVM classifier that have accuracy grather then 0.9*"
```SPARQL
  PREFIX  mexcore:  <http://mex.aksw.org/mex-core/>
  PREFIX  mexperf:  <http://mex.aksw.org/mex-perf/>
  PREFIX  mexalgo:  <http://mex.aksw.org/mex-algo/>
  SELECT  ?e ?acc
  WHERE   { ?e mexperf:accuracy ?acc .
          FILTER (?acc > 0.90)
          ?e prov:used mexalgo:C-SVM . }
```

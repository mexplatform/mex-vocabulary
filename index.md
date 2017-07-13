**MEX** is an _open source_ initiative which aims at facilitating the sharing and data management of machine learning experiment outcomes. Relying on Linked Data technologies, we provide a *simple*, *flexible* and *lightweight* platform to manage machine leaning metadata. In the following we introduce the sub-projects ([more info...](https://github.com/SmartDataAnalytics/mexproject/)).

## [MEX Vocabulary](https://github.com/SmartDataAnalytics/mexproject/tree/master/vocabulary)

MEX Vocabulary provides a free-format to export and share machine learning metadata, indifferent to existing workflow systems or frameworks. As major advantage, users can benefit of the ``mex format`` for further analysis and integrations with less effort ([more info...](https://github.com/AKSW/mexproject/tree/master/vocabulary)).

### How to cite
```Tex
@inproceedings{esteves2015mex,
  title={MEX Vocabulary: a Lightweight Interchange Format for Machine Learning Experiments},
  author={Esteves, Diego and Moussallem, Diego and Neto, Ciro Baron and Soru, Tommaso and Usbeck, Ricardo and Ackermann, Markus and Lehmann, Jens},
  booktitle={Proceedings of the 11th International Conference on Semantic Systems},
  pages={169--176},
  year={2015},
  organization={ACM}
}
```

## [LOG4MEX Library](https://github.com/SmartDataAnalytics/mexproject/tree/master/log4mex)

**LOG4MEX** is a Java library ([javadoc](http://dne5.com/mex/documentation/log4mex/)) which aims at facilitating the ``data management`` for machine learning experiments, increasing the ``interoperability`` as well as adding ``provenance`` to the generated metadata ([more info](https://github.com/AKSW/mexproject/tree/master/log4mex) and [use cases](https://github.com/AKSW/mexproject/wiki/LOG4MEX-Use-Cases)).

### How to cite
```Tex
@inproceedings{esteves2017log4mex,
  title={{LOG4MEX}: A Library to Export Machine Learning Experiments},
  author={Esteves, Diego and Moussallem, Diego and Soru, Tommaso and Baron Neto, Ciro and Lehmann, Jens and Ngomo, Axel and Duarte, Julio Cesar},
  booktitle={Web Intelligence (WI), 2017 IEEE/WIC/ACM International Conference},
  year={2017},
  organization={IEEE}
}
```

**Advantages**
###
> With a few lines of code, automatically generate metadata for your ML experiments at every run (code excerpt below).

```java
MyMEX mex = new MyMEX();
...
String ex1 = mex.Configuration().addExecution(EnumExecutionsType.OVERALL, EnumPhases.TRAIN);
mex.Configuration().Execution(ex1).setStartDate(new Date());
mex.Configuration().Execution(ex1).setAlgorithm(alg1);
...
mex.Configuration().Execution(ex1).setEndDate(new Date());
...
```

> Never lose an experiment configuration and find the best results among thousands with a simple query!

    PREFIX  mexcore:  <http://mex.aksw.org/mex-core#>
    PREFIX  mexperf:  <http://mex.aksw.org/mex-perf#>
    PREFIX  mexalgo:  <http://mex.aksw.org/mex-algo#>
    PREFIX  prov:     <http://www.w3.org/ns/prov#>
    PREFIX  rdfs:     <http://www.w3.org/2000/01/rdf-schema#>

    SELECT DISTINCT ?ExecutionID ?Algorithm ?Performance ?fMeasure WHERE {
      ?execution prov:used ?alg;
                 prov:id ?ExecutionID.
      ?Performance prov:wasGeneratedBy  ?execution.
      ?Performance mexperf:f1Measure ?fMeasure.
      ?alg a mexalgo:Algorithm.
      ?alg rdfs:label ?Algorithm.
    } 
    ORDER BY DESC (?fMeasure)
    LIMIT 4

<table class="sparql" border="1">
  <tr>
    <th>ExecutionID</th>
    <th>Algorithm</th>
    <th>Performance</th>
    <th>fMeasure</th>
  </tr>
  <tr>
    <td>"C0_MEX_EXEC_D44"</td>
    <td>"BaggingJ48"</td>
    <td><a href="http://mex.aksw.org/examples/mea_clas_C0_MEX_EXEC_D44_cf_1_-568657719">mea_clas_C0_MEX_EXEC_D44_cf_1_-568657719</a></td>
    <td>0.9968</td>
  </tr>
  <tr>
    <td>"C0_MEX_EXEC_D24"</td>
    <td>"Logistic Model Trees"</td>
    <td><a href="http://mex.aksw.org/examples/mea_clas_C0_MEX_EXEC_D24_cf_1_-568657719">mea_clas_C0_MEX_EXEC_D24_cf_1_-568657719</a></td>
    <td>0.9968</td>
  </tr>
  <tr>
    <td>"C0_MEX_EXEC_D16"</td>
    <td>"Random Forest"</td>
    <td><a href="http://mex.aksw.org/examples/mea_clas_C0_MEX_EXEC_D16_cf_1_-568657719">mea_clas_C0_MEX_EXEC_D16_cf_1_-568657719</a></td>
    <td>0.9968</td>
  </tr>
  <tr>
    <td>"C0_MEX_EXEC_D64"</td>
    <td>"Multilayer Perceptron"</td>
    <td><a href="http://mex.aksw.org/examples/mea_clas_C0_MEX_EXEC_D64_cf_1_-568657719">mea_clas_C0_MEX_EXEC_D64_cf_1_-568657719</a></td>
    <td>0.9967</td>
  </tr>
</table>

> Analyse your experiment and related variables visually with Linked Data technologies. Compare your results with the state of the art and enter our leaderboard by sharing the metadata on the [WASOTA](http://cirola2000.cloudapp.net:3019/#/home).

<!--![rel](http://dne5.com/mex/imagens/mex_relations_lod.png)-->

## [MEX Framework](https://github.com/AKSW/mexproject/tree/master/framework/src/main/java/org/aksw/mex/framework)

### How to cite
```Tex
@inproceedings{esteves2016mex,
  title={{MEX} Interfaces: Automating Machine Learning Metadata Generation},
  author={Esteves, Diego and Mendes, Pablo N and Moussallem, Diego and Duarte, Julio Cesar and Zaveri, Amrapali and Lehmann, Jens},
  booktitle={Proceedings of the 12th International Conference on Semantic Systems},
  pages={17--24},
  year={2016},
  organization={ACM}
}
@inproceedings{duarte2017mex,
  title={An Interoperable Service for the Provenance of Machine Learning Experiments},
  author={Duarte, Julio Cesar and Cavalcanti, Maria Claudia and Sousa Costa, Igor and Esteves, Diego},
  booktitle={Web Intelligence (WI), 2017 IEEE/WIC/ACM International Conference},
  year={2017},
  organization={IEEE}
}
```

What about annotating your machine learning code and generate ``mex output``? 

```java
@ExperimentInfo(createdBy = "Esteves", email = "esteves@informatik.uni-leipzig.de", title = "Weka Lib Example", tags = {"WEKA","J48", "DecisionTable", "MEX", "Iris"})
@Hardware(cpu = "Intel Core i7", memory = "8 GB", hdType = "SSD")
@SamplingMethod(klass = MEXEnum.EnumSamplingMethod.CrossValidation, trainSize = 0.8, testSize = 0.2, folds = 10)
public class Foo{
  ...
}
```
as easy as possible ([more info...](https://github.com/AKSW/mexproject/tree/master/framework/src/main/java/org/aksw/mex/framework)).

```java
java -cp /home/user/mexframework org.aksw.mex.framework.MetaGeneration -uc IrisWekaExample.java -out mymex01.ttl
```

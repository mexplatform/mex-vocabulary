## [MEX](http://mex.aksw.org/): Metadata for Machine Learning Outputs

[![Join the chat at https://gitter.im/AKSW/mexproject](https://badges.gitter.im/AKSW/mexproject.svg)](https://gitter.im/AKSW/mexproject?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/AKSW/mexproject.svg?branch=master)](https://travis-ci.org/AKSW/mexproject)

This project aims at facilitating the sharing and data management of machine learning experiment outputs. 

For more information, please see the sub projects listed bellow:

### MEX Project
  * [MEX Vocabulary: A Lightweight Interchange Format for Machine Learning Experiments](https://github.com/AKSW/mexproject/tree/master/vocabulary)
  * [LOG4MEX Library](https://github.com/AKSW/mexproject/tree/master/log4mex)
  * [MEX Framework](https://github.com/AKSW/mexproject/tree/master/framework)
  * [Examples](https://github.com/AKSW/mexproject/tree/master/examples)
  * [Get in touch! Google Group](mex-project@googlegroups.com)

##LOG4MEX Library: Demo and Documentation
![LOG4MEX](http://dne5.com/mex/diagram/log4mex-small.png)

Project Page: [http://aksw.github.io/mexproject/]

SPARQL Endpoint: [http://mex.aksw.org/sparql]

##License
MEX Project is licensed under the [Creative Commons Non-Commercial](http://creativecommons.org/licenses/by-nc/2.0/).

### How to use MEX?

For **semantic web users** the usage is straightforward, once the vocabulary can easily be handled with semantic web technologies, such as [Apache Jena](https://jena.apache.org/). After the generation, you can semantically validate the serialized file [here](http://mex.aksw.org/). 

For **non-semantic web users**, there are two ways to use `MEX`: if you're coding, you can import an *API* (**log4mex**), which implements an interface to be consumed into the user code for directly exporting the metadata in a simple manner, regardless possible semantic web aspects. 

More recently, we have started the framework's development, which aims at automatizing the task of generating metadata for ``Java`` classes based on the concepts of ``reflection``, ``annotation`` and ``generics``.

## [MEX](http://mex.aksw.org/): Metadata for Machine Learning Outputs

[![Join the chat at https://gitter.im/AKSW/mexproject](https://badges.gitter.im/AKSW/mexproject.svg)](https://gitter.im/AKSW/mexproject?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/AKSW/mexproject.svg?branch=master)](https://travis-ci.org/AKSW/mexproject)

This project aims to facilitate the sharing and data management of machine learning experiment outputs. 

The first milestone was the [vocabulary definition](http://www.w3.org/standards/semanticweb/ontology), i.e., the `schema definition` to describe and represent each machine learning **algorithm execution** and its **performance measures** in a concise (but comprehensive) way. At this stage, we want to achieve a **high level of interoperability**.

Futhermore, the development of *APIs* and the integration with *ML Tools* are goals in order to support different system architectures. 

Recently, we have started the framework's development, which aims to automatize the task of generating metadata for ``Java`` classes based on the concepts of ``reflection``, ``annotation`` and ``generics``.

For more information, please see the sub projects listed bellow:

### Projects
  * [Vocabulary: *A Lightweight Interchange Format for Machine Learning Experiments*](https://github.com/AKSW/mexproject/tree/master/ontology)
  * [LOG4MEX: *A ML Logger for Java*](https://github.com/AKSW/mexproject/tree/master/src/main/java/org/aksw/mex/log4mex)
  * [Framework: *A new way to generate ML metadada for Java code*](https://github.com/AKSW/mexproject/tree/master/src/main/java/org/aksw/mex/framework)
  * [Examples](https://github.com/AKSW/mexproject/tree/master/src/main/java/examples)
  * [Google Group](mex-project@googlegroups.com)

### How to use MEX?

For **semantic web users** the usage is straightforward, once the vocabulary can easily be handled with semantic web technologies, such as [Apache Jena](https://jena.apache.org/). After the generation, you can semantically validate the serialized file [here](http://mex.aksw.org/). 

![LOG4MEX](http://dne5.com/mex/diagram/log4mex-small.png)

For **non-semantic web users**, there are two ways to use `MEX`: if you're coding, you can import an *API* (**log4mex**), which implements an interface to be consumed into the user code for directly exporting the metadata in a simple manner, regardless possible semantic web aspects. Alternatively, over existing *machine learning frameworks* (100% transparent process), such as [DL-Learner](http://dl-learner.org/), [WEKA](http://www.cs.waikato.ac.nz/ml/weka/) and [FAMa](https://github.com/duartejulio/fama). It's a ongoing work, feel free to collaborate ;-)

Most recently, we have introduced a novel approach to generate metadata out of ML runs: MEX Framework: A Java Framework for Generating Machine Learning Metadata

Finnaly, for **general and non-expert users** we've created an [user interface](http://mex.aksw.org/) for exporting the ML experiment metadata.

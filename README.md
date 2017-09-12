## [MEX](http://mex.aksw.org/): Metadata for Machine Learning Outputs

[![Join the chat at https://gitter.im/AKSW/mexproject](https://badges.gitter.im/AKSW/mexproject.svg)](https://gitter.im/AKSW/mexproject?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/AKSW/mexproject.svg?branch=master)](https://travis-ci.org/AKSW/mexproject)

This project aims at facilitating the sharing and data management of machine learning experiment outputs. 

For more information, please see the sub projects listed bellow:

[Project Page](http://aksw.github.io/mexproject/)

[SPARQL Endpoint](http://mex.aksw.org/sparql) (will be updated soon!)

##License
MEX is Open Source and licensed under the GNU General Public License 3.
(Copyright (c) 2014-2016, Diego Esteves)

MEX uses several other libraries. An incomplete list is as follows:
  * Google Guava (Apache License Version 2.0)
  * Log4j (Apache License Version 2.0)
  * Apache Jena (Apache License Version 2.0)

### How to use MEX?

For **semantic web users** the usage is straightforward, once the vocabulary can easily be handled with semantic web technologies, such as [Apache Jena](https://jena.apache.org/).

For **non-semantic web users**, there are two ways to use `MEX`: if you're coding, you can import [LOG4MEX](http://aksw.github.io/mexproject/) library, which implements an interface to be consumed into the user code for directly exporting the metadata in a simple manner, regardless possible semantic web aspects. 

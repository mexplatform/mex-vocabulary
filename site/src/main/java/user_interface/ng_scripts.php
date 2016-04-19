    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="js/angular.js"></script>
        <script src="js/angular-sanitize.js"></script>
        <script src="js/jquery-2.1.0.js"></script>
        <script src="3party/bootstrap-gh-pages/ui-bootstrap-tpls-0.11.0.js"></script>
        <script src="js/func.js"></script>
        <link href="3party/bootstrap/css/bootstrap.min.css" rel="stylesheet"> 
        <script src="3party/jquery-ui/jquery-ui.js"></script>
        <link rel="stylesheet" href="3party/jquery-ui/jquery-ui.css">

<!--Template for application modal-->

<script type="text/ng-template" id="modalAgentApplication.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Organization Information</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
    <input type="radio" ng-model="agent.prop" value="prov:actedOnBehalfOf" > prov:actedOnBehalfOf
      <br>
	  <label style="margin-top: 10px">Label</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> rdfs:label </a>    <br/> 
    <input class="form-control"  placeholder="Contact Point Label" ng-model="agent.label"> <br/>
	<br>
    <label>Name</label> 
    <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_name'> foaf:name </a>    
    <br/> 
    <input class="form-control"  placeholder="Contact Point Name"  ng-model="agent.name"> <br/>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
	</script>
    
	<!--Template for experiment Configuration modal -->

<script type="text/ng-template" id="modalAgentExpConf.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Experiment Configuration</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
	<label style="margin-top: 10px">Description</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://purl.org/dc/terms/'> dct:description </a>    <br/> 
    <input class="form-control"   ng-model="expeconf.desc"> <br/>
      <br>
	  <h2>Hardware Configuration</h2>
	  <label style="margin-top: 10px">Os</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#Project'> doap:os </a>    <br/> 
    <input class="form-control"   ng-model="expeconf.os"> <br/>
	<br>
   <label>Cpu</label> 
            <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#Project'> mex:cpu </a>     
    <br/> 
    <input class="form-control"  ng-model="expeconf.cpu"> <br/>
	<br>
	   <label>Memory</label>  		
	<a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#homepage'> mex:memory </a>    
    <input class="form-control"  ng-model="expeconf.memory"> <br/>
	<br>
	<label>HdType</label> 
    <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#description'> mex:hdType </a>    <br/> 
    <input class="form-control"  ng-model="expeconf.hdType"> <br/>
	<br>
	<label>VideoGraphs</label>   
    <a class="desc2" target="_blank" href='http://dublincore.org/documents/dcmi-terms/#terms-publisher'> mex:videoGraphs</a>    <br/> 
    <input class="form-control"  ng-model="expeconf.videoGraphs"> <br/>
	<br>
	  <label>Cache</label> 
     <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#Project'> mex:cache </a>    <br/> 
    <input class="form-control"  ng-model="expeconf.cache"> <br/>
	<br>
	      <br>
	  <h2>DataSet</h2>
	 <label>Title</label>  
      <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_mbox'> dcterms:title</a>     <br/> 
    <input class="form-control"   ng-model="expeconf.titleDset"> <br/>
	<br>
   <label>Description</label>  
   <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#homepage'>dcterms:description</a>    
    <br/> 
    <input class="form-control"  ng-model="expeconf.descriDset"> <br/>
	<br>
	<label>Homepage</label>  
    <a class="desc2" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:dataset_landingpage'> dcat:landingPage </a>    
    <input class="form-control"  ng-model="expeconf.landingPage"> <br/>
	<br>
	<h2>Sampling Method</h2>
	<br>
	  <label style="margin-top: 10px">Train Size</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> mexcore:trainSize </a>    <br/> 
    <input class="form-control"   ng-model="expeconf.train"> <br/>
	<br>
    <label>Test Size</label> 
    <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_name'>mexcore:testSize </a>    
    <br/> 
    <input class="form-control"  ng-model="expeconf.test"> <br/>
	<br>
	 <label style="margin-top: 10px">Folds</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> mexcore:folds</a>    <br/> 
    <input class="form-control"  ng-model="expeconf.folds"> <br/>
	<br>
	 <label style="margin-top: 10px">Sequential</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> mexcore:sequential:</a>    <br/> 
    <input class="form-control"  ng-model="expeconf.sequential"> <br/>
	<br>
	<h2>Implementation</h2>
	 <label style="margin-top: 10px">Software Name</label>  
            <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#name'> doap:name</a>     <br/> 
                <select class="form-control" ng-model="expeconf.softwareName">
                    <option>Weka</option>
                     <option>FAMa</option>
                      <option>LibSVM</option>
                </select>
	<br>
	<br>
	<br>
   <label style="margin-top: 10px">Software Version</label>  
            <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#revision'> doap:revision</a>     
    <br/> 
    <input class="form-control"  ng-model="expeconf.softwareVersion"> <br/>
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
	
	</script>
	
	<!--Template for experiment modal -->

<script type="text/ng-template" id="modalAgentExp.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Sampling Information</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
	<h1>Sampling</h1>
    <input type="radio" ng-model="expe.samp" value="mexcore:hasSamplingMethod" > mexcore:hasSamplingMethod
      <br>
	  <label style="margin-top: 10px">Train Size</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> mexcore:trainSize </a>    <br/> 
    <input class="form-control"   ng-model="expe.train"> <br/>
	<br>
    <label>Test Size</label> 
    <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_name'>mexcore:testSize </a>    
    <br/> 
    <input class="form-control"  ng-model="expe.test"> <br/>
	<br>
	 <label style="margin-top: 10px">Folds</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> mexcore:folds</a>    <br/> 
    <input class="form-control"  ng-model="expe.folds"> <br/>
	<br>
	 <label style="margin-top: 10px">Sequential</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> mexcore:sequential:</a>    <br/> 
    <input class="form-control"  ng-model="expe.sequential"> <br/>
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
	
	</script>
    
<!--Template for feature modal-->

<script type="text/ng-template" id="modalFeatureExp.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Feature</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
      <br>
	      <label style="margin-top: 10px">Experiment Configuration</label>
				 <br/>
			<select  class="form-control"ng-model="feature.expe">
    		<option ng-repeat="expeconf in feature.collection" value={{expeconf.iden}}>{{expeconf.iden}}</option>
            </select>
			<br/>
	  <label style="margin-top: 10px">Identifier</label> 
    <a class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dcterms:identifier</a>    <br/> 
   <label style="margin-top: 10px">{{feature.iden}}</label> 
	<br>
    <label>Value</label> 
    <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_name'>rdfs:label </a>    
    <br/> 
    <input class="form-control"   ng-model="feature.value"> <br/>
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>
	
<!--Template for execution modal-->

<script type="text/ng-template" id="modalActiveExec.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Execution </h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
	<h4>Information</h4>
    <div class="col-md-11">
	  <label style="margin-top: 10px">Identifier</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dcterms:identifier</a>    <br/> 
     <label style="margin-top: 10px">{{active.prop}}</label><br/>
	<br>
	<label style="margin-top: 10px">Description</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://purl.org/dc/terms/'> dct:description </a>    <br/> 
    <input class="form-control"   ng-model="active.desc"> <br/>
	<br>
    <label>StartedAtTime</label> 
    <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_name'>prov:startedAtTime </a>    
    <br/> 
    <input class="form-control"   ng-model="active.start"> <br/>
	<br>
	 <label style="margin-top: 10px">EndAtTime</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> prov:endAtTime</a>    <br/> 
    <input class="form-control" ng-model="active.end"> <br/>
		<br>
	</br>
    </div>
	<h4>Experiment Configuration </h4>
	<div class="col-md-11">
				 <br/>
			<select  class="form-control" ng-model="active.expe">
    		<option ng-repeat="expeconf in active.expeconf" value={{expeconf.iden}}>{{expeconf.iden}}</option>
            </select>
			<br/>
			</div>
	<h4>Algorithm</h4>
	<div class="col-md-11">
				 <br/>
			<select  class="form-control" ng-model="active.algo">
    		<option ng-repeat="algo in active.algos" value={{algo.prop}}>{{algo.prop}}</option>
            </select>
			<br/>
			</div>
		<h4>Phase</h4>
	  <div class="col-md-11">
                <select class="form-control" ng-model="active.phase">
                    <option>Test</option>
                     <option>Training</option>
					 <option>Validation</option>
                </select>
					<br>
					<br>
					<br>
	</div>
	<h4>Model</h4>
	  <div class="col-md-11">
		<label style="margin-top: 10px">Identifier</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'>dcterms:identifier </a>    <br/> 
    <label style="margin-top: 10px">{{active.modeliden}}</label><br>
	  <label style="margin-top: 10px">Description</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dcterms:description</a>    <br/> 
    <input class="form-control"  ng-model="active.modeldesc"> <br/>
		  <label style="margin-top: 10px">Date</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dcterms:date</a>    <br/> 
    <input class="form-control"  ng-model="active.modeldate"> <br/>
	</div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>

<!--Template for  collection modal-->
<script type="text/ng-template" id="modalParameterAlgo.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Parameter Information</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
           		 <label style="margin-top: 10px">Algorithm</label>
				 <br/>
			<select  class="form-control" ng-model="parameter.algo">
    		<option ng-repeat="algo in parameter.collection" value={{algo.prop}}>{{algo.prop}}</option>
            </select>
			<br/>
	<label style="margin-top: 10px">Identifier</label> 
    <a style="margin-top: 10px"  target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dcterms:identifier</a>     <br/> 
   <label style="margin-top: 10px">{{parameter.prop}} </label> <br/>
	  <label style="margin-top: 10px">Value</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> prop:value</a>    <br/> 
    <input class="form-control"  ng-model="parameter.value"> <br/>
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>

<!--Template for  model  modal-->

<script type="text/ng-template" id="modalPerfomance.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Perfomance</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
      <br>
	<label style="margin-top: 10px">Identifier</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'>dcterms:identifier </a>    <br/> 
  <label style="margin-top: 10px">{{perfomance.iden}}</label> <br/>
	  <label style="margin-top: 10px">Description</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dcterms:description</a>    <br/> 
    <input class="form-control"  ng-model="perfomance.desc"> <br/>
    <label style="margin-top: 10px">Type</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> mexperf</a>    <br/> 
    	   <select class="form-control" ng-model="perfomance.type">
                    <option>accuracy</option>
                     <option>sensitive</option>
					 <option>fmeasure</option>
                </select>
    <label style="margin-top: 10px">Execution</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dcterms:date</a>    <br/> 
    	<div class="col-md-11">
				 <br/>
			<select  class="form-control" ng-model="perfomance.exec">
    		<option ng-repeat="active in perfomance.collectionexec" value={{active.prop}}>{{active.prop}}</option>
            </select>
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>

<!--Template for  model  modal-->

<script type="text/ng-template" id="modalPhaseExec.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Phase Information</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
      <br>
	  <label style="margin-top: 10px">Execution</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> Execution</a>    <br/> 
    <input class="form-control"  ng-model="phase.exec"> <br/>
	<label style="margin-top: 10px">Identifier</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'>Phase</a>    <br/> 
    <input class="form-control"  ng-model="phase.iden"> <br/>
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>

<!--Template for  Algorithm modal-->

<script type="text/ng-template" id="modalAlgo.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Algorith Information</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
      <br>
	  <label style="margin-top: 10px">Identifier</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dctemrs:identifier</a><br/> 
   <label style="margin-top: 10px">{{algo.prop}} </label>
	  <div class="form-group" >
           		 <label style="margin-top: 10px">Name</label>
				 <br/>
            <div class="col-xs-9">
                <select class="form-control" ng-model="algo.iden">
                    <option>Naive Bayes</option>
                     <option>SVM</option>
                      <option>K-means</option>
                </select>
            </div>
	
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>

<!--Template for  Algorithm Class  modal-->

<script type="text/ng-template" id="modalAlgoC.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Algorith Information</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
      <br>
	 <label style="margin-top: 10px">Algorithm</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'>Algorithm</a>    <br/> 
    <input class="form-control"  ng-model="algoc.algo"> <br/>
	  <label style="margin-top: 10px">Identifier</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> dctemrs:identifier</a><br/> 
    <input class="form-control"  ng-model="algoc.iden"> <br/>

	
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>

<!--Template for    modal-->

<script type="text/ng-template" id="modalAlgoP.html">
    <form name="formLinkset" novalidate>
    <div class="modal-header">
    <h3 class="modal-title">New Algorithm Information</h3>
    <div style="padding: 10px 5px;">
    <span> 
    Here you can create new information. 
    </span>
    </div>
    </div>
    <div class="modal-body">
    <div class="row">
    <div class="col-md-11">
      <br>
	 <label style="margin-top: 10px">Algorithm</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'>Algorithm</a>    <br/> 
    <input class="form-control"  ng-model="algop.algo"> <br/>
	  <label style="margin-top: 10px">Name</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> doap:name</a><br/> 
    <input class="form-control"  ng-model="algop.name"> <br/>
	<label style="margin-top: 10px">HomePage</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> doap:homepage</a><br/> 
    <input class="form-control"  ng-model="algop.homePage"> <br/>
	<label style="margin-top: 10px">Revision</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> doap:revision</a><br/> 
    <input class="form-control"  ng-model="algop.revision"> <br/>
	<label style="margin-top: 10px">Description</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> doap:description</a><br/> 
    <input class="form-control"  ng-model="algop.desc"> <br/>
	<label style="margin-top: 10px">Program Language</label> 
    <a style="margin-top: 10px" class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> doap:programming-language</a><br/> 
    <input class="form-control"  ng-model="algop.lang"> <br/>
	<br>
    </div>
    </div>
    <div class="modal-footer">
    <button class="btn btn-primary" ng-click="ok()">OK</button>
    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
    </form>
</script>
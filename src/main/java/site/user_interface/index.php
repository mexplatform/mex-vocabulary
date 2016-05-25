<?php include 'header.php'; ?>
  <?php include './ng_scripts.php'; ?> 
  <div class="container">
	  <div>
      <br>
      <br>
      <br>
				  <div class="tabbable">
				  <ul class="nav nav-tabs">
				  <li>
				<a href="#1" data-toggle="tab">Context</a>
				   </li>
			   <li>
			   <a href="#2" data-toggle="tab">Experiment Data</a>
			   </li> 
			   <li>
			  <a href="#3" data-toggle="tab">Experiment Configuration</a>
				</li>    
				 <li>
				<a href="#4" data-toggle="tab">Execution</a>
			   </li>              
			   </ul>
	<div class="tab-content" style="text-align:left">
    <br>
    <br>
	  <div class="tab-pane" id="1">
      		<div class="form-group"><?php include 'application.php'; ?></div>
       </div>
	  <div class="tab-pane" id="2">
      		<div class="form-group"><?php include 'exp.php'; ?></div>
	  </div>
	  <div class="tab-pane" id="3">
        	<div class="form-group"><?php include 'exp_conf.php'; ?></div>
            <div class="form-group"><?php include 'algorithm.php'; ?></div>
	   </div>
	  <div class="tab-pane" id="4">
        	<div class="form-group"><?php include 'execution.php'; ?></div>
       </div>		 
		</div>
	  <div id="rdfOutput" style="display: none">
		  <a class="label label-success ng-binding"  href="" ng-click="downloadInnerHtml()">Download Mex file</a>
		  <pre id="rdf" style="margin: 0.5em; padding:0.5em; background-color:#eee; border:dashed 1px grey;">
  {{rdf}}
		  </pre>
	  </div>
	  <div id="rdfOutputValidator">
		  <div id="rdfOutputValidatorHTML">
		  </div>
	  </div>
	  <?php include 'footer.php'; ?>
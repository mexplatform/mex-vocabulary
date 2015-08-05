<?php

set_include_path(get_include_path() . PATH_SEPARATOR . '../lib/');
require_once "./lib/EasyRdf.php";

$ns = new EasyRdf_Namespace();
$ns->set("dcterms", "http://purl.org/dc/terms/");
$ns->set("dcat", "http://www.w3.org/ns/dcat#");
$ns->set("foaf", "http://xmlns.com/foaf/0.1/");
$ns->set("sd", "http://www.w3.org/ns/sparql-service-description#");
$ns->set("void", "http://rdfs.org/ns/void#");
$ns->set("owl", "http://www.w3.org/2002/07/owl#");
$ns->set("xsd", "http://www.w3.org/2001/XMLSchema#");
$ns->set("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
$ns->set("prov", "http://www.w3.org/ns/prov#");
$ns->set("mexcore", "http://www.ui.dne5.com/core#");
$ns->set("odrl", "http://www.w3.org/ns/odrl/2#");
$ns->set("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");

$mainDataset = true;

$data = '';

$graph = new EasyRdf_Graph();

generate($_POST['dataSets'][0], $graph, $data);

echo ($data);

function generate($ds, $graph, &$data) {
    if (!isset($ds['datasetURI']) || $ds['datasetURI'] == '') {
        echo "Dataset URI missing";
        if (isset($ds['title']) && $ds['title'] != '')
            echo " - Dataset Title: " . $ds['title'];
        return;
    }
    $rdf = $graph->resource('#application', array('mexcore:ApplicationContext','prov:Agent','prov:Person', 'prov:Organization', 'doap:Project'));
	
	  if (isset($ds['givenName']) && $ds['givenName'] != '')
        $rdf->set('foaf:givenName', $ds['givenName']);
	  if (isset($ds['mbox']) && $ds['mbox'] != '')
        $rdf->set('foaf:mbox', $graph->resource($ds['mbox']));
	  if (isset($ds['fHomePage']) && $ds['fHomePage'] != '')
        $rdf->set('foaf:homePage', $graph->resource($ds['fHomePage']));
    if (isset($ds['homePage']) && $ds['homePage'] != '')
        $rdf->set('doap:homePage', $graph->resource($ds['homePage']));
    if (isset($ds['ddescription']) && $ds['ddescription'] != '')
        $rdf->set('doap:description', $ds['ddescription']);
    if (isset($ds['category']) && $ds['category']!= '')
        $rdf->set('doap:category', $ds['category']);
    if (isset($ds['seeAlso']) && $ds['seeAlso'] != '')
        $rdf->set('rdfs:seeAlso', $graph->resource($ds['seeAlso']));
    if (isset($ds['location']) && $ds['location']!= '')
        $rdf->set('doap:location', $graph->resource($ds['location']));		
	if (isset($ds["agent"])) {
        foreach ($ds["agent"] as $l) {
            $r;
            if (isset($l['resource']) && $l['resource'] != '') {
                $r = $graph->resource($l['resource']);
                $graph->add($rdf, $l["prop"], $r);

                if (isset($l['label']) || isset($l['name']) ) {
                    $r = $graph->resource($l['resource'], array('a prov:Agent','prov:Organization'));
                }
            } else {
                $r = $graph->newBNode(array('prov:Agent','prov:Organization'));
                $graph->add($rdf, $l["prop"], $r);
            }
            if (isset($l['name']) && $l['name'] != '')
                $r->set('foaf:name', $l['name']);
        }
    }

//EXP started 
    $rdf = $graph->resource("#exp", array('prov:Entity','mexcore:Experiment'));
	
	  if (isset($ds['idenExp']) && $ds['idenExp'] != '')
        $rdf->set('dcterms:identifier', $ds['idenExp']);
	  if (isset($ds['titleExp']) && $ds['titleExp'] != '')
        $rdf->set('dcterms:title', $ds['titleExp']);
	  if (isset($ds['dateExp']) && $ds['dateExp'] != '')
        $rdf->set('dcterms:date', $ds['dateExp']);
    if (isset($ds['descriExp']) && $ds['descriExp'] != '')
        $rdf->set('dcterms:description', $ds['descriExp']);
    if (isset($ds['dateNormDes']) && $ds['dateNormDes'] != '')
        $rdf->set('mexcore:dataNormalizedDescription', $ds['dateNormDes']);
    if (isset($ds['outRemoDes']) && $ds['outRemoDes']!= '')
        $rdf->set('mexcore:outliersRemovedDescription', $ds['outRemoDes']);
    if (isset($ds['noiseRemoDes']) && $ds['noiseRemoDes'] != '')
        $rdf->set('mexcore:noiseRemovedDescription', $ds['noiseRemoDes']);
    if (isset($ds['attSelecDes']) && $ds['attSelecDes']!= '')
        $rdf->set('mexcore:attributeSelectionDescription', $ds['attSelecDes']);
        $rdf->set('prov:wasAttributedTo:',  $graph->resource('#application'));
        $rdf->set('mexcore:hasSamplingMethod',  $graph->resource('#sampling'));
		

$rdf = $graph->resource("#sampling", array('prov:Entity','mexcore:SamplingMethod'));	
		if (isset($ds["expe"])) {
          foreach ($ds["expe"] as $l) {
			  if (isset($l['train']) && $l['train'] != '')
                $rdf->set('mexcore:trainSize', $l['train']);
				  if (isset($l['test']) && $l['test'] != '')
                $rdf->set('mexcore:testSize', $l['test']);
				  if (isset($l['folds']) && $l['folds'] != '')
                $rdf->set('mexcore:folds', $l['folds']);
				  if (isset($l['sequential']) && $l['sequential'] != '')
                $rdf->set('mexcore:sequential', $l['sequential']);
                $rdf->set('prov:wasDerivedFrom',  $graph->resource("#exp"));
				
		  }
		}
		
$rdf = $graph->resource("#dset", array('prov:Entity','mexcore:Dataset'));
	
	if (isset($ds['titleDset']) && $ds['titleDset'] != '')
        $rdf->set('dcterms:title', $ds['titleDset']);
    if (isset($ds['descriDset']) && $ds['descriDset'] != '')
        $rdf->set('dcterms:description', $ds['descriDset']);
    if (isset($ds['landingPage']) && $ds['landingPage'] != '')
        $rdf->set('dcat:landingPage', $graph->resource($ds['landingPage']));
		
$rdf = $graph->resource("#hardware", array('prov:Entity','doap:Project','mexcore:HardwareConfiguration'));
	
	if (isset($ds['os']) && $ds['os'] != '')
        $rdf->set('doap:os', $ds['os']);
    if (isset($ds['cpu']) && $ds['cpu'] != '')
        $rdf->set('mexcore:cpu', $ds['cpu']);
    if (isset($ds['memory']) && $ds['memory'] != '')
        $rdf->set('mexcore:memory', $ds['memory']);
    if (isset($ds['hdType']) && $ds['hdType'] != '')
        $rdf->set('mexcore:hdType', $ds['hdType']);
    if (isset($ds['cache']) && $ds['cache'] != '')
        $rdf->set('mexcore:cpu-cache', $ds['cache']);
	if (isset($ds['videoGraphs']) && $ds['videoGraphs'] != '')
        $rdf->set('mexcore:video-graphs', $ds['videoGraphs']);

//execution
	$parameter = array();
	 if (isset($ds["active"])) {
		 $k=0;
        foreach ($ds["active"] as $m) {
		 
            // if there is a resource
            if (isset($m['resource']) && $m['resource'] != '') {
                $rdf = $graph->resource("#".$m['prop'], array('prov:Activity', 'mexcore:Execution'));
                $lastword = strlen($m['prop']) -1;
                if (isset($m['iden']) || isset($m['start']) || isset($m['end'])) {
                    $rdf = $graph->resource("#".$m['prop'], array('prov:Activity', 'mexcore:Execution'));
					$lastword = strlen($m['prop']) -1;
                }
            } else {
					$rdf = $graph->resource("#".$m['prop'], array('prov:Activity', 'mexcore:Execution'));
					$lastword = strlen($m['prop']) -1;
					$k++;
					
            }
			    $rdf->set('prov:used', $graph->resource(" #algparam".$k." #execparam".$k." #model".$k." #alg".$k." #phase"." #dset"." #hardware"));
            if (isset($m['iden']) && $m['iden'] != '')
                $rdf->set('dcterms:identifier', $m['iden']);
            if (isset($m['start']) && $m['start'] != '')
                $rdf->set('prov:startedAtTime', $m['start']);
            if (isset($m['end']) && $m['end'] != '')
                $rdf->set('prov:endAtTime', $m['end']);
        }	
    }
	
//execparameter
	$i=0;
	 if (isset($ds["active"])) {
        foreach ($ds["active"] as $l) {
			$rdf = $graph->resource("#".$l['execparameter'], array('prov:Collection, mexcore:ExecutionParameterCollection'));
				 if (isset($l['execparameter']) && $l['execparameter'] != ''){
                $rdf->set('dcterms:identifier', $l['execparameter']);
				$parameter[i]= $l['execparameter'];
				  if ($parameter[i]!= null) {
					   foreach ($ds["parameter"] as $j) {												
										if($parameter[i] == $j['exec']) {
                                       $rdf->set('prov:hadMember', $graph->resource($j['prop']));
									   $i++;
									}
					  	 }
					   }
				  }
		}
	 }
	
	/*
	:execparam1
  a prov:Collection, mexcore:ExecutionParameterCollection;
  dcterms:identifier "ep"^^xsd:string;
.*/
//parameter execution 
	
	if (isset($ds["parameter"])) {
        foreach ($ds["parameter"] as $l) {
            // if there is a resource
            if (isset($l['resource']) && $l['resource'] != '') {
                $rdf = $graph->resource("#".$l['prop'], array('mexcore:InputParameter'));

                if (isset($l['iden']) || isset($l['start']) || isset($l['end'])) {
                    $rdf = $graph->resource("#".$l['prop'], array('mexcore:InputParameter'));
                }
            } else {
					$rdf = $graph->resource("#".$l['prop'], array('mexcore:InputParameter'));
					
            }
            if (isset($l['iden']) && $l['iden'] != '')
                $rdf->set('dcterms:identifier', $l['iden']);
        }
    }
	

//model

	 if (isset($ds["model"])) {
		 $k=0;
        foreach ($ds["model"] as $m) {
		 
            // if there is a resource
            if (isset($m['resource']) && $m['resource'] != '') {
                $rdf = $graph->resource("#".$m['prop'], array('mexcore:Model'));
                if (isset($m['iden']) || isset($m['start']) || isset($m['end'])) {
                    $rdf = $graph->resource("#".$m['prop'], array('mexcore:Model'));
                }
            } else {
					$rdf = $graph->resource("#".$m['prop'], array('mexcore:Model'));
					$k++;
					
            }
            if (isset($m['iden']) && $m['iden'] != '')
                $rdf->set('dcterms:identifier', $m['iden']);
            if (isset($m['desc']) && $m['desc'] != '')
                $rdf->set('dcterms:description', $m['desc']);
            if (isset($m['page']) && $m['page'] != '')
                $rdf->set('prov:landingPage', $m['page']);
				if (isset($m['date']) && $m['date'] != '')
                $rdf->set('dcterms:date', $m['date']);
			 $rdf->set('prov:used', $graph->resource(" #exec".$k));
        }	
    }
	
	//phase

//dataid
  
    if (isset($ds['datasets'])) {
        foreach ($ds['datasets'] as $ds2) {
            if (isset($ds2['datasetURI']) && $ds2['datasetURI'] != '') {
                $r = $graph->resource($ds2['datasetURI']);
                $graph->add($rdf, $ds2['type'], $r);
                generate($ds2, $graph, $data);
            } else {
                echo 'Error: Please check datasets URI!';
                return;
            }
        }
    }

    if (isset($ds["distribution"])) {
        foreach ($ds["distribution"] as $d) {
            if (isset($d['accessUrl']) && isset($d['prop'])) {
                $r = $graph->resource($d['accessUrl']);
                $graph->add($rdf, $d['prop'], $r);
            }
        }
    }
    if (isset($ds["linkset"])) {
        foreach ($ds["linkset"] as $l) {
            if (isset($l['exampleResource']) && isset($l['prop']))
                $r = $graph->resource($l['exampleResource']);
            $graph->add($rdf, $l["prop"], $r);
        }
    }

    //contact information
    if (isset($ds["agentt"])) {
        foreach ($ds["agent"] as $l) {
            $r;
            // if there is a resource
            if (isset($l['resource']) && $l['resource'] != '') {
                $r = $graph->resource($l['resource']);
                $graph->add($rdf, $l["prop"], $r);

                if (isset($l['label']) || isset($l['name']) || isset($l['mbox'])) {
//                  $r = $graph->resource($l['resource'], array('prov:Agent', 'foaf:Agent'));
                    $r = $graph->resource($l['resource'], array('dataid:Agent'));
                }
            } else {
//              $r = $graph->newBNode(array( 'prov:Agent', 'foaf:Agent'));
                $r = $graph->newBNode(array('dataid:Agent'));
                $graph->add($rdf, $l["prop"], $r);
            }
            if (isset($l['label']) && $l['label'] != '')
                $r->set('rdfs:label', $l['label']);
            if (isset($l['name']) && $l['name'] != '')
                $r->set('foaf:name', $l['name']);
            if (isset($l['mbox']) && $l['mbox'] != '')
                $r->set('foaf:mbox', $l['mbox']);
        }
    }


    if (isset($ds["wasDerivedFromAgent"]) || (isset($ds["wasDerivedFromTitle"]) && $ds["wasDerivedFromTitle"] != ''))  {
        $r = $graph->newBNode(array('dataid:Entity'));
        $graph->add($rdf, "prov:wasDerivedFrom", $r);
        if ((isset($ds["wasDerivedFromTitle"]) && $ds["wasDerivedFromTitle"] != ''))
            $r->set('dct:title', $ds["wasDerivedFromTitle"]);
        foreach ($ds["wasDerivedFromAgent"] as $l) {
            $r2 = $graph->newBNode(array('dataid:Agent'));
            $graph->add($r, $l["prop"], $r2);
            if (isset($l['label']))
                $r2->set('rdfs:label', $l['label']);
            if (isset($l['name']))
                $r2->set('foaf:name', $l['name']);
            if (isset($l['mbox']))
                $r2->set('foaf:mbox', $l['mbox']);
        }
    }



    if (isset($ds["wasGeneratedByAgent"]) || (isset($ds["wasGeneratedByTitle"]) && $ds["wasGeneratedByTitle"] != '')) {
        $r = $graph->newBNode(array('dataid:Activity'));
        $graph->add($rdf, "prov:wasGeneratedBy", $r);
        if ($ds["wasGeneratedByTitle"] != '')
            $r->set('dct:title', $ds["wasGeneratedByTitle"]);
        foreach ($ds["wasGeneratedByAgent"] as $l) {
            $r2 = $graph->newBNode(array('dataid:Agent'));
            $graph->add($r, $l["prop"], $r2);
            if (isset($l['label']))
                $r2->set('rdfs:label', $l['label']);
            if (isset($l['name']))
                $r2->set('foaf:name', $l['name']);
            if (isset($l['mbox']))
                $r2->set('foaf:mbox', $l['mbox']);
        }
    }


    if (isset($ds["distribution"])) {
        foreach ($ds["distribution"] as $d) {
            if (isset($d['accessUrl'])) {
//                $rdfo = $graph->resource($d['accessUrl'], array('rdfs:subClassOf', 'dcat:distribution'));
                $rdfo = $graph->resource($d['accessUrl'], array('dataid:Distribution'));
                if (isset($d['title']))
                    $rdfo->set('dct:title', $d['title']);
                if (isset($d['description']))
                    $rdfo->set('dct:description', $d['description']);
                if (isset($d['issued']))
                    $rdfo->set('dct:issued', $d['issued']);
                if (isset($d['rights']))
                    $rdfo->set('dct:rights', $d['rights']);
                if (isset($d['accessUrl']))
                    $rdfo->set('dcat:accessURL', $graph->resource($d['accessUrl']));
                if (isset($d['mediaType']))
                    $rdfo->set('dcat:mediaType', $d['mediaType']);
                if (isset($d['format']))
                    $rdfo->set('dct:format', $d['format']);
                if (isset($d['triples']))
                    $rdfo->set('void:triples', $d['triples']);
            }
        }
    }

    if (isset($ds["linkset"])) {
        foreach ($ds["linkset"] as $d) {
            $rdfo = $graph->resource($d['exampleResource'], array('dataid:Linkset'));
            $rdfo->set('dct:issued', $d['issued']);
            $rdfo->set('dct:modified', $d['modified']);
            $rdfo->set('void:exampleResource', $d['exampleResource']);
            $rdfo->set('void:linkPredicate', $d['linkPredicate']);
            $rdfo->set('void:triples', $d['triples']);
            $rdfo->set('void:target', $d['target']);
        }
    }
    $format = 'turtle';

    $data = $graph->serialise($format);
//    $data = str_replace($data, $_POST['keyword'], str_replace($_POST['keyword'],',','","'));
    if (isset($ds['keyword']))
        $data = str_replace($ds['keyword'], str_replace(array(',', ', '), '","', $ds['keyword']), $data);
    if (!is_scalar($data)) {
        $data = var_export($data, true);
    }
}

?>
<?php

set_include_path(get_include_path() . PATH_SEPARATOR . '../lib/');
require_once "./lib/EasyRdf.php";

$ns = new EasyRdf_Namespace();
$ns->set("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
$ns->set("xsd", "http://www.w3.org/2001/XMLSchema#");
$ns->set("owl", "http://www.w3.org/2002/07/owl#");
$ns->set("prov", "http://www.w3.org/ns/prov#");
$ns->set("dcterms", "http://purl.org/dc/terms/");
$ns->set("dcat", "http://www.w3.org/ns/dcat#");
$ns->set("dc", "http://purl.org/dc/elements/1.1/");
$ns->set("dct", "http://purl.org/dc/terms/");
$ns->set("doap", "http://usefulinc.com/ns/doap#");
$ns->set("foaf", "http://xmlns.com/foaf/0.1/");
$ns->set("mexcore", "http://mex.aksw.org/mex-core#");
$ns->set("mexalgo", "http://mex.aksw.org/mex-algo#");
$ns->set("mexperf", "http://mex.aksw.org/mex-perf#");
$ns->set("this", "http://mex.aksw.org/example/001/");

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
    $rdf = $graph->resource('this:application', array('mexcore:ApplicationContext','prov:Agent','prov:Person', 'prov:Organization'));
	   if (isset($ds['seeAlso']) && $ds['seeAlso'] != '')
        $rdf->set('rdfs:seeAlso', $graph->resource($ds['seeAlso']));	
		
		if (isset($ds['copyDate']) && $ds['copyDate'] != '')
        $rdf->set('dct:dateCopyrigthed', $ds['copyDate']);
		
	  if (isset($ds['givenName']) && $ds['givenName'] != '')
        $rdf->set('foaf:givenName', $ds['givenName']);
		
	  if (isset($ds['mbox']) && $ds['mbox'] != '')
        $rdf->set('foaf:mbox', $graph->resource($ds['mbox']));
		
	  if (isset($ds['fHomePage']) && $ds['fHomePage'] != '')
        $rdf->set('foaf:homePage', $graph->resource($ds['fHomePage']));
		
    if (isset($ds['homePage']) && $ds['homePage'] != '')
        $rdf->set('doap:homePage', $graph->resource($ds['homePage']));

 $rdf = $graph->resource( "this:context", array('prov:Entity','mexcore:'.$ds['context']));
 		$rdf->set('prov:wasAttributedTo', 'this:application');

//Experiment Data 
    $rdf = $graph->resource("this:experiment-header", array('prov:Entity','mexcore:Experiment'));
	
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
        $rdf->set('prov:wasAttributedTo:',  $graph->resource('this:application'));
		

#ExperimentConf

	 if (isset($ds["expeconf"])) {
		 $k=1;
		 $w=0;
        foreach ($ds["expeconf"] as $m) {
		 
            // if there is a resource
            if (isset($m['iden']) && $m['iden'] != '') {
                $rdf = $graph->resource("this:".$m['iden'], array('prov:Activity', 'mexcore:ExperimentConfiguration'));
                $lastword = strlen($m['iden']) -1;
				$rdf->set('dcterms:identifier', $m['iden']);
                $rdf->set('dct:description', $m['desc']);
			    $rdf->set('prov:used', $graph->resource(" this:samplingmehtod".$k." this:dataset".$k." this:hardware".$k." this:implementation".$k." this:featureCollection".$k));
		}
				$k++;
				$w++;
        }	
    }
	


#Features

	 if (isset($ds["feature"])) {
		 $k=1;
        foreach ($ds["feature"] as $m) {
		 
            // if there is a resource
            if (isset($m['iden']) && $m['iden'] != '') {
                $rdf = $graph->resource("this:".$m['iden'], array('mexcore:Feature', 'prov:Entity'));
				$rdf->set('dcterms:identifier', $m['iden']);
                $rdf->set('rdfs:label', $m['value']);
		}
				$k++;
        }	
    }
	

#FeatureCollection

	 if (isset($ds["feature"])) {
		 $k=1;
        foreach ($ds["feature"] as $m) {
		 
            // if there is a resource
            if($m['expe'] == "configuration".$k){
                $rdf = $graph->resource("this:featureCollection".$k, array('mexcore:Feature', 'prov:Entity'));
					 if (isset($ds["feature"])) {
					foreach ($ds["feature"] as $w) {
						if($w['expe'] == "configuration".$k ) {
							$rdf->set('prov:used', "this:".$w['iden']);
		}
			}
			}
			}
		$k++;
			}
        }	
#SamplingMethod

	 if (isset($ds["expeconf"])) {
		 $k=1;
        foreach ($ds["expeconf"] as $m) {
		 
            // if there is a resource
            if (isset($m['iden']) && $m['iden'] != '') {
                $rdf = $graph->resource("this:samplingmethod".$k, array('prov:Entity', 'mexcore:SlidingValidation'));
						 $rdf->set('mexcore:trainSize', $m['train']);
						 $rdf->set('mexcore:testSize', $m['test']);
						 $rdf->set('mexcore:folds', $m['folds']);
						 $rdf->set('mexcore:sequential', $m['sequential']);

		}
				$k++;
        }	
    }
#Dataset

	 if (isset($ds["expeconf"])) {
		 $k=1;
        foreach ($ds["expeconf"] as $m) {
		 
            // if there is a resource
            if (isset($m['iden']) && $m['iden'] != '') {
                $rdf = $graph->resource("this:dataset".$k, array('prov:Entity','mexcore:Dataset'));
						 $rdf->set('dcterms:title', $m['titleDset']);
						 $rdf->set('dcterms:description', $m['descriDset']);
						 $rdf->set('dcat:landingPage', $m['landingPage']);

		}
				$k++;
        }	
    }
#Hardware

	 if (isset($ds["expeconf"])) {
		 $k=1;
        foreach ($ds["expeconf"] as $m) {
		 
            // if there is a resource
            if (isset($m['iden']) && $m['iden'] != '') {
                $rdf = $graph->resource("this:hardware".$k, array('prov:Entity','doap:Project','mexcore:HardwareConfiguration'));
						$rdf->set('doap:os', $m['os']);
						$rdf->set('mexcore:cpu', $m['cpu']);
						$rdf->set('mexcore:memory', $m['memory']);
						$rdf->set('mexcore:hdType', $m['hdType']);
						$rdf->set('mexcore:cpu-cache', $m['cache']);
						$rdf->set('mexcore:video-graphs', $m['videoGraphs']);

		}
				$k++;
        }	
    }

#Implementation

	 if (isset($ds["expeconf"])) {
		 $k=1;
        foreach ($ds["expeconf"] as $m) {
		 
            // if there is a resource
            if (isset($m['iden']) && $m['iden'] != '') {
                $rdf = $graph->resource("this:implementation".$k, array('mexalgo:'.$m['softwareName']));
						$rdf->set('doap:name', $m['softwareName']);
						$rdf->set('doap:revision', $m['softwareVersion']);
		}
				$k++;
        }	
    }
	
//execution

	 if (isset($ds["active"])) {
		 $k=1;
        foreach ($ds["active"] as $m) {
		 
            // if there is a resource
            if (isset($m['prop']) && $m['prop'] != '') {
                $rdf = $graph->resource("this:".$m['prop'], array('prov:Activity', 'mexcore:Execution'));
                $rdf->set('dct:description', $m['desc']);         
                $rdf->set('prov:startedAtTime', $m['start']);
                $rdf->set('prov:endAtTime', $m['end']);
				$rdf->set('dcterms:identifier', $m['modeliden']);
				$rdf->set('dcterms:description', $m['modeldesc']);
				$rdf->set('dcterms:date', $m['modeldate']);
				$rdf->set('prov:used', ('this:'.$m['expe'].'  '.'this:'.$m['algo'].'  '.'this:'.$m['phase']));
			}
				$k++;
        }	
    }

	
#Perfomance

	 if (isset($ds["perfomance"])) {
		 $k=1;
        foreach ($ds["perfomance"] as $m) {
		 
            // if there is a resource
            if (isset($m['iden']) && $m['iden'] != '') {
                $rdf = $graph->resource("this:".$m['iden'], array('prov:Activity', 'mexperf:ExecutionPerformance'));
                $rdf->set('prov:value', $m['desc']);         
                $rdf->set('mexperf:formula', $m['type']);
                $rdf->set('prov:wasInformedBy', $m['exec']);
			}
				$k++;
        }	
    }
	
/*	
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
	
/*
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
	

/*model

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
	*/
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
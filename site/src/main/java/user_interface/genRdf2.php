<?php

set_include_path(get_include_path() . PATH_SEPARATOR . '../lib/');
require_once "./lib/EasyRdf.php";

$ns = new EasyRdf_Namespace();
$ns->set("dct", "http://purl.org/dc/terms/");
$ns->set("dcat", "http://www.w3.org/ns/dcat#");
$ns->set("foaf", "http://xmlns.com/foaf/0.1/");
$ns->set("sd", "http://www.w3.org/ns/sparql-service-description#");
$ns->set("void", "http://rdfs.org/ns/void#");
$ns->set("owl", "http://www.w3.org/2002/07/owl#");
$ns->set("xsd", "http://www.w3.org/2001/XMLSchema#");
$ns->set("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
$ns->set("prov", "http://www.w3.org/ns/prov#");
$ns->set("dataid", "http://dataid.dbpedia.org/ns/core#");
$ns->set("odrl", "http://www.w3.org/ns/odrl/2#");
//$ns->set("dataid", "https://raw.githubusercontent.com/dbpedia/dataId/master/ontology/dataid.ttl#Dataset");
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
    $rdf = $graph->resource($ds['datasetURI'], array('dataid:Dataset','odrl:license', 'dcat:Dataset', 'void:Dataset', 'sd:Dataset', 'prov:Entity'));
    if (isset($ds['title']) && $ds['title'] != '')
        $rdf->set('dct:title', $ds['title']);
    if (isset($ds['label']) && $ds['label'] != '')
        $rdf->set('rdfs:label', $ds['label']);
    if (isset($ds['description']) && $ds['description'] != '')
        $rdf->set('dct:description', $ds['description']);
    if (isset($ds['issued']) && $ds['issued'] != '')
        $rdf->set('dct:issued', new EasyRdf_Literal_Date($ds['issued']));
    if (isset($ds['rights']) && $ds['rights'] != '')
        $rdf->set('dct:rights', $ds['rights']);
    if (isset($ds['rootResource']) && $ds['rootResource'] != '')
        $rdf->set('void:rootResource', $graph->resource($ds['rootResource']));
    if (isset($ds['exampleResource']) && $ds['exampleResource'] != '')
        $rdf->set('void:exampleResource', $graph->resource($ds['exampleResource']));
    if (isset($ds['language']) && $ds['language'] != '')
        $rdf->set('dct:language', $ds['language']);
    if (isset($ds['ontologyLocation']) && $ds['ontologyLocation'] != '')
        $rdf->set('void:vocabulary', $graph->resource($ds['ontologyLocation']));
    if (isset($ds['landingPage']) && $ds['landingPage'] != '')
        $rdf->set('dcat:landingPage', $graph->resource($ds['landingPage']));
    if (isset($ds['keyword']) && $ds['keyword'] != '')
        $rdf->set('dcat:keyword', $ds['keyword']);
    if (isset($ds['license'])) {
        $rdf->set('dataid:licenseName', $ds['license']['name']);
        $rdf->set('dct:license', $graph->resource($ds['license']['val']));
    }

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
    if (isset($ds["agent"])) {
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
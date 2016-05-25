<?php
    /**
     * Making a SPARQL SELECT query
     *
     * This example creates a new SPARQL client, pointing at the
     * dbpedia.org endpoint. It then makes a SELECT query that
     * returns all of the countries in DBpedia along with an
     * english label.
     *
     * Note how the namespace prefix declarations are automatically
     * added to the query.
     *
     * @package    EasyRdf
     * @copyright  Copyright (c) 2009-2013 Nicholas J Humfrey
     * @license    http://unlicense.org/
     */

    set_include_path(get_include_path() . PATH_SEPARATOR . '../lib/');
    require_once "EasyRdf.php";
    require_once "html_tag_helpers.php";

    $sparql = new EasyRdf_Sparql_Client(
        'http://127.0.0.1:3030/ds/query',
        'http://127.0.0.1:3030/ds/update'
    );
?>
<html>
<head>
  <title>EasyRdf Basic Sparql Example</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
</head>
<body>
<h1>EasyRdf Basic Sparql Example</h1>

<?php
    print "Count: ".$sparql->countTriples()."<br />\n";

    $result = $sparql->clear('all');
    print "Result: ".$result->getMessage()."<br />\n";


    $graph = new EasyRdf_Graph();
    $person = $graph->resource('http://example.com/joe', 'foaf:Person');
    $person->add('foaf:name', 'Joe');

    $result = $sparql->insert($graph, 'http://example.com/joe');

    $result = $sparql->insert('<s> <p> <o>', 'http://example.org/1');
    print "Result: ".$result->getMessage()."<br />\n";

    $result = $sparql->insert('<s> <p> <o>', 'http://example.org/2');
    print "Result: ".$result->getMessage()."<br />\n";

    $result = $sparql->insert('<s> <p> <o>', 'http://example.org/2');
    print "Result: ".$result->getMessage()."<br />\n";
    
//     $result = $sparql->load('http://www.dajobe.org/foaf.rdf');
//     print "Result: ".$result->getMessage()."<br />\n";

    print "Count: ".$sparql->countTriples()."<br />\n";

    print "<ul>\n";
    foreach ($sparql->listNamedGraphs(10) as $uri) {
      print "<li>$uri</li>\n";
    }
    print "</ul>\n";
?>

</body>
</html>

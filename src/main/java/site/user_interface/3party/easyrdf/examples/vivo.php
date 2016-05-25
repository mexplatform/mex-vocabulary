<?php
    set_include_path(get_include_path() . PATH_SEPARATOR . '../lib/');
    require_once "EasyRdf.php";

    ## Add namespaces
    EasyRdf_Namespace::set('vitro', 'http://vitro.mannlib.cornell.edu/ns/vitro/public#');
    EasyRdf_Namespace::set('vivo', 'http://vivoweb.org/ontology/core#');

    $uri = 'http://54.235.146.115:8080/vivo/individual/n5642';
    $graph = EasyRdf_Graph::newAndLoad($uri);
    $person = $graph->resource($uri);  
?>

<html>
<head><title>Vivo Reader</title></head>
<body>
<h1>Vivo Reader</h1>

<p>
  <b>First Name</b>: <?= $person->get('foaf:firstName') ?><br />
  <b>Last Name</b>: <?= $person->get('foaf:lastName') ?><br />
  <b>Title</b>: <?= $person->get('vivo:preferredTitle') ?><br />
</p>

<?php
  // Make second request to load information about the main image
  $image = $person->get('vitro:mainImage');
  if ($image) {
      $graph->load($image);
      $imageUrl = $image->get('vitro:downloadLocation');
      echo "<img src='$imageUrl' />\n";
  }
?>

<?php
  echo $graph->dump();
?>

</body>
</html>

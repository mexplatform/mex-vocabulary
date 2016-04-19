<?php
    /**
     * Example of reading Open Graph Protocol properties
     *
     * @package    EasyRdf
     * @copyright  Copyright (c) 2012-2013 Nicholas J Humfrey
     * @license    http://unlicense.org/
     */

    set_include_path(get_include_path() . PATH_SEPARATOR . '../lib/');
    require_once "EasyRdf.php";

    EasyRdf_Namespace::setDefault('dc11');
?>
<html>
<head>
  <title>Open Graph Protocol example</title>
</head>
<body>

<?php
  $doc = EasyRdf_Graph::newAndLoad('http://www.rottentomatoes.com/m/10011268-oceans/');
?>

<p>
  Title: <?= $doc->title ?><br />
  Creator: <?= $doc->creator ?><br />
</p>

</body>
</html>

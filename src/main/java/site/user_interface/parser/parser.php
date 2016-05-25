<?php

if (isset($_POST['address'])) {
    $cURL = curl_init($_POST['address']);
    curl_setopt($cURL, CURLOPT_RETURNTRANSFER, true);
    $page = curl_exec($cURL);

    libxml_use_internal_errors(false);

    $dom = new DOMDocument;
    $dom->loadHTML($page);
    $dom->loadHTML($page);


    $tables = $dom->getElementsByTagName('table');

    $aVal = array();

    echo(json_encode(parser($tables)));


    curl_close($cURL);
}

function parser($p) {

    $header = 0;
    $index = 0;
    $index2 = 0;
    $subsets = array();

    foreach ($p as $tr) {
        $trVal = $tr->getElementsByTagName('tr');

        foreach ($trVal as $td) {
            if ($header < 3) {
                $header++;
            } else {
                $tdVal = $td->getElementsByTagName('td');

                foreach ($tdVal as $a) {
                    $index++;
                    if ($index == 2) {
                        $subsets[$index2]['name'] = trim($a->textContent);
                    }
                    if ($index == 3) {
                        $subsets[$index2]['date'] = trim(date("m-d-Y", strtotime($a->textContent)));
                    }
                    if ($index == 4) {
                        $subsets[$index2]['size'] = trim($a->textContent);
                    }
                    if ($index == 5) {
                        $subsets[$index2]['description'] = trim($a->textContent);
                    }
                }
                

                $index = 0;
                $index2++;
            }
        }
    }
    return $subsets;
}
?>



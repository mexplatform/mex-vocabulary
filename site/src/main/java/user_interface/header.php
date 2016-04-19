<!DOCTYPE html>
<html ng-app="mexGen">
    <head>
        <meta charset="UTF-8">
        <title>MEX Generator</title>
    </head> 
    <head>
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
        <style>

            .desc2{
                float: right; 
                margin-right: 2px;
            }
            .input{
                margin-bottom: 5px; 
                width: 98%
            }
            .input-modal-md{
                margin-bottom: 5px; 
                width: 100%
            }
            .input-small2{
                margin-bottom: 5px; 
                width: 90%
            }

            #page-wrapper {
                position: inherit;
                margin: 0 0 0 280px;
                padding: 0 30px;
                min-height: 1300px;
                border-left: 1px solid #e7e7e7;
            }

            .navbar-fixed-bottom{
                /*position: inherit !important;*/
                /*margin: 0 0 0 0px !important;*/
                padding: 0 0px !important;
                min-height: 130px !important;
                border-left: 1px solid #e7e7e7 !important;

            }
            .navbar-static-side {
                z-index: 1;
                position: absolute;
                width: 300px;
                height: 2350px;
                margin-top: 35px;
            }
            .URIerror{
                color: red;
            }
            .popover{
                max-width: 340px;
            }
            .help-button {
                display: inline-block;
                height: 22px;
                width: 22px;
                line-height: 22px;
                text-align: center;
                padding: 0;
                background-color: #65bcda;
                color: #FFF;
                font-size: 12px;
                font-weight: bold;
                cursor: default;
                margin-left: 4px;
                border-radius: 100%;
                border-color: #FFF;
                border: 2px solid #FFF;
                -webkit-box-shadow: 0px 1px 0px 1px rgba(0, 0, 0, 0.2);
                box-shadow: 0px 1px 0px 1px rgba(0, 0, 0, 0.2);
            }
            .ui-dialog { z-index: 10800 !important ;
            }
            .navbar-fixed-top{
                height: 35px !important ;
                min-height:  10px !important ; 
            }
            .nav.navbar-nav{
                height: 35px !important ;
                min-height:  10px !important ; 
            }

            .panel-heading{
                height: 30px;
            }
            .panel-title{
                margin-top: -7px;
                color: #777 !important;
                font-size: 13px !important;  
            }
            ul {/* Remove margin for all <ul>s (and padding, because different browsers have different default styles) */
                margin-left: 3px;
                padding-left: 0;
            }

            ul ul {/* Add a margin for any <ul> inside another <ul> */
                margin-left: 13px;
            }
            li { list-style: none; } 

            .accordion-toggle{
                color: #777 !important;
                font-size: 13px !important;
                margin-top: 12px !important;
            }
            .panel .panel-default .ng-isolate-scope{
                max-height: 200px !important;
            }
            #list{
                max-height: 360px !important;
                overflow:auto !important; 
            }
            .navbar-fixed-bottom{
                width: 300px;
            }


        </style>
    </head>

    <body ng-controller="genController">
     <div class="bs-example">
        <form name="form"   id="personal-data"  class="form-horizontal"  ng-submit="processForm()" >
            <div id="wrapper">
                <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
                    <ul class="nav navbar-nav">
                        <span class="navbar-text" style="margin-top: 5px">
                            MEX Generator
                        </span>
                        <li><a  style="margin-top: -10px" target="_blank" href="{{ds.datasetURI}}">Dataset URI: {{ds.datasetURI}}</a></li>
                        <li> <span style="color: red">{{spanError}}</span>
                            <button style="width: 145px; margin-left: 1px" class="btn btn-sm btn-primary" type="submit">Generate</button> 
                            <input type="checkbox" ng-model="showValidation"> Show Validation Output
                            <div class="progress-bar progress-danger" style="height: 10px; width: {{progressBarWidth}}%"></div>
                        </li>
                    </ul>
                    </div>
                </nav>


                     <br/>








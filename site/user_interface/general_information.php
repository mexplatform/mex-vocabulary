<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            General Information

        </div>
    </div>
    <div class="panel-body"> 
        <div>

            <label>Dataset/Subset URI <span style="color: red">*</span></label> 
            <br>  
            <input url required placeholder="e.g. http://dbpedia.org/dataid.ttl" class="input" name="dataset" type="text" ng-model="ds.datasetURI">
            <br/> 
            <span class="label label-danger" ng-show="form.dataset.$error.url">{{messages.notValidURI}} <br></span>
            <!--<label>Title</label><span popover-placement="top" popover="Reference: <a href='http://www.w3.org/TR/vocab-dcat/#Property:catalog_title'> dct:title </a>" style="color: #428bca; margin-left: 10px"><span class="glyphicon glyphicon-question-sign"></span></span>    <br>                <input size="45" type="text" class="dataset" ng-model="ds.title">-->
            <label>Title <span style="color: red">*</span></label> 
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:catalog_title'> dct:title </a>    
            <br>                
            <input required placeholder="e.g. DBpedia" type="text" class="dataset input" ng-model="ds.title">
            <br/>
            <label>Label <span style="color: red">*</span></label> 
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_label'> rdfs:label </a>    
            <br>                
            <input required size="51" type="text" class="dataset input" ng-model="ds.label">
            <br/>
            <label>Homepage <span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:dataset_landingpage'> dcat:landingPage </a>   
            <br>                
            <input required placeholder="" class="input" name="landingPage" url size="51" type="text" ng-model="ds.landingPage">
            <br/>
            <span class="label label-danger" ng-show="form.landingPage.$error.url">{{messages.notValidURI}} <br></span>
            <label>Example Resource <span style="color: red">*</span> </label> 
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/void/#example-resource'> void:exampleResource </a>   
            <br>                
            <input  required class="input"  name="exampleResource" type="text" ng-model="ds.exampleResource">
            <br/>
            <span ng-show="form.exampleResource.$error.url">{{messages.notValidURI}} <br></span>
<!--                                <label>LODstats link</label>    <br>                <input url style="margin-bottom: 5px"  name="LODstats" size="45" type="text" ng-model="ds.LODStats">
            <br/>
            <span ng-show="form.LODstats.$error.url">{{messages.notValidURI}} <br></span>
            <label>Datahub link </label>    <br>                <input url style="margin-bottom: 5px" name="datahub" size="45" type="text" ng-model="ds.datahub">
            <br/> 
            <span ng-show="form.datahub.$error.url">{{messages.notValidURI}} <br></span>-->


            <label>Language  <span style="color: red">*</span>   </label>   
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:catalog_language'> dct:language </a>   
            <br>          
            <input required class="input" type="text" ng-model="ds.language">
            <br/>
            <label>Root Resource</label>  
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/void/#root-resource'> void:rootResource </a>   
            <br>             
            <input url name="rootResource" class="input" type="text" ng-model="ds.rootResource">
            <br/>
            <span ng-show="form.rootResource.$error.url">{{messages.notValidURI}} <br></span>
            <br/>
            <label>Ontology Location<span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href=''> dataid:ontologyLocation </a>   
            <br>             
            <input required url name="ontologyLocation" class="input" type="text" ng-model="ds.ontologyLocation">
            <br/>
            <span ng-show="form.ontologyLocation.$error.url">{{messages.notValidURI}} <br></span>
            <br/>


        </div>
    </div>
</div>
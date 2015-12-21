<div class="panel panel-default"> 
    <div class="panel-heading">
        <div class="panel-title">
        Description and rights
        </div>
    </div>
    <div class="panel-body">
        <div>
            <div class="row">
                <div class="col-md-6"> 
                    <label>Issued date <span style="color: red">*</span> </label> 
                    <a style="float: right; margin-right: 0px;" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:catalog_release_date'> dct:issued </a>   
                    <br> 
                    <input required class="input-small2" placeholder="mm-dd-yyyy" OnKeyUp="mascaraData(this);" type="text" ng-model="ds.issued">
                </div> 
                <div class="col-md-6">
                    <label>Version Info <span style="color: red">*</span> </label>  
                    <a style="float: right;" target="_blank" href=''> dataid:versionInfo </a>  
                    <br> 
                    <input required class="input-small2"  size="18" type="text" ng-model="ds.versionInfo">
                </div>
            </div>
            <label>Description <span style="color: red">*</span> </label> 
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:dataset_description'> dct:description </a>   
            <br>                
            <textarea required class="input" rows="6" ng-model="ds.description"></textarea>
            <br/>
            <label>License <span style="color: red">*</span> </label> 
            <a class="desc2" target="_blank" href='http://www.w3.org/community/odrl/work/cc/'> odrl:License </a>   
            <br>              
            <select class="input" ng-model="ds.license" ng-options="license.name for license in license"></select>
            <br/>
            <label>Rights</label>  
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:catalog_rights'> dct:rights </a>    
            <br>               
            <textarea class="input" ng-model="ds.rights"></textarea>
            <br/>
            <label>Keywords</label>  
            <a class="desc2" target="_blank" href='http://www.w3.org/TR/vocab-dcat/#Property:dataset_keyword'> dcat:keyword </a>     
            <br>            
            <input placeholder="Use commas to separate words" class="input" type="text" ng-model="ds.keyword">
            <br/>
            <br/>
        </div>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">Experiment</div>
    </div>
    <div class="panel-body"> 
        <div>
            <label>Identifier <span style="color: red">*</span></label> 
            <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_givenName'> dct:identifier</a>    
            <br>                
          <input required placeholder="" type="text" class="form-control" ng-model="ds.idenExp">
            <br/>
            <label>Tittle <span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_mbox'> dcterms:title</a>   
            <br>                
          <input required placeholder="" class="form-control" type="text" ng-model="ds.titleExp">
            <br/>
            <label>Date<span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_homepage'>dcterms:date</a>   
            <br>                
          <input required placeholder="" class="form-control"  type="text" ng-model="ds.dateExp">
            <br/>
            <label>Description<span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#homepage'>dcterms:description</a>   
            <br>                
          <input required placeholder="" class="form-control"   type="text" ng-model="ds.descriExp">
            <br/>

            <label>dataNormalizedDescription <span style="color: red">*</span> </label> 
             <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#description'> mexcore:dataNormalizedDescription </a>   
            <br>                
            <input required placeholder="" class="form-control" type="text" ng-model="ds.dateNormDes"></textarea>
            <br/>
            
             <label>outliersRemovedDescription <span style="color: red">*</span></label> 
             <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#Project'> mexcore:outliersRemovedDescription </a>    
            <br>                
            <input required placeholder="" type="text" class="form-control" ng-model="ds.outRemoDes">
            <br/>
            
             <label>noiseRemovedDescription<span style="color: red">*</span></label> 
             <a class="desc2" target="_blank" href='http://www.w3.org/TR/rdf-schema/#ch_seealso'> mexcore:noiseRemovedDescription  </a> 
             <br>
              <input required placeholder="" type="text" class="form-control" ng-model="ds.noiseRemoDes">
              <br>   
                           
            <label>attributeSelectionDescription<span style="color: red">*</span></label> 
            <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#location'> mexcore:attributeSelectionDescription</a>    
            <br>                
            <input required size="51" type="text" class="form-control" ng-model="ds.attSelecDes">
             <br>
        </div>
                <br />
        <br />
                <input type="reset" class="btn btn-default" value="Reset">
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">ApplicationContext</div>
    </div>
    <div class="panel-body"> 
        <div>
           <div class="form-group" >
           		 <label class="control-label col-xs-1">Context<span style="color: red">*</span></label>
            <div class="col-xs-9">
                <select class="form-control">
                    <option>AdaptativeWebSites</option>
                     <option>AffectiveComputing</option>
                      <option>LinkDiscovery</option>
                </select>
            </div>
            </div>
            <br/> 
            <label>Name<span style="color: red">*</span></label> 
            <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_givenName'> foaf:givenName </a>    
            <br>                
          <input required placeholder="" type="text" class="dataset input" ng-model="ds.givenName">
            <br/>
            <label>Creator Email <span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://xmlns.com/foaf/spec/#term_mbox'> foaf:mbox </a>   
            <br>                
          <input required placeholder="" class="input" type="text" ng-model="ds.mbox">
            <br/>
            <label>Project Homepage <span style="color: red">*</span> </label>  
            <a class="desc2" target="_blank" href='http://usefulinc.com/ns/doap#homepage'> doap:homepage </a>   
            <br>                
          <input required placeholder="" class="input" name="homePage" url size="51" type="text" ng-model="ds.homePage">
            <br/>
            <span class="label label-danger" ng-show="form.landingPage.$error.url">{{messages.notValidURI}} <br></span>
        </div>   
    </div>
</div>
<div ng-if="!ds.main">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="panel-title">
                Resource Identification
            </div>
        </div>
        <div class="panel-body"> 
            <div>

                <label>Type of this resource:<span style="color: red">*</span></label> 
                <br>  

                <input checked style="margin-left: 0px" type="radio" ng-model="ds.type" value="void:subset"> void:subset 
                <input style="margin-left: 6px" type="radio" ng-model="ds.type" value="dataid:version"> mex:version
                <input style="margin-left: 6px" type="radio" ng-model="ds.type" value="dataid:latestVersion"> mex:latestVersion <br/>

            </div> 
        </div>
    </div>
</div>


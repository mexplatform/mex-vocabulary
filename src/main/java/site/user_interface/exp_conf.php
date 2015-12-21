               <div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title">New Experimente Configuration<br /></div>
        <div class="pull-right">
           <!--<span class="help-button" popover-placement="bottom" popover="You can add here new contacts for your subset." popover-title="New Contact" >?</span>-->
        </div>
    </div>
    <div class="panel-body">
        <table class="table"><tr>
                <th>Identifier</th>
                <th></th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="expeconf in ds.expeconf"> 
                   <td>
                    <span> {{expeconf.iden}} </span> <br> 
                </td>
                   <td>
                    <span></span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editExpConf(expeconf)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeExpConf(expeconf)"></a>
                </td>
            </tr>
            <tr ng-if="ds.expeconf.length == 0">
                <td colspan="4"><i style="color: grey">(Organization Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openExpConf('md')">Add Method</a>
        </div> 
    </div>
</div>                
<div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title"> Features</div>
        <div class="pull-right">
           <!--<span class="help-button" popover-placement="bottom" popover="You can add here new contacts for your subset." popover-title="New Contact" >?</span>-->
        </div>
    </div>
    <div class="panel-body">
        <table class="table"><tr> New Feature<br /></tr>
        <tr>
                <th>iden</th>
                <th>value</th>
                <th>Experiment Configuration</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="feature in ds.feature"> 
                <td>
                    <span> {{feature.iden}} </span> <br> 
                </td>
                 <td>
                    <span> {{feature.value}} </span> <br> 
                </td>
                 <td>
                    <span> {{feature.expe}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editFeatureExp(feature)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeFeatureExp(feature)"></a>
                </td>
            </tr>
            <tr ng-if="ds.feature.length == 0">
                <td colspan="4"><i style="color: grey">(Feature list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openFeatureExp('md')">Add Feature</a>
        </div> 
    </div>
    </div>
<div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title">
        Execution Information:
        </div>
        <div class="pull-right">
           <!--<span class="help-button" popover-placement="bottom" popover="You can add here new contacts for your subset." popover-title="New Contact" >?</span>-->
        </div>
    </div>
    <div class="panel-body">
        <table class="table"><tr> New Execution</tr>
        <tr>
                <th>Identifier</th>
                <th>Exp. Config</th>
                <th>Algorithm</th>
                <th>Started</th>
                <th>End</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="active in ds.active"> 
                <td>
                    <span> {{active.prop}} </span>
                </td>
                <td>
                    <span> {{active.expe}} </span> <br> 
                </td>
                 <td>
                    <span> {{active.algo}} </span> <br> 
                </td>
                 <td>
                    <span> {{active.start}} </span> <br> 
                </td>
                 <td>
                    <span> {{active.end}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editActiveExec(active)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeActiveExec(active)"></a>
                </td>
            </tr>
            <tr ng-if="ds.active.length == 0">
                <td colspan="4"><i style="color: grey">(Execution Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openActiveExec('md')">Add Execution</a>
        </div> 
    </div>
    <br />
</div>
<div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title"> Performance Information</div>
        <div class="pull-right">
           <!--<span class="help-button" popover-placement="bottom" popover="You can add here new contacts for your subset." popover-title="New Contact" >?</span>-->
        </div>
    </div>
    <div class="panel-body">
        <table class="table"><tr> New Perfomance<br /></tr>
        <tr>
                <th>identifier</th>
                <th>type</th>
                <th>value</th>
                <th>Execution</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="perfomance in ds.perfomance"> 
                <td>
                    <span> {{perfomance.iden}} </span> <br> 
                </td>
                <td>
                    <span> {{perfomance.type}} </span> <br> 
                </td>
                 <td>
                    <span> {{perfomance.desc}} </span> <br> 
                </td>
                 <td>
                    <span> {{perfomance.exec}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editPerfomance(perfomance)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removePerfomance(perfomance)"></a>
                </td>
            </tr>
            <tr ng-if="ds.feature.length == 0">
                <td colspan="4"><i style="color: grey">(Feature list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openPerfomance('md')">Add Perfomance</a>
        </div> 
    </div>
    </div>
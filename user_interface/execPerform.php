<div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title">
        Execution Performance Information:
      </div>
    </div>
    <div class="panel-body">
        <table class="table"><tr>New Execution Performance</tr>
        <tr>
                <th>prop</th>
                <th>iden</th>
                <th>Started</th>
                <th>End</th>
                <th>Collection Parameter</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="active in ds.active"> 
                <td>
                    <span> {{active.prop}} </span>
                </td>
                <td>
                    <span> {{active.iden}} </span> <br> 
                </td>
                 <td>
                    <span> {{active.start}} </span> <br> 
                </td>
                 <td>
                    <span> {{active.end}} </span> <br> 
                </td>
                           <td>
                    <span> {{active.execparameter}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editActiveExec(active)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeActiveExec(active)"></a>
                </td>
            </tr>
            <tr ng-if="ds.active.length == 0">
                <td colspan="4"><i style="color: grey">(Execution Performance Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openActiveExec('md')">Add  Execution Perfomance</a>
        </div> 
    </div>
    <br />
       <div class="panel-body">
        <table class="table"><tr>New Measure</tr>
        <tr>
                <th>prop</th>
                <th>iden</th>
                <th>execution</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="model in ds.model"> 
                <td>
                    <span> {{model.iden}} </span>
                </td>
                <td>
                    <span> {{model.desc}} </span> <br> 
                </td>
                 <td>
                    <span> {{model.exec}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editModelExec(model)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeModelExec(model)"></a>
                </td>
            </tr>
            <tr ng-if="ds.model.length == 0">
                <td colspan="4"><i style="color: grey">(Measure Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openModelExec('md')">Add Measure</a>
        </div> 
        </div>
        </br>
     <div class="panel-body">
        <table class="table"><tr>New User Defined</tr>
        <tr>
                <th>prop</th>
                <th>iden</th>
                <th>collection</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="parameter in ds.parameter"> 
                <td>
                    <span> {{parameter.prop}} </span>
                </td>
                <td>
                    <span> {{parameter.iden}} </span> <br> 
                </td>
                 <td>
                    <span> {{parameter.exec}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editParameterExec(parameter)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeParameterExec(parameter)"></a>
                </td>
            </tr>
            <tr ng-if="ds.parameter.length == 0">
                <td colspan="4"><i style="color: grey">(User Defined Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openParameterExec('md')">Add User Defined</a>
        </div> 
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title">
        Algortihm Information:
        </div>
    </div>
    <div class="panel-body">
        <table class="table"><tr>New Algorithm</tr>
        <tr>
                <th>iden</th>
                <th>name</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="algo in ds.algo"> 
                <td>
                    <span> {{algo.prop}} </span>
                </td>
                <td>
                    <span> {{algo.iden}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editAlgo(algo)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeAlgo(algo)"></a>
                </td>
            </tr>
            <tr ng-if="ds.algo.length == 0">
                <td colspan="4"><i style="color: grey">(Algorithm Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openAlgo('md')">Add Algorithm</a>
        </div> 
    </div>
</div>            
<div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title">
        Algortihm Information:
        </div>
    </div>
     <div class="panel-body">
        <table class="table"><tr>New Algorithm Parameters</tr>
        <tr>
                <th>identifier</th>
                <th>value</th>
                <th>algo</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="parameter in ds.parameter"> 
                <td>
                    <span> {{parameter.prop}} </span>
                </td>
                <td>
                    <span> {{parameter.value}} </span> <br> 
                </td>
                 <td>
                    <span> {{parameter.algo}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editParameterAlgo(parameter)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeParameterAlgo(parameter)"></a>
                </td>
            </tr>
            <tr ng-if="ds.parameter.length == 0">
                <td colspan="4"><i style="color: grey">( Algorithm Parameters Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openParameterAlgo('md')">Add Parameters</a>
        </div> 
    </div>
</div>
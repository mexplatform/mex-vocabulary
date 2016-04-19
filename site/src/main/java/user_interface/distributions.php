<div class="panel panel-default"> 
    <div class="panel-heading">
        <div class="panel-title">
            Distribution:
        </div>
    </div>
    <div class="panel-body">
        <div>
            <table  class="table" style="width: 99%;"><tr>
                    <th>Title</th>
                    <th>Property</th>
                    <th style="width: 15px">Edit</th>
                    <th style="width: 15px">Remove</th>
                </tr>
                <tr ng-repeat="distribution in ds.distribution">
                    <td> 
                        <span> {{distribution.title}} </span> <br>
                    </td>
                    <td>
                        <span> {{distribution.prop}} </span> <br>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-edit" href="" ng-click="editDist(distribution)"></a>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-remove" href="" ng-click="removeDistribution(distribution)"></a>
                    </td>
                </tr>
                <tr ng-if="ds.distribution.length == 0">
                    <td colspan="4"><i style="color: grey">(Distribution list is empty)</i></td>
                </tr>
            </table>
            <div class="pull-right">
                <a class="label label-xlg label-default" href="" ng-click="">Fetch Stats</a>
                <a class="label label-xlg label-default" href="" ng-click="openDist('lg')">Add Distribution</a>
                <a class="label label-xlg label-default" href="" ng-click="openDistWeb('md')">Add Distribution from Web</a>
                <a class="label label-danger ng-binding"  href="" ng-click="removeAllDist()">Remove all</a>
            </div> 
        </div> 
    </div>
</div>
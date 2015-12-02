<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            Linkset:
        </div>
    </div>
    <div class="panel-body">
        <div>
            <table  class="table" style="width: 99%;"><tr>
                    <th>Link Predicate</th>
                    <th>Property</th>
                    <th style="width: 15px">Edit</th>
                    <th style="width: 15px">Remove</th>
                </tr> 
                <tr ng-repeat="linkset in ds.linkset">
                    <td>
                        <span> {{linkset.linkPredicate}} </span> <br>
                    </td>
                    <td>
                        <span> {{linkset.prop}} </span> <br>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-edit" href="" ng-click="editLinkset(linkset)"></a>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-remove" href="" ng-click="removeLinkset(linkset)"></a>
                    </td>
                </tr>
                <tr ng-if="ds.linkset.length == 0">
                    <td colspan="4"><i style="color: grey">(Linkset list is empty)</i></td>
                </tr>
            </table>
            <div class="pull-right">
                <a class="label label-xlg label-default" href="" ng-click="openLinkset('md')">Add Linkset</a>
            </div> 
        </div> 
    </div>
</div>
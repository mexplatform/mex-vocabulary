<div class="panel panel-default">
    <div class="panel-heading">   
        <div class="panel-title">
        Contact Information:
        </div>
        <div class="pull-right">
           <!--<span class="help-button" popover-placement="bottom" popover="You can add here new contacts for your subset." popover-title="New Contact" >?</span>-->
        </div>
    </div>
    <div class="panel-body">
        <table class="table"><tr>
                <th>Contact Label</th>
                <th>Contact Property</th>
                <th style="width: 15px">Edit</th> 
                <th style="width: 15px">Remove</th>
            </tr>
            <tr ng-repeat="agent in ds.agent"> 
                <td>
                    <span> {{agent.label}} </span>
                </td>
                <td>
                    <span> {{agent.prop}} </span> <br> 
                </td>
                <td>
                    <a class="glyphicon glyphicon-edit" href="" ng-click="editAgent(agent)"></a>
                </td>
                <td>
                    <a class="glyphicon glyphicon-remove" href="" ng-click="removeAgent(agent)"></a>
                </td>
            </tr>
            <tr ng-if="ds.agent.length == 0">
                <td colspan="4"><i style="color: grey">(Contact Information list is empty)</i></td>
            </tr>
        </table>
        <div class="pull-right">
            <a class="label label-xlg label-default" href="" ng-click="openAgent('md')">Add Contact</a>
        </div> 
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            Provenance:
        </div>
    </div>
    <div class="panel-body">
        <div>
            <label>WasDerivedFrom</label>
            <div class="pull-right">
                <span  popover-placement="left" class="help-button"  popover=" A physical, digital, conceptual, or other kind of thing with some fixed aspects. See http://www.w3.org/TR/prov-o/#Entity" popover-title="WasDerivedFrom" >?</span>
            </div> 
            
            <br/>
<!--            <label style="margin-top: 7px">Resource</label>  
            <br>              
            <input class="input" type="text" ng-model="ds.wasDerivedFromResource">-->
            <label style="margin-top: 7px">Title</label> 
            <a style="margin-top: 7px" class="desc2" target="_blank" href=''> dct:title </a>   
            <br>              
            <input class="input" type="text" ng-model="ds.wasDerivedFromTitle">

            <table style="margin-top: 20px" class="table"><tr>
                    <th>Contact Label</th>
                    <th>Contact Property</th>
                    <th style="width: 15px">Edit</th>
                    <th style="width: 15px">Remove</th>
                </tr>
                <tr ng-repeat="wasDerivedFromAgent in ds.wasDerivedFromAgent">
                    <td>
                        <span> {{wasDerivedFromAgent.label}} </span> <br> 
                    </td>
                    <td>
                        <span> {{wasDerivedFromAgent.prop}} </span> <br> 
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-edit" href="" ng-click="editWasDerivedFromAgent(wasDerivedFromAgent)"></a>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-remove" href="" ng-click="removeWasDerivedFromAgent(wasDerivedFromAgent)"></a>
                    </td>
                </tr>
                <tr ng-if="ds.wasDerivedFromAgent.length == 0">
                    <td colspan="4"><i style="color: grey">(Contact Information list is empty)</i></td>
                </tr>
            </table>
            <div style="margin-top: -15px" class="pull-right">
                <a class="label label-xlg label-default" href="" ng-click="openWasDerivedFromAgent('md')">Add Contact</a>
            </div> 
        </div>


        <div style="margin-top: 40px">
            <label>WasGeneratedBy</label>
            <div class="pull-right">
                <span popover-placement="left"  class="help-button"  popover="Something that occurs over a period of time and acts upon or with entities; it may include consuming, processing, transforming, modifying, relocating, using, or generating entities. See http://www.w3.org/TR/prov-o/#Activity" popover-title="WasGeneratedBy" >?</span>
            </div>  
            <br>              
<!--            <label style="margin-top: 7px">Resource</label>  
            <input class="input" type="text" ng-model="ds.wasGeneratedByResource">
            <br>-->
            <label>Title</label> 
            <a class="desc2" target="_blank" href=''> dct:title </a>   

            <input class="input" type="text" ng-model="ds.wasGeneratedByTitle">

            <table style="margin-top: 20px" class="table"><tr>
                    <th>Contact Label</th>
                    <th>Contact Property</th>
                    <th style="width: 15px">Edit</th>
                    <th style="width: 15px">Remove</th>
                </tr>
                <tr ng-repeat="wasGeneratedByAgent in ds.wasGeneratedByAgent">
                    <td>
                        <span> {{wasGeneratedByAgent.label}} </span> <br> 
                    </td>
                    <td>
                        <span> {{wasGeneratedByAgent.prop}} </span> <br> 
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-edit" href="" ng-click="editWasGeneratedByAgent(wasGeneratedByAgent)"></a>
                    </td>
                    <td>
                        <a class="glyphicon glyphicon-remove" href="" ng-click="removeWasGeneratedByAgent(wasGeneratedByAgent)"></a>
                    </td>
                </tr>
                <tr ng-if="ds.wasGeneratedByAgent.length == 0">
                    <td colspan="4"><i style="color: grey">(Contact Information list is empty)</i></td>
                </tr>
            </table>
            <div style="margin-top: -15px" class="pull-right">
                <a class="label label-xlg label-default" href="" ng-click="openWasGeneratedByAgent('md')">Add Contact</a>
            </div> 
        </div>
    </div>
</div>
<div ng-init ='lstActivitiesNew = ${lstActivitiesNew}; lstActivitiesOld = ${lstActivitiesOld}; lstNotification = ${lstNotification};'></div>

<div id="tasks-tab" class="tab-pane active">
    <h4 class="smaller lighter green" ng-show="lstActivitiesNew.length > 0">
        <i class="icon-list"></i>
        Actividades por hacer el d&iacute;a de hoy
    </h4>

    <ul id="tasksToday" class="item-list">
        <li class="item-orange clearfix" ng-repeat="a in lstActivitiesNew">
            <label class="inline">
                <i class="glyphicon glyphicon-paperclip green"></i> &nbsp;&nbsp;
                <span class="lbl">Caso {{a.caseId}} (<strong>{{a.mpId}}</strong>), del <strong>{{a.start}}</strong> al <strong>{{a.end}}.</strong><br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Imputado: {{a.personName}}, {{a.activityName}} / {{a.goalName}}</span>
            </label>
        </li>
    </ul>

    <div class="space-14" ></div>
    <h4 class="smaller lighter color-danger" ng-show="lstActivitiesOld.length > 0">
        <i class="icon-list"></i>
        Actividades pendientes de d&iacute;as pasados
    </h4>

    <ul id="tasksOld" class="item-list">
        <li class="item-purple clearfix" ng-repeat="a in lstActivitiesOld">
            <label class="inline">
                <i class="glyphicon glyphicon-fire color-warning"></i> &nbsp;&nbsp;
                <span class="lbl">Caso {{a.caseId}} (<strong>{{a.mpId}}</strong>), del <strong>{{a.start}}</strong> al <strong>{{a.end}}.</strong><br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Imputado: {{a.personName}}, {{a.activityName}} / {{a.goalName}}</span>
            </label>
        </li>
    </ul>

</div>


<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css" />
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>

<div class="row element-center" ng-controller="scheduleController">
    <input type="hidden" ng-update-hidden ng-model="schString" name='sch'>
    <input type="hidden" ng-update-hidden ng-init='listSchedule = ${(listSchedule == null) ? "undefined" : listSchedule};'>
    <div class="col-xs-8 col-xs-offset-2">
        <div class="row">
    <div class="col-xs-4 element-center">
       Día<br/>
        <select class="form-control element-center" ng-model="s.dayWeek"
                ng-options="e.name for e in lstDayWeek"
                ng-change="s.dayWeekId = s.dayWeek.id"
                ng-init='lstDayWeek = ${lstDayWeek};'></select>
    </div>
    <div class="col-xs-3 element-center">
                    Inicio<br/>
        <div class="input-group bootstrap-timepicker">
            <input id="timepickerStart{{content}}" type="text" class="form-control tp" ng-model="s.start"/>
            <span class="input-group-addon">
				<i class="icon-time bigger-60"></i>
			</span>
        </div>
    </div>
    <div class="col-xs-3 element-center">
        Fin <br/>
        <div class="input-group bootstrap-timepicker">
            <input id="timepickerEnd{{content}}" type="text" class="form-control tp" ng-model="s.end"/>
            <span class="input-group-addon">
				<i class="icon-time  bigger-40"></i>
			</span>
        </div>
    </div>
    <div class="col-xs-2">
        Acciones<br/><div class="space-5"></div>
        <i class="icon-plus-sign orange" style="cursor:pointer;" ng-click="addSchedule()"></i>
    </div>
    </div>
        <div class="row" >
        <div class="hr hr-6"></div>
        </div>
        <div ng-show="msgError" class="alert alert-danger element-center error-font">
            {{msgError}}
        </div>
        <div class="col-xs-12" ng-show="listSchedule.length==0">
            <br/>
            <br/>
            <br/>
        </div>
        <div class="col-xs-9 col-xs-offset-3" ng-show ="listSchedule.length > 0">
         <div class="row center">
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Día</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Inicio</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-2">
                 <h5 class="smaller lighter blue">Fin</h5>
                 <div class="hr hr-2"></div>
             </div>
             <div class="col-xs-3">
                 <h5 class="smaller lighter blue">Acciones</h5>
                 <div class="hr hr-2"></div>
             </div>
         </div>
            <div class="row center" ng-repeat ="sch in listSchedule">
                <div class="col-xs-2">
                    {{sch.day.name}}
                </div>
                <div class="col-xs-2">
                     {{sch.start}}
                </div>
                <div class="col-xs-2">
                    {{sch.end}}
                </div>
                <div class="col-xs-3">
                    <i class="icon-trash red" style="cursor:pointer;" ng-click="deleteSchedule($index)"></i>
                </div>
            </div>
         </div>
    </div>
    <br/>
</div>
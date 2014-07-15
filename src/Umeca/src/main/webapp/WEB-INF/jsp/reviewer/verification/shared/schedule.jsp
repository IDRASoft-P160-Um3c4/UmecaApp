<div id="divScheduleComponent">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css" />
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>

    <div class="row element-center" ng-controller="innerScheduleController" id="divElementVerifSchedule">
        <div class="col-xs-10 col-xs-offset-1">
            <div class="row">
                <div class="col-xs-4 element-center">
                    Día(s)<br/>
                    <input class="form-control" type="text" value=""
                           ng-model="s.day" ng-init='s.day= ""'>
                </div>
                <div class="col-xs-3 element-center">
                    Inicio<br/>
                    <div class="input-group bootstrap-timepicker">
                        <input  type="text" class="form-control tp" ng-model="s.start" readonly="readonly"/>
            <span class="input-group-addon">
				<i class="icon-time bigger-60"></i>
			</span>
                    </div>
                </div>
                <div class="col-xs-3 element-center">
                    Fin <br/>
                    <div class="input-group bootstrap-timepicker">
                        <input  type="text" class="form-control tp" ng-model="s.end" readonly="readonly"/>
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
            <div class="row">

                <div class="hr hr-6"></div>
            </div>
            <div ng-show="msgError" class="alert-danger element-center">
                {{msgError}}
            </div>

        </div>
        <br/>
    </div>
</div>
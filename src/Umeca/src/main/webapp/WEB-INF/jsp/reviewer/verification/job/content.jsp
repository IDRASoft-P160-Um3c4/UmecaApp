<input type="hidden" ng-init="j = listJob[$index]">
<input type="hidden" ng-update-hidden ng-model="j.id" name="id" id="id">

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('jobs.company',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="jobs.company" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('jobs.company',j.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('jobs.company',j.id)"></i>
        Empresa:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y m�ximo 150 caracteres"
               data-val-length-max="150" data-val-length-min="3"
               data-val-required="La empresa es un campo requerido"
               type="text" ng-model="j.company" id="company" name="jobs.company">
        <span class="field-validation-valid" data-valmsg-for="company" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('jobs.post',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="jobs.post" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('jobs.post',j.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('jobs.post',j.id)"></i>
        Puesto:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y m�ximo 50 caracteres"
               data-val-length-max="50" data-val-length-min="3" data-val-required="El puesto es un campo requerido"
               type="text" value="{{j.post}}" name="jobs.post" id="post">
        <span class="field-validation-valid" data-valmsg-for="post" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('jobs.phone',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="jobs.phone" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('jobs.phone',j.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('jobs.phone',j.id)"></i>
        Tel�fono:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 8 y m�ximo 20 caracteres"
               data-val-length-max="20" data-val-length-min="8"
               data-val-required="El tel�fono es un campo requerido"
               type="text" value="{{j.phone}}" name="jobs.phone" id="phone">
        <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('jobs.nameHead',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="jobs.nameHead" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('jobs.nameHead',j.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('jobs.nameHead',j.id)"></i>
        Nombre del patr�n:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m�ximo 150 caracteres"
               data-val-length-max="150" data-val-length-min="6"
               data-val-required="El nombre del patr�n es un campo requerido"
               type="text" value="{{j.nameHead}}" name="jobs.nameHead" id="nameHead">
        <span class="field-validation-valid" data-valmsg-for="nameHead" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifEqual('jobs.address',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification"
           code="jobs.address" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
           ng-click="doConfirmVerifNotKnow('jobs.address',j.id);"></i>
        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('jobs.address',j.id)"></i>
        Direcci�n:
    </div>
    <div class="col-xs-10">
        <textarea class="form-control" name="jobs.address" id="address"
                  data-val="true" data-val-required="La direcci�n es un campo requerido"
                  ng-model="j.address"
                  data-val-length="Debe tener al menos 6 y m�ximo 500 caracteres"
                  data-val-length-max="500" data-val-length-min="6"></textarea>
        <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div>
    <div class="row">
        <div class="col-xs-2 element-left">
            <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifEqual('jobs.registerType.id',j.id);"></i>
            <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3" ng-show="verification"
               code="jobs.registerType.id" id-element="{{j.id}}"></i>
            <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
               ng-click="doConfirmVerifNotKnow('jobs.registerType.id',j.id);"></i>
            <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('jobs.registerType.id',j.id)"></i>
            Tipo de empleo:
        </div>
        <div class="col-xs-10">
            <input type="hidden" ng-update-hidden ng-model="j.registerTypeId" name="jobs.registerType.id"
                   id="registerTypeId">
            <select class="form-control element-center" ng-model="j.registerType"
                    ng-options="e.name for e in lstRegisterType" ng-init='lstRegisterType = ${lstRegisterType};'
                    ng-change="j.registerTypeId = j.registerType.id;"></select>
        </div>

    </div>
    <br/>

    <div class="row" ng-show="j.registerTypeId==3">

        <div class="widget-box">
            <div class="widget-header">
                <div class="row">

                    <h4 ng-show="verification ==true">Trabajo {{j.registerType.name}}</h4>
                    <h4 ng-show="verification == false">Especificaci�n del trabajo</h4>

                </div>
            </div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="row widget-main">
                            <div class="col-xs-3">
                                Fecha Inicio:
                            </div>
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input class="form-control date-picker" id="jdpStart" type="text"
                                           readonly="readonly"
                                           data-date-format="yyyy/mm/dd" value="{{j.startPrev}}"
                                           data-val="true" data-val-required="La fecha de inicio es un campo requerido"
                                           name="jobs.startPrev"/> <span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                                </div>
                            </div>
                            <div class="col-xs-3">
                                Fecha Fin:
                            </div>
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input class="form-control date-picker" id="jdbEnd" type="text"
                                           data-date-format="yyyy/mm/dd" value="{{j.end}}" readonly="readonly"
                                           data-val="true" data-val-required="La fecha de fin es un campo requerido"
                                           name="jobs.end"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-3">Motivo de cambio:</div>
                            <div class="col-xs-9">
                                <textarea id="form-field-11" class="form-control"
                                          name="jobs.reasonChange"
                                          data-val="true"
                                          ng-model="j.reasonChange"
                                          data-val-required="El motivo de cambio es un campo requerido"></textarea>
                                <span class="field-validation-valid" data-valmsg-for="reasonChange"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-show="j.registerTypeId == 1 || j.registerTypeId == 2">
        <div class="widget-box">
            <div class="widget-header">
                <div class="row">
                    <h4 ng-show="verification ==true">Trabajo {{j.registerType.name}}</h4>
                    <h4 ng-show="verification == false">Especificaci�n del trabajo</h4>

                </div>
            </div>

            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <div class="row">
                            <div class="col-xs-3">
                                Fecha Inicio:
                            </div>
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input class="form-control date-picker" id="jdpStartCurrent" type="text"
                                           data-date-format="yyyy/mm/dd" value="{{j.start}}" readonly="readonly"
                                           data-val="true" data-val-required="La fecha de inicio es un campo requerido"
                                           name="jobs.start"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                                </div>
                                        <span class="field-validation-valid" data-valmsg-for="start"
                                              data-valmsg-replace="true"></span>
                            </div>
                            <div class="col-xs-3">
                                Salario semanal:
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener m�ximo 7 caracteres" name="jobs.salaryWeek"
                                       data-val-length-max="7" data-val-length-min="1"
                                       data-val-required="El salario semanal es un campo requerido"
                                       data-val-regex-pattern="([0-9]+(.[0-9])?)"
                                       data-val-regex="El salario s�lo puede contener n�meros y un punto"
                                       type="text" value="{{j.salaryWeek}}">

                                    <span class="field-validation-valid" data-valmsg-for="salaryWeek"
                                          data-valmsg-replace="true"></span>

                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-12" ng-init='schList = pastToJson(listJob[$index].schedule);'
                                 ng-show="verification || selectSource">
                                <div class="widget-header header-color-blue">
                                    <h6> &nbsp;
                                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification"
                                           ng-click="doConfirmVerifEqual('jobs.schedule', j.id)"></i>
                                        <i class="icon-remove-circle red  icon-only bigger-120" verif-schedule
                                           level-child="5"
                                           ng-show="verification"  id-code ="jobs.schedule"  id-element="{{j.id}}"></i>
                                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"
                                           ng-click="doConfirmVerifNotKnow('jobs.schedule', j.id)"></i>
                                        <i class="purple icon-list icon-only bigger-120"   ng-show="selectSource" ng-click="showChoices('jobs.schedule',j.id)"></i>
                                        <i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h6>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main no-padding">
                                        <table class="table table-striped table-bordered table-hover">
                                            <thead class="thin-border-bottom">
                                            <tr>
                                                <th class="element-center">
                                                    D�a(s)
                                                </th>
                                                <th class="element-center">
                                                    Hora de inicio
                                                </th>
                                                <th class="element-center">
                                                    Hora de fin
                                                </th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <tr ng-repeat="sch in schList track by $index">
                                                <td class="element-center">
                                                    {{sch.day}}
                                                </td>
                                                <td class="element-center">
                                                    {{sch.start}}
                                                </td>
                                                <td class="element-center">
                                                    {{sch.end}}
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hr hr-8"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
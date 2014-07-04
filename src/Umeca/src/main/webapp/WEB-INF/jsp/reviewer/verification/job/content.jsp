<input type="hidden" ng-init="j = listJob[$index]">

<div class="row">
    <input type="hidden" ng-update-hidden ng-model="j.id" name="id" id="id">

    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('jobs.company',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="jobs.company" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('jobs.company',j.id);"></i>
        Empresa:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y máximo 150 caracteres"
               data-val-length-max="150" data-val-length-min="3"  value="{{j.company}}"
               data-val-required="La empresa es un campo requerido"
               type="text" value="" value="{{j.company}}" id="company" name="company">
    </div>
    <div class="row">
        <div class="col-xs-offset-2 element-rigth">
            <span class="field-validation-valid" data-valmsg-for="company" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('jobs.post',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="jobs.post" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('jobs.post',j.id);"></i>
        Puesto:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y máximo 50 caracteres"
               data-val-length-max="50" data-val-length-min="3" data-val-required="El puesto es un campo requerido"
               type="text" value="{{j.post}}" name="post" id="post">
    </div>
    <div class="col-xs-9 col-xs-offset-3">
        <span class="field-validation-valid" data-valmsg-for="post" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('jobs.phone',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="jobs.phone" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('jobs.phone',j.id);"></i>
        Teléfono:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 8 y máximo 20 caracteres"
               data-val-length-max="20" data-val-length-min="8"
               data-val-required="El teléfono es un campo requerido"
               type="text" value="{{j.phone}}" name="phone" id="phone">
    </div>
    <div class="col-xs-9 col-xs-offset-3">
        <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('jobs.nameHead',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="jobs.nameHead" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('jobs.nameHead',j.id);"></i>
        Nombre del patrón:
    </div>
    <div class="col-xs-10">
        <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y máximo 150 caracteres"
               data-val-length-max="150" data-val-length-min="6"
               data-val-required="El nombre del patrón es un campo requerido"
               type="text"  value="{{j.nameHead}}" name="nameHead" id="nameHead">
    </div>
    <div class="col-xs-10 col-xs-offset-1">
        <span class="field-validation-valid" data-valmsg-for="nameHead" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-2 element-left">
        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('jobs.address',j.id);"></i>
        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="2" ng-show="verification" code="jobs.address" id-element="{{j.id}}"></i>
        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('jobs.address',j.id);"></i>
        Dirección:
    </div>
    <div class="col-xs-10">
        <textarea class="form-control" name="address" id="address"
                  data-val="true" data-val-required="La dirección es un campo requerido"
                  data-val-length="Debe tener al menos 6 y máximo 500 caracteres"
                  data-val-length-max="500" data-val-length-min="6">{{j.address}}</textarea>
        <br/>
        <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

<div>
    <div class="row" ng-show="verification==false">
        <div class="col-xs-2 element-left">
            Tipo de empleo:
        </div>
        <div class="col-xs-10">
            <input type="hidden" ng-update-hidden ng-model="j.registerTypeId" name="registerType.id"
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
                    <div class="col-xs-1">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('jobs.registerType',j.id);"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="6" ng-show="verification" code="jobs.registerType" id-element="{{j.id}}"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('jobs.registerType',j.id);"></i>
                    </div>
                    <div class="col-xs-11">
                        <h4 ng-show="verification ==true">Trabajo {{j.registerType.name}}</h4>
                        <h4 ng-show="verification == false">Especificación del trabajo</h4>
                    </div>
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
                                           data-date-format="yyyy/mm/dd" value="{{j.startPrev}}"
                                           data-val="true" data-val-required="La fecha de inicio es un campo requerido"
                                           name="startPrev"/> <span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                                </div>
                                <div class="row">
                                    <div class="col-xs-9 col-xs-offset-3">
                                        <span class="field-validation-valid" data-valmsg-for="startPrev"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-3">
                                Fecha Fin:
                            </div>
                            <div class="col-xs-3">
                                <div class="input-group">
                                    <input class="form-control date-picker" id="jdbEnd" type="text"
                                           data-date-format="yyyy/mm/dd" value="{{j.end}}"
                                           data-val="true" data-val-required="La fecha de fin es un campo requerido"
                                           name="end"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                                </div>

                                <div class="row">
                                    <div class="col-xs-9 col-xs-offset-3">
                                        <span class="field-validation-valid" data-valmsg-for="end"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-3">Motivo de cambio:</div>
                            <div class="col-xs-9">
                                <textarea id="form-field-11" class="form-control"
                                          name="reasonChange"
                                          data-val="true"
                                          data-val-required="El motivo de cambio es un campo requerido">{{j.reasonChange}}</textarea>
                            </div>
                        </div>
                        <div class="row">
                            <span class="field-validation-valid" data-valmsg-for="reasonChange"
                                  data-valmsg-replace="true"></span>
                            <br/>

                            <div class="hr hr-8"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-show="j.registerTypeId == 1 || j.registerTypeId == 2">
        <div class="widget-box">
            <div class="widget-header">
                <div class="row">
                    <div class="col-xs-1">
                        <i class="icon-ok-circle green  icon-only bigger-120" ng-show="verification" ng-click="doConfirmVerifEqual('jobs.registerType',j.id);"></i>
                        <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="6" ng-show="verification" code="jobs.registerType" id-element="{{j.id}}"></i>
                        <i class="icon-ban-circle gray icon-only bigger-120" ng-show="verification"  ng-click="doConfirmVerifNotKnow('jobs.registerType',j.id);"></i>
                    </div>
                    <div class="col-xs-11">
                        <h4 ng-show="verification ==true">Trabajo {{j.registerType.name}}</h4>
                        <h4 ng-show="verification == false">Especificación del trabajo</h4>
                    </div>
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
                                           data-date-format="yyyy/mm/dd" value="{{j.start}}"
                                           data-val="true" data-val-required="La fecha de inicio es un campo requerido"
                                           name="start"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <span class="field-validation-valid" data-valmsg-for="start"
                                              data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-3">
                                Salario semanal:
                            </div>
                            <div class="col-xs-3">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener máximo 7 caracteres" name="salaryWeek"
                                       data-val-length-max="7" data-val-length-min="1"
                                       data-val-required="El salario semanal es un campo requerido"
                                       data-val-regex-pattern="([0-9]+(.[0-9])?)"
                                       data-val-regex="El salario sólo puede contener números y un punto"
                                       type="text" value="" value="{{j.salaryWeek}}">
                            </div>
                            <div class="row">
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="salaryWeek"
                                          data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row schedule_visible">
                            <div class="widget-box">
                                <div class="widget-header">
                                    <i class="icon-remove-circle red  icon-only bigger-120" verif-comp level-child="3"
                                       ng-show="verification"></i>
                                    <h5><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h5>
                                </div>
                                <div class="widget-body">
                                    <br/>
                                    <div class="row element-center" ng-controller="scheduleController">
                                        <%@ include file="/WEB-INF/jsp/reviewer/verification/shared/schedule.jsp" %>
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
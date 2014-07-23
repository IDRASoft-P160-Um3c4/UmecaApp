<div class="row">
    <input type="hidden" ng-update-hidden ng-model="idCase" name="idCase" id="idCase"
           ng-init="idCase = ${(idCase == null) ? 0 : idCase};">
    <input type="hidden" ng-update-hidden ng-model="id" name="id" id="id"
           ng-init='id = ${(j.id == null)? 0: j.id}'>
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            Empresa:
        </div>
        <div class="col-xs-10">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y m&aacute;ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="3" data-val-required="La empresa es un campo requerido"
                   type="text" value="" ng-model="j.company" ng-init='j.company = "${(j.company == null) ? '' : j.company}"' id="company" name="company">
        </div>
        <div class="row">
            <div class="col-xs-offset-2 element-rigth">
                <span class="field-validation-valid" data-valmsg-for="company" data-valmsg-replace="true"></span>
            </div>
        </div>
    </div>

</div>
<br/>
<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Puesto:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 3 y m&aacute;ximo 50 caracteres"
                   data-val-length-max="50" data-val-length-min="3" data-val-required="El puesto es un campo requerido"
                   type="text" value="" ng-init='j.post= "${(j.post == null) ? "" : j.post}"' ng-model="j.post" name="post" id="post">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="post" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="col-xs-5 element-left">
            Tel&eacute;fono:
        </div>
        <div class="col-xs-6">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 8 y m&aacute;ximo 20 caracteres"
                   data-val-length-max="20" data-val-length-min="8" data-val-required="El tel&eacute;fono es un campo requerido"
                   type="text" value="" ng-model="j.phone" ng-init='j.phone = "${(j.phone == null)? "": j.phone}"' name="phone" id="phone">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="phone" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Nombre del patr&oacute;n:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m&aacute;ximo 150 caracteres"
                   data-val-length-max="150" data-val-length-min="6" data-val-required="El nombre del patr&oacute;n es un campo requerido"
                   type="text" value="" ng-model="j.nameHead" ng-init='j.nameHead= "${(j.nameHead == null) ? "" : j.nameHead}"' name="nameHead" id="nameHead">
        </div>
        <div class="col-xs-10 col-xs-offset-1">
            <span class="field-validation-valid" data-valmsg-for="nameHead" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="col-xs-5 element-left">
            Tipo de empleo:
        </div>
        <div class="col-xs-6">
            <input type="hidden" ng-update-hidden ng-model="j.registerTypeId" name="registerType.id" id="registerTypeId"
                   ng-init='j.registerTypeId = ${(registerTypeId == null) ? "undefined" : registerTypeId}'>
            <select class="form-control element-center" ng-model="j.registerType"
                    ng-options="e.name for e in lstRegisterType" ng-init='lstRegisterType = ${lstRegisterType};'
                    ng-change="j.registerTypeId = j.registerType.id;"></select>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">Direcci&oacute;n:</div>
        <div class="col-xs-10">
           <textarea class="form-control" ng-init='j.address = "${(j.address == null) ? "" : j.address}"' ng-model="j.address" name="address" id="address"
                     data-val="true" data-val-required="La direcci&oacute;n es un campo requerido"
                     data-val-length="Debe tener al menos 6 y m&aacute;ximo 500 caracteres"
                     data-val-length-max="500" data-val-length-min="6">${j.address}</textarea>
            <br/>
            <span class="field-validation-valid" data-valmsg-for="address" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row" ng-show="j.registerTypeId==3">
    <div class="widget-box">
        <div class="widget-header">
            <h4>Trabajo {{j.registerType.name}}</h4>
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
                                <input class="form-control date-picker" id="jdpStart" type="text" data-date-format="yyyy/mm/dd" value="${j.startPrev}" readonly="readonly"
                                       data-val="true" data-val-required="La fecha de inicio es un campo requerido" name="startPrev"/> <span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                            </div>
                            <div class="row">
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="startPrev" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            Fecha Fin:
                        </div>
                        <div class="col-xs-3">
                            <div class="input-group">
                                <input class="form-control date-picker" id="jdbEnd" type="text" data-date-format="yyyy/mm/dd"  value='${j.end}' readonly="readonly"
                                       data-val="true" data-val-required="La fecha de fin es un campo requerido" name="end"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                            </div>

                            <div class="row">
                                <div class="col-xs-9 col-xs-offset-3">
                                    <span class="field-validation-valid" data-valmsg-for="end" data-valmsg-replace="true" ></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-xs-3">Motivo de cambio:</div>
                        <div class="col-xs-9">
                            <textarea id="form-field-11" class="form-control" ng-init='j.reasonChange = "${(j.reasonChange == null) ? "" : j.reasonChange}";' ng-model ="j.reasonChange" name ="reasonChange"
                                      data-val="true" data-val-required="El motivo de cambio es un campo requerido" >${j.reasonChange}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <span class="field-validation-valid" data-valmsg-for="reasonChange" data-valmsg-replace="true"></span>
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
            <h4>Trabajo  {{j.registerType.name}}</h4>
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
                                <input class="form-control date-picker" id="jdpStartCurrent" type="text" data-date-format="yyyy/mm/dd"  value='${j.start}'  readonly="readonly"
                                       data-val="true" data-val-required="La fecha de inicio es un campo requerido" name="start"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <span class="field-validation-valid" data-valmsg-for="start" data-valmsg-replace="true"></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            Salario semanal:
                        </div>
                        <div class="col-xs-3">
                            <input class="form-control" data-val="true" data-val-length="Debe tener máximo 7 caracteres"  name="salaryWeek"
                                   data-val-length-max="7" data-val-length-min="1" data-val-required="El salario semanal es un campo requerido"
                                   data-val-regex-pattern="([0-9]+(.[0-9])?)" data-val-regex="El salario sólo puede contener números y un punto"
                                   type="text" value="" ng-init='j.salaryWeek = ${(j.salaryWeek == null)? 0 : j.salaryWeek}' ng-model="j.salaryWeek">
                        </div>
                        <div class="row">
                            <div class="col-xs-9 col-xs-offset-3">
                                <span class="field-validation-valid" data-valmsg-for="salaryWeek" data-valmsg-replace="true"></span>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-header header-color-blue">
                            <h5 class="bigger lighter">
                                <h6><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h6>
                            </h5>
                        </div>

                        <div class="widget-body">
                            <br/>
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp" %>
                        </div>
                    </div>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
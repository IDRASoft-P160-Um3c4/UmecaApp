<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">
            Empresa:
        </div>
        <div class="col-xs-10">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                   type="text" value="" ng-model="m.name">
        </div>
        <div class="row">
            <div class="col-xs-9 col-xs-offset-3">
                <span class="field-validation-valid" data-valmsg-for="name" data-valmsg-replace="true"></span>
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
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                   type="text" value="" ng-model="m.name">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="col-xs-5 element-left">
            Teléfono:
        </div>
        <div class="col-xs-6">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido" id="name"
                   type="text" value="" ng-model="m.name">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-6">
        <div class="col-xs-4 element-left">
            Nombre del patrón:
        </div>
        <div class="col-xs-8">
            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                   type="text" value="" ng-model="m.name">
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="col-xs-5 element-left">
            Tipo de empleo:
        </div>
        <div class="col-xs-6">
            <select class="form-control element-center" ng-init="typeJob=0" ng-model="typeJob">
                <option value="0">Actual</option>
                <option value="1">Anterior</option>
            </select>
        </div>
        <div class="col-xs-9 col-xs-offset-3">
            <span class="field-validation-valid" data-valmsg-for="lastName1" data-valmsg-replace="true"></span>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="col-xs-12">
        <div class="col-xs-2 element-left">Dirección:</div>
        <div class="col-xs-10">
            <textarea id="form-field-11" class="form-control"></textarea>
        </div>
    </div>
</div>
<br/>
<div class="row" ng-show="typeJob==1">
    <div class="widget-box">
        <div class="widget-header">
            <h4>Trabajo Anterior</h4>
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
                                <input class="form-control date-picker" id="dpStartBefore" type="text" data-date-format="dd-mm-yyyy" />
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            Fecha Fin:
                        </div>
                        <div class="col-xs-3">
                            <div class="input-group">
                                <input class="form-control date-picker" id="dpEndBefore" type="text" data-date-format="dd-mm-yyyy" />
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
                            <textarea id="form-field-11" class="form-control"></textarea>
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
<div class="row" ng-show="typeJob==0">
    <div class="widget-box">
        <div class="widget-header">
            <h4>Trabajo actual</h4>
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
                                <input class="form-control date-picker" id="dpStartBefore" type="text" data-date-format="dd-mm-yyyy" />
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            Salario semanal:
                        </div>
                        <div class="col-xs-3">
                            <input class="form-control" data-val="true" data-val-length="Debe tener al menos 6 y m?ximo 200 caracteres"
                                   data-val-length-max="200" data-val-length-min="3" data-val-required="El Nombre es un campo requerido"
                                   type="text" value="">
                        </div>
                    </div>
                    <br/>
                    <div class="row schedule_visible" >
                        <div class="widget-box">
                            <div class="widget-header">
                                <h5><i class="glyphicon glyphicon-calendar "></i>Disponibilidad</h5>
                            </div>

                            <div class="widget-body">
                                <br/>
                                <%@ include file="/WEB-INF/jsp/reviewer/meeting/shared/schedule.jsp"%>
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
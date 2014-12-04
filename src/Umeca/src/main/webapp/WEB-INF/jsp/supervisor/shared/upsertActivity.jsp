<div id="UpsertActivityEventDlgId" class="modal fade" ng-controller="upsertActivityEventController"
     data-backdrop="static" ng-cloak>
<div class="modal-dialog modal-showhigh">
<div class="modal-content">
<div class="modal-header">
    <div class="alert alert-info">
        <button type="button" class="close" ng-click="cancel()">&times;</button>
        <h4 class="modal-title element-center">{{Title}}</h4>
    </div>
</div>
<div class="modal-body">
<form id="activityArrangementForm" name="activityArrangementForm" class="form-horizontal" role="form">
<div class="row">
    <label class="col-xs-3 control-label">Obligaciones procesales:</label>

    <div class="col-xs-9">
        <div class="row" ng-repeat="e in lstArrangements">
            <div class="col-xs-11 checkbox">
                <label>
                    <input type="checkbox" ng-model="m.lstArrangements[e.id]" ng-disabled="isReadOnly"/>
                    <span class="control-label">{{e.name}} | {{e.description}}</span>
                </label>
            </div>
        </div>
    </div>
</div>
<br/>

<div class="form-group">
    <label for="selectActivity" class="col-xs-3 control-label">Actividad de supervisi&oacute;n:</label>

    <div class="col-xs-9">
        <select class="form-control element-center" ng-model="m.activity" id="selectActivity"
                ng-options="e.name for e in lstActivities"
                ng-change="m.activityId = m.activity.id"
                ng-disabled="isReadOnly">
        </select>
    </div>
</div>
<div class="form-group" ng-show="m.activity.specification==true">
    <label class="col-xs-3 control-label" for="activitySpec">Especifique actividad</label>

    <div class="col-xs-4">
        <textarea ng-model="m.activitySpec"
                  id="activitySpec"
                  type="text" class="input-xxlarge">
        </textarea>
        <br/>
        <span class="field-validation-error" ng-show="activitySpecError!=''">{{activitySpecError}}</span>
    </div>
</div>

<div class="form-group">
    <label for="selectGoal" class="col-xs-3 control-label">Objetivo de la actividad:</label>

    <div class="col-xs-9">
        <select class="form-control element-center" ng-model="m.goal" id="selectGoal"
                ng-options="e.name for e in lstGoals"
                ng-change="m.goalId = m.goal.id"
                ng-disabled="isReadOnly">
        </select>
    </div>
</div>
<div class="form-group" ng-show="m.goal.specification==true">
    <label class="col-xs-3 control-label" for="goalSpec">Especifique objetivo</label>

    <div class="col-xs-4">
        <textarea ng-model="m.goalSpec"
                  id="goalSpec"
                  type="text" class="input-xxlarge">
        </textarea>
        <br/>
        <span class="field-validation-error" ng-show="goalSpecError!=''">{{goalSpecError}}</span>
    </div>
</div>

<div class="form-group">
    <label for="selectSource" class="col-xs-3 control-label">Fuentes:</label>

    <div class="col-xs-9">
        <select class="form-control element-center" ng-model="m.source" id="selectSource"
                ng-options="e.name for e in lstSources"
                ng-change="m.sourceId = m.source.id; changeSource();"
                ng-disabled="isReadOnly">
        </select>
    </div>
</div>
<div class="form-group" ng-show="m.isOtherSourceSelected==true">
    <label class="col-xs-3 control-label" for="sourceSpec">Especifique fuente</label>

    <div class="col-xs-4">
        <textarea ng-model="m.sourceSpec"
                  id="sourceSpec"
                  type="text" class="input-xxlarge">
        </textarea>
        <br/>
        <span class="field-validation-error" ng-show="sourceSpecError!=''">{{sourceSpecError}}</span>
    </div>
</div>

<div class="row" ng-show="!isNew && m.groupCount > 1">
<div class="col-xs-10 col-xs-offset-1">
<div class="widget-box light-border">
<div class="widget-header header-color-dark">
    <h5>Informaci&oacute;n del grupo de actividades</h5>
</div>
<div class="widget-body">
<div class="widget-main">
<div class="widget-box transparent">
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-6">
                <label for="id-date-picker-end" class="control-label">Fecha inicial:</label>
                <div class="row">
                    <div class="col-xs-10">
                        <div class="input-group">
                            <input class="form-control date-picker" id="id-date-picker-group-start" readonly="readonly"
                                   type="text" data-date-format="dd-mm-yyyy"/>
                                                                <span class="input-group-addon">
                                                                    <i class="icon-calendar bigger-110"></i>
                                                                </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-6">
                <label for="id-date-picker-end" class="control-label">Fecha final:</label>
                <div class="row">
                    <div class="col-xs-10">
                        <div class="input-group">
                            <input class="form-control date-picker" id="id-date-picker-group-end" readonly="readonly"
                                   type="text" data-date-format="dd-mm-yyyy"/>
                                                                <span class="input-group-addon">
                                                                    <i class="icon-calendar bigger-110"></i>
                                                                </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12">
                <label class="control-label">*Total: {{m.groupCount}} actividad(es)</label>
            </div>
            <br/>
            <div class="col-xs-12">
                <span><small>*Las actividades pre-eliminadas son contadas en el total, ya que a&uacute;n no se eliminan.</small></span>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
</div>
</div>


<div class="row">
<div class="col-xs-10 col-xs-offset-1">
<div class="widget-box light-border">
<div class="widget-header header-color-dark">
    <h5>Periodicidad</h5>
</div>
<div class="widget-body">
<div class="widget-main">
<div class="widget-box transparent" ng-show="isNew">
    <div class="widget-body">
        <div class="checkbox">
            <label>
                <input type="checkbox" ng-model="m.isForToday" ng-change="onIsForToday()"/>
                <span class="control-label"><strong>Solo por hoy&nbsp;&nbsp;({{m.dToday}})</strong></span>
            </label>
        </div>
    </div>
</div>
<div class="row" ng-show="!m.isForToday">
    <div class="col-xs-6">
        <label for="id-date-picker-start" class="control-label">Fecha inicial:</label>

        <div class="row">
            <div class="col-xs-10">
                <div class="input-group">
                    <input class="form-control date-picker" id="id-date-picker-start" readonly="readonly"
                           type="text" data-date-format="dd-mm-yyyy"/>
																	<span class="input-group-addon">
																		<i class="icon-calendar bigger-110"></i>
																	</span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-6">
        <label for="id-date-picker-end" class="control-label">Fecha final:</label>

        <div class="row">
            <div class="col-xs-10">
                <div class="input-group">
                    <input class="form-control date-picker" id="id-date-picker-end" readonly="readonly"
                           type="text" data-date-format="dd-mm-yyyy"/>
                                                                <span class="input-group-addon">
                                                                    <i class="icon-calendar bigger-110"></i>
                                                                </span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-6">
        <label for="id-timepicker-start" class="control-label">Hora inicial:</label>

        <div class="row">
            <div class="col-xs-10">
                <div class="input-group bootstrap-timepicker">
                    <input id="id-timepicker-start" type="text" readonly="readonly" class="form-control"/>
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xs-6">
        <label for="id-timepicker-end" class="control-label">Hora final:</label>

        <div class="row">
            <div class="col-xs-10">
                <div class="input-group bootstrap-timepicker">
                    <input id="id-timepicker-end" type="text" readonly="readonly" class="form-control"/>
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>

<div class="row" ng-show="isNew && !m.isForToday">
    <div class="col-xs-10 col-xs-offset-1 widget-container-span">
        <div class="widget-box transparent" ng-show="!m.isForToday">
            <div class="widget-header">
                <h6 class="lighter">Elige la periodicidad por: &nbsp; <select class="element-center"
                                                                              ng-model="m.periodicity"
                                                                              id="selectPeriodicity"
                                                                              ng-options="e.name for e in lstPeriodicity"
                                                                              ng-change="m.periodicityId = m.periodicity.id"
                                                                              ng-disabled="isReadOnly">
                </select>
                </h6>
            </div>
            <div ng-show="m.periodicityId < 3 ">
                <div class="widget-body">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-1">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.chkBusinessWeek"
                                               ng-change="onBusinessWeek()"/>
                                        <span class="control-label"><strong>Toda la semana h&aacute;bil</strong></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-1">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.chkWeek" ng-change="onWeek()"/>
                                        <span class="control-label"><strong>Toda la semana</strong></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 col-xs-offset-1">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.daysOfWeek[1]"/>
                                        <span class="control-label">Lunes</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-2">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.daysOfWeek[2]"/>
                                        <span class="control-label">Martes</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-2">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.daysOfWeek[3]"/>
                                        <span class="control-label">Mi&eacute;rcoles</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-2">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.daysOfWeek[4]"/>
                                        <span class="control-label">Jueves</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-2">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.daysOfWeek[5]"/>
                                        <span class="control-label">Viernes</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 col-xs-offset-1">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.daysOfWeek[6]"/>
                                        <span class="control-label">S&aacute;bado</span>
                                    </label>
                                </div>
                            </div>
                            <div class="col-xs-2">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.daysOfWeek[0]"/>
                                        <span class="control-label">Domingo</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div ng-show="m.periodicityId > 2">
                <div class="widget-body">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <div class="row">
                            <div class="col-xs-8">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.chkAllDays" ng-change="onAllDays()"/>
                                                <span class="control-label"><strong>Todos los
                                                    d&iacute;as</strong></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" ng-model="m.sundayNoBusiness"/>
                                        <span class="control-label">En caso de domingo, mover la actividad al lunes siguiente</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div ng-repeat="r in range(0,3,1)">
                            <div class="row">
                                <div ng-repeat="c in range(0,12,1)">
                                    <div ng-if="($index+1)+(r*12) <= 31" class="col-xs-1">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox"
                                                       ng-model="m.daysOfMonth[($index)+(r*12)]"/>
                                                <span class="control-label">{{($index+1)+(r*12)}}</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
</div>
</div>
</div>
</form>
<br/>

<div class="row" ng-show="msgError">
    <div class="col-xs-8 col-xs-offset-2 alert alert-danger element-center">
        <span class="control-label element-center">{{msgError}}</span>
    </div>
</div>
<div class="row" ng-show="!isNew && !isReadOnly">
    <div class="col-xs-8 col-xs-offset-3 element-right">
        <div class="tinyfont">*Eliminar grupo, borrar&aacute; todas las actividades no realizadas del grupo</div>
    </div>
</div>
<div class="row" ng-show="isNew && !isReadOnly">
    <div class="col-xs-8 col-xs-offset-3 element-right">
        <div class="tinyfont">*Si usted elige un periodo, se crear&aacute; un grupo de actividades, el cual, podr&aacute;
            eliminar posteriormente
        </div>
    </div>
</div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default btn-primary" ng-show="isNew" ng-click="add()">Agregar</button>
    <button type="button" class="btn btn-default btn-primary" ng-show="!isNew && !isReadOnly" ng-click="save()">
        Modificar
    </button>
    <button type="button" class="btn btn-default btn-danger" ng-show="!isNew && !isReadOnly" ng-click="delete()">
        Eliminar
    </button>
    <button type="button" class="btn btn-default btn-danger" ng-show="!isNew && !isReadOnly" ng-click="deleteGroup()">
        Eliminar grupo
    </button>
    <button type="button" class="btn btn-default" ng-click="cancel()">{{(isReadOnly?'Regresar':'Cancelar')}}</button>
</div>
</div>
</div>
</div>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div id="UpsertRolActivityDlgId" class="modal fade" ng-controller="upsertRolActivityController" data-backdrop="static" ng-cloak>
    <div class="modal-dialog modal-showhigh">
        <div class="modal-content">
            <div class="modal-header">
                <div class="alert alert-info">
                    <button type="button" class="close" ng-click="cancel()">&times;</button>
                    <h4 class="modal-title element-center">Rol de supervisi&oacute;n</h4>
                </div>
            </div>
            <div class="modal-body">
                <form id="rolActivityForm" name="rolActivityForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="selectSupervisor" class="col-xs-3 control-label">Supervisores disponibles:</label>
                        <div class="col-xs-9">
                            <select class="form-control element-center" ng-model="m.supervisor" id="selectSupervisor"
                                    ng-options="(e.name + ' (' + e.description + ')') for e in lstSupervisor"
                                    ng-change="m.supervisorId = m.supervisor.id"
                                    ng-disabled = "isReadOnly">
                            </select>
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
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <label for="id-date-picker-start" class="control-label">Fecha inicial:</label>
                                                <div class="row">
                                                    <div class="col-xs-10">
                                                        <div class="input-group">
                                                            <input class="form-control date-picker" id="id-date-picker-start" readonly="readonly" type="text" data-date-format="dd-mm-yyyy" />
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
                                                            <input class="form-control date-picker" id="id-date-picker-end" readonly="readonly" type="text" data-date-format="dd-mm-yyyy" />
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
                                                            <input id="id-timepicker-start" type="text" readonly="readonly" class="form-control umeca-time-picker" />
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
                                                            <input id="id-timepicker-end" type="text" readonly="readonly" class="form-control umeca-time-picker" />
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <br />
                                        <div class="row"  ng-show="isNew">
                                            <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                                                <div class="widget-box transparent">
                                                    <div class="widget-header">
                                                        <h6 class="lighter">Elige la periodicidad por d&iacute;a(s) de la semana</h6>
                                                    </div>

                                                    <div class="widget-body">
                                                        <div class="widget-main padding-6 no-padding-left no-padding-right">
                                                            <div class="row">
                                                                <div class="col-xs-8 col-xs-offset-1">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model="m.chkBusinessWeek" ng-change = "onBusinessWeek()"/>
                                                                            <span class="control-label"><strong>Toda la semana h&aacute;bil</strong></span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-8 col-xs-offset-1">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model="m.chkWeek" ng-change = "onWeek()"/>
                                                                            <span class="control-label"><strong>Toda la semana</strong></span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2 col-xs-offset-1">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model = "m.daysOfWeek[1]" />
                                                                            <span class="control-label">Lunes</span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model = "m.daysOfWeek[2]"/>
                                                                            <span class="control-label">Martes</span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model = "m.daysOfWeek[3]"/>
                                                                            <span class="control-label">Mi&eacute;rcoles</span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model = "m.daysOfWeek[4]"/>
                                                                            <span class="control-label">Jueves</span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model = "m.daysOfWeek[5]"/>
                                                                            <span class="control-label">Viernes</span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-2 col-xs-offset-1">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model = "m.daysOfWeek[6]"/>
                                                                            <span class="control-label">S&aacute;bado</span>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <div class="checkbox">
                                                                        <label>
                                                                            <input type="checkbox" ng-model = "m.daysOfWeek[0]"/>
                                                                            <span class="control-label">Domingo</span>
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
                </form>
                <br/>
                <div class="row" ng-show="msgError">
                    <div class="col-xs-8 col-xs-offset-2 alert alert-danger element-center">
                        <span class="control-label element-center">{{msgError}}</span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-primary" ng-show="isNew" ng-click="add()">Agregar</button>
                <button type="button" class="btn btn-default btn-primary" ng-show="!isNew && !isReadOnly" ng-click="save()">Modificar</button>
                <button type="button" class="btn btn-default btn-danger" ng-show="!isNew && !isReadOnly"  ng-click="delete()">Eliminar</button>
                <button type="button" class="btn btn-default" ng-click="cancel()">{{(isReadOnly?'Regresar':'Cancelar')}}</button>
            </div>
        </div>
    </div>
</div>
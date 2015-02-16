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
                <form id="activityForm" name="activityForm" class="form-horizontal" role="form">
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                            <div class="widget-box">
                                <div class="widget-header widget-hea1der-small header-color-dark">
                                    <h6>Fecha de la actividad</h6>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main padding-4">
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-xs-12 align-center">
                                                    <strong>{{startDt | date:'dd / MM / yyyy'}}</strong>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                            <div class="widget-box">
                                <div class="widget-header widget-hea1der-small header-color-dark">
                                    <h6>Horario de la actividad</h6>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main padding-4">
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-xs-5 col-xs-offset-1">
                                                    <label for="id-timepicker-start" class="control-label">Hora
                                                        inicial:</label>

                                                    <div class="row">
                                                        <div class="col-xs-10">
                                                            <div class="input-group bootstrap-timepicker">
                                                                <input id="id-timepicker-start" type="text"
                                                                       readonly="readonly"
                                                                       class="form-control umeca-time-picker"/>
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-5 col-xs-offset-1">
                                                    <label for="id-timepicker-end" class="control-label">Hora
                                                        final:</label>

                                                    <div class="row">
                                                        <div class="col-xs-10">
                                                            <div class="input-group bootstrap-timepicker">
                                                                <input id="id-timepicker-end" type="text"
                                                                       readonly="readonly"
                                                                       class="form-control umeca-time-picker"/>
															<span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="space-10"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-xs-10 col-xs-offset-1 widget-container-span">
                            <div class="widget-box">
                                <div class="widget-header widget-hea1der-small header-color-dark">
                                    <h6>Informaci&oacute;n de la actividad</h6>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main padding-4">
                                        <div class="content">
                                            <div class="row">
                                                <div class="col-xs-2 align-right">
                                                    <label class="control-label">
                                                        Lugar:
                                                    </label>
                                                </div>
                                                <div class="col-xs-9">
                                                    <textarea class="form-control" name="place" required="required" ng-maxlength="500" rows="4" ng-model="m.place">
                                                    </textarea>
                                                    <span class="error" ng-show="activityForm.place.$error.required">Campo requerido</span>
                                                    <span class="error" ng-show="activityForm.place.$error.maxlength">Longitud m&aacute;xima de 500 caracteres</span>
                                                </div>
                                            </div>
                                            <div class="space-10"></div>
                                            <div class="row">
                                                <div class="col-xs-2 align-right">
                                                    <label class="control-label">
                                                        Descripci&oacute;n:
                                                    </label>
                                                </div>
                                                <div class="col-xs-9">
                                                    <textarea class="form-control" name="description" required="required" ng-maxlength="500" rows="4" ng-model="m.description">
                                                    </textarea>
                                                    <span class="error" ng-show="activityForm.description.$error.required">Campo requerido</span>
                                                    <span class="error" ng-show="activityForm.description.$error.maxlength">Longitud m&aacute;xima de 500 caracteres</span>
                                                </div>
                                            </div>
                                            <div class="space-10"></div>
                                            <div class="row">
                                                <div class="col-xs-offset-3 col-xs-6">
                                                    <div class="form-group">
                                                        <label for="priority" class="col-xs-3 control-label">Prioridad:</label>
                                                        <div class="col-xs-6">
                                                            <select class="form-control element-center" ng-model="m.priority" id="priority"
                                                                    ng-options="e.name for e in cfg.lstPriorities"
                                                                    ng-change="m.priorityId = m.priority.id"
                                                                    ng-disabled="isReadOnly">
                                                            </select>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <div style="width: 32px; height: 32px; background-color: {{m.priority.description}}"></div>
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
                <button type="button" class="btn btn-default btn-primary" ng-show="isNew" ng-disabled="!activityForm.$valid" ng-click="add()">Guardar
                </button>
                <button type="button" class="btn btn-default btn-primary" ng-show="!isNew && !isReadOnly" ng-disabled="!activityForm.$valid"
                        ng-click="save()">
                    Modificar
                </button>
                <button type="button" class="btn btn-default btn-danger" ng-show="!isNew && !isReadOnly"
                        ng-click="delete()">
                    Eliminar
                </button>
                <button type="button" class="btn btn-default" ng-click="cancel()">
                    {{(isReadOnly?'Regresar':'Cancelar')}}
                </button>
            </div>
        </div>
    </div>
</div>
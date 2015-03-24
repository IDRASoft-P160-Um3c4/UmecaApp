<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormIncidentId");
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-controller="incidentController"
             ng-init='incident=${incident}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Agregar incidencia
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormIncidentId" name="FormIncidentId" class="form-horizontal" role="form">
                        <input type="hidden" name="idEmployee" value="{{incident.idEmployee}}">
                        <input type="hidden" name="id" value="{{incident.id}}">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Agregar incidencia</div>
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-xs-12">

                                                <div class="col-xs-12">
                                                    <br/>

                                                    <div ng-show="MsgError!=''"
                                                         class="alert alert-danger element-center"
                                                         ng-bind-html="MsgError">
                                                    </div>
                                                </div>
                                                <br/>

                                                <div id="divIncident">
                                                    <div class="col-xs-12">
                                                        <div class="col-xs-6">
                                                            <label>Tipo</label>
                                                            <br/>
                                                            <select class="form-control element-center"
                                                                    ng-model="incident.incidentType"
                                                                    ng-options="e.name for e in lstIncidentType"
                                                                    ng-change="clearSpec();"
                                                                    ng-init='lstIncidentType = ${lstIncidentType};'></select>
                                                            <input type="hidden" name="idIncidentType"
                                                                   value="{{incident.incidentType.id}}"/>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <div ng-show="incident.incidentType.specification==true">
                                                                <label>Especifique</label>
                                                                <br/>
                                                                <input id="specIncidentType"
                                                                       ng-model="incident.specIncidentType"
                                                                       name="specIncidentType"
                                                                       type="text" style=" width: 100% !important"
                                                                       class="input-xxlarge" data-val="true"
                                                                       data-val-required="Especifique es un campo requerido"/>
                                                                <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="specIncidentType"
                                                                  data-valmsg-replace="true"></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Raz&oacute;n</label>
                                                            <br/>
                                                            <textarea class="form-control limited" name="reason"
                                                                      ng-model="incident.reason"
                                                                      name="reason"
                                                                      maxlength="980"
                                                                      data-val="true"
                                                                      data-val-required="Raz&oacute;n es un campo requerido">
                                                            </textarea>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="reason"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>

                                                        <div class="col-xs-6">
                                                            <label>Fecha</label>
                                                            <br/>

                                                            <div class="input-group">
                                                                <input class="form-control date-picker"
                                                                       name="incidentDate" type="text"
                                                                       data-date-format="yyyy/mm/dd"
                                                                       readonly
                                                                       ng-model="incident.incidentDate" data-val="true"
                                                                       data-val-required="Fecha es un campo requerido"/>
                                                                    <span class="input-group-addon">
                                                                        <i class="icon-calendar bigger-110"></i>
                                                                    </span>
                                                            </div>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="incidentDate"
                                                              data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <div class="col-xs-12">
                                                            <label>Comentarios</label>
                                                            <br/>
                                                            <textarea class="form-control limited" name="comments"
                                                                      ng-model="incident.comments"
                                                                      name="reason"
                                                                      maxlength="980"
                                                                      data-val="true"
                                                                      data-val-required="Comentarios es un campo requerido">
                                                            </textarea>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="comments"
                                                                  data-valmsg-replace="true"></span>
                                                            <br/>
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
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitIncident('#FormIncidentId', '<c:url value="/humanResources/digitalRecord/doUpsertIncident.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
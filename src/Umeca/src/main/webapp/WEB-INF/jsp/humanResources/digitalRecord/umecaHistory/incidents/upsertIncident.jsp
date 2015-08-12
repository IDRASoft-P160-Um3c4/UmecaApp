<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormIncidentId");
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $(function () {
        'use strict';
        var url = '<c:url value='/humanResources/digitalRecord/uploadAttachment.json' />';

        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            done: function (e, data) {
                try {
                    var scope = angular.element($("#FormIncidentId")).scope();

                    if (data.result === undefined || data.result.hasError === undefined) {
                        scope.setOutError("No hubo respuesta del servidor. Por favor intente de nuevo");
                        return;
                    }

                    if (data.result.hasError === true) {
                        scope.setOutError(data.result.message);
                        return;
                    } else {
                        scope.setSuccess(data.result);
                        scope.$apply(function () {
                            scope.incident.fileId = data.result.returnData;
                            scope.canSaveI = true;
                        });
                    }

                } catch (e) {
                    scope.setOutError("Hubo un error al momento de procesar la respuesta: " + e);
                    return;
                }
                finally {
                    window.setTimeout(function () {
                        $('#progress .progress-bar').css('width', 0 + '%');
                    }, 2000);
                }

            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                        'width',
                        progress + '%'
                );
            }
        }).prop('disabled', !$.support.fileInput)
                .parent().addClass($.support.fileInput ? undefined : 'disabled');

    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-controller="attachmentController">
            <div class="modal-content" ng-controller="incidentController"
                 ng-init='incident=${incident}; canSaveI=${canSaveI};'>
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
                                                    <div ng-show="MsgSuccess&&MsgSuccess!=''"
                                                         class="alert alert-success element-center success-font"
                                                         ng-bind-html="MsgSuccess">
                                                    </div>
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
                                                            <br/><small>(A&ntilde;o/Mes/D&iacute;a) Ej. (2015/01/01)</small>

                                                            <div class="input-group">
                                                                <input class="form-control date-picker"
                                                                       name="incidentDate" type="text"
                                                                       data-date-format="yyyy/mm/dd"

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

                                                    <div class="col-xs-12" ng-show="chkFields()==true">
                                                        <br/>

                                                        <div class="col-xs-10 col-xs-offset-1">

                                                            <input type="hidden" name="fileId"
                                                                   value="{{incident.fileId}}">

                                                            <input type="hidden" name="description"
                                                                   value="INCIDENT_FILE_EMPLOYEE_{{incident.idEmployee}}">

                                                            <div>
                                                                <div class="row">
                                                                    <div class="col-xs-12 element-center">
                                                             <span class="btn btn-success fileinput-button element-center">
                                                                <i class="glyphicon glyphicon-upload"></i>
                                                                <span>Elige el archivo...</span>
                                                                <input id="fileupload" type="file" name="files[]"/>
                                                            </span>
                                                                    </div>
                                                                </div>
                                                                <br/>

                                                                <div class="row">
                                                                    <div class="col-xs-12">
                                                                        <div id="progress" class="progress">
                                                                            <div class="progress-bar progress-bar-success"></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <br/>

                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div id="files" class="files"></div>
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
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span ng-show="canSaveI==true" class="btn btn-default btn-primary btn-sm"
                          ng-disabled="WaitFor==true"
                          ng-click="submitIncident('#FormIncidentId', '<c:url value="/humanResources/digitalRecord/doUpsertIncident.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
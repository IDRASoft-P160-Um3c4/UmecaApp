<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormAttachmentId");
    });

    $(function () {
        'use strict';
        var url = '<c:url value='/humanResources/digitalRecord/uploadAttachment.json' />';

        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            done: function (e, data) {
                try {
                    var scope = angular.element($("#FormAttachmentId")).scope();
                    if (data.result === undefined || data.result.hasError === undefined) {
                        scope.setOutError("No hubo respuesta del servidor. Por favor intente de nuevo");
                        return;
                    }
                    if (data.result.hasError === true) {
                        scope.setOutError(data.result.message);
                        return;
                    }

                    scope.setSuccess(data.result);

                } catch (e) {
                    scope.setOutError("Hubo un error al momento de procesar la respuesta: " + e);
                    return;
                } finally {
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
        <div class="modal-dialog" style="width:800px" ng-controller="attachmentController"
             ng-init='attachment=${attachment}; canSave=${canSave}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Subir archivo
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormAttachmentId" name="FormAttachmentId" class="form-horizontal" role="form">
                        <input type="hidden" name="idEmployee" value="{{attachment.idEmployee}}">
                        <input type="hidden" name="id" value="{{attachment.id}}">
                        <input type="hidden" name="fileId" value="{{attachment.fileId}}">
                        <input type="hidden" name="oldFileId" value="{{attachment.oldFileId}}">
                        <input type="hidden" id="description" name="description"
                               value="employee_{{attachment.idEmployee}}_attachment">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Subir archivo</div>
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <br/>

                                                <div class="col-xs-12">
                                                    <div ng-show="MsgSuccess&&MsgSuccess!=''"
                                                         class="col-xs-12 alert alert-success element-center success-font"
                                                         ng-bind-html="MsgSuccess">
                                                    </div>
                                                    <div ng-show="MsgError&&MsgError!=''"
                                                         class="alert alert-danger element-center error-font"
                                                         ng-bind-html="MsgError">
                                                    </div>
                                                </div>

                                                <br/>

                                                <div id="divAttachment">
                                                    <div class="col-xs-12">
                                                        <div class="col-xs-10 col-xs-offset-1">
                                                            <label>Nombre</label>
                                                            <br/>
                                                            <input id="attachmentName"
                                                                   ng-model="attachment.attachmentName"
                                                                   name="attachmentName"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Nombre es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="attachmentName"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-10 col-xs-offset-1">
                                                            <label>Descripci&oacute;n</label>
                                                            <br/>
                                                            <textarea class="form-control limited"
                                                                      ng-model="attachment.attachmentDescription"
                                                                      maxlength="980"
                                                                      id="attachmentDescription"
                                                                      data-val="true"
                                                                      name="attachmentDescription"
                                                                      data-val-required="Descripci&oacute;n es un campo requerido">
                                                            </textarea>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="attachmentDescription"
                                                                  data-valmsg-replace="true"></span>
                                                            <br/>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-10 col-xs-offset-1">

                                                            <div ng-show="attachment.attachmentDescription && attachment.attachmentName">
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
                                            <br/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>

                    </form>
                    <br/>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-show="canSave==true"
                          ng-click="submitAttachment('#FormAttachmentId', '<c:url value="/humanResources/digitalRecord/doUpsertAttachment.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
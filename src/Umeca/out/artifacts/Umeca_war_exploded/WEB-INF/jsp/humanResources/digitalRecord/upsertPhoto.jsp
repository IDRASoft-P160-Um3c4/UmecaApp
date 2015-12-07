<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormPhotoId");
    });

    $(function () {
        'use strict';
        var url = '<c:url value='/humanResources/digitalRecord/doUploadPhoto.json' />';

        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            done: function (e, data) {
                var scope = angular.element($("#FormPhotoId")).scope();
                try {

                    if (data.result === undefined || data.result.hasError === undefined) {
                        scope.setPhotoError("No hubo respuesta del servidor. Por favor intente de nuevo");
                        return;
                    }

                    if (data.result.hasError === true) {
                        scope.setPhotoError(data.result.message);
                        return;
                    }

                    if (data.result.urlToGo == "close") {
                        scope.setPhotoSuccess(data.result, "${pageContext.request.contextPath}/");
                        var mdlScope = angular.element($("#dlgUpModalId")).scope();
                        mdlScope.cancel(true);
                        return;
                    }
                } catch (e) {
                    scope.setPhotoError("Hubo un error al momento de procesar la respuesta: " + e);
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
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-camera"></i>&nbsp;&nbsp;Agregar foto a ${employeeName}
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormPhotoId" name="FormPhotoId" class="form-horizontal" role="form"
                          ng-controller="digitalRecordController">
                        <input type="hidden" name="idEmployee" value="${idEmployee}">
                        <input type="hidden" id="description" name="description"
                               value="employee_${idEmployee}_photo">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Agregar foto</div>
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

                                                <div id="divPhoto">

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-10 col-xs-offset-1">
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
                </div>
            </div>
        </div>
    </div>
</div>
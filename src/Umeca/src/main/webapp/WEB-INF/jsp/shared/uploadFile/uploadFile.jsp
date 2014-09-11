<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });

    $(function () {
        'use strict';
        var url = '<c:url value='/shared/uploadFile/doUploadFile.json' />';

        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            done: function (e, data) {
                try{
                    var scope =  angular.element($("#FormUploadId")).scope();
                    if(data.result === undefined || data.result.hasError === undefined){
                        scope.setOutError("No hubo respuesta del servidor. Por favor intente de nuevo");
                        return;
                    }
                    if(data.result.hasError === true){
                        scope.setOutError(data.result.message);
                        return;
                    }

                    scope.setSuccess(data.result.message);

                }catch(e){
                    scope.setOutError("Hubo un error al momento de procesar la respuesta: " + e);
                    return;
                }finally{
                    window.setTimeout(function(){
                        $('#progress .progress-bar').css('width', 0 + '%');
                    },2000);
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
        /*
        $('#fileupload').bind('fileuploadsubmit', function (e, data) {
            // The example input, doesn't have to be part of the upload form:
            /*var input = $('#input');
            data.formData = {example: input.val()};
            if (!data.formData.example) {
                data.context.find('button').prop('disabled', false);
                input.focus();
                return false;
            }   */
          //  alert(e);
        //});*/
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-upload"></i>&nbsp;Subir archivos al caso
                                <br/>Imputado: ${fullname}
                            </h5>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormUploadId" name="FormUploadId" ng-submit="submit('#FormUploadId')" class="form-horizontal"
                          role="form" ng-controller="uploadFileController">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.caseId" name="caseId" id="caseId"
                               ng-init="m.caseId = ${caseId};">

                        <div class="row">
                            <div class="col-xs-8 col-xs-offset-2">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n del
                                        archivo
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="row">
                                                    <div class="col-xs-3 element-right">
                                                        Descripci&oacute;n:
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <textarea class="form-control" data-val="true"
                                                               data-val-length="Debe tener al menos 4 y m&aacute;ximo 200 caracteres"
                                                               data-val-length-max="200" data-val-length-min="4"
                                                               data-val-required="Descripci&oacute;n es un campo requerido"
                                                               id="description" name="description" rows="5" cols="30"
                                                               type="text" ng-model="m.description" />
                                                    </div>
                                                    <div class="col-xs-7 col-xs-offset-3">
                                                    <span class="field-validation-valid" data-valmsg-for="description"
                                                          data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                                <hr/>
                                                <div ng-show="m.description">
                                                    <div class="row">
                                                        <div class="col-xs-12 element-center">
                                                             <span class="btn btn-success fileinput-button element-center">
                                                                <i class="glyphicon glyphicon-upload"></i>
                                                                <span>Elige el archivo...</span>
                                                                <input id="fileupload" type="file" name="files[]" />
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
                                        <br/>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div ng-show="MsgError" class="alert alert-danger element-center"  ng-bind-html="MsgError">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div ng-show="MsgSuccess" class="alert alert-success element-center"  ng-bind-html="MsgSuccess">
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
                    <span class="btn btn-primary btn-sm" ng-click="cancel(true)">
                        Regresar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>


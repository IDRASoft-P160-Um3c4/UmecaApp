<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
    });

    $(function () {
        'use strict';
        var url = '<c:url value='/shared/uploadFileGeneric/doUploadFileGeneric.json' />';

        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            done: function (e, data) {
                try{
                    var scope =  angular.element($("#FormUpId")).scope();
                    if(data.result === undefined || data.result.hasError === undefined){
                        scope.setOutError("No hubo respuesta del servidor. Por favor intente de nuevo");
                        return;
                    }
                    if(data.result.hasError === true){
                        scope.setOutError(data.result.message);
                        return;
                    }

                    scope.setSuccess(data.result);

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

    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content"  ng-controller="projectController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-tags"></i>&nbsp;&nbsp;{{(m.id <= 0 ? 'Agregar' : 'Editar')}} actividad al proyecto</h5>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormUpId" name="FormUpId" class="form-horizontal"
                          role="form">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.id" name="id" id="id" ng-init="m.id = ${id};">
                        <input type="hidden" ng-update-hidden ng-model="m.projectId" name="projectId" id="projectId" ng-init="m.projectId = ${projectId};">
                        <input type="hidden" ng-update-hidden ng-model="m.fileUploadGenericId" ng-init="m.fileUploadGenericId = undefined;" name="fileUploadGenericId" id="fileUploadGenericId" />
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-tags"></span>&nbsp;&nbsp;{{(m.id <= 0 ? 'Agregar' : 'Editar')}} actividad al proyecto
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="row">
                                                    <div class="col-xs-3 element-right">
                                                       Nombre de la actividad:
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <input class="form-control" name="name" required="required" ng-maxlength="100" ng-model="m.name"
                                                               ng-init="m.name = '${(model == null) ? '' : model.name}';"/>
                                                        <span class="error" ng-show="FormUpId.name.$error.required">Campo requerido</span>
                                                        <span class="error" ng-show="FormUpId.name.$error.maxlength">Longitud m&aacute;xima de 100 caracteres</span>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div class="row">
                                                    <div class="col-xs-3 element-right">
                                                        Descripci&oacute;n:
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <textarea class="form-control" name="description" required="required" ng-maxlength="450" rows="4"
                                                                  ng-model="m.description"
                                                                  ng-init="m.description = '${(model == null) ? '' : model.description}';">
                                                        </textarea>
                                                        <span class="error" ng-show="FormUpId.description.$error.required">Campo requerido</span>
                                                        <span class="error" ng-show="FormUpId.description.$error.maxlength">Longitud m&aacute;xima de 450 caracteres</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row" ng-show="m.description && m.name">
                                                <div class="col-xs-8 col-xs-offset-3">
                                                    <div class="checkbox">
                                                        <input type="checkbox" ng-model="reportForChk" ng-init="reportForChk = false;"/>
                                                        <span class="lbl"> &iquest;Adjuntar archivo?</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr/>
                                            <div ng-show="m.description && m.name && reportForChk">
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
                    <button class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </button>
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            ng-click="submit('#FormUpId', '<c:url value='/director/project/doUpsertActivity.json' />', undefined, FormUpId.$valid)">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


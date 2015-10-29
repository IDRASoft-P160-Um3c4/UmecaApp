<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px">
            <div class="modal-content"  ng-controller="projectController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-share"></i>&nbsp;&nbsp;Finalizar proyecto</h5>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormUpId" name="FormUpId" ng-submit="submit('#FormUpId')" class="form-horizontal"
                          role="form">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.id" name="id" id="id"
                               ng-init="m.id = ${projectId};">
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-share"></span>&nbsp;&nbsp;Finalizar proyecto
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="row">
                                                    <div class="col-xs-8 col-xs-offset-2 element-center">
                                                        <strong>&iquest;Est&aacute; seguro que desea finalizar el proyecto ${projectName}?</strong>
                                                    </div>
                                                </div>
                                                <br/>
                                                <div class="row">
                                                    <div class="col-xs-3 element-right">
                                                        Observaciones:
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <textarea class="form-control" name="comments" required="required" ng-maxlength="450" rows="4"
                                                                  ng-model="m.comments">
                                                        </textarea>
                                                        <span class="error" ng-show="FormUpId.comments.$error.required">Campo requerido</span>
                                                        <span class="error" ng-show="FormUpId.comments.$error.maxlength">Longitud m&aacute;xima de 450 caracteres</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="MsgError">
                                            <br/>
                                            <div class="col-xs-12">
                                                <div class="alert alert-danger element-center"  ng-bind-html="MsgError">
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
                            ng-click="submit('#FormUpId', '<c:url value='/director/project/doEndProject.json' />', undefined, FormUpId.$valid)">
                        Guardar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


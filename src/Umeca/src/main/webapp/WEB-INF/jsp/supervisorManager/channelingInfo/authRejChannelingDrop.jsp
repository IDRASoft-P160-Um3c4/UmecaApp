<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>

    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormUpId");
    });

</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px">
            <div class="modal-content"  ng-controller="authRejChannelingDropController">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h5 class="element-center"><i class="glyphicon glyphicon-screenshot"></i>&nbsp;&nbsp;Autorizar / Rechazar solicitud</h5>
                    </div>
                </div>
                <div class="modal-body"
                         ng-init='m = ${channelingInfo};'>
                    <form id="FormUpId" name="FormUpId" class="form-horizontal"
                          role="form">
                        <br/>
                        <input type="hidden" ng-update-hidden ng-model="m.id" name="id" id="id">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <h6><i class="glyphicon glyphicon-map-marker"></i>&nbsp;&nbsp;Autorizar / Rechazar solicitud de baja de canalizaci&oacute;n</h6>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Causa penal / # carpeta judicial:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.idMp}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Distrito:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.district}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Imputado:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.imputed}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Canalizaci&oacuten:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.channelingName}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Tipo de baja:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.channelingDropType}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Fecha solicitud:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.creationDateTx}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="row">
                                                    <div class="col-xs-6 element-right">
                                                        <label class="form-control-static">Usuario solicitante:</label>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <label class="form-control-static"><strong>{{m.userCreator}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <div class="row">
                                                    <div class="col-xs-3 element-right">
                                                        <label class="form-control-static">Comentarios solicitante:</label>
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <label class="form-control-static"><strong>{{m.commentsCreator}}</strong></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2 element-center">
                                                <label class="form-control-static"><b>Seleccione la acci&oacute;n a realzar sobre esta solicitud</b></label>
                                            </div>
                                            <div class="space-12"></div>
                                            <div class="col-xs-8 col-xs-offset-2 element-center">
                                                <input type="radio"  name="authRejValue"
                                                       id="authId" ng-value="true" ng-model="m.authRejValue" value="true" ng-init="m.authRejValue = false">
                                                <label for="authId">Si</label>   &nbsp;&nbsp;&nbsp;
                                                <input type="radio"  name="authRejValue"
                                                       id="rejId" ng-value="false" ng-model="m.authRejValue" value="false">
                                                <label for="rejId">No</label>
                                            </div>
                                        </div>
                                        <hr/>
                                        <div class="row">
                                            <div class="col-xs-12 element-center">
                                                <label class="form-control-static">Observaciones:</label>
                                            </div>
                                            <div class="col-xs-8 col-xs-offset-2 element-center">
                                                <textarea class="form-control" name="commentsAuthorizer" rows="4"
                                                          ng-required="true" ng-maxlength="100" ng-model="m.commentsAuthorizer"/>
                                                <span class="error" ng-show="FormUpId.commentsAuthorizer.$error.required">Campo requerido</span>
                                                <span class="error" ng-show="FormUpId.commentsAuthorizer.$error.maxlength">Longitud m&aacute;xima de 100 caracteres</span>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.commentsAuthorizer.length > 0">
                                            <div class="col-xs-8 col-xs-offset-2 element-center">
                                                <label class="form-control-static">Escriba su contrase&ntilde;a:</label>
                                            </div>
                                            <div class="space-12"></div>
                                            <div class="col-xs-8 col-xs-offset-2 element-center">
                                                <input type="password" class="form-control" name="password"
                                                       ng-required="true" ng-maxlength="1000" ng-model="m.password"/>
                                                <span class="error" ng-show="FormUpId.password.$error.required">Campo requerido</span>
                                                <span class="error" ng-show="FormUpId.password.$error.maxlength">Longitud m&aacute;xima de 100 caracteres</span>
                                            </div>
                                        </div>
                                        <br/>
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
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true || !(m.commentsAuthorizer.length > 0) || !(m.password.length > 0)"
                            ng-click="submit('#FormUpId', '<c:url value='/supervisorManager/channelingInfo/doAuthRejChannelingDrop.json' />', undefined, FormUpId.$valid)">
                        Aceptar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


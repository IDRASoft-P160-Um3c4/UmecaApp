<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px" ng-controller="authRejectController" ng-init="isAuthorized=${isAuthorized};">
            <div class="modal-content">
                <div class="modal-header">
                    <div  ng-class="(isAuthorized ? 'alert alert-info' : 'alert alert-warning')">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center" ng-show="isAuthorized"><i class="glyphicon glyphicon-saved"></i>&nbsp;&nbsp;Autorizar ${msgPlan}</h4>
                        <h4 class="element-center" ng-show="!isAuthorized"><i class="glyphicon glyphicon-remove"></i>&nbsp;&nbsp;Rechazar ${msgPlan}</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">
                        <input type="hidden" name="monPlanId" id="monPlanId" value="${monPlanId}" />
                        <input type="hidden" name="authorized" id="authorized" value="${isAuthorized}" />
                        <div class="row">
                            <div class="col-xs-12">
                                <div ng-class="(isAuthorized ? 'panel panel-primary' : 'panel panel-warning')">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n del caso
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Caso </div>
                                                        <div class="profile-info-value">
                                                            <span id="case">${caseId}&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Carpeta Judicial </div>

                                                        <div class="profile-info-value">
                                                            <span id="mpId">${mpId}&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Imputado </div>
                                                        <div class="profile-info-value">
                                                            <span id="fullName">${fullName}&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Estatus </div>
                                                        <div class="profile-info-value">
                                                            <span id="status">${status}&nbsp;</span>
                                                        </div>
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
                            <div class="col-xs-12">
                                <div ng-class="(isAuthorized ? 'panel panel-primary' : 'panel panel-warning')">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Observaciones sobre ${msgPlan}
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small"
                                                         ng-class="(isAuthorized ? 'header-color-green' : 'header-color-orange')">
                                                        <h6>Observaciones</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <textarea id="comments" name="comments" ng-model="m.comments" class="form-control" rows="8"></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.comments">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small"
                                                         ng-class="(isAuthorized ? 'header-color-green' : 'header-color-orange')">
                                                        <h6>Ingrese su contrase&ntilde;a para validar su usuario</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <input id="password" type="password" name="password" ng-model="m.password" class="form-control" rows="8"></textarea>
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

                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center">
                                {{MsgError}}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" id="btn-act-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel()">Cancelar</button>
                        <button class="btn btn-default btn-primary btn-sm" ng-show = "m.password && m.comments"
                              ng-click="submit('#FormCatId', '<c:url value='${urlToGo}' />')">
                              Aceptar
                        </button>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px" ng-controller="changeSupervisorController">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;Cambio de
                            supervisor</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">
                        <input type="hidden" name="monPlanId" id="monPlanId" value="${monPlanId}"/>

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n
                                        general del caso
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Caso</div>
                                                        <div class="profile-info-value">
                                                            <span id="case">${caseId}&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Carpeta Judicial</div>

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
                                                        <div class="profile-info-name"> Imputado</div>
                                                        <div class="profile-info-value">
                                                            <span id="fullName">${fullName}&nbsp;</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div class="profile-user-info profile-user-info-striped">
                                                    <div class="profile-info-row">
                                                        <div class="profile-info-name"> Estatus</div>
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
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Supervisores
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-6 col-xs-offset-3">
                                                <div class="element-center">
                                                    <label for="supervisor" class="control-label element-center">Elige el nuevo
                                                        supervisor para el caso:</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6 col-xs-offset-3">
                                                <div class="form-group">
                                                    <input type="hidden" ng-model="m.supervisorId" id="supervisorId" name="supervisorId" ng-update-hidden />
                                                    <select class="form-control element-center" ng-model="m.supervisor"
                                                            id="supervisor" name="supervisor"
                                                            ng-change="m.supervisorId = m.supervisor.id"
                                                            ng-init='lstUsers = ${lstUsers}'
                                                            ng-options="(u.name + ' (' + u.description) + ')' for u in lstUsers">
                                                        <option value="">-- Elige el nuevo supervisor para el caso --</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.supervisor">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small"
                                                         ng-class="(isAuthorized ? 'header-color-green' : 'header-color-orange')">
                                                        <h6>Observaciones sobre el cambio de supervisor</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <textarea id="comments" name="comments" ng-model="m.comments" class="form-control" rows="4"></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.comments">
                                            <div class="col-xs-6 col-xs-offset-3">
                                                <div class="form-group">
                                                    <input type="checkbox" ng-model="m.isAccepted" /> &iquest;Usted acepta cambiar de supervisor para este caso?
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.isAccepted">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-orange">
                                                        <h6>Ingrese su contrase&ntilde;a para validar su usuario</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <input id="password" type="password" name="password" ng-model="m.hasPassword" class="form-control" rows="8"></textarea>
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
                    <button class="btn btn-default btn-primary btn-sm" ng-disable="WaitFor==true"
                            ng-show="m.hasPassword"
                            ng-click="submit('#FormCatId', '<c:url value='/supervisorManager/activeMonitoringPlan/doChangeSupervisor.json' />')">
                        Cambiar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:900px"
             ng-init='lstCloseCause = ${lstCloseCause}; fillSelect("closeCause","lstCloseCause");'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-off"></i>&nbsp;&nbsp;Solicitud de
                            cierre de caso</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" ng-submit="submit('#FormCatId')" class="form-horizontal"
                          role="form">
                        <input type="hidden" name="caseId" id="caseId" value="${caseId}"/>
                        <input type="hidden" name="idCloseCause" id="idCloseCause" value="{{closeCause.id}}"/>

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Informaci&oacute;n
                                        del caso
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
                        <br/>

                        <div class="row">
                            <div class="col-xs-12">
                                <div class='panel panel-primary'>
                                    <div class="panel-heading">
                                        <span class="glyphicon glyphicon-map-marker"></span>&nbsp;&nbsp;Solicitu de de
                                        cierre
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-dark">
                                                        <h6>Motivo de cierre</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <div>
                                                                <select class="form-control element-center"
                                                                        ng-model="closeCause"
                                                                        ng-options="e.name for e in lstCloseCause">
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-dark">
                                                        <h6>Comentarios</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <textarea id="comments" name="comments"
                                                                      ng-model="m.comments" class="form-control"
                                                                      rows="8"></textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row" ng-show="m.comments">
                                            <div class="col-xs-8 col-xs-offset-2 widget-container-span">
                                                <div class="widget-box">
                                                    <div class="widget-header widget-header-small header-color-dark">
                                                        <h6>Ingrese su contrase&ntilde;a para validar su
                                                            usuario</h6>
                                                    </div>
                                                    <div class="widget-body">
                                                        <div class="widget-main padding-12">
                                                            <input id="password" type="password" name="password"
                                                                   ng-model="m.password" class="form-control"
                                                                   ng-enter-key for-element-id="btn-def-ck"
                                                                   rows="8"/>
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
                            <div ng-show="MsgError" class="alert alert-danger element-center" ng-bind-html="MsgError">
                                <%--{{MsgError}}--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" id="btn-act-footer">
                    <button class="btn btn-default btn-sm" ng-click="cancel()">Cancelar</button>
                    <button class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                            ng-show="m.password && m.comments" id="btn-def-ck"
                            ng-click="submit('#FormCatId', '<c:url value='/supervisor/caseActive/doRequestCloseCase.json' />')">
                        Solicitar
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


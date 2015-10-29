<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormRespId");
    });
</script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:60%" ng-controller="authRejFinishController"
             ng-init='minuteData=${minuteData}; responseData=${responseData};'>


            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Respuesta
                            de solicitud
                        </h4>
                    </div>
                </div>

                <div class="modal-body">

                    <div class="row">
                        <div class="col-xs-12">
                            <%@ include file="/WEB-INF/jsp/shared/minute/grlMinuteData.jsp" %>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel panel-default panel-primary">
                                <div class="panel-heading">
                                    <span class="icon-tasks"></span>&nbsp;&nbsp;Datos de la solicitud
                                </div>
                                <div class="panel-body">
                                    <div class="row" style="position: relative;">
                                        <div class="col-xs-12">
                                            <div class="row">
                                                <div class="col-xs-12 photoRow">
                                                    <div class="profile-user-info profile-user-info-striped">
                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">FECHA DE SOLICITUD
                                                            </div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.requestDate}}&nbsp;</span>
                                                            </div>
                                                        </div>


                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">SOLICITANTE</div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.requestUser}}&nbsp;</span>
                                                            </div>
                                                        </div>

                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">SOLICITUD</div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.requestType}}&nbsp;</span>
                                                            </div>
                                                        </div>

                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">COMENTARIO</div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.requestComment}}&nbsp;</span>
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

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel panel-default panel-primary">
                                <div class="panel-heading">
                                    <span class="icon-tasks"></span>&nbsp;&nbsp;Datos de la respuesta
                                </div>
                                <div class="panel-body">
                                    <div class="row" style="position: relative;">
                                        <div class="col-xs-12">
                                            <div class="row">
                                                <div class="col-xs-12 photoRow">
                                                    <div class="profile-user-info profile-user-info-striped">
                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">FECHA DE RESPUESTA
                                                            </div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.responseDate}}&nbsp;</span>
                                                            </div>
                                                        </div>


                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">ATENDI&Oacute;</div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.responseUser}}&nbsp;</span>
                                                            </div>
                                                        </div>

                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">RESOLUCI&Oacute;N</div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.responseType}}&nbsp;</span>
                                                            </div>
                                                        </div>

                                                        <div class="profile-info-row two-lines">
                                                            <div class="profile-info-name">COMENTARIO</div>
                                                            <div class="profile-info-value element-left">
                                                                <span>{{responseData.responseComment}}&nbsp;</span>
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
                </div>

                <div class="modal-footer">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="cancel();">
                          Aceptar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormRespId");
    });
</script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:60%" ng-controller="authRejFinishController"
             ng-init='agreementData=${agreementData}; requestData=${requestData};'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Autorizar/Rechazar
                            solicitud de conclusi&oacute;n de acuerdo
                        </h4>
                    </div>
                </div>

                <div class="modal-body">
                    <form id="FormRespId" name="FormRespId" class="form-horizontal" role="form">
                        <div class="row">
                            <div ng-show="MsgError&&MsgError!=''"
                                 class="alert alert-danger element-center"
                                 ng-bind-html="MsgError">
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${agreementId}"/>

                        <div class="row">
                            <div class="col-xs-12">
                                <%@ include file="/WEB-INF/jsp/shared/minute/agreement/grlAgreementData.jsp" %>
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
                                                                    <span>{{requestData.requestDate}}&nbsp;</span>
                                                                </div>
                                                            </div>


                                                            <div class="profile-info-row two-lines">
                                                                <div class="profile-info-name">SOLICITANTE</div>
                                                                <div class="profile-info-value element-left">
                                                                    <span>{{requestData.requestUser}}&nbsp;</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row two-lines">
                                                                <div class="profile-info-name">SOLICITUD</div>
                                                                <div class="profile-info-value element-left">
                                                                    <span>{{requestData.requestType}}&nbsp;</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row two-lines">
                                                                <div class="profile-info-name">ESTADO</div>
                                                                <div class="profile-info-value element-left">
                                                                    <span>{{requestData.status}}&nbsp;</span>
                                                                </div>
                                                            </div>

                                                            <div class="profile-info-row two-lines">
                                                                <div class="profile-info-name">COMENTARIO</div>
                                                                <div class="profile-info-value element-left">
                                                                    <span>{{requestData.requestComment}}&nbsp;</span>
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

                        <div class="row" ng-show="responseSaved==true">
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div ng-show="MsgSuccess&&MsgSuccess!=''"
                                             class="alert alert-success element-center">
                                            <p ng-bind-html="MsgSuccess"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" ng-show="responseSaved==false">
                            <div class="col-xs-12">
                                <div class="panel panel-default panel-primary">
                                    <div class="panel-heading">
                                        <span class="icon-tasks"></span>&nbsp;&nbsp;Respuesta de solicitud
                                    </div>
                                    <div class="panel-body">
                                        <div class="row" style="position: relative;">
                                            <div class="col-xs-12">
                                                <div class="row">
                                                    <div ng-show="MsgError&&MsgError!=''"
                                                         class="alert alert-danger element-center"
                                                         ng-bind-html="MsgError">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <label>Autorizar / Rechazar solicitud</label>
                                                    </div>
                                                    <div class="element-center">
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="authorize"
                                                              data-valmsg-replace="true"></span>
                                                    </div>
                                                    <br/>

                                                    <div class="radio col-xs-12">
                                                        <div class="col-xs-6 element-center">
                                                            <label>
                                                                <input class="ace" type="radio"
                                                                       ng-value="true" name="authorize"
                                                                       ng-model="authorize"
                                                                       data-val="true"
                                                                       data-val-required="Debe seleccionar un valor">
                                                                <span class="lbl">&nbsp;&nbsp;Autorizar</span>
                                                            </label>
                                                        </div>
                                                        <div class="col-xs-6 element-center">
                                                            <label>
                                                                <input class="ace" type="radio"
                                                                       ng-value="false" name="authorize"
                                                                       ng-model="authorize"
                                                                       data-val="true"
                                                                       data-val-required="Debe seleccionar un valor">
                                                                <span class="lbl">&nbsp;&nbsp;Rechazar</span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br/>

                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <label>Comentarios</label>
                                                        <br/>
                                                        <textarea class="input-xxlarge form-control limited"
                                                                  name="comments"
                                                                  ng-model="comments"
                                                                  maxlength="980" data-val="true"
                                                                  data-val-required="Comentarios es un campo requerido">
                                                        </textarea>
                                                          <span class="field-validation-valid"
                                                                data-valmsg-for="comments"
                                                                data-valmsg-replace="true"></span>
                                                    </div>
                                                </div>
                                                <br/>

                                                <div class="row">
                                                    <div class="col-xs-12"
                                                         ng-show="comments&&authorize!=undefined">
                                                        <label>Ingrese su contrase&ntilde;a para validar su
                                                            usuario</label>
                                                        <input name="password"
                                                               class="input-xxlarge form-control  form-control"
                                                               type="password" ng-enter-key
                                                               for-element-id="btn-def-ck"
                                                               ng-model="obs.password"
                                                               data-val-required="Contrase&ntilde;a es un campo requerido"
                                                               data-val="true">
                                                        <span class="field-validation-valid" data-valmsg-replace="true"
                                                              data-valmsg-for="password"></span>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer" ng-show="responseSaved==false">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" id="btn-def-ck" ng-disabled="WaitFor==true"
                          ng-click="submitResponse('#FormRespId','<c:url value="/shared/agreement/doAuthRejFinishRequest.json"/>');">
                          Guardar
                    </span>
                </div>

                <div class="modal-footer" ng-show="responseSaved==true">
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="cancel();">
                          Aceptar
                    </span>
                </div>

            </div>
        </div>
    </div>
</div>
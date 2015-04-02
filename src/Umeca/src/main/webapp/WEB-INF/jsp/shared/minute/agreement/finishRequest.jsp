<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormRequestId");
    });
</script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:45%" ng-controller="requestFinishController">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-lock"></i>&nbsp;&nbsp;Solicitar
                            concluir acuerdo</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormRequestId" name="FormRequestId" class="form-horizontal" role="form">
                        <div class="row">
                            <div ng-show="MsgError&&MsgError!=''"
                                 class="alert alert-danger element-center"
                                 ng-bind-html="MsgError">
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${agreementId}"/>

                        <div class="row">
                            <div class="col-xs-12">
                                <label>Comentario</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited" name="comments"
                                          ng-model="request.comments"
                                          maxlength="980" data-val="true"
                                          data-val-required="Observaciones es un campo requerido">
                                </textarea>
                                  <span class="field-validation-valid" data-valmsg-for="comments"
                                        data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-12">
                                <label>Estado del acuerdo</label>
                            </div>
                            <br/>

                            <div class="radio col-xs-12">
                                <div class="col-xs-6 element-center">
                                    <label>
                                        <input class="ace" type="radio"
                                               ng-value="true" name="isDone"
                                               ng-model="request.isDone"
                                               data-val="true"
                                               data-val-required="Debe seleccionar un valor">
                                        <span class="lbl">&nbsp;&nbsp;Realizado</span>
                                    </label>
                                </div>
                                <div class="col-xs-6 element-center">
                                    <label>
                                        <input class="ace" type="radio"
                                               ng-value="false" name="isDone"
                                               ng-model="request.isDone"
                                               data-val="true"
                                               data-val-required="Debe seleccionar un valor">
                                        <span class="lbl">&nbsp;&nbsp;No realizado</span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <div class="row" ng-show="request.comments&&request.isDone!=undefined">
                            <div class="col-xs-12">
                                <label>Ingrese su contrase&ntilde;a para validar su usuario</label>
                                <input name="password" class="input-xxlarge form-control  form-control"
                                       type="password" ng-enter-key for-element-id="btn-def-ck"
                                       ng-model="obs.password"
                                       data-val-required="Contrase&ntilde;a es un campo requerido"
                                       data-val="true">
                                    <span class="field-validation-valid" data-valmsg-replace="true"
                                          data-valmsg-for="password"></span>
                            </div>
                        </div>
                        <br/>
                    </form>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" id="btn-def-ck" ng-disabled="WaitFor==true"
                          ng-click="submitRequest('#FormRequestId','<c:url value="/shared/agreement/doCloseRequestAgreement.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
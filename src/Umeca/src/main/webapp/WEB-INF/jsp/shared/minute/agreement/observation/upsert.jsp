<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormObsId");
    });
</script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:45%" ng-controller="obsController">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Registrar
                            observaci&oacute;n
                        </h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormObsId" name="FormObsId" class="form-horizontal" role="form">
                        <div class="row">
                            <div ng-show="MsgError&&MsgError!=''"
                                 class="alert alert-danger element-center"
                                 ng-bind-html="MsgError">
                            </div>
                        </div>
                        <input type="hidden" name="agreementId" value="${agreementId}"/>

                        <div class="row">
                            <div class="col-xs-12">
                                <label>Observaciones</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited" name="comment"
                                          ng-model="obs.comment"
                                          maxlength="980" data-val="true"
                                          data-val-required="Observaciones es un campo requerido">
                                </textarea>
                                  <span class="field-validation-valid" data-valmsg-for="comment"
                                        data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-12"
                                 ng-show="obs.comment">
                                <label>Ingrese su contrase&ntilde;a para validar su usuario</label>
                                <input name="password" class="input-xxlarge form-control  form-control"
                                       type="password" ng-enter-key for-element-id="btn-def-ck"
                                       ng-model="obs.password"
                                       data-val-required="Contrase&ntilde;a es un campo requerido" data-val="true">
                                    <span class="field-validation-valid" data-valmsg-replace="true"
                                          data-valmsg-for="password"></span>
                            </div>
                        </div>
                        <br/>
                    </form>
                    <br/>

                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" id="btn-def-ck" ng-disabled="WaitFor==true"
                          ng-click="submitObs('#FormObsId','<c:url value="/shared/observation/doUpsertObservation.json"/>');">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>
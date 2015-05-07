<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormAgreementId");
    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="col-xs-10 col-xs-offset-1">
            <div class="modal-dialog" style="width:65%" ng-controller="agreementController"
                 ng-init='agreement=${agreement}; isRH=${isRH};'>
                <div class="modal-content">

                    <div class="modal-header">
                        <div class="alert alert-info ">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Registrar
                                acuerdo
                            </h4>
                        </div>
                    </div>
                    <div class="modal-body">


                        <form id="FormAgreementId" name="FormAgreementId" class="form-horizontal" role="form">
                            <div class="row">
                                <div ng-show="MsgError&&MsgError!=''"
                                     class="alert alert-danger element-center"
                                     ng-bind-html="MsgError">
                                </div>
                            </div>
                            <input type="hidden" name="minuteId" value="${minuteId}"/>

                            <div class="row">
                                <div class="col-xs-6">
                                    <label for="title">Acuerdo:</label>
                                    <input class="form-control" data-val="true"
                                           ng-disabled="isRH==false||agreement.isFinished==true"
                                           data-val-required="Acuerdo es un campo requerido"
                                           id="title" name="title" ng-model="agreement.title"
                                           type="text"/>
                                <span class="field-validation-valid" data-valmsg-for="title"
                                      data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-6">
                                    <label>Tema(s)</label>
                                    <br/>
                                <textarea class="input-xxlarge form-control limited" name="theme"
                                          ng-disabled="isRH==false||agreement.isFinished==true"
                                          ng-model="agreement.theme"
                                          maxlength="980" data-val="true"
                                          data-val-required="Tema(s) es un campo requerido">
                                </textarea>
                                  <span class="field-validation-valid" data-valmsg-for="theme"
                                        data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-xs-6">
                                    <label for="agreementDate">Fecha:</label>

                                    <div class="input-group">
                                        <input id="agreementDate" name="agreementDate"
                                               ng-disabled="isRH==false||agreement.isFinished==true"
                                               ng-model="agreement.agreementDate"
                                               class="form-control date-picker"
                                               id="id-date-picker-1" type="text"
                                               data-date-format="yyyy/mm/dd" data-val="true"
                                               readonly
                                               data-val-required="Fecha es un campo requerido"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                    </div>
                                <span class="field-validation-valid" data-valmsg-for="agreementDate"
                                      data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-6">
                                    <label>Comentarios</label>
                                    <br/>
                                <textarea class="input-xxlarge form-control limited" name="comments"
                                          ng-disabled="isRH==false||agreement.isFinished==true"
                                          ng-model="agreement.comments"
                                          maxlength="980" data-val="true"
                                          data-val-required="Comentarios es un campo requerido">
                                </textarea>
                                  <span class="field-validation-valid" data-valmsg-for="comments"
                                        data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-xs-6">
                                    <label>&Aacute;rea</label>
                                    <br/>
                                    <select class="form-control element-center"
                                            ng-disabled="isRH==false||agreement.isFinished==true"
                                            ng-model="agreement.area"
                                            ng-options="e.name for e in lstArea"
                                            ng-init='lstArea = ${lstArea};'></select>
                                    <input type="hidden" name="areaId" value="{{agreement.area.id}}"/>
                                </div>
                                <div class="col-xs-6" ng-show="agreement.area.specification==true">
                                    <label>Especifique</label>
                                    <br/>
                                <textarea class="input-xxlarge form-control limited" name="specArea"
                                          ng-model="agreement.specArea"
                                          ng-disabled="isRH==false||agreement.isFinished==true"
                                          maxlength="980" data-val="true"
                                          data-val-required="Especifique es un campo requerido">
                                </textarea>
                                <span class="field-validation-valid" data-valmsg-for="specArea"
                                      data-valmsg-replace="true"></span>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="col-xs-12"
                                     ng-show="agreement.title&&agreement.theme&&agreement.agreementDate&&agreement.comments&&isRH==true">
                                    <label>Ingrese su contrase&ntilde;a para validar su usuario</label>
                                    <input name="password" class="input-xxlarge form-control  form-control"
                                           type="password" ng-enter-key for-element-id="btn-def-ck"
                                           ng-model="agreement.password"
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
                          ng-if="isRH==true"
                          ng-click="submitAgreement('#FormAgreementId','<c:url value="/shared/agreement/doUpsertAgreement.json"/>');">
                          Guardar
                    </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>
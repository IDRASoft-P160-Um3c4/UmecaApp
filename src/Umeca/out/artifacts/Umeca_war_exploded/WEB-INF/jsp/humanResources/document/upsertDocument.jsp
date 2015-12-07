<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormCatId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:500px" ng-controller="documentController">
            <div class="modal-content" ng-init='document = ${document}'>
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="glyphicon glyphicon-comment "></i>&nbsp;&nbsp;Informaci&oacute;n de documento</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormCatId" name="FormCatId" class="form-horizontal" role="form">
                        <br/>
                        <input type="hidden" name="id" value="{{document.id}}"/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Fecha:
                            </div>
                            <div class="col-xs-8">
                                <div class="input-group">
                                    <input class="form-control date-picker" type="text"
                                           data-date-format="yyyy/mm/dd"
                                           data-val="true"
                                           data-val-required="Fecha es un campo requerido"
                                           id="documentDate" name="documentDate" ng-model="document.documentDate"/>
											<span class="input-group-addon">
														<i class="icon-calendar bigger-110"></i>
											</span>
                                </div>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                <span class="field-validation-valid" data-valmsg-for="documentDate"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                N&uacute;mero de oficio:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener m&aacute;ximo 50 caracteres"
                                       data-val-required="El nombre es un campo requerido"
                                       data-val-length-max="50"
                                       id="numberDocument" name="numberDocument" ng-model="document.numberDocument"
                                       type="text"/>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                <span class="field-validation-valid" data-valmsg-for="numberDocument"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Remitente:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener m&aacute;ximo 50 caracteres"
                                       data-val-length-max="50"
                                       data-val-required="Remitente es un campo requerido"
                                       id="sender" name="sender"
                                       type="text" ng-model="document.sender"/>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                <span class="field-validation-valid" data-valmsg-for="sender"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Destinatario:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener m&aacute;ximo 50 caracteres"
                                       data-val-length-max="50"
                                       data-val-required="Destinatario es un campo requerido"
                                       id="receiver" name="receiver"
                                       type="text" ng-model="document.receiver"/>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                <span class="field-validation-valid" data-valmsg-for="receiver"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Causa penal:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener m&aacute;ximo 50 caracteres"
                                       data-val-length-max="50"
                                       data-val-required="Causa penal es un campo requerido"
                                       id="criminalCause" name="criminalCause"
                                       type="text" ng-model="document.criminalCause"/>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                <span class="field-validation-valid" data-valmsg-for="criminalCause"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Asunto:
                            </div>
                            <div class="col-xs-8">
                                <textarea class="input-xxlarge form-control limited" name="subject"
                                          ng-model="document.subject"
                                          maxlength="980" data-val="true"
                                          data-val-required="Asunto es un campo requerido">
                                </textarea>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                    <span class="field-validation-valid" data-valmsg-for="subject"
                                          data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Turnado a:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener m&aacute;ximo 50 caracteres"
                                       data-val-length-max="50"
                                       data-val-required="Turnado a es un campo requerido"
                                       id="turnedOver" name="turnedOver"
                                       type="text" ng-model="document.turnedOver"/>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                <span class="field-validation-valid" data-valmsg-for="turnedOver"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="col-xs-4 element-left">
                                Acci&oacute;n final:
                            </div>
                            <div class="col-xs-8">
                                <input class="form-control" data-val="true"
                                       data-val-length="Debe tener m&aacute;ximo 50 caracteres"
                                       data-val-length-max="50"
                                       data-val-required="Acci&oacute;n final es un campo requerido"
                                       id="finalAction" name="finalAction"
                                       type="text" ng-model="document.finalAction"/>
                            </div>
                            <div class="col-xs-8 col-xs-offset-4">
                                <span class="field-validation-valid" data-valmsg-for="finalAction"
                                      data-valmsg-replace="true"></span>
                            </div>
                        </div>

                        <br/>
                    </form>
                    <br/>

                    <div class="row">
                        <div class="col-xs-12">
                            <div ng-show="MsgError" class="alert alert-danger element-center" ng-bind-html="MsgError">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitDocument('#FormCatId','<c:url value="/humanResources/document/doUpsertDocument.json"/>');">
                          Guardar
                    </span>
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
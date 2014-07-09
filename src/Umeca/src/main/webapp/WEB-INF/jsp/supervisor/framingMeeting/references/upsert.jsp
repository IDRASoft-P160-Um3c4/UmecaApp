<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormReferencesId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="referencesController" ng-cloak>

        <div class="modal-dialog" style="width:800px" ng-init='refe=${reference}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Referencias personales</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormReferencesId" name="FormReferencesId" class="form-horizontal" role="form">
                        <input type="hidden" name="id" value="{{refe.id}}">
                        <input type="hidden" name="personType" value="REFERENCE">
                        <br/>

                        <div class="row">

                            <div class="col-xs-8">
                                <label>Nombre</label>
                                <br/>
                                <input id="name" ng-model="hm.name" name="name" type="text"
                                       class="input-xxlarge" data-val="true"
                                       data-val-required="Nombre es un campo requerido"/>
                                <br/>
                                        <span class="field-validation-valid" data-valmsg-for="name"
                                              data-valmsg-replace="true"></span>
                            </div>
                            <div class="col-xs-4">
                                <label>Teléfono</label>
                                <br/>
                                <input id="phone" ng-model="refe.phone" name="phone" type="text"
                                       class="input-xxlarge" data-val="true"
                                       data-val-required="Teléfono es un campo requerido"/>
                                <br/>
                                        <span class="field-validation-valid" data-valmsg-for="age"
                                              data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-6">
                                <label>Parentesco</label>
                                <br/>
                                <input id="relationship" ng-model="hm.relationship" name="relationship" type="text"
                                       class="input-xxlarge" data-val="true"
                                       data-val-required="Parentesco es un campo requerido"/>
                                <br/>
                                        <span class="field-validation-valid" data-valmsg-for="relationship"
                                              data-valmsg-replace="true"></span>
                            </div>
                            <div class="col-xs-6">
                                <label>Dirección</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited" name="address"
                                          ng-model="refe.address"
                                          maxlength="980" data-val="true"
                                          data-val-required="Dirección adicionales es un campo requerido">
                                    {{refe.address}}</textarea>
                                <span class="field-validation-valid" data-valmsg-for="address"
                                      data-valmsg-replace="true"></span>
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
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" ng-click="cancel()">
                        Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitIdCaseParam('#FormReferencesId', '<c:url value="/supervisor/framingMeeting/references/doReferenceUpsert.json?idCase="/>',refe.idCase);">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>


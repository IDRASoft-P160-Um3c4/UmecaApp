<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormReferenceId");
    });

    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
</script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="upsertController" ng-cloak>
        <div class="modal-dialog" style="width:800px" ng-controller="empReferenceController"
             ng-init='reference=${reference}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Agregar referencia</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormReferenceId" name="FormReferenceId" class="form-horizontal" role="form">
                        <input type="hidden" name="idEmployee" value="{{reference.idEmployee}}">
                        <input type="hidden" name="id" value="{{reference.id}}">
                        <input type="hidden" name="isTraining" value="false">

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header">Agregar referencia</div>
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-xs-12">

                                                <div class="col-xs-12">
                                                    <br/>

                                                    <div ng-show="MsgError!=''"
                                                         class="alert alert-danger element-center"
                                                         ng-bind-html="MsgError">
                                                    </div>
                                                </div>
                                                <br/>

                                                <div id="divReference">
                                                    <div class="col-xs-12">
                                                        <label>Nombre</label>
                                                        <br/>
                                                        <input id="name" ng-model="reference.name"
                                                               name="name"
                                                               type="text" style=" width: 100% !important"
                                                               class="input-xxlarge" data-val="true"
                                                               data-val-required="Nombre es un campo requerido"/>
                                                        <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="name"
                                                                  data-valmsg-replace="true"></span>

                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Edad</label>
                                                            <br/>
                                                            <input id="age" ng-model="reference.age"
                                                                   name="age"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Edad es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="age"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>

                                                        <div class="col-xs-6">
                                                            <label>Tel&eacute;fono</label>
                                                            <br/>
                                                            <input id="phone" ng-model="reference.phone"
                                                                   name="phone"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Tel&eacute;fono es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="phone"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>

                                                    </div>
                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Relaci&oacute;n</label>
                                                            <br/>
                                                            <select class="form-control element-center"
                                                                    ng-model="reference.relation"
                                                                    ng-init='lstRelationship = ${lstRelationship}'
                                                                    ng-change="cleanSpecs();"
                                                                    ng-options="e.name for e in lstRelationship"></select>
                                                            <input type="hidden" name="idCourseType"
                                                                   value="{{reference.relation.id}}"/>
                                                        </div>
                                                        <div class="col-xs-6"
                                                             ng-show="reference.relation.specification==true">
                                                            <label>Especifique relaci&oacute;n</label>
                                                            <br/>
                                                            <input id="specRelationship"
                                                                   ng-model="reference.specRelationship"
                                                                   name="specRelationship"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Especifique relaci&oacute;n es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="specRelationship"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-12">
                                                        <br/>

                                                        <div class="col-xs-6">
                                                            <label>Tiempo de conocerlo</label>
                                                            <br/>
                                                            <input id="timeAgo" ng-model="reference.timeAgo"
                                                                   name="timeAgo"
                                                                   type="text" style=" width: 100% !important"
                                                                   class="input-xxlarge" data-val="true"
                                                                   data-val-required="Tiempo de conocerlo es un campo requerido"/>
                                                            <br/>
                                                            <span class="field-validation-valid"
                                                                  data-valmsg-for="timeAgo"
                                                                  data-valmsg-replace="true"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        archivo
                                                        <br/>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                </div>
                            </div>
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
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submitReference('#FormReferenceId', '<c:url value="/humanResources/digitalRecord/doUpsertReference.json"/>');">
                          Guardar
                    </span>
            </div>
        </div>
    </div>
</div>
</div>
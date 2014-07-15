<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormHousemateId");
    });
</script>

<div>
    <div id="dlgUpModalId" class="modal fade" ng-controller="housemateController" ng-cloak>

        <div class="modal-dialog" style="width:800px" ng-init='hm=${housemate}'>
            <div class="modal-content">
                <div class="modal-header">
                    <div class="alert alert-info ">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Personas que viven con el
                            imputado</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <form id="FormHousemateId" name="FormHousemateId" class="form-horizontal" role="form">
                        <input type="hidden" name="id" value="{{hm.id}}">
                        <input type="hidden" name="personType" value="HOUSEMATE">
                        <input type="hidden" name="relationshipId" value="{{hm.relationship.id}}">

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
                                <label>Edad</label>
                                <br/>
                                <input id="age" ng-model="hm.age" name="age" type="text"
                                       class="input-xxlarge" data-val="true"
                                       data-val-required="Edad es un campo requerido"/>
                                <br/>
                                        <span class="field-validation-valid" data-valmsg-for="age"
                                              data-valmsg-replace="true"></span>
                            </div>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-4">

                                <label>Parentesco:</label>
                                <br/>
                                <select class="form-control element-center" ng-model="hm.relationship"
                                        ng-options="e.name for e in lstRelationship"
                                        ng-init='lstRelationship = ${lstRelationship};'></select>
                            </div>
                            <div class="col-xs-8">
                                <label>Ocupación</label>
                                <br/>
                                <input id="occupation" ng-model="hm.occupation" name="occupation" type="text"
                                       class="input-xxlarge" data-val="true"
                                       data-val-required="Ocupación es un campo requerido"/>
                                <br/>
                                        <span class="field-validation-valid" data-valmsg-for="occupation"
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
                          ng-click="submitIdCaseParam('#FormHousemateId', '<c:url value="/supervisor/framingMeeting/references/doReferenceUpsert.json?idCase="/>',hm.idCase);">
                          Guardar
                    </span>
                </div>
            </div>
        </div>
    </div>
</div>


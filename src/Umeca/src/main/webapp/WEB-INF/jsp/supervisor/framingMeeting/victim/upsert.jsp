<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    $(document).ready(function () {
        window.showModalFormDlg("#dlgUpModalId", "#FormVictimId");
    });
</script>

<div>
<div id="dlgUpModalId" class="modal fade" ng-controller="victimController" ng-cloak>

<div class="modal-dialog" style="width:800px" ng-init='victim=${victim}'>
<div class="modal-content">
<div class="modal-header">
    <div class="alert alert-info ">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Victimas y testigos</h4>
    </div>
</div>
<div class="modal-body">
    <form id="FormVictimId" class="form-horizontal" role="form">
        <input type="hidden" name="id" value="{{victim.id}}">
        <input type="hidden" name="relationshipId" value="{{victim.relationship.id}}">
        <input type="hidden" name="isAccompaniment" value="{{victim.isAccompaniment}}">

        <br/>

        <div class="row">
            <div class="widget-box">
                <div class="widget-header">Datos generales</div>
                <div class="widget-body">
                    <div class="row">
                        <div class="col-xs-12">

                            <div class="col-xs-12 element-center">
                                <label>&iquest;Cuenta con informaci&oacute; de alguna v&iacute;ctima o
                                    testigo?</label>
                                <br/>
                      <span class="field-validation-valid" data-valmsg-for="hasVictimWitnessInfo"
                            data-valmsg-replace="true"></span>
                                <br/>

                                <div class="radio">
                                    <label>
                                        <input name="hasVictimWitnessInfo" class="ace" type="radio" ng-value="true"
                                               ng-model="victim.hasVictimWitnessInfo"
                                               ng-checked="victim.personType==true" data-val="true"
                                               data-val-required="Debe seleccionar un valor"
                                               ng-change="existVictim(victim.hasVictimWitnessInfo);">
                                        <span class="lbl">&nbsp;&nbsp;S&iacute;</span>
                                    </label>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <label>
                                        <input name="hasVictimWitnessInfo" class="ace" type="radio"
                                               ng-value="false"
                                               ng-model="victim.hasVictimWitnessInfo"
                                               ng-checked="victim.hasVictimWitnessInfo==false" data-val="true"
                                               data-val-required="Debe seleccionar un valor"
                                               ng-change="existVictim(victim.hasVictimWitnessInfo);">
                                        <span class="lbl">&nbsp;&nbsp;No</span>
                                    </label>
                                </div>
                            </div>
                            <div id="divVictim">
                                <div class="col-xs-12">
                                    <br/>
                                    <label>Seleccione el tipo de persona</label>
                                    <br/>
                      <span class="field-validation-valid" data-valmsg-for="personType"
                            data-valmsg-replace="true"></span>
                                    <br/>

                                    <div class="radio">
                                        <label>
                                            <input name="personType" class="ace" type="radio" value="VICTIM"
                                                   ng-model="victim.personType"
                                                   ng-checked="victim.personType=='VICTIM'" data-val="true"
                                                   data-val-required="Debe seleccionar un valor">
                                            <span class="lbl">&nbsp;&nbsp;V&iacute;ctima</span>
                                        </label>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <label>
                                            <input name="personType" class="ace" type="radio"
                                                   value="WITNESS"
                                                   ng-model="victim.personType"
                                                   ng-checked="victim.personType=='WITNESS'" data-val="true"
                                                   data-val-required="Debe seleccionar un valor">
                                            <span class="lbl">&nbsp;&nbsp;Testigo</span>
                                        </label>

                                    </div>
                                    <br/>
                                </div>
                                <br/>

                                <div class="col-xs-7">
                                    <label>Nombre</label>
                                    <br/>
                                    <input id="name" ng-model="victim.name" name="name" type="text"
                                           class="input-xxlarge" data-val="true"
                                           data-val-required="Nombre es un campo requerido"/>
                                    <br/>
                                        <span class="field-validation-valid" data-valmsg-for="name"
                                              data-valmsg-replace="true"></span>
                                </div>
                                <div class="col-xs-4">
                                    <label>Edad</label>
                                    <br/>
                                    <input id="age" ng-model="victim.age" name="age" type="text"
                                           class="input-xxlarge" data-val="true"
                                           data-val-required="Edad es un campo requerido"/>
                                    <br/>
                                        <span class="field-validation-valid" data-valmsg-for="age"
                                              data-valmsg-replace="true"></span>
                                </div>


                                <div class="col-xs-12">
                                    <br/>

                                    <div class="col-xs-6">

                                        <label>Parentesco con el imputado:</label>
                                        <br/>
                                        <select class="form-control element-center"
                                                ng-model="victim.relationship"
                                                ng-options="e.name for e in lstRelationship"
                                                ng-init='lstRelationship = ${lstRelationship};'></select>
                                    </div>
                                    <%--<div class="col-xs-6" ng-show="victim.relationship.id==8">--%>
                                    <%--<label>Tiempo de conocerlo</label>--%>
                                    <%--<br/>--%>
                                    <%--<input id="timeAgo" ng-model="hm.timeAgo" name="timeAgo" type="text"--%>
                                    <%--class="input-xxlarge" data-val="true"--%>
                                    <%--data-val-required="Tiempo de conocerlo es un campo requerido"/>--%>
                                    <%--<br/>--%>
                                    <%--<span class="field-validation-valid" data-valmsg-for="timeAgo"--%>
                                    <%--data-valmsg-replace="true"></span>--%>
                                    <%--</div>--%>
                                    <div class="col-xs-5 col-xs-offset-1">
                                        <label>Tel&eacute;fono(s)</label>
                                        <br/>
                                        <textarea class="input-xxlarge form-control limited" name="phone"
                                                  ng-model="victim.phone"
                                                  maxlength="980" data-val="true"
                                                  data-val-required="Tel&eacute;fono es un campo requerido">
                                        </textarea>
                    <span class="field-validation-valid" data-valmsg-for="phone"
                          data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <br/>

                                <div class="col-xs-12" ng-show="victim.relationship.specification == true">
                                    <br/>

                                    <div class="col-xs-6">
                                        Especif&iacute;que parentesco:
                                        <br/>
                                        <input class="form-control" data-val="true"
                                               data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                                               data-val-length-max="255" data-val-length-min="2"
                                               data-val-required="La especificaci&oacute;n es un campo requerido"
                                               type="text" ng-model="victim.specificationRelationship"
                                               id="specificationRelationship" name="specificationRelationship">
                                        <br/>

                                        <div class="col-xs-9 col-xs-offset-3">
                            <span class="field-validation-valid" data-valmsg-for="specificationRelationship"
                                  data-valmsg-replace="true"></span>
                                        </div>
                                    </div>

                                </div>
                                <br/>

                                <div class="col-xs-12">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <div class="col-xs-11">
                                                <div class="col-xs-12">
                                                    <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>

        <div id="divHiddenVictim">

            <input type="hidden" name="name" value="{{victim.name}}">
            <input type="hidden" name="age" value="{{victim.age}}">
            <input type="hidden" name="phone" value="{{victim.phone}}">
            <input type="hidden" name="personType" value="{{victim.personType}}">

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
                          ng-click="submitIdCaseParam('#FormVictimId', '<c:url value="/supervisor/framingMeeting/victim/doVictimUpsert.json?idCase="/>',victim.idCase);">
                          Guardar
                    </span>
</div>
</div>
</div>
</div>
</div>


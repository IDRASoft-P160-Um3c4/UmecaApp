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
<input type="hidden" name="relationshipId" value="{{refe.relationship.id}}">
<input type="hidden" name="academicLvlId" value="{{refe.relationship.id}}">
<br/>

<div class="widget-box">
    <div class="widget-header">Datos generales</div>
    <div class="widget-body">
        <div class="row">
            <br/>

            <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-6">
                            <label>Nombre</label>
                            <br/>
                            <input id="name" ng-model="refe.name" name="name" type="text"
                                   class="input-xxlarge" data-val="true"
                                   data-val-required="Nombre es un campo requerido"/>
                            <br/>
                                                    <span class="field-validation-valid" data-valmsg-for="name"
                                                          data-valmsg-replace="true"></span>
                        </div>
                        <div class="col-xs-6">
                            <label>Tel&eacute;fono</label>
                            <br/>
                            <textarea class="input-xxlarge form-control limited"
                                      id="phone" ng-model="refe.phone" name="phone"
                                      maxlength="980" data-val="true"
                                      data-val-required="Tel&eacute;fono  es un campo requerido">
                            </textarea>
                                <span class="field-validation-valid"
                                      data-valmsg-for="phone"
                                      data-valmsg-replace="true"></span>

                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-6">

                            <label>Relaci&oacute;n con el imputado:</label>&nbsp;&nbsp;<label class="info-example">(En
                            caso de no tener relaci&oacute;n explique por cu&aacute;nto tiempo lo conoce)</label>
                            <br/>
                            <select class="form-control element-center"
                                    ng-model="refe.relationship"
                                    id="relationshipId" name="relationshipId"
                                    ng-options="e.name for e in lstRelationship"
                                    ng-init='lstRelationship = ${lstRelationship};'></select>
                        </div>
                        <div class="col-xs-6" ng-show="refe.relationship.id==8">
                            <label>Tiempo de conocerlo</label><br/>
                            <br/>
                            <input id="timeAgo" ng-model="refe.timeAgo" name="timeAgo" type="text"
                                   class="input-xxlarge" data-val="true"
                                   data-val-required="Tiempo de conocerlo es un campo requerido"/>
                            <br/>
                                                    <span class="field-validation-valid" data-valmsg-for="timeAgo"
                                                          data-valmsg-replace="true"></span>

                        </div>
                    </div>
                </div>
                <br/>

                <div class="row" ng-show="refe.isAccompaniment==false">
                    <div class="col-xs-12">
                        <div class="col-xs-6">
                            <label>Direcci&oacute;n</label>
                            <br/>
                            <textarea class="input-xxlarge form-control limited"
                                      id="address"
                                      name="address"
                                      ng-model="refe.address"
                                      maxlength="980" data-val="true"
                                      data-val-required="Direcci&oacute;n es un campo requerido">
                            </textarea>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="address"
                                                              data-valmsg-replace="true"></span>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row" ng-show="refe.relationship.specification == true">
                    <div class="col-xs-6">
                        <div class="col-xs-4 element-left">
                            Especif&iacute;que<br/>relaci&oacute;n:
                        </div>
                        <div class="col-xs-8">
                            <input class="form-control" data-val="true"
                                   data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                                   data-val-length-max="255" data-val-length-min="2"
                                   data-val-required="La especificaci&oacute;n es un campo requerido"
                                   type="text" ng-model="refe.specificationRelationship"
                                   id="specificationRelationship" name="specificationRelationship">
                        </div>
                        <div class="col-xs-9 col-xs-offset-3">
                            <span class="field-validation-valid" data-valmsg-for="specificationRelationship"
                                  data-valmsg-replace="true"></span>
                        </div>
                    </div>

                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-8">
                        <div class="checkbox">
                            <br/>
                            <label>
                                <input class="ace"
                                       type="checkbox"
                                       name="isAccompaniment"
                                       ng-model="refe.isAccompaniment"
                                       ng-checked="refe.isAccompaniment==true">
                                        <span class="lbl col-xs-10">&nbsp;&nbsp;&iquest;&Eacute;sta persona lo acompa&ntilde;ar&aacute;
                                            durante el proceso?</span>
                            </label>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
        <br/>
    </div>
</div>
<br/>

<div class="widget-box" ng-show="refe.isAccompaniment==true">
    <div class="widget-header">Datos adicionales&nbsp;&nbsp;<label class="info-example">(Corroborar que los datos
        proporcionados
        en la entrevista de evaluaci&oacute;n son los m&aacute;s actualizados)</label></div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <br/>

                    <div class="col-xs-12">
                        <div class="col-xs-5">
                            <div>
                                <label>G&eacute;nero</label>
                            </div>

                            <div class="control-group col-xs-offset-1">
                    <span class="field-validation-valid" data-valmsg-for="gender"
                          data-valmsg-replace="true"></span>

                                <div class="radio">
                                    <label>
                                        <input type="radio" class="ace" name="gender"
                                               ng-model="refe.gender" value="1" data-val="true"
                                               data-val-required="Debe seleccionar una opci&oacute;n"/>
                                        <span class="lbl">&nbsp;&nbsp;Femenino</span>
                                    </label>
                                    <br/>
                                    <label>
                                        <input type="radio" class="ace" name="gender"
                                               ng-model="refe.gender" value="2"/>
                                        <span class="lbl">&nbsp;&nbsp;Masculino</span>
                                    </label>
                                </div>
                                <br/>
                            </div>
                        </div>
                        <div class="col-xs-7">
                            <label>Edad</label>
                            <br/>
                            <input id="age" ng-model="refe.age" name="age" type="text"
                                   class="input-large" data-val="true"
                                   data-val-required="Edad es un campo requerido"/>
                            <br/>
                                        <span class="field-validation-valid" data-valmsg-for="age"
                                              data-valmsg-replace="true"></span>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-5">
                            <label>Ocupaci&oacute;n</label>
                            <br/>
                            <input id="occupation" ng-model="refe.occupation" name="occupation"
                                   type="text"
                                   class="input-xxlarge" data-val="true"
                                   data-val-required="Ocupaci&oacute;n es un campo requerido"/>
                            <br/>
                                        <span class="field-validation-valid" data-valmsg-for="occupation"
                                              data-valmsg-replace="true"></span>
                        </div>
                        <div class="col-xs-7">
                            <div>
                                <label>Lugar de ocupaci&oacute;n</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited"
                                          id="occupationPlace"
                                          name="occupationPlace"
                                          ng-model="refe.occupationPlace"
                                          maxlength="980" data-val="true"
                                          data-val-required="Lugar de ocupaci&oacute;n es un campo requerido">
                                </textarea>
                                                        <span class="field-validation-valid"
                                                              data-valmsg-for="occupationPlace"
                                                              data-valmsg-replace="true"></span>
                            </div>
                        </div>

                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-10">
                        <div class="col-xs-11">
                            <div class="col-xs-12">
                                <%@ include file="/WEB-INF/jsp/address/index.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
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


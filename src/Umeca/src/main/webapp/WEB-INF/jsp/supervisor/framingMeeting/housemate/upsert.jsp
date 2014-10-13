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
        <h4 class="element-center"><i class="icon-group "></i>&nbsp;&nbsp;Personas con las que vive el
            imputado</h4>
    </div>
</div>
<div class="modal-body">
<form id="FormHousemateId" name="FormHousemateId" class="form-horizontal" role="form">
<input type="hidden" name="id" value="{{hm.id}}">
<input type="hidden" name="personType" value="HOUSEMATE">
<input type="hidden" name="relationshipId" value="{{hm.relationship.id}}">
<input type="hidden" name="academicLvlId" value="{{hm.academicLvl.id}}">

<br/>

<div class="row">
    <div class="widget-box">
        <div class="widget-header">Datos generales&nbsp;&nbsp;<label class="info-example">(Corroborar que los
            datos proporcionados en la entrevista de evaluaci&oacute;n son los m&aacute;s actualizados)</label>
        </div>
        <div class="widget-body">
            <div class="row">
                <div class="col-xs-12">
                    <br/>

                    <div class="col-xs-7">
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

                <div class="col-xs-12">
                    <br/>

                    <div class="col-xs-6">

                        <label>Parentesco con el imputado:</label><label class="info-example">(En
                        caso de no tener parentesco explique por cu&aacute;nto tiempo lo conoce)</label>
                        <br/>
                        <select class="form-control element-center" ng-model="hm.relationship"
                                ng-options="e.name for e in lstRelationship"
                                ng-init='lstRelationship = ${lstRelationship};'></select>
                    </div>
                    <div class="col-xs-6" ng-show="hm.relationship.id==8">
                        <label>Tiempo de conocerlo</label>
                        <br/>
                        <input id="timeAgo" ng-model="hm.timeAgo" name="timeAgo" type="text"
                               class="input-xxlarge" data-val="true"
                               data-val-required="Tiempo de conocerlo es un campo requerido"/>
                        <br/>
                                        <span class="field-validation-valid" data-valmsg-for="timeAgo"
                                              data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>

                <div class="col-xs-12" ng-show="hm.relationship.specification == true">
                    <br/>
                    <div class="col-xs-6">
                            Especif&iacute;que parentesco:
                      <br/>
                            <input class="form-control" data-val="true"
                                   data-val-length="Debe tener al menos 2 y m&aacute;ximo 255 caracteres"
                                   data-val-length-max="255" data-val-length-min="2"
                                   data-val-required="La especificaci&oacute;n es un campo requerido"
                                   type="text" ng-model="hm.specificationRelationship"
                                   id="specificationRelationship" name="specificationRelationship">
                        <br/>
                        <div class="col-xs-9 col-xs-offset-3">
                            <span class="field-validation-valid" data-valmsg-for="specificationRelationship" data-valmsg-replace="true"></span>
                        </div>
                    </div>

                </div>
                <br/>
                <div class="col-xs-12">
                    <div class="col-xs-11">
                        <label>Ocupaci&oacute;n</label>
                        <br/>
                        <input id="occupation" ng-model="hm.occupation" name="occupation" type="text"
                               class="input-xxlarge" data-val="true"
                               data-val-required="Ocupaci&oacute;n es un campo requerido"/>
                        <br/>
                                        <span class="field-validation-valid" data-valmsg-for="occupation"
                                              data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>

                <br/>

                <div class="col-xs-12">
                    <br/>

                    <div class="checkbox">
                        <label>
                            <input class="ace"
                                   type="checkbox"
                                   name="isAccompaniment"
                                   ng-model="hm.isAccompaniment"
                                   ng-checked="hm.isAccompaniment==true">
                                        <span class="lbl col-xs-10">&nbsp;&nbsp;&iquest;&Eacute;sta persona lo acompa&ntilde;ar&aacute;
                                            durante el proceso?</span>
                        </label>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>
</div>
<br/>

<div class="row" ng-show="hm.isAccompaniment==true">
    <div class="widget-box">
        <div class="widget-header">Datos adicionaes</div>
        <div class="widget-body">
            <div class="row">
                <div class="col-xs-12">
                    <br/>

                    <div class="row">
                        <div class="col-xs-12">
                            <br/>

                            <div class="col-xs-6">
                                <label>Domicilio</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited" name="address"
                                          ng-model="hm.address"
                                          maxlength="980" data-val="true"
                                          data-val-required="Direcci&oacute;n es un campo requerido">
                                </textarea>
        <span class="field-validation-valid" data-valmsg-for="address"
              data-valmsg-replace="true"></span>
                                <br/>
                            </div>
                            <div class="col-xs-6">
                                <label>Referencias del domicilio</label>
                                <br/>
                                <textarea class="input-xxlarge form-control limited" name="addressRef"
                                          ng-model="hm.addressRef"
                                          maxlength="980" data-val="true"
                                          data-val-required="Referencias del domicilio es un campo requerido">
                                </textarea>
        <span class="field-validation-valid" data-valmsg-for="addressRef"
              data-valmsg-replace="true"></span>
                                <br/>
                            </div>
                        </div>


                        <div class="col-xs-4 col-xs-offset-1">
                            <div>
                                <label>G&eacute;nero</label>
                            </div>

                            <div class="control-group col-xs-offset-1">
                    <span class="field-validation-valid" data-valmsg-for="gender"
                          data-valmsg-replace="true"></span>

                                <div class="radio">
                                    <label>
                                        <input type="radio" class="ace" name="gender"
                                               ng-model="hm.gender" value="1" data-val="true"
                                               data-val-required="Debe seleccionar una opci&oacute;n"/>
                                        <span class="lbl">&nbsp;&nbsp;Femenino</span>
                                    </label>
                                    <br/>
                                    <label>
                                        <input type="radio" class="ace" name="gender"
                                               ng-model="hm.gender" value="2"/>
                                        <span class="lbl">&nbsp;&nbsp;Masculino</span>
                                    </label>
                                </div>
                                <br/>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <label>Escolaridad:</label>
                            <br/>
                            <select class="form-control element-center" ng-model="hm.academicLvl"
                                    ng-options="al.name for al in lstAcademicLevel"
                                    ng-init='lstAcademicLevel = ${lstAcademicLevel};'></select>
                        </div>
                    </div>
                    <br/>

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="col-xs-5">
                                <div>
                                    <label>T&eacute;lefono</label>
                                    <textarea class="input-xxlarge form-control limited"
                                              id="phone" ng-model="hm.phone" name="phone"
                                              maxlength="980" data-val="true"
                                              data-val-required="T&eacute;lefono  es un campo requerido">
                                    </textarea>
                                        <span class="field-validation-valid" data-valmsg-for="phone"
                                              data-valmsg-replace="true"></span></div>
                            </div>
                            <div class="col-xs-6">
                                <div>
                                    <label>Lugar de ocupaci&oacute;n</label>
                                    <br/>
                                    <textarea class="input-xxlarge form-control limited"
                                              id="occupationPlace"
                                              name="occupationPlace"
                                              ng-model="hm.occupationPlace"
                                              maxlength="980" data-val="true"
                                              data-val-required="Lugar de ocupaci&oacute;n es un campo requerido">
                                    </textarea>
        <span class="field-validation-valid" data-valmsg-for="occupationPlace"
              data-valmsg-replace="true"></span>
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


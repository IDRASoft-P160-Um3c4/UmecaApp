<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row">
<div class="col-xs-10 col-xs-offset-1">

<div class="row element-center">
    <h2><i class="red glyphicon glyphicon-user bigger-100 element-center"></i> &nbsp;Persona que acompaña
        durante el proceso</h2>
</div>

<div class="row">
    <div ng-show="msgExito" class="col-xs-12 alert alert-success element-center success-font">
        {{successMsg}}
    </div>
</div>

<div class="row">

<form id="FormProccessAccompaniment" name="FormProccessAccompaniment"
      ng-submit="submit('#FormProccessAccompaniment')"
      class="form-horizontal"
      role="form">

<div class="widget-box">
    <div class="widget-header">Datos personales</div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="row">
                    <br/>

                    <div class="col-xs-4">
                        <label for="accompName">Nombre</label>
                        <br/>
                        <input id="accompName" ng-model="m.accompName" name="accompName"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Nombre es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompName"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label for="accompLastNameP">Apellido paterno</label>
                        <br/>
                        <input id="accompLastNameP" ng-model="m.accompLastNameP" name="accompLastNameP"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Apellido paterno es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompLastNameP"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label for="accompLastNameM">Apellido materno</label>
                        <br/>
                        <input id="accompLastNameM" ng-model="m.accompLastNameM" name="accompLastNameM"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Apellido materno es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompLastNameM"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>

                <div class="row">

                    <div class="col-xs-4">
                        <label>Género</label>
                        <br/>

                        <div class="radio">
                            <label>
                                <input type="radio" class="ace" name="gender"
                                       ng-model="m.objView.personalData.gender" value="true"/>
                                <span class="lbl">&nbsp;&nbsp;Femenino</span>
                            </label>
                            <br/>
                            <label>
                                <input type="radio" class="ace" name="gender"
                                       ng-model="m.objView.personalData.gender" value="false"/>
                                <span class="lbl">&nbsp;&nbsp;Masculino</span>
                            </label>
                        </div>
                    </div>

                    <div class="col-xs-4">
                        <label for="accompAge">Edad</label>
                        <br/>
                        <input id="accompAge" ng-model="m.accompAge" name="accompAge"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Nombre es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompAge"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label>Escolaridad</label>
                        <br/>
                        <select class="form-control element-center" ng-model="country"
                                ng-options="e.name for e in lstCountry"
                                url-request="/catalogs/getStatesByCountry.json"
                                ng-change="countryId = country.id;"
                                ng-init='lstCountry = ${lstCountry};'></select>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
</div>
<br/>

<div class="widget-box">
    <div class="widget-header">Ocupación</div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="row">
                    <br/>

                    <div class="col-xs-4">
                        <label for="accompOccupation">Ocupación</label>
                        <br/>
                        <input id="accompOccupation" ng-model="m.accompOccupation"
                               name="accompOccupation"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Ocupación es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompOccupation"
                  data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-4">
                        <label for="accompOccupationPlace">Lugar de ocupación</label>
                        <br/>
                        <input id="accompOccupationPlace" ng-model="m.accompOccupationPlace"
                               name="accompOccupationPlace"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Lugar de ocupación es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompOccupationPlace"
                  data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-4">
                        <label for="accompOccupationPhone">Teléfono</label>
                        <br/>
                        <input id="accompOccupationPhone" ng-model="m.accompOccupationPhone"
                               name="accompOccupationPhone"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Teléfono es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompOccupationPhone"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </div>
</div>
<br/>

<div class="widget-box">
    <div class="widget-header">Parentesco con el imputado</div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="row">
                    <br/>

                    <div class="col-xs-6">
                        <label>Parentesco:</label>
                        <br/>
                        <select class="form-control element-center" ng-model="country"
                                ng-options="e.name for e in lstCountry"
                                url-request="/catalogs/getStatesByCountry.json"
                                ng-change="countryId = country.id;"
                                ng-init='lstCountry = ${lstCountry};'></select>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </div>
</div>
<br/>

<div class="widget-box">
    <div class="widget-header">Domicilio</div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="row">
<br/>
                    <div class="col-xs-6">
                        <label for="accompAddress">Domicilio</label>
                        <br/>
                        <input id="accompAddress" ng-model="m.accompAddress"
                               name="accompAddress"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Domicilio es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompAddress"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-6">
                        <label for="accompAddressRef">Referencias</label>
                        <br/>
                        <input id="accompAddressRef" ng-model="m.accompAddressRef"
                               name="accompAddressRef"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Referencias es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompAddressRef"
                  data-valmsg-replace="true"></span>
                    </div>

                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-6">
                        <label for="accompCel">Celular</label>
                        <br/>
                        <input id="accompCel" ng-model="m.accompCel"
                               name="accompCel"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Celular es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompCel"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-6">
                        <label for="accompPhone">Teléfono fijo</label>
                        <br/>
                        <input id="accompPhone" ng-model="m.accompPhone"
                               name="accompPhone"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Teléfono fijo es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="accompPhone"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
</div>


</form>
</div>
<br/>

<div ng-show="msgError" class="alert alert-danger element-center error-font">
    {{errorMsg}}
</div>

</div>

<div class="col-xs-12">
    <div class="modal-footer">
        <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
              ng-click="submit('#FormPersonalData', '<c:url value="/reviewer/meeting/upsertPersonalData.json"/>');">
            <span class="glyphicon glyphicon-cloud-upload"></span>
              Guardar
        </span>
    </div>
</div>

</div>

<script>
    $('.date-picker').datepicker({autoclose: true}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });
    $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
        $(this).next().focus();
    });
</script>
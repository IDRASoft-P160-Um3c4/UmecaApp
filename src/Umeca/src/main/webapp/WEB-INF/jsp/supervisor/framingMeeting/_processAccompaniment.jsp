<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>

<div class="row" ng-controller="processAcompanimentController">
<input type="hidden" id="hidIdCaseProc" value="{{fm.objView.idCase}}"/>

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

<form id="FormProccessAccompaniment" name="FormProccessAccompaniment" class="form-horizontal" role="form">
<input type="hidden" id="hidUrlPA" value="<c:url value="/supervisor/framingMeeting/processAccompaniment/loadProcessAccompaniment.json"/>"/>
<div class="widget-box">
    <div class="widget-header">Datos personales</div>
    <div class="widget-body">
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="row">
                    <br/>

                    <div class="col-xs-4">
                        <label for="name">Nombre</label>
                        <br/>
                        <input id="name" ng-model="pa.name" name="name"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Nombre es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="name"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label for="lastNameP">Apellido paterno</label>
                        <br/>
                        <input id="lastNameP" ng-model="pa.lastNameP" name="lastNameP"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Apellido paterno es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="lastNameP"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label for="lastNameM">Apellido materno</label>
                        <br/>
                        <input id="lastNameM" ng-model="pa.lastNameM" name="lastNameM"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Apellido materno es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="lastNameM"
                  data-valmsg-replace="true"></span>
                    </div>
                </div>
                <br/>

                <div class="row">

                    <div class="col-xs-4">
                        <label>Género</label>
                        <div class="radio">
                            <label>
                                <input type="radio" class="ace" name="gender"
                                       ng-model="pa.gender" value="1" data-val="true"
                                       data-val-required="Debe seleccionar una opción"/>
                                <span class="lbl">&nbsp;&nbsp;Femenino</span>
                            </label>
                            <br/>
                            <label>
                                <input type="radio" class="ace" name="gender"
                                       ng-model="pa.gender" value="2"/>
                                <span class="lbl">&nbsp;&nbsp;Masculino</span>
                            </label>
                            <br/>
                             <span class="field-validation-valid" data-valmsg-for="gender"
                                   data-valmsg-replace="true"></span>
                        </div>
                    </div>

                    <div class="col-xs-4">
                        <label for="age">Edad</label>
                        <br/>
                        <input id="age" ng-model="pa.age" name="age"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Edad es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="age"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-4">
                        <label>Escolaridad</label>
                        <br/>
                        <select class="form-control element-center" ng-model="pa.academicLevel"
                                ng-options="e.name for e in lstAcademicLevel"
                                ng-init='lstAcademicLevel = ${lstAcademicLevel};'></select>
                        <input type="hidden" name="academicLevelId" value="{{pa.academicLevel.id}}">
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
                        <label for="occName">Ocupación</label>
                        <br/>
                        <input id="occName" ng-model="pa.occName"
                               name="occName"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Ocupación es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="occName"
                  data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-4">
                        <label for="occPlace">Lugar de ocupación</label>
                        <br/>
                        <input id="occPlace" ng-model="pa.occPlace"
                               name="occPlace"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Lugar de ocupación es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="occPlace"
                  data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-4">
                        <label for="occPhone">Teléfono</label>
                        <br/>
                        <input id="occPhone" ng-model="pa.occPhone"
                               name="occPhone"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Teléfono es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="occPhone"
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
                        <select class="form-control element-center" ng-model="pa.relationship"
                                ng-options="e.name for e in lstRelationship"
                                ng-init='lstRelationship = ${lstRelationship};'></select>
                        <input type="hidden" name="relationshipId" value="{{pa.relationship.id}}">
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

                    <%@ include file="/WEB-INF/jsp/address/index.jsp" %>

                </div>

                <br/>

                <div class="row">
                    <div class="col-xs-6">
                        <label for="celphone">Celular</label>
                        <br/>
                        <input id="celphone" ng-model="pa.celphone"
                               name="celphone"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Celular es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="celPhone"
                  data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-6">
                        <label for="phone">Teléfono fijo</label>
                        <br/>
                        <input id="phone" ng-model="pa.phone"
                               name="phone"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Teléfono fijo es un campo requerido"/>
                        <br/>
            <span class="field-validation-valid" data-valmsg-for="phone"
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
              ng-click="submitIdCaseParam('#FormProccessAccompaniment', '<c:url value="/supervisor/framingMeeting/processAccompaniment/doUpsert.json?idCase="/>',fm.objView.idCase);">

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
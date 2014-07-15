<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="personalDataFMController">

<div class="col-xs-10 col-xs-offset-1">

<div class="row element-center">
    <h2><i class="purple glyphicon glyphicon-user bigger-100 element-center"></i> &nbsp;Datos personales</h2>
</div>
<br/>
<div class="row">
    <div ng-show="pdSuccessMsg&&pdSuccessMsg!=''" class="col-xs-12 alert alert-success element-center success-font">
        {{pdSuccessMsg}}
    </div>
    <div ng-show="pdErrorMsg&&pdErrorMsg!=''" class="alert alert-danger element-center error-font">
        {{pdErrorMsg}}
    </div>
</div>

<div class="row">

<form id="FormPersonalData" name="FormPersonalData" class="form-horizontal" role="form">
<input type="hidden" id="hidIdCasePD" value="{{fm.objView.idCase}}"/>

<input type="hidden" id="hidUrlPD"
       value="<c:url value="/supervisor/framingMeeting/personalData/loadPersonalData.json"/>"/>

<div class="widget-box">
    <div class="widget-header">Nombre</div>
    <div class="widget-body">
        <div class="row">
            <br/>

            <div class="col-xs-10 col-xs-offset-1">
                <br/>

                <div class="col-xs-4">
                    <label for="name">Nombre</label>
                    <br/>
                    <input id="name" ng-model="pd.name" name="name"
                           type="text" class="input-xxlarge"
                           data-val="true"
                           data-val-required="Nombre es un campo requerido"/>
                    <br/>
            <span class="field-validation-valid" data-valmsg-for="name"
                  data-valmsg-replace="true"></span>
                </div>

                <div class="col-xs-4">
                    <label for="name">Apellido paterno</label>
                    <br/>
                    <input id="lastNameP" ng-model="pd.lastNameP" name="lastNameP"
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
                    <input id="lastNameM" ng-model="pd.lastNameM" name="lastNameM"
                           type="text" class="input-xxlarge"
                           data-val="true"
                           data-val-required="Apellido materno es un campo requerido"/>
                    <br/>
            <span class="field-validation-valid" data-valmsg-for="lastNameM"
                  data-valmsg-replace="true"></span>
                </div>
            </div>
        </div>
        <br/>
    </div>
</div>

<br/>

<div class="row">

    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header">G�nero</div>
            <div class="widget-body">
                <div class="control-group col-xs-offset-1">
                    <span class="field-validation-valid" data-valmsg-for="gender"
                          data-valmsg-replace="true"></span>
                    <br/>
                    <div class="radio">

                        <label>
                            <input type="radio" class="ace" name="gender"
                                   ng-model="pd.gender" value="1" data-val="true"
                                   data-val-required="Debe seleccionar una opci�n"/>
                            <span class="lbl">&nbsp;&nbsp;Femenino</span>
                        </label>
                        <br/>
                        <label>
                            <input type="radio" class="ace" name="gender"
                                   ng-model="pd.gender" value="2"/>
                            <span class="lbl">&nbsp;&nbsp;Masculino</span>
                        </label>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-6">
        <div class="widget-box">
            <div class="widget-header">Estado civil</div>
            <div class="widget-body ">

                <div class="control-group col-xs-offset-1">
                    <span class="field-validation-valid" data-valmsg-for="maritalStatus"
                          data-valmsg-replace="true"></span>
                    <br/>
                    <div class="radio">
                        <label>
                            <input type="radio" class="ace" name="maritalStatus"
                                   ng-model="pd.maritalStatus" value="1" data-val="true"
                                   data-val-required="Debe seleccionar una opci�n"/>
                            <span class="lbl">&nbsp;&nbsp;Soltero</span>
                        </label>
                        <br/>
                        <label>
                            <input type="radio" class="ace" name="maritalStatus"
                                   ng-model="pd.maritalStatus" value="2"/>
                            <span class="lbl">&nbsp;&nbsp;Casado</span>
                        </label>
                        <br/>
                        <label>
                            <input type="radio" class="ace" name="maritalStatus"
                                   ng-model="pd.maritalStatus" value="3"/>
                            <span class="lbl">&nbsp;&nbsp;Divorciado</span>
                        </label>
                        <br/>
                        <label>
                            <input type="radio" class="ace" name="maritalStatus"
                                   ng-model="pd.maritalStatus" value="4"/>
                            <span class="lbl">&nbsp;&nbsp;Union libre</span>
                        </label>
                        <br/>
                        <label>
                            <input type="radio" class="ace" name="maritalStatus"
                                   ng-model="pd.maritalStatus" value="5"/>
                            <span class="lbl">&nbsp;&nbsp;Viudo</span>
                        </label>
                    </div>
                    <br/>
                    <div ng-show="pd.maritalStatus==2 || pd.maritalStatus==4">
                        <div class="col-xs-offset-1">
                            <label>A�os</label><br>
                            <input id="maritalStatusYears" ng-model="pd.maritalStatusYears" name="maritalStatusYears"
                                   type="text" class="input-sm"
                                   data-val="true"
                                   data-val-required="A�os es un campo requerido"/>
                            <br/>
                        <span class="field-validation-valid" data-valmsg-for="maritalStatusYears"
                              data-valmsg-replace="true"></span>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
</div>

<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header">Lugar y fecha de nacimiento</div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-4 col-xs-offset-1">
                        <label>Pais</label>
                        <select class="form-control element-center" ng-model="pd.birthCountry"
                                ng-options="e.name for e in lstCountry"
                                ng-change="pd.birthCountryId = pd.birthCountry.id;"
                                ng-init='lstCountry = ${lstCountry};'></select>
                        <input type="hidden" name="birthCountryId" value="{{pd.birthCountryId}}"/>
                    </div>

                    <div class="col-xs-3">
                        <label>Estado</label>
                        <br/>
                        <input id="birthState" ng-model="pd.birthState" name="birthState"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Estado es un campo requerido"/>
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="birthState"
                              data-valmsg-replace="true"></span>
                    </div>

                    <div class="col-xs-3">
                        <label for="birthDate">Fecha de nacimiento</label>

                        <div class="input-group">
                            <input class="form-control date-picker"
                                   id="birthDate" name="birthDate" ng-model="pd.birthDate"
                                   data-date-format="dd/mm/yyyy" type="text" readonly data-val="true"
                                   data-val-required="Fecha de naciemiento es un campo requerido">
                            <span class="input-group-addon">
                                <i class="icon-calendar bigger-110"></i>
                            </span>
                        </div>
                            <span class="field-validation-valid" data-valmsg-for="birthDate"
                                  data-valmsg-replace="true"></span>
                    </div>

                </div>
                <br/>
            </div>

        </div>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div class="widget-box">
            <div class="widget-header">Padecimientos</div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-10 col-xs-offset-1">
                        <label>�Padece alguna enfermedad o condici�n f�sica?</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" name="physicalCondition"
                                  ng-model="pd.physicalCondition"
                                  maxlength="980" data-val="true"
                                  data-val-required="Enfermedad o condici�n f�sica es un campo requerido">
                            {{pd.physicalCondition}}</textarea>
        <span class="field-validation-valid" data-valmsg-for="physicalCondition"
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


</div>

<div class="col-xs-12">
    <div class="modal-footer">
        <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
              ng-click="submitIdCaseParam('#FormPersonalData', '<c:url value="/supervisor/framingMeeting/personalData/doUpsert.json?idCase="/>',fm.objView.idCase);">
            <span class="glyphicon glyphicon-cloud-upload"></span>
              Guardar
        </span>
    </div>
</div>

</div>

<script>
    var date=new Date();
    date.setFullYear(date.getFullYear()-18);
    $('.date-picker').datepicker({autoclose:true, endDate:date}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });

    $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
        $(this).next().focus();
    });


</script>

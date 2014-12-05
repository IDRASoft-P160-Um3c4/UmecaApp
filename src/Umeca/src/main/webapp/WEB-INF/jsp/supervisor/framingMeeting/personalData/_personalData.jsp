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
    <div ng-show="pdSuccessMsg&&pdSuccessMsg!=''" class="alert alert-success element-center success-font">
        <span ng-bind-html="pdSuccessMsg"></span>
    </div>

    <div ng-show="pdErrorMsg&&pdErrorMsg!=''" class="alert alert-danger element-center error-font">
        <span ng-bind-html="pdErrorMsg"></span>
    </div>
</div>

<div class="row">

<form id="FormPersonalData" name="FormPersonalData" class="form-horizontal" role="form">
<input type="hidden" id="hidIdCasePD" value="{{fm.objView.idCase}}"/>

<input type="hidden" id="hidUrlPD"
       value="<c:url value="/supervisor/framingMeeting/personalData/loadPersonalData.json"/>"/>

<input type="hidden" name="birthStateId" value="{{pd.birthStateCmb.id}}"/>
<input type="hidden" name="isMexico" value="{{pd.isMexico}}"/>


<input type="hidden" ng-init="urlGetStates= '<c:url value="/supervisor/framingMeeting/personalData/getStates.json"/>';">

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
            <div class="widget-header">G&eacute;nero</div>
            <div class="widget-body">
                <div class="control-group col-xs-offset-1">
                    <span class="field-validation-valid" data-valmsg-for="gender"
                          data-valmsg-replace="true"></span>
                    <br/>

                    <div class="radio">

                        <label>
                            <input type="radio" class="ace" name="gender"
                                   ng-model="pd.gender" value="1" data-val="true"
                                   data-val-required="Debe seleccionar una opci&oacute;n"/>
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
                                   data-val-required="Debe seleccionar una opci&oacute;n"/>
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
                            <label>A&ntilde;os</label><br>
                            <input id="maritalStatusYears" ng-model="pd.maritalStatusYears" name="maritalStatusYears"
                                   type="text" class="input-sm"
                                   data-val="true"
                                   data-val-required="A&ntilde;os es un campo requerido"/>
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
            <div class="widget-header">Contacto</div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-5 col-xs-offset-1">
                        <label>Tel&eacute;fono</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" name="phone"
                                  ng-model="pd.phone"
                                  maxlength="980" data-val="true"
                                  data-val-required="Tel&eacute;fono es un campo requerido">
                        </textarea>
                    <span class="field-validation-valid" data-valmsg-for="phone"
                          data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-5">
                        <label>Celular</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" name="celPhone"
                                  ng-model="pd.celPhone"
                                  maxlength="980" data-val="true"
                                  data-val-required="Celular es un campo requerido">
                        </textarea>
                    <span class="field-validation-valid" data-valmsg-for="celPhone"
                          data-valmsg-replace="true"></span>
                    </div>
                </div>

                <br/>

                <div class="row">
                    <div class="col-xs-5 col-xs-offset-1">
                        <label>Correo electr&oacute;nico</label><br>
                        <input id="email" ng-model="pd.email" name="email"
                               type="text" class="input-xxlarge"
                               data-val="true"
                               data-val-required="Correo electr&oacute;nico es un campo requerido"/>
                        <br/>
                        <span class="field-validation-valid" data-valmsg-for="email"
                              data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-5">
                        <label>Redes sociales <label class="info-example">(Facebook, twiter, etc.)</label></label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" name="socialNetworking"
                                  ng-model="pd.socialNetworking"
                                  maxlength="980" data-val="true"
                                  data-val-required="Redes sociales es un campo requerido">
                        </textarea>
                    <span class="field-validation-valid" data-valmsg-for="socialNetworking"
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
            <div class="widget-header">Lugar y fecha de nacimiento</div>
            <div class="widget-body">
                <br/>

                <div class="row">
                    <div class="col-xs-5 col-xs-offset-1">
                        <label>Pa&iacute;s</label>
                        <select class="form-control element-center" ng-model="pd.birthCountry"
                                ng-options="e.name for e in lstCountry"
                                ng-change="pd.birthCountryId = pd.birthCountry.id; changeCountry();"
                                ng-init='lstCountry = ${lstCountry};'></select>
                        <input type="hidden" name="birthCountryId" value="{{pd.birthCountryId}}"/>
                    </div>

                    <div class="col-xs-5" ng-show="pd.isMexico==true">
                        <label>Estado</label>
                        <select class="form-control element-center" ng-model="pd.birthStateCmb"
                                ng-options="e.name for e in pd.lstStates"></select>
                        <input type="hidden" name="birthCountryId" value="{{pd.birthCountryId}}"/>
                    </div>

                    <div class="col-xs-5" ng-show="pd.isMexico==false">
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

                </div>

                <br/>

                <div class="row">
                    <div class="col-xs-5 col-xs-offset-1">
                        <label for="birthDate">Fecha de nacimiento</label>

                        <div class="input-group">
                            <input class="form-control date-picker"
                                   id="birthDate" name="birthDate" ng-model="pd.birthDate"
                                   data-date-format="yyyy/mm/dd" type="text" readonly data-val="true"
                                   data-val-required="Fecha de naciemiento es un campo requerido"
                                   ng-change="calcAge();">
                            <span class="input-group-addon">
                                <i class="icon-calendar bigger-110"></i>
                            </span>
                        </div>
                            <span class="field-validation-valid" data-valmsg-for="birthDate"
                                  data-valmsg-replace="true"></span>
                    </div>
                    <div class="col-xs-5">
                        <label>Edad</label>
                        <br/>
                        <input id="age" ng-model="pd.age"
                               type="text" class="input-xxlarge" readonly/>
                        <br/>
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
                        <label>&iquest;Padece alguna enfermedad o condici&oacute;n f&iacute;sica?</label><br/>
                        <label class="info-example">(tuberculosis, hepatitis, alergias, embarazo, tiempo de gestaci&oacute;n,
                            lactancia, etc.)</label>
                        <br/>
                        <textarea class="input-xxlarge form-control limited" name="physicalCondition"
                                  ng-model="pd.physicalCondition"
                                  maxlength="980" data-val="true"
                                  data-val-required="Enfermedad o condici&oacute;n f&iacute;sica es un campo requerido">
                        </textarea>
        <span class="field-validation-valid" data-valmsg-for="physicalCondition"
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
        <label>Observaciones</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="comments"
                  ng-model="pd.comments"
                  maxlength="980" data-val="true"
                  data-val-required="Observaciones es un campo requerido">
        </textarea>
                    <span class="field-validation-valid" data-valmsg-for="comments"
                          data-valmsg-replace="true"></span>
    </div>
</div>
</form>
</div>
<br/>


</div>

<div class="col-xs-12">
    <%--<div class="modal-footer" ng-show="fm.objView.canTerminate==true">--%>
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
    var date = new Date();
    date.setFullYear(date.getFullYear() - 18);
    $('.date-picker').datepicker({autoclose: true, endDate: date}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function () {
        $(this).next().focus();
    });


</script>

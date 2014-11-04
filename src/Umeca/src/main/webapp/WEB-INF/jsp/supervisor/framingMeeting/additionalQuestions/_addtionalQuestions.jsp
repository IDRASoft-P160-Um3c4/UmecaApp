<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="additionalQuestionsController">
<div class="col-xs-10 col-xs-offset-1">

<div class="row element-center">
    <h2><i class="red icon-list bigger-100"></i> &nbsp;Preguntas adicionales
    </h2>
</div>
<br/>

<div class="row">
    <div ng-show="aqSuccessMsg&&aqSuccessMsg!=''" class="col-xs-12 alert alert-success element-center success-font">
        {{aqSuccessMsg}}
    </div>
    <div ng-show="aqErrorMsg&&aqErrorMsg!=''" class="alert alert-danger element-center error-font">
        {{aqErrorMsg}}
    </div>
</div>

<div class="row">

<form id="FormAddQuest" name="FormAddQuest" class="form-horizontal" role="form">
<input type="hidden" id="loadAdditionalQuestion"
       value="<c:url value='/supervisor/framingMeeting/additionalQuestions/loadAdditionalQuestion.json'/>"/>
<input type="hidden" id="hidIdCaseAQ" value="{{fm.objView.idCase}}"/>

<div class="row">
<div class="widget-box">
<div class="widget-header">Preguntas adicionales</div>
<div class="widget-body">
<div class="row">
<div class="col-xs-10 col-xs-offset-1">
<br/>

<div class="row">
    <label>&iquest;Se encuentra en alg&uacute;n tipo de tratamiento de adicciones?</label>
    <br/>
    <span class="field-validation-valid" data-valmsg-for="addictionTreatment"
          data-valmsg-replace="true"></span>

    <div class="radio">
        <label>
            <input type="radio" class="ace" name="addictionTreatment"
                   ng-change="selAddictionTreatment();"
                   ng-model="aq.addictionTreatment" ng-checked="aq.addictionTreatment==1" value="1"
                   data-val="true" data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;S&iacute;</span>
        </label>
        <br/>
        <label>
            <input type="radio" class="ace" name="addictionTreatment" ng-checked="aq.addictionTreatment==2"
                   ng-change="selAddictionTreatment();"
                   ng-model="aq.addictionTreatment" value="2"/>
            <span class="lbl">&nbsp;&nbsp;No</span>
        </label>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-8" ng-show='aq.addictionTreatment==1'>
        <label>&iquest;En qu&eacute; instituci&oacute;n?</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="addictionTreatmentInstitute"
                  ng-model="aq.addictionTreatmentInstitute"
                  maxlength="980" data-val="true"
                  data-val-required="Instituci&oacute;n es un campo requerido">
        </textarea>
                                            <span class="field-validation-valid"
                                                  data-valmsg-for="addictionTreatmentInstitute"
                                                  data-valmsg-replace="true"></span>
    </div>
    <br/>

    <div class="col-xs-4" ng-show='aq.addictionTreatment==1'>
        <label for="addictionTreatmentDate">&iquest;Desde cu&aacute;ndo?</label>
        <br/>

        <div class="input-group">
            <input class="form-control date-picker"
                   id="addictionTreatmentDate" name="addictionTreatmentDate"
                   type="text"
                   data-date-format="yyyy/mm/dd" readonly
                   ng-model="aq.addictionTreatmentDate"
                   data-val="true"
                   data-val-required="Fecha de tratamiento es un campo requerido"/>
                                                <span class="input-group-addon">
                                                    <i class="icon-calendar bigger-110"></i>
                                                </span>
        </div>
                                <span class="field-validation-valid" data-valmsg-for="addictionTreatmentDate"
                                      data-valmsg-replace="true"></span>
    </div>
</div>


<br/>

<div class="row">
    <label>&iquest;Tus familiares y/o amigos consumen substancias adictivas?</label>
    <br/>
    <input type="hidden" name="selectedAddictedAcquaintances" value="{{selectedAddictedAcquaintances}}">
  <span class="field-validation-valid" data-valmsg-for="addictedAcquaintance"
        data-valmsg-replace="true"></span>

    <div class="radio">
        <label>
            <input type="radio" class="ace" name="addictedAcquaintance"
                   ng-model="aq.addictedAcquaintance" value="1" data-val="true"
                   data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;S&iacute;</span>
        </label>
        <br/>
        <label>
            <input type="radio" class="ace" name="addictedAcquaintance"
                   ng-model="aq.addictedAcquaintance" value="2"/>
            <span class="lbl">&nbsp;&nbsp;No</span>
        </label>
    </div>
    <br/>

    <div ng-show="aq.addictedAcquaintance==1">
        <label>&iquest;Qu&eacute; parentesco/relaci&oacute;n tienes con ellos?</label>
        <br/>

        <div ng-show="errorSelAddAcq&&errorSelAddAcq!=''"
             class="field-validation-error col-xs-10">
            <span>{{errorSelAddAcq}}</span>
        </div>
        <br/>

        <div class="row" ng-repeat="addictedAcq in selectedAddictedAcquaintances">
            <div class="col-xs-offset-10 col-xs-offset-1">
                <label>
                    <input class="ace" ng-model="selectedAddictedAcquaintances[$index].selVal"
                           type="checkbox" ng-disabled="{{fm.objView.canTerminate==false}}">
                    <span class="lbl">&nbsp;&nbsp;{{addictedAcq.name}}</span>
                </label>
            </div>
        </div>

    </div>

</div>
<br/>

<div class="row">
    <label>&iquest;Cuenta con familiares en el extranjero?</label>
    <br/>
 <span class="field-validation-valid" data-valmsg-for="relativeAbroad"
       data-valmsg-replace="true"></span>

    <div class="radio">
        <label>
            <input type="radio" class="ace" name="relativeAbroad"
                   ng-model="aq.relativeAbroad" value="1" data-val="true"
                   data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;S&iacute;</span>
        </label>
        <br/>
        <label>
            <input type="radio" class="ace" name="relativeAbroad"
                   ng-model="aq.relativeAbroad" value="2"/>
            <span class="lbl">&nbsp;&nbsp;No</span>
        </label>
    </div>
    <br/>

    <div ng-show="aq.relativeAbroad==1">
        <label>&iquest;Qu&eacute; parentesco/relaci&oacute;n tienes con esa persona?</label>
        <br/>
        <input type="hidden" name="selectedRelativesAbroad" value="{{selectedRelativesAbroad}}">

        <div ng-show="errorSelRelAbroad&&errorSelRelAbroad!=''"
             class="field-validation-error col-xs-10">
            <span>{{errorSelRelAbroad}}</span>
        </div>
        <div class="row" ng-repeat="relativeAbroad in selectedRelativesAbroad">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="checkbox">
                    <label ng-click="validateRelAbroad();">
                        <input class="ace"
                               ng-model="selectedRelativesAbroad[$index].selVal"
                               type="checkbox"
                               ng-disabled="{{fm.objView.canTerminate==false}}">
                        <span class="lbl">&nbsp;&nbsp;{{relativeAbroad.name}}</span>
                    </label>
                </div>
                <div class="col-xs-offset-1" ng-show="selectedRelativesAbroad[$index].selVal==true">
                    <label>&iquest;D&oacute;nde vive?</label>
                    <textarea class="form-control limited"
                              maxlength="980" ng-blur="validateRelAbroad();"
                              ng-model="selectedRelativesAbroad[$index].description"
                              ng-disabled="{{fm.objView.canTerminate==false}}"
                            ></textarea>
                </div>

            </div>
        </div>
    </div>

</div>
<br/>

<div class="row">
    <label>&iquest;Consideras que alguna de las obligaciones impuestas ser&aacute; dif&iacute;cil de
        cumplir?</label>
    <br/>
 <span class="field-validation-valid" data-valmsg-for="obligationIssue"
       data-valmsg-replace="true"></span>

    <div class="radio">
        <label>
            <input type="radio" class="ace" name="obligationIssue"
                   ng-model="aq.obligationIssue" value="1" data-val="true"
                   data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;S&iacute;</span>
        </label>
        <br/>
        <label>
            <input type="radio" class="ace" name="obligationIssue"
                   ng-model="aq.obligationIssue" value="2"/>
            <span class="lbl">&nbsp;&nbsp;No</span>
        </label>
    </div>
    <br/>

    <div ng-show="aq.obligationIssue==1">
        <label>&iquest;Cu&aacute;les?</label>
        <br/>
        <input type="hidden" name="selectedObligationIssues" value="{{selectedObligationIssues}}">

        <div ng-show="errorSelObligIssues&&errorSelObligIssues!=''"
             class="field-validation-error col-xs-10">
            <span>{{errorSelObligIssues}}</span>
        </div>
        <div class="row" ng-repeat="obliIssue in selectedObligationIssues">
            <div class="col-xs-10 col-xs-offset-1">
                <div class="checkbox">
                    <label ng-click="validateOblIssues();">
                        <input class="ace"
                               ng-model="selectedObligationIssues[$index].selVal"
                               type="checkbox" ng-disabled="{{fm.objView.canTerminate==false}}">
                        <span class="lbl">&nbsp;&nbsp;{{obliIssue.name}}</span>
                    </label>
                </div>
                <div class="col-xs-offset-1" ng-show="selectedObligationIssues[$index].selVal==true">
                    <label>Causa</label>
                    <textarea class="form-control limited"
                              maxlength="980"
                              ng-model="selectedObligationIssues[$index].description"
                              ng-blur="validateOblIssues();"
                              ng-disabled="{{fm.objView.canTerminate==false}}"
                            ></textarea>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>

<div class="row">
    <div>
        <label>Observaciones</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="observations"
                  ng-model="aq.observations"
                  maxlength="980" data-val="true"
                  data-val-required="Comentarios es un campo requerido">
        </textarea>
                                            <span class="field-validation-valid" data-valmsg-for="observations"
                                                  data-valmsg-replace="true"></span>
    </div>
</div>
<br/>

</div>
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
              ng-show="{{fm.objView.canTerminate==true}}"
              ng-click="submitIdCaseParam('#FormAddQuest', '<c:url value="/supervisor/framingMeeting/additionalQuestions/doUpsert.json?idCase="/>',fm.objView.idCase);">
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
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
    <label>¿Se encuentra en algun tipo de tratamiento de adicciones?</label>
    <br/>
    <span class="field-validation-valid" data-valmsg-for="addictionTreatment"
          data-valmsg-replace="true"></span>

    <div class="radio">
        <label>
            <input type="radio" class="ace" name="addictionTreatment"
                   ng-model="aq.addictionTreatment" ng-checked="aq.addictionTreatment==1" value="1"
                   data-val="true" data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;Si</span>
        </label>
        <br/>
        <label>
            <input type="radio" class="ace" name="addictionTreatment" ng-checked="aq.addictionTreatment==2"
                   ng-model="aq.addictionTreatment" value="2"/>
            <span class="lbl">&nbsp;&nbsp;No</span>
        </label>
    </div>
</div>
<br/>

<div class="row">
    <div class="col-xs-8" ng-show='aq.addictionTreatment==1'>
        <label>¿En que institución?</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="addictionTreatmentInstitute"
                  ng-model="aq.addictionTreatmentInstitute"
                  maxlength="980" data-val="true"
                  data-val-required="Institución es un campo requerido">{{aq.addictionTreatmentInstitute}}
        </textarea>
                                            <span class="field-validation-valid" data-valmsg-for="crimes"
                                                  data-valmsg-replace="true"></span>
    </div>
    <br/>

    <div class="col-xs-4" ng-show='aq.addictionTreatment==1'>
        <label for="addictionTreatmentDate">¿Desde cuando?</label>
        <br/>

        <div class="input-group">
            <input class="form-control date-picker"
                   id="addictionTreatmentDate" name="addictionTreatmentDate"
                   type="text"
                   data-date-format="dd/mm/yyyy" readonly
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
    <label>¿Tus familiares y/o amigos consumen substancias adictivas? {{aq.addictedAcquaintance}}</label>
    <br/>
    <input type="hidden" name="selectedAddictedAcquaintances" value="{{selectedAddictedAcquaintances}}">
  <span class="field-validation-valid" data-valmsg-for="addictedAcquaintance"
        data-valmsg-replace="true"></span>
    <div class="radio">
        <label>
            <input type="radio" class="ace" name="addictedAcquaintance"
                   ng-model="aq.addictedAcquaintance" value="1" data-val="true" data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;Si</span>
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
        <label>¿Que parentesco tienes con ellos?</label>
        <br/>

        <div ng-show="errorAcq&&errorAcq!=''"
             class="field-validation-error col-xs-10 col-xs-offset-1">
            <span>Debe seleccionar al menos una opción</span>
        </div>
        <br/>

        <div class="row" ng-repeat="addictedAcq in selectedAddictedAcquaintances">
            <div class="col-xs-offset-10 col-xs-offset-1">
                <label>
                    <input class="ace" ng-model="selectedAddictedAcquaintances[$index].selVal"
                           type="checkbox">
                    <span class="lbl">&nbsp;&nbsp;{{addictedAcq.name}}</span>
                </label>
            </div>
        </div>

    </div>

</div>
<br/>

<div class="row">
    <label>¿Cuenta con familiares en el extranjero?</label>
    <br/>
 <span class="field-validation-valid" data-valmsg-for="relativeAbroad"
       data-valmsg-replace="true"></span>
    <div class="radio">
        <label>
            <input type="radio" class="ace" name="relativeAbroad"
                   ng-model="aq.relativeAbroad" value="1" data-val="true" data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;Si</span>
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
        <label>¿Que parentesco tienes con ellos?</label>
        <br/>
        <input type="hidden" name="selectedRelativesAbroad" value="{{selectedRelativesAbroad}}">

        <div class="row" ng-repeat="relativeAbroad in selectedRelativesAbroad">
            <div class="col-xs-10 col-xs-offset-1">
                <div>
                    <label>
                        <input class="ace"
                               ng-model="selectedRelativesAbroad[$index].selVal"
                               type="checkbox">
                        <span class="lbl">&nbsp;&nbsp;{{relativeAbroad.name}}</span>
                    </label>
                </div>
                <div class="col-xs-offset-1" ng-show="selectedRelativesAbroad[$index].selVal==true">
                    <label>¿Dónde vive?</label>
                    <textarea class="form-control limited"
                              maxlength="980"
                              ng-model="selectedRelativesAbroad[$index].description"
                            >{{selectedRelativesAbroad[$index].description}}</textarea>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>

<div class="row">
    <label>¿Consideras que alguna de las obligaciones impuestas será difícil de cumplir?</label>
    <br/>
 <span class="field-validation-valid" data-valmsg-for="obligationIssue"
       data-valmsg-replace="true"></span>
    <div class="radio">
        <label>
            <input type="radio" class="ace" name="obligationIssue"
                   ng-model="aq.obligationIssue" value="1" data-val="true" data-val-required="Debe seleccionar un valor"/>
            <span class="lbl">&nbsp;&nbsp;Si</span>
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
        <label>¿Cuales?</label>
        <br/>
        <input type="hidden" name="selectedObligationIssues" value="{{selectedObligationIssues}}">

        <div class="row" ng-repeat="obliIssue in selectedObligationIssues">
            <div class="col-xs-10 col-xs-offset-1">
                <div>
                    <label>
                        <input class="ace"
                               ng-model="selectedObligationIssues[$index].selVal"
                               type="checkbox">
                        <span class="lbl">&nbsp;&nbsp;{{obliIssue.name}}</span>
                    </label>
                </div>
                <div class="col-xs-offset-1" ng-show="selectedObligationIssues[$index].selVal==true">
                    <label>Causa</label>
                    <textarea class="form-control limited"
                              maxlength="980"
                              ng-model="selectedObligationIssues[$index].description"
                            >{{selectedObligationIssues[$index].description}}</textarea>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>

<div class="row">
    <div>
        <label>Comentarios</label>
        <br/>
        <textarea class="input-xxlarge form-control limited" name="observations"
                  ng-model="aq.observations"
                  maxlength="980" data-val="true"
                  data-val-required="Comentarios es un campo requerido">{{observations}}
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
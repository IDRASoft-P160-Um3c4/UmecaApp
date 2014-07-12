<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="additionalQuestionsController">
<div class="col-xs-10 col-xs-offset-1">

<div class="row element-center">
    <h2><i class="red icon-list bigger-100"></i> &nbsp;Preguntas adicionales
    </h2>
</div>

<div class="row">
    <div ng-show="aqSuccessMsg&&aqSuccessMsg!=''" class="col-xs-12 alert alert-success element-center success-font">
        {{aqSuccessMsg}}
    </div>
    <div ng-show="aqErrorMsg&&aqErrorMsg!=''" class="alert alert-danger element-center error-font">
        {{aqErrorMsg}}
    </div>
</div>

<div class="row">

<form id="FormPersonalData" name="FormPersonalData" class="form-horizontal" role="form">
<input type="hidden" id="loadRelativeAbroad" value="<c:url value='/supervisor/framingMeeting/activities/loadRelativeAbroad.html'/>"/>

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

            <div class="radio">
                <label>
                    <input type="radio" class="ace" name="addictionTreatment"
                           ng-model="aq.addictionTreatment" value="1"/>
                    <span class="lbl">&nbsp;&nbsp;Si</span>
                </label>
                <br/>
                <label>
                    <input type="radio" class="ace" name="addictionTreatment"
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
                <textarea class="input-xxlarge form-control limited" name="crimes"
                          ng-model="m.crimes"
                          maxlength="980" data-val="true"
                          data-val-required="Institución es un campo requerido">
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
                           ng-model="m.addictionTreatmentDate"
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
            <label>¿Tus familiares y/o amigos consumen substancias adictivas?</label>
            <br/>

            <div class="radio">
                <label>
                    <input type="radio" class="ace" name="addictedAcquaintance"
                           ng-model="aq.addictedAcquaintance" value="1"/>
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

            <div ng-show="aq.addictedAcquaintance==1"
                 ng-init='lstRelationship = ${lstRelationship}'>
                <label>¿Que parentesco tienes con ellos?</label>
                <br/>

                <div class="row" ng-repeat="addictedAcquaintanceRelationship in lstRelationship">
                    <label>
                        <input ng-click="selectAddictedAcquaintanceRelationship(threat.id);"
                               class="ace"
                               type="checkbox">
                        <span class="lbl">&nbsp;&nbsp;{{addictedAcquaintanceRelationship.name}}</span>
                    </label>
                </div>
                                            <span class="field-validation-valid" data-valmsg-for="crimes"
                                                  data-valmsg-replace="true"></span>
            </div>

        </div>
        <br/>

        <div class="row">
            <label>¿Cuenta con familiares en el extranjero?</label>
            <br/>

            <div class="radio">
                <label>
                    <input type="radio" class="ace" name="relativeAbroad"
                           ng-model="aq.relativeAbroad" value="1"/>
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
                <div class="row" ng-repeat="relativeAbroad in lstRelativeAbroad">
                    <div>
                        <label>
                            <input class="ace"
                                   ng-model="lstRelativeAbroad[$index].selVal"
                                   type="checkbox">
                            <span class="lbl">&nbsp;&nbsp;{{relativeAbroad.name}}</span>
                        </label>
                    </div>
                    <div class="col-xs-offset-1" ng-show="lstRelativeAbroad[$index].selVal==true">
                        <label>¿Dónde vive?</label>
                        <textarea class="form-control limited"
                                  maxlength="980"
                                  ng-model="lstRelativeAbroad[$index].address"
                                  >{{lstRelativeAbroad[$index].address}}</textarea>
                    </div>
                </div>
            </div>

        </div>
        <br/>

        <div class="row">
            <label>¿Consideras que alguna de las obligaciones legales impuestas será difícil
                de cumplir?</label>
            <br/>

            <div class="radio">
                <label>
                    <input type="radio" class="ace" name="gender"
                           ng-model="m.objView.personalData.gender" value="1"/>
                    <span class="lbl">&nbsp;&nbsp;Si</span>
                </label>
                <br/>
                <label>
                    <input type="radio" class="ace" name="gender"
                           ng-model="m.objView.personalData.gender" value="2"/>
                    <span class="lbl">&nbsp;&nbsp;No</span>
                </label>
            </div>
            <br/>

            <div>
                <label>¿Cual(es) y por qué?</label>
                <br/>
                <textarea class="input-xxlarge form-control limited" name="crimes"
                          ng-model="m.crimes"
                          maxlength="980" data-val="true"
                          data-val-required="Parentesco es un campo requerido">
                </textarea>
                                            <span class="field-validation-valid" data-valmsg-for="crimes"
                                                  data-valmsg-replace="true"></span>
            </div>

        </div>
        <br/>

        <div class="row">
            <div>
                <label>Comentarios</label>
                <br/>
                <textarea class="input-xxlarge form-control limited" name="crimes"
                          ng-model="m.crimes"
                          maxlength="980" data-val="true"
                          data-val-required="Comentarios es un campo requerido">
                </textarea>
                                            <span class="field-validation-valid" data-valmsg-for="crimes"
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
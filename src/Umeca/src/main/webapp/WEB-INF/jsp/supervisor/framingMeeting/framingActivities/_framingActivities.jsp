<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<style>
    .chosen-container {
        width: 100% !important;

    }
</style>
<div class="row" ng-controller="framingActivitiesController">
    <input type="hidden" id="hidUrlAct"
           value="<c:url value="/supervisor/framingMeeting/framingActivities/loadAFramingActivities.json"/>"/>

    <input type="hidden" id="hidIdCaseAct" value="{{fm.objView.idCase}}"/>


    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="pink icon-briefcase  bigger-100"></i> &nbsp;Actividades que realiza
                el imputado </h2>
        </div>
        <br/>

        <div class="row">
            <div ng-show="actSuccessMsg&&actSuccessMsg!=''"
                 class="col-xs-12 alert alert-success element-center success-font">
                {{actSuccessMsg}}
            </div>
            <div ng-show="actErrorMsg&&actErrorMsg!=''" class="alert alert-danger element-center error-font">
                {{actErrorMsg}}
            </div>
        </div>

        <div class="row">

            <form id="FormFramingActivites" name="FormFramingActivites" class="form-horizontal" role="form">

                <div class="widget-box">
                    <div class="widget-header">Ocupaci&oacute;n laboral</div>
                    <div class="widget-body">
                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">
                                <div class="row">
                                    <br/>

                                    <div class="col-xs-4">
                                        <label for="occName">Ocupaci&oacute;n</label>
                                        <br/>
                                        <input id="occName" ng-model="act.occName"
                                               name="occName"
                                               type="text" class="input-xxlarge"
                                               data-val="true"
                                               data-val-required="Ocupaci&oacute;n es un campo requerido"/>
                                        <br/>
            <span class="field-validation-valid" data-valmsg-for="occName"
                  data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="col-xs-4">
                                        <label for="occPlace">Lugar de ocupaci&oacute;n</label>
                                        <br/>
                                        <input id="occPlace" ng-model="act.occPlace"
                                               name="occPlace"
                                               type="text" class="input-xxlarge"
                                               data-val="true"
                                               data-val-required="Lugar de ocupaci&oacute;n es un campo requerido"/>
                                        <br/>
            <span class="field-validation-valid" data-valmsg-for="occPlace"
                  data-valmsg-replace="true"></span>
                                    </div>
                                    <div class="col-xs-4">
                                        <label for="occPhone">Tel&eacute;fono</label>
                                        <br/>
                                        <textarea id="occPhone" ng-model="act.occPhone"
                                                  name="occPhone"
                                                  type="text" class="input-xxlarge"
                                                  data-val="true"
                                                  data-val-required="Tel&eacute;fono es un campo requerido">
                                        </textarea>
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
                    <div class="widget-header">Actividades&nbsp;&nbsp;<label class="info-example">(d&iacute;as,
                        horarios, lugares y personas que frecuenta)</label>
                    </div>
                    <div class="widget-body">
                        <br/>

                        <div class="row">
                            <div class="col-xs-10 col-xs-offset-1">

                                <div class="row">
                                    <div class="col-xs-3 element-left">
                                        &iquest;Qu&eacute; actividades realiza?:
                                    </div>
                                    <div class="col-xs-9 element-left">
                                        <input ng-model="activities" ng-update-hidden type="hidden"
                                               data-val="true"
                                               data-val-required="Las actividades qeu realiza el imputado es un campo requerido"
                                               name="activities">
                                        <select multiple="" class="width-100 chosen-select" ng-model="activityModel"
                                                data-placeholder="..."
                                                ng-init='lstActivity = ${lstActivity};  lstActivitySelec = ${(activity == null) ? '[]' : activity}; selectedActivities(lstActivity,lstActivitySelec);'
                                                id="slctActivityV" ng-change="matchActivities()"
                                                ng-options="ac as ac.name for ac in lstActivity">
                                        </select>
                                                     <span class="field-validation-valid" data-valmsg-for="activities"
                                                           data-valmsg-replace="true"></span>
                                    </div>
                                </div>
                                <br/>

                                <div class="row">
                                    <div ng-repeat="activity in activityModel">
                                        <div ng-show="activity.specification==true">
                                            <div class="col-xs-3">
                                                Especif&iacute;que actividades {{activity.name}}:
                                            </div>
                                            <div class="col-xs-9">
                                                <textarea class="form-control" data-val="true"
                                                          data-val-length="Debe tener al menos 3 y m&aacute;ximo 255 caracteres"
                                                          data-val-length-max="255" data-val-length-min="3"
                                                          data-val-required="La especificaci&oacute;n de actividades {{activity.name}} es un campo requerido"
                                                          type="text" value=""
                                                          ng-model="specification[activity.name]"
                                                          id="specification{{activity.name}}"
                                                          name="specification{{activity.name}}"
                                                          ng-change="matchActivities()"> </textarea><br/>
                <span class="field-validation-valid" data-valmsg-for="specification{{activity.name}}"
                      data-valmsg-replace="true"></span>
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

                <div class="col-xs-8">
                    <label for="activitiesComments">Observaciones</label>
                    <br/>
                    <textarea ng-model="act.activitiesComments"
                              id="activitiesComments"
                              name="activitiesComments"
                              type="text" class="input-xxlarge"
                              data-val="true"
                              data-val-required="Observaciones es un campo requerido">
                    </textarea>
                    <br/>
            <span class="field-validation-valid" data-valmsg-for="activitiesComments"
                  data-valmsg-replace="true"></span>
                </div>

                <br/>
            </form>
        </div>
        <br/>
    </div>

    <div class="col-xs-12">
        <div class="modal-footer" ng-show="fm.objView.canTerminate==true">
        <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
              ng-click="submitIdCaseParam('#FormFramingActivites', '<c:url value="/supervisor/framingMeeting/activities/doUpsert.json?idCase="/>',fm.objView.idCase);">
            <span class="glyphicon glyphicon-cloud-upload"></span>
              Guardar
        </span>
        </div>
    </div>

</div>
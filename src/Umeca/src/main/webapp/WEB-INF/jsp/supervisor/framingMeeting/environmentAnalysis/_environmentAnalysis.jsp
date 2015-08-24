<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="environmentAnalysisController">
    <div class="blocker" ng-show="WaitFor==true">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>
    <input type="hidden" id="loadEnvironmentAnalysis"
           value="<c:url value='/supervisor/framingMeeting/environmentAnalysis/loadEnvironmentAnalysis.json'/>"/>
    <input type="hidden" id="envIdCase" value="{{fm.objView.idCase}}"/>

    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="blue icon-eye-open bigger-100"></i> &nbsp;An&aacute;lisis del entorno
            </h2>
        </div>

        <div class="row">
            <div ng-show="errorMsgEnv&&errorMsgEnv!=''" class="alert alert-danger element-center"
                 ng-bind-html="errorMsgEnv">
            </div>
            <div ng-show="successMsgEnv&&successMsgEnv!=''"
                 class="col-xs-12 alert alert-success element-center success-font"
                 ng-bind-html="successMsgEnv">
            </div>
        </div>

        <div class="row">

            <form id="FormEnvironmentAnalisysId" name="FormEnvironmentAnalisysId" class="form-horizontal" role="form">

                <input type="hidden" name="lstSelectedSources" value="{{lstSelectedSources}}">
                <input type="hidden" name="lstSelectedArrangement" value="{{lstSelectedArrangement}}">
                <input type="hidden" name="lstSelectedRisk" value="{{lstSelectedRisk}}">
                <input type="hidden" name="lstSelectedThreat" value="{{lstSelectedThreat}}">
                <input type="hidden" name="lstSelectedSafetyFactor" value="{{lstSelectedSafetyFactor}}">

                <div class="row" ng-show="${hasTR==true}" ng-init='lstCL = ${lstCL}; lstPR = ${lstPR};'>
                    <br/>

                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header">Instrumento de evaluaci&oacute;n
                                <div class="widget-toolbar">
                                    <a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
                                </div>
                            </div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <table class="table table-bordered table-striped">
                                            <thead class="thin-border-bottom">
                                            <tr>
                                                <th>
                                                    <i class="icon-caret-right blue"></i>
                                                    V&iacute;nculos comunitarios
                                                </th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <tr ng-repeat="cl in lstCL">
                                                <td>{{cl.name}}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-xs-6">
                                        <table class="table table-bordered table-striped">
                                            <thead class="thin-border-bottom">
                                            <tr>
                                                <th>
                                                    <i class="icon-caret-right blue"></i>
                                                    Riesgos procesales
                                                </th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <tr ng-repeat="pr in lstPR">
                                                <td>{{pr.name}}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">V&iacute;nculos / V&iacute;ctimas o Testigos</div>
                            <div class="widget-body">

                                <div class="row">
                                    <br/>

                                    <div ng-show="errorSources&&errorSources!=''"
                                         class="field-validation-error col-xs-10 col-xs-offset-1">
                                        <span>{{errorSources}}</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>

                                        <div class="row" ng-repeat="source in lstSources">
                                            <label ng-if="source.disabled!=true">

                                                <input id="chkSource_{{source.id}}"
                                                       ng-checked='lstSelectedSources.indexOf(source.id)>=0'
                                                       ng-disabled='findDependentSource(source.id) == true;'
                                                       ng-click="selectSource(source.id);"
                                                       class="ace" type="checkbox"
                                                <%--ng-disabled="{{fm.objView.canTerminate==false}}"--%>>
                                                <span class="lbl">&nbsp;&nbsp;{{source.description}}</span>
                                            </label>
                                            <label ng-if="source.disabled==true">

                                                <input checked disabled class="ace" type="checkbox">
                                                <span class="lbl">&nbsp;&nbsp;{{source.description}}</span>
                                            </label>
                                        </div>
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Medidas cautelares</div>
                            <div class="widget-body">
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>

                                        <div class="row" ng-repeat="arrangement in lstSelectedArrangement">
                                            <label>
                                                <i class="icon-check"></i><span
                                                    class="lbl">&nbsp;&nbsp;{{arrangement}}</span>
                                            </label>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <br/>

                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Riesgos</div>
                            <div class="widget-body">
                                <br/>

                                <div class="row">
                                    <div ng-show="errorRisks&&errorRisks!=''"
                                         class="field-validation-error col-xs-10 col-xs-offset-1">
                                        <span>{{errorRisks}}</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>

                                        <div class="row" ng-repeat="risk in lstRisk">
                                            <label>
                                                <input id="chkRisk_{{risk.id}}"
                                                       ng-click="selectRisk(risk.id);"
                                                       ng-checked='lstSelectedRisk.indexOf(risk.id)>=0'
                                                       class="ace"
                                                       type="checkbox"
                                                <%--ng-disabled="{{fm.objView.canTerminate==false}}"--%>>
                                                <span class="lbl">&nbsp;&nbsp;{{risk.description}}</span>
                                            </label>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">

                        <div class="widget-box">
                            <div class="widget-header">Amenazas</div>
                            <div class="widget-body">
                                <div class="row">
                                    <br/>

                                    <div ng-show="errorThreats&&errorThreats!=''"
                                         class="field-validation-error col-xs-10 col-xs-offset-1">
                                        <span>{{errorThreats}}</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>

                                        <div class="row" ng-repeat="threat in lstThreat">
                                            <label>
                                                <input id="chkThreat_{{threat.id}}"
                                                       ng-click="selectThreat(threat.id);"
                                                       ng-checked='lstSelectedThreat.indexOf(threat.id)>=0'
                                                       class="ace"
                                                       type="checkbox"
                                                <%--ng-disabled="{{fm.objView.canTerminate==false}}"--%>>
                                                <span class="lbl">&nbsp;&nbsp;{{threat.description}}</span>
                                            </label>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-6 col-xs-offset-3">

                        <div class="widget-box">
                            <div class="widget-header">Factores estabilidad</div>
                            <div class="widget-body">
                                <div class="row">
                                    <br/>

                                    <div ng-show="errorSafetyFactors&&errorSafetyFactors!=''"
                                         class="field-validation-error col-xs-10 col-xs-offset-1">
                                        <span>{{errorSafetyFactors}}</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>

                                        <div class="row" ng-repeat="safetyFactor in lstSafetyFactor">
                                            <label>
                                                <input id="chkSafetyFactor_{{safetyFactor.id}}"
                                                       ng-click="selectSafetyFactor(safetyFactor.id);"
                                                       ng-checked='lstSelectedSafetyFactor.indexOf(safetyFactor.id)>=0'
                                                       class="ace"
                                                       type="checkbox">

                                                <span class="lbl">&nbsp;&nbsp;{{safetyFactor.description}}</span>
                                            </label>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="col-xs-8">
                    <label for="environmentComments">Observaciones</label>
                    <br/>
                    <textarea ng-model="environmentComments"
                              id="environmentComments"
                              name="environmentComments"
                              type="text" class="input-xxlarge"
                              data-val="true"
                              data-val-required="Observaciones es un campo requerido">

                    </textarea>
                    <br/>

                    <div ng-show="errorComments&&errorComments!=''"
                         class="field-validation-error col-xs-10">
                        <span>{{errorComments}}</span>
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
              ng-click="submitIdCaseParam('#FormEnvironmentAnalisysId', '<c:url value="/supervisor/framingMeeting/environmentAnalysis/doUpsert.json?idCase="/>',fm.objView.idCase);">
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
<script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
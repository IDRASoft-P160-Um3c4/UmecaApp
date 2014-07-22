<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="environmentAnalysisController">

    <input type="hidden" id="loadEnvironmentAnalysis"
           value="<c:url value='/supervisor/framingMeeting/environmentAnalysis/loadEnvironmentAnalysis.json'/>"/>
    <input type="hidden" id="envIdCase" value="{{fm.objView.idCase}}"/>

    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="blue icon-eye-open bigger-100"></i> &nbsp;Analisis del entorno
            </h2>
        </div>

        <div class="row">
            <div ng-show="errorMsg&&errorMsg!=''" class="alert alert-danger element-center">
                {{errorMsg}}
            </div>
            <div ng-show="successMsg&&successMsg!=''" class="col-xs-12 alert alert-success element-center success-font">
                {{successMsg}}
            </div>
        </div>

        <div class="row">

            <form id="FormEnvironmentAnalisysId" name="FormEnvironmentAnalisysId" class="form-horizontal" role="form">

                <input type="hidden" name="lstSelectedSources" value="{{lstSelectedSources}}">
                <input type="hidden" name="lstSelectedArrangement" value="{{lstSelectedArrangement}}">
                <input type="hidden" name="lstSelectedRisk" value="{{lstSelectedRisk}}">
                <input type="hidden" name="lstSelectedThreat" value="{{lstSelectedThreat}}">

                <div class="row">
                    <br/>

                    <div class="col-xs-6">
                        <div class="widget-box">
                            <div class="widget-header">Vinculos</div>
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
                                            <label>
                                                <input id="chkSource_{{source.id}}"
                                                       ng-checked='lstSelectedSources.indexOf(source.id)>=0'
                                                       ng-click="selectSource(source.id);"
                                                       class="ace" type="checkbox"
                                                        ng-disabled="{{fm.objView.canTerminate==false}}">
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
                                                <i class="icon-check"></i><span class="lbl">&nbsp;&nbsp;{{arrangement}}</span>
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
                                    <div ng-show="errorRisks&&errorRisks!=''" class="field-validation-error col-xs-10 col-xs-offset-1">
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
                                                       ng-disabled="{{fm.objView.canTerminate==false}}">
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
                                                       ng-disabled="{{fm.objView.canTerminate==false}}">
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

            </form>
        </div>
        <br/>

    </div>

    <div class="col-xs-12">
        <div class="modal-footer" ng-show="fm.objView.canTerminate==true">
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<div class="row" ng-controller="environmentAnalysisController">

    <input type="hidden" id="loadEnvironmentAnalysis" value="<c:url value='/supervisor/framingMeeting/environmentAnalysis/loadEnvironmentAnalysis.json'/>"/>
    <input type="hidden" id="envIdCase" value="{{fm.objView.idCase}}"/>

    <div class="col-xs-10 col-xs-offset-1">

        <div class="row element-center">
            <h2><i class="blue icon-eye-open bigger-100"></i> &nbsp;Analisis del entorno {{fm.objView.idCase}}
            </h2>
        </div>

        <div class="row">
            <div ng-show="errorMsg&&errorMsg!=''"class="alert alert-danger element-center">
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
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>

                                        <div class="row" ng-repeat="source in lstSources">
                                                <label>
                                                    <input id="chkSource_{{source.id}}"
                                                           ng-checked='lstSelectedSources.indexOf(source.id)>=0'
                                                            ng-click="selectSource(source.id);"
                                                           class="ace" type="checkbox">
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
                                        <div class="row" ng-repeat="arrangement in lstArrangements">
                                            <label>
                                                <input ng-click="selectArrangement(arrangement.id);"
                                                       class="ace"
                                                       type="checkbox">
                                                <span class="lbl">&nbsp;&nbsp;{{arrangement.description}}</span>
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
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>
                                        <div class="row" ng-repeat="risk in lstRisk">
                                            <label>
                                                <input ng-click="selectRisk(risk.id);"
                                                       class="ace"
                                                       type="checkbox">
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
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <br/>
                                        <div class="row" ng-repeat="threat in lstThreat">
                                            <label>
                                                <input ng-click="selectThreat(threat.id);"
                                                       class="ace"
                                                       type="checkbox">
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
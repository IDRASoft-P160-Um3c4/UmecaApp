<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/framingMeetingLogCtrl.js"></script>

    <title>Registro de cambios en entrevista de encuadre</title>
</head>
<body scroll="no" ng-app="ptlUmc" style="width: 1100px; margin: auto" class="element-center">

<div class="container body-content" ng-controller="framingMeetingLogController">
    <div class="container body-content">

        <div ng-init='lstAlterLog = ${lstAlterLog}; lstDayChanges=${lstDayChanges}; lstTerminateLog=${lstTerminateLog};'>
            <h4 class="element-center">REGISTRO DE CAMBIOS EN ENTREVISTA DE ENCUADRE</h4>


            <div class="widget-box">
                <div class="widget-header"><b>ENTREVISTA DE ENCUADRE FINALIZADA</b>

                    <div class="widget-toolbar">
                        <a data-action="collapse" href="#"><i class="icon-chevron-up"></i></a>
                    </div>
                </div>
                <div class="widget-body">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="profile-user-info profile-user-info-striped">
                                    <div class="profile-info-row" ng-repeat="terminateLog in lstTerminateLog">
                                        <div class="profile-info-name three-lines" style='width: 150px;'>
                                            <b>&nbsp;<span ng-bind-html='formatHtml(terminateLog.name)'>
                                            </span> &nbsp; &nbsp;</b>
                                        </div>
                                        <div class="profile-info-value element-left three-lines"
                                             style='padding-left: 50px !important'>
                                            <div ng-repeat="elmnt in terminateLog.lstElements">
                                                <div ng-if="elmnt.newRow!=true">
                                                    <b><span ng-bind-html='formatHtml(elmnt.fieldName)'>
                                                    </span></b>:&nbsp;<span ng-bind-html='formatHtml(elmnt.value)'>
                                                    </span> &nbsp;
                                                </div>
                                                <div ng-if="elmnt.newRow==true">
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="widget-box collapsed" ng-repeat="actDay in lstFinalLog track by $index">
                <div class="widget-header">Cambios realizados el {{actDay.date}}
                    <div class="widget-toolbar">
                        <a data-action="collapse" href="#"><i class="icon-chevron-down"></i></a>
                    </div>
                </div>
                <div class="widget-body">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="profile-user-info profile-user-info-striped">
                                    <div class="profile-info-row" ng-repeat="actLog in actDay.lstAlterLog">
                                        <div class="profile-info-name three-lines" style='width: 150px;'>
                                            <b>&nbsp;<span ng-bind-html='formatHtml(actLog.name)'>
                                            </span> &nbsp; &nbsp;</b>
                                            <b><br/>&nbsp; &nbsp;-&nbsp;
                                                &nbsp;<span>{{getLogType(actLog.logType)}}</span></b>
                                        </div>
                                        <div class="profile-info-value element-left three-lines"
                                             style='padding-left: 50px !important'>
                                            <div ng-repeat="elmnt in actLog.lstElements">
                                                <div ng-if="elmnt.newRow!=true">
                                                    <b><span ng-bind-html='formatHtml(elmnt.fieldName)'>
                                            </span></b>:&nbsp;<span ng-bind-html='formatHtml(elmnt.value)'>
                                            </span> &nbsp;
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <br/>

            <div class="row">
                <div class="col-xs-12">
                    <div ng-show="MsgError" class="alert-danger element-center">
                        {{MsgError}}
                    </div>
                </div>
                <br/>
            </div>

        </div>
    </div>
</div>
</body>
</html>

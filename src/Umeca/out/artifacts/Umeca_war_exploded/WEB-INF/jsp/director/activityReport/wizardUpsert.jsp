<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>

    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/commonActMonPlan.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardActivityUpsert.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardReport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardActivity.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardSupervision.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardEvaluation.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardDirector.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardProject.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardChanneling.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/activityReport/wizardHumanRes.js"></script>

    <script>
        $(document).ready(function () {
            var date = new Date();
            $('#date-pk-act-start,#date-pk-act-end').datepicker({autoclose: true}).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });
        });
    </script>

    <title>Informe de actividades del coordinador para la direcci&oacute;n</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="wizardActivityUpsertController"
     ng-init="url='<c:url value='/director/activityReport/doWizardUpsert.json' />'; urlBack='<c:url value='/director/activityReport/index.html'/>'">

    <h2 class="element-center"><i class="glyphicon glyphicon-flash"></i>
        &nbsp;&nbsp;Asistente para generar el reporte de actividades</h2>

    <div class="row" >
        <div class="col-xs-8 col-xs-offset-2">
            <div class="alert alert-danger element-center" ng-show="MsgErr">
                {{MsgErr}}
            </div>
        </div>
        <div class="col-xs-2 element-center">
            <button type="button" class="btn btn-warning" ng-click="end()">
                Terminar&nbsp;&nbsp;<i class="glyphicon glyphicon-check"></i></button>
        </div>
    </div>


    <div class="blocker" ng-show="working">
        <div>
            Procesando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="widget-box">
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="row">
                            <div class="col-xs-12">
                                <ul class="nav nav-tabs nav-list nav-justified" id="myTab">
                                    <li ng-class="tabItem === 'report' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'report'" href="#report">
                                            <i class="brown glyphicon glyphicon-file" style="margin-bottom: 5px !important;"></i>
                                            Informe
                                        </a>
                                    </li>
                                    <li ng-class="tabItem === 'activity' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'activity'" href="#activity">
                                            <i class="green glyphicon glyphicon-tasks" style="margin-bottom: 5px !important;"></i>
                                            Actividades
                                        </a>
                                    </li>
                                    <li ng-class="tabItem === 'supervision' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'supervision'" href="#supervision">
                                            <i class="purple glyphicon glyphicon-check" style="margin-bottom: 7px !important;"></i>
                                            Supervisi&oacute;n
                                        </a>
                                    </li>
                                    <li ng-class="tabItem === 'evaluation' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'evaluation'" href="#evaluation">
                                            <i class="blue glyphicon glyphicon-map-marker" style="margin-bottom: 7px !important;"></i>
                                            Evaluaci&oacute;n
                                        </a>
                                    </li>
                                    <li ng-class="tabItem === 'management' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'management'" href="#management">
                                            <i class="grey glyphicon glyphicon-home" style="margin-bottom: 7px !important;"></i>
                                            Direcci&oacute;n
                                        </a>
                                    </li>
                                    <li ng-class="tabItem === 'project' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'project'" href="#project">
                                            <i class="dark glyphicon glyphicon-road" style="margin-bottom: 7px !important;"></i>
                                            Proyectos
                                        </a>
                                    </li>
                                    <li ng-class="tabItem === 'channeling' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'channeling'" href="#channeling">
                                            <i class="orange glyphicon glyphicon-user" style="margin-bottom: 7px !important;"></i>
                                            Canalizaci&oacute;n
                                        </a>
                                    </li>
                                    <li ng-class="tabItem === 'minute' ? 'active' : ''">
                                        <a data-toggle="tab" ng-click="tabItem = 'minute'" href="#minute">
                                            <i class="red glyphicon glyphicon-sound-dolby" style="margin-bottom: 7px !important;"></i>
                                            Minutas
                                        </a>
                                    </li>

                                </ul>
                                <div class="tabbable">

                                    <div class="tab-content">
                                        <div id="report" ng-class="tabItem === 'report' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardReport.jsp" %>
                                        </div>

                                        <div id="activity" ng-class="tabItem === 'activity' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardActivity.jsp" %>
                                        </div>

                                        <div id="supervision" ng-class="tabItem === 'supervision' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardSupervision.jsp" %>
                                        </div>

                                        <div id="evaluation" ng-class="tabItem === 'evaluation' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardEvaluation.jsp" %>
                                        </div>

                                        <div id="management" ng-class="tabItem === 'management' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardDirector.jsp" %>
                                        </div>

                                        <div id="project" ng-class="tabItem === 'project' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardProject.jsp" %>
                                        </div>

                                        <div id="channeling" ng-class="tabItem === 'channeling' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardChanneling.jsp" %>
                                        </div>

                                        <div id="minute" ng-class="tabItem === 'minute' ? 'tab-pane fade in active' : 'tab-pane fade'">
                                            <%@ include file="/WEB-INF/jsp/director/activityReport/wizardHumanRes.jsp" %>
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

    <div id="angJsjqGridId" ng-controller="modalDlgController"></div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>
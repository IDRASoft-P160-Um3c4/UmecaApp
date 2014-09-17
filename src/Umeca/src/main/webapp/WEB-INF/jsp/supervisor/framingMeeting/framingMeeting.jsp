<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/chosen.min.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/chosen.jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/framingMeetingCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/housemate/housemateCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/references/referencesCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/environmentAnalysis/environmentAnalysisCtrl.js"></script>
    <%--<script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/processAcompaniment/processAccompanimentCtrl.js"></script>--%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/framingActivities/framingActivitiesCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/addtionalQuestions/additionalQuestionsCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/personalData/personalDataCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/address/addressCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/drugs/drugsCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
    </script>


    <title>Entrevista de encuadre</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" id="divFM" ng-controller="framingMeetingController"
     ng-init='fm.objView=${objView}; returnId=${returnId};
     urlManagerSup="<c:url value='/supervisor/showCaseSupervision/index.html'/>";
     urlIndex="<c:url value='/supervisor/framingMeeting/index.html'/>";'
     ng-cloak>
    <input type="hidden" name="idFolder" value="{{fm.objView.idFolder}}">
    <br/>

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevista de encuadre</h2>


    <div class="row">
        <div class="col-xs-4">

            <h3 class="header smaller lighter blue">
                <small>N&uacute;mero de carpeta <br/> de investigaci&oacute;n:</small>
                &nbsp;&nbsp;&nbsp;&nbsp;{{fm.objView.idFolder}}
            </h3>
        </div>

    </div>

    <div class="row">
        <div ng-show="FMsuccessMsg&&FMsuccessMsg!=''" class="col-xs-12 alert alert-success element-center success-font">
            {{FMsuccessMsg}}
        </div>
        <div ng-show="FMerrorMsg&&FMerrorMsg!=''" class="alert alert-danger element-center error-font">
            {{FMerrorMsg}}
        </div>
        <div ng-show="FMerrorMsgLst&&FMerrorMsgLst.length>0" class="alert alert-danger element-center error-font">
            <span ng-repeat="error in FMerrorMsgLst track by $index">{{error}}<br/></span>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabFramingMeeting">

                    <li class="active">
                        <a data-toggle="tab" href="#personalData">
                            <i class="purple glyphicon glyphicon-user bigger-200"></i>&nbsp;&nbsp;
                            Datos personales y entorno social
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#address">
                            <i class="green icon-home  bigger-200"/></i>
                            Domicilios
                        </a>
                    </li>

                    <!--<li>
                        <a data-toggle="tab" href="#accompaniment" ng-click="resizeMap()">
                            <i class="red glyphicon glyphicon-user bigger-200"></i>&nbsp;&nbsp;
                            Persona que acompa&ntilde;ara durante <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;el proceso
                        </a>
                    </li>-->

                    <li>
                        <a data-toggle="tab" href="#houseMate">
                            <i class="blue icon-group bigger-200"></i>
                            Personas que viven con el imputado
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#references">
                            <i class="purple icon-list bigger-200"></i>
                            Referencias personales
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#activities">
                            <i class="pink icon-briefcase  bigger-200"></i>
                            Actividades que realiza el imputado
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#drugs">
                            <i class="green icon-warning-sign  bigger-200"></i>
                            Consumo de sustancias
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#environmentAnalysis">
                            <i class="blue icon-eye-open bigger-200"></i>
                            An&aacute;lisis de entorno
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#addtional">

                            <i class="red icon-list bigger-200"></i>
                            Formulario de preguntas al supervisado
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#fingerTab">

                            <i class="icon-edit bigger-200"></i>
                            Enrolamiento
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="personalData" class="tab-pane  in active">
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/personalData/_personalData.jsp" %>
                    </div>

                    <div id="address" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/framingAddress/_framingAddress.jsp" %>
                    </div>

                    <%--<div id="accompaniment" class="tab-pane">--%>
                    <%--<%@ include--%>
                    <%--file="/WEB-INF/jsp/supervisor/framingMeeting/proccessAccompaniment/_processAccompaniment.jsp" %>--%>
                    <%--</div>--%>


                    <div id="houseMate" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/housemate/_framingHousemate.jsp" %>
                    </div>

                    <div id="references" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/references/_framingReferences.jsp" %>
                    </div>

                    <div id="activities" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/supervisor/framingMeeting/framingActivities/_framingActivities.jsp" %>
                    </div>

                    <div id="drugs" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/drugs/_drugs.jsp" %>
                    </div>

                    <div id="environmentAnalysis" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/supervisor/framingMeeting/environmentAnalysis/_environmentAnalysis.jsp" %>
                    </div>

                    <div id="addtional" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/supervisor/framingMeeting/additionalQuestions/_addtionalQuestions.jsp" %>
                    </div>

                    <div id="fingerTab" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/fingerPrinting/index.jsp" %>
                    </div>

                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="modal-footer" ng-show="fm.objView.canTerminate==true">
                    <span class="btn btn-default btn-sm" ng-click="returnFM();">
                        Regresar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="doTerminate();">
                          Terminar
                    </span>
        </div>
        <div class="modal-footer" ng-show="fm.objView.canTerminate==false">
                    <span class="btn btn-default btn-sm" ng-click="returnFM();">
                        Regresar
                    </span>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>
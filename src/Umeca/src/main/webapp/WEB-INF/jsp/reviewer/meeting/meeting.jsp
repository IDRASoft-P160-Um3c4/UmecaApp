<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
* Project: Umeca
* User: Israel
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/chosen.min.css" />
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/chosen.jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css" />
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/personalDataCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/socialNetworkCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/referenceCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/drugCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/scheduleCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/schoolCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/jobCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/addressCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/leavingCountryCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/meetingCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/loadStatesDrct.js.js"></script>
      <script>
          window.cancelMeeting = function (){
              window.goToUrlMvcUrl("<c:url value='/reviewer/meeting/index.html'/>");
          }
      </script>

    <title>Entrevista</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="meetingController">

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevista de evaluación de riesgos procesales</h2>

    <div class="row">
        <div class="col-sm-3">
            <h3 class="header smaller lighter blue">
                <small>Número de carpeta <br/> de investigación:  </small>
                &nbsp;&nbsp;&nbsp;&nbsp;${m.caseDetention.idFolder}
            </h3>
        </div>
        <div class="col-sm-7 col-sm-offset-1">
            <h3 class="header smaller lighter blue">
                <small><br/>Nombre del imputado:  </small>
                &nbsp;${m.imputed.name} &nbsp; ${m.imputed.lastNameP} &nbsp; ${m.imputed.lastNameM}
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabMeeting">
                    <li class="active">
                        <a data-toggle="tab" href="#personalData">
                            <i class="purple glyphicon glyphicon-user bigger-200"></i>
                            Datos personales y <br/>    <div class="col-xs-offset-3"> Entorno social</div>
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#address">
                            <i class="green  icon-home  bigger-200"></i>
                            Domicilios
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#socialNetwork">
                            <i class="blue icon-group bigger-200"></i>
                            Red social
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#personalReferences">
                            <i class="red icon-list bigger-200"></i>
                            Referencias personales
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#jobHistory">
                            <i class="pink icon-briefcase  bigger-200"></i>
                            Historia laboral
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#school">
                            <i class="orange icon-book  bigger-200"></i>
                            Historia Escolar
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#drugsSection">
                            <i class="green icon-warning-sign  bigger-200"></i>
                            Consumo de sustancias
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#leaveCountry">
                            <i class="blue icon-globe  bigger-200"></i>
                            Facilidad de &nbsp;&nbsp;&nbsp;&nbsp; <i class="icon-exclamation-sign red bigger-100" ng-show="validf['form2'] == true" ng-init="validf['form2'] = false"></i>
                            <br/><div class="col-xs-offset-3">abandonar el país</div>

                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="personalData" class="tab-pane in active">
                      <%@ include file="/WEB-INF/jsp/reviewer/meeting/_personalData.jsp"%>
                    </div>
                    <div id="address" class="tab-pane">
                      <%@ include file="/WEB-INF/jsp/reviewer/meeting/address/index.jsp"%>
                    </div>
                    <div id="socialNetwork" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/socialNetwork/index.jsp"%>
                    </div>
                    <div id="personalReferences" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/reference/index.jsp"%>
                    </div>
                    <div id="jobHistory" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/job/index.jsp"%>
                    </div>
                    <div id="school" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/school/index.jsp"%>
                    </div>
                    <div id="drugsSection" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/drug/index.jsp"%>
                    </div>
                    <div id="leaveCountry" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/leavingCountry.jsp"%>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-12">
            <div ng-show="MsgError" class="alert alert-danger element-center">
                {{MsgError}}
            </div>
        </div>
    </div>
    <div class="row">
        <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelMeeting()">
                        Regresar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormSchool , #FormPersonalData, #FormLeaveCountry','<c:url value="/reviewer/meeting/terminateMeeting.json?idCase=${idCase}"/>');">
                          Terminar
                    </span>
        </div>
    </div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>
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
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/personalDataCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/socialNetworkCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/referenceCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/drugCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/scheduleCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/schoolCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/jobCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/addressCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/leavingCountryCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/meetingCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/upsertMeetingCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/showMessageErrorDrct.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
    </script>

    <script>
        window.cancelMeeting = function () {
            window.goToUrlMvcUrl("<c:url value='/reviewer/meeting/index.html'/>");
        };

        function controller($scope) {
            $scope.showflag = true;
            setTimeout(function () {
                $scope.$apply(function () {
                    $scope.showflag = false;
                });
            }, 1000);
        }
    </script>
    <style>
        .width-100 {
            max-width: none !important;
            width: 100% !important;
        }

    </style>
    <title>Entrevista</title>
</head>
<body scroll="no" ng-app="ptlUmc" ng-cloak>
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content">
    <div ng-controller="meetingController">

        <div class="blocker" ng-show="WaitFor==true">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>

        <div id="divErrorMessage" class="alert alert-danger" style="display: none;">
            <button type="button" class="close" ng-click="hideMessageError();">
                <i class="icon-remove"></i>
            </button>
            <br/>
            <span ng-bind-html="listMsgError[entityError]"></span>
            <br/>
        </div>
        <div class="row">
            <br/>

            <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevista de evaluaci&oacute;n
                de riesgos
                procesales</h2>
        </div>
        <br/>

        <div class="row">
            <div class="col-sm-3">
                <h3 class="header smaller lighter blue">
                    <small>Carpeta de investigaci&oacute;n:</small>
                    &nbsp;${m.caseDetention.idFolder}
                </h3>
            </div>
            <div class="col-sm-8"
                 ng-init="i.name='${m.imputed.name}'; i.lastNameP='${m.imputed.lastNameP}'; i.lastNameM ='${m.imputed.lastNameM}';">
                <h3 class="header smaller lighter blue">
                    <small>Nombre del imputado:</small>
                    &nbsp;&nbsp;{{i.name}} &nbsp; {{i.lastNameP}} &nbsp; {{i.lastNameM}}
                </h3>
            </div>
            <div class="col-sm-1">
                <h3 class="header smaller lighter blue">
                    <small>Edad:</small>
                    &nbsp;${age}
                </h3>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-4">
                <h3 class="header smaller lighter blue">
                    <small>Inicio:</small>
                    &nbsp;${tStart}
                </h3>
            </div>
            <div class="col-sm-4">
                <h3 class="header smaller lighter blue">
                    <small>Fin:</small>
                    &nbsp;${tEnd}
                </h3>
            </div>
            <div class="col-sm-4">
                <h3 class="header smaller lighter blue">
                    <small>Evaluador:</small>
                    &nbsp;${reviewerFullname}
                </h3>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="tabbable tabs-left">
                    <ul class="nav nav-tabs" id="tabMeeting">
                        <li class="active" id="liPersonalData">
                            <a data-toggle="tab" href="#personalData">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="purple glyphicon glyphicon-user bigger-200"></i>
                                        Datos personales
                                        <br/>

                                        <div class="col-xs-offset-3">y entorno social</div>
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['personalData']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                   ng-click="showMessageError('personalData');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>

                        <li id="liImputedHome">
                            <a data-toggle="tab" href="#address">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="green  icon-home  bigger-200"></i>
                                        Domicilios <br/>
                                        <label class="info-example">De los &uacute;ltimos 5 a&ntilde;os</label>
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['imputedHome']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120"
                                                   ng-click="showMessageError('imputedHome');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>

                        <li id="liSocialNetwork">
                            <a data-toggle="tab" href="#socialNetwork" style="z-index: 0;">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="blue icon-group bigger-200"></i>
                                        Red social <br/>
                                        <label class="info-example">Personas con las que vive</label>
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['socialNetwork']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120"
                                                   ng-click="showMessageError('socialNetwork');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li id="liReference">
                            <a data-toggle="tab" href="#personalReferences" style="z-index: 1;">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="red icon-list bigger-200"></i>
                                        Referencias personales <br/>
                                        <label class="info-example">Personas con las que no vive</label>
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['reference']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120"
                                                   ng-click="showMessageError('reference');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li id="liJob">
                            <a data-toggle="tab" href="#jobHistory" style="z-index: 0;">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="pink icon-briefcase  bigger-200"></i>
                                        Historia laboral<br/>
                                        <label class="info-example">&Uacute;ltimos 3-5 empleos</label>
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['job']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120"
                                                   ng-click="showMessageError('job');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li id="liSchool">
                            <a data-toggle="tab" href="#school" style="z-index: 1;">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="orange icon-book  bigger-200"></i>
                                        Historia escolar
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['school']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120"
                                                   ng-click="showMessageError('school');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li id="liDrug">
                            <a data-toggle="tab" href="#drugsSection" style="z-index: 0;">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="green icon-warning-sign  bigger-200"></i>
                                        Consumo de sustancias
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['drug']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120"
                                                   ng-click="showMessageError('drug');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li id="liLeaveCountry">
                            <a data-toggle="tab" href="#leaveCountry" style="z-index: 0;">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="blue icon-globe  bigger-200"></i>
                                        Facilidad de
                                        <br/>

                                        <div class="col-xs-offset-3">abandonar el pa&iacute;s</div>
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['leavingCountry']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120"
                                                   ng-click="showMessageError('leavingCountry');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="personalData" class="tab-pane in active">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/personal/index.jsp" %>
                        </div>
                        <div id="address" class="tab-pane">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/address/index.jsp" %>
                        </div>
                        <div id="socialNetwork" class="tab-pane">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/socialNetwork/index.jsp" %>
                        </div>
                        <div id="personalReferences" class="tab-pane">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/reference/index.jsp" %>
                        </div>
                        <div id="jobHistory" class="tab-pane">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/job/index.jsp" %>
                        </div>
                        <div id="school" class="tab-pane">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/school/index.jsp" %>
                        </div>
                        <div id="drugsSection" class="tab-pane">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/drug/index.jsp" %>
                        </div>
                        <div id="leaveCountry" class="tab-pane">
                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/leavingCountry/index.jsp" %>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <br/>

        <div class="row">
            <div class="col-xs-12">
                <div ng-show="listMsgError['general']" class="alert alert-danger element-center error-font">
            <span ng-bind-html="listMsgError['general']">
            </span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelMeeting()">
                        Regresar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-click="hideMessageError();" ng-confirm-action
                          confirm-message="&iquest;Est&aacute; seguro que desea terminar la entrevista de riesgos procesales?"
                          confirm-title="Terminar entrevista" confirm-type="info"
                          confirmed-click-action="submit('#FormSchool , #FormPersonalData, #FormLeaveCountry, #FormCommentHomeId, #FormCommentReferenceId, #FormCommentJobId, #FormSocialNetworkIndexId, #FormCommentDrugId','<c:url value="/reviewer/meeting/terminateMeeting.json?idCase=${idCase}"/>');">
                          Terminar
                    </span>

            </div>
        </div>


        <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
        <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
    </div>
</div>
</body>
</html>
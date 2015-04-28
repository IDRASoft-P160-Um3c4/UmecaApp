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
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/scheduleVerifCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/schoolCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/jobCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/addressCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/leavingCountryCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/meetingCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/showMessageErrorDrct.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>


    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/choiceInformationCtrl.js"></script>

    <style>
        body {
            font-size: 11px;
        }

        .icon-list {
            cursor: pointer;
        }


    </style>
    <script>
        window.cancelViewMeeting = function () {
            window.goToUrlMvcUrl("<c:url value='/managereval/showCaseEvaluation/index.html'/>");
        }

        window.cancelChoiceInformation = function () {
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/sources.html?id=${idCase}'/>");
        }

        window.showChoicesSection = function (idSection, idList) {
            var divScope = "#divChoiceInformation";
            var urlToGo = "<c:url value='/reviewer/verification/showChoicesBySection.html'/>";
            var scope = angular.element($(divScope)).scope();
            var data = {};
            data.id = idSection;
            data.idCase = ${idCase};
            if (id != undefined) {
                data.idList = id;
            }
            scope.show(data, urlToGo, undefined, undefined, true);
        }
        window.terminateVerification = function () {
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/index.html'/>");
        }
        window.showChoices = function (code, id) {
            var divScope = "#divChoiceInformation";
            var urlToGo = "<c:url value='/reviewer/verification/showChoices.html'/>";
            var scope = angular.element($(divScope)).scope();
            var data = {};
            data.id = code;
            data.idCase = ${idCase};
            if (id != undefined) {
                data.idList = id;
            }
            scope.show(data, urlToGo, undefined, undefined, true);
        };

        window.closeShowChoicesSection = function () {
            $("#VerifBySectionDialog").modal("hide");
        }

    </script>
    <title>Usuarios</title>

</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content">
    <div ng-controller="modalDlgController" id="divChoiceInformation">
        <div ng-controller="choiceInformationController">
            <div id="divErrorMessage" class="alert alert-danger" style="display: none;">
                <button type="button" class="close" ng-click="hideMessageError();">
                    <i class="icon-remove"></i>
                </button>
                <br/>
                <span ng-bind-html="listMsgError[entityError]"></span>
                <br/>
            </div>

            <div id="VerifBySectionDialog" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div>
                                <h4 class="modal-title element-center"><span ng-bind-html="TitleV"
                                                                             class="label label-lg label-{{TypeV}}"
                                                                             style="font-size: large; display: block !important;"></span>
                                </h4>
                            </div>
                        </div>
                        <div class="modal-body">
                            <div class="element-left" ng-bind-html="MessageV" ng-init="commentVerifSection = '';"></div>
                            <br/>
                            Raz&oacute;n por la que establece la informaci&oacute;n: <br/>
                <textarea class="form-control" id="commentVerifSection" ng-model="commentVerifSection">
                </textarea>
                        </div>
                        <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.closeShowChoicesSection()">
                        Cancelar
                    </span>
                    <span class="btn btn-primary btn-sm" ng-disabled="WaitForChoice==true || commentVerifSection ==''"
                          ng-click="sendVerifSection('<c:url value="/reviewer/verification/showChoicesBySection.json"/>', ${idCase})">
                          Guardar
                    </span>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" ng-init="urlVerifNotKnow= '<c:url value="/reviewer/verification/showChoices.json"/>'"
                   ng-model="urlShowChoices"
                   ng-update-hidden>

            <h2 class="element-center"><i class="glyphicon icon-list"></i>&nbsp;&nbsp;Elecci&oacute;n de informaci&oacute;n
            </h2>
            <%@ include file="/WEB-INF/jsp/reviewer/meeting/imputedName.jsp" %>
            <br/>

            <div class="row" ng-init="readOnly = ${managereval}">
                <div class="col-sm-12">
                    <div class="tabbable tabs-left">
                        <ul class="nav nav-tabs" id="tabMeeting">
                            <li class="active" id="liImputed">
                                <a data-toggle="tab" href="#personalData">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <i class="purple glyphicon glyphicon-user bigger-200"></i>
                                            Datos personales
                                            <br/>

                                            <div class="col-xs-offset-3">y entorno social</div>
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['imputed']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('imputed');"></i>
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
                                            Domicilios
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['imputedHome']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('imputedHome');"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li id="liSocialNetwork">
                                <a data-toggle="tab" href="#socialNetwork">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <i class="blue icon-group bigger-200"></i>
                                            Red social
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['socialNetwork']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('socialNetwork');"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li id="liReference">
                                <a data-toggle="tab" href="#reference">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <i class="red icon-list bigger-200"></i>
                                            Referencias personales
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['reference']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('reference');"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li id="liJob">
                                <a data-toggle="tab" href="#jobHistory">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <i class="pink icon-briefcase  bigger-200"></i>
                                            Historia laboral
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['job']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('job');"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li id="liSchool">
                                <a data-toggle="tab" href="#academyHistory">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <i class="orange icon-book  bigger-200"></i>
                                            Historia escolar
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['school']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('school');"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li id="liDrug">
                                <a data-toggle="tab" href="#drugs">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <i class="green icon-warning-sign  bigger-200"></i>
                                            Consumo de sustancias
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['drug']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('drug');"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li id="liLeaveCountry">
                                <a data-toggle="tab" href="#leaveCountry">
                                    <div class="row">
                                        <div class="col-xs-10">
                                            <i class="blue icon-globe  bigger-200"></i>
                                            Facilidad de
                                            <br/>

                                            <div class="col-xs-offset-3">abandonar el pa&iacute;s</div>
                                        </div>
                                        <div class="col-xs-2" ng-show="listMsgError['leaveCountry']">
                                            <div class="tools">
                                                <div class="inline position-relative">
                                                    <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                       ng-click="showMessageError('leaveCountry');"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                        </ul>

                        <div class="tab-content">
                            <div id="personalData" class="tab-pane in active">
                                <div class="center">
                                    <h2><i class="purple glyphicon glyphicon-user bigger-100"></i> &nbsp;Datos
                                        personales y
                                        entorno social</h2>

                                    <div ng-init="i.name='${m.imputed.name}'; i.lastNameP='${m.imputed.lastNameP}'; i.lastNameM ='${m.imputed.lastNameM}';">
                                        <div class="col-xs-10 col-xs-offset-1">
                                            <br/>
                                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/personal/content.jsp" %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="address" class="tab-pane">
                                <div class="row center">
                                    <h2><i class="green  icon-home  bigger-100"></i>&nbsp;</i>Domicilios</h2>
                                </div>
                                <br/>
                                <%@ include file="/WEB-INF/jsp/reviewer/verification/imputedHome/accordeon.jsp" %>
                            </div>
                            <div id="socialNetwork" class="tab-pane">
                                <div class="row center">
                                    <h2><i class="blue icon-group bigger-100"></i>
                                        Red social </h2>
                                </div>
                                <br/>
                                <%@ include file="/WEB-INF/jsp/reviewer/verification/socialNetwork/accordeon.jsp" %>
                            </div>
                            <div id="reference" class="tab-pane">
                                <div class="row center">
                                    <h2><i class="red icon-list bigger-100"></i>
                                        Referencias personales </h2>
                                </div>
                                <br/>
                                <%@ include file="/WEB-INF/jsp/reviewer/verification/reference/accordeon.jsp" %>
                            </div>
                            <div id="jobHistory" class="tab-pane">
                                <div class="row center">
                                    <h2><i class="pink icon-briefcase  bigger-100">&nbsp;</i>Historia Laboral</h2>
                                </div>
                                <br/>
                                <%@ include file="/WEB-INF/jsp/reviewer/verification/job/accordeon.jsp" %>
                            </div>
                            <div id="academyHistory" class="tab-pane">
                                <div class="row center">
                                    <h2><i class="orange icon-book  bigger-100">&nbsp;</i>Historia Escolar</h2>
                                </div>
                                <br/>

                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <%@ include file="/WEB-INF/jsp/reviewer/verification/school/content.jsp" %>
                                    </div>
                                </div>

                            </div>
                            <div id="drugs" class="tab-pane">
                                <div class="row center">
                                    <h2><i class="green icon-warning-sign  bigger-100">&nbsp;</i>Consumo de sustancias
                                    </h2>
                                </div>
                                <br/>
                                <%@ include file="/WEB-INF/jsp/reviewer/verification/drug/accordeon.jsp" %>
                            </div>
                            <div id="leaveCountry" class="tab-pane">
                                <div class="row">
                                    <div class="col-xs-10 col-xs-offset-1">
                                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/leavingCountry/content.jsp" %>
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
                    <div ng-show="listMsgError['general']" class="alert alert-danger element-center error-font">
            <span ng-bind-html="listMsgError['general']">
            </span>
                    </div>
                </div>
            </div>
            <div class="row" ng-show="readOnly == false">
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelChoiceInformation()">
                        Regresar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true" ng-confirm-action
                          confirm-message="&iquest;Est&aacute; seguro que desea terminar la verificaci&oacute;n con esta fuente?"
                          confirm-title="Terminar verificaci&oacute;n" confirm-type="info"
                          confirmed-click-action="terminateVerification('<c:url value="/reviewer/verification/terminateVerification.json?idCase=${idCase}"/>');">
                          Terminar
                    </span>
                </div>
            </div>
            <div class="row" ng-show="readOnly == true">
                <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelViewMeeting()">
                        Regresar
                    </span>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>

</div>

</body>
</html>
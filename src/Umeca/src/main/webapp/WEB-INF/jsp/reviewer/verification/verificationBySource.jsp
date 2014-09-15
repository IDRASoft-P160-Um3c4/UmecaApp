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

    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/ComponentVerificationDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/upsertVerifCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/upsertVerifAddressCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/upsertVerifScheduleCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/upsertVerifActivitiesCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/verificationCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/AddressVerificationDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/verification/ScheduleVerificationDrct.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/upsertMeetingCtrl.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
    </script>

<style>

    body {
        font-size: 11px;
    }
    .icon-ban-circle{
        color: #555 !important;;
    }

    .width-100{
        max-width: none !important;
        width: 100% !important;
    }
</style>
    <script>
        window.cancelMeetingSource = function(){
            window.goToUrlMvcUrl("<c:url value='/reviewer/verification/sources.html?id=${idCase}'/>");
        }

        window.cancelViewMeeting = function(){
            window.goToUrlMvcUrl("<c:url value='/managereval/showCaseEvaluation/index.html'/>");
        }

        window.verificationActivities = function(id) {
            window.showUpsertWithIdCase(id, "#upsertModal", "<c:url value='/reviewer/verification/verificationActivities.html?idSource=${idSource}'/>", undefined,undefined, ${idCase});
        };

    </script>
    <title>Usuarios</title>

</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content"   ng-cloak>
    <div ng-controller="verificationController">
        <input type="hidden" ng-init="urlVerifTrue= '<c:url value="/reviewer/verification/verifBySourceEqual.json"/>'" ng-model="urlVerifTrue"
               ng-update-hidden>
        <input type="hidden" ng-init="urlVerifNotKnow= '<c:url value="/reviewer/verification/verifBySourceNotKnow.json"/>'" ng-model="urlVerifTrue"
               ng-update-hidden>
        <br/>
        <h2 class="element-center" ng-show="managereval==false"><i class="glyphicon icon-edit"></i>&nbsp;&nbsp;Entrevista de verificaci&oacute;n</h2>
        <h2 class="element-center"  ng-show="managereval==true"><i class="glyphicon icon-comments-alt">&nbsp;&nbsp;Entrevista de riesgos procesales</i></h2>
        <%@ include file="/WEB-INF/jsp/reviewer/meeting/imputedName.jsp" %>
        <div class="row" ng-init="managereval = ${managereval}" ng-show="managereval == false">
        <div class="col-xs-12 widget-container-span">
            <div class="widget-box">
                <div class="widget-header widget-header-small header-color-dark">
                    <h6 class="smaller">Detalles de la fuente</h6>
                </div>
                <div class="widget-body" ng-init='source = ${source};'>
                    <div class="widget-main">
                      <div class="row">
                          <div class="col-xs-5">
                              <div class="col-xs-2 smaller lighter blue">
                                  Nombre:
                              </div>
                              <div class="col-xs-10 ">
                                  {{source.fullName}}
                              </div>
                          </div>
                          <div class="col-xs-5 col-xs-offset-1">
                              <div class="col-xs-2 smaller lighter blue">
                                  Parentesco:
                              </div>
                              <div class="col-xs-10 ">
                                  {{source.relationship}}
                              </div>
                          </div>
                      </div>
                        <div class="row">
                            <div class="col-xs-5">
                                <div class="col-xs-2 smaller lighter blue">
                                    Edad:
                                </div>
                                <div class="col-xs-10 ">
                                    {{source.age}}
                                </div>
                            </div>
                            <div class="col-xs-5 col-xs-offset-1">
                                <div class="col-xs-2 smaller lighter blue">
                                    Tel&eacute;fono:
                                </div>
                                <div class="col-xs-10 ">
                                    {{source.phone}}
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-10">
                                <div class="col-xs-1 smaller lighter blue">
                                    Direcci&oacute;n:
                                </div>
                                <div class="col-xs-10">
                                    {{source.address}}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            </div>
        <br/>
        <div class="row"  ng-init="i.name='${m.imputed.name}'; i.lastNameP='${m.imputed.lastNameP}'; i.lastNameM ='${m.imputed.lastNameM}';" >
            <div class="col-sm-12">
                <div class="tabbable tabs-left">
                    <ul class="nav nav-tabs" id="tabMeeting">
                        <li class="active">
                            <a data-toggle="tab" href="#personalData">
                                <i class="purple glyphicon glyphicon-user bigger-200"></i>
                                Datos personales y <br/>

                                <div class="col-xs-offset-3"> Entorno social</div>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#address">
                                <i class="green  icon-home  bigger-200"></i>
                                Domicilios<br/>
                                <label class="info-example">De los &uacute;ltimos 5 a&ntilde;os</label>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#socialNetwork">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="blue icon-group bigger-200"></i>
                                        Red social               <br/>
                                        <label class="info-example">Personas con las que vive</label>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#reference">
                                <i class="red icon-list bigger-200"></i>
                                Referencias personales  <br/>
                                <label class="info-example">Personas con las que no vive</label>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#jobHistory">
                                <i class="pink icon-briefcase  bigger-200"></i>
                                Historia laboral                                   <br/>
                                <label class="info-example">&Uacute;ltimos 3-5 empleos</label>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#academyHistory">
                                <i class="orange icon-book  bigger-200"></i>
                                Historia Escolar
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#drougs">
                                <i class="green icon-warning-sign  bigger-200"></i>
                                Consumo de sustancias
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#leaveCountry">
                                <i class="blue icon-globe  bigger-200"></i>
                                Facilidad de <br/>

                                <div class="col-xs-offset-3">abandonar el pa&iacute;s</div>
                            </a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="personalData" class="tab-pane in active">
                            <div class="row center">
                                <h2><i class="purple glyphicon glyphicon-user bigger-100"></i> &nbsp;Datos personales y
                                    entorno social</h2>
                            </div>
                            <br/>
                            <br/>
                            <br/>
                            <div class="row">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <%@ include file="/WEB-INF/jsp/reviewer/meeting/personal/content.jsp" %>
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
                                    Red social                                    </h2>
                            </div>
                            <br/>
                            <%@ include file="/WEB-INF/jsp/reviewer/verification/socialNetwork/accordeon.jsp" %>
                        </div>
                        <div id="reference" class="tab-pane">
                            <div class="row center">
                                <h2><i class="red icon-list bigger-100"></i>
                                    Referencias personales                                   </h2>
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
                        <div id="drougs" class="tab-pane">
                             <div class="row center">
                                <h2><i class="green icon-warning-sign  bigger-100">&nbsp;</i>Consumo de sustancias</h2>
                            </div>
                            <br/>
                            <%@ include file="/WEB-INF/jsp/reviewer/verification/drug/accordeon.jsp" %>
                        </div>
                        <div id="leaveCountry" class="tab-pane">
                            <div class="row center">
                                <div class="col-xs-10 col-xs-offset-1">
                                    <%@ include file="/WEB-INF/jsp/reviewer/meeting/leavingCountry/content.jsp" %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <div class="row" ng-show="managereval == false">
            <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelMeetingSource()">
                        Regresar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="terminateMeetingSource('<c:url value="/reviewer/verification/terminateMeetingSource.json?idCase=${idCase}&&idSource=${idSource}"/>');">
                          Terminar Entrevista
                    </span>
            </div>
        </div>
        <div class="row" ng-show="managereval == true">
            <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelViewMeeting()">
                        Regresar
                    </span>
            </div>
        </div>
    </div>

    <div ng-controller="modalDlgController" id="upsertModal">

    </div>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/reviewer/verification/detailVerification.jsp" %>
    <%@ include file="/WEB-INF/jsp/reviewer/verification/detailVerificationAddress.jsp" %>
    <%@ include file="/WEB-INF/jsp/reviewer/verification/detailVerificationSchedule.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>

</div>

</body>
</html>
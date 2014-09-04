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
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/proceedingLegalkCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
    </script>
   <title>Usuarios</title>
    <script>
        window.cancelLegal = function (){
            window.goToUrlMvcUrl("<c:url value='/reviewer/meeting/index.html'/>");
        }
        window.cancelViewManagerEval = function(){
            window.goToUrlMvcUrl("<c:url value='/managereval/showCaseEvaluation/index.html'/>");
        }

    </script>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-cloak>
                                                                            <br/>
    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Informaci&oacute;n legal</h2>
    <br/>
    <div class="element-center"><label class="element-center info-example">LOS SIGUIENTES DATOS SE OBTIENEN DE LA CARPETA DE INVESTIGACI&Oacute;N, DE LA INFROMACI&Oacute;N POLICIAL, DE LA PGJ O DEL TSJ</label><br/>
    <label class="info-example" style="font-size: small;!important">BAJO NINGUNA CIRCUNSTANCIA SE LE SOLICITAR&Aacute; ESTA INFORMACI&Oacute;N AL IMPUTADO</label>
    </div>
    <%@ include file="/WEB-INF/jsp/reviewer/meeting/imputedName.jsp" %>
    <div ng-controller="proceedingLegalController">
    <div class="row" ng-init="managereval = ${managereval}; idCase = ${idCase};">
        <div class="col-sm-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabMeeting">
                    <li class="active" id="liLegalActual">
                        <a data-toggle="tab" href="#legalActual">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="green  icon-legal  bigger-200"></i>
                                    Proceso actual            <br/>
                                    <label class="info-example">Analizar carpeta de investigaci&oacute;n</label>
                                </div>
                                <div class="col-xs-2" ng-show="listMsgError['legalActual'].length > 0">
                                    <div class="tools">
                                        <div class="inline position-relative">
                                            <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                               data-toggle="dropdown" ng-click="changeZIndex('liLegalActual');"></i>

                                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                                                style="width: 400px; z-index: 100000; padding: 0 0;">
                                                <div class="alert-danger element-center error-font">
                                                    <div ng-repeat="msg in listMsgError['legalActual']">
                                                        <li>
                                                            {{msg}}
                                                        </li>

                                                    </div>
                                                </div>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liLegalPrevious">
                        <a data-toggle="tab" href="#legalPrevious">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="gray  icon-legal  bigger-200"></i>
                                    Procesos anteriores
                                </div>
                                <div class="col-xs-2" ng-show="listMsgError['legalPrevious'].length > 0">
                                    <div class="tools">
                                        <div class="inline position-relative">
                                            <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                               data-toggle="dropdown" ng-click="changeZIndex('liLegalPrevious');"></i>

                                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                                                style="width: 400px; z-index: 100000; padding: 0 0;">
                                                <div class="alert-danger element-center error-font">
                                                    <div ng-repeat="msg in listMsgError['legalPrevious']">
                                                        <li>
                                                            {{msg}}
                                                        </li>
                                                    </div>
                                                </div>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="legalActual" class="tab-pane in active">

                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/legal/current.jsp"%>

                    </div>

                    <div id="legalPrevious" class="tab-pane">

                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/legal/previous.jsp"%>

                    </div>

                </div>
            </div>
        </div>
    </div>
        <br/>

        <div class="row">
            <div class="col-xs-12">
                <div ng-show="listMsgError['general'].length > 0" class="alert alert-danger element-center error-font">
                    <div ng-repeat="msg in listMsgError['general']">
                        <li>
                            {{msg}}
                        </li>

                    </div>
                </div>
            </div>
        </div>
    <div class="row" ng-show="managereval == false">
        <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelLegal()">
                          Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCurrentLegalId,#FormPreviousLegalId','<c:url value="/reviewer/meeting/saveProceedingLegal.json?idCase=${idCase}"/>');">
                          Terminar
                    </span>
        </div>
    </div>
    <div class="row" ng-show="managereval == true">
        <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelViewManagerEval()">
                          Regresar
                    </span>
        </div>
    </div>
    </div>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>
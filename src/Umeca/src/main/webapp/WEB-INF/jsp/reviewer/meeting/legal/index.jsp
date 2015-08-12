<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/reviewer/proceedingLegalkCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/showMessageErrorDrct.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"   href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>

    <title>Usuarios</title>
    <script>
        window.cancelLegal = function () {
            window.goToUrlMvcUrl("<c:url value='/reviewer/meeting/index.html'/>");
        }
        window.cancelViewManagerEval = function () {
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

    <div class="element-center"><label class="element-center info-example">LOS SIGUIENTES DATOS SE OBTIENEN DE LA
        CARPETA DE INVESTIGACI&Oacute;N, DE LA INFORMACI&Oacute;N POLICIAL, DE LA PGJ O DEL TSJ</label><br/>
        <label class="info-example" style="font-size: small;!important">BAJO NINGUNA CIRCUNSTANCIA SE LE SOLICITAR&Aacute;
            ESTA INFORMACI&Oacute;N AL IMPUTADO</label>
    </div>
    <%@ include file="/WEB-INF/jsp/reviewer/meeting/handingOver.jsp" %>
    <%@ include file="/WEB-INF/jsp/reviewer/meeting/imputedName.jsp" %>

    <div class="col-xs-12" ng-controller="proceedingLegalController">
        <input type="hidden" id="urlMun" value="<c:url value='/reviewer/legal/getMun.json'/>"/>
        <input type="hidden" id="urlLoc" value="<c:url value='/reviewer/legal/getLoc.json'/>"/>

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
        <div class="row"
             ng-init="managereval = ${managereval == null ? false: managereval}; idCase = ${idCase};">

            <div class="col-sm-12"
                 ng-init="stateId=${stateId==null?'-1':stateId}; municipalityId=${municipalityId==null?'-1':municipalityId}; locationId=${locationId==null?'-1':locationId};">
                <div class="tabbable tabs-left">
                    <ul class="nav nav-tabs" id="tabMeeting">
                        <li class="active" id="liLegalActual">
                            <a data-toggle="tab" href="#legalActual">
                                <div class="row">
                                    <div class="col-xs-10">
                                        <i class="green  icon-legal  bigger-200"></i>
                                        Proceso actual <br/>
                                        <label class="info-example">Analizar carpeta de investigaci&oacute;n</label>
                                    </div>
                                    <div class="col-xs-2" ng-show="listMsgError['legalActual']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                   ng-click="showMessageError('legalActual');"></i>
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
                                    <div class="col-xs-2" ng-show="listMsgError['legalPrevious']">
                                        <div class="tools">
                                            <div class="inline position-relative">
                                                <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                                                   ng-click="showMessageError('legalPrevious');"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="legalActual" class="tab-pane in active">

                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/legal/current.jsp" %>

                        </div>

                        <div id="legalPrevious" class="tab-pane">

                            <%@ include file="/WEB-INF/jsp/reviewer/meeting/legal/previous.jsp" %>

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
        <div class="row" ng-show="managereval == false">
            <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelLegal()">
                          Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-disabled="WaitFor==true" ng-confirm-action
                          confirm-message="&iquest;Est&aacute; seguro que desea terminar el llenado de informaci&oacute;n legal?"
                          confirm-title="Terminar informaci&oacute;n legal" confirm-type="info"
                          confirmed-click-action="submit('#FormCurrentLegalId,#FormPreviousLegalId','<c:url value="/reviewer/meeting/saveProceedingLegal.json?idCase=${idCase}"/>');">
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
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>
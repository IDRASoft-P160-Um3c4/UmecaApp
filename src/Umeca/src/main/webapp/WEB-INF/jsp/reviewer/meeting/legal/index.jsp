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

    <title>Usuarios</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevista de evaluación de riesgos procesales</h2>

    <div class="row">
        <div class="col-sm-3">
            <h3 class="header smaller lighter blue">
                <small>Número de caso:  </small>
                &nbsp;${m.caseDetention.idString}
            </h3>
        </div>
        <div class="col-sm-7 col-sm-offset-1">
            <h3 class="header smaller lighter blue">
                <small>Nombre del imputado:  </small>
                &nbsp;${m.imputed.name} &nbsp; ${m.imputed.lastNameP} &nbsp; ${m.imputed.lastNameM}
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabMeeting">
                    <li class="active">
                        <a data-toggle="tab" href="#legalActual">
                            <i class="green  icon-legal  bigger-200"></i>
                           Proceso actual
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#legalBefore">
                            <i class="gray  icon-legal  bigger-200"></i>
                            Procesos anteriores
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="legalActual" class="tab-pane in active">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/legal/actual.jsp"%>
                    </div>

                    <div id="legalBegore" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/legal/before.jsp"%>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>
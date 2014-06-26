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
   <title>Usuarios</title>
    <script>
        window.cancelLegal = function (){
            window.goToUrlMvcUrl("<c:url value='/reviewer/meeting/index.html'/>");
        }
    </script>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content">

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevista de evaluación de riesgos procesales</h2>
    <%@ include file="/WEB-INF/jsp/reviewer/meeting/imputedName.jsp" %>
    <div ng-controller="proceedingLegalController">
    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabMeeting">
                    <li class="active">
                        <a data-toggle="tab" href="#legalActual">
                            <i class="green  icon-legal  bigger-200"></i>
                           Proceso actual
                            <i class="icon-exclamation-sign red bigger-100" ng-show="validf['form0'] == true" ng-init="validf['form0'] = false"></i>
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#legalPrevious">
                            <i class="gray  icon-legal  bigger-200"></i>
                            Procesos anteriores
                            <i class="icon-exclamation-sign red bigger-100" ng-show="validf['form1'] == true" ng-init="validf['form1'] = false"></i>
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
        <div ng-show="listMsgErrorCon.length > 0" class="alert alert-danger element-center error-font">
            <div  ng-repeat ="msg in listMsgErrorCon">
                {{msg}}
                <br/>
            </div>
        </div>
        </div>
    <div class="row">
        <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.cancelLegal()">
                          Cancelar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="submit('#FormCurrentLegalId,#FormPreviousLegalId','<c:url value="/reviewer/meeting/saveProceedingLegal.json?idCase=${idCase}"/>');">
                          Guardar
                    </span>
        </div>
    </div>
    </div>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>
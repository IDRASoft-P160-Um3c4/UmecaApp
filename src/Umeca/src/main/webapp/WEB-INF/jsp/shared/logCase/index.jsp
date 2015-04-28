<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/logCaseCtrl.js"></script>

    <title>Historial de supervisi&oacute;n</title>
    <script>
        window.addSpontaneousActivity = function() {

            window.showUpsert(${caseId}, "#angJsjqGridId", "<c:url value='/shared/logCase/addActivity.html' />");
        };
        window.downloadLogCase = function () {
            var goTo = "<c:url value='/shared/logCase/generateFile.html?id=${caseId}'/>";
            window.goToUrlMvcUrl(goTo);
        };

    </script>
</head>
<body scroll="no" ng-app="ptlUmc" >
<div class="row">
    <div class="col-xs-12">
        <%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
    </div>
</div>
<div class="container body-content" ng-controller="logCaseController">
    <div class="col-xs-10 col-xs-offset-1">
<div ng-init='lstHfAssignedArrangement = ${lstHfAssignedArrangement== null ? "[]":lstHfAssignedArrangement}; lstActivities = ${lstActivities==null?"[]":lstActivities};
    lstGoals = ${lstGoals== null ? "[]":lstGoals};'></div>
<br/>
<h4 class="element-center">${titleDoc == null? "BIT&Aacute;CORA DE SUPERVISI&Oacute;N":titleDoc}</h4>
<h4 class="element-center">UNIDAD DE VIGILANCIA Y SEGUIMIENTO DE MEDIDAS JUDICIALES</h4>
        <div class="row">
            <div class="col-xs-12 align-right">
                <i class="icon-cloud-download icon-animated-wrench purple">&nbsp; </i><a href="#" onclick="downloadLogCase()">Descargar bit&aacute;cora</a>
            </div>
        </div>
<div class="hr hr8"></div>
    <%@ include file="/WEB-INF/jsp/shared/generalDataLog.jsp"%>
<br/>
<div class="row">
   <div class="col-xs-6 align-left">
       <i class="glyphicon glyphicon-plus-sign purple">&nbsp; </i><a href="#" onclick="addSpontaneousActivity()">Agrgar actividad espont&aacute;nea</a>
   </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <div ng-show="MsgError" class="alert-danger element-center">
            {{MsgError}}
        </div>
    </div>
    <br/>
</div>
<div class="panel panel-default panel-primary">
    <div class="panel-heading" ng-init='listLog=${listLog};'>
        <span class="icon-tasks"></span>&nbsp;&nbsp;LISTADO DE ACTIVIDADES
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="widget-box transparent">
                <div class="widget-body">
                    <div class="widget-main no-padding">
                        <table class="table table-bordered table-striped">
                            <thead class="thin-border-bottom">
                            <tr>
                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    FECHA Y HORA
                                </th>

                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    ACTIVIDAD
                                </th>
                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    REALIZADO POR
                                </th>
                                <th class="col-xs-6 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    DETALLES
                                </th>
                            </tr>
                            </thead>

                            <tbody ng-repeat="a in listLog">
                            <tr>
                                <td><span ng-bind-html="formatHtml(a.dateString)"></span></td>
                                <td><span ng-bind-html="formatHtml(a.activity)"></span>
                                </td>
                                <td><span ng-bind-html="formatHtml(a.userName)"></span></td>
                                <td class="align-left"><strong><span ng-show="a.title" ng-bind-html="formatHtml(a.title)" class="text-primary"></span></strong><br/>
                                    <span ng-bind-html="formatHtml(a.resume)"></span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<div id="angJsjqGridId" ng-controller="modalDlgController">
    <div class="blocker" ng-show="working">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>

</body>
</html>

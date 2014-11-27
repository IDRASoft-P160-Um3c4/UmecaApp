<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/commonActMonPlan.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/logCaseCtrl.js"></script>

    <%--<title>Bit&aacute;cora de supervisi&oacute;n</title>--%>
    <title>Historial de supervisi&oacute;n</title>
</head>
<body scroll="no" ng-app="ptlUmc" style="width: 1100px; margin: auto" class="element-center">

<div class="container body-content" ng-controller="logCaseController">

<div ng-init='lstHfAssignedArrangement = ${lstHfAssignedArrangement}; lstActivities = ${lstActivities};  mpId =${mpId};
    lstGoals = ${lstGoals};'></div>

<h4 class="element-center">DIRECCI&Oacute;N DE EJECUCI&Oacute;N DE PENAS Y MEDIDAS JUDICIALES</h4>
<h4 class="element-center">UNIDAD DE VIGILANCIA Y SEGUIMIENTO DE MEDIDAS JUDICIALES</h4>

<div class="hr hr8"></div>
    <%@ include file="/WEB-INF/jsp/shared/generalDataLog.jsp" %>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div ng-show="MsgError" class="alert-danger element-center">
            {{MsgError}}
        </div>
    </div>
    <br/>
</div>
<div class="panel panel-default panel-primary">
    <div class="panel-heading">
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

                            <tbody ng-repeat="a in reconstructedLstActMonPlan">
                            <tr>
                                <td>Inicio: {{a.start}}<br/>Fin: {{a.end}}</td>
                                <td>Descripci&oacute;n:<br/> {{a.supActivity}}
                                    <br/> <br/>
                                    Registrada por:<br/> {{a.user}}
                                </td>
                                <td>{{a.aidSource}}</td>
                                <td>
                                    <table>
                                        <tr ng-repeat="i in a.lstAssignedArrangements">
                                            <td class="col-xs-8 element-left">{{i.name}}</td>
                                            <td class="col-xs-4 element-left">{{i.status}}</td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <span ng-class="createLabel(a.status)">{{a.status}}</span>
                                    <br/>
                                    {{a.comments}}
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
</body>
</html>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/commonActMonPlan.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/log/supervisionLogCtrl.js"></script>

    <%--<title>Bit&aacute;cora de supervisi&oacute;n</title>--%>
    <title>Historial de supervisi&oacute;n</title>
</head>
<body scroll="no" ng-app="ptlUmc" style="width: 1100px; margin: auto" class="element-center">

<div class="container body-content" ng-controller="supervisionLogController">

<div ng-init='lstHfAssignedArrangement = ${lstHfAssignedArrangement}; lstActivities = ${lstActivities};  mpId =${mpId}; caseId =${caseId};
    lstGoals = ${lstGoals}; lstSources = ${lstSources}; lstActMonPlan = ${lstActMonPlan}; lstActMonPlanArrangement = ${lstActMonPlanArrangement}; constructActMonPlan();'></div>

<h4 class="element-center">DIRECCI&Oacute;N DE EJECUCI&Oacute;N DE PENAS Y MEDIDAS JUDICIALES</h4>
<h4 class="element-center">UNIDAD DE VIGILANCIA Y SEGUIMIENTO DE MEDIDAS JUDICIALES</h4>

<div class="hr hr8"></div>
<%@ include file="/WEB-INF/jsp/shared/generalDataLog.jsp" %>

    <br/>
    <br/>

    <div class="row">
    <div class="col-xs-6">
        <div class="col-xs-5 element-right">
            Ver por obligaciones procesales:
        </div>
        <div class="col-xs-7 element-right" ng-init="urlToFill = '<c:url value="/supervisor/log/fillByFilter.json"/>'">
            <select class="width-100" ng-model="b.filterAssArr" ng-change="fillByFilter()" ng-disabled="WaitFor == true"
                    ng-options="e.name for e in assignedArrangementFilter">
            </select>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="col-xs-5 element-right">
            Ver por tipo de actividad:
        </div>
        <div class="col-xs-7 element-right" ng-init="urlToFill = '<c:url value="/supervisor/log/fillByFilter.json"/>'">
            <select class="width-100" ng-model="b.filterActTyp" ng-change="fillByFilter()" ng-disabled="WaitFor == true"
                    ng-options="e.name for e in activitiesTypeFilter">
            </select>
        </div>
    </div>
</div>
<br/>

    <div class="row">
        <div class="col-xs-6 element-left">
            <button class="btn btn-default btn-primary btn-sm"
                    ng-click="deleteActivities('<c:url value="/supervisor/generateMonitoringPlan/deleteActMonPlan.json" />')">
                *Eliminar actividades seleccionadas
            </button>
        </div>
        <br/>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div ng-show="MsgError" class="alert-danger element-center">
                {{MsgError}}
            </div>
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-xs-12 element-left">
            <small>*Si el caso ya ha sido autorizado, al eliminar una o m&aacute;s actividades, &eacute;stas se establecer&aacute;n
                en un estado pre-eliminado y cuando el coordinador autorice de nuevo el plan, ser&aacute;n eliminadas por completo. Recordar que no es posible </small>
        </div>
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
                                <th class="col-xs-0_4 element-center">
                                    <input class="form-control-static" type="checkbox" ng-model="m.isSelectedAll" ng-change="selectedAll()"/>
                                </th>
                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    FECHA Y HORA DE ACTIVIDAD
                                </th>

                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    DETALLES
                                </th>
                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    FUENTE DE CONTACTO Y DATO DE LOCALIZACI&Oacute;N
                                </th>
                                <th class="col-xs-3_6 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    RESULTADO DE LOS RIESGOS PROCESALES
                                </th>
                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    RESULTADO DE LA ACTIVIDAD DE SUPERVISI&Oacute;N
                                </th>
                            </tr>
                            </thead>

                            <tbody ng-repeat="a in reconstructedLstActMonPlan">
                            <tr>
                                <td><input class="form-control-static" type="checkbox" ng-model="m.lstMonActPlanSel[a.id]" ng-init="m.lstMonActPlanSel[a.id] = false;"
                                           ng-if="a.status === 'PRE_NUEVA' || a.status === 'PRE_MODIFICADA' || a.status === 'NUEVA' || a.status === 'MODIFICADA'"/></td>
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
<div>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
</div>
</body>
</html>

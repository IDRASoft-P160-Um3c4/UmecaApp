<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/log/supervisionLogCtrl.js"></script>

    <%--<title>Bit&aacute;cora de supervisi&oacute;n</title>--%>
    <title>Plan estrat&eacute;gico</title>
</head>
<body scroll="no" ng-app="ptlUmc" style="width: 1100px; margin: auto" class="element-center">

<div class="container body-content" ng-controller="supervisionLogController">

<div ng-init='lstHfAssignedArrangement = ${lstHfAssignedArrangement}; lstActivities = ${lstActivities};
    lstGoals = ${lstGoals}; lstSources = ${lstSources}; lstActMonPlan = ${lstActMonPlan}; lstActMonPlanArrangement = ${lstActMonPlanArrangement}; constructActMonPlanAccomplishment();'></div>

<h4 class="element-center">REPORTE DE INCUMPLIMIENTO Y CUMPLIMIENTO DE LAS ACTIVIDADES </h4>
<h4 class="element-center">DEL PLAN DE SEGUIMIENTO</h4>

<div class="hr hr8"></div>

<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-eye-open"></span>&nbsp;&nbsp;DATOS DEL CASO
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> IMPUTADO</div>
                        <div class="profile-info-value element-left">
                            <span id="imputedName">${imputedName}&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> DELITO</div>
                        <div class="profile-info-value element-left">
                            <span id="crime">${crime}&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> JUEZ</div>
                        <div class="profile-info-value element-left">
                            <span id="judge">${judge}&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> DEFENSOR</div>
                        <div class="profile-info-value element-left">
                            <span id="defender">${defender}&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> MINISTERIO P&Uacute;BLICO</div>
                        <div class="profile-info-value element-left">
                            <span id="mp">${mp}&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> TEL&Eacute;FONO DEL IMPUTADO</div>
                        <div class="profile-info-value element-left">
                            <span id="imputedTel">${imputedTel}&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> DOMICILIO DEL IMPUTADO</div>
                        <div class="profile-info-value element-left">
                            <span id="imputedAddr">${imputedAddr}&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> APOYO MORAL</div>
                        <div class="profile-info-value element-left">
                            <%--<span id="moralName">${moralName}&nbsp;&nbsp;${moralPhone}</span>--%>
                            <span id="moralName">${moralName}&nbsp;&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> TODAS LAS RESOLUCIONES</div>
                        <div class="profile-info-value element-left">
                            <span id="prevResolution">${prevResolution}&nbsp;&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"><b>RESOLUCI&Oacute;N ACTUAL</b></div>
                        <div class="profile-info-value element-left">
                            <span id="resolution">${lastResolution}&nbsp;&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--<div class="profile-info-row">--%>
        <%--<div class="profile-info-name"> TIPO DE RESOLUCI&Oacute;N</div>--%>

        <%--</div>--%>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> TIPO DE MEDIDA JUDICIAL</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstHfAssignedArrangement"><i class="icon-bookmark blue"></i>&nbsp;&nbsp;{{i.name}}
                                / {{i.description}} </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name "> COMENTARIO DE CIERRE DE CASO</div>
                        <div class="profile-info-value element-left ">
                            <span style="display: inline; word-wrap: break-word;" id="closeComment">${closeComment}&nbsp;&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> ACTIVIDADES DE SUPERVISI&Oacute;N</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstActivities"><i
                                    class="icon-tasks blue"></i>&nbsp;&nbsp;{{i.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row three-lines">
                        <%--<div class="profile-info-name"> ESTRATEGIAS DE RECORDATORIOS</div>--%>
                        <div class="profile-info-name"> OBJETIVOS DE LA ACTIVIDAD DE SUPERVISI&Oacute;N</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstGoals"><i class="icon-rss blue"></i>&nbsp;&nbsp;{{i.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<div class="panel panel-default panel-primary">--%>
<%--<div class="panel-heading">--%>
<%--<span class="glyphicon glyphicon-saved"></span>&nbsp;&nbsp;DATOS DEL CASO--%>
<%--</div>--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-xs-12">--%>
<%--<div class="profile-user-info profile-user-info-striped">--%>
<%--<div class="profile-info-row">--%>
<%--<div class="profile-info-name"> IMPUTADO</div>--%>
<%--<div class="profile-info-value element-left">--%>
<%--<span id="imputedName">${imputedName}&nbsp;</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="row">--%>
<%--<div class="col-xs-12">--%>
<%--<div class="profile-user-info profile-user-info-striped">--%>
<%--<div class="profile-info-row two-lines">--%>
<%--<div class="profile-info-name"> CARPETA JUDICIAL</div>--%>
<%--<div class="profile-info-value element-left">--%>
<%--<span id="mpId">${mpId}&nbsp;</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="panel panel-default panel-primary">--%>
<%--<div class="panel-heading">--%>
<%--<span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;DATOS DEL IMPUTADO--%>
<%--</div>--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-xs-12">--%>
<%--<div class="profile-user-info profile-user-info-striped">--%>
<%--<div class="profile-info-row">--%>
<%--<div class="profile-info-name"> DOMICILIO</div>--%>
<%--<div class="profile-info-value element-left">--%>
<%--<span id="address">${address}&nbsp;</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;FUENTES Y MEDIOS DE VERIFICACI&Oacute;N
        UTILIZADAS
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> FUENTES</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstSources"><i
                                    class="icon-tasks blue"></i>&nbsp;&nbsp;{{i.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;ACTIVIDADES DE SUPERVISI&Oacute;N
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> ACTIVIDADES DE SUPERVISI&Oacute;N</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstActivities"><i
                                    class="icon-tasks blue"></i>&nbsp;&nbsp;{{i.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-thumbs-down"></span>&nbsp;&nbsp;MEDIDAS INCUMPLIDAS / CONDICIONES INCLUMPLIDAS
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
                                    FECHA Y HORA DE ACTIVIDAD
                                </th>

                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    DESCRIPCI&Oacute;N
                                </th>
                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    FUENTE DE CONTACTO Y DATO DE LOCALIZACI&Oacute;N
                                </th>
                                <th class="col-xs-4 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    <%--RESULTADO DE LOS RIESGOS PROCESALES--%>
                                    OBLIGACIONES PROCESALES
                                </th>
                            </tr>
                            </thead>

                            <tbody ng-repeat="a in reconstructedLstActMonPlanFailed">
                            <tr>
                                <td>Inicio: {{a.start}}<br/>Fin: {{a.end}}</td>
                                <td>{{a.supActivity}}</td>
                                <td>{{a.aidSource}}</td>
                                <td>
                                    <table>
                                        <tr ng-repeat="i in a.lstAssignedArrangements">
                                            <td class="col-xs-8 element-left">{{i.name}} / {{i.description}}</td>
                                            <td class="col-xs-4 element-left">{{i.status}}</td>
                                        </tr>
                                    </table>
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
<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;MEDIDAS CUMPLIDAS / CONDICIONES CLUMPLIDAS
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
                                    FECHA Y HORA DE ACTIVIDAD
                                </th>

                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    DESCRIPCI&Oacute;N
                                </th>
                                <th class="col-xs-2 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    FUENTE DE CONTACTO Y DATO DE LOCALIZACI&Oacute;N
                                </th>
                                <th class="col-xs-4 element-center">
                                    <i class="icon-caret-right blue"></i>
                                    <%--RESULTADO DE LOS RIESGOS PROCESALES--%>
                                    OBLIGACIONES PROCESALES
                                </th>
                            </tr>
                            </thead>

                            <tbody ng-repeat="a in reconstructedLstActMonPlanOk">
                            <tr>
                                <td>Inicio: {{a.start}}<br/>Fin: {{a.end}}</td>
                                <td>{{a.supActivity}}</td>
                                <td>{{a.aidSource}}</td>
                                <td>
                                    <table>
                                        <tr ng-repeat="i in a.lstAssignedArrangements">
                                            <td class="col-xs-8 element-left">{{i.name}} / {{i.description}}</td>
                                            <td class="col-xs-4 element-left">{{i.status}}</td>
                                        </tr>
                                    </table>
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

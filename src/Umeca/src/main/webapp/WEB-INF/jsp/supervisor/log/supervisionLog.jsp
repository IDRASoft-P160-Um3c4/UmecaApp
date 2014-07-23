<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/log/supervisionLogCtrl.js"></script>

    <title>Bit&aacute;cora de supervisi&oacute;n</title>
</head>
<body scroll="no" ng-app="ptlUmc" style="width: 1100px; margin: auto" class="element-center">

<div class="container body-content" ng-controller="supervisionLogController">

<div ng-init='lstHfAssignedArrangement = ${lstHfAssignedArrangement}; lstActivities = ${lstActivities};
    lstGoals = ${lstGoals}; lstSources = ${lstSources}; lstActMonPlan = ${lstActMonPlan}; lstActMonPlanArrangement = ${lstActMonPlanArrangement}; constructActMonPlan();'></div>

<h4 class="element-center">DIRECCI&Oacute;N DE EJECUCI&Oacute;N DE PENAS Y MEDIDAS JUDICIALES</h4>
<h4 class="element-center">UNIDAD DE VIGILANCIA Y SEGUIMIENTO DE MEDIDAS JUDICIALES</h4>

<div class="hr hr8"></div>

<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-eye-open"></span>&nbsp;&nbsp;PLAN DE ESTRATEGIAS Y BIT&Aacute;CORA DE SUPERVISI&Oacute;N
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
                            <span id="moralName">${moralName}&nbsp;&nbsp;${moralPhone}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> ESTRATEGIAS DE RECORDATORIOS</div>
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
                                <td>Inicio: {{a.start}}<br/>Fin: {{a.end}}</td>
                                <td>{{a.supActivity}}</td>
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
                                    {{a.status}}<br/>{{a.comments}}
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

<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
        .photoRow{
            padding-right: 0px;!important
        }
    </style>
</head>
<body>
<div class="panel panel-default panel-primary">
    <div class="panel-heading" ng-init='titleDoc = "${titleDoc == null? "PLAN DE ESTRATEGIAS Y BIT&Aacute;CORA DE SUPERVISI&Oacute;N":titleDoc}"'>
        <span class="glyphicon glyphicon-eye-open"></span>&nbsp;&nbsp;<span ng-bind-html="formatHtml(titleDoc)"></span>

    </div>
    <div class="panel-body">
        <div class="row" style="position: relative;">
            <div class="col-xs-10 photoRow" style="position: absolute; bottom: 0;" >
                    <div class="row">
                        <div class="col-xs-12 photoRow">
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
                    <div class="row" ng-show="${judge==null ? false: true}">
                        <div class="col-xs-12 photoRow">
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
                    <div class="row"  ng-show="${defender==null ? false: true}">
                        <div class="col-xs-12 photoRow">
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
                    <div class="row" ng-show="${mp==null ? false: true}">
                        <div class="col-xs-12 photoRow">
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

            </div>
            <div class="col-xs-2 pull-right">
                <img src="${pageContext.request.contextPath}/${pathPhoto == null ?'assets/avatars/user.png':pathPhoto}"
                     style="width: 100%; min-height: 140px; padding-right: 12px; padding-left: 5px;"/>
            </div>
        </div>

        <div class="row" ng-show="${crime==null ? false: true}">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> DELITO</div>
                        <div class="profile-info-value element-left" ng-init='listCrime = ${crime == null ? "[]":crime};'>
                            <div ng-repeat="c in listCrime"><i class="icon-legal blue"></i>&nbsp;&nbsp;<span ng-bind-html="formatHtml(c)"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" ng-show="${imputedTel==null ? false: true}">
            <div class="col-xs-12" >
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
        <div class="row" ng-show="${imputedAddr==null ? false: true}">
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
        <div class="row" ng-show="${moralName== '' ? false: true}">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row">
                        <div class="profile-info-name"> APOYO MORAL</div>
                        <div class="profile-info-value element-left">
                            <%--<span id="moralName">${moralName}&nbsp;&nbsp;${moralPhone}</span>--%>
                            <span id="moralName">${moralName == null ? "Sin registrar":moralName}&nbsp;&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" ng-show="${prevResolution=='' ? false: true}">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"> TODAS LAS RESOLUCIONES</div>
                        <div class="profile-info-value element-left">
                            <span id="prevResolution">${prevResolution== null ? "Sin registrar":prevResolution}&nbsp;&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" ng-show="${lastResolution=='' ? false: true}">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row two-lines">
                        <div class="profile-info-name"><b>RESOLUCI&Oacute;N ACTUAL</b></div>
                        <div class="profile-info-value element-left">
                            <span id="resolution">${lastResolution== null ? "Sin registrar":lastResolution}&nbsp;&nbsp;</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--<div class="profile-info-row">--%>
        <%--<div class="profile-info-name"> TIPO DE RESOLUCI&Oacute;N</div>--%>

        <%--</div>--%>
        <div class="row" ng-show="lstHfAssignedArrangement.length > 0">
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
        <div class="row"  ng-show="${closeComment==null ? false: true}">
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
        <div class="row"  ng-show="lstActivities.length > 0">
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
        <div class="row"  ng-show="lstGoals.length > 0">
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
        <div class="row"  ng-show="${schedules=='[]' ? false: true}">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row three-lines">
                        <div class="profile-info-name">DISPONIBILIDAD</div>
                        <div class="profile-info-value element-left" ng-init='schedules = ${schedules == null ? "Sin registrar":schedules};'>
                            <div ng-repeat="i in schedules">
                                {{i.name}}<br/>
                                <div ng-repeat="sd in i.sch track by $index">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-rss blue"></i>  {{sd.day}} &nbsp;&nbsp;&nbsp;&nbsp;{{sd.start}}-{{sd.end}}
                                </div>
                                <div class="hr hr8"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row"  ng-show="${lstRisk=='[]' ? false: true}">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row three-lines" ng-init='lstRisk = ${lstRisk== null ? "[]":lstRisk};'>
                        <div class="profile-info-name"> RIESGOS</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstRisk"><i class="icon-info-sign blue"></i>&nbsp;&nbsp;{{i.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row"  ng-show="${lstThreat== '[]' ? false: true}">
            <div class="col-xs-12">
                <div class="profile-user-info profile-user-info-striped">
                    <div class="profile-info-row three-lines" ng-init='lstThreat = ${lstThreat == '' ? "[]": lstThreat};'>
                        <div class="profile-info-name"> AMENAZAS</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstThreat"><i
                                    class="icon-exclamation-sign blue"></i>&nbsp;&nbsp;{{i.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Historial de supervisi&oacute;n</title>
</head>
<body scroll="no" style="width: 1100px; margin: auto" class="element-center">

<div class="container body-content">

<h4 class="element-center">DIRECCI&Oacute;N DE EJECUCI&Oacute;N DE PENAS Y MEDIDAS JUDICIALES</h4>
<h4 class="element-center">UNIDAD DE VIGILANCIA Y SEGUIMIENTO DE MEDIDAS JUDICIALES</h4>

<div class="hr hr8"></div>

<div class="panel panel-default panel-primary">
    <div class="panel-heading">
        <span class="glyphicon glyphicon-eye-open"></span>&nbsp;&nbsp;PLAN DE ESTRATEGIAS Y BIT&Aacute;CORA DE
        SUPERVISI&Oacute;N
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
                        <div class="profile-info-name"> RESOLUCI&Oacute;N ACTUAL</div>
                        <div class="profile-info-value element-left">
                            <span id="resolution">${lastResolution}&nbsp;&nbsp;</span>
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

                            <c:forEach var="i" begin="1" end="5">
                            Item <c:out value="${i}"/><p>
                            </c:forEach>

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
                        <div class="profile-info-name"> OBJETIVOS DE LA ACTIVIDAD DE SUPERVISI&Oacute;N</div>
                        <div class="profile-info-value element-left">
                            <div ng-repeat="i in lstGoals"><i
                                    class="icon-rss blue"></i>&nbsp;&nbsp;{{i.name}}</span>
                            </div>
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

<script>
    colorActMonPlan = function (status, end, today, color, isSuspended) {

        if (isSuspended === true) {
            return 'label-black';
        }

        switch (status) {
            case "NO REALIZADA":
                return color === undefined ? 'label-grey' : 'grey';
            case "PRE_NUEVA":
                return color === undefined ? "label-pre-new" : 'pre-new';
            case "PRE_MODIFICADA":
                return color === undefined ? "label-pre-update" : 'pre-update';
            case "PRE_ELIMINADA":
                return color === undefined ? "label-pre-delete" : 'pre-delete';
            case "REALIZADA":
                return color === undefined ? 'label-success' : 'green';
            case "NUEVA":
            case "MODIFICADA":
                if (end === undefined || today === undefined || end >= today)
                    return color === undefined ? "label-info" : 'blue';
                return color === undefined ? 'label-danger' : 'red';
        }
    };
</script>
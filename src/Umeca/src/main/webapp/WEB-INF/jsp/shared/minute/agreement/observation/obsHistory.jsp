<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <script>
        app.controller('obsHistoryController', function () {
        });
    </script>
    <title>Historial de observaciones</title>
</head>
<body scroll="no" ng-app="ptlUmc" style="width: 1100px; margin: auto" class="element-center">

<div class="container body-content" ng-controller="obsHistoryController"
     ng-init='lstObs = ${lstObs}; agreementData=${agreementData};'>


    <div>
        <h2><i class="blue icon-dashboard bigger">&nbsp;</i>Historial de observaciones</h2>
    </div>

    <div>
        <%@ include file="/WEB-INF/jsp/shared/minute/agreement/grlAgreementData.jsp" %>
    </div>
    <br/>

    <div class="panel panel-default panel-primary">
        <div class="panel-heading">
            <span class="icon-tasks"></span>&nbsp;&nbsp;Listado de observaciones
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
                                        FECHA DE REGISTRO
                                    </th>

                                    <th class="col-xs-2 element-center">
                                        <i class="icon-caret-right blue"></i>
                                        USUARIO
                                    </th>
                                    <th class="col-xs-2 element-center">
                                        <i class="icon-caret-right blue"></i>
                                        PERFIL
                                    </th>
                                    <th class="col-xs-2 element-center">
                                        <i class="icon-caret-right blue"></i>
                                        OBSERVACI&Oacute;N
                                    </th>
                                </tr>
                                </thead>

                                <tbody ng-repeat="obs in lstObs">
                                <tr>
                                    <td>{{obs.strDate}}</td>
                                    <td>{{obs.name}}</td>
                                    <td>{{obs.logType}}</td>
                                    <td>{{obs.description}}</td>
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
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
</div>
</body>
</html>
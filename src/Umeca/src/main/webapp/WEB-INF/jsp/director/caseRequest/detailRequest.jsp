<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.umeca.model.shared.Constants" %>
<!--
* Project: Umeca
* User: Israel
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <title>Entrevistas</title>
    <style>
        .titleCase{
            font-size: large;
        }
        .infoCase{
            font-size: medium;
            font-weight: bold;
        }
    </style>
    <script>
        window.showRequest = function () {
            window.goToUrlMvcUrl("<c:url value='/director/caseRequest/show.html'/>");

        };
    </script>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="upsertController">
    <div class="container body-content">
        <div class="page-content">
            <div class="row">
                <div class="space-20"></div>
                <div class="col-xs-8 col-xs-offset-1">
                    <h2 class="element-center"><i class="icon icon-envelope orange"></i>&nbsp;&nbsp;Historial de solicitudes</h2>
                    <div class="row">
                        <div class="hr hr-20"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 blue titleCase blue text-right">
                           Carpeta de investigaci&oacute;n:
                        </div>
                        <div class="col-xs-7 infoCase">
                           ${caseInfo.folderId}
                        </div>
                    </div>
                    <div class="row" ng-init='listRequest=${listRequest};'>
                        <div class="col-xs-4 titleCase blue  text-right">
                            Imputado:
                        </div>
                        <div class="col-xs-7 infoCase">
                            ${caseInfo.personName}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 titleCase blue text-right">
                            Estatus:
                        </div>
                        <div class="col-xs-7 infoCase">
                            ${caseInfo.status}
                        </div>

                    </div>
                    <div class="row">
                        <div class="hr hr-20"></div>
                    </div>
                    <div ng-repeat="n in listRequest">
                        <div class="row">
                            <div class="col-xs-1">
                                <div class="itemdiv commentdiv">
                                    <div class="user">
                                        <img class="nav-user-photo"
                                             src="<c:url value='/assets/avatars/avatar0.png' />"/>
                                    </div>
                                </div>

                            </div>
                            <div class="col-xs-11">
                                <div class="col-xs-12">
                                    <label class="inline">
                                        <i class="glyphicon glyphicon-tag green"></i>
                                        &nbsp;&nbsp;
                                <span class="lbl"><strong>{{n.typeRequest}}</strong><br/>
                                    </span>
                                    </label>
                                </div>
                                <div class="col-xs-11 col-xs-offset-1">
                                    <span class="lbl smaller blue"> Realizada por: </span> <span class="lbl">{{n.userRequest}}.</span><br/>
                                    <span class="lbl smaller blue">Fecha de solicitud: </span><span class="lbl">{{n.dateRequestString}}.</span><br/>
                                    <span class="lbl smaller blue"> Mensaje:  </span><span class="lbl">{{n.messageRequest}}.</span>

                                    <div class="row">
                                        <div class="col-xs-5">
                                            <div class="hr hr-4"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-11 col-xs-offset-1"
                                     ng-show="n.dateResponse != null ">
                                    <span class="lbl smaller blue"> Respondida por: </span> <span class="lbl">{{n.userResponse}}.</span><br/>
                                    <span class="lbl smaller blue">Fecha de respuesta: </span><span class="lbl">{{n.dateResponseString}}.</span><br/>
                                    <span class="lbl smaller blue"> Mensaje:  </span><span class="lbl">{{n.messageResponse}}.</span>
                                </div>
                                <div class="col-xs-11 col-xs-offset-1 "
                                     ng-show="n.dateResponse == null ">
                                    <span class="lbl alert-danger">Solicitud sin respuesta</span>
                                </div>
                                <%--<div class="row">--%>
                                <%--<span>{{n.message}}</span>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="hr hr-15"></div>
                        </div>
                    </div>

                    <div ng-show="{{listRequest.length==0}}">
                        <ul class="item-list">
                            <li class="item-green clearfix">
                                No existen soliictudes para este caso.
                            </li>
                        </ul>
                    </div>
                </div>


                <!-- /widget-body -->
            </div>
            <div class="modal-footer">
                    <span class="btn btn-default btn-sm" onclick="window.showRequest()">
                        Regresar
                    </span>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>


</body>
</html>


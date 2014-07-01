<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/chosen.min.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/chosen.jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/framingMeetingCtrl.js"></script>

    <title>Entrevista de encuadre</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="framingMeetingController" ng-init='m.objView=${objView}'>
    <input type="hidden" name="idFolder" value="{{m.objView.idFolder}}">

    <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevista de encuadre</h2>


    <div class="row">
        <div class="col-xs-4">

            <h3 class="header smaller lighter blue">
                <small>N�mero de carpeta <br/> de investigaci�n:</small>
                &nbsp;&nbsp;&nbsp;&nbsp;{{m.objView.idFolder}}
            </h3>
        </div>

        <div class="col-xs-6">
            <h3 class="header smaller lighter blue">
                <small><br/>Nombre del imputado:</small>
                {{m.objView.personalData.fullName}}
            </h3>
        </div>

    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabFramingMeeting">

                    <li class="active">
                            <a data-toggle="tab" href="#personalData">
                                <i class="purple glyphicon glyphicon-user bigger-200"></i>&nbsp;&nbsp;
                                Datos personales y entorno social
                            </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#address">
                            <i class="green  icon-home  bigger-200"/></i>
                            Persona que acompa�ara durante <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;el proceso
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#socialNetwork">
                            <i class="blue icon-group bigger-200"></i>
                            Personas con las que vive
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#references">
                            <i class=""></i>
                            Referencias personales
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#activities">
                            <i class="pink icon-briefcase  bigger-200"></i>
                            Actividades que realiza el imputado
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#drugs">
                            <i class="green icon-warning-sign  bigger-200"></i>
                            Consumo de subtancias
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#addtional">
                            <i class="red icon-list bigger-200"></i>
                            An�liis de entorno
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#addtional">
                            <i class="red icon-list bigger-200"></i>
                            Preguntas adicionales
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="personalData" class="tab-pane in active">
                        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/_personalData.jsp" %>
                    </div>

                    <div id="accompaniment" class="tab-pane in active">

                    </div>

                    <div id="socialNetwork" class="tab-pane">

                    </div>

                    <div id="references" class="tab-pane">

                    </div>

                    <div id="activities" class="tab-pane">

                    </div>

                    <div id="drugs" class="tab-pane">

                    </div>

                    <div id="additional" class="tab-pane">

                    </div>

                </div>
            </div>
        </div>

    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>
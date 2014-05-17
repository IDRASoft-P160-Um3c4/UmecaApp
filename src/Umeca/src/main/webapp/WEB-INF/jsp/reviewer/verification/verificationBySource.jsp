<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
* Project: Umeca
* User: Israel
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/chosen.min.css" />
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/chosen.jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css" />
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/daterangepicker.min.js"></script>

    <script>
        $(function() {
            var total = "";
            $(".form-control").each(function() {
               $( this ).attr('disabled','disabled');
            });
            $("select").each(function(){
               $(this).hide();
            });
            $(".icon-list-alt").each(function(){
              $(this).show();
            });

            $("div > i").each(function(){
                $(this).show();
            });

            $(".schedule_visible").hide();

        });

        function selectSource(i){
         var divContent = $(i).parent().parent();
         $(divContent).find("div").show();
        }

        function checkSource(){
            var scope = angular.element($("#divContent")).scope();
            scope.show(null,"/reviewer/verification/detailSource.html");
        }

        function verification(i){
          //  var fieldName=$(div).find( "label").html();
          //  var idInput = $(div).find( "label").attr("for")
          //  var fieldValue = $("#"+idInput).val();
          //  alert("fieldName = "+fieldName + ", idInput = "+idInput+", fieldValue="+fieldValue);
            var scope = angular.element($("#divContent")).scope();
            scope.show(null,"/reviewer/verification/detailVerification.html");
        }
    </script>
    <title>Usuarios</title>

</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div class="container body-content">

    <h2 class="element-center"><i class="glyphicon icon-edit "></i>&nbsp;&nbsp;Verificación</h2>

    <div class="row"  ng-controller="modalDlgController" id="divContent">
        <div class="col-sm-5">
            <h3 class="header smaller lighter blue">
                <small>Número de carpeta de investigación:  </small>
                56-565L
            </h3>
        </div>
        <div class="col-sm-5 col-sm-offset-1">
            <h3 class="header smaller lighter blue">
                <small>Nombre del imputado:  </small>
                Nombre del Imputado
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabMeeting">
                    <li class="active">
                        <a data-toggle="tab" href="#personalData">
                            <i class="purple glyphicon glyphicon-user bigger-200"></i>
                            Datos personales y <br/>    <div class="col-xs-offset-3"> Entorno social</div>
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#address">
                            <i class="green  icon-home  bigger-200"></i>
                            Domicilios
                        </a>
                    </li>

                    <li>
                        <a data-toggle="tab" href="#socialNetwork">
                            <i class="blue icon-group bigger-200"></i>
                            Red social
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#jobHistory">
                            <i class="pink icon-briefcase  bigger-200"></i>
                            Historia laboral
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#academyHistory">
                            <i class="orange icon-book  bigger-200"></i>
                            Historia Escolar
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#drougs">
                            <i class="green icon-warning-sign  bigger-200"></i>
                            Consumo de sustancias
                        </a>
                    </li>
                    <li>
                        <a data-toggle="tab" href="#leaveCountry">
                            <i class="blue icon-globe  bigger-200"></i>
                            Facilidad de <br/><div class="col-xs-offset-3">abandonar el país</div>
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="personalData" class="tab-pane in active">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/_personalData.jsp"%>
                    </div>
                    <div id="address" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/address/accordeon.jsp"%>
                    </div>
                    <div id="jobHistory" class="tab-pane">
                             historia laboral
                    </div>
                    <div id="academyHistory" class="tab-pane">
                                historia escolar
                    </div>
                    <div id="drougs" class="tab-pane">
                         susustancias
                    </div>
                    <div id="leaveCountry" class="tab-pane">
                        <%@ include file="/WEB-INF/jsp/reviewer/meeting/leavingCountry.jsp"%>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>
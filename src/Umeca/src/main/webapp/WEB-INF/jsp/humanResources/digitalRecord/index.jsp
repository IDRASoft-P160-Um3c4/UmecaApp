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
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/generalData/employeeGeneralDataCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/job/digitalRecordJobCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/school/historySchoolCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/school/courseCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/references/empReferencesCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/umecaHistory/umecaJobCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/umecaHistory/trainingCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/umecaHistory/incidentCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/vacation/vacationCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/incapacity/incapacityCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/attachment/attachmentCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/humanResources/digitalRecord/digitalRecordCtrl.js"></script>


    <link href="${pageContext.request.contextPath}/assets/content/upload/jquery.fileupload.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/colorbox.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/jquery.colorbox-min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upload/uploadFileCtrl.js"></script>


    <style>
        .containerPhoto {
            position: relative;
            text-align: center;
            max-width: 100%;
            max-height: 100%;
        }
    </style>

    <script>
        returnEmployeesGrid = function () {
            window.goToUrlMvcUrl('<c:url value="/humanResources/employees/index.html"/>');
        }

        uploadPhoto = function (id) {
            window.showUpsertWithIdEmployee(id, "#divPhoto", '<c:url value='/humanResources/digitalRecord/uploadPhoto.html' />', undefined, undefined, ${idEmployee});

        };
    </script>

    <title>Expediente electr&oacute;nico</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>
<div id="divPhoto" ng-controller="modalDlgController">
</div>
<div class="container body-content" ng-controller="digitalRecordController">

    <div class="row">
        <div class="col-xs-12">
            <br/>

            <h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Expediente electr&oacute;nico
            </h2>

            <div class="col-xs-8 col-xs-offset-1" style="padding-top: 3%;"
                 ng-init='dr.employeeName="${employeeName}"; dr.employeePost="${employeePost}";'>
                <div class="col-xs-8">
                    <h3 class="header smaller lighter blue">
                        <small>Nombre del imputado:</small>
                        &nbsp;{{dr.employeeName}}
                    </h3>
                </div>
                <div class="col-xs-4">
                    <h3 class="header smaller lighter blue">
                        <small>Puesto:</small>
                        &nbsp;{{dr.employeePost}}
                    </h3>
                </div>

            </div>

            <div class="col-xs-1 col-xs-offset-1">
                <div class="row-fluid">
                    <ul class="ace-thumbnails">
                        <li>
                            <a href="${pageContext.request.contextPath}/${pathPhoto == null ?'assets/avatars/user.png':pathPhoto}"
                               data-rel="colorbox" id="idLinkPhotoEmployee">
                                <img src="${pageContext.request.contextPath}/${pathPhoto == null ?'assets/avatars/user.png':pathPhoto}"
                                     id="photoEmployee" class="containerPhoto"/>
                            </a>

                            <div class="tools tools-right">
                                <a href="#" onclick="uploadPhoto()" title="Cambiar foto">
                                    <i class="icon-pencil"></i>
                                </a>

                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="padding: 1%;">
        <div class="col-xs-10 col-xs-offset-1 element-right">
            <br/>
    <span class="btn btn-default btn-sm" onclick="returnEmployeesGrid();">
    Regresar
    </span>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-10 col-xs-offset-1">
            <div class="tabbable tabs-left">
                <ul class="nav nav-tabs" id="tabsEmployee">

                    <li class="active" id="liGeneralData">
                        <a data-toggle="tab" href="#generalData">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="purple glyphicon glyphicon-user bigger-200"></i>
                                    Datos generales
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liJobHistory">
                        <a data-toggle="tab" href="#job">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="green  icon-briefcase  bigger-200"></i>
                                    Historia laboral
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liSchoolHistory">
                        <a data-toggle="tab" href="#empSchool">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="blue icon-book bigger-200"></i>
                                    Historia escolar
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liReference">
                        <a data-toggle="tab" href="#references">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="red icon-group bigger-200"></i>
                                    Referencias personales
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liJobUmeca">
                        <a data-toggle="tab" href="#jobUmeca">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="orange icon-list bigger-200"></i>
                                    Historial UMECA
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liVacations">
                        <a data-toggle="tab" href="#vacations">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="purple icon-book  bigger-200"></i>
                                    Vacaciones
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liOvertime">
                        <a data-toggle="tab" href="#overtime">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="pink icon-briefcase  bigger-200"></i>
                                    Tiempo extra
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liIncapacity">
                        <a data-toggle="tab" href="#incapacity">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="black icon-male bigger-200"></i>
                                    Incapacidades
                                </div>
                            </div>
                        </a>
                    </li>

                    <li id="liDocuments">
                        <a data-toggle="tab" href="#documents">
                            <div class="row">
                                <div class="col-xs-10">
                                    <i class="black icon-male bigger-200"></i>
                                    Documentos adjuntos
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="generalData" class="tab-pane  in active">
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/generalData/employeeGeneralData.jsp" %>
                    </div>

                    <div id="job" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/job/job.jsp" %>
                    </div>

                    <div id="empSchool" class="tab-pane" ng-cloak>
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/school/school.jsp" %>
                    </div>

                    <div id="references" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/references/references.jsp" %>
                    </div>

                    <div id="jobUmeca" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/umecaHistory/index.jsp" %>
                    </div>

                    <div id="vacations" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/vacation/vacation.jsp" %>
                    </div>
                    <div id="overtime" class="tab-pane">
                        GRAFICA!!!
                        <%--<%@ include--%>
                        <%--file="/WEB-INF/jsp/supervisor/framingMeeting/job/school.jsp" %>--%>
                    </div>

                    <div id="incapacity" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/incapacity/incapacity.jsp" %>
                    </div>

                    <div id="documents" class="tab-pane">
                        <%@ include
                                file="/WEB-INF/jsp/humanResources/digitalRecord/attachment/attachment.jsp" %>
                    </div>

                </div>

            </div>
        </div>
    </div>

    <div class="row">
        <div class="modal-footer col-xs-10 col-xs-offset-1">
            <span class="btn btn-default btn-sm" onclick="returnEmployeesGrid();">
            Regresar
            </span>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
            <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
        </div>
    </div>

</div>
</body>
</html>
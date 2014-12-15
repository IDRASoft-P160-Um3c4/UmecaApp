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

    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/housemate/housemateCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/references/referencesCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/environmentAnalysis/environmentAnalysisCtrl.js"></script>
    <%--<script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/processAcompaniment/processAccompanimentCtrl.js"></script>--%>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/activities/activitiesCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/addtionalQuestions/additionalQuestionsCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/personalData/personalDataCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/address/addressCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/drugs/drugsCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/job/jobCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/school/schoolCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/framingMeeting/victim/victimCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/zipSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/municipalitySearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/locationSearchDrct.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/address/addressComponentCtrl.js"></script>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
    </script>
    <link href="${pageContext.request.contextPath}/assets/content/upload/jquery.fileupload.css" rel="stylesheet"
          type="text/css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/colorbox.css"/>
    <script src="${pageContext.request.contextPath}/assets/scripts/jquery.colorbox-min.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/upload/vendor/jquery.ui.widget.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.iframe-transport.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/upload/jquery.fileupload.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upload/uploadFileCtrl.js"></script>

    <script>
        generateFile = function (id) {
            var goTo = "<c:url value='/reviewer/technicalReview/generateFileCase.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };

        downloadTecReview = function (id) {
            var goTo = "<c:url value='/shared/uploadFile/downloadFile.html'/>" + "?id=" + id;
            window.goToUrlMvcUrl(goTo);
        };
        var uploadPhoto = function () {
            var id = ${idCase};
            window.showUpsert(id, "#divPhoto", '<c:url value='/shared/uploadFile/uploadFile.html?type=PHOTO' />', undefined, undefined);

        };

        window.setPhoto = function (resp) {
            alert("hola!");
        };

        window.framingMeetingLog = function (id) {
            var params = [];
            params["idParam"] = id;
            window.goToNewUrl("<c:url value='/supervisor/framingMeeting/framingMeetingLog.html?id=idParam' />", params);
        };

    </script>
    <style>
        .header {
            line-height: 16px !important;
            margin-bottom: 10px !important;
            margin-top: 10px !important;
            padding-bottom: 10px !important;
        }

        .containerPhoto {
            position: relative;
            text-align: center;
            max-width: 100%;
            max-height: 100%;
        }
    </style>

    <title>Entrevista de encuadre</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div id="divPhoto" ng-controller="modalDlgController">
</div>

<div class="container body-content" id="divFM" ng-controller="framingMeetingController"
     ng-init='fm.objView=${objView}; returnId=${returnId};
     urlManagerSup="<c:url value='/supervisor/showCaseSupervision/index.html'/>";
     urlIndex="<c:url value='/supervisor/framingMeeting/index.html'/>";'
     ng-cloak>
<input type="hidden" name="idFolder" value="{{fm.objView.idFolder}}">


<h2 class="element-center"><i class="glyphicon icon-comments-alt "></i>&nbsp;&nbsp;Entrevista de encuadre</h2>

<div class="col-xs-12">
<div class="row">
    <div class="col-xs-10">
        <div class="row">
            <div class="col-sm-5 col-xs-offset-1">
                <h3 class="header smaller lighter blue">
                    <small>Carpeta de investigaci&oacute;n:</small>
                    &nbsp;${idFolder}
                </h3>
            </div>
            <div class="col-xs-2">
                <h3 class="header smaller lighter blue">
                    <small>Resoluci&oacute;n:</small>
                    &nbsp;&nbsp;&nbsp;<label>${resolution}</label>
                </h3>
            </div>
            <div class="col-xs-4">
                <h3 class="header smaller lighter blue">
                    <small>Supervisor:</small>
                    &nbsp;
                    &nbsp;<label>{{fm.objView.userName}}</label>
                </h3>
            </div>

        </div>
        <div class="row">
            <div class="col-sm-9 col-xs-offset-1">
                <h3 class="header smaller lighter blue">
                    <small>Nombre del imputado:</small>
                    &nbsp;${fullNameImputed}
                </h3>
            </div>
            <div class="col-sm-2">
                <h3 class="header smaller lighter blue">
                    <small>Edad:</small>
                    &nbsp;${age}
                </h3>
            </div>
        </div>
        <div class="row" ng-show="${tStart==null?false:true}">
            <div class="col-sm-7 col-xs-offset-1">
                <h3 class="header smaller lighter blue">
                    <small>Entrevista de riesgos procesales:</small>
                    &nbsp;${tStart}&nbsp;-&nbsp;${tEnd}
                </h3>
            </div>
            <div class="col-sm-4">
                <h3 class="header smaller lighter blue">
                    <small>Evaluador:</small>
                    &nbsp;${reviewerFullname}
                </h3>
            </div>
        </div>
        <div class="row">
            <div
                    ng-init="hasMeeting = ${hasMeeting}; hasTR = ${hasTR}; checked=true; fileIdTR = ${fileIdTR == null?0:fileIdTR};">
                <div class="col-xs-9 col-xs-offset-1">
                    <h3 class="header smaller lighter blue">
                        <br/>
                        <small>Actividades de evaluaci&oacute;n:</small>
                        &nbsp;
                        <i class="icon-check" ng-show="hasMeeting"></i><i class="icon-unchecked"
                                                                          ng-show="!hasMeeting"></i>&nbsp;<label>Entrevista
                        de
                        riesgos procesales</label>&nbsp;&nbsp;
                        <i class="icon-check" ng-show="hasTR"></i><i class="icon-unchecked"
                                                                     ng-show="!hasTR"></i>&nbsp;<label>Opini&oacute;n
                        T&eacute;cnica</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a ng-show="hasMeeting && hasTR" href="javascript:;"
                                                         style="display:inline-block;"
                                                         title="Descargar opini&oacute;n t&eacute;cnica generada por el sistema"
                                                         onclick="generateFile(${idCase});"><span
                            class="icon-cloud-download"></span>&nbsp;<label>Opini&oacute;n
                        t&eacute;cnica(sistema)</label></a>
                        &nbsp;&nbsp;
                        <a ng-show="fileIdTR!=0" href="javascript:;"
                           style="display:inline-block;" title="Descargar opini&oacute;n t&eacute;cnica final"
                           onclick="downloadTecReview(${fileIdTR});"><span
                                class="icon-cloud-download"></span>&nbsp;<label>Opini&oacute;n
                            t&eacute;cnica(final)</label></a>
                    </h3>
                </div>
                <div class="col-xs-2">
                    <h3 class="header smaller lighter blue">
                        <small>Inicio:</small>
                        &nbsp;
                        &nbsp;<label>{{fm.objView.initDate}}</label> <br/>
                        <small>Fin:</small>
                        &nbsp;
                        &nbsp;<label>{{fm.objView.endDate}}</label>
                    </h3>
                </div>

            </div>
        </div>
    </div>
    <div class="col-xs-1">
        <div class="row-fluid">
            <ul class="ace-thumbnails">
                <li>
                    <a href="${pageContext.request.contextPath}/${pathPhoto == null ?'assets/avatars/user.png':pathPhoto}"
                       data-rel="colorbox" id="idLinkPhotoImputed">
                        <img src="${pageContext.request.contextPath}/${pathPhoto == null ?'assets/avatars/user.png':pathPhoto}"
                             id="photoImputed" class="containerPhoto"/>
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

<br/>

<div class="row" ng-show="fm.objView.canTerminate==false">
    <div class="col-xs-4 col-xs-offset-1">
        <h3 class="header smaller lighter blue">
            &nbsp;<a href="javascript:;" style="display:inline-block;"
                     title="Ver registro de cambios en entrevista de encuadre"
                     onclick="window.framingMeetingLog(${idCase});">
            <small><span>Ver registro de cambios en entrevista de
            encuadre</span></small>
        </a>
        </h3>
    </div>
</div>

<br/>

<div class="row">
<div class="col-xs-10 col-xs-offset-1">
    <div class="widget-box transparent">
        <div class="widget-header widget-header-flat">
            <h4 class="lighter">
                <i class="icon-legal orange"></i>
                Delitos
            </h4>

            <div class="widget-toolbar">
                <a href="#" data-action="collapse">
                    <i class="icon-chevron-up"></i>
                </a>
            </div>
        </div>

        <div class="widget-body" ng-init='listCrime=${listCrime};'>
            <div class="widget-main no-padding">
                <table class="table table-bordered table-striped">
                    <thead class="thin-border-bottom">
                    <tr>
                        <th>
                            <i class="icon-caret-right blue"></i>
                            Delito
                        </th>

                        <th>
                            <i class="icon-caret-right blue"></i>
                            Art&iacute;culo
                        </th>

                        <th class="hidden-480">
                            <i class="icon-caret-right blue"></i>
                            Federal
                        </th>

                        <th class="hidden-480">
                            <i class="icon-caret-right blue"></i>
                            Comentarios
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr ng-repeat="c in listCrime">
                        <td>{{c.crime.name}}</td>
                        <td>{{c.article}}</td>
                        <td>{{c.federal.name}}</td>
                        <td>{{c.comment}}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- /widget-main -->
        </div>
        <!-- /widget-body -->
    </div>
    <!-- /widget-box -->
</div>
<br/>

<div class="row">
    <div ng-show="FMsuccessMsg&&FMsuccessMsg!=''" class="col-xs-12 alert alert-success element-center success-font">
        {{FMsuccessMsg}}
        <br/>
    </div>
    <div ng-show="FMerrorMsg&&FMerrorMsg!=''" class="alert alert-danger element-center error-font">
        {{FMerrorMsg}}
        <br/>
    </div>
    <div ng-show="FMerrorMsgLst&&FMerrorMsgLst.length>0" class="alert alert-danger element-center error-font">
        <span ng-repeat="error in FMerrorMsgLst track by $index">{{error}}<br/></span>
    </div>
</div>

<div class="row">
    <div class="col-xs-12">
        <div ng-show="listMsgError['general']" class="alert alert-danger element-center error-font">
            <span ng-bind-html="listMsgError['general']">
            </span>
        </div>
    </div>
</div>
<br/>

<div class="row">
<div class="col-sm-10 col-xs-offset-1">
<div class="tabbable tabs-left">
<ul class="nav nav-tabs" id="tabFramingMeeting">

<li class="active" id="liImputed">
    <a data-toggle="tab" href="#personalData">
        <div class="row">
            <div class="col-xs-10">
                <i class="purple glyphicon glyphicon-user bigger-200"></i>
                Datos personales
                <br/>

                <div class="col-xs-offset-3">y Entorno social</div>
            </div>
            <div class="col-xs-2" ng-show="listMsgError['imputed']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liImputed');"></i>

                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['imputed']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liImputedHome">
    <a data-toggle="tab" href="#address">
        <div class="row">
            <div class="col-xs-10">
                <i class="green  icon-home  bigger-200"></i>
                Domicilios
            </div>
            <div class="col-xs-2" ng-show="listMsgError['imputedHome']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liImputedHome');"></i>

                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['imputedHome']">
                                    </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<!--<li>
    <a data-toggle="tab" href="#accompaniment" ng-click="resizeMap()">
        <i class="red glyphicon glyphicon-user bigger-200"></i>&nbsp;&nbsp;
        Persona que acompa&ntilde;ara durante <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;el proceso
                        </a>
                    </li>-->

<li id="liSocialNetwork">
    <a data-toggle="tab" href="#houseMate">
        <div class="row">
            <div class="col-xs-10">
                <i class="blue icon-group bigger-200"></i>
                Personas con las que vive el imputado
            </div>
            <div class="col-xs-2" ng-show="listMsgError['socialNetwork']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120  dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liSocialNetwork');"></i>

                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['socialNetwork']">
                                    </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liReference">
    <a data-toggle="tab" href="#references">
        <div class="row">
            <div class="col-xs-10">
                <i class="red icon-list bigger-200"></i>
                Referencias personales
            </div>
            <div class="col-xs-2" ng-show="listMsgError['reference']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liReference');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['reference']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liVictim">
    <a data-toggle="tab" href="#victim">
        <div class="row">
            <div class="col-xs-10">
                <i class="orange icon-eye-open bigger-200"></i>
                Victimas y testigos
            </div>
            <div class="col-xs-2" ng-show="listMsgError['victim']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liVictim');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['victim']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liSchool">
    <a data-toggle="tab" href="#school">
        <div class="row">
            <div class="col-xs-10">
                <i class="purple icon-book  bigger-200"></i>
                Historia escolar
            </div>
            <div class="col-xs-2" ng-show="listMsgError['school']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liSchool');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['school']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liJob">
    <a data-toggle="tab" href="#job">
        <div class="row">
            <div class="col-xs-10">
                <i class="pink icon-briefcase  bigger-200"></i>
                Historia laboral
            </div>
            <div class="col-xs-2" ng-show="listMsgError['job']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liJob');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['job']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liActivities">
    <a data-toggle="tab" href="#activities">
        <div class="row">
            <div class="col-xs-10">
                <i class="black icon-male bigger-200"></i>
                Actividades que realiza el imputado
            </div>
            <div class="col-xs-2" ng-show="listMsgError['activities']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liActivities');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['activities']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liDrug">
    <a data-toggle="tab" href="#drugs">
        <div class="row">
            <div class="col-xs-10">
                <i class="green icon-warning-sign  bigger-200"></i>
                Consumo de sustancias
            </div>
            <div class="col-xs-2" ng-show="listMsgError['drug']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liDrug');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['drug']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liQuestion">
    <a data-toggle="tab" href="#addtional">

        <div class="row">
            <div class="col-xs-10">
                <i class="red icon-question bigger-200"></i>
                Formulario de preguntas al supervisado
            </div>
            <div class="col-xs-2" ng-show="listMsgError['question']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liQuestion');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['question']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liFinger">
    <a data-toggle="tab" href="#fingerTab">

        <div class="row">
            <div class="col-xs-10">
                <i class="icon-edit bigger-200"></i>
                Enrolamiento
            </div>
            <div class="col-xs-2" ng-show="listMsgError['fingerprint']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liFinger');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['fingerprint']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
<li id="liAnalysis">
    <a data-toggle="tab" href="#environmentAnalysis">
        <div class="row">
            <div class="col-xs-10">
                <i class="blue icon-eye-open bigger-200"></i>
                An&aacute;lisis de entorno
            </div>
            <div class="col-xs-2" ng-show="listMsgError['analysis']">
                <div class="tools">
                    <div class="inline position-relative">
                        <i class=" icon-exclamation-sign red  icon-only bigger-120 dropdown-toggle"
                           data-toggle="dropdown" ng-click="changeZIndex('liAnalysis');"></i>
                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close"
                            style="width: 400px; z-index: 100000; padding: 0 0;">
                            <div class="alert-danger element-center error-font">
                                <span ng-bind-html="listMsgError['analysis']">
                                </span>
                            </div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </a>
</li>
</ul>

<div class="tab-content">
    <div id="personalData" class="tab-pane  in active">
        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/personalData/_personalData.jsp" %>
    </div>

    <div id="address" class="tab-pane">
        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/framingAddress/_framingAddress.jsp" %>
    </div>

    <%--<div id="accompaniment" class="tab-pane">--%>
    <%--<%@ include--%>
    <%--file="/WEB-INF/jsp/supervisor/framingMeeting/proccessAccompaniment/_processAccompaniment.jsp" %>--%>
    <%--</div>--%>


    <div id="houseMate" class="tab-pane">
        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/housemate/_framingHousemate.jsp" %>
    </div>

    <div id="references" class="tab-pane">
        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/references/_framingReferences.jsp" %>
    </div>

    <div id="victim" class="tab-pane">
        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/victim/_framingVictim.jsp" %>
    </div>

    <div id="school" class="tab-pane">
        <%@ include
                file="/WEB-INF/jsp/supervisor/framingMeeting/school/_school.jsp" %>
    </div>
    <div id="job" class="tab-pane">
        <%@ include
                file="/WEB-INF/jsp/supervisor/framingMeeting/job/_framingJob.jsp" %>
    </div>

    <div id="activities" class="tab-pane">
        <%@ include
                file="/WEB-INF/jsp/supervisor/framingMeeting/activities/_framingActivities.jsp" %>
    </div>

    <div id="drugs" class="tab-pane">
        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/drugs/_drugs.jsp" %>
    </div>

    <div id="environmentAnalysis" class="tab-pane">
        <%@ include
                file="/WEB-INF/jsp/supervisor/framingMeeting/environmentAnalysis/_environmentAnalysis.jsp" %>
    </div>

    <div id="addtional" class="tab-pane">
        <%@ include
                file="/WEB-INF/jsp/supervisor/framingMeeting/additionalQuestions/_addtionalQuestions.jsp" %>
    </div>

    <div id="fingerTab" class="tab-pane">
        <%@ include file="/WEB-INF/jsp/supervisor/framingMeeting/fingerPrinting/index.jsp" %>
    </div>

</div>
</div>
</div>

</div>
<br/>

<div class="row">
    <div class="col-xs-12">
        <div ng-show="listMsgError['general']" class="alert alert-danger element-center error-font">
            <span ng-bind-html="listMsgError['general']">
            </span>
        </div>
    </div>
</div>
<br/>

<div class="row">
    <div class="modal-footer" ng-show="fm.objView.canTerminate==true">
                    <span class="btn btn-default btn-sm" ng-click="returnFM();">
                        Regresar
                    </span>
                    <span class="btn btn-default btn-primary btn-sm" ng-disabled="WaitFor==true"
                          ng-click="doTerminate();">
                          Terminar
                    </span>
    </div>
    <div class="modal-footer" ng-show="fm.objView.canTerminate==false">
                    <span class="btn btn-default btn-sm" ng-click="returnFM();">
                        Regresar
                    </span>
    </div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>
</div>
</body>
</html>
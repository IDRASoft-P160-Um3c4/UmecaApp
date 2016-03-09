<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <%@ include file="/WEB-INF/jsp/shared/headUmGrid.jsp" %>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/detainedCarousel.css"/>

    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/detentionRecord/detentionCarouselCtrl.js"></script>

    <title>Casos para entrevista de encuadre</title>
</head>

<body scroll="no" ng-app="ptlUmc">

<div class="container body-content detainedCont">

    <script>
        $(document).ready(function () {

            fullScreen = function () {
                if (!document.fullscreenElement &&    // alternative standard method
                        !document.mozFullScreenElement && !document.webkitFullscreenElement && !document.msFullscreenElement ) {  // current working methods
                    if (document.documentElement.requestFullscreen) {
                        document.documentElement.requestFullscreen();
                    } else if (document.documentElement.msRequestFullscreen) {
                        document.documentElement.msRequestFullscreen();
                    } else if (document.documentElement.mozRequestFullScreen) {
                        document.documentElement.mozRequestFullScreen();
                    } else if (document.documentElement.webkitRequestFullscreen) {
                        document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
                    }
                } else {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.webkitExitFullscreen) {
                        document.webkitExitFullscreen();
                    }
                }
            }


            var OutOfTimePeriod = "${OutOfTimePeriod}";

            var _48hrsMil = 172800000;
            var _1hourMil = 3600000;
            var _1minMil = 60000;


            var OutOfTimeMil = OutOfTimePeriod * _1hourMil;

            calcTerm = function (dateMil, timeMil, now, obj, id, prosecute) {

                var totDue = "";
                var totLeft = "";
                if (prosecute == 'false') {
                    var milNow = parseInt(now, 10);
                    var milDate = parseInt(dateMil, 10);
                    var milTime = parseInt(timeMil, 10);

                    var dt = new Date(milTime);
                    var dtA = new Date(milDate);

                    var lowDt = new Date(dtA.getFullYear(), dtA.getMonth(), dtA.getDate(), dt.getHours(), dt.getMinutes(), dt.getSeconds());
                    var lowL = lowDt.getTime();
                    var upL = lowL + _48hrsMil;

                    var stillVisible = upL + OutOfTimeMil;

                    if (milNow <= upL) {

                        var dueMil = milNow - lowL;
                        var hrsDue = Math.floor(dueMil / _1hourMil);
                        var minDue = dueMil % _1hourMil;
                        minDue = Math.floor(minDue / _1minMil);

                        var leftMil = upL - milNow;
                        var hrsLeft = Math.floor(leftMil / _1hourMil);
                        var minLeft = leftMil % _1hourMil;
                        minLeft = Math.floor(minLeft / _1minMil);

                        if (hrsDue > -1 && minDue > -1)
                            totDue = hrsDue + " hrs. " + minDue + " min.";
                        if (hrsLeft > -1 && minLeft > -1)
                            totLeft = hrsLeft + " hrs. " + minLeft + " min.";
                    } else if (milNow > stillVisible) {
                        $(obj).jqGrid('delRowData', id);
                        return;
                    }
                    else {
                        totDue = "El plazo ha vencido.";
                        totLeft = "El plazo ha vencido.";
                    }
                }
                else {
                    totDue = "Judicializado.";
                    totLeft = "Judicializado.";
                }

                $(obj).jqGrid('setRowData', id, {dueTime: totDue});
                $(obj).jqGrid('setRowData', id, {timeLeft: totLeft});


            };


        });
    </script>

    <div class="containerFixed" ng-controller="modalDlgController">

        <h2 class="element-center">
            <button type="button" class="btn btn-primary btn-xs" onclick="fullScreen()">
                <span class="glyphicon glyphicon-fullscreen" style="margin: auto" aria-hidden="true"></span></button>
            <i class="glyphicon icon-comments-alt "></i> S&aacute;bana de detenidos
        </h2>

        <div class="row" ng-controller="detentionCarouselController" ng-init="init(); m.OutOfTimePeriod = ${(OutOfTimePeriod == null) ? 0 : OutOfTimePeriod};" ng-cloak drctv>
            <div class="col-xs-12">
                <section class="card">
                    <div class="row black-54">
                        <div class="col-xs-2"><h5><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Imputado</h5></div>
                        <div class="col-xs-1 center"><h5><span class="glyphicon glyphicon-align-left" aria-hidden="true"></span> Edad</h5></div>
                        <div class="col-xs-1"><h5><span class="glyphicon glyphicon-folder-close" aria-hidden="true"></span> Carpeta de investigaci&oacute;n</h5></div>
                        <div class="col-xs-1 center"><h5><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> Fecha inicio</h5></div>
                        <div class="col-xs-1 center"><h5><span class="glyphicon glyphicon-time" aria-hidden="true"></span> Hora inicio</h5></div>
                        <div class="col-xs-2"><h5><span class="glyphicon glyphicon-folder-close" aria-hidden="true"></span> Unidad de investigaci&oacute;n</h5></div>
                        <div class="col-xs-1"><h5><span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span> Presentado por</h5></div>
                        <div class="col-xs-1 center"><h5><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Distrito</h5></div>
                        <div class="col-xs-1 center"><h5><span class="glyphicon glyphicon-time" aria-hidden="true"></span> T&eacute;rmino</h5></div>
                        <div class="col-xs-1 center"><h5><span class="glyphicon glyphicon-time" aria-hidden="true"></span> Tiempo para cumplir t&eacute;rmino</h5></div>
                    </div>
                </section>

                <ul class="container list-group" >
                    <li id="itemList" class="list-group-item repeat-item" ng-repeat="item in items">
                        <div class="row black-86">
                            <div class="col-xs-2">{{item.fullName}}</div>
                            <div class="col-xs-1 center">{{item.age}}</div>
                            <div class="col-xs-1 ">{{item.idFolder}}</div>
                            <div class="col-xs-1 center">{{item.initDateStr}}</div>
                            <div class="col-xs-1 center">{{item.initTimeStr}}</div>
                            <div class="col-xs-2 ">{{item.investigationUnit}}</div>
                            <div class="col-xs-1">{{item.crime}}</div>
                            <div class="col-xs-1 center">{{item.district}}</div>
                            <div class="col-xs-1 center">{{item.dueTime}}</div>
                            <div class="col-xs-1 center">{{item.timeLeft}}</div>
                        </div>

                    </li>
                </ul>


            </div>
        </div>



        <div class="blocker" ng-show="working">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
</div>

</body>
</html>
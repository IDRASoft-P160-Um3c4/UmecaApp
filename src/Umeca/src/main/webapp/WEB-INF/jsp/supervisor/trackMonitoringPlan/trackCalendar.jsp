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
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp"%>
    <link href="${pageContext.request.contextPath}/assets/content/themes/umeca/fullcalendar.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css" />

    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/typeahead-bs2.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery.ui.touch-punch.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/fullcalendar.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/bootbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/commonActMonPlan.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/trackMonitoringPlan/trackMonPlanCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/trackMonitoringPlan/monActivityCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/hiddenDrct.js"></script>
    <script>
        jQuery(function($) {

            var monitoringPlanId = ${monitoringPlanId};
            var scopeTrackMon = angular.element($("#TrackMonPlanControllerId")).scope();
            window.calendar = $('#calendar').fullCalendar({
                buttonText: {
                    prev: '<i class="icon-chevron-left"></i>',
                    next: '<i class="icon-chevron-right"></i>'
                },

                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
                },
                allDayText: 'Todo el día',
                allDaySlot: false,
                slotMinutes: 30,
                axisFormat: 'HH:mm',
                timeFormat: {
                    agenda: 'H:mm{ - H:mm}'
                },
                minTime: 0,
                maxTime: 24,
                defaultView: 'agendaWeek',
                editable: false,
                selectable: false,
                selectHelper: false,
                eventResize: function(event, dayDelta, minuteDelta, allDay, revertFunc) {
                    revertFunc();
                    return;
                },
                eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc) {
                    revertFunc();
                    return;
                },
                viewRender: function( view, element ){
                    scopeTrackMon.loadActivities(monitoringPlanId, view.start, view.end, '<c:url value='${urlGetActivities}' />');
                },
                eventClick: function(event, jsEvent, view) {
                    var ans = {};
                    window.showUpsert(event.idActivity, "#angJsjqGridId", '<c:url value='${urlShowActivity}' />', undefined, undefined,
                        function(ans){
                            try{
                                if(ans !== undefined && ans.resp !== undefined && ans.resp.hasError === false){

                                    if(ans.resp.returnData === "REALIZADA")
                                        event.className = "label-success";
                                    else
                                        event.className = "label-grey";

                                    calendar.fullCalendar('updateEvent', event);
                                }
                            }catch(e){return;}

                        });
                }

            });

            scopeTrackMon.m = { calendar:calendar};

        });
    </script>

    <title>Planes de seguimiento</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content"  ng-controller="trackMonPlanController" id="TrackMonPlanControllerId">
    <div class="page-content">
        <div class="page-header">
            <h1 class="element-center">
                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Planes de seguimiento (Calendario)
            </h1>
        </div>
        <div class="blocker" ng-show="workingTrack">
            <div>
                Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="space"></div>
                    <div class="col-xs-4 col-xs-offset-4 element-center">
                        <div class="btn btn-success element-center" ng-disabled="waitFor==true" ng-click="returnToCases('<c:url value="${urlReturn}" />')"><i class="glyphicon glyphicon-chevron-left"></i> &nbsp; Regresar</div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6 widget-container-span col-xs-offset-3">
                        <div class="widget-box transparent">
                            <div class="widget-header">
                                <h4 class="lighter">Filtro</h4>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main padding-12 no-padding-left no-padding-right">
                                    <div class="tab-content padding-4">
                                        <div id="filter" class="tab-pane in active">
                                            <div class="slim-scroll" data-height="100">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        <h6>Actividad de supervisión</h6>
                                                    </div>
                                                    <div class="col-xs-8">
                                                        <select class="form-control element-center" ng-model="m.activity"
                                                                ng-options="e.name for e in lstActivities"
                                                                ng-change="onChangeSelect();"
                                                                ng-init='lstActivities = ${lstActivities}; initActivitySelect();'>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row" ng-show="msgError">
                    <div class="col-xs-8 col-xs-offset-2 alert alert-danger element-center">
                        <span class="control-label element-center">{{msgError}}</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="space"></div>
                        <div id="calendar"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="angJsjqGridId" ng-controller="modalDlgController">
    <div class="blocker" ng-show="working">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt="" />
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>

</body>
</html>
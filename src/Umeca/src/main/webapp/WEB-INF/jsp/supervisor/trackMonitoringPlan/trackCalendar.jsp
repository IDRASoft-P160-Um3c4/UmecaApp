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

    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/trackMonitoringPlan/trackMonPlanCtrl.js"></script>
    <script>
        jQuery(function($) {

            /* initialize the calendar
            -----------------------------------------------------------------*/

            var calendar = $('#calendar').fullCalendar({
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
                selectable: true,
                selectHelper: false,
                eventResize: function(event, dayDelta, minuteDelta, allDay, revertFunc) {
                    revertFunc();
                    return;
                },
                eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc) {
                    revertFunc();
                    return;
                },
                select: function(start, end, allDay) {
                    /*var today = new Date();
                    today.setHours(0,0,0,0);
                    if(start < today){
                        scope.showMsg({title:"Plan de seguimiento", msg:'No es posible agregar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                        return;
                    }

                    scope.showDlg({title:'Agregar actividad', start: start, end: end, isNew: true})
                            .then(function(result){
                                for(i=0; i<result.activities.length; i++){
                                    calendar.fullCalendar('renderEvent', result.activities[i], true);
                                }
                            });
                    calendar.fullCalendar('unselect');      */
                }
                ,
                eventClick: function(event, jsEvent, view) {
                    /*var today = new Date();
                    today.setHours(0,0,0,0);
                    var isReadOnly = false;
                    if(event.start < today){
                        isReadOnly = true;
                        //scope.showMsg({title:"Plan de seguimiento", msg:'No es posible modificar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                        //return;
                    }

                    scope.showDlg({title:'Modificar o eliminar actividad', start: event.start, end: event.end, isNew: false, event: event, isReadOnly: isReadOnly})
                            .then(function(result){
                                switch(result.option){
                                    case 'REMOVE':
                                        calendar.fullCalendar('removeEvents' , function(ev){
                                            return (ev._id == event._id);
                                        });
                                        scopeMon.addActivityToDelete(event.idActivity);
                                        break;
                                    case 'UPDATE':
                                        calendar.fullCalendar('updateEvent', event);
                                        break;
                                    default:
                                        break;
                                }
                            });*/
                }

            });
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
        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="space"></div>
                    <div class="col-xs-3 col-xs-offset-3 element-center">
                        <div class="btn btn-success element-center" ng-disabled="waitFor==true" ng-click="returnToCases('<c:url value="/supervisor/generateMonitoringPlan/index.html" />')"><i class="glyphicon glyphicon-chevron-left"></i> &nbsp; Regresar</div>
                    </div>
                    <div class="col-xs-3 element-center">
                        <div class="btn btn-primary element-center" ng-disabled="waitFor==true"
                             ng-click="saveActivities('${caseId}', '${monitoringPlanId}', '<c:url value="/supervisor/generateMonitoringPlan/doUpsert.json" />')"><i class="glyphicon glyphicon-ok-circle"></i> &nbsp; Guardar</div>
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

    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>
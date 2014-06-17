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


    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/generateMonitoringPlan/generateMonPlanCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/shared/upsertActivityEventController.js"></script>
    <script>
        jQuery(function($) {

            var caseInfo = {caseId:"${caseId}", folderId: "${folderId}", personName: "${personName}", monStatus: "${monStatus}", monitoringPlanId: "${monitoringPlanId}"};

            var date = new Date();
            $('#id-date-picker-start,#id-date-picker-end').datepicker({autoclose:true, startDate:new Date(date.getFullYear(), date.getMonth(), date.getDate()-1)}).next().on(ace.click_event, function(){
                $(this).prev().focus();
            });

            $('#id-timepicker-start, #id-timepicker-end').timepicker({
                minuteStep: 15,
                showSeconds: false,
                showMeridian: false
            }).next().on(ace.click_event, function(){
                $(this).prev().focus();
            });

            //Initilize angular scope and config
            var scope = angular.element($("#UpsertActivityEventDlgId")).scope();
            scope.config({startDateId: "#id-date-picker-start", endDateId: "#id-date-picker-end",
                    startTimeId: "#id-timepicker-start", endTimeId: "#id-timepicker-end", caseInfo: caseInfo});


            /* initialize the external events
             -----------------------------------------------------------------*/
            $('#external-events div.external-event').each(function() {
                // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
                // it doesn't need to have a start or end
                var eventObject = {
                    title: $.trim($(this).text()) // use the element's text as the event title
                };

                // store the Event Object in the DOM element so we can get to it later
                $(this).data('eventObject', eventObject);

                // make the event draggable using jQuery UI
                $(this).draggable({
                    zIndex: 999,
                    revert: true,      // will cause the event to go back to its
                    revertDuration: 0  //  original position after the drag
                });

            });

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
                editable: true,
                selectable: true,
                selectHelper: true,
                select: function(start, end, allDay) {
                    var today = new Date();
                    today.setHours(0,0,0,0);
                    if(start < today){
                        scope.showMsg({title:"Error al agregar una actividad", msg:'No es posible agregar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                        return;
                    }

                    scope.showDlg({title:'Agregar actividad', start: start, end: end, isNew: true})
                            .then(function(activities){
                                for(i=0; i<activities.length; i++){
                                    calendar.fullCalendar('renderEvent', activities[i], true);
                                }
                            });
                    calendar.fullCalendar('unselect');
                }
                ,
                eventClick: function(calEvent, jsEvent, view) {

                    scope.showDlg({title:'Modificar actividad', start: calEvent.start, end: calEvent.end, isNew: false})
                            .then(function(activity, option){
                                switch(option){
                                    case 'REMOVE':
                                        calendar.fullCalendar('removeEvents' , function(ev){
                                            return (ev._id == calEvent._id);
                                        });
                                        break;
                                    case 'UPDATE':
                                        break;
                                    default:
                                        break;
                                }
                            });

                }

            });
        })
    </script>

    <title>Plan de Supervisión</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content"  ng-controller="generateMonPlanController">
    <div class="page-content">
        <div class="page-header">
            <h1 class="element-center">
                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Plan de supervisión (Calendario)
            </h1>
        </div>
        <div class="page-header">
            <h1 class="element-center" ng-init="caseId='${caseId}'; folderId='${folderId}'; personName='${personName}';">
                Id. del caso: {{caseId}}, &nbsp;&nbsp; No. de carpeta: {{folderId}}, &nbsp;&nbsp; Imputado: {{personName}}
            </h1>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="space"></div>
                        <div id="calendar"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jsp/supervisor/shared/upsertActivity.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp"%>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp"%>
</div>

</body>
</html>
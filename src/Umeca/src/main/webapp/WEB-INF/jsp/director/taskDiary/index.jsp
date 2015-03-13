<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--
* Project: Umeca
* Date: 4/30/14
* Time: 9:53 AM
-->

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <link href="${pageContext.request.contextPath}/assets/content/themes/umeca/fullcalendar.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>

    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery.ui.touch-punch.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/fullcalendar.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/bootbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/taskDiary/calendarTaskDiaryCtrl.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/commonActMonPlan.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/upsertCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/modalDlgCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/director/taskDiary/upsertActivityEventController.js"></script>

    <script>
        jQuery(function ($) {
            var lstPriorities = ${lstPriorities};
            var lstPrioritiesIndex = ${lstPriorities};
            var scope = angular.element($("#UpsertActivityEventDlgId")).scope();
            var scopeCal = angular.element($("#CalendarTaskDiaryControllerId")).scope();
            scopeCal.lstPriorities = lstPrioritiesIndex;
            scopeCal.initActivitySelect();

            $('#id-timepicker-start, #id-timepicker-end').timepicker({
                minuteStep: 15,
                showSeconds: false,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            scope.config({
                startTimeId: "#id-timepicker-start",
                endTimeId: "#id-timepicker-end",
                lstPriorities: lstPriorities
            });

            $('#external-events div.external-event').each(function () {
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
                //events: lstEventsAct,
                allDayText: 'Todo el d&iacute;a',
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
                eventResize: function (event, dayDelta, minuteDelta, revertFunc) {
                    var today = new Date();
                    today.setHours(0, 0, 0, 0);

                    if(event.actIsDone === true){
                        revertFunc();
                        scope.showMsg({title: "Agenda de actividades", msg: 'No es posible modificar una actividad ya realizada.', type: "danger"});
                        return;
                    }

                    if (event.start < today) {
                        revertFunc();
                        scope.showMsg({title: "Agenda de actividades", msg: 'No es posible modificar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                        return;
                    }

                    var oldDay = event.start.addDays(dayDelta * (-1));
                    oldDay.setHours(0, 0, 0, 0);
                    if (oldDay < today) {
                        revertFunc();
                        scope.showMsg({title: "Agenda de actividades", msg: 'No es posible modificar una actividad con una fecha anterior.', type: "danger"});
                        return;
                    }

                    event.isModified = true;
                    event.doTitle(true);
                },
                eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc) {
                    var today = new Date();
                    today.setHours(0, 0, 0, 0);

                    if(event.actIsDone === true){
                        revertFunc();
                        scope.showMsg({title: "Agenda de actividades", msg: 'No es posible modificar una actividad ya realizada.', type: "danger"});
                        return;
                    }

                    if (event.start < today) {
                        revertFunc();
                        scope.showMsg({title: "Agenda de actividades", msg: 'No es posible modificar una actividad a una fecha anterior a la fecha actual.', type: "danger"});
                        return;
                    }

                    var oldDay = event.start.addDays(dayDelta * (-1));
                    oldDay.setHours(0, 0, 0, 0);
                    if (oldDay < today) {
                        revertFunc();
                        scope.showMsg({title: "Agenda de actividades", msg: 'No es posible modificar una actividad con una fecha anterior.', type: "danger"});
                        return;
                    }

                    event.isModified = true;
                    event.doTitle(true);
                },
                viewRender: function (view, element) {
                    scopeCal.loadActivities(view.start, view.end, '<c:url value='${urlGetActivities}' />');
                },
                select: function (start, end, allDay) {
                    var today = new Date();
                    today.setHours(0, 0, 0, 0);
                    if (start < today) {
                        scope.showMsg({
                            title: "Agenda de actividades",
                            msg: 'No es posible agregar una actividad con fecha anterior a la fecha actual.',
                            type: "danger"
                        });
                        return;
                    }

                    scope.showDlg({title: 'Agregar actividad', start: start, end: end, isNew: true})
                            .then(function (result) {
                                var lstAct = [];
                                lstAct.push(result.activity);
                                scopeCal.mergeAndFilterActivities(lstAct);
                                //calendar.fullCalendar('renderEvent', result.activity, true);
                            });
                    calendar.fullCalendar('unselect');
                },
                eventClick: function (event, jsEvent, view) {
                    var today = new Date();
                    today.setHours(0, 0, 0, 0);
                    var isReadOnly = false;
                    if (event.start < today) {
                        isReadOnly = true;
                    }

                    scope.showDlg({title: 'Modificar o eliminar actividad', start: event.start, end: event.end, isNew: false, event: event, isReadOnly: isReadOnly}).then(function (result) {
                                switch (result.option) {
                                    case 'REMOVE':
                                        calendar.fullCalendar('removeEvents', function (ev) {
                                            return (ev._id == event._id);
                                        });
                                        scopeCal.addActivityToDelete(event);
                                        break;
                                    case 'UPDATE':
                                        calendar.fullCalendar('updateEvent', event);
                                        break;
                                    default:
                                        break;
                                }
                            });
                }
            });

            scopeCal.m = { calendar: calendar};
        })
    </script>

    <title>Agenda de actividades</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>


<div class="container body-content" ng-controller="calendarTaskDiaryController" id="CalendarTaskDiaryControllerId" ng-cloak>
    <style>
        <c:forEach var="priority" items="${lstPrioritiesDt}">
        .label-act-evt-${priority.id} {
            background-color: ${priority.description} !important;
        }

        .label-act-evt-done-${priority.id} {
            background-color: ${priority.description} !important;
            opacity: 0.3 !important;
            filter: alpha(opacity=30) !important;
        }

        </c:forEach>
    </style>

    <div class="page-content">
        <div class="page-header">
            <h1 class="element-center">
                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Agenda de actividades (Direcci&oacute;n)
            </h1>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="col-xs-4 widget-container-span col-xs-offset-4">
                        <div class="widget-box transparent">
                            <div class="widget-header align-center">
                                <h4 class="lighter">Filtro</h4>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main padding-12 no-padding-left no-padding-right">
                                    <div class="tab-content padding-4">
                                        <div id="filter" class="tab-pane in active">
                                            <div class="slim-scroll" data-height="100">
                                                <div class="row">
                                                    <div class="col-xs-4 col-xs-offset-4 align-center">
                                                        <h6>Prioridad de la actividad</h6>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-xs-4 col-xs-offset-4">
                                                        <select class="form-control element-center"
                                                                ng-model="m.priority"
                                                                ng-options="e.name for e in lstPriorities"
                                                                ng-change="onChangePriority();">
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
                <div class="blocker" ng-show="workingTrack">
                    <div>
                        Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-4 col-xs-offset-4 element-center">
                                <div class="btn btn-primary element-center" ng-disabled="waitFor===true"
                                     ng-click="saveActivities('<c:url value="/director/taskDiary/doUpsert.json" />')">
                                    <i class="glyphicon glyphicon-ok-circle"></i> &nbsp; Guardar
                                </div>
                            </div>
                        </div>
                        <div class="row" ng-show="msgError">
                            <br/>
                            <div class="col-xs-8 col-xs-offset-2 alert alert-danger element-center">
                                <span class="control-label element-center"><div ng-bind-html="formatHtml(msgError);"></div></span>
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
    </div>
</div>
<div id="angJsjqGridId" ng-controller="modalDlgController">
    <div class="blocker" ng-show="working">
        <div>
            Cargando...<img src="<c:url value='/assets/content/images/ajax_loader.gif' />" alt=""/>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/director/taskDiary/upsertActivity.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
<%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</body>
</html>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
    <link href="${pageContext.request.contextPath}/assets/content/themes/umeca/fullcalendar.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>

    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/typeahead-bs2.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery.ui.touch-punch.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/fullcalendar.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/bootbox.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>

    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/rolSupervision/generateRolSupervisionCtrl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/supervisorManager/rolSupervision/upsertRolActivityCtrl.js"></script>

    <script>

        jQuery(function ($) {
            var lstSupervisor = ${lstSupervisor};
            var isEvaluator = ${isEvaluator};

            var titleD = "Rol de supervisi&oacute;n";
            var msgDA = "No es posible agregar una actividad para el rol de supervisi&oacute;n con fecha anterior a la fecha actual.";
            var msgDM = "No es posible modificar una actividad para el rol de supervisi&oacute;n con fecha anterior a la fecha actual.";

            if(isEvaluator === true){
                titleD = "Rol de evaluaci&oacute;n";
                msgDA = "No es posible agregar una actividad para el rol de evaluaci&oacute;n con fecha anterior a la fecha actual.";
                msgDM = "No es posible modificar una actividad para el rol de evaluaci&oacute;n con fecha anterior a la fecha actual.";
            }

            window.calendarOnEventChange = function (event, revertFunc) {
                var today = new Date();
                today.setHours(0, 0, 0, 0);
                if (event.start < today) {
                    revertFunc();
                    scope.showMsg({
                        title: titleD,
                        msg: msgDM,
                        type: "danger"
                    });
                    return;
                }
                event.doTitle(true);
                event.isModified = true;
            }

            var date = new Date();
            $('#id-date-picker-start,#id-date-picker-end').datepicker(
                    {
                        autoclose: true,
                        startDate: new Date(date.getFullYear(), date.getMonth(), date.getDate() - 1)
                    }).next().on(ace.click_event, function () {
                        $(this).prev().focus();
                    });

            $('#id-timepicker-start, #id-timepicker-end').timepicker({
                minuteStep: 15,
                showSeconds: false,
                showMeridian: false
            }).next().on(ace.click_event, function () {
                $(this).prev().focus();
            });

            var scope = angular.element($("#UpsertRolActivityDlgId")).scope();
            var scopeMon = angular.element($("#RolSupervisionControllerId")).scope();

            scope.config({
                startDateId: "#id-date-picker-start", endDateId: "#id-date-picker-end",
                startTimeId: "#id-timepicker-start", endTimeId: "#id-timepicker-end"
            }, lstSupervisor);

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
                viewRender: function (view, element) {
                    if(isEvaluator === true)
                        scopeMon.loadActivitiesEvaluator(view.start, view.end, '<c:url value='${urlGetActivities}' />');
                    else
                        scopeMon.loadActivities(view.start, view.end, '<c:url value='${urlGetActivities}' />');
                },
                eventResize: function (event, dayDelta, minuteDelta, allDay, revertFunc) {
                    window.calendarOnEventChange(event, revertFunc);
                },
                eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc) {
                    window.calendarOnEventChange(event, revertFunc);
                },
                select: function (start, end, allDay) {
                    var today = new Date();
                    today.setHours(0, 0, 0, 0);
                    if (start < today) {
                        scope.showMsg({
                            title: titleD,
                            msg: msgDA,
                            type: "danger"
                        });
                        return;
                    }
                    scope.showDlg({start: start, end: end, isNew: true})
                            .then(function (result) {
                                for (i = 0; i < result.activities.length; i++) {
                                    calendar.fullCalendar('renderEvent', result.activities[i], true);
                                }
                            });
                    calendar.fullCalendar('unselect');
                }
                ,
                eventClick: function (event, jsEvent, view) {
                    var today = new Date();
                    today.setHours(0, 0, 0, 0);
                    var isReadOnly = false;
                    if (event.start < today) {
                        isReadOnly = true;
                        //scope.showMsg({title:"Plan de seguimiento", msg:'No es posible modificar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                        //return;
                    }

                    if(isEvaluator === true){
                        scope.showDlgEvaluator({
                            title: 'Modificar o eliminar actividad',
                            start: event.start,
                            end: event.end,
                            isNew: false,
                            event: event,
                            isReadOnly: isReadOnly
                        })
                                .then(function (result) {
                                    switch (result.option) {
                                        case 'REMOVE':
                                            calendar.fullCalendar('removeEvents', function (ev) {
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
                                });

                    }else{
                        scope.showDlg({
                            title: 'Modificar o eliminar actividad',
                            start: event.start,
                            end: event.end,
                            isNew: false,
                            event: event,
                            isReadOnly: isReadOnly
                        })
                                .then(function (result) {
                                    switch (result.option) {
                                        case 'REMOVE':
                                            calendar.fullCalendar('removeEvents', function (ev) {
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
                                });
                    }

                }

            });

            scopeMon.m = {calendar: calendar, lstSupervisor: lstSupervisor};

        });
    </script>
    <script src="${pageContext.request.contextPath}/assets/scripts/app/shared/dateTimePickerCursor.js"></script>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="rolSupervisionController" id="RolSupervisionControllerId">
    <div class="page-content">
        <div class="page-header">
            <h1 class="element-center">
                <sec:authorize access="hasRole('ROLE_EVALUATION_MANAGER')">
                    <i class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Rol de evaluaci&oacute;n
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SUPERVISOR_MANAGER')">
                    <i class="glyphicon glyphicon-transfer"></i>&nbsp;&nbsp;Rol de supervisi&oacute;n
                </sec:authorize>
            </h1>
        </div>
        <div class="row">
            <div class="col-xs-12 element-center">
                <div class="row">
                    <div class="space"></div>
                    <div class="col-xs-2 col-xs-offset-5 element-center">
                        <sec:authorize access="hasRole('ROLE_EVALUATION_MANAGER')">
                            <div class="btn btn-primary element-center" ng-disabled="waitFor==true"
                                 ng-click="saveRolEvaluatorActivities('<c:url value="/managereval/rolEvaluation/doUpsert.json" />')">
                                <i class="glyphicon glyphicon-ok-circle"></i> &nbsp; Guardar
                            </div>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_SUPERVISOR_MANAGER')">
                            <div class="btn btn-primary element-center" ng-disabled="waitFor==true"
                                 ng-click="saveRolActivities('<c:url value="/supervisorManager/rolSupervision/doUpsert.json" />')">
                                <i class="glyphicon glyphicon-ok-circle"></i> &nbsp; Guardar
                            </div>
                        </sec:authorize>
                    </div>
                </div>
                <div class="row" ng-show="msgError">
                    <br/>

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

    <%@ include file="/WEB-INF/jsp/supervisorManager/rolSupervision/upsertRolActivity.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>
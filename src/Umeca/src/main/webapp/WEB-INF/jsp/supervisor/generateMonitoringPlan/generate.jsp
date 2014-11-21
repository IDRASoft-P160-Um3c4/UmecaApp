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
<%@ include file="/WEB-INF/jsp/shared/headUm.jsp" %>
<link href="${pageContext.request.contextPath}/assets/content/themes/umeca/fullcalendar.css" rel="stylesheet"
      type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/datepicker.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/content/themes/umeca/bootstrap-timepicker.css"/>

<script src="${pageContext.request.contextPath}/assets/scripts/umeca/typeahead-bs2.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery-ui-1.10.3.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/jquery.ui.touch-punch.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/fullcalendar.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/bootbox.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/umeca/date-time/moment.min.js"></script>

<script src="${pageContext.request.contextPath}/assets/scripts/commonActMonPlan.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/generateMonitoringPlan/generateMonPlanCtrl.js"></script>
<script src="${pageContext.request.contextPath}/assets/scripts/app/supervisor/shared/upsertActivityEventController.js"></script>
<script>
var lstIdsToObjects = function (lstIds) {
    var lstObjects = {};
    for (var i = 0; i < lstIds.length; i++) {
        var id = lstIds[i];
        lstObjects[id] = true;
    }
    return lstObjects;
};

var idToObject = function (id, lstCat) {
    for (var i = 0; i < lstCat.length; i++) {
        var cat = lstCat[i];
        if (cat.id == id) {
            return cat;
        }
    }
    return null;
};

var convertToEvents = function (lstActivitiesMonPlan, caseInfo, lstArrangements, lstActivities, lstGoals, lstSources) {
    var lstEvents = [];
    var today = new Date();
    today.setHours(0, 0, 0, 0);

    for (var i = 0; i < lstActivitiesMonPlan.length; i++) {
        var act = lstActivitiesMonPlan[i];
        var event = {
            title: "",
            doTitle: function (isModified) {
                this.title = (isModified === true ? "*" : "") + "Caso "
                        + this.infoActivity.caseInfo.caseId + "  (" + this.infoActivity.caseInfo.mpId + ") Imputado: "
                        + this.infoActivity.caseInfo.personName + " / " + this.infoActivity.activity.name + " / " + this.infoActivity.goal.name;
            },
            idActivity: act.activityId,
            start: window.stringToDate(act.start),
            end: window.stringToDate(act.end),
            allDay: false,
            isModified: false,
            infoActivity: {
                lstArrangements: lstIdsToObjects(act.lstArrangements),
                activity: idToObject(act.activityMonId, lstActivities),
                goal: idToObject(act.goalId, lstGoals),
                source: idToObject(act.sourceId, lstSources),
                activitySpec: act.activitySpec,
                goalSpec: act.goalSpec,
                sourceSpec: act.sourceSpec,
                caseInfo: caseInfo
            },
            groupEvt: act.group
        };

        event.className = window.colorActMonPlan(act.status, window.stringToDate(act.end), today);

        event.doTitle(false);
        lstEvents.push(event);
    }
    return lstEvents;
}

jQuery(function ($) {
    var lstActivitiesMonPlan = ${lstActivitiesMonPlan};
    var lstArrangements = ${lstArrangements};
    var lstActivities = ${lstActivities};
    var lstGoals = ${lstGoals};
    var lstSources = ${lstSources};

    var caseInfo = {caseId: "${caseId}", mpId: "${mpId}", personName: "${personName}",
        monStatus: "${monStatus}", monitoringPlanId: "${monitoringPlanId}", isInAuthorizeReady: ${isInAuthorizeReady}};
    lstEventsAct = convertToEvents(lstActivitiesMonPlan, caseInfo, lstArrangements, lstActivities, lstGoals, lstSources);

    var date = new Date();
    $('#id-date-picker-start,#id-date-picker-end').datepicker({autoclose: true, startDate: new Date(date.getFullYear(), date.getMonth(), date.getDate() - 1)}).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    $('#id-timepicker-start, #id-timepicker-end').timepicker({
        minuteStep: 15,
        showSeconds: false,
        showMeridian: false
    }).next().on(ace.click_event, function () {
        $(this).prev().focus();
    });

    //Initilize angular scope and config

    var scope = angular.element($("#UpsertActivityEventDlgId")).scope();
    var scopeMon = angular.element($("#GenerateMonPlanControllerId")).scope();

    scope.config({startDateId: "#id-date-picker-start", endDateId: "#id-date-picker-end",
                startTimeId: "#id-timepicker-start", endTimeId: "#id-timepicker-end", caseInfo: caseInfo},
            lstArrangements, lstActivities, lstGoals, lstSources);


    /* initialize the external events
     -----------------------------------------------------------------*/
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
        events: lstEventsAct,
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
        eventResize: function (event, dayDelta, minuteDelta, allDay, revertFunc) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            if (event.start < today) {
                revertFunc();
                scope.showMsg({title: "Plan de seguimiento", msg: 'No es posible modificar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                return;
            }
            event.doTitle(true);
            event.isModified = true;
            event.className = (event.infoActivity.caseInfo.isInAuthorizeReady ?
                    (event.className == 'label-pre-new' ? 'label-pre-new' : 'label-pre-update') : 'label-info');
        },
        eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            if (event.start < today) {
                revertFunc();
                scope.showMsg({title: "Plan de seguimiento", msg: 'No es posible modificar una actividad a una fecha anterior a la fecha actual.', type: "danger"});
                return;
            }
            event.doTitle(true);
            event.isModified = true;
            event.className = (event.infoActivity.caseInfo.isInAuthorizeReady ?
                    (event.className == 'label-pre-new' ? 'label-pre-new' : 'label-pre-update') : 'label-info');
        },
        select: function (start, end, allDay) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            if (start < today) {
                scope.showMsg({title: "Plan de seguimiento", msg: 'No es posible agregar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                return;
            }

            scope.showDlg({title: 'Agregar actividad', start: start, end: end, isNew: true})
                    .then(function (result) {
                        for (i = 0; i < result.activities.length; i++) {
                            calendar.fullCalendar('renderEvent', result.activities[i], true);
                        }
                    });
            calendar.fullCalendar('unselect');
        },
        eventClick: function (event, jsEvent, view) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            var isReadOnly = false;
            if (event.start < today) {
                isReadOnly = true;
                //scope.showMsg({title:"Plan de seguimiento", msg:'No es posible modificar una actividad con fecha anterior a la fecha actual.', type: "danger"});
                //return;
            }

            scope.showDlg({title: 'Modificar o eliminar actividad', start: event.start, end: event.end, isNew: false, event: event, isReadOnly: isReadOnly})
                    .then(function (result) {
                        switch (result.option) {
                            case 'REMOVE':
                                calendar.fullCalendar('removeEvents', function (ev) {
                                    return (ev._id == event._id);
                                });
                                scopeMon.addActivityToDelete(event.idActivity);
                                break;
                            case 'PRE_REMOVE':
                                if (event.idActivity === -1) {
                                    calendar.fullCalendar('removeEvents', function (ev) {
                                        return (ev._id == event._id);
                                    });
                                } else {
                                    event.className = 'label-pre-delete';
                                    calendar.fullCalendar('updateEvent', event);
                                    scopeMon.addActivityToDelete(event.idActivity);
                                }
                                break;
                            case 'REMOVE_GROUP':
                                calendar.fullCalendar('removeEvents', function (ev) {
                                    if (ev.groupEvt === event.groupEvt) {
                                        scopeMon.addActivityToDelete(ev.idActivity);
                                        return true;
                                    }
                                });
                                break;
                            case 'PRE_REMOVE_GROUP':
                                calendar.fullCalendar('removeEvents', function (ev) {
                                    if (ev.groupEvt === event.groupEvt) {
                                        if (ev.idActivity === -1) {
                                            return true;
                                        } else {
                                            ev.className = 'label-pre-delete';
                                            calendar.fullCalendar('updateEvent', ev);
                                            scopeMon.addActivityToDelete(ev.idActivity);
                                        }
                                    }
                                });
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

    scopeMon.m = { calendar: calendar};

});
</script>

<title>Plan de seguimiento</title>
</head>
<body scroll="no" ng-app="ptlUmc">
<%@ include file="/WEB-INF/jsp/shared/menu.jsp" %>

<div class="container body-content" ng-controller="generateMonPlanController" id="GenerateMonPlanControllerId" ng-cloak>
    <div class="page-content">
        <div class="page-header">
            <h1 class="element-center">
                <i class="glyphicon glyphicon-calendar"></i>&nbsp;&nbsp;Plan de seguimiento (Calendario)
            </h1>
        </div>
        <div class="page-header" ng-init="idTec = ${idTec == null? 0: idTec};">
            <h1 class="element-center" ng-init="caseId='${caseId}'; mpId='${mpId}'; personName='${personName}';">
                <%--Id. del caso: {{caseId}}, &nbsp;&nbsp; --%> Carpeta judicial: {{mpId}}, &nbsp;&nbsp; Imputado:
                {{personName}}
            </h1>
        </div>
        <div class="row" ng-show="idTec != 0">
            <div class="col-xs-12 text-info element-right">
                <a href="<c:url  value='/reviewer/technicalReview/generateFile.html?id=${caseId}'/>">
                    <i class="icon-download-alt icon-animated-wrench bigger-120" id="downOT"></i>&nbsp;
                    <label for="downOT">Descargar opini&oacute;n t&eacutecnica.</label>
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="space"></div>
                    <div class="col-xs-3 col-xs-offset-3 element-center">
                        <div class="btn btn-success element-center" ng-disabled="waitFor==true"
                             ng-click="returnToCases('<c:url value="/supervisor/generateMonitoringPlan/index.html" />')">
                            <i class="glyphicon glyphicon-chevron-left"></i> &nbsp; Regresar
                        </div>
                    </div>
                    <div class="col-xs-3 element-center">
                        <div class="btn btn-primary element-center" ng-disabled="waitFor==true"
                             ng-click="saveActivities('${caseId}', '${monitoringPlanId}', '<c:url value="/supervisor/generateMonitoringPlan/doUpsert.json" />')">
                            <i class="glyphicon glyphicon-ok-circle"></i> &nbsp; Guardar
                        </div>
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

    <%@ include file="/WEB-INF/jsp/supervisor/shared/upsertActivity.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/sharedSvc.jsp" %>
    <%@ include file="/WEB-INF/jsp/shared/footer.jsp" %>
</div>

</body>
</html>
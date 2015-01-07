package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;

import java.util.Calendar;

public class ActivityMonitoringPlanResponse {
    private Long activityMonId;
    private String eventId;
    private Long monitoringPlanId;
    private Long caseId;
    private String mpId;
    private String end;
    private String start;
    private Calendar endCalendar;
    private Calendar startCalendar;
    private Long activityId;
    private Long goalId;
    private String status;
    private String personName;
    private Boolean isSuspended;

    public ActivityMonitoringPlanResponse(Long activityMonId, Long monitoringPlanId, Long caseId, String mpId, Calendar endCalendar, Calendar startCalendar,
                                          Long activityId, Long goalId, String status, String name, String lastP, String lastM){
        this.activityMonId = activityMonId;
        this.monitoringPlanId = monitoringPlanId;
        this.caseId = caseId;
        this.mpId = mpId;
        this.endCalendar = endCalendar;
        this.startCalendar = startCalendar;
        this.activityId = activityId;
        this.goalId = goalId;
        this.status = status;
        this.personName = name + " " + lastP + " " + lastM;
        this.isSuspended = false;
    }

    public Long getActivityMonId() {
        return activityMonId;
    }

    public void setActivityMonId(Long activityMonId) {
        this.activityMonId = activityMonId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getMonitoringPlanId() {
        return monitoringPlanId;
    }

    public void setMonitoringPlanId(Long monitoringPlanId) {
        this.monitoringPlanId = monitoringPlanId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getEnd() {
        if(end != null)
            return end;
        end = CalendarExt.calendarToString(endCalendar);
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        if(start != null)
            return start;
        start = CalendarExt.calendarToString(startCalendar);
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Calendar getEndCalendar() {
        return endCalendar;
    }

    public void setEndCalendar(Calendar endCalendar) {
        this.endCalendar = endCalendar;
    }

    public Calendar getStartCalendar() {
        return startCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        this.startCalendar = startCalendar;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Boolean getSuspended() {
        return isSuspended;
    }

    public void setSuspended(Boolean suspended) {
        isSuspended = suspended;
    }
}

package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class ActivityMonitoringPlanNotice {
    private Long activityMonId;
    private Long caseId;
    private String mpId;
    private String end;
    private String start;
    private String activityName;
    private String goalName;
    private String personName;

    public ActivityMonitoringPlanNotice(Long activityMonId, Long caseId, String mpId, Calendar endCalendar, Calendar startCalendar,
                                        String activityName, String goalName, String name, String lastP, String lastM){
        this.activityMonId = activityMonId;
        this.caseId = caseId;
        this.mpId = mpId;
        this.end = CalendarExt.calendarToFormatString(endCalendar, Constants.FORMAT_CALENDAR_I);
        this.start = CalendarExt.calendarToFormatString(startCalendar, Constants.FORMAT_CALENDAR_I);
        this.activityName = activityName;
        this.goalName = goalName;
        this.personName = name + " " + lastP + " " + lastM;
    }

    public Long getActivityMonId() {
        return activityMonId;
    }

    public void setActivityMonId(Long activityMonId) {
        this.activityMonId = activityMonId;
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
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}

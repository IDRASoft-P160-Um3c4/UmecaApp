package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import org.apache.commons.lang.time.StopWatch;

import java.util.Calendar;

public class ActivityMonitoringPlanInfo {

    private Long activityMonitoringPlanId;
    private Long monitoringPlanId;
    private Long caseId;
    private String mpId;
    private String status;
    private Calendar end;
    private Calendar start;
    private String supervisionActivityName;
    private String activityGoalName;
    private String aidSourceName;
    private String actStatus;
    private String personName;
    private String supUserDone;
    private String comments;
    private Calendar endDone;

    public ActivityMonitoringPlanInfo(Long activityMonitoringPlanId, Long monitoringPlanId, Long caseId, String mpId, String status, Calendar end,
                                      Calendar start, String supervisionActivityName,
                                      String activityGoalName, String aidSourceName, String aidSourceRelationship, String actStatus, String name, String lastNameP, String lastNameM,
                                      String supUserDone, String comments, Calendar endDone){

        this.activityMonitoringPlanId = activityMonitoringPlanId;
        this.monitoringPlanId = monitoringPlanId;
        this.caseId = caseId;
        this.mpId = mpId;
        this.status = status;
        this.end = end;
        this.start = start;
        this.supervisionActivityName = supervisionActivityName;
        this.activityGoalName = activityGoalName;
        this.aidSourceName = aidSourceRelationship + " / " + aidSourceName;
        this.actStatus = actStatus;
        this.personName = name + " " + lastNameP + " " + lastNameM;
        this.supUserDone = supUserDone;
        this.comments = comments;
        this.endDone = endDone;
    }

    public Long getActivityMonitoringPlanId() {
        return activityMonitoringPlanId;
    }

    public void setActivityMonitoringPlanId(Long activityMonitoringPlanId) {
        this.activityMonitoringPlanId = activityMonitoringPlanId;
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

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public String getSupervisionActivityName() {
        return supervisionActivityName;
    }

    public void setSupervisionActivityName(String supervisionActivityName) {
        this.supervisionActivityName = supervisionActivityName;
    }

    public String getActivityGoalName() {
        return activityGoalName;
    }

    public void setActivityGoalName(String activityGoalName) {
        this.activityGoalName = activityGoalName;
    }

    public String getAidSourceName() {
        return aidSourceName;
    }

    public void setAidSourceName(String aidSourceName) {
        this.aidSourceName = aidSourceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getStartDateTime() {
        return CalendarExt.calendarToDateString(start);
    }

    public String getEndDateTime() {
        return CalendarExt.calendarToDateString(end);
    }

    public String getSupUserDone() {
        return supUserDone;
    }

    public void setSupUserDone(String supUserDone) {
        this.supUserDone = supUserDone;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Calendar getEndDone() {
        return endDone;
    }

    public void setEndDone(Calendar endDone) {
        this.endDone = endDone;
    }
}
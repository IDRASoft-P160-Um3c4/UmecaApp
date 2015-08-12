package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;

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
    private String endSt;
    private String startSt;
    private String assignedArrangement;
    private Long actMonPlanToReplaceId;
    private Long activityGoalId;
    private Calendar generationTime;
    private Calendar authorizationTime;
    private Calendar posAuthorizationChangeTime;
    private Long channelingId;
    private String channelingName;
    private String channelingType;
    private boolean isSuspended;
    private Integer channelingAssistance;

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
        this.startSt = CalendarExt.calendarToFormatString(start, Constants.FORMAT_CALENDAR_I);
        this.endSt = CalendarExt.calendarToFormatString(end, Constants.FORMAT_CALENDAR_I);
    }

    public ActivityMonitoringPlanInfo(Long activityMonitoringPlanId, Long monitoringPlanId, Long caseId, String mpId, String status, Calendar end,
                                      Calendar start, String supervisionActivityName,
                                      String activityGoalName, Long activityGoalId, String aidSourceName, String aidSourceRelationship, String actStatus, String name, String lastNameP, String lastNameM,
                                      String supUserDone, String comments, Calendar endDone, Calendar generationTime, Calendar authorizationTime, Calendar posAuthorizationChangeTime,
                                      Long channelingId, String channelingName, String channelingType, Integer channelingAssistance){

        this(activityMonitoringPlanId, monitoringPlanId, caseId, mpId, status, end, start, supervisionActivityName, activityGoalName, aidSourceName, aidSourceRelationship,
                actStatus, name, lastNameP, lastNameM, supUserDone, comments, endDone);

        this.generationTime = generationTime;
        this.authorizationTime = authorizationTime;
        this.posAuthorizationChangeTime = posAuthorizationChangeTime;
        this.channelingAssistance = channelingAssistance;
        this.isSuspended = MonitoringPlanView.calculateIsMonPlanSuspended(generationTime, authorizationTime, posAuthorizationChangeTime);
        this.activityGoalId = activityGoalId;
        this.channelingId = channelingId;
        this.channelingName = channelingName;
        this.channelingType = channelingType;
    }

        public ActivityMonitoringPlanInfo(Long activityMonitoringPlanId, Long monitoringPlanId, Long caseId, String mpId, String status, Calendar end,
                                      Calendar start, String  assignedArrangement, String supervisionActivityName,
                                      String activityGoalName, String aidSourceName, String aidSourceRelationship, String actStatus, String name, String lastNameP, String lastNameM,
                                      String supUserDone, String comments, Calendar endDone, Long actMonPlanToReplaceId){

        this(activityMonitoringPlanId, monitoringPlanId, caseId, mpId, status, end, start, supervisionActivityName, activityGoalName, aidSourceName, aidSourceRelationship,
                actStatus, name, lastNameP, lastNameM, supUserDone, comments, endDone);
        this.assignedArrangement = assignedArrangement;
        this.actMonPlanToReplaceId = actMonPlanToReplaceId;
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

    public String getEndSt() {
        return endSt;
    }

    public void setEndSt(String endSt) {
        this.endSt = endSt;
    }

    public String getStartSt() {
        return startSt;
    }

    public void setStartSt(String startSt) {
        this.startSt = startSt;
    }

    public String getAssignedArrangement() {
        return assignedArrangement;
    }

    public void setAssignedArrangement(String assignedArrangement) {
        this.assignedArrangement = assignedArrangement;
    }

    public Long getActMonPlanToReplaceId() {
        return actMonPlanToReplaceId;
    }

    public void setActMonPlanToReplaceId(Long actMonPlanToReplaceId) {
        this.actMonPlanToReplaceId = actMonPlanToReplaceId;
    }

    public boolean getSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public String stringToLogCase(String aux){
        String result = "";
        if(supervisionActivityName!=null)
            result+="<strong>Actividad de supervisi&oacute;n: </strong>"+supervisionActivityName+"<br/>";
        if(activityGoalName!=null)
            result+="<strong>Objetivo de la actividad: </strong>"+activityGoalName+"<br/>";
        if(aidSourceName!=null)
            result+="<strong>Fuente: </strong>"+aidSourceName+"<br/>";
        if(startSt!=null){
            result+="<strong>Duraci&oacute;n: </strong>"+startSt+" - ";
            result+=endSt != null ? endSt+"<br/>": "Sin registrar </br>";
        }
        if(actStatus!=null)
            result+="<strong>Status: </strong>"+actStatus+"<br/>";
        if(aux!=null)
            result+="<strong>Obligaciones procesales: </strong>"+aux;
        if(comments!=null)
            result+="<strong>Comentarios: </strong>"+comments+"<br/>";
        return result;
     }

    public Long getActivityGoalId() {
        return activityGoalId;
    }

    public void setActivityGoalId(Long activityGoalId) {
        this.activityGoalId = activityGoalId;
    }

    public Long getChannelingId() {
        return channelingId;
    }

    public void setChannelingId(Long channelingId) {
        this.channelingId = channelingId;
    }

    public String getChannelingName() {
        return channelingName;
    }

    public void setChannelingName(String channelingName) {
        this.channelingName = channelingName;
    }

    public String getChannelingType() {
        return channelingType;
    }

    public void setChannelingType(String channelingType) {
        this.channelingType = channelingType;
    }

    public Integer getChannelingAssistance() {
        return channelingAssistance;
    }

    public void setChannelingAssistance(Integer channelingAssistance) {
        this.channelingAssistance = channelingAssistance;
    }
}

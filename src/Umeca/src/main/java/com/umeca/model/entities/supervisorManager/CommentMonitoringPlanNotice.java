package com.umeca.model.entities.supervisorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;
import com.umeca.model.shared.MonitoringConstants;

import java.util.Calendar;

public class CommentMonitoringPlanNotice {

    private Long id;
    private String type;
    private String action;
    private String senderUser;
    private String receiveUser;
    private String timestamp;
    private String comments;
    private Long caseId;
    private String mpId;
    private String personName;
    private String typeName;

    public CommentMonitoringPlanNotice(Long id, String type, String action, String senderUser, String receiveUser, Calendar timestamp, String comments,
                                       Long caseId, String mpId, String name, String lastP, String lastM) {
        this.id = id;
        this.type = type;
        this.typeName = convertToTypeName(type);
        this.action = action;
        this.senderUser = senderUser;
        this.receiveUser = receiveUser;
        this.timestamp = CalendarExt.calendarToFormatString(timestamp, Constants.FORMAT_CALENDAR_I);
        this.comments = comments;
        this.caseId = caseId;
        this.mpId = mpId;
        this.personName = name + " " + lastP + " " + lastM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(String senderUser) {
        this.senderUser = senderUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String convertToTypeName(String type) {
        switch (type){
            case MonitoringConstants.TYPE_COMMENT_AUTHORIZED:
                return "PLAN DE SEGUIMIENTO";
            case MonitoringConstants.TYPE_COMMENT_LOG_ACCOMPLISHMENT:
                return "REPORTE DE INCUMPLIMIENTO";
            case MonitoringConstants.TYPE_COMMENT_MONITORING_PLAN_END:
                return "TERMINACIÓN DEL PLAN DE SEGUIMIENTO";
            case MonitoringConstants.TYPE_COMMENT_CASE_END:
                return "TERMINACIÓN DEL CASO";
            default:
                return "NA";
        }
    }
}

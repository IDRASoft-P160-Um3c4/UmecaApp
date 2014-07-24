package com.umeca.model.entities.supervisorManager;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.*;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "log_comment")
public class LogComment {

    @Id
    @GeneratedValue
    @Column(name = "id_log_comment", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_monitoring_plan", nullable = true)
    private MonitoringPlan monitoringPlan;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case", nullable = false)
    private Case caseDetention;

    @Column(name = "type", length = 100, nullable = false)
    private String type;

    @Column(name = "action", length = 100, nullable = false)
    private String action;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_sender_user", nullable = false)
    private User senderUser;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_receive_user", nullable = true)
    private User receiveUser;

    @Column(name = "timestamp", nullable = false)
    private Calendar timestamp;

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name = "comments", nullable = false)
    private String comments;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_set_obsolete_user", nullable = true)
    private User obsoleteUser;

    @Column(name = "timestamp_obsolete", nullable = true)
    private Calendar timestampObsolete;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

    public LogComment() {
        isObsolete = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MonitoringPlan getMonitoringPlan() {
        return monitoringPlan;
    }

    public void setMonitoringPlan(MonitoringPlan monitoringPlan) {
        this.monitoringPlan = monitoringPlan;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
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

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }

    public User getObsoleteUser() {
        return obsoleteUser;
    }

    public void setObsoleteUser(User obsoleteUser) {
        this.obsoleteUser = obsoleteUser;
    }

    public Calendar getTimestampObsolete() {
        return timestampObsolete;
    }

    public void setTimestampObsolete(Calendar timestampObsolete) {
        this.timestampObsolete = timestampObsolete;
    }
}

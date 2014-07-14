package com.umeca.model.entities.supervisorManager;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.MonitoringPlan;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "log_change_supervisor")
public class LogChangeSupervisor {

    @Id
    @GeneratedValue
    @Column(name = "id_log_change_supervisor", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_monitoring_plan", nullable = false)
    private MonitoringPlan monitoringPlan;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_supervisor_old", nullable = false)
    private User supervisorOld;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_supervisor_new", nullable = false)
    private User supervisorNew;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_supervisor_manager", nullable = false)
    private User supervisorManager;

    @Column(name = "timestamp", nullable = false)
    private Calendar timestamp;

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name = "comments", nullable = false)
    private String comments;

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

    public User getSupervisorOld() {
        return supervisorOld;
    }

    public void setSupervisorOld(User supervisorOld) {
        this.supervisorOld = supervisorOld;
    }

    public User getSupervisorNew() {
        return supervisorNew;
    }

    public void setSupervisorNew(User supervisorNew) {
        this.supervisorNew = supervisorNew;
    }

    public User getSupervisorManager() {
        return supervisorManager;
    }

    public void setSupervisorManager(User supervisorManager) {
        this.supervisorManager = supervisorManager;
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
}

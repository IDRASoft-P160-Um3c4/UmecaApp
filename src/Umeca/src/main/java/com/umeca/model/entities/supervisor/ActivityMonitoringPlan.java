package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/13/14
 * Time: 4:34 PM
 */


@Entity
@Table(name = "activity_monitoring_plan")
public class ActivityMonitoringPlan {

    @Id
    @GeneratedValue
    @Column(name = "id_activity_monitoring_plan", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_monitoring_plan", nullable = false)
    private MonitoringPlan monitoringPlan;

    @Column(name = "start", nullable = false)
    private Calendar start;

    @Column(name= "search_start", nullable = false)
    private int searchStart;

    @Column(name = "end", nullable = false)
    private Calendar end;

    @Column(name= "search_end", nullable = false)
    private int searchEnd;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="activity_monitoring_plan_arrangement", joinColumns ={@JoinColumn(name = "id_activity_monitoring_plan")}, inverseJoinColumns = {@JoinColumn(name = "id_assigned_arrangement")})
    private List<AssignedArrangement> lstAssignedArrangement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_supervision_activity", nullable = false)
    private SupervisionActivity supervisionActivity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_activity_goal", nullable = false)
    private ActivityGoal activityGoal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_aid_source", nullable = false)
    private AidSource aidSource;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @Lob @Basic(fetch=FetchType.LAZY)
    @Column(name = "assigned_arrangements", nullable = false)
    private String assignedArrangements;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_create", nullable = false)
    private User supervisorCreate;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_modify", nullable = true)
    private User supervisorModify;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_done", nullable = true)
    private User supervisorDone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public MonitoringPlan getMonitoringPlan() {
        return monitoringPlan;
    }

    public void setMonitoringPlan(MonitoringPlan monitoringPlan) {
        this.monitoringPlan = monitoringPlan;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public List<AssignedArrangement> getLstAssignedArrangement() {
        return lstAssignedArrangement;
    }

    public void setLstAssignedArrangement(List<AssignedArrangement> lstAssignedArrangement) {
        this.lstAssignedArrangement = lstAssignedArrangement;
    }

    public SupervisionActivity getSupervisionActivity() {
        return supervisionActivity;
    }

    public void setSupervisionActivity(SupervisionActivity supervisionActivity) {
        this.supervisionActivity = supervisionActivity;
    }

    public ActivityGoal getActivityGoal() {
        return activityGoal;
    }

    public void setActivityGoal(ActivityGoal activityGoal) {
        this.activityGoal = activityGoal;
    }

    public AidSource getAidSource() {
        return aidSource;
    }

    public void setAidSource(AidSource aidSource) {
        this.aidSource = aidSource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getSupervisorCreate() {
        return supervisorCreate;
    }

    public void setSupervisorCreate(User supervisorCreate) {
        this.supervisorCreate = supervisorCreate;
    }

    public User getSupervisorModify() {
        return supervisorModify;
    }

    public void setSupervisorModify(User supervisorModify) {
        this.supervisorModify = supervisorModify;
    }

    public User getSupervisorDone() {
        return supervisorDone;
    }

    public void setSupervisorDone(User supervisorDone) {
        this.supervisorDone = supervisorDone;
    }

    public String getAssignedArrangements() {
        return assignedArrangements;
    }

    public void setAssignedArrangements(String assignedArrangements) {
        this.assignedArrangements = assignedArrangements;
    }

    public int getSearchStart() {
        return searchStart;
    }

    public void setSearchStart(int searchStart) {
        this.searchStart = searchStart;
    }

    public int getSearchEnd() {
        return searchEnd;
    }

    public void setSearchEnd(int searchEnd) {
        this.searchEnd = searchEnd;
    }
}

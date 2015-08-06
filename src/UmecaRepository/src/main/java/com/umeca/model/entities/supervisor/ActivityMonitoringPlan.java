package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.shared.MonitoringConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "activity_monitoring_plan")
public class ActivityMonitoringPlan {

    @Id
    @GeneratedValue
    @Column(name = "id_activity_monitoring_plan", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monitoring_plan", nullable = false)
    private MonitoringPlan monitoringPlan;

    @Column(name = "start", nullable = false)
    private Calendar start;

    @Column(name = "search_start", nullable = false)
    private int searchStart;

    @Column(name = "end", nullable = false)
    private Calendar end;

    @Column(name = "search_end", nullable = false)
    private int searchEnd;

    @OneToMany(mappedBy = "activityMonitoringPlan", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<ActivityMonitoringPlanArrangement> lstAssignedArrangement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_supervision_activity", nullable = false)
    private SupervisionActivity supervisionActivity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activity_goal", nullable = false)
    private ActivityGoal activityGoal;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_framing_source", nullable = false)
    private FramingSelectedSourceRel framingSelectedSourceRel;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "assigned_arrangements", nullable = false)
    private String assignedArrangements;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "assigned_arrangements_ids", nullable = false)
    private String assignedArrangementsIds;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_create", nullable = false)
    private User supervisorCreate;

    @Column(name = "creation_time", nullable = false)
    private Calendar creationTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_modify", nullable = true)
    private User supervisorModify;

    @Column(name = "modify_time", nullable = true)
    private Calendar modifyTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_done", nullable = true)
    private User supervisorDone;

    @Column(name = "done_time", nullable = true)
    private Calendar doneTime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "comments", nullable = true)
    private String comments;

    @Column(name = "group_evt", length = 50, nullable = false)
    private String group;

    @ManyToOne(fetch = FetchType.LAZY) //A quien va a reemplazar antes de autorizar
    @JoinColumn(name = "act_mon_plan_to_replace_id", nullable = true)
    private ActivityMonitoringPlan actMonPlanToReplace;

    @ManyToOne(fetch = FetchType.LAZY)//A quien reemplazó después de autorizar, para mantener una trazabilidad
    @JoinColumn(name = "act_mon_plan_replaced_id", nullable = true)
    private ActivityMonitoringPlan actMonPlanReplaced;

    @Column(name = "is_pre_authorize_mode", nullable = true)
    private Boolean isPreAuthorizeMode;

    @Column(name = "is_replaced", nullable = true)
    private Boolean isReplaced;

    @Column(name = "activity_spec")
    private String activitySpec;

    @Column(name = "goal_spec")
    private String goalSpec;

    @Column(name = "source_spec")
    private String sourceSpec;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_channeling", nullable = true)
    private Channeling channeling;

    @Column(name = "channeling_assistance", nullable = true)
    private Integer channelingAssistance;

    @Column(name="isJustified", nullable = true)
    private Boolean isJustified;

    @OneToOne(fetch = FetchType.LAZY)//Relación a la actividad para reagendar la cita
    @JoinColumn(name = "reschedule_appointment_id", nullable = true)
    private ActivityMonitoringPlan rescheduleAppointment;

    @Column(name="commentsJustification", length = 1000, nullable = true)
    private String commentsJustification;


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

    public List<ActivityMonitoringPlanArrangement> getLstAssignedArrangement() {
        return lstAssignedArrangement;
    }

    public void setLstAssignedArrangement(List<ActivityMonitoringPlanArrangement> lstAssignedArrangement) {
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

    public FramingSelectedSourceRel getFramingSelectedSourceRel() {
        return framingSelectedSourceRel;
    }

    public void setFramingSelectedSourceRel(FramingSelectedSourceRel framingSelectedSourceRel) {
        this.framingSelectedSourceRel = framingSelectedSourceRel;
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

    public String getAssignedArrangementsIds() {
        return assignedArrangementsIds;
    }

    public void setAssignedArrangementsIds(String assignedArrangementsIds) {
        this.assignedArrangementsIds = assignedArrangementsIds;
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

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public Calendar getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Calendar modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Calendar getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Calendar doneTime) {
        this.doneTime = doneTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ActivityMonitoringPlan getActMonPlanToReplace() {
        return actMonPlanToReplace;
    }

    public void setActMonPlanToReplace(ActivityMonitoringPlan actMonPlanToReplace) {
        this.actMonPlanToReplace = actMonPlanToReplace;
    }

    public Boolean isPreAuthorizeMode() {
        return isPreAuthorizeMode;
    }

    public void setPreAuthorizeMode(Boolean preAuthorizeMode) {
        isPreAuthorizeMode = preAuthorizeMode;
    }

    public Boolean getReplaced() {
        return isReplaced;
    }

    public void setReplaced(Boolean replaced) {
        isReplaced = replaced;
    }

    public ActivityMonitoringPlan getActMonPlanReplaced() {
        return actMonPlanReplaced;
    }

    public void setActMonPlanReplaced(ActivityMonitoringPlan actMonPlanReplaced) {
        this.actMonPlanReplaced = actMonPlanReplaced;
    }

    public String getActivitySpec() {
        return activitySpec;
    }

    public void setActivitySpec(String activitySpec) {
        this.activitySpec = activitySpec;
    }

    public String getGoalSpec() {
        return goalSpec;
    }

    public void setGoalSpec(String goalSpec) {
        this.goalSpec = goalSpec;
    }

    public String getSourceSpec() {
        return sourceSpec;
    }

    public void setSourceSpec(String sourceSpec) {
        this.sourceSpec = sourceSpec;
    }

    public Channeling getChanneling() {
        return channeling;
    }

    public void setChanneling(Channeling channeling) {
        this.channeling = channeling;
    }

    public Boolean getIsReplaced() {
        return isReplaced;
    }

    public void setIsReplaced(Boolean isReplaced) {
        this.isReplaced = isReplaced;
    }

    public Boolean getIsPreAuthorizeMode() {
        return isPreAuthorizeMode;
    }

    public void setIsPreAuthorizeMode(Boolean isPreAuthorizeMode) {
        this.isPreAuthorizeMode = isPreAuthorizeMode;
    }

    public Integer getChannelingAssistance() {
        return channelingAssistance;
    }

    public void setChannelingAssistance(Integer channelingAssistance) {
        this.channelingAssistance = channelingAssistance;
    }

    public Boolean getIsJustified() {
        return isJustified;
    }

    public void setIsJustified(Boolean isJustified) {
        this.isJustified = isJustified;
    }

    public String getCommentsJustification() {
        return commentsJustification;
    }

    public void setCommentsJustification(String commentsJustification) {
        this.commentsJustification = commentsJustification;
    }

    public ActivityMonitoringPlan getRescheduleAppointment() {
        return rescheduleAppointment;
    }

    public void setRescheduleAppointment(ActivityMonitoringPlan rescheduleAppointment) {
        this.rescheduleAppointment = rescheduleAppointment;
    }

    public ActivityMonitoringPlan rescheduleCopy() {
        ActivityMonitoringPlan model = new ActivityMonitoringPlan();
        model.setCaseDetention(caseDetention);
        model.setMonitoringPlan(monitoringPlan);
        List<ActivityMonitoringPlanArrangement> lstAssArr = new ArrayList<ActivityMonitoringPlanArrangement>();
        for(ActivityMonitoringPlanArrangement actMonArr : lstAssignedArrangement){
            actMonArr.setActivityMonitoringPlan(model);
            actMonArr.setStatus(MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED);
            lstAssArr.add(actMonArr);
        }
        model.setLstAssignedArrangement(lstAssArr);
        model.setSupervisionActivity(supervisionActivity);
        model.setActivityGoal(activityGoal);
        model.setFramingSelectedSourceRel(framingSelectedSourceRel);
        model.setAssignedArrangements(assignedArrangements);
        model.setAssignedArrangementsIds(assignedArrangementsIds);
        model.setGroup(group);
        model.setActivitySpec(activitySpec);
        model.setGoalSpec(goalSpec);
        model.setSourceSpec(sourceSpec);
        model.setChanneling(channeling);

        return model;

    }
}

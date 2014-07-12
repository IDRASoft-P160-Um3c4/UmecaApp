package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Arrangement;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;

public class ActivityMonitoringPlanJson {

    private Long id;
    private Long caseId;
    private Long monitoringPlanId;
    private Calendar start;
    private Calendar end;
    private String assignedArrangements;
    private Long supervisionActivityId;
    private Long activityGoalId;
    private Long aidSourceId;
    private String status;
    private Long supervisorCreateId;
    private Long supervisorModifyId;
    private Long supervisorDoneId;

    private ActivityMonitoringPlanJson(Long id, Long caseId, Long monitoringPlanId, Calendar start, Calendar end, String assignedArrangements,
                                      Long supervisionActivityId, Long activityGoalId, Long aidSourceId ,String status, Long supervisorCreateId,
                                      Long supervisorModifyId, Long supervisorDoneId){
        this.id = id;
        this.caseId = caseId;
        this.monitoringPlanId = monitoringPlanId;
        this.start = start;
        this.end = end;
        this.assignedArrangements = assignedArrangements;
        this.supervisionActivityId = supervisionActivityId;
        this.activityGoalId = activityGoalId;
        this.aidSourceId = aidSourceId;
        this.status = status;
        this.supervisorCreateId = supervisorCreateId;
        this.supervisorModifyId = supervisorModifyId;
        this.supervisorDoneId = supervisorDoneId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getMonitoringPlanId() {
        return monitoringPlanId;
    }

    public void setMonitoringPlanId(Long monitoringPlanId) {
        this.monitoringPlanId = monitoringPlanId;
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

    public String getAssignedArrangements() {
        return assignedArrangements;
    }

    public void setAssignedArrangements(String assignedArrangements) {
        this.assignedArrangements = assignedArrangements;
    }

    public Long getSupervisionActivityId() {
        return supervisionActivityId;
    }

    public void setSupervisionActivityId(Long supervisionActivityId) {
        this.supervisionActivityId = supervisionActivityId;
    }

    public Long getActivityGoalId() {
        return activityGoalId;
    }

    public void setActivityGoalId(Long activityGoalId) {
        this.activityGoalId = activityGoalId;
    }

    public Long getAidSourceId() {
        return aidSourceId;
    }

    public void setAidSourceId(Long aidSourceId) {
        this.aidSourceId = aidSourceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSupervisorCreateId() {
        return supervisorCreateId;
    }

    public void setSupervisorCreateId(Long supervisorCreateId) {
        this.supervisorCreateId = supervisorCreateId;
    }

    public Long getSupervisorModifyId() {
        return supervisorModifyId;
    }

    public void setSupervisorModifyId(Long supervisorModifyId) {
        this.supervisorModifyId = supervisorModifyId;
    }

    public Long getSupervisorDoneId() {
        return supervisorDoneId;
    }

    public void setSupervisorDoneId(Long supervisorDoneId) {
        this.supervisorDoneId = supervisorDoneId;
    }

    public static ActivityMonitoringPlanJson convertToJson(ActivityMonitoringPlan model){
        Long id = model.getId();
        Long caseId = model.getId();
        Long monitoringPlanId = model.getId();
        Calendar start = model.getStart();
        Calendar end = model.getEnd();
        String assignedArrangements = model.getAssignedArrangements();
        Long supervisionActivityId = model.getSupervisionActivity().getId();
        Long activityGoalId = model.getActivityGoal().getId();
        Long framingSourceId = model.getFramingSelectedSourceRel().getId();
        String status = model.getStatus();
        Long supervisorCreateId = model.getSupervisorCreate().getId();
        Long supervisorModify = (model.getSupervisorModify() == null ? null : model.getSupervisorModify().getId());
        Long supervisorDone = (model.getSupervisorDone() == null ? null : model.getSupervisorDone().getId());


        return new ActivityMonitoringPlanJson(id, caseId, monitoringPlanId, start, end, assignedArrangements,
                supervisionActivityId, activityGoalId, framingSourceId, status,
                supervisorCreateId, supervisorModify, supervisorDone);
    }
}

package com.umeca.model.entities.supervisor;

/**
 * Project: Umeca
 * User: Israel
 * Date: 7/9/14
 * Time: 4:11 PM
 */
public class ActivityMonitoringPlanArrangementLog {
    private Long id;
    private Long actMonPlanId;
    private Long assignedArrangementId;
    private Integer status;

    public ActivityMonitoringPlanArrangementLog(Long id, Long actMonPlanId, Long assignedArrangementId, Integer status){
        this.id = id;
        this.actMonPlanId = actMonPlanId;
        this.assignedArrangementId = assignedArrangementId;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActMonPlanId() {
        return actMonPlanId;
    }

    public void setActMonPlanId(Long actMonPlanId) {
        this.actMonPlanId = actMonPlanId;
    }

    public Long getAssignedArrangementId() {
        return assignedArrangementId;
    }

    public void setAssignedArrangementId(Long assignedArrangementId) {
        this.assignedArrangementId = assignedArrangementId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

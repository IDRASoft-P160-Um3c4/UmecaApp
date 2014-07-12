package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name = "activity_monitoring_plan_arrangement")
public class ActivityMonitoringPlanArrangement {
    @Id
    @GeneratedValue
    @Column(name = "id_activity_monitoring_plan_arrangement", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activity_monitoring_plan", nullable = false)
    private ActivityMonitoringPlan activityMonitoringPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_assigned_arrangement", nullable = false)
    private AssignedArrangement assignedArrangement;

    @Column(name = "status", nullable = false)
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActivityMonitoringPlan getActivityMonitoringPlan() {
        return activityMonitoringPlan;
    }

    public void setActivityMonitoringPlan(ActivityMonitoringPlan activityMonitoringPlan) {
        this.activityMonitoringPlan = activityMonitoringPlan;
    }

    public AssignedArrangement getAssignedArrangement() {
        return assignedArrangement;
    }

    public void setAssignedArrangement(AssignedArrangement assignedArrangement) {
        this.assignedArrangement = assignedArrangement;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

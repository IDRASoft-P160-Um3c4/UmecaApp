package com.umeca.model.entities.supervisor;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/16/14
 * Time: 1:56 PM
 */
public class ActivityMonitoringPlanEvent {
    private Long activityMonitoringPlanId;
    private String eventId;

    public ActivityMonitoringPlanEvent(){

    }

    public ActivityMonitoringPlanEvent(Long activityMonitoringPlanId, String eventId){
        this.activityMonitoringPlanId = activityMonitoringPlanId;
        this.eventId = eventId;

    }

    public Long getActivityMonitoringPlanId() {
        return activityMonitoringPlanId;
    }

    public void setActivityMonitoringPlanId(Long activityMonitoringPlanId) {
        this.activityMonitoringPlanId = activityMonitoringPlanId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}


package com.umeca.model.entities.director.agenda;

public class ActivityAgendaEvent {
    private Long activityId;
    private String eventId;

    public ActivityAgendaEvent(){
    }

    public ActivityAgendaEvent(Long activityId, String eventId){
        this.activityId = activityId;
        this.eventId = eventId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

}


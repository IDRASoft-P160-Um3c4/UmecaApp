package com.umeca.model.entities.director.agenda;

public class ActivityAgendaEvent {
    private Long activityAgendaId;
    private String eventId;

    public ActivityAgendaEvent(){
    }

    public ActivityAgendaEvent(Long activityAgendaId, String eventId){
        this.activityAgendaId = activityAgendaId;
        this.eventId = eventId;
    }

    public Long getActivityAgendaId() {
        return activityAgendaId;
    }

    public void setActivityAgendaId(Long activityAgendaId) {
        this.activityAgendaId = activityAgendaId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

}


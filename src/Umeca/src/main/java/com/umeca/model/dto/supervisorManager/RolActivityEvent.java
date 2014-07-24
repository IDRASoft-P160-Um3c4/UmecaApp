package com.umeca.model.dto.supervisorManager;

public class RolActivityEvent {
    private Long rolActivityId;
    private String eventId;

    public RolActivityEvent(){

    }

    public RolActivityEvent(Long rolActivityId, String eventId){
        this.rolActivityId = rolActivityId;
        this.eventId = eventId;

    }

    public Long getRolActivityId() {
        return rolActivityId;
    }

    public void setRolActivityId(Long rolActivityId) {
        this.rolActivityId = rolActivityId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}


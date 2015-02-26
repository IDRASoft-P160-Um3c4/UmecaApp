package com.umeca.model.entities.director.agenda;

import com.umeca.model.entities.supervisor.ActivityMonitoringPlan;

import java.util.Calendar;

public class ActivityAgendaJson {

    private Long id;
    private Calendar start;
    private Calendar end;
    private Calendar updateDate;
    private Calendar doneDate;
    private Long priorityId;
    private String status;
    private String place;
    private String description;
    private String comments;
    private Boolean isDone;

    private ActivityAgendaJson(Long id, Calendar start, Calendar end, Long priorityId, String status,
                               String place, String description, Calendar updateDate, Calendar doneDate,
                               String comments, Boolean isDone){
        this.id = id;
        this.start = start;
        this.end = end;
        this.priorityId = priorityId;
        this.status = status;
        this.place = place;
        this.description = description;
        this.updateDate = updateDate;
        this.doneDate = doneDate;
        this.comments = comments;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ActivityAgendaJson convertToJson(ActivityAgenda model){
        Long id = model.getId();
        Calendar start = model.getStart();
        Calendar end = model.getEnd();
        Calendar updateDate = model.getUpdateTime();
        Long priorityId = model.getPriority().getId();
        String status = model.getStatus();
        String place = model.getPlace();
        String description = model.getDescription();
        Calendar doneDate = model.getDoneTime();
        String comments = model.getComments();
        Boolean isDone = model.isDone();

        return new ActivityAgendaJson(id, start, end, priorityId,
                status, place, description, updateDate, doneDate, comments, isDone);
    }
}

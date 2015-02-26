package com.umeca.model.entities.director.agenda;

import com.umeca.infrastructure.extensions.CalendarExt;
import java.util.Calendar;

public class ActivityAgendaResponse {
    private Long activityId;
    private String eventId;
    private String end;
    private String start;
    private Calendar endCalendar;
    private Calendar startCalendar;
    private Long priorityId;
    private String status;
    private String place;
    private String description;
    private String comments;

    public ActivityAgendaResponse(Long activityId, Calendar endCalendar, Calendar startCalendar,
                                  Long priorityId, String status, String place, String description, String comments){
        this.activityId = activityId;
        this.endCalendar = endCalendar;
        this.startCalendar = startCalendar;
        this.priorityId = priorityId;
        this.status = status;
        this.place = place;
        this.description = description;
        this.comments = comments;
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

    public String getEnd() {
        if(end != null)
            return end;
        end = CalendarExt.calendarToString(endCalendar);
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        if(start != null)
            return start;
        start = CalendarExt.calendarToString(startCalendar);
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Calendar getEndCalendar() {
        return endCalendar;
    }

    public void setEndCalendar(Calendar endCalendar) {
        this.endCalendar = endCalendar;
    }

    public Calendar getStartCalendar() {
        return startCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        this.startCalendar = startCalendar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

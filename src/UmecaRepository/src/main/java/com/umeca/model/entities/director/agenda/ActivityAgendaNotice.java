package com.umeca.model.entities.director.agenda;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class ActivityAgendaNotice {
    private Long activityId;
    private String end;
    private String start;
    private String place;
    private String description;
    private String status;
    private String priority;
    private String color;

    public ActivityAgendaNotice(Long activityId, Calendar endCalendar, Calendar startCalendar, String place, String description, String status, String priority, String color) {
        this.activityId = activityId;
        this.color = color;
        this.end = CalendarExt.calendarToFormatString(endCalendar, Constants.FORMAT_CALENDAR_I);
        this.start = CalendarExt.calendarToFormatString(startCalendar, Constants.FORMAT_CALENDAR_I);
        this.place = place;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.color = color;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
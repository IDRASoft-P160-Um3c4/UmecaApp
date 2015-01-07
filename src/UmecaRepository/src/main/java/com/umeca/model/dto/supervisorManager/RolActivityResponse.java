package com.umeca.model.dto.supervisorManager;

import com.umeca.infrastructure.extensions.CalendarExt;

import java.util.Calendar;

public class RolActivityResponse {
    private Long rolActivityId;
    private String eventId;
    private String end;
    private String start;
    private Calendar endCalendar;
    private Calendar startCalendar;
    private Long supervisorId;
    private String status;

    public RolActivityResponse(Long rolActivityId, Calendar endCalendar, Calendar startCalendar, Long supervisorId, String status){
        this.rolActivityId = rolActivityId;
        this.endCalendar = endCalendar;
        this.startCalendar = startCalendar;
        this.supervisorId = supervisorId;
        this.status = status;
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

    public void setEnd(String end) {
        this.end = end;
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

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnd() {
        if(end != null)
            return end;
        end = CalendarExt.calendarToString(endCalendar);
        return end;
    }

    public String getStart() {
        if(start != null)
            return start;
        start = CalendarExt.calendarToString(startCalendar);
        return start;
    }
}

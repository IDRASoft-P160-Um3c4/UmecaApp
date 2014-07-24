package com.umeca.model.dto.supervisorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangement;
import com.umeca.model.entities.supervisorManager.RolActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RolActivityDto {
    private Long rolActivityId;
    private String eventId;
    private Long supervisorId;
    private String end;
    private String start;
    private Calendar endCalendar;
    private Calendar startCalendar;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Long getRolActivityId() {
        return rolActivityId;
    }

    public void setRolActivityId(Long rolActivityId) {
        this.rolActivityId = rolActivityId;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
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

    public void setEndCalendar(Calendar endCalendar) {
        this.endCalendar = endCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        this.startCalendar = startCalendar;
    }

    public Calendar getEndCalendar() {

        if(endCalendar != null)
            return endCalendar;

        endCalendar = CalendarExt.stringToCalendar(end);

        return endCalendar;
    }

    public Calendar getStartCalendar() {
        if(startCalendar != null)
            return startCalendar;

        startCalendar = CalendarExt.stringToCalendar(start);

        return startCalendar;
    }

    public static List<RolActivityDto> convertToDtos(List<RolActivity> lstActivities) {
        List<RolActivityDto> lstDtos = new ArrayList<>();

        for(RolActivity rolAct : lstActivities){
            RolActivityDto dto = new RolActivityDto();
            dto.setRolActivityId(rolAct.getId());
            dto.setSupervisorId(rolAct.getSupervisor().getId());
            dto.setEnd(CalendarExt.calendarToString(rolAct.getEnd()));
            dto.setStart(CalendarExt.calendarToString(rolAct.getStart()));
            lstDtos.add(dto);
        }
        return lstDtos;
    }
}

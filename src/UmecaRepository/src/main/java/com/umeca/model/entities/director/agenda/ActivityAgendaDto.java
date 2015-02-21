package com.umeca.model.entities.director.agenda;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlan;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanArrangement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityAgendaDto {
    private Long activityId;
    private String eventId;
    private String end;
    private String start;
    private Calendar endCalendar;
    private Calendar startCalendar;
    private String place;
    private String description;
    private Long priorityId;

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

    public Calendar getEndCalendar() {

        if (endCalendar != null)
            return endCalendar;

        endCalendar = CalendarExt.stringToCalendar(end);

        return endCalendar;
    }

    public Calendar getStartCalendar() {
        if (startCalendar != null)
            return startCalendar;

        startCalendar = CalendarExt.stringToCalendar(start);

        return startCalendar;
    }

    public static List<ActivityAgendaDto> convertToDtos(List<ActivityAgenda> lstActivities) {
        List<ActivityAgendaDto> lstDtos = new ArrayList<>();

        for (ActivityAgenda actAgenda : lstActivities) {
            ActivityAgendaDto dto = new ActivityAgendaDto();
            dto.setActivityId(actAgenda.getId());

            dto.setEnd(CalendarExt.calendarToString(actAgenda.getEnd()));
            dto.setStart(CalendarExt.calendarToString(actAgenda.getStart()));

            dto.setDescription(actAgenda.getDescription());
            dto.setPlace(actAgenda.getPlace());
            dto.setPriorityId(actAgenda.getPriority().getId());

            lstDtos.add(dto);
        }
        return lstDtos;
    }

    public void setEndCalendar(Calendar endCalendar) {
        this.endCalendar = endCalendar;
    }

    public void setStartCalendar(Calendar startCalendar) {
        this.startCalendar = startCalendar;
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

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }
}

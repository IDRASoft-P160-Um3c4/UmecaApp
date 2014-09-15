package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class ActivityMonitoringPlanDto {
    private Long activityId;
    private String eventId;
    private Long caseId;
    private Long monitoringPlanId;
    private String end;
    private String start;
    private Calendar endCalendar;
    private Calendar startCalendar;
    private Long activityMonId;
    private Long goalId;
    private Long sourceId;
    private List<Long> lstArrangements;
    private String group;

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

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getMonitoringPlanId() {
        return monitoringPlanId;
    }

    public void setMonitoringPlanId(Long monitoringPlanId) {
        this.monitoringPlanId = monitoringPlanId;
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

    public Long getActivityMonId() {
        return activityMonId;
    }

    public void setActivityMonId(Long activityMonId) {
        this.activityMonId = activityMonId;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
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

    public List<Long> getLstArrangements() {
        return lstArrangements;
    }

    public void setLstArrangements(List<Long> lstArrangements) {
        this.lstArrangements = lstArrangements;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public static List<ActivityMonitoringPlanDto> convertToDtos(List<ActivityMonitoringPlan> lstActivities) {
        List<ActivityMonitoringPlanDto> lstDtos = new ArrayList<>();

        for(ActivityMonitoringPlan actMonPlan: lstActivities){
            ActivityMonitoringPlanDto dto = new ActivityMonitoringPlanDto();
            dto.setActivityId(actMonPlan.getId());

            dto.setActivityMonId(actMonPlan.getSupervisionActivity().getId());
            dto.setGoalId(actMonPlan.getActivityGoal().getId());
            dto.setSourceId(actMonPlan.getFramingSelectedSourceRel().getId());

            List<ActivityMonitoringPlanArrangement> lstAssArr = actMonPlan.getLstAssignedArrangement();
            List<Long> lstAssArrId = new ArrayList<>();
            for(ActivityMonitoringPlanArrangement arr: lstAssArr){
                lstAssArrId.add(arr.getAssignedArrangement().getId());
            }
            dto.setLstArrangements(lstAssArrId);

            dto.setEnd(CalendarExt.calendarToString(actMonPlan.getEnd()));
            dto.setStart(CalendarExt.calendarToString(actMonPlan.getStart()));

            Long caseId = actMonPlan.getCaseDetention().getId();
            dto.setCaseId(caseId);
            dto.setMonitoringPlanId(actMonPlan.getMonitoringPlan().getId());
            dto.setGroup(actMonPlan.getGroup());

            lstDtos.add(dto);
        }
        return lstDtos;
    }
}

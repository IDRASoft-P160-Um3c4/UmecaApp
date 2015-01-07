package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.SelectList;

import java.util.Calendar;
import java.util.List;

public class ResponseActivities {
    public List<SelectList> lstGoals;
    public List<SelectList> lstActivities;
    public List<ActivityMonitoringPlanResponse> lstMonPlanActivities;
    public boolean hasError;
    public String message;
    public String today = CalendarExt.calendarToString(Calendar.getInstance());

    public List<SelectList> getLstGoals() {
        return lstGoals;
    }

    public void setLstGoals(List<SelectList> lstGoals) {
        this.lstGoals = lstGoals;
    }

    public List<SelectList> getLstActivities() {
        return lstActivities;
    }

    public void setLstActivities(List<SelectList> lstActivities) {
        this.lstActivities = lstActivities;
    }

    public List<ActivityMonitoringPlanResponse> getLstMonPlanActivities() {
        return lstMonPlanActivities;
    }

    public void setLstMonPlanActivities(List<ActivityMonitoringPlanResponse> lstMonPlanActivities) {
        this.lstMonPlanActivities = lstMonPlanActivities;
    }

    public boolean getHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

}

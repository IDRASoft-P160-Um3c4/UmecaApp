package com.umeca.model.entities.director.agenda;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlanResponse;

import java.util.Calendar;
import java.util.List;

public class ResponseAgendaActivities {
    public List<ActivityAgendaResponse> lstAgendaActivities;
    public boolean hasError;
    public String message;
    public String today = CalendarExt.calendarToString(Calendar.getInstance());

    public List<ActivityAgendaResponse> getLstAgendaActivities() {
        return lstAgendaActivities;
    }

    public void setLstAgendaActivities(List<ActivityAgendaResponse> lstAgendaActivities) {
        this.lstAgendaActivities = lstAgendaActivities;
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

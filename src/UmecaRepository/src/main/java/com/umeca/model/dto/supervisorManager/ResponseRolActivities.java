package com.umeca.model.dto.supervisorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.SelectList;

import java.util.Calendar;
import java.util.List;

public class ResponseRolActivities {
    public List<SelectList> lstSupervisor;
    public List<RolActivityResponse> lstRolActivities;
    public boolean hasError;
    public String message;
    public String today = CalendarExt.calendarToString(Calendar.getInstance());

    public List<SelectList> getLstSupervisor() {
        return lstSupervisor;
    }

    public void setLstSupervisor(List<SelectList> lstSupervisor) {
        this.lstSupervisor = lstSupervisor;
    }

    public List<RolActivityResponse> getLstRolActivities() {
        return lstRolActivities;
    }

    public void setLstRolActivities(List<RolActivityResponse> lstRolActivities) {
        this.lstRolActivities = lstRolActivities;
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

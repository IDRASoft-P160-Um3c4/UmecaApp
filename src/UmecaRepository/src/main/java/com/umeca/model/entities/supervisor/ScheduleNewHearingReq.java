package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class ScheduleNewHearingReq {

    private Calendar hearingDate;
    private String hearingDateSt;
    private String hearingTimeSt;

    private String hearingRoom;
    private Long hearingTypeId;
    private String lstIds;

    private List<Long> lstMonPlanIds;
    private Boolean hasReminder;

    private Calendar hearingDateReminder;
    private String hearingReminderDateSt;
    private String hearingReminderTimeSt;

    public String getHearingReminderTimeSt() {
        return hearingReminderTimeSt;
    }

    public void setHearingReminderTimeSt(String hearingReminderTimeSt) {
        this.hearingReminderTimeSt = hearingReminderTimeSt;
    }

    public String getHearingReminderDateSt() {
        return hearingReminderDateSt;
    }

    public void setHearingReminderDateSt(String hearingReminderDateSt) {
        this.hearingReminderDateSt = hearingReminderDateSt;
    }

    public Boolean getHasReminder() {
        if(hasReminder == null)
            hasReminder = false;

        return hasReminder;
    }

    public void setHasReminder(Boolean hasReminder) {
        this.hasReminder = hasReminder;
    }


    public Calendar getHearingDate() {
        if(hearingDate != null){
            return hearingDate;
        }
        hearingDate = CalendarExt.stringToCalendar(hearingDateSt + "|" + hearingTimeSt);
        return hearingDate;
    }

    public void setHearingDate(Calendar hearingDate) {
        this.hearingDate = hearingDate;
    }

    public String getHearingDateSt() {
        return hearingDateSt;
    }

    public void setHearingDateSt(String hearingDateSt) {
        this.hearingDateSt = hearingDateSt;
    }

    public String getHearingTimeSt() {
        return hearingTimeSt;
    }

    public void setHearingTimeSt(String hearingTimeSt) {
        this.hearingTimeSt = hearingTimeSt;
    }

    public String getHearingRoom() {
        return hearingRoom;
    }

    public void setHearingRoom(String hearingRoom) {
        this.hearingRoom = hearingRoom;
    }

    public Long getHearingTypeId() {
        return hearingTypeId;
    }

    public void setHearingTypeId(Long hearingTypeId) {
        this.hearingTypeId = hearingTypeId;
    }

    public String getLstIds() {
        return lstIds;
    }

    public void setLstIds(String lstIds) {
        this.lstIds = lstIds;
    }

    public Calendar getHearingDateReminder() {

        if(hasReminder.equals(false))
            return null;

        if(hearingDateReminder != null){
            return hearingDateReminder;
        }
        hearingDateReminder = CalendarExt.stringToCalendar(hearingReminderDateSt + "|" + hearingReminderTimeSt);
        return hearingDateReminder;
    }

    public void setHearingDateReminder(Calendar hearingDateReminder) {
        this.hearingDateReminder = hearingDateReminder;
    }

    public List<Long> getLstMonPlanIds() {
        if(lstMonPlanIds != null)
            return lstMonPlanIds;

        lstMonPlanIds = new ArrayList<>();

        try {
            String[] arrIds = lstIds.split(",");
            for (String id : arrIds){
                lstMonPlanIds.add(Long.parseLong(id.trim()));
            }
        }catch (Exception ex){
            return lstMonPlanIds;
        }
        return lstMonPlanIds;
    }

    public void setLstMonPlanIds(List<Long> lstMonPlanIds) {
        this.lstMonPlanIds = lstMonPlanIds;
    }
}

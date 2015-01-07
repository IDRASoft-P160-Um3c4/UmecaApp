package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.util.Calendar;

public class ScheduleHearingSubListView implements EntityGrid {

    private Long id;
    private Calendar timestamp;
    private Calendar hearingDate;
    private String hearingDateSt;
    private Calendar hearingReminderDate;
    private String hearingReminderDateSt;
    private String room;
    private String usernameCreate;
    private boolean hasReminder;
    private String hearingTypeName;
    private String hearingCreationDateSt;

    public ScheduleHearingSubListView(Long id, String room, Calendar hearingDate, Calendar timestamp, Calendar hearingReminderDate, boolean hasReminder,
                                      String usernameCreate, String hearingTypeName) {
        this.id = id;
        this.timestamp = timestamp;
        this.hearingDate = hearingDate;
        this.hearingReminderDate = hearingReminderDate;
        this.room = room;
        this.usernameCreate = usernameCreate;
        this.hasReminder = hasReminder;
        this.hearingTypeName = hearingTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public Calendar getHearingDate() {
        return hearingDate;
    }

    public void setHearingDate(Calendar hearingDate) {
        this.hearingDate = hearingDate;
    }

    public String getHearingDateSt() {
        return CalendarExt.calendarToFormatString(hearingDate, Constants.FORMAT_CALENDAR_I);
    }

    public void setHearingDateSt(String hearingDateSt) {
        this.hearingDateSt = hearingDateSt;
    }

    public Calendar getHearingReminderDate() {
        return hearingReminderDate;
    }

    public void setHearingReminderDate(Calendar hearingReminderDate) {
        this.hearingReminderDate = hearingReminderDate;
    }

    public String getHearingReminderDateSt() {
        return CalendarExt.calendarToFormatString(hearingReminderDate, Constants.FORMAT_CALENDAR_I);
    }

    public void setHearingReminderDateSt(String hearingReminderDateSt) {
        this.hearingReminderDateSt = hearingReminderDateSt;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUsernameCreate() {
        return usernameCreate;
    }

    public void setUsernameCreate(String usernameCreate) {
        this.usernameCreate = usernameCreate;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public String getHearingTypeName() {
        return hearingTypeName;
    }

    public void setHearingTypeName(String hearingTypeName) {
        this.hearingTypeName = hearingTypeName;
    }

    public String getHearingCreationDateSt() {
        return CalendarExt.calendarToFormatString(timestamp, Constants.FORMAT_CALENDAR_I);
    }

    public void setHearingCreationDateSt(String hearingCreationDateSt) {
        this.hearingCreationDateSt = hearingCreationDateSt;
    }
}

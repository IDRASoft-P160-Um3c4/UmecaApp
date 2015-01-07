package com.umeca.model.entities.shared;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class LogCommentJson {
    private String action;
    private String type;
    private String timestamp;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimestampCalendar(Calendar now) {
        this.timestamp = CalendarExt.calendarToFormatString(now, Constants.FORMAT_CALENDAR_I);
    }
}

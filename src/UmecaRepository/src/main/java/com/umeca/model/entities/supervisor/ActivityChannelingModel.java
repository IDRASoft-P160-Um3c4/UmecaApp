package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class ActivityChannelingModel {

    private String startTx;
    private String endTx;
    private Integer attendance;
    private String hasJustification;
    private String commentsJustify;
    private String hasReschedule;
    private String activityGoal;

    public ActivityChannelingModel(Calendar start, Calendar end, Integer attendance, Boolean isJustified, String commentsJustify,
                                   Long hasReschedule, String activityGoal) {
        this.startTx = CalendarExt.calendarToFormatString(start, Constants.FORMAT_CALENDAR_I);
        this.endTx = CalendarExt.calendarToFormatString(end, Constants.FORMAT_CALENDAR_I);
        this.attendance = attendance == null ? -1 : attendance;
        this.hasJustification = isJustified == null ? "NA" : (isJustified ? "SI" : "NO");
        this.commentsJustify = commentsJustify;
        this.hasReschedule = hasReschedule == null ? "NO" : "SI";
        this.activityGoal = activityGoal;
    }

    public String getStartTx() {
        return startTx;
    }

    public void setStartTx(String startTx) {
        this.startTx = startTx;
    }

    public String getEndTx() {
        return endTx;
    }

    public void setEndTx(String endTx) {
        this.endTx = endTx;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public String getHasJustification() {
        return hasJustification;
    }

    public void setHasJustification(String hasJustification) {
        this.hasJustification = hasJustification;
    }

    public String getCommentsJustify() {
        return commentsJustify;
    }

    public void setCommentsJustify(String commentsJustify) {
        this.commentsJustify = commentsJustify;
    }

    public String getHasReschedule() {
        return hasReschedule;
    }

    public void setHasReschedule(String hasReschedule) {
        this.hasReschedule = hasReschedule;
    }

    public String getActivityGoal() {
        return activityGoal;
    }

    public void setActivityGoal(String activityGoal) {
        this.activityGoal = activityGoal;
    }
}

package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;

import java.util.Calendar;

/**
 * Project: Umeca
 * User: Israel
 * Date: 7/9/14
 * Time: 3:13 PM
 */
public class ActivityMonitoringPlanLog {

    private Long id;
    private String start;
    private String end;
    private String status;
    private Long actSupervisionId;
    private Long aidSourceId;
    private String comments;

    public ActivityMonitoringPlanLog(Long id, Calendar start, Calendar end, String status, Long actSupervisionId, Long aidSourceId, String comments){
        this.id = id;
        this.start = CalendarExt.calendarToFormatString(start, "dd/MM/yyyy HH:mm");
        this.end = CalendarExt.calendarToFormatString(end, "dd/MM/yyyy HH:mm");
        this.status = status;
        this.actSupervisionId = actSupervisionId;
        this.aidSourceId = aidSourceId;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getActSupervisionId() {
        return actSupervisionId;
    }

    public void setActSupervisionId(Long actSupervisionId) {
        this.actSupervisionId = actSupervisionId;
    }

    public Long getAidSourceId() {
        return aidSourceId;
    }

    public void setAidSourceId(Long aidSourceId) {
        this.aidSourceId = aidSourceId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

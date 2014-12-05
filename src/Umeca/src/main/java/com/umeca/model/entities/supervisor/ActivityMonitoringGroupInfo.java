package com.umeca.model.entities.supervisor;

import java.util.Calendar;

public class ActivityMonitoringGroupInfo {
    private String group;
    private Long totalActivities;
    private Calendar start;
    private Calendar end;

    public ActivityMonitoringGroupInfo(String group, Long totalActivities) {
        this.group = group;
        this.totalActivities = totalActivities;
    }


    public ActivityMonitoringGroupInfo(String group, Long totalActivities, Calendar start, Calendar end) {
        this.group = group;
        this.totalActivities = totalActivities;
        this.start = start;
        this.end = end;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getTotalActivities() {
        return totalActivities;
    }

    public void setTotalActivities(Long totalActivities) {
        this.totalActivities = totalActivities;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
}

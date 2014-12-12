package com.umeca.model.entities.supervisor;

public class RequestActivities {

    public Long monPlanId;
    public int yearStart;
    public int monthStart;
    public int yearEnd;
    public int monthEnd;
    public Long activityId;
    public Long caseFilterId;
    public Long userFilterId;

    public Long getMonPlanId() {
        return monPlanId;
    }

    public void setMonPlanId(Long monPlanId) {
        this.monPlanId = monPlanId;
    }

    public int getYearStart() {
        return yearStart;
    }

    public void setYearStart(int yearStart) {
        this.yearStart = yearStart;
    }

    public int getMonthStart() {
        return monthStart;
    }

    public void setMonthStart(int monthStart) {
        this.monthStart = monthStart;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd = yearEnd;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(int monthEnd) {
        this.monthEnd = monthEnd;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getCaseFilterId() {
        return caseFilterId;
    }

    public void setCaseFilterId(Long caseFilterId) {
        this.caseFilterId = caseFilterId;
    }

    public Long getUserFilterId() {

        return userFilterId;
    }

    public void setUserFilterId(Long userFilterId) {
        this.userFilterId = userFilterId;
    }
}

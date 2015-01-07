package com.umeca.model.entities.shared;

import java.util.List;

public class SystemSettingValues {

    private List<Long> LstIdsArrangement;
    private Long SupervisionActivityId;
    private Long GoalActivityId;
    private List<Long> LstIdsArrangementReminder;
    private Long SupervisionActivityIdReminder;
    private Long GoalActivityIdReminder;
    private int DaysBeforeForReminder;


    public List<Long> getLstIdsArrangement() {
        return LstIdsArrangement;
    }

    public void setLstIdsArrangement(List<Long> lstIdsArrangement) {
        LstIdsArrangement = lstIdsArrangement;
    }

    public Long getSupervisionActivityId() {
        return SupervisionActivityId;
    }

    public void setSupervisionActivityId(Long supervisionActivityId) {
        SupervisionActivityId = supervisionActivityId;
    }

    public Long getGoalActivityId() {
        return GoalActivityId;
    }

    public void setGoalActivityId(Long goalActivityId) {
        GoalActivityId = goalActivityId;
    }

    public List<Long> getLstIdsArrangementReminder() {
        return LstIdsArrangementReminder;
    }

    public void setLstIdsArrangementReminder(List<Long> lstIdsArrangementReminder) {
        LstIdsArrangementReminder = lstIdsArrangementReminder;
    }

    public Long getSupervisionActivityIdReminder() {
        return SupervisionActivityIdReminder;
    }

    public void setSupervisionActivityIdReminder(Long supervisionActivityIdReminder) {
        SupervisionActivityIdReminder = supervisionActivityIdReminder;
    }

    public Long getGoalActivityIdReminder() {
        return GoalActivityIdReminder;
    }

    public void setGoalActivityIdReminder(Long goalActivityIdReminder) {
        GoalActivityIdReminder = goalActivityIdReminder;
    }

    public int getDaysBeforeForReminder() {
        return DaysBeforeForReminder;
    }

    public void setDaysBeforeForReminder(int daysBeforeForReminder) {
        DaysBeforeForReminder = daysBeforeForReminder;
    }
}

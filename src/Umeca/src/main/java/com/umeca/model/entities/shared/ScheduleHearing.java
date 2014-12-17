package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.supervisor.ActivityMonitoringPlan;
import com.umeca.model.entities.supervisor.HearingType;
import com.umeca.model.entities.supervisor.MonitoringPlan;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name="schedule_hearing")
public class ScheduleHearing {

    @Id
    @GeneratedValue
    @Column(name = "id_schedule_hearing", nullable = false)
    private Long id;

    @Column(name="timestamp", nullable = false)
    private Calendar timestamp;

    @Column(name="hearingDate", nullable = false)
    private Calendar hearingDate;

    @Column(name="hearingReminderDate", nullable = true)
    private Calendar hearingReminderDate;

    @Column(name="room", nullable = false)
    private String room;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name="has_reminder", nullable = false)
    private boolean hasReminder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monitoring_plan", nullable = false)
    private MonitoringPlan monitoringPlan;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activity_monitoring_plan_hearing", nullable = false)
    private ActivityMonitoringPlan activityMonitoringPlanHearing;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_activity_monitoring_plan_hearing_reminder", nullable = true)
    private ActivityMonitoringPlan activityMonitoringPlanHearingReminder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hearing_type", nullable = false)
    private HearingType hearingType;

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

    public Calendar getHearingReminderDate() {
        return hearingReminderDate;
    }

    public void setHearingReminderDate(Calendar hearingReminderDate) {
        this.hearingReminderDate = hearingReminderDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    public MonitoringPlan getMonitoringPlan() {
        return monitoringPlan;
    }

    public void setMonitoringPlan(MonitoringPlan monitoringPlan) {
        this.monitoringPlan = monitoringPlan;
    }

    public ActivityMonitoringPlan getActivityMonitoringPlanHearing() {
        return activityMonitoringPlanHearing;
    }

    public void setActivityMonitoringPlanHearing(ActivityMonitoringPlan activityMonitoringPlanHearing) {
        this.activityMonitoringPlanHearing = activityMonitoringPlanHearing;
    }

    public ActivityMonitoringPlan getActivityMonitoringPlanHearingReminder() {
        return activityMonitoringPlanHearingReminder;
    }

    public void setActivityMonitoringPlanHearingReminder(ActivityMonitoringPlan activityMonitoringPlanHearingReminder) {
        this.activityMonitoringPlanHearingReminder = activityMonitoringPlanHearingReminder;
    }

    public HearingType getHearingType() {
        return hearingType;
    }

    public void setHearingType(HearingType hearingType) {
        this.hearingType = hearingType;
    }

}

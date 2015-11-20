package com.umeca.model.entities.supervisor;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by DeveloperII on 19/11/2015.
 */
public class ExcelActivityMonitoringPlan {

    private Long idCase;
    private Long idSupervisionActivity;
    private String supervisionActivity;
    private String supervisionActivityCode;
    private Long idActivityGoal;
    private String activityGoal;
    private Integer channelingAssistance;
    private Calendar doneTime;
    private String status;


    public ExcelActivityMonitoringPlan(Long idCase,
                                       Long idSupervisionActivity,
                                       String supervisionActivity,
                                       String supervisionActivityCode,
                                       Long idActivityGoal,
                                       String activityGoal,
                                       Integer channelingAssistance,
                                       Calendar doneTime,
                                       String status) {
        this.idCase = idCase;
        this.idSupervisionActivity = idSupervisionActivity;
        this.supervisionActivity = supervisionActivity;
        this.supervisionActivityCode = supervisionActivityCode;
        this.idActivityGoal = idActivityGoal;
        this.activityGoal = activityGoal;
        this.channelingAssistance = channelingAssistance;
        this.doneTime = doneTime;
        this.status = status;

    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Long getIdSupervisionActivity() {
        return idSupervisionActivity;
    }

    public void setIdSupervisionActivity(Long idSupervisionActivity) {
        this.idSupervisionActivity = idSupervisionActivity;
    }

    public String getSupervisionActivity() {
        return supervisionActivity;
    }

    public void setSupervisionActivity(String supervisionActivity) {
        this.supervisionActivity = supervisionActivity;
    }

    public Long getIdActivityGoal() {
        return idActivityGoal;
    }

    public void setIdActivityGoal(Long idActivityGoal) {
        this.idActivityGoal = idActivityGoal;
    }

    public String getActivityGoal() {
        return activityGoal;
    }

    public void setActivityGoal(String activityGoal) {
        this.activityGoal = activityGoal;
    }

    public Integer getChannelingAssistance() {
        return channelingAssistance;
    }

    public void setChannelingAssistance(Integer channelingAssistance) {
        this.channelingAssistance = channelingAssistance;
    }


    public Calendar getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Calendar doneTime) {
        this.doneTime = doneTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupervisionActivityCode() {
        return supervisionActivityCode;
    }

    public void setSupervisionActivityCode(String supervisionActivityCode) {
        this.supervisionActivityCode = supervisionActivityCode;
    }
}

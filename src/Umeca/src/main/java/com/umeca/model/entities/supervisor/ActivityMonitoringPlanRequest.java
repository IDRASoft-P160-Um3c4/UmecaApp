package com.umeca.model.entities.supervisor;

import java.util.ArrayList;
import java.util.List;

public class ActivityMonitoringPlanRequest {
    private Long caseId;
    private Long monitoringPlanId;
    private String monitoringPlanStatus;
    private List<Long> lstActivitiesDel;
    private List<ActivityMonitoringPlanEvent> lstActivitiesUpserted;
    private List<ActivityMonitoringPlanDto> lstActivitiesUpsert;

    private long actsIns;
    private long actsUpd;
    private long actsDel;

    public ActivityMonitoringPlanRequest(){
        lstActivitiesDel = new ArrayList<>();
        lstActivitiesUpsert = new ArrayList<>();
        lstActivitiesUpserted = new ArrayList<>();
        actsIns = 0;
        actsUpd = 0;
        actsDel = 0;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public List<Long> getLstActivitiesDel() {
        return lstActivitiesDel;
    }

    public void setLstActivitiesDel(List<Long> lstActivitiesDel) {
        this.lstActivitiesDel = lstActivitiesDel;
    }

    public List<ActivityMonitoringPlanDto> getLstActivitiesUpsert() {
        return lstActivitiesUpsert;
    }

    public void setLstActivitiesUpsert(List<ActivityMonitoringPlanDto> lstActivitiesUpsert) {
        this.lstActivitiesUpsert = lstActivitiesUpsert;
    }

    public Long getMonitoringPlanId() {
        return monitoringPlanId;
    }

    public void setMonitoringPlanId(Long monitoringPlanId) {
        this.monitoringPlanId = monitoringPlanId;
    }

    public String getMonitoringPlanStatus() {
        return monitoringPlanStatus;
    }

    public void setMonitoringPlanStatus(String monitoringPlanStatus) {
        this.monitoringPlanStatus = monitoringPlanStatus;
    }

    public List<ActivityMonitoringPlanEvent> getLstActivitiesUpserted() {
        return lstActivitiesUpserted;
    }

    public void setLstActivitiesUpserted(List<ActivityMonitoringPlanEvent> lstActivitiesUpserted) {
        this.lstActivitiesUpserted = lstActivitiesUpserted;
    }

    public long getActsIns() {
        return actsIns;
    }

    public void setActsIns(long actsIns) {
        this.actsIns = actsIns;
    }

    public void addActsIns(){
        this.actsIns++;
    }

    public long getActsUpd() {
        return actsUpd;
    }

    public void setActsUpd(long actsUpd) {
        this.actsUpd = actsUpd;
    }

    public void addActsUpd(){
        this.actsUpd++;
    }

    public long getActsDel() {
        return actsDel;
    }

    public void setActsDel(long actsDel) {
        this.actsDel = actsDel;
    }

    public void addActsDel(){
        this.actsDel++;
    }

}

package com.umeca.model.entities.supervisor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityMonitoringPlanRequest {
    private Long caseId;
    private Long monitoringPlanId;
    private String monitoringPlanStatus;
    private List<Long> lstActivitiesDel;
    private List<ActivityMonitoringPlanEvent> lstActivitiesUpserted;
    private List<ActivityMonitoringPlanDto> lstActivitiesUpsert;
    private String password;

    private long actsIns;
    private long actsUpd;
    private long actsDel;
    private long actsPreIns;
    private long actsPreUpd;
    private long actsPreDel;
    private Calendar now;

    private boolean isInAuthorizeReady;

    public ActivityMonitoringPlanRequest(){
        lstActivitiesDel = new ArrayList<>();
        lstActivitiesUpsert = new ArrayList<>();
        lstActivitiesUpserted = new ArrayList<>();
        actsIns = 0;
        actsUpd = 0;
        actsDel = 0;
        actsPreIns = 0;
        actsPreUpd = 0;
        actsPreDel = 0;
        isInAuthorizeReady = false;
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

    public void incActsIns(){
        this.actsIns++;
    }

    public long getActsUpd() {
        return actsUpd;
    }

    public void setActsUpd(long actsUpd) {
        this.actsUpd = actsUpd;
    }

    public void incActsUpd(){
        this.actsUpd++;
    }

    public long getActsDel() {
        return actsDel;
    }

    public void setActsDel(long actsDel) {
        this.actsDel = actsDel;
    }

    public void incActsDel(){
        this.actsDel++;
    }

    public void setNow(Calendar now) {
        this.now = now;
    }

    public Calendar getNow() {
        return now;
    }

    public void setInAuthorizeReady(boolean inAuthorizeReady) {
        isInAuthorizeReady = inAuthorizeReady;
    }

    public boolean isInAuthorizeReady() {
        return isInAuthorizeReady;
    }

    public void decActsUpd() {
        this.actsUpd--;
    }

    public long getActsPreIns() {
        return actsPreIns;
    }

    public void setActsPreIns(long actsPreIns) {
        this.actsPreIns = actsPreIns;
    }

    public void incActsPreIns(){
        this.actsPreIns++;
    }

    public long getActsPreUpd() {
        return actsPreUpd;
    }

    public void incActsPreUpd(){
        this.actsPreUpd++;
    }

    public void setActsPreUpd(long actsPreUpd) {
        this.actsPreUpd = actsPreUpd;
    }

    public long getActsPreDel() {
        return actsPreDel;
    }

    public void setActsPreDel(long actsPreDel) {
        this.actsPreDel = actsPreDel;
    }

    public void incActsPreDel(){
        this.actsPreDel++;
    }

    public boolean hasActivitiesInPreAuthorizeMode(){
        if(actsPreDel > 0 || actsPreIns > 0 || actsPreUpd > 0 )
            return true;
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

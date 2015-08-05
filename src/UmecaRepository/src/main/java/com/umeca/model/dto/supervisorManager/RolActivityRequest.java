package com.umeca.model.dto.supervisorManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RolActivityRequest {

    private List<Long> lstActivitiesDel;
    private List<RolActivityEvent> lstActivitiesUpserted;
    private List<RolActivityDto> lstActivitiesUpsert;

    private long actsIns;
    private long actsUpd;
    private long actsDel;
    private Calendar now;

    public RolActivityRequest(){
        lstActivitiesDel = new ArrayList<>();
        lstActivitiesUpsert = new ArrayList<>();
        lstActivitiesUpserted = new ArrayList<>();
        actsIns = 0;
        actsUpd = 0;
        actsDel = 0;
    }

    public List<Long> getLstActivitiesDel() {
        return lstActivitiesDel;
    }

    public void setLstActivitiesDel(List<Long> lstActivitiesDel) {
        this.lstActivitiesDel = lstActivitiesDel;
    }

    public List<RolActivityEvent> getLstActivitiesUpserted() {
        return lstActivitiesUpserted;
    }

    public void setLstActivitiesUpserted(List<RolActivityEvent> lstActivitiesUpserted) {
        this.lstActivitiesUpserted = lstActivitiesUpserted;
    }

    public List<RolActivityDto> getLstActivitiesUpsert() {
        return lstActivitiesUpsert;
    }

    public void setLstActivitiesUpsert(List<RolActivityDto> lstActivitiesUpsert) {
        this.lstActivitiesUpsert = lstActivitiesUpsert;
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

    public void setNow(Calendar now) {
        this.now = now;
    }

    public Calendar getNow() {
        return now;
    }

}

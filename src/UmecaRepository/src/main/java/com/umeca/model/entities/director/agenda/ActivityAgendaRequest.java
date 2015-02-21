package com.umeca.model.entities.director.agenda;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityAgendaRequest {
    private List<Long> lstActivitiesDel;
    private List<ActivityAgendaEvent> lstActivitiesUpserted;
    private List<ActivityAgendaDto> lstActivitiesUpsert;

    private long actsIns;
    private long actsUpd;
    private long actsDel;
    private Calendar now;

    public ActivityAgendaRequest(){
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

    public List<ActivityAgendaDto> getLstActivitiesUpsert() {
        return lstActivitiesUpsert;
    }

    public void setLstActivitiesUpsert(List<ActivityAgendaDto> lstActivitiesUpsert) {
        this.lstActivitiesUpsert = lstActivitiesUpsert;
    }

    public List<ActivityAgendaEvent> getLstActivitiesUpserted() {
        return lstActivitiesUpserted;
    }

    public void setLstActivitiesUpserted(List<ActivityAgendaEvent> lstActivitiesUpserted) {
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

    public void decActsUpd() {
        this.actsUpd--;
    }

}

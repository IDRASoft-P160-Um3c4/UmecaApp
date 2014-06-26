package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Location;
import com.umeca.model.entities.reviewer.Case;

import java.sql.Time;
import java.util.Date;

public class HearingFormatView {

    public HearingFormatView() {

    }

    private String idFolder;

    private String idJudicial;

    private Date appointmentDate;

    private String room;

    private Time initTime;

    private Time endTime;

    private String judgeName;

    private String mpName;

    private String defenderName;

    private String imputedName;

    private String imputedFLastName;

    private String imputedSLastName;

    private Date imputedBirthDate;

    private String imputedTel;


/*
    private Integer arrangementType;

    private String crimes;

    private String additionalData;

    private Integer controlDetention;

    private Integer hearingType;

    private Date imputationDate;

    private String linkageRoom;

    private Date linkageDate;

    private Time linkageTime;

    private String terms;

    private Integer extension;


    public Boolean canSave;

    public Boolean hasHF;

    public String lstArrangement;

    public String zipCode;

    public String stateName;

    public String municipalityName;

    public String locationName;

    public String street;

    public String innNum;

    public String outNum;

    public Long idLocation;

    public String location;

    public Boolean existImputed;

    public String supervisorName;
*/

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdJudicial() {
        return idJudicial;
    }

    public void setIdJudicial(String idJudicial) {
        this.idJudicial = idJudicial;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Time getInitTime() {
        return initTime;
    }

    public void setInitTime(Time initTime) {
        this.initTime = initTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getMpName() {
        return mpName;
    }

    public void setMpName(String mpName) {
        this.mpName = mpName;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public void setDefenderName(String defenderName) {
        this.defenderName = defenderName;
    }

    public String getImputedName() {
        return imputedName;
    }

    public void setImputedName(String imputedName) {
        this.imputedName = imputedName;
    }

    public String getImputedFLastName() {
        return imputedFLastName;
    }

    public void setImputedFLastName(String imputedFLastName) {
        this.imputedFLastName = imputedFLastName;
    }

    public String getImputedSLastName() {
        return imputedSLastName;
    }

    public void setImputedSLastName(String imputedSLastName) {
        this.imputedSLastName = imputedSLastName;
    }

    public Date getImputedBirthDate() {
        return imputedBirthDate;
    }

    public void setImputedBirthDate(Date imputedBirthDate) {
        this.imputedBirthDate = imputedBirthDate;
    }

    public String getImputedTel() {
        return imputedTel;
    }

    public void setImputedTel(String imputedTel) {
        this.imputedTel = imputedTel;
    }
}
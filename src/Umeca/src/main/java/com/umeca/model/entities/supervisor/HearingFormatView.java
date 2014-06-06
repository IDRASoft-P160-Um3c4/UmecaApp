package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.reviewer.Case;

import java.sql.Time;
import java.util.Date;

public class HearingFormatView {

    public HearingFormatView() {

    }

    private Integer arrangementType;

    private String numberDate;

    private String room;

    private Time initTime;

    private Time endTime;

    private String judgeName;

    private String mpName;

    private String defenderName;

    private String imputedName;

    private String imputedFLastName;

    private String imputedSLastName;

    private String imputedBirthDate;

    private String imputedTel;

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

    public String idFolderCode;

    public Boolean canSave;


    public String getNumberDate() {
        return numberDate;
    }

    public void setNumberDate(String numberDate) {
        this.numberDate = numberDate;
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

    public String getImputedBirthDate() {
        return imputedBirthDate;
    }

    public void setImputedBirthDate(String imputedBirthDate) {
        this.imputedBirthDate = imputedBirthDate;
    }

    public String getImputedTel() {
        return imputedTel;
    }

    public void setImputedTel(String imputedTel) {
        this.imputedTel = imputedTel;
    }

    public String getCrimes() {
        return crimes;
    }

    public void setCrimes(String crimes) {
        this.crimes = crimes;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public Integer getControlDetention() {
        return controlDetention;
    }

    public void setControlDetention(Integer controlDetention) {
        this.controlDetention = controlDetention;
    }

    public Integer getHearingType() {
        return hearingType;
    }

    public void setHearingType(Integer hearingType) {
        this.hearingType = hearingType;
    }

    public Date getImputationDate() {
        return imputationDate;
    }

    public void setImputationDate(Date imputationDate) {
        this.imputationDate = imputationDate;
    }

    public String getLinkageRoom() {
        return linkageRoom;
    }

    public void setLinkageRoom(String linkageRoom) {
        this.linkageRoom = linkageRoom;
    }

    public Date getLinkageDate() {
        return linkageDate;
    }

    public void setLinkageDate(Date linkageDate) {
        this.linkageDate = linkageDate;
    }

    public Time getLinkageTime() {
        return linkageTime;
    }

    public void setLinkageTime(Time linkageTime) {
        this.linkageTime = linkageTime;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Integer getExtension() {
        return extension;
    }

    public void setExtension(Integer extension) {
        this.extension = extension;
    }

    public String getIdFolderCode() {
        return idFolderCode;
    }

    public void setIdFolderCode(String idFolderCode) {
        this.idFolderCode = idFolderCode;
    }

    public Boolean getCanSave() {
        return canSave;
    }

    public void setCanSave(Boolean canSave) {
        this.canSave = canSave;
    }

    public Integer getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(Integer arrangementType) {
        this.arrangementType = arrangementType;
    }
}
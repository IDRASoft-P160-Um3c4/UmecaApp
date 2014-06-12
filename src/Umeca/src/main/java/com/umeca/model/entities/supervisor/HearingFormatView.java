package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Location;
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

    private Date imputedBirthDate;

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

    public String idJudicialFolderCode;

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

    public String getLinkageRoom() {
        return linkageRoom;
    }

    public void setLinkageRoom(String linkageRoom) {
        this.linkageRoom = linkageRoom;
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

    public Boolean getHasHF() {
        return hasHF;
    }

    public void setHasHF(Boolean hasHF) {
        this.hasHF = hasHF;
    }

    public Date getImputedBirthDate() {
        return imputedBirthDate;
    }

    public void setImputedBirthDate(Date imputedBirthDate) {
        this.imputedBirthDate = imputedBirthDate;
    }

    public Date getImputationDate() {
        return imputationDate;
    }

    public void setImputationDate(Date imputationDate) {
        this.imputationDate = imputationDate;
    }

    public Date getLinkageDate() {
        return linkageDate;
    }

    public void setLinkageDate(Date linkageDate) {
        this.linkageDate = linkageDate;
    }

    public String getLstArrangement() {
        return lstArrangement;
    }

    public void setLstArrangement(String lstArrangement) {
        this.lstArrangement = lstArrangement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getInnNum() {
        return innNum;
    }

    public void setInnNum(String innNum) {
        this.innNum = innNum;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public Long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Long idLocation) {
        this.idLocation = idLocation;
    }

    public Boolean getExistImputed() {
        return existImputed;
    }

    public void setExistImputed(Boolean existImputed) {
        this.existImputed = existImputed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIdJudicialFolderCode() {
        return idJudicialFolderCode;
    }

    public void setIdJudicialFolderCode(String idJudicialFolderCode) {
        this.idJudicialFolderCode = idJudicialFolderCode;
    }
}
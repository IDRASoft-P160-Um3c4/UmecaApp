package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.dto.LocationDto;
import com.umeca.model.entities.reviewer.Case;

import java.sql.Time;
import java.util.Date;

public class HearingFormatView {

    public HearingFormatView() {

    }

    private Long idCase;

    private Long idFormat;

    private Boolean canSave;

    private Boolean canEdit;

    private Boolean disableAll;

    private String idFolder;

    private String idJudicial;

    private Date appointmentDate;
    private String appointmentDateStr;

    private String room;

    private Time initTime;
    private String initTimeStr;

    private Time endTime;
    private String endTimeStr;

    private String judgeName;

    private String mpName;

    private String defenderName;

    private String imputedName;

    private String imputedFLastName;

    private String imputedSLastName;

    private Date imputedBirthDate;
    private String imputedBirthDateStr;

    private String imputedTel;

    private Integer controlDetention;

    private Integer extension;

    private Integer impForm;

    private Date imputationDate;
    private String imputationDateStr;

    private Date extDate;
    private String extDateStr;

    private Integer vincProcess;

    private String linkageRoom;

    private Date linkageDate;
    private String linkageDateStr;

    private Time linkageTime;
    private String linkageTimeStr;

    private Integer arrangementType;

    private Boolean nationalArrangement;

    private String additionalData;

    private String crimes;

    private String terms;

    private String lstArrangement;

    private String street;

    private String outNum;

    private String innNum;

    private LocationDto location;

    private Long idAddres;

    private String lstContactData;

    private String hearingFormatType;

    private String userName;

    private String confirmComment;

    private String credPass;

    private String lat;

    private String lng;

    private Boolean isFinished;

    private Boolean hasPrevHF;

    private String comments;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Boolean getCanSave() {
        return canSave;
    }

    public void setCanSave(Boolean canSave) {
        this.canSave = canSave;
    }

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

    public Integer getControlDetention() {
        return controlDetention;
    }

    public void setControlDetention(Integer controlDetention) {
        this.controlDetention = controlDetention;
    }

    public Integer getExtension() {
        return extension;
    }

    public void setExtension(Integer extension) {
        this.extension = extension;
    }

    public Integer getImpForm() {
        return impForm;
    }

    public void setImpForm(Integer impForm) {
        this.impForm = impForm;
    }

    public Date getImputationDate() {
        return imputationDate;
    }

    public void setImputationDate(Date imputationDate) {
        this.imputationDate = imputationDate;
    }

    public Integer getVincProcess() {
        return vincProcess;
    }

    public void setVincProcess(Integer vincProcess) {
        this.vincProcess = vincProcess;
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

    public Integer getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(Integer arrangementType) {
        this.arrangementType = arrangementType;
    }

    public String getHearingFormatType() {
        return hearingFormatType;
    }

    public void setHearingFormatType(String hearingFormatType) {
        this.hearingFormatType = hearingFormatType;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getLstArrangement() {
        return lstArrangement;
    }

    public void setLstArrangement(String lstArrangement) {
        this.lstArrangement = lstArrangement;
    }

    public String getCrimes() {
        return crimes;
    }

    public void setCrimes(String crimes) {
        this.crimes = crimes;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getDisableAll() {
        return disableAll;
    }

    public void setDisableAll(Boolean disableAll) {
        this.disableAll = disableAll;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getInnNum() {
        return innNum;
    }

    public void setInnNum(String innNum) {
        this.innNum = innNum;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Long getIdAddres() {
        return idAddres;
    }

    public void setIdAddres(Long idAddres) {
        this.idAddres = idAddres;
    }

    public String getLstContactData() {
        return lstContactData;
    }

    public void setLstContactData(String lstContactData) {
        this.lstContactData = lstContactData;
    }

    public Boolean getNationalArrangement() {
        return nationalArrangement;
    }

    public void setNationalArrangement(Boolean nationalArrangement) {
        this.nationalArrangement = nationalArrangement;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConfirmComment() {
        return confirmComment;
    }

    public void setConfirmComment(String confirmComment) {
        this.confirmComment = confirmComment;
    }

    public String getCredPass() {
        return credPass;
    }

    public void setCredPass(String credPass) {
        this.credPass = credPass;
    }

    public String getAppointmentDateStr() {
        return appointmentDateStr;
    }

    public void setAppointmentDateStr(String appointmentDateStr) {
        this.appointmentDateStr = appointmentDateStr;
    }

    public String getInitTimeStr() {
        return initTimeStr;
    }

    public void setInitTimeStr(String initTimeStr) {
        this.initTimeStr = initTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getImputedBirthDateStr() {
        return imputedBirthDateStr;
    }

    public void setImputedBirthDateStr(String imputedBirthDateStr) {
        this.imputedBirthDateStr = imputedBirthDateStr;
    }

    public String getImputationDateStr() {
        return imputationDateStr;
    }

    public void setImputationDateStr(String imputationDateStr) {
        this.imputationDateStr = imputationDateStr;
    }

    public String getLinkageDateStr() {
        return linkageDateStr;
    }

    public void setLinkageDateStr(String linkageDateStr) {
        this.linkageDateStr = linkageDateStr;
    }

    public String getLinkageTimeStr() {
        return linkageTimeStr;
    }

    public void setLinkageTimeStr(String linkageTimeStr) {
        this.linkageTimeStr = linkageTimeStr;
    }

    public Long getIdFormat() {
        return idFormat;
    }

    public void setIdFormat(Long idFormat) {
        this.idFormat = idFormat;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Boolean getHasPrevHF() {
        return hasPrevHF;
    }

    public void setHasPrevHF(Boolean hasPrevHF) {
        this.hasPrevHF = hasPrevHF;
    }

    public Date getExtDate() {
        return extDate;
    }

    public void setExtDate(Date extDate) {
        this.extDate = extDate;
    }

    public String getExtDateStr() {
        return extDateStr;
    }

    public void setExtDateStr(String extDateStr) {
        this.extDateStr = extDateStr;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
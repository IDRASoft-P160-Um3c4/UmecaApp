package com.umeca.model.dto.tablet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabletHearingFormatSpecsDto {

    public TabletHearingFormatSpecsDto() {
    }

    public TabletHearingFormatSpecsDto(Long id, Integer controlDetention, Integer extension, Integer imputationFormulation, Date imputationDate, Integer linkageProcess, String linkageRoom, Date linkageDate, Date extDate, Date linkageTime, Integer arrangementType, Boolean nationalArrangement) {
        SimpleDateFormat sdfD = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss");
        this.id = id;
        this.controlDetention = controlDetention;
        this.extension = extension;
        this.imputationFormulation = imputationFormulation;
        this.imputationDate = imputationDate == null ? null : sdfD.format(imputationDate);
        this.linkageProcess = linkageProcess;
        this.linkageRoom = linkageRoom;
        this.linkageDate = linkageDate == null ? null : sdfD.format(linkageDate);
        this.extDate = extDate == null ? null : sdfD.format(extDate);
        this.linkageTime = linkageTime == null ? null : sdfT.format(linkageTime);
        this.arrangementType = arrangementType;
        this.nationalArrangement = nationalArrangement;
    }

    private Long id;
    private Integer controlDetention;
    private Integer extension;
    private Integer imputationFormulation;
    private String imputationDate;
    private Integer linkageProcess;
    private String linkageRoom;
    private String linkageDate;
    private String extDate;
    private String linkageTime;
    private Integer arrangementType;
    private Boolean nationalArrangement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getImputationFormulation() {
        return imputationFormulation;
    }

    public void setImputationFormulation(Integer imputationFormulation) {
        this.imputationFormulation = imputationFormulation;
    }

    public String getImputationDate() {
        return imputationDate;
    }

    public void setImputationDate(String imputationDate) {
        this.imputationDate = imputationDate;
    }

    public Integer getLinkageProcess() {
        return linkageProcess;
    }

    public void setLinkageProcess(Integer linkageProcess) {
        this.linkageProcess = linkageProcess;
    }

    public String getLinkageRoom() {
        return linkageRoom;
    }

    public void setLinkageRoom(String linkageRoom) {
        this.linkageRoom = linkageRoom;
    }

    public String getLinkageDate() {
        return linkageDate;
    }

    public void setLinkageDate(String linkageDate) {
        this.linkageDate = linkageDate;
    }

    public String getExtDate() {
        return extDate;
    }

    public void setExtDate(String extDate) {
        this.extDate = extDate;
    }

    public String getLinkageTime() {
        return linkageTime;
    }

    public void setLinkageTime(String linkageTime) {
        this.linkageTime = linkageTime;
    }

    public Integer getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(Integer arrangementType) {
        this.arrangementType = arrangementType;
    }

    public Boolean getNationalArrangement() {
        return nationalArrangement;
    }

    public void setNationalArrangement(Boolean nationalArrangement) {
        this.nationalArrangement = nationalArrangement;
    }
}

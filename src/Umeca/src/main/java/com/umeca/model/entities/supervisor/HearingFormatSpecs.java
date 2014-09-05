package com.umeca.model.entities.supervisor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "hearing_format_specs")
public class HearingFormatSpecs {

    @Id
    @GeneratedValue
    @Column(name = "id_format_specs")
    private Long id;

    @Column(name = "control_detention")
    private Integer controlDetention;

    @Column(name = "extension")
    private Integer extension;

    @Column(name = "imputation_formulation")
    private Integer imputationFormulation;

    @Column(name = "imputation_date")
    private Date imputationDate;

    @Column(name = "linkage_process")
    private Integer linkageProcess;

    @Column(name = "linkage_room")
    private String linkageRoom;

    @Column(name = "linkage_date")
    private Date linkageDate;

    @Column(name = "linkage_time")
    private Time linkageTime;

    @Column(name = "arrangement_type")
    private Integer arrangementType;

    @Column(name = "national_arrangement")
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

    public Date getImputationDate() {
        return imputationDate;
    }

    public void setImputationDate(Date imputationDate) {
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

    public Boolean getNationalArrangement() {
        return nationalArrangement;
    }

    public void setNationalArrangement(Boolean nationalArrangement) {
        this.nationalArrangement = nationalArrangement;
    }
}
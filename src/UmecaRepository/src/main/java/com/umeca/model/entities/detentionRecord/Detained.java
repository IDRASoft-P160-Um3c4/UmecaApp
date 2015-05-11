package com.umeca.model.entities.detentionRecord;

import com.umeca.model.catalog.District;

import javax.persistence.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="detained")
public class Detained {

    @Id
    @GeneratedValue
    @Column(name="id_detained")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="last_name_p")
    private String lastNameP;

    @Column(name="last_name_m")
    private String lastNameM;

    @Column(name="id_folder")
    private String idFolder;

    @Column(name="age")
    private String age;

    @Column(name="register_timestamp", nullable = false)
    private Calendar registerTimestamp;

    @Column(name="init_date", nullable = false)
    private Date initDate;

    @Column(name="init_time", nullable = false)
    private Time initTime;

    @Column(name="investigation_unit",length = 500)
    private String investigationUnit;

    @Column(name="crime",length = 500)
    private String crime;

    @Column(name="is_prosecute",nullable = false)
    private Boolean isProsecute;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="id_district")
    private District district;

    public Detained() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Calendar getRegisterTimestamp() {
        return registerTimestamp;
    }

    public void setRegisterTimestamp(Calendar registerTimestamp) {
        this.registerTimestamp = registerTimestamp;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Time getInitTime() {
        return initTime;
    }

    public void setInitTime(Time initTime) {
        this.initTime = initTime;
    }

    public String getInvestigationUnit() {
        return investigationUnit;
    }

    public void setInvestigationUnit(String investigationUnit) {
        this.investigationUnit = investigationUnit;
    }

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Boolean getIsProsecute() {
        return isProsecute;
    }

    public void setIsProsecute(Boolean isProsecute) {
        this.isProsecute = isProsecute;
    }
}

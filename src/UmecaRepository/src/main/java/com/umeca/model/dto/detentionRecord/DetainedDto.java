package com.umeca.model.dto.detentionRecord;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetainedDto implements EntityGrid {

    private Long id;
    private String initDateStr;
    private Long initDateL;
    private Date initDate;
    private String initTimeStr;
    private Long initTimeL;
    private Long timestamp;
    private Time initTime;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;
    private String idFolder;
    private String age;
    private String investigationUnit;
    private String crime;
    private String district;
    private Long districtId;
    private Boolean isProsecute;
    private String prosecute;
    private Long nowL;

    public DetainedDto() {
    }

    //grid
    public DetainedDto(Long id, Calendar registerTimestamp, String name, String lastNameP, String lastNameM, Date initDate, Date initTime,
                       String idFolder, String age, String investigationUnit, String crime, String district, Boolean isProsecute) {
        SimpleDateFormat fDate = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat fTime = new SimpleDateFormat("HH:mm:ss");

        this.id = id;

        if (initDate != null) {
            this.initDateStr = fDate.format(initDate);
            this.initDateL = initDate.getTime();
            this.initDate = initDate;
        }

        if (initTime != null) {
            this.initTime = new Time(initTime.getTime());
            this.initTimeStr = fTime.format(initTime);
            this.initTimeL = initTime.getTime();
        }

        this.fullName = name + " " + lastNameP + " " + lastNameM;
        this.idFolder = idFolder;
        this.age = age;
        this.investigationUnit = investigationUnit;
        this.crime = crime;
        this.district = district;
        this.isProsecute = isProsecute;
        if (this.isProsecute != null && this.isProsecute.equals(true))
            this.prosecute = "Si";
        else
            this.prosecute = "No";
        this.nowL = new Date().getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInitDateStr() {
        return initDateStr;
    }

    public void setInitDateStr(String initDateStr) {
        this.initDateStr = initDateStr;
    }

    public Long getInitDateL() {
        return initDateL;
    }

    public void setInitDateL(Long initDateL) {
        this.initDateL = initDateL;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public String getInitTimeStr() {
        return initTimeStr;
    }

    public void setInitTimeStr(String initTimeStr) {
        this.initTimeStr = initTimeStr;
    }

    public Long getInitTimeL() {
        return initTimeL;
    }

    public void setInitTimeL(Long initTimeL) {
        this.initTimeL = initTimeL;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Time getInitTime() {
        return initTime;
    }

    public void setInitTime(Time initTime) {
        this.initTime = initTime;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Boolean getIsProsecute() {
        return isProsecute;
    }

    public void setIsProsecute(Boolean isProsecute) {
        this.isProsecute = isProsecute;
    }

    public String getProsecute() {
        return prosecute;
    }

    public void setProsecute(String prosecute) {
        this.prosecute = prosecute;
    }

    public Long getNowL() {
        return nowL;
    }

    public void setNowL(Long nowL) {
        this.nowL = nowL;
    }
}

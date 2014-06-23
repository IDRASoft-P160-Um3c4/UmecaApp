package com.umeca.model.entities.supervisor;

import java.util.Date;
import java.util.List;

public class FramingPersonalDataView {

    private String fullName;
    private Boolean gender;
    private Long maritalStatus;
    private Integer maritalStatusYears;

    private Long idContry;
    private String state;
    private Date brthDate;

    private List<Long> physicalConditionsSel;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Long getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Long maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getMaritalStatusYears() {
        return maritalStatusYears;
    }

    public void setMaritalStatusYears(Integer maritalStatusYears) {
        this.maritalStatusYears = maritalStatusYears;
    }

    public Long getIdContry() {
        return idContry;
    }

    public void setIdContry(Long idContry) {
        this.idContry = idContry;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Long> getPhysicalConditionsSel() {
        return physicalConditionsSel;
    }

    public void setPhysicalConditionsSel(List<Long> physicalConditionsSel) {
        this.physicalConditionsSel = physicalConditionsSel;
    }

    public Date getBrthDate() {
        return brthDate;
    }

    public void setBrthDate(Date brthDate) {
        this.brthDate = brthDate;
    }

}


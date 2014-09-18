package com.umeca.model.entities.supervisor;

import java.util.Date;
import java.util.List;

public class FramingPersonalDataView {

    private String name;
    private String lastNameP;
    private String lastNameM;
    private Integer gender;
    private Long maritalStatus;
    private String maritalStatusYears;
    private Long birthCountryId;
    private String birthState;
    private Date birthDate;
    private String physicalCondition;
    private Long birthStateId;
    private Boolean isMexico;
    private Integer age;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Long maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatusYears() {
        return maritalStatusYears;
    }

    public void setMaritalStatusYears(String maritalStatusYears) {
        this.maritalStatusYears = maritalStatusYears;
    }

    public Long getBirthCountryId() {
        return birthCountryId;
    }

    public void setBirthCountryId(Long birthCountryId) {
        this.birthCountryId = birthCountryId;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = birthState;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public Long getBirthStateId() {
        return birthStateId;
    }

    public void setBirthStateId(Long birthStateId) {
        this.birthStateId = birthStateId;
    }

    public Boolean getIsMexico() {
        return isMexico;
    }

    public void setIsMexico(Boolean isMexico) {
        this.isMexico = isMexico;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

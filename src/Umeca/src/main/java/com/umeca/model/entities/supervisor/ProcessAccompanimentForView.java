package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.dto.LocationDto;

public class ProcessAccompanimentForView {

    private Long idCase;

    private String name;

    private String lastNameP;

    private String lastNameM;

    private Integer gender;

    private String age;

    private String phone;

    private String celphone;

    private String occName;

    private String occPlace;

    private String occPhone;

    private String street;

    private String outNum;

    private String innNum;

    private LocationDto location;

    private Long idAddres;

    private Long academicLevelId;

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCelphone() {
        return celphone;
    }

    public void setCelphone(String celphone) {
        this.celphone = celphone;
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

    public Long getAcademicLevelId() {
        return academicLevelId;
    }

    public void setAcademicLevelId(Long academicLevelId) {
        this.academicLevelId = academicLevelId;
    }

    public String getOccName() {
        return occName;
    }

    public void setOccName(String occName) {
        this.occName = occName;
    }

    public String getOccPlace() {
        return occPlace;
    }

    public void setOccPlace(String occPlace) {
        this.occPlace = occPlace;
    }

    public String getOccPhone() {
        return occPhone;
    }

    public void setOccPhone(String occPhone) {
        this.occPhone = occPhone;
    }
}
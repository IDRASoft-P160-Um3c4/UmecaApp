package com.umeca.model.entities.supervisor;

public class FramingReferenceInfo {

    private Long id;
    private String name;
    private String phone;
    private String relationship;
    private String address;
    private String age;
    private String occupation;
    private String personType;
    private Boolean isAccompaniment;
    private String isAccompanimentStr;
    private String addressStr;
    private Integer gender;
    private String genderStr;
    private String occupationPlace;
    private String academicLvl;

    public FramingReferenceInfo(Long id, String name, String phone, String relationship, String address, String age, String occupation, String personType, Boolean isAccompaniment, Integer gender, String occupationPlace, String academicLvl, String addressStr) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.relationship = relationship;
        this.address = address;
        this.age = age;
        this.occupation = occupation;
        this.personType = personType;
        this.isAccompaniment = isAccompaniment;
        this.gender = gender;
        this.occupationPlace = occupationPlace;
        this.academicLvl = academicLvl;
        this.addressStr = addressStr;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Boolean getIsAccompaniment() {
        return isAccompaniment;
    }

    public void setIsAccompaniment(Boolean isAccompaniment) {
        this.isAccompaniment = isAccompaniment;
    }

    public String getIsAccompanimentStr() {
        isAccompanimentStr = "";
        if (isAccompaniment != null && isAccompaniment == true)
            isAccompanimentStr = "Si";
        else if (isAccompaniment != null && isAccompaniment == false)
            isAccompanimentStr = "No";
        return isAccompanimentStr;
    }

    public void setIsAccompanimentStr(String isAccompanimentStr) {
        this.isAccompanimentStr = isAccompanimentStr;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderStr() {
        genderStr = "";
        if (gender != null && gender == 1)
            genderStr = "Femenino";
        else if (gender != null && gender == 1)
            genderStr = "Masculino";
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public String getOccupationPlace() {
        return occupationPlace;
    }

    public void setOccupationPlace(String occupationPlace) {
        this.occupationPlace = occupationPlace;
    }

    public String getAcademicLvl() {
        return academicLvl;
    }

    public void setAcademicLvl(String academicLvl) {
        this.academicLvl = academicLvl;
    }
}

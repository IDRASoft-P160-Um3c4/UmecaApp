package com.umeca.model.entities.supervisor;

public class SchoolDto {

    private Long id;
    private Long idCase;
    private String name;
    private String phone;
    private String address;
    private Long degreeId;
    private Long academicLvlId;
    private String schedule;
    private String commentSchool;
    private String specification;
    private Boolean hasActualSchool;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Long degreeId) {
        this.degreeId = degreeId;
    }

    public Long getAcademicLvlId() {
        return academicLvlId;
    }

    public void setAcademicLvlId(Long academicLvlId) {
        this.academicLvlId = academicLvlId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Boolean getHasActualSchool() {
        return hasActualSchool;
    }

    public void setHasActualSchool(Boolean hasActualSchool) {
        this.hasActualSchool = hasActualSchool;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getCommentSchool() {
        return commentSchool;
    }

    public void setCommentSchool(String commentSchool) {
        this.commentSchool = commentSchool;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}

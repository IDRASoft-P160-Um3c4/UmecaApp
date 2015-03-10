package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class EmployeeReferenceDto implements EntityGrid{
    private Long id;
    private Long idEmployee;
    private String name;
    private String age;
    private String phone;
    private Long idRelationship;
    private String relationship;
    private String specRelationship;
    private String timeAgo;

    public EmployeeReferenceDto() {
    }

    //grid
    public EmployeeReferenceDto(Long id, String name, String relationship, String age, String phone, String timeAgo) {
        this.name = name;
        this.id = id;
        this.relationship = relationship;
        this.age = age;
        this.phone = phone;
        this.timeAgo = timeAgo;
    }

    //upsert
    public EmployeeReferenceDto(Long id, Long idEmployee, String name, String age, String phone, Long idRelationship, String specRelationship, String timeAgo) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.idRelationship = idRelationship;
        this.specRelationship = specRelationship;
        this.timeAgo = timeAgo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getIdRelationship() {
        return idRelationship;
    }

    public void setIdRelationship(Long idRelationship) {
        this.idRelationship = idRelationship;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getSpecRelationship() {
        return specRelationship;
    }

    public void setSpecRelationship(String specRelationship) {
        this.specRelationship = specRelationship;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }
}

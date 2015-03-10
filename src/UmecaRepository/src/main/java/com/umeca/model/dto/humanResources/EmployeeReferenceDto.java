package com.umeca.model.dto.humanResources;

import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

public class EmployeeReferenceDto {
    private Long id;

    private Long idEmployee;
    private String name;
    private String phone;
    private String timeAgo;
    private String address;
    private String addressRef;
    private String age;
    private String occupation;
    private Long idRelationship;
    private String specRelationship;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = addressRef;
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

    public Long getIdRelationship() {
        return idRelationship;
    }

    public void setIdRelationship(Long idRelationship) {
        this.idRelationship = idRelationship;
    }

    public String getSpecRelationship() {
        return specRelationship;
    }

    public void setSpecRelationship(String specRelationship) {
        this.specRelationship = specRelationship;
    }
}

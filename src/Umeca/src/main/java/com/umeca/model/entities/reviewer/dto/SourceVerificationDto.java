package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.entities.reviewer.SourceVerification;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 2/07/14
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class SourceVerificationDto {
    private Long id;
    private String fullName;
    private Integer age;
    private String relationship;
    private String address;
    private String phone;

    public SourceVerificationDto dtoSourceVerification(SourceVerification sv){
        this.id = sv.getId();
        this.fullName = sv.getFullName();
        this.age = sv.getAge();
        if(sv.getRelationship()!=null){
            this.relationship = sv.getRelationship().getName();
        }else{
            this.relationship="No proporcionado";
        }
        this.address= sv.getAddress();
        this.phone = sv.getPhone();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

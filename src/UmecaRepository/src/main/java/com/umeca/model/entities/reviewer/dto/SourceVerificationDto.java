package com.umeca.model.entities.reviewer.dto;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.entities.reviewer.SourceVerification;
import com.umeca.model.entities.reviewer.View.Section;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 2/07/14
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class SourceVerificationDto implements EntityGrid{
    private Long id;
    private String fullName;
    private String age;
    private String relationship;
    private String address;
    private String phone;
    private List<Section> sections;

    public SourceVerificationDto(){
    }

    public SourceVerificationDto(Long id, String fullName, String relationship, String address){
        this.id = id;
        this.fullName = fullName;
        this.relationship = relationship;
        this.address= address;
    }

    public SourceVerificationDto dtoSourceVerification(SourceVerification sv){
        this.id = sv.getId();
        this.fullName = sv.getFullName();
        this.age = sv.getAge();
        if(sv.getRelationship()!=null){
            this.relationship = sv.getRelationship().getName();
            if(sv.getRelationship().getSpecification()){
                this.relationship += ": "+sv.getSpecification();
            }
        }else{
            this.relationship="No aplica";
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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

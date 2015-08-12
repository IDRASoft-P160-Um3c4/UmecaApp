package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.entities.reviewer.Reference;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 2/07/14
 * Time: 02:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReferenceDto {
    Long id;
    Long relId;
    Long docId;
    String fullName;
    String phone;
    String age;
    String specification;
    String address;
    String specificationRelationship;

    public ReferenceDto dtoReference(Reference r){
        id = r.getId();
        if(r.getRelationship()!=null)
            relId = r.getRelationship().getId();
        if(r.getDocumentType()!=null)
            docId= r.getDocumentType().getId();
        fullName = r.getFullName();
        phone = r.getPhone();
        age = r.getAge();
        specificationRelationship = r.getSpecificationRelationship();
        specification = r.getSpecification();
        address = r.getAddress();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

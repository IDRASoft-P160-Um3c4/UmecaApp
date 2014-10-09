package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.entities.reviewer.PersonSocialNetwork;
import com.umeca.model.entities.reviewer.SocialNetwork;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 2/07/14
 * Time: 02:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class SocialNetworkDto {
    Long id;
    Long relId;
    Long docId;
    Long livId;
    Long depId;
    String name;
    String phone;
    Integer age;
    String specification;
    String address;

    public SocialNetworkDto dtoSocialNetwork(PersonSocialNetwork p){
        this.id=p.getId();
        if(p.getRelationship()!=null)
            this.relId=p.getRelationship().getId();
        if(p.getDocumentType()!=null)
            this.docId = p.getDocumentType().getId();
        if(p.getLivingWith()!=null)
            this.livId = p.getLivingWith().getId();
        if(p.getDependent()!=null)
            this.depId = p.getDependent().getId();
        this.name = p.getName();
        this.phone = p.getPhone();
        this.age = p.getAge();
        this.specification = p.getSpecification();
        this.address = p.getAddress();
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

    public Long getLivId() {
        return livId;
    }

    public void setLivId(Long livId) {
        this.livId = livId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
}

package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletDocumentTypeDto;
import com.umeca.model.dto.tablet.catalog.TabletElectionDto;
import com.umeca.model.dto.tablet.catalog.TabletRelationshipDto;

public class TabletPersonSocialNetworkDto{

    public TabletPersonSocialNetworkDto(){}

    public TabletPersonSocialNetworkDto(Long id, String name, Integer age, String phone, String address, String specification, Boolean isAccompaniment, String specificationRelationship, Boolean block) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.specification = specification;
        this.isAccompaniment = isAccompaniment;
        this.specificationRelationship = specificationRelationship;
        this.block = block;
    }

    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String address;
    private String specification;
    private Boolean isAccompaniment;
    private String specificationRelationship;
    private Boolean block;

    private TabletRelationshipDto relationship;
    private TabletDocumentTypeDto documentType;
    private TabletElectionDto dependent;
    private TabletElectionDto livingWith;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Boolean getIsAccompaniment() {
        return isAccompaniment;
    }

    public void setIsAccompaniment(Boolean isAccompaniment) {
        this.isAccompaniment = isAccompaniment;
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public TabletRelationshipDto getRelationship() {
        return relationship;
    }

    public void setRelationship(TabletRelationshipDto relationship) {
        this.relationship = relationship;
    }

    public TabletDocumentTypeDto getDocumentType() {
        return documentType;
    }

    public void setDocumentType(TabletDocumentTypeDto documentType) {
        this.documentType = documentType;
    }

    public TabletElectionDto getDependent() {
        return dependent;
    }

    public void setDependent(TabletElectionDto dependent) {
        this.dependent = dependent;
    }

    public TabletElectionDto getLivingWith() {
        return livingWith;
    }

    public void setLivingWith(TabletElectionDto livingWith) {
        this.livingWith = livingWith;
    }
}

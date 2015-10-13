package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletDocumentTypeDto;
import com.umeca.model.dto.tablet.catalog.TabletRelationshipDto;

public class TabletReferenceDto {

    public TabletReferenceDto(Long id, String fullName, String age, String address, String phone, String specification, Boolean isAccompaniment, String specificationRelationship, Boolean block,
                              Long idDT, String nameDT, Boolean isObsoleteDT, Boolean specificationDT,
                              Long idR, String nameR, Boolean isObsoleteR, Boolean specificationR) {
        this.id = id;
        this.webId = id;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.specification = specification;
        this.isAccompaniment = isAccompaniment;
        this.specificationRelationship = specificationRelationship;
        this.block = block;

        if (idDT != null) {
            this.documentType = new TabletDocumentTypeDto(idDT, nameDT, isObsoleteDT, specificationDT);
        }

        if (idR != null) {
            this.relationship = new TabletRelationshipDto(idR, nameR, isObsoleteR, specificationR);
        }
    }

    private Long webId;
    private Long id;
    private String fullName;
    private String age;
    private String address;
    private String phone;
    private String specification;
    private Boolean isAccompaniment;
    private String specificationRelationship;
    private Boolean block;
    private TabletDocumentTypeDto documentType;
    private TabletRelationshipDto relationship;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public TabletDocumentTypeDto getDocumentType() {
        return documentType;
    }

    public void setDocumentType(TabletDocumentTypeDto documentType) {
        this.documentType = documentType;
    }

    public TabletRelationshipDto getRelationship() {
        return relationship;
    }

    public void setRelationship(TabletRelationshipDto relationship) {
        this.relationship = relationship;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }
}

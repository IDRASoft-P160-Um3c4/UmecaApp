package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelSocialNetworkDto {

    private Long idCase;
    private String name;
    private String relationship;
    private String age;
    private String document;
    private String dependent;
    private String livingWith;
    private Boolean accompaniment;
    private String accompanimentStr;
    private String phone;
    private String address;
    private String specificationRelationship;
    private Boolean block;

    public ExcelSocialNetworkDto(Long idCase, String name, String relationship, String age, String document, String dependent, String livingWith, Boolean accompaniment, String phone, String address, String specificationRelationship, Boolean block) {
        this.idCase = idCase;
        this.name = name;
        this.relationship = relationship;
        this.age = age;
        this.document = document;
        this.dependent = dependent;
        this.livingWith = livingWith;
        this.accompaniment = accompaniment;
        this.phone = phone;
        this.address = address;
        if (specificationRelationship != null && !specificationRelationship.equals("")) {
            this.relationship += ": " + specificationRelationship;
        }
        this.block = block;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDependent() {
        return dependent;
    }

    public void setDependent(String dependent) {
        this.dependent = dependent;
    }

    public String getLivingWith() {
        return livingWith;
    }

    public void setLivingWith(String livingWith) {
        this.livingWith = livingWith;
    }

    public Boolean getAccompaniment() {
        return accompaniment;
    }

    public void setAccompaniment(Boolean accompaniment) {
        this.accompaniment = accompaniment;
    }

    public String getAccompanimentStr() {

        this.accompanimentStr = "";

        if (this.accompaniment == true)
            this.accompanimentStr = "Si";
        else
            this.accompanimentStr = "No";

        return accompanimentStr;
    }

    public void setAccompanimentStr(String accompanimentStr) {
        this.accompanimentStr = accompanimentStr;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

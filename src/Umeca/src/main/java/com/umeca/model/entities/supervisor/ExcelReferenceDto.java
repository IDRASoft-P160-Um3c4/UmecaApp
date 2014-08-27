package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelReferenceDto {

    private Long idCase;
    private String name;
    private Integer age;
    private String document;
    private String relationship;
    private String address;
    private String phone;
    private Boolean accompaniment;
    private String accompanimentStr;

    public ExcelReferenceDto(Long idCase, String name, Integer age, String document, String relationship, String address, String phone, Boolean accompaniment) {
        this.idCase = idCase;
        this.name = name;
        this.age = age;
        this.document = document;
        this.relationship = relationship;
        this.address = address;
        this.phone = phone;
        this.accompaniment = accompaniment;
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

    public Boolean getAccompaniment() {
        return accompaniment;
    }

    public void setAccompaniment(Boolean accompaniment) {
        this.accompaniment = accompaniment;
    }

    public String getAccompanimentStr() {

        if (this.accompaniment == true)
            accompanimentStr = "Si";
        else
            accompanimentStr = "No";

        return accompanimentStr;
    }

    public void setAccompanimentStr(String accompanimentStr) {
        this.accompanimentStr = accompanimentStr;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}

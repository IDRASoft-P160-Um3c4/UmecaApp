package com.umeca.model.entities.supervisor;

public class FramingReferenceDto {

    private Long id;
    private String name;
    private String phone;
    private Long relationshipId;
    private String address;
    private String age;
    private String occupation;
    private Long idCase;

    public FramingReferenceDto(){

    }

    public FramingReferenceDto(FramingReference ref){
        this.id=ref.getId();
        this.name=ref.getName();
        this.phone=ref.getPhone();
        this.relationshipId=ref.getRelationship().getId();
        this.address=ref.getAddress();
        this.age=ref.getAge();
        this.setOccupation(ref.getOccupation());
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

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId =  relationshipId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

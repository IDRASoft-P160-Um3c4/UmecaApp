package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.EntityGrid;

import javax.persistence.*;

@Entity
@Table(name = "framing_reference")
public class FramingReference implements EntityGrid {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_reference")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private String age;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "person_type")
    private String personType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_framing_meeting")
    private FramingMeeting framingMeeting;

    @Transient
    private Long idCase;

    public FramingReference() {

    }

    public FramingReference(Long id, String name, String phone, String relationship, String address, String type) {

        this.id = id;
        this.name = name;
        this.relationship = relationship;

        if (type.equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {
            this.age = phone;
            this.occupation= address;
        } else if (type.equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {
            this.phone = phone;
            this.address = address;
        }

        this.personType=type;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
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

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    @Override
    public String toString() {
        String toStr="";

        if  (this.personType.equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)){
            toStr=this.name+", parentesco "+this.relationship+", vive con el imputado.";
        }
        else if  (this.personType.equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {
            toStr = this.name + ", parentesco " + this.relationship + ", teléfono "+this.phone+", dirección "+this.address;
        }

        return toStr;
    }
}

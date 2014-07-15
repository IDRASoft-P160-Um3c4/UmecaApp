package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Relationship;
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

    @OneToOne
    @JoinColumn(name = "id_relationship")
    private Relationship relationship;

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private String age;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "person_type")
    private String personType;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting")
    private FramingMeeting framingMeeting;

    @Transient
    private Long idCase;

    @Transient
    private String relationshipName;

    @Transient
    private Long relationshipId;

    public FramingReference() {
    }

    public FramingReference(Long id, String name, String phone, String relationship, String address, String type) {

        this.id = id;
        this.name = name;
        this.relationshipName = relationship;

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

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }
}

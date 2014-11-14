package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.dto.LocationDto;
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

    @Column(name = "time_ago")
    private String timeAgo;

    @Column(name = "address")
    private String address;

    @Column(name = "address_ref")
    private String addressRef;

    @Column(name = "age")
    private String age;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "person_type")
    private String personType;

    @Column(name = "is_accompaniment")
    private Boolean isAccompaniment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_accompaniment_info")
    private AccompanimentInfo accompanimentInfo;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting")
    private FramingMeeting framingMeeting;

    @Column(name = "specification_relationship", length = 255, nullable = true)
    private String specificationRelationship;

    @Transient
    private Long idCase;

    @Transient
    private String relationshipName;

    @Transient
    private Long relationshipId;

    @Transient
    private Long academicLvlId;

    @Transient
    private String occupationPlace;

    @Transient
    private Integer gender;

    @Transient
    private Long addressId;

    @Transient
    private String lat;

    @Transient
    private String lng;

    @Transient
    private String streetComponent;

    @Transient
    private String outNumComponent;

    @Transient
    private String innNumComponent;

    @Transient
    private LocationDto location;

    @Transient
    private String isAccompanimentString;


    public FramingReference() {
    }

    public FramingReference(Long id, String name, String phone, String relationship, String addressA, String addressB, String type, String specificationRelationship, Boolean isAccompaniment) {

        this.id = id;
        this.name = name;
        this.relationshipName = relationship;

        if (type.equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {
            this.age = phone;
            this.occupation = address;
        } else if (type.equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {
            this.phone = phone;

            if ((addressA != null && !addressA.equals("")) && (addressB == null || addressB.equals("")))
                this.address = addressA;
            else if ((addressB != null && !addressB.equals("")) && (addressA == null || addressB.equals("")))
                this.address = addressB;
        }
        if (specificationRelationship != null && !specificationRelationship.equals("")) {
            relationshipName += ": " + specificationRelationship;
        }
        this.personType = type;
        this.isAccompanimentString = isAccompaniment ? "Si" : "No";
    }

    public FramingReference(Long id, String name, String phone, String relationship, String address, String type, String specificationRelationship, Boolean isAccompaniment) {

        this.id = id;
        this.name = name;
        this.relationshipName = relationship;

        if (type.equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {
            this.age = phone;
            this.occupation = address;
        } else if (type.equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {
            this.phone = phone;

            this.address = address;
        }
        if (specificationRelationship != null && !specificationRelationship.equals("")) {
            relationshipName += ": " + specificationRelationship;
        }
        this.personType = type;
        this.isAccompanimentString = isAccompaniment ? "Si" : "No";
    }

    public FramingReference(Long id, String name, String age, String relationship, String phone, String address, String type) {
        this.id = id;
        this.name = name;
        this.relationshipName = relationship;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.personType = type;
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

    public Boolean getIsAccompaniment() {
        return isAccompaniment;
    }

    public void setIsAccompaniment(Boolean isAccompaniment) {
        this.isAccompaniment = isAccompaniment;
    }

    public AccompanimentInfo getAccompanimentInfo() {
        return accompanimentInfo;
    }

    public void setAccompanimentInfo(AccompanimentInfo accompanimentInfo) {
        this.accompanimentInfo = accompanimentInfo;
    }

    public Long getAcademicLvlId() {
        return academicLvlId;
    }

    public void setAcademicLvlId(Long academicLvlId) {
        this.academicLvlId = academicLvlId;
    }

    public String getOccupationPlace() {
        return occupationPlace;
    }

    public void setOccupationPlace(String occupationPlace) {
        this.occupationPlace = occupationPlace;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getStreetComponent() {
        return streetComponent;
    }

    public void setStreetComponent(String streetComponent) {
        this.streetComponent = streetComponent;
    }

    public String getOutNumComponent() {
        return outNumComponent;
    }

    public void setOutNumComponent(String outNumComponent) {
        this.outNumComponent = outNumComponent;
    }

    public String getInnNumComponent() {
        return innNumComponent;
    }

    public void setInnNumComponent(String innNumComponent) {
        this.innNumComponent = innNumComponent;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = addressRef;
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }

    public String getIsAccompanimentString() {
        return isAccompanimentString;
    }

    public void setIsAccompanimentString(String accompanimentString) {
        isAccompanimentString = accompanimentString;
    }
}

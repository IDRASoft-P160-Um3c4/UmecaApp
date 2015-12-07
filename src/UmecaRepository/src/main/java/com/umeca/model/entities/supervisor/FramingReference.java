package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.dto.LocationDto;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

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

    @Column(name = "has_victim_witness_info")
    private Boolean hasVictimWitnessInfo;

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
        this.name = StringExt.substringMax(name, Constants.DEFAULT_LEN_STRING);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = StringExt.substringMax(phone, Constants.DEFAULT_LEN_STRING);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = StringExt.substringMax(address, Constants.DEFAULT_LEN_STRING);
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
        this.age = StringExt.substringMax(age, Constants.DEFAULT_LEN_STRING);
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = StringExt.substringMax(occupation, Constants.DEFAULT_LEN_STRING);
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = StringExt.substringMax(personType, Constants.DEFAULT_LEN_STRING);
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
        this.timeAgo =  StringExt.substringMax(timeAgo, Constants.DEFAULT_LEN_STRING);
    }

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = StringExt.substringMax(addressRef, Constants.DEFAULT_LEN_STRING);
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = StringExt.substringMax(specificationRelationship, Constants.DEFAULT_LEN_STRING);
    }

    public String getIsAccompanimentString() {
        return isAccompanimentString;
    }

    public void setIsAccompanimentString(String accompanimentString) {
        isAccompanimentString = accompanimentString;
    }

    public Boolean getHasVictimWitnessInfo() {
        return hasVictimWitnessInfo;
    }

    public void setHasVictimWitnessInfo(Boolean hasVictimWitnessInfo) {
        this.hasVictimWitnessInfo = hasVictimWitnessInfo;
    }
}

package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.dto.LocationDto;

public class FramingReferenceDto {

    private Long id;
    private String name;
    private String phone;
    private Long relationshipId;
    private String address;
    private String age;
    private String occupation;
    private Long idCase;
    private Boolean isAccompaniment;
    private LocationDto location;
    private Long academicLvlId;
    private String occupationPlace;
    private Integer gender;
    private Long addressId;
    private String timeAgo;
    private String addressRef;

    public FramingReferenceDto() {

    }

    public FramingReferenceDto(FramingReference ref) {
        this.id = ref.getId();
        this.name = ref.getName();
        this.phone = ref.getPhone();
        this.relationshipId = ref.getRelationship().getId();
        this.address = ref.getAddress();
        this.age = ref.getAge();
        this.isAccompaniment = ref.getIsAccompaniment();
        this.timeAgo = ref.getTimeAgo();
        addressRef = ref.getAddressRef();

        if (ref.getPersonType() != null && ref.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {

            if (ref.getAccompanimentInfo() != null) {
                this.location = new LocationDto();

                if (ref.getAccompanimentInfo().getAddress() != null) {
                    this.location.setId(ref.getAccompanimentInfo().getAddress().getLocation().getId());
                }

                if (ref.getAccompanimentInfo().getAcademicLevel() != null)
                    this.academicLvlId = ref.getAccompanimentInfo().getAcademicLevel().getId();

                this.occupationPlace = ref.getAccompanimentInfo().getOccupationPlace();
                this.gender = ref.getAccompanimentInfo().getGender();

            }

        } else if (ref.getPersonType() != null && ref.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {

            if (ref.getAccompanimentInfo() != null) {
                this.location = new LocationDto();

                if (ref.getAccompanimentInfo().getAddress() != null) {
                    this.location.setId(ref.getAccompanimentInfo().getAddress().getLocation().getId());
                    this.addressId = ref.getAccompanimentInfo().getAddress().getId();
                }

                if (ref.getAccompanimentInfo().getAcademicLevel() != null)
                    this.academicLvlId = ref.getAccompanimentInfo().getAcademicLevel().getId();

                this.occupationPlace = ref.getAccompanimentInfo().getOccupationPlace();
                this.gender = ref.getAccompanimentInfo().getGender();

            }

        }

        this.occupation = ref.getOccupation();
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
        this.relationshipId = relationshipId;
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

    public Boolean getIsAccompaniment() {
        return isAccompaniment;
    }

    public void setIsAccompaniment(Boolean isAccompaniment) {
        this.isAccompaniment = isAccompaniment;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
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
}

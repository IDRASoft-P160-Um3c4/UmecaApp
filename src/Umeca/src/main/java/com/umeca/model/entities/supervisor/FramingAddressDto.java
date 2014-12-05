package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.dto.LocationDto;
import com.umeca.model.entities.reviewer.Address;

public class FramingAddressDto {

    private Long id;
    private String street;
    private String outNum;
    private String innNum;
    private String zipCode;
    private String lat;
    private String lng;
    private Long idCase;
    private LocationDto location;
    private String addressRef;
    private String schedule;
    private String timeAgo;
    private String timeLive;
    private String reasonAnother;
    private String phone;
    private String reasonChange;
    private String specification;
    private Long registerTypeId;
    private Long homeTypeId;

    private String addressStr;
    private String registerTypeStr;
    private String homeTypeStr;

    public FramingAddressDto framingAddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.outNum = address.getOutNum();
        this.innNum = address.getInnNum();
        this.lat = address.getLat();
        this.lng = address.getLng();
        if (address.getLocation() != null) {
            this.zipCode = address.getLocation().getZipCode();
        }
        return this;
    }

    public FramingAddressDto() {
    }

    public FramingAddressDto(Long id, String addressString, String addressRef, String timeAgo, String timeLive,
                             String reasonAnother, String phone, String reasonChange, String specification,
                             String registerType, String homeType) {
        this.id = id;
        this.addressStr = addressString;
        this.addressRef = addressRef;
        this.timeAgo = timeAgo;
        this.timeLive = timeLive;
        this.reasonAnother = reasonAnother;
        this.phone = phone;
        this.reasonChange = reasonChange;
        this.specification = specification;
        this.registerTypeStr = registerType;
        this.homeTypeStr = homeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getInnNum() {
        return innNum;
    }

    public void setInnNum(String innNum) {
        this.innNum = innNum;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
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

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = addressRef;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(String timeLive) {
        this.timeLive = timeLive;
    }

    public String getReasonAnother() {
        return reasonAnother;
    }

    public void setReasonAnother(String reasonAnother) {
        this.reasonAnother = reasonAnother;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getRegisterTypeId() {
        return registerTypeId;
    }

    public void setRegisterTypeId(Long registerTypeId) {
        this.registerTypeId = registerTypeId;
    }

    public Long getHomeTypeId() {
        return homeTypeId;
    }

    public void setHomeTypeId(Long homeTypeId) {
        this.homeTypeId = homeTypeId;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public String getRegisterTypeStr() {
        return registerTypeStr;
    }

    public void setRegisterTypeStr(String registerTypeStr) {
        this.registerTypeStr = registerTypeStr;
    }

    public String getHomeTypeStr() {
        return homeTypeStr;
    }

    public void setHomeTypeStr(String homeTypeStr) {
        this.homeTypeStr = homeTypeStr;
    }
}

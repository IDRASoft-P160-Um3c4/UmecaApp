package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletRelationshipDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TabletSourceVerificationDto {

    public TabletSourceVerificationDto(Long id, String fullName, Integer age, String address, String phone, Boolean isAuthorized, Date dateComplete, Date dateAuthorized, String specification, Boolean visible,
                                       Long idVM, String nameVM, Boolean isObsoleteVM,
                                       Long idR, String nameR, Boolean isObsoleteR, Boolean specificationR) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.webId = id;
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.isAuthorized = isAuthorized;
        this.dateComplete = dateComplete == null ? null : sdf.format(dateComplete);
        this.dateAuthorized = dateAuthorized == null ? null : sdf.format(dateAuthorized);
        this.specification = specification;
        this.visible = visible;

        if (idVM != null) {
            this.verificationMethod = new TabletVerificationMethodDto(idVM, nameVM, isObsoleteVM);
        }

        if (idR != null) {
            this.relationship = new TabletRelationshipDto(idR, nameR, isObsoleteR, specificationR);
        }
    }

    private Long webId;
    private Long id;
    private String fullName;
    private Integer age;
    private String address;
    private String phone;
    private Boolean isAuthorized;
    private String dateComplete;
    private String dateAuthorized;
    private String specification;
    private Boolean visible;
    private TabletVerificationMethodDto verificationMethod;
    private TabletRelationshipDto relationship;

    private List<TabletFieldMeetingSourceDto> fieldMeetingSourceList;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public String getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(String dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getDateAuthorized() {
        return dateAuthorized;
    }

    public void setDateAuthorized(String dateAuthorized) {
        this.dateAuthorized = dateAuthorized;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public TabletVerificationMethodDto getVerificationMethod() {
        return verificationMethod;
    }

    public void setVerificationMethod(TabletVerificationMethodDto verificationMethod) {
        this.verificationMethod = verificationMethod;
    }

    public TabletRelationshipDto getRelationship() {
        return relationship;
    }

    public void setRelationship(TabletRelationshipDto relationship) {
        this.relationship = relationship;
    }

    public List<TabletFieldMeetingSourceDto> getFieldMeetingSourceList() {
        return fieldMeetingSourceList;
    }

    public void setFieldMeetingSourceList(List<TabletFieldMeetingSourceDto> fieldMeetingSourceList) {
        this.fieldMeetingSourceList = fieldMeetingSourceList;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }
}

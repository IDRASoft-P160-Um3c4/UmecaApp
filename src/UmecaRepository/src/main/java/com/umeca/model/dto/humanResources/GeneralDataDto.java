package com.umeca.model.dto.humanResources;

import com.umeca.model.catalog.dto.LocationDto;

import java.util.Date;

/**
 * Created by Vmware on 05/03/2015.
 */
public class GeneralDataDto {

    private String name;
    private String lastNameP;
    private String lastNameM;
    private Boolean gender;
    private Date birthDate;
    private Long maritalStatusId;
    private Long documentId;
    private String documentDesc;
    private String email;
    private String phone;
    private String certificate;
    private String dependents;
    private String noEmployee;
    private String datePublicServ;
    private String dateEntryUmeca;
    private Boolean commissioner;
    private String noSocial;
    private String appointment;
    private String street;
    private String outNum;
    private String innNum;
    private LocationDto location;
    private Long idAddres;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Long maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentDesc() {
        return documentDesc;
    }

    public void setDocumentDesc(String documentDesc) {
        this.documentDesc = documentDesc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDependents() {
        return dependents;
    }

    public void setDependents(String dependents) {
        this.dependents = dependents;
    }

    public String getNoEmployee() {
        return noEmployee;
    }

    public void setNoEmployee(String noEmployee) {
        this.noEmployee = noEmployee;
    }

    public String getDatePublicServ() {
        return datePublicServ;
    }

    public void setDatePublicServ(String datePublicServ) {
        this.datePublicServ = datePublicServ;
    }

    public String getDateEntryUmeca() {
        return dateEntryUmeca;
    }

    public void setDateEntryUmeca(String dateEntryUmeca) {
        this.dateEntryUmeca = dateEntryUmeca;
    }

    public Boolean getCommissioner() {
        return commissioner;
    }

    public void setCommissioner(Boolean commissioner) {
        this.commissioner = commissioner;
    }

    public String getNoSocial() {
        return noSocial;
    }

    public void setNoSocial(String noSocial) {
        this.noSocial = noSocial;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
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

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Long getIdAddres() {
        return idAddres;
    }

    public void setIdAddres(Long idAddres) {
        this.idAddres = idAddres;
    }
}

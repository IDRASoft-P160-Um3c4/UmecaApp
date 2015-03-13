package com.umeca.model.dto.humanResources;

import com.umeca.model.catalog.dto.LocationDto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vmware on 05/03/2015.
 */
public class EmployeeGeneralDataDto {
    private String name;
    private String lastNameP;
    private String lastNameM;
    private Boolean gender;
    private String birthDate;
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
    private String noImss;
    private String appointment;
    private String street;
    private String outNum;
    private String innNum;
    private LocationDto location;
    private String lat;
    private String lng;
    private Long idEmployee;
    private Long idAddres;

    public EmployeeGeneralDataDto() {

    }

    public EmployeeGeneralDataDto(Long idEmployee, String name, String lastNameP, String lastNameM, Boolean gender, Date birthDate, Long maritalStatusId, Long documentId, String email, String documentDesc, String phone, String certificate,
                                  String dependents, String noEmployee, Date datePublicServ, Date dateEntryUmeca, Boolean commissioner, String noImss, String appointment, Long idAddres) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.idEmployee = idEmployee;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.gender = gender;

        try {
            this.birthDate = birthDate != null ? sdf.format(birthDate) : null;
            this.datePublicServ = datePublicServ != null ? sdf.format(datePublicServ) : null;
            this.dateEntryUmeca = dateEntryUmeca != null ? sdf.format(dateEntryUmeca) : null;
        } catch (Exception e) {

        }
        this.maritalStatusId = maritalStatusId;
        this.documentId = documentId;
        this.documentDesc = documentDesc;
        this.email = email;
        this.phone = phone;
        this.certificate = certificate;
        this.dependents = dependents;
        this.noEmployee = noEmployee;
        this.commissioner = commissioner;
        this.noImss = noImss;
        this.appointment = appointment;
        this.idAddres = idAddres;

    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    public String getNoImss() {
        return noImss;
    }

    public void setNoImss(String noImss) {
        this.noImss = noImss;
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

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }
}

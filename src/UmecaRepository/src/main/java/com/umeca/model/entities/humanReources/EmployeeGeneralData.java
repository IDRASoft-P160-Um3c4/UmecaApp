package com.umeca.model.entities.humanReources;

import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.MaritalStatus;
import com.umeca.model.catalog.dto.LocationDto;
import com.umeca.model.entities.reviewer.Address;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Rata on 05/03/2015.
 */
@Entity
@Table(name = "employee_general_data")
public class EmployeeGeneralData {

    @Id
    @GeneratedValue
    @Column(name = "id_employee_general_data")
    private Long id;

    @Column(name = "identification_desc")
    private String identificationDesc;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "dependents")
    private String dependents;

    @Column(name = "no_employee")
    private String noEmployee;

    @Column(name = "no_imss")
    private String noImss;

    @Column(name = "appointment")
    private String appointment;

    @Column(name = "isCommissioner")
    private Boolean isCommissioner;

    @Column(name = "date_public_serv")
    private Date datePublicServ;

    @Column(name = "date_entry_umeca")
    private Date dateEntryUmeca;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marital_status")
    private MaritalStatus maritalStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_document_type")
    private DocumentType identification;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationDesc() {
        return identificationDesc;
    }

    public void setIdentificationDesc(String identificationDesc) {
        this.identificationDesc = identificationDesc;
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

    public Boolean getIsCommissioner() {
        return isCommissioner;
    }

    public void setIsCommissioner(Boolean isCommissioner) {
        this.isCommissioner = isCommissioner;
    }

    public Date getDatePublicServ() {
        return datePublicServ;
    }

    public void setDatePublicServ(Date datePublicServ) {
        this.datePublicServ = datePublicServ;
    }

    public Date getDateEntryUmeca() {
        return dateEntryUmeca;
    }

    public void setDateEntryUmeca(Date dateEntryUmeca) {
        this.dateEntryUmeca = dateEntryUmeca;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public DocumentType getIdentification() {
        return identification;
    }

    public void setIdentification(DocumentType identification) {
        this.identification = identification;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

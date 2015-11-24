package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.catalog.Country;
import com.umeca.model.catalog.InformationAvailability;
import com.umeca.model.catalog.MaritalStatus;
import com.umeca.model.catalog.State;
import com.umeca.model.shared.Constants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "framing_imputed_personal_data")
public class FramingImputedPersonalData {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_imputed_personal_data")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name_p")
    private String lastNameP;

    @Column(name = "last_name_m")
    private String lastNameM;

    @Column(name = "gender")
    private Integer gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "marital_status_years")
    private String maritalStatusYears;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country")
    private Country birthCountry;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_state")
    private State birthStateCmb;

    @Column(name = "birth_state")
    private String birthState;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "physical_condition")
    private String physicalCondition;

    @Column(name = "phone")
    private String phone;

    @Column(name = "cel_phone")
    private String celPhone;

    @Column(name = "email")
    private String email;

    @Column(name = "social_networking")
    private String socialNetworking;

    @Column(name = "comments")
    private String comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_birth_info_availability",nullable = true)
    protected InformationAvailability birthInfo;

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

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = StringExt.substringMax(lastNameP, Constants.DEFAULT_LEN_STRING);
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = StringExt.substringMax(lastNameM, Constants.DEFAULT_LEN_STRING);
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatusYears() {
        return maritalStatusYears;
    }

    public void setMaritalStatusYears(String maritalStatusYears) {
        this.maritalStatusYears = StringExt.substringMax(maritalStatusYears, Constants.DEFAULT_LEN_STRING);
    }

    public Country getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(Country birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getBirthState() {
        return birthState;
    }

    public void setBirthState(String birthState) {
        this.birthState = StringExt.substringMax(birthState, Constants.DEFAULT_LEN_STRING);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = StringExt.substringMax(physicalCondition, Constants.DEFAULT_LEN_STRING);
    }

    public State getBirthStateCmb() {
        return birthStateCmb;
    }

    public void setBirthStateCmb(State birthStateCmb) {
        this.birthStateCmb = birthStateCmb;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = StringExt.substringMax(phone, Constants.DEFAULT_LEN_STRING);
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) {
        this.celPhone = StringExt.substringMax(celPhone, Constants.DEFAULT_LEN_STRING);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = StringExt.substringMax(email, Constants.DEFAULT_LEN_STRING);
    }

    public String getSocialNetworking() {
        return socialNetworking;
    }

    public void setSocialNetworking(String socialNetworking) {
        this.socialNetworking = StringExt.substringMax(socialNetworking, Constants.DEFAULT_LEN_STRING);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = StringExt.substringMax(comments, Constants.DEFAULT_LEN_STRING);
    }

    public InformationAvailability getBirthInfo() {
        return birthInfo;
    }

    public void setBirthInfo(InformationAvailability birthInfo) {
        this.birthInfo = birthInfo;
    }
}
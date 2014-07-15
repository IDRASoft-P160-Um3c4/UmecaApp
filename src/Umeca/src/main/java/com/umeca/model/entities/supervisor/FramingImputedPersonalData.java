package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Country;
import com.umeca.model.catalog.MaritalStatus;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="framing_imputed_personal_data")
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

    @Column(name = "birth_state")
    private String birthState;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "physical_condition")
    private String physicalCondition;

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
        this.maritalStatusYears = maritalStatusYears;
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
        this.birthState = birthState;
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
        this.physicalCondition = physicalCondition;
    }
}
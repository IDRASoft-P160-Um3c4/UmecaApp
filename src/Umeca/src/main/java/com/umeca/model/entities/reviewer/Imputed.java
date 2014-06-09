package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Country;
import com.umeca.model.catalog.MaritalStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 01:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="imputed")
public class Imputed { @Id
    @GeneratedValue
    @Column(name="id_imputed")
    private Long id;

    @Column(name="rfc", length = 10, nullable = false)
    private String rfc;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="lastname_p", length = 50, nullable = false)
    private String lastNameP;

    @Column(name="lastname_m", length = 50, nullable = false)
    private String lastNameM;

    @Column(name="gender", nullable = true)
    private Boolean gender;

    @Column(name="date_birth", nullable = false)
    private Date dateBirth;

    @Column(name="cel_phone", length = 10, nullable = true)
    private String celPhone;

    @Column(name="years_marital_status", nullable = true)
    private Integer yearsMaritalStatus;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_marital_status", nullable = true)
    private MaritalStatus maritalStatus;

    @Column(name="boys", nullable = true)
    private Integer boys;

    @Column(name="dependent_boys", nullable = true)
    private Integer dependentBoys;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_country", nullable = true)
    private Country birthCountry;

    @Column(name="birth_place", nullable = true, length = 500)
    private String birthPlace;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) {
        this.celPhone = celPhone;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getYearsMaritalStatus() {
        return yearsMaritalStatus;
    }

    public void setYearsMaritalStatus(Integer yearsMaritalStatus) {
        this.yearsMaritalStatus = yearsMaritalStatus;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getBoys() {
        return boys;
    }

    public void setBoys(Integer boys) {
        this.boys = boys;
    }

    public Integer getDependentBoys() {
        return dependentBoys;
    }

    public void setDependentBoys(Integer dependentBoys) {
        this.dependentBoys = dependentBoys;
    }

    public Country getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(Country birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}



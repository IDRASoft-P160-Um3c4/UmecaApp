package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.*;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "channeling", uniqueConstraints = @UniqueConstraint(columnNames = {"id_case", "consecutive"}))
public class Channeling {

    @Id
    @GeneratedValue
    @Column(name = "id_channeling", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district", nullable = false)
    private District district;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat_channeling_type", nullable = false)
    private CatChannelingType channelingType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat_institution_type", nullable = false)
    private CatInstitutionType institutionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat_economic_support", nullable = true)
    private CatEconomicSupport economicSupport;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat_prevention_type", nullable = true)
    private CatPreventionType preventionType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat_education_level", nullable = true)
    private CatEducationLevel educationLevel;

    @Column(name = "spec_other", length = 100, nullable = true)
    private String specOther;

//    @Column(name = "institution_name", length = 100, nullable = false)
//    private String institutionName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat_institution_name", nullable = true)
    private CatChannelingInstitutionName institutionName;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator_user", nullable = false)
    private User creatorUser;

    @Column(name="last_update_date", nullable = true)
    private Calendar lastUpdateDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_updater_user", nullable = true)
    private User updaterUser;

    @Column(name="delete_date", nullable = true)
    private Calendar deleteDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_delete_user", nullable = true)
    private User deleteUser;

    @Column(name="consecutive", nullable = false)
    private Long consecutive;

    @Column(name="is_authorize_to_drop", nullable = true)
    private Boolean isAuthorizeToDrop;

    @Column(name="is_volunteer", nullable = false)
    private Boolean isVolunteer;

    @Column(name="is_fulfilled", nullable = true)
    private Boolean isFulfilled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fulfilled_user", nullable = true)
    private User fulfilledUser;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatChannelingType getChannelingType() {
        return channelingType;
    }

    public void setChannelingType(CatChannelingType channelingType) {
        this.channelingType = channelingType;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Calendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Calendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public User getUpdaterUser() {
        return updaterUser;
    }

    public void setUpdaterUser(User updaterUser) {
        this.updaterUser = updaterUser;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Long getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(Long consecutive) {
        this.consecutive = consecutive;
    }

    public CatInstitutionType getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(CatInstitutionType institutionType) {
        this.institutionType = institutionType;
    }

    public CatEconomicSupport getEconomicSupport() {
        return economicSupport;
    }

    public void setEconomicSupport(CatEconomicSupport economicSupport) {
        this.economicSupport = economicSupport;
    }

    public String getSpecOther() {
        return specOther;
    }

    public void setSpecOther(String specOther) {
        this.specOther = specOther;
    }

    public CatPreventionType getPreventionType() {
        return preventionType;
    }

    public void setPreventionType(CatPreventionType preventionType) {
        this.preventionType = preventionType;
    }

    public CatEducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(CatEducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Calendar getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Calendar deleteDate) {
        this.deleteDate = deleteDate;
    }

    public User getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(User deleteUser) {
        this.deleteUser = deleteUser;
    }

    public Boolean isAuthorizeToDrop() {
        return isAuthorizeToDrop;
    }

    public void setAuthorizeToDrop(Boolean isAuthorizeToDrop) {
        this.isAuthorizeToDrop = isAuthorizeToDrop;
    }

    public Boolean getIsAuthorizeToDrop() {
        return isAuthorizeToDrop;
    }

    public void setIsAuthorizeToDrop(Boolean isAuthorizeToDrop) {
        this.isAuthorizeToDrop = isAuthorizeToDrop;
    }

    public Boolean getIsVolunteer() {
        return isVolunteer;
    }

    public void setIsVolunteer(Boolean isVolunteer) {
        this.isVolunteer = isVolunteer;
    }

    public Boolean getIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(Boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

    public User getFulfilledUser() {
        return fulfilledUser;
    }

    public void setFulfilledUser(User fulfilledUser) {
        this.fulfilledUser = fulfilledUser;
    }

    public CatChannelingInstitutionName getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(CatChannelingInstitutionName institutionName) {
        this.institutionName = institutionName;
    }
}



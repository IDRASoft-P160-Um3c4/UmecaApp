package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.CatChannelingType;
import com.umeca.model.catalog.CatEconomicSupport;
import com.umeca.model.catalog.CatInstitutionType;
import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

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

    @Column(name = "spec_other", length = 100, nullable = false)
    private String specOther;

    @Column(name = "institution_name", length = 100, nullable = false)
    private String institutionName;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator_user", nullable = false)
    private User creatorUser;

    @Column(name="last_update_date", nullable = false)
    private Calendar lastUpdateDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_updater_user", nullable = false)
    private User updaterUser;

    @Column(name="consecutive", nullable = false)
    private Long consecutive;

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

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
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
}

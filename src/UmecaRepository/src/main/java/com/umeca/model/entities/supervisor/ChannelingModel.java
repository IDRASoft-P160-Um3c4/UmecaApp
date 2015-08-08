package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.LongExt;

public class ChannelingModel {

    //Case Id
    private Long channelingId;
    private Long caseId;
    private String idMP;
    private String imputed;
    private Long districtId;
    private String supervisor;

    private Long channelingTypeId;
    private Long economicSupportId;
    private Long preventionTypeId;
    private Long educationLevelId;
    private Long institutionTypeId;
    private String name;
    private String institutionName;
    private String consecutiveTx;
    private String specOther;

    private Boolean isVolunteer;

    public ChannelingModel() {
    }

    public ChannelingModel(Long caseId, String idMP, String name, String lastNameA, String lastNameB, Long districtId, String supervisor) {
        this.caseId = caseId;
        this.idMP = idMP;
        this.imputed = name + " " + lastNameA + " " + lastNameB;
        this.districtId = districtId;
        this.supervisor = supervisor;
        this.isVolunteer = false;
    }

    public ChannelingModel(Long channelingId, Long caseId, String idMP, String firstName, String lastNameA, String lastNameB, Long districtId, String supervisor,
                           String name, Long channelingTypeId, Long institutionTypeId, Long economicSupportId, Long preventionTypeId, Long educationLevelId,
                           String specOther, String institutionName,
                           Long consecutive, Boolean isVolunteer) {
        this.channelingId = channelingId;
        this.caseId = caseId;
        this.idMP = idMP;
        this.name = name;
        this.channelingTypeId = channelingTypeId;
        this.institutionTypeId = institutionTypeId;
        this.economicSupportId = economicSupportId;
        this.preventionTypeId = preventionTypeId;
        this.educationLevelId = educationLevelId;
        this.specOther = specOther;
        this.institutionName = institutionName;
        this.isVolunteer = isVolunteer;
        this.consecutiveTx = LongExt.paddingLeft("0", "4", consecutive);
        this.imputed = firstName + " " + lastNameA + " " + lastNameB;
        this.districtId = districtId;
        this.supervisor = supervisor;
    }

    public Long getChannelingId() {
        return channelingId;
    }

    public void setChannelingId(Long channelingId) {
        this.channelingId = channelingId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Long getChannelingTypeId() {
        return channelingTypeId;
    }

    public void setChannelingTypeId(Long channelingTypeId) {
        this.channelingTypeId = channelingTypeId;
    }

    public Long getEconomicSupportId() {
        return economicSupportId;
    }

    public void setEconomicSupportId(Long economicSupportId) {
        this.economicSupportId = economicSupportId;
    }

    public Long getInstitutionTypeId() {
        return institutionTypeId;
    }

    public void setInstitutionTypeId(Long institutionTypeId) {
        this.institutionTypeId = institutionTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getSpecOther() {
        return specOther;
    }

    public void setSpecOther(String specOther) {
        this.specOther = specOther;
    }

    public Long getPreventionTypeId() {
        return preventionTypeId;
    }

    public void setPreventionTypeId(Long preventionTypeId) {
        this.preventionTypeId = preventionTypeId;
    }

    public String getConsecutiveTx() {
        return consecutiveTx;
    }

    public void setConsecutiveTx(String consecutiveTx) {
        this.consecutiveTx = consecutiveTx;
    }

    public Long getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Long educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public Boolean getIsVolunteer() {
        return isVolunteer;
    }

    public void setIsVolunteer(Boolean isVolunteer) {
        this.isVolunteer = isVolunteer;
    }
}



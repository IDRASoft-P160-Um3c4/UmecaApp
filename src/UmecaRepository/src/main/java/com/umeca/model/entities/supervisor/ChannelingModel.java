package com.umeca.model.entities.supervisor;

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
    private Long institutionTypeId;
    private String name;
    private String institutionName;
    private String specOther;

    public ChannelingModel() {
    }

    public ChannelingModel(Long caseId, String idMP, String name, String lastNameA, String lastNameB, Long districtId, String supervisor) {
        this.caseId = caseId;
        this.idMP = idMP;
        this.imputed = name + " " + lastNameA + " " + lastNameB;
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
}

package com.umeca.model.entities.supervisorManager;

import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;

import java.util.List;

/**
 * Created by DeveloperII on 04/11/2015.
 */
public class ExcelCaseInfoSupDto {
    private Long idCase;
    private String idFolder;
    private Integer gender;
    private String genderStr;
    private String countryBirth;
    private String stateBirth;
    private String maritalStatus;
    private List<ExcelImputedHomeDto> listHome;
    private String isHomeless;
    private String zipCode;
    private String stateAddress;
    private String municipalityAddress;
    private String homeType;
    private String regType;

    private List<ExcelReferenceDto> lstHousemates;
    private String ageHouseMates;
    private String relationshipHouseMates;
    private String isAccomplishedRelationshipHouseMate;
    private String livingWithSomeone;

    private List<ExcelReferenceDto> references;
    private String ageReferences;
    private String relationshipReferences;
    private String isAccomplishedReferences;
    private String hasReferences;

    private Boolean isStudying;
    private String isStudyingStr;
    private String schoolGrade;
    private String schoolAcademicLevel;

    private List<ExcelJobDto> lstJobs;
    private String hasJob;
    private String jobType;

    private List<ExcelActivitiesDto> lstAct;
    private String activitiesStr;


    private List<ExcelDrugDto> lstDrug;
    private String takeDrugs;
    private String drugTypeStr;
    private String periodicityStr;
    private String onsetAgeStr;


    private Integer addictionTreatment;
    private Integer addictedAcquaintance;
    private Integer relativeAbroad;
    private Integer obligationIssue;

    private String addictionTreatmentStr;
    private String addictedAcquaintanceStr;
    private String relativeAbroadStr;
    private String obligationIssueStr;


    private List<ExcelRiskDto> lstRisks;
    private String risks;

    private List<ExcelThreatsDto> lstThreats;
    private String threats;

    private List<ExcelSafetyFactorsDto> lstSafetyFactors;
    private String safetyFactors;


    private List<ExcelReferenceDto> lstVictims;
    private String hasVictimInformation;
    private String ageVictims;
    private String relationShipVictim;


    private List<ExcelReferenceDto> lstWitness;
    private String ageWitness;
    private String getRelationShipWitness;


    List<ExcelChannelingDto> lstChanneling;
    private String hasChanneling;
    private String channelingType;
    private Boolean isVolunteer;
    private String isVolunteerStr;
    private String institutionName;
    private String institutionType;


    public ExcelCaseInfoSupDto(
            Long idCase,
            String idFolder,
            Integer gender,
            String countryBirth,
            String stateBirth,
            String maritalStatus,
            Boolean isStudying,
            String schoolGrade,
            String schoolAcademicLevel,
            Integer addictionTreatment,
            Integer addictedAcquaintance,
            Integer relativeAbroad,
            Integer obligationIssue
    ) {
        this.idCase = idCase;
        this.idFolder = idFolder;
        this.gender = gender;
        this.countryBirth = countryBirth;
        this.stateBirth = stateBirth;
        this.maritalStatus = maritalStatus;
        this.isStudying = isStudying;

        if (gender != null) {
            if (gender.equals(Constants.GENDER_MALE))
                genderStr = "Masculino";
            else
                genderStr = "Femenino";
        }
        if (this.isStudying == null) {
            this.isStudyingStr = "";
        } else {
            if (this.isStudying == true) {
                this.isStudyingStr = "Sí";
            } else {
                this.isStudyingStr = "No";
            }
        }


        this.schoolGrade = schoolGrade;
        this.schoolAcademicLevel = schoolAcademicLevel;


        this.addictionTreatment = addictionTreatment;
        this.addictedAcquaintance = addictedAcquaintance;
        this.relativeAbroad = relativeAbroad;
        this.obligationIssue = obligationIssue;

        if (addictionTreatment == null) {
            this.addictionTreatmentStr = "";
        } else if (addictionTreatment.longValue() == Constants.ELECTION_YES) {
            this.addictionTreatmentStr = "Sí";
        } else {
            this.addictionTreatmentStr = "No";
        }

        if (addictedAcquaintance == null) {
            this.addictedAcquaintanceStr = "";
        } else if (addictedAcquaintance.longValue() == Constants.ELECTION_YES) {
            this.addictedAcquaintanceStr = "Sí";
        } else {
            this.addictedAcquaintanceStr = "No";
        }

        if (relativeAbroad == null) {
            this.relativeAbroadStr = "";
        } else if (relativeAbroad.longValue() == Constants.ELECTION_YES) {
            this.relativeAbroadStr = "Sí";
        } else {
            this.relativeAbroadStr = "No";
        }

        if (obligationIssue == null) {
            this.obligationIssueStr = "";
        } else if (obligationIssue.longValue() == Constants.ELECTION_YES) {
            this.obligationIssueStr = "Sí";
        } else {
            this.obligationIssueStr = "No";
        }


    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderStr() {
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public String getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(String countryBirth) {
        this.countryBirth = countryBirth;
    }

    public String getStateBirth() {
        return stateBirth;
    }

    public void setStateBirth(String stateBirth) {
        this.stateBirth = stateBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }


    public List<ExcelImputedHomeDto> getListHome() {
        return listHome;
    }

    public void setListHome(List<ExcelImputedHomeDto> listHome) {
        this.listHome = listHome;
    }

    public String getIsHomeless() {
        this.isHomeless = "";

        if (listHome != null) {
            for (int i = 0; i < listHome.size(); i++) {
                this.isHomeless += listHome.get(i).getIsHomelessStr();
                if (i < listHome.size() - 1) {
                    this.isHomeless += ",";
                }
            }
        }

        return this.isHomeless;
    }

    public void setIsHomeless(String isHomeless) {
        this.isHomeless = isHomeless;
    }


    public String getZipCode() {
        this.zipCode = "";

        if (listHome != null) {
            for (int i = 0; i < listHome.size(); i++) {
                this.zipCode += listHome.get(i).getZip();
                if (i < listHome.size() - 1) {
                    this.zipCode += ",";
                }
            }
        }
        return this.zipCode;
    }


    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStateAddress() {
        this.stateAddress = "";

        if (listHome != null) {
            for (int i = 0; i < listHome.size(); i++) {
                this.stateAddress += listHome.get(i).getState();
                if (i < listHome.size() - 1) {
                    this.stateAddress += ",";
                }
            }
        }
        return this.stateAddress;
    }

    public void setStateAddress(String stateAddress) {
        this.stateAddress = stateAddress;
    }

    public String getMunicipalityAddress() {
        this.municipalityAddress = "";

        if (listHome != null) {
            for (int i = 0; i < listHome.size(); i++) {
                this.municipalityAddress += listHome.get(i).getMunicipality();
                if (i < listHome.size() - 1) {
                    this.municipalityAddress += ",";
                }
            }
        }
        return this.municipalityAddress;
    }

    public void setMunicipalityAddress(String municipalityAddress) {
        this.municipalityAddress = municipalityAddress;
    }


    public String getHomeType() {
        this.homeType = "";

        if (listHome != null) {
            for (int i = 0; i < listHome.size(); i++) {
                this.homeType += listHome.get(i).getHomeType();
                if (i < listHome.size() - 1) {
                    this.homeType += ",";
                }
            }
        }
        return this.homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public String getRegType() {

        this.regType = "";

        if (listHome != null) {
            for (int i = 0; i < listHome.size(); i++) {
                this.regType += listHome.get(i).getRegType();
                if (i < listHome.size() - 1) {
                    this.regType += ",";
                }
            }
        }
        return this.regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }


    public List<ExcelReferenceDto> getLstHousemates() {
        return lstHousemates;
    }

    public void setLstHousemates(List<ExcelReferenceDto> lstHousemates) {
        this.lstHousemates = lstHousemates;
    }


    public String getAgeHouseMates() {
        this.ageHouseMates = "";

        if (this.lstHousemates != null) {
            for (int i = 0; i < lstHousemates.size(); i++) {
                this.ageHouseMates += lstHousemates.get(i).getAge();
                if (i < listHome.size() - 1) {
                    this.ageHouseMates += ",";
                }
            }
        }
        return this.ageHouseMates;
    }


    public void setAgeHouseMates(String ageHouseMates) {
        this.ageHouseMates = ageHouseMates;
    }

    public String getRelationshipHouseMates() {
        this.relationshipHouseMates = "";

        if (this.lstHousemates != null) {
            for (int i = 0; i < lstHousemates.size(); i++) {
                this.relationshipHouseMates += lstHousemates.get(i).getRelationship();
                if (i < listHome.size() - 1) {
                    this.relationshipHouseMates += ",";
                }
            }
        }

        return this.relationshipHouseMates;
    }

    public void setRelationshipHouseMates(String relationshipHouseMates) {
        this.relationshipHouseMates = relationshipHouseMates;
    }

    public String getIsAccomplishedRelationshipHouseMate() {
        this.isAccomplishedRelationshipHouseMate = "";

        if (this.lstHousemates != null) {
            for (int i = 0; i < lstHousemates.size(); i++) {
                this.isAccomplishedRelationshipHouseMate += lstHousemates.get(i).getAccompanimentStr();
                if (i < listHome.size() - 1) {
                    this.isAccomplishedRelationshipHouseMate += ",";
                }
            }
        }
        return isAccomplishedRelationshipHouseMate;
    }

    public void setIsAccomplishedRelationshipHouseMate(String isAccomplishedRelationshipHouseMate) {
        this.isAccomplishedRelationshipHouseMate = isAccomplishedRelationshipHouseMate;
    }

    public String getLivingWithSomeone() {
        if (this.lstHousemates == null) {
            this.livingWithSomeone = "";
        } else {
            if (lstHousemates.get(0).getAge().equals("NO APLICA")) {
                this.livingWithSomeone = "No";
            } else {
                this.livingWithSomeone = "Sí";
            }
        }
        return livingWithSomeone;
    }

    public void setLivingWithSomeone(String livingWithSomeone) {
        this.livingWithSomeone = livingWithSomeone;
    }

    public List<ExcelReferenceDto> getReferences() {
        return references;
    }

    public void setReferences(List<ExcelReferenceDto> references) {
        this.references = references;
    }


    public String getAgeReferences() {
        this.ageReferences = "";

        if (this.references != null) {
            for (int i = 0; i < references.size(); i++) {
                this.ageReferences += references.get(i).getAge();
                if (i < references.size() - 1) {
                    this.ageReferences += ",";
                }
            }
        }
        return this.ageReferences;
    }

    public void setAgeReferences(String ageReferences) {
        this.ageReferences = ageReferences;
    }

    public String getRelationshipReferences() {
        this.relationshipReferences = "";

        if (this.references != null) {
            for (int i = 0; i < references.size(); i++) {
                this.relationshipReferences += references.get(i).getRelationship();
                if (i < references.size() - 1) {
                    this.relationshipReferences += ",";
                }
            }
        }

        return this.relationshipReferences;
    }

    public void setRelationshipReferences(String relationshipReferences) {
        this.relationshipReferences = relationshipReferences;
    }

    public String getIsAccomplishedReferences() {
        this.isAccomplishedReferences = "";

        if (this.references != null) {
            for (int i = 0; i < references.size(); i++) {
                this.isAccomplishedReferences += references.get(i).getAccompanimentStr();
                if (i < references.size() - 1) {
                    this.isAccomplishedReferences += ",";
                }
            }
        }
        return isAccomplishedReferences;
    }

    public void setIsAccomplishedReferences(String isAccomplishedReferences) {
        this.isAccomplishedReferences = isAccomplishedReferences;
    }

    public String getHasReferences() {

        if (this.references == null) {
            this.hasReferences = "";
        } else {
            if (references.get(0).getAge().equals("")) {
                this.hasReferences = "No";
            } else {
                this.hasReferences = "Sí";
            }
        }
        return hasReferences;
    }

    public void setHasReferences(String hasReferences) {
        this.hasReferences = hasReferences;
    }


    public Boolean getIsStudying() {
        return isStudying;
    }

    public void setIsStudying(Boolean isStudying) {
        this.isStudying = isStudying;
    }

    public String getIsStudyingStr() {
        return isStudyingStr;
    }

    public void setIsStudyingStr(String isStudyingStr) {
        this.isStudyingStr = isStudyingStr;
    }

    public String getSchoolGrade() {
        return schoolGrade;
    }

    public void setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public String getSchoolAcademicLevel() {
        return schoolAcademicLevel;
    }

    public void setSchoolAcademicLevel(String schoolAcademicLevel) {
        this.schoolAcademicLevel = schoolAcademicLevel;
    }

    public List<ExcelJobDto> getLstJobs() {
        return lstJobs;
    }

    public void setLstJobs(List<ExcelJobDto> lstJobs) {
        this.lstJobs = lstJobs;
    }

    public String getHasJob() {
        this.hasJob = "";
        if (lstJobs != null) {
            for (int i = 0; i < lstJobs.size(); i++) {
                if (lstJobs.get(i).getBlock() == true) {
                    this.hasJob += "Sí";
                } else {
                    this.hasJob += "No";
                }
                if (i < references.size() - 1) {
                    this.hasJob += ",";
                }
            }
        }
        return this.hasJob;
    }

    public void setHasJob(String hasJob) {
        this.hasJob = hasJob;
    }

    public String getJobType() {
        this.jobType = "";
        if (lstJobs != null) {
            for (int i = 0; i < lstJobs.size(); i++) {
                if (lstJobs.get(i).getBlock() == true)
                    this.jobType = lstJobs.get(i).getRegisterType();
                else
                    this.jobType = "SIN TRABAJO";
                if (i < references.size() - 1) {
                    this.jobType += ",";
                }
            }
        }

        return this.jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public List<ExcelActivitiesDto> getLstAct() {
        return lstAct;
    }

    public void setLstAct(List<ExcelActivitiesDto> lstAct) {
        this.lstAct = lstAct;
    }

    public String getActivitiesStr() {
        this.activitiesStr = "";

        if (lstAct != null) {
            for (int i = 0; i < lstAct.size(); i++) {
                this.activitiesStr += lstAct.get(i).getNameAct();
                if (i < references.size() - 1) {
                    this.activitiesStr += ",";
                }
            }
        }
        return this.activitiesStr;
    }

    public void setActivitiesStr(String activitiesStr) {
        this.activitiesStr = activitiesStr;
    }

    public List<ExcelDrugDto> getLstDrug() {
        return lstDrug;
    }

    public void setLstDrug(List<ExcelDrugDto> lstDrug) {
        this.lstDrug = lstDrug;
    }

    public String getTakeDrugs() {

        this.takeDrugs = "";
        if (lstDrug != null) {
            for (int i = 0; i < lstJobs.size(); i++) {
                if (lstDrug.get(i).getDrugType().equals("No consume")) {
                    this.takeDrugs = "No";
                    break;
                } else {
                    this.takeDrugs = "Sí";
                }

            }
        }

        return this.takeDrugs;
    }

    public void setTakeDrugs(String takeDrugs) {
        this.takeDrugs = takeDrugs;
    }

    public String getDrugTypeStr() {
        this.drugTypeStr = "";
        if (lstDrug != null) {
            for (int i = 0; i < lstDrug.size(); i++) {
                this.drugTypeStr += lstDrug.get(i).getDrugType();
                if (i < lstDrug.size() - 1) {
                    this.drugTypeStr += ",";
                }
            }
        }

        return drugTypeStr;
    }

    public void setDrugTypeStr(String drugTypeStr) {
        this.drugTypeStr = drugTypeStr;
    }

    public String getPeriodicityStr() {
        this.periodicityStr = "";
        if (lstDrug != null) {
            for (int i = 0; i < lstDrug.size(); i++) {
                this.periodicityStr += lstDrug.get(i).getPeriodicity();
                if (i < lstDrug.size() - 1) {
                    this.periodicityStr += ",";
                }
            }
        }
        return periodicityStr;
    }

    public void setPeriodicityStr(String periodicityStr) {
        this.periodicityStr = periodicityStr;
    }

    public String getOnsetAgeStr() {
        this.onsetAgeStr = "";
        if (lstDrug != null) {
            for (int i = 0; i < lstDrug.size(); i++) {
                this.onsetAgeStr += lstDrug.get(i).getOnsetAge();
                if (i < lstDrug.size() - 1) {
                    this.onsetAgeStr += ",";
                }
            }
        }
        return onsetAgeStr;
    }

    public void setOnsetAgeStr(String onsetAgeStr) {
        this.onsetAgeStr = onsetAgeStr;
    }

    public Integer getAddictionTreatment() {
        return addictionTreatment;
    }

    public void setAddictionTreatment(Integer addictionTreatment) {
        this.addictionTreatment = addictionTreatment;
    }

    public Integer getAddictedAcquaintance() {
        return addictedAcquaintance;
    }

    public void setAddictedAcquaintance(Integer addictedAcquaintance) {
        this.addictedAcquaintance = addictedAcquaintance;
    }

    public Integer getRelativeAbroad() {
        return relativeAbroad;
    }

    public void setRelativeAbroad(Integer relativeAbroad) {
        this.relativeAbroad = relativeAbroad;
    }

    public Integer getObligationIssue() {
        return obligationIssue;
    }

    public void setObligationIssue(Integer obligationIssue) {
        this.obligationIssue = obligationIssue;
    }

    public String getAddictionTreatmentStr() {
        return addictionTreatmentStr;
    }

    public void setAddictionTreatmentStr(String addictionTreatmentStr) {
        this.addictionTreatmentStr = addictionTreatmentStr;
    }

    public String getAddictedAcquaintanceStr() {
        return addictedAcquaintanceStr;
    }

    public void setAddictedAcquaintanceStr(String addictedAcquaintanceStr) {
        this.addictedAcquaintanceStr = addictedAcquaintanceStr;
    }

    public String getRelativeAbroadStr() {
        return relativeAbroadStr;
    }

    public void setRelativeAbroadStr(String relativeAbroadStr) {
        this.relativeAbroadStr = relativeAbroadStr;
    }

    public String getObligationIssueStr() {
        return obligationIssueStr;
    }

    public void setObligationIssueStr(String obligationIssueStr) {
        this.obligationIssueStr = obligationIssueStr;
    }


    public List<ExcelRiskDto> getLstRisks() {
        return lstRisks;
    }

    public void setLstRisks(List<ExcelRiskDto> lstRisks) {
        this.lstRisks = lstRisks;
    }

    public String getRisks() {
        this.risks = "";
        if (lstRisks != null) {
            for (int i = 0; i < lstRisks.size(); i++) {
                this.risks += lstRisks.get(i).getDescription();
                if (i < lstRisks.size() - 1) {
                    this.risks += ",";
                }
            }
        }

        return this.risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public List<ExcelThreatsDto> getLstThreats() {
        return lstThreats;
    }

    public void setLstThreats(List<ExcelThreatsDto> lstThreats) {
        this.lstThreats = lstThreats;
    }

    public String getThreats() {
        this.threats = "";
        if (lstThreats != null) {
            for (int i = 0; i < lstThreats.size(); i++) {
                this.threats += lstThreats.get(i).getDescription();
                if (i < lstThreats.size() - 1) {
                    this.threats += ',';
                }
            }
        }

        return this.threats;
    }

    public void setThreats(String threats) {
        this.threats = threats;
    }

    public List<ExcelSafetyFactorsDto> getLstSafetyFactors() {
        return lstSafetyFactors;
    }

    public void setLstSafetyFactors(List<ExcelSafetyFactorsDto> lstSafetyFactors) {
        this.lstSafetyFactors = lstSafetyFactors;
    }

    public String getSafetyFactors() {
        this.safetyFactors = "";

        if (safetyFactors != null) {
            for (int i = 0; i < lstSafetyFactors.size(); i++) {
                this.safetyFactors += lstSafetyFactors.get(i).getDescription();
                if (i < lstSafetyFactors.size() - 1) {
                    this.safetyFactors += ",";
                }
            }
        }

        return this.safetyFactors;
    }

    public void setSafetyFactors(String safetyFactors) {
        this.safetyFactors = safetyFactors;
    }

    public List<ExcelReferenceDto> getLstVictims() {
        return lstVictims;
    }

    public void setLstVictims(List<ExcelReferenceDto> lstVictims) {
        this.lstVictims = lstVictims;
    }

    public String getHasVictimInformation() {
        this.hasVictimInformation = "";

        if (lstVictims != null) {
            for (int i = 0; i < lstVictims.size(); i++) {
                if (lstVictims.get(i).getAge().equals("NO APLICA")) {
                    this.hasVictimInformation = "No";
                } else {
                    this.hasVictimInformation = "Sí";
                }
            }
        }

        return hasVictimInformation;
    }

    public void setHasVictimInformation(String hasVictimInformation) {
        this.hasVictimInformation = hasVictimInformation;
    }

    public String getAgeVictims() {
        this.ageVictims = "";

        if (lstVictims != null) {
            for (int i = 0; i < lstVictims.size(); i++) {
                this.ageVictims += lstVictims.get(i).getAge();
                if (i < lstVictims.size() - 1) {
                    this.ageVictims += ",";
                }
            }
        }

        return this.ageVictims;
    }

    public void setAgeVictims(String ageVictims) {
        this.ageVictims = ageVictims;
    }

    public String getRelationShipVictim() {
        this.relationShipVictim = "";
        if (lstVictims != null) {
            for (int i = 0; i < lstVictims.size(); i++) {
                this.relationShipVictim += lstVictims.get(i).getRelationship();
                if (i < lstVictims.size() - 1) {
                    this.relationShipVictim += ",";
                }
            }
        }

        return this.relationShipVictim;
    }

    public void setRelationShipVictim(String relationShipVictim) {
        this.relationShipVictim = relationShipVictim;
    }

    public List<ExcelReferenceDto> getLstWitness() {
        return lstWitness;
    }

    public void setLstWitness(List<ExcelReferenceDto> lstWitness) {
        this.lstWitness = lstWitness;
    }

    public String getAgeWitness() {
        this.ageWitness = "";

        if (lstWitness != null) {
            for (int i = 0; i < lstWitness.size(); i++) {
                this.ageWitness += lstWitness.get(i).getAge();
                if (i < lstWitness.size() - 1) {
                    this.ageWitness = ",";
                }
            }
        }

        return this.ageWitness;
    }

    public void setAgeWitness(String ageWitness) {
        this.ageWitness = ageWitness;
    }

    public String getGetRelationShipWitness() {
        this.getRelationShipWitness = "";
        if (lstWitness != null) {
            for (int i = 0; i < lstWitness.size(); i++) {
                this.getRelationShipWitness += lstWitness.get(i).getRelationship();
                if (i < lstWitness.size() - 1) {
                    this.getRelationShipWitness = ",";
                }
            }
        }
        return this.getRelationShipWitness;
    }

    public void setGetRelationShipWitness(String getRelationShipWitness) {
        this.getRelationShipWitness = getRelationShipWitness;
    }

    public List<ExcelChannelingDto> getLstChanneling() {
        return lstChanneling;
    }

    public void setLstChanneling(List<ExcelChannelingDto> lstChanneling) {
        this.lstChanneling = lstChanneling;
    }

    public String getHasChanneling() {
        if (lstChanneling == null || lstChanneling.size() == 0)
            this.hasChanneling = "No";
        else
            this.hasChanneling = "Sí";

        return this.hasChanneling;
    }

    public void setHasChanneling(String hasChanneling) {
        this.hasChanneling = hasChanneling;
    }

    public String getChannelingType() {
        this.channelingType = "";

        if (lstChanneling != null) {
            for (int i = 0; i < lstChanneling.size(); i++) {
                this.channelingType += lstChanneling.get(i).getChannelingType();
                if (i < lstChanneling.size() - 1) {
                    this.channelingType += ",";
                }
            }

        }

        return this.channelingType;
    }

    public void setChannelingType(String channelingType) {
        this.channelingType = channelingType;
    }

    public Boolean getIsVolunteer() {

        return isVolunteer;
    }
    public void setIsVolunteer(Boolean isVolunteer) {
        this.isVolunteer = isVolunteer;
    }

    public String getIsVolunteerStr() {
        this.isVolunteerStr = "";

        if(lstChanneling != null ){
            for(int i = 0; i < lstChanneling.size(); i++){
                if(lstChanneling.get(i).getIsVolunteer() == null){
                    this.isVolunteerStr += "";
                }
                else if(lstChanneling.get(i).getIsVolunteer() == true){
                    this.isVolunteerStr += "Sí";
                }
                else {
                    this.isVolunteerStr += "No";
                }
                if(i < lstChanneling.size() -1 ){
                    this.isVolunteerStr += ",";
                }

            }
        }
        return isVolunteerStr;
    }

    public void setIsVolunteerStr(String isVolunteerStr) {
        this.isVolunteerStr = isVolunteerStr;
    }



    public String getInstitutionName() {
        this.institutionName = "";

        if (lstChanneling != null) {
            for (int i = 0; i < lstChanneling.size(); i++) {
                this.institutionName += lstChanneling.get(i).getInstitutionName();
                if (i < lstChanneling.size() - 1) {
                    this.institutionName += ",\n";
                }
            }

        }
        return this.institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionType() {
        this.institutionType = "";

        if (lstChanneling != null) {
            for (int i = 0; i < lstChanneling.size(); i++) {
                this.institutionType += lstChanneling.get(i).getChannelingType();
                if (i < lstChanneling.size() - 1) {
                    this.institutionType += ",\n";
                }
            }

        }

        return this.institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }
}

package com.umeca.model.entities.managereval;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.umeca.model.entities.supervisor.*;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 30/10/2015.
 */
public class ExcelCaseInfoEvalDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    private Long idCase;
    private String reviewer;
    private String idFolder;
    private String hasNegation;
    private String isFromFormulation;
    private String report;
    private String gotFreedom;
    private String opinion;
    private String riskEvaluation;
    private Date imputedBirthday;
    private Integer age;
    private String gender;
    private String district;
    private String interviewAccepted;
    private String formulationPresence;
    private String formulationInformationDelivered;
    private String maritalStatus;
    private Integer children;
    private Integer dependedChildren;
    private String countryBirth;
    private String stateBirth;
    private String municipalityBirth;
    private String Activities;
    private String isHomeless;
    private String homeType;
    private String zipCode;
    private String propertyType;
    private String regType;
    private String AddressType;


    private String hasPeopleSocialNetwork;
    private String peopleSocialNetworkAccompaniment;
    private String peopleSocialNetworkRelationship;
    private String peopleSocialNetworkIdentification;
    private String peopleSocialNetworkAge;
    private String peopleSocialNetworkLiveTogether;
    private String peopleSocialNetworkDependent;


    private String hasPersonalReference;
    private String personalReferenceAccompaniment;
    private String personalReferenceRelationship;
    private String personalReferenceIdentification;
    private String personalReferenceAge;


    private List<ExcelJobDto> lstJob;
    private String isWorking;
    private String job;
    private String jobStartDay;


    private String isStudying;
    private String degree;
    private String academicLevel;

    private String takeDrugs;
    private String drugsType;
    private String takeDrugPeriod;
    private String lastTasted;
    private String getTakeDrugsStartAge;

    private String easyLeaveCountry;
    private String documentation;
    private String livedInAnotherCountry;
    private String countryHasLived;
    private String howLongHasLiveInAnotherCountry;
    private String yearsLivedInAnotherCountry;
    private String relativesLivingInAnotherCountry;
    private String hasCommunicationWithThem;
    private String relationshipWithPeopleLivingInAnotherCountry;
    private List<ExcelDrugDto> lstDrug;
    private List<ExcelActivitiesDto> lstActivities;
    private String activitiesStr;
    private List<ExcelImputedHomeDto> lstHomes;
    private String homesStr;
    private String imputedBirthdayStr;
    private List<ExcelSocialNetworkDto> lstSN;
    private String socialNetworkStr;
    private List<ExcelReferenceDto> lstRef;
    private String referencesStr;

    private String jobsStr;
    private Long idMonP;
    private MonitoringPlanExcelInfo monitoringPlanExcelInfo;

    private FramingMeetingInfo framingMeetingInfo;


    public ExcelCaseInfoEvalDto(
            Long idCase,
            String reviewer,
            String idFolder,
            boolean hasNegation,
            boolean isFromFormulation,
            String statusName,
            Boolean technicalReviewIsFinished,
            Integer totalRisk,
            Date imputedBirthday,
            Boolean gender,
            String district,
            Boolean formulationPresence,
            Boolean formulationInformationDelivered,
            String maritalStatus,
            Integer children,
            Integer dependedChildren,
            String municipalityBirth,
            String stateBirth,
            String countryBirth,
            String meetingStatus,
            String essyLeaveCountry,
            String documentation,
            String livedInAnotherCountry,
            String countryHasLived,
            String howLongHasLiveInAnotherCountry,
            String yearsLivedInAnotherCountry,
            String relativesLivingInAnotherCountry,
            String hasCommunicationWithThem,
            String relationshipWithPeopleLivingInAnotherCountry,
            Boolean isStudying,
            String academicLevel,
            String degree

    ) {


        this.idCase = idCase;
        this.reviewer = reviewer;
        this.idFolder = idFolder;

        if (hasNegation == true)
            this.hasNegation = "Sí";
        else
            this.hasNegation = "No";

        if (isFromFormulation == true)
            this.isFromFormulation = "Sí";
        else
            this.isFromFormulation = "No";


        if (isFromFormulation == true) {

            if (formulationPresence == null) {
                this.formulationPresence = "Pendiente";
            } else if (formulationPresence.equals(true)) {
                this.formulationPresence = "Sí";
            } else {
                this.formulationPresence = "No";
            }

            this.isFromFormulation = "Sí";
            if (formulationInformationDelivered == null) {
                this.formulationInformationDelivered = "Pendiente";
            } else if (formulationInformationDelivered.equals(true)) {
                this.formulationInformationDelivered = "Sí";
            } else {
                this.formulationInformationDelivered = "No";
            }
        } else {
            this.isFromFormulation = "No";
            this.formulationInformationDelivered = "No aplica";
            this.formulationPresence = "No aplica";
        }


        if (statusName.equals(Constants.CASE_STATUS_NOT_PROSECUTE))
            this.report = "Sí";
        else
            this.report = "No";

        if (statusName.equals(Constants.CASE_STATUS_GOT_FREEDOM))
            this.gotFreedom = "Sí";
        else
            this.gotFreedom = "No";

        if (technicalReviewIsFinished != null) {
            if (technicalReviewIsFinished.equals(true))
                this.opinion = "Sí";
            else
                this.opinion = "No";
        }

        if (totalRisk != null) {
            int totalRiskVal = totalRisk.intValue();
            if (totalRiskVal < -15)
                this.riskEvaluation = "Alto";
            else if (totalRiskVal > -16 && totalRiskVal < 0)
                this.riskEvaluation = "Medio";
            else if (totalRiskVal > -1 && totalRiskVal < 10)
                this.riskEvaluation = "Bajo";
            else if (totalRiskVal > 9)
                this.riskEvaluation = "Mínimo";
        }


        this.imputedBirthday = imputedBirthday;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.imputedBirthdayStr = imputedBirthday == null ? "" : sdf.format(imputedBirthday);


        if (gender.equals(Constants.GENDER_MALE))
            this.gender = "Masculino";
        else
            this.gender = "Femenino";

        this.district = district;
        this.maritalStatus = maritalStatus;
        this.children = children;
        this.dependedChildren = dependedChildren;

        this.countryBirth = countryBirth;
        this.stateBirth = stateBirth;
        this.municipalityBirth = municipalityBirth;

        if (meetingStatus.equals(Constants.S_MEETING_DECLINE))
            this.interviewAccepted = "No";
        else
            this.interviewAccepted = "Sí";


        //FACILIDADES DE ABANDONAR EL PAÍS

        this.easyLeaveCountry = essyLeaveCountry;

        if (easyLeaveCountry == null || easyLeaveCountry.equals("No")) {
            this.documentation = "";
        } else {
            this.documentation = documentation;
        }


        this.livedInAnotherCountry = livedInAnotherCountry;

        if (livedInAnotherCountry == null || livedInAnotherCountry.equals("No")) {
            this.countryHasLived = "";
            this.howLongHasLiveInAnotherCountry = "";
            this.yearsLivedInAnotherCountry = "";


        } else {
            this.countryHasLived = countryHasLived;
            this.howLongHasLiveInAnotherCountry = howLongHasLiveInAnotherCountry;
            this.yearsLivedInAnotherCountry = yearsLivedInAnotherCountry;
        }


        this.relativesLivingInAnotherCountry = relativesLivingInAnotherCountry;
        if (relativesLivingInAnotherCountry == null || relativesLivingInAnotherCountry.equals("No")) {
            this.hasCommunicationWithThem = "";
            this.relationshipWithPeopleLivingInAnotherCountry = "";
        } else {
            this.hasCommunicationWithThem = hasCommunicationWithThem;
            this.relationshipWithPeopleLivingInAnotherCountry = relationshipWithPeopleLivingInAnotherCountry;
        }

        if (isStudying == null || isStudying.equals(false)) {
            this.isStudying = "No";
        } else {
            this.isStudying = "Sí";
        }

        this.academicLevel = academicLevel;
        this.degree = degree;

    }


    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public List<ExcelDrugDto> getLstDrug() {
        return lstDrug;
    }

    public void setLstDrug(List<ExcelDrugDto> lstDrug) {
        this.lstDrug = lstDrug;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getHasNegation() {
        return hasNegation;
    }

    public void setHasNegation(String hasNegation) {
        this.hasNegation = hasNegation;
    }

    public String getIsFromFormulation() {
        return isFromFormulation;
    }

    public void setIsFromFormulation(String isFromFormulation) {
        this.isFromFormulation = isFromFormulation;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getGotFreedom() {
        return gotFreedom;
    }

    public void setGotFreedom(String gotFreedom) {
        this.gotFreedom = gotFreedom;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRiskEvaluation() {
        return riskEvaluation;
    }

    public void setRiskEvaluation(String riskEvaluation) {
        this.riskEvaluation = riskEvaluation;
    }

    public Integer getAge() {
        return age;
    }

    public Date getImputedBirthday() {
        return imputedBirthday;
    }

    public void setImputedBirthday(Date imputedBirthday) {
        this.imputedBirthday = imputedBirthday;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getInterviewAccepted() {
        return interviewAccepted;
    }

    public void setInterviewAccepted(String interviewAccepted) {
        this.interviewAccepted = interviewAccepted;
    }

    public String getFormulationPresence() {
        return formulationPresence;
    }

    public void setFormulationPresence(String formulationPresence) {
        this.formulationPresence = formulationPresence;
    }

    public String getFormulationInformationDelivered() {
        return formulationInformationDelivered;
    }

    public void setFormulationInformationDelivered(String formulationInformationDelivered) {
        this.formulationInformationDelivered = formulationInformationDelivered;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    public Integer getDependedChildren() {
        return dependedChildren;
    }

    public void setDependedChildren(Integer dependedChildren) {
        this.dependedChildren = dependedChildren;
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

    public String getMunicipalityBirth() {
        return municipalityBirth;
    }

    public void setMunicipalityBirth(String municipalityBirth) {
        this.municipalityBirth = municipalityBirth;
    }

    public String getActivities() {
        return Activities;
    }

    public void setActivities(String activities) {
        Activities = activities;
    }

    public String getIsHomeless() {
        if (this.lstHomes != null && this.lstHomes.size() > 0) {
            this.isHomeless = this.lstHomes.get(0).getIsHomelessStr();
        }
        return isHomeless;
    }

    public void setIsHomeless(String isHomeless) {
        this.isHomeless = isHomeless;
    }

    public String getZipCode() {
        this.zipCode = "";

        if (this.lstHomes != null) {
            for (int i = 0; i < this.lstHomes.size(); i++) {
                if (this.lstHomes.get(i).getZip() != null) {
                    this.zipCode += this.lstHomes.get(i).getZip();
                    if (i < this.lstHomes.size() - 1) {
                        this.zipCode += ",";
                    }
                }
            }
        }
        return this.zipCode;
    }


    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAddressType() {
        return AddressType;
    }

    public void setAddressType(String addressType) {
        AddressType = addressType;
    }

    public String getHasPeopleSocialNetwork() {
        if (lstSN == null) {
            return "";
        }

        for (int i = 0; i < lstSN.size(); i++) {
            if (lstSN.get(i).getName() == null || lstSN.get(i).getName().equals("NO TIENE")) {
                return "No";
            }
        }
        return "Sí";
    }

    public void setHasPeopleSocialNetwork(String hasPeopleSocialNetwork) {
        this.hasPeopleSocialNetwork = hasPeopleSocialNetwork;
    }

    public String getPeopleSocialNetworkAccompaniment() {
        this.peopleSocialNetworkAccompaniment = "";


        if (lstSN != null) {
            for (int i = 0; i < lstSN.size(); i++) {
                if (lstSN.get(i).getAccompaniment() != null) {

                    if (lstSN.get(i).getName().equals("NO TIENE")) {
                        return "";
                    }

                    if (lstSN.get(i).getAccompaniment() == true) {

                        this.peopleSocialNetworkAccompaniment += "Sí";
                    } else {
                        this.peopleSocialNetworkAccompaniment += "No";
                    }
                }
                if (i < lstSN.size() - 1) {
                    this.peopleSocialNetworkAccompaniment += ",";
                }
            }
        }


        return peopleSocialNetworkAccompaniment;
    }

    public void setPeopleSocialNetworkAccompaniment(String peopleSocialNetworkAccompaniment) {
        this.peopleSocialNetworkAccompaniment = peopleSocialNetworkAccompaniment;
    }

    public String getPeopleSocialNetworkRelationship() {
        this.peopleSocialNetworkRelationship = "";
        if (lstSN != null) {
            for (int i = 0; i < lstSN.size(); i++) {

                if (lstSN.get(i).getRelationship() != null) {
                    if (lstSN.get(i).getName().equals("NO TIENE")) {
                        return "";
                    }
                    this.peopleSocialNetworkRelationship = lstSN.get(i).getRelationship();
                }
                if (i < lstSN.size() - 1) {
                    this.peopleSocialNetworkRelationship += ",";
                }
            }
        }
        return peopleSocialNetworkRelationship;
    }

    public void setPeopleSocialNetworkRelationship(String peopleSocialNetworkRelationship) {
        this.peopleSocialNetworkRelationship = peopleSocialNetworkRelationship;
    }

    public String getPeopleSocialNetworkIdentification() {

        this.peopleSocialNetworkIdentification = "";
        if (lstSN != null) {
            for (int i = 0; i < lstSN.size(); i++) {

                if (lstSN.get(i).getDocument() != null) {
                    if (lstSN.get(i).getName().equals("NO TIENE")) {
                        return "";
                    }
                    this.peopleSocialNetworkIdentification = lstSN.get(i).getDocument();
                }
                if (i < lstSN.size() - 1) {
                    this.peopleSocialNetworkRelationship += ",";
                }
            }
        }

        return peopleSocialNetworkIdentification;
    }

    public void setPeopleSocialNetworkIdentification(String peopleSocialNetworkIdentification) {
        this.peopleSocialNetworkIdentification = peopleSocialNetworkIdentification;
    }

    public String getPeopleSocialNetworkAge() {
        this.peopleSocialNetworkAge = "";
        if (lstSN != null) {
            for (int i = 0; i < lstSN.size(); i++) {
                if (lstSN.get(i).getName().equals("NO TIENE")) {
                    return "";
                }
                if (lstSN.get(i).getAge() != null) {
                    this.peopleSocialNetworkAge += lstSN.get(i).getAge();
                }
                if (i < lstSN.size() - 1) {
                    this.peopleSocialNetworkAge += ",";
                }
            }
        }

        return peopleSocialNetworkAge;
    }

    public void setPeopleSocialNetworkAge(String peopleSocialNetworkAge) {
        this.peopleSocialNetworkAge = peopleSocialNetworkAge;
    }

    public String getPeopleSocialNetworkLiveTogether() {
        this.peopleSocialNetworkLiveTogether = "";

        if (lstSN != null) {
            for (int i = 0; i < lstSN.size(); i++) {
                if (lstSN.get(i).getLivingWith() != null) {
                    if (lstSN.get(i).getName().equals("NO TIENE")) {
                        return "";
                    }
                    this.peopleSocialNetworkLiveTogether += lstSN.get(i).getLivingWith();
                }
                if (i < lstSN.size() - 1) {
                    this.peopleSocialNetworkLiveTogether += ",";
                }
            }
        }

        return peopleSocialNetworkLiveTogether;
    }

    public void setPeopleSocialNetworkLiveTogether(String peopleSocialNetworkLiveTogether) {
        this.peopleSocialNetworkLiveTogether = peopleSocialNetworkLiveTogether;
    }

    public String getPeopleSocialNetworkDependent() {
        this.peopleSocialNetworkDependent = "";
        if (lstSN != null) {
            if (lstSN != null) {
                for (int i = 0; i < lstSN.size(); i++) {
                    if (lstSN.get(i).getDependent() != null) {
                        if (lstSN.get(i).getName().equals("NO TIENE")) {
                            return "";
                        }
                        this.peopleSocialNetworkDependent += lstSN.get(i).getDependent();
                    }
                    if (i < lstSN.size() - 1) {
                        this.peopleSocialNetworkDependent += ",";
                    }
                }
            }
        }

        return peopleSocialNetworkDependent;
    }

    public void setPeopleSocialNetworkDependent(String peopleSocialNetworkDependent) {
        this.peopleSocialNetworkDependent = peopleSocialNetworkDependent;
    }

    public String getHasPersonalReference() {
        if (lstRef == null) {
            return "";
        }
        for (int i = 0; i < lstRef.size(); i++) {
            if (lstRef.get(i).getName().equals("NO TIENE")) {
                return "No";
            }
        }
        hasPersonalReference = "Sí";
        return hasPersonalReference;
    }

    public void setHasPersonalReference(String hasPersonalReference) {
        this.hasPersonalReference = hasPersonalReference;
    }

    public String getPersonalReferenceAccompaniment() {
        personalReferenceAccompaniment = "";
        if (lstRef != null) {
            for (int i = 0; i < lstRef.size(); i++) {
                if (lstRef.get(i).getName().equals("NO TIENE")) {
                    return "";
                }
                if (lstRef.get(i).getAccompaniment() == Boolean.TRUE) {
                    personalReferenceAccompaniment += "Sí";
                } else {
                    personalReferenceAccompaniment += "No";
                }
                if (i < lstSN.size() - 1) {
                    this.personalReferenceAccompaniment += ",";
                }
            }
        }

        return personalReferenceAccompaniment;
    }

    public void setPersonalReferenceAccompaniment(String personalReferenceAccompaniment) {
        this.personalReferenceAccompaniment = personalReferenceAccompaniment;
    }

    public String getPersonalReferenceRelationship() {
        personalReferenceRelationship = "";
        if (lstRef != null) {
            for (int i = 0; i < lstRef.size(); i++) {
                if (lstRef.get(i).getName().equals("NO TIENE")) {
                    return "";
                }
                personalReferenceRelationship += lstRef.get(i).getRelationship();

                if (i < lstRef.size() - 1) {
                    this.personalReferenceRelationship += ",";
                }
            }
        }
        return personalReferenceRelationship;
    }

    public void setPersonalReferenceRelationship(String personalReferenceRelationship) {
        this.personalReferenceRelationship = personalReferenceRelationship;
    }

    public String getPersonalReferenceIdentification() {
        personalReferenceIdentification = "";

        if (lstRef != null) {
            for (int i = 0; i < lstRef.size(); i++) {
                if (lstRef.get(i).getName().equals("NO TIENE")) {
                    return "";
                }
                personalReferenceIdentification += lstRef.get(i).getDocument();

                if (i < lstRef.size() - 1) {
                    this.personalReferenceIdentification += ",";
                }
            }
        }

        return personalReferenceIdentification;
    }

    public void setPersonalReferenceIdentification(String personalReferenceIdentification) {
        this.personalReferenceIdentification = personalReferenceIdentification;
    }

    public String getPersonalReferenceAge() {
        this.personalReferenceAge = "";
        if (lstRef != null) {
            for (int i = 0; i < lstRef.size(); i++) {
                if (lstRef.get(i).getName().equals("NO TIENE")) {
                    return "";
                }
                personalReferenceAge += lstRef.get(i).getAge();

                if (i < lstRef.size() - 1) {
                    this.personalReferenceAge += ",";
                }
            }
        }
        return personalReferenceAge;
    }

    public void setPersonalReferenceAge(String personalReferenceAge) {
        this.personalReferenceAge = personalReferenceAge;
    }

    public String getIsWorking() {

        isWorking = "";
        if (lstJob != null) {
            for (int i = 0; i < lstJob.size(); i++) {
                if (lstJob.get(i).getBlock().equals(Boolean.FALSE)) {
                    isWorking = "No";
                } else {
                    isWorking = "Sí";
                }
            }
        }

        return isWorking;
    }

    public void setIsWorking(String isWorking) {
        this.isWorking = isWorking;
    }

    public String getJob() {
        job = "";
        if (lstJob != null) {
            for (int i = 0; i < lstJob.size(); i++) {
                if (lstJob.get(i).getBlock().equals(Boolean.FALSE)) {
                    return "";
                }
                this.job += lstJob.get(i).getRegisterType();
                if (i < lstJob.size() - 1) {
                    this.job += ",";
                }
            }
        }

        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobStartDay() {
        jobStartDay = "";

        if (lstJob != null) {
            for (int i = 0; i < lstJob.size(); i++) {
                if (lstJob.get(i).getBlock().equals(Boolean.FALSE)) {
                    return "";
                }
                this.jobStartDay += lstJob.get(i).getStartStr();
                if (i < lstJob.size() - 1) {
                    this.job += ",";
                }
            }
        }

        return jobStartDay;
    }

    public void setJobStartDay(String jobStartDay) {
        this.jobStartDay = jobStartDay;
    }

    public String getIsStudying() {
        return isStudying;
    }

    public void setIsStudying(String isStudying) {
        this.isStudying = isStudying;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getTakeDrugs() {

        takeDrugs = "";

        if (lstDrug != null) {
            for (int i = 0; i < lstDrug.size(); i++) {
                if (lstDrug.get(i).getDrugType().equals("No consume")) {
                    return "No";
                } else {
                    return "Sí";
                }
            }
        }
        return takeDrugs;
    }

    public void setTakeDrugs(String takeDrugs) {
        this.takeDrugs = takeDrugs;
    }

    public String getDrugsType() {
        drugsType = "";

        if (lstDrug != null) {
            for (int i = 0; i < lstDrug.size(); i++) {
                if (lstDrug.get(i).getDrugType().equals("No consume")) {
                    return "";
                }
                drugsType += lstDrug.get(i).getDrugType();
                if (i < lstJob.size() - 1) {
                    this.drugsType += ",";
                }
            }
        }

        return drugsType;
    }

    public void setDrugsType(String drugsType) {
        this.drugsType = drugsType;
    }

    public String getTakeDrugPeriod() {
        takeDrugPeriod = "";
        if (lstDrug != null) {
            for (int i = 0; i < lstDrug.size(); i++) {
                if (lstDrug.get(i).getDrugType().equals("No consume")) {
                    return "";
                }
                takeDrugPeriod += lstDrug.get(i).getPeriodicity();
                if (i < lstJob.size() - 1) {
                    this.takeDrugPeriod += ",";
                }
            }
        }

        return takeDrugPeriod;
    }

    public void setTakeDrugPeriod(String takeDrugPeriod) {
        this.takeDrugPeriod = takeDrugPeriod;
    }

    public String getLastTasted() {
        lastTasted = "";
        if (lstDrug != null) {
            for (int i = 0; i < lstDrug.size(); i++) {
                if (lstDrug.get(i).getDrugType().equals("No consume")) {
                    return "";
                }
                lastTasted += lstDrug.get(i).getLastUseStr();
                if (i < lstJob.size() - 1) {
                    this.lastTasted += ",";
                }
            }
        }
        return lastTasted;
    }

    public void setLastTasted(String lastTasted) {
        this.lastTasted = lastTasted;
    }

    public String getGetTakeDrugsStartAge() {
        return getTakeDrugsStartAge;
    }

    public void setGetTakeDrugsStartAge(String getTakeDrugsStartAge) {
        this.getTakeDrugsStartAge = getTakeDrugsStartAge;
    }

    public String getEasyLeaveCountry() {
        return easyLeaveCountry;
    }

    public void setEasyLeaveCountry(String easyLeaveCountry) {
        this.easyLeaveCountry = easyLeaveCountry;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getLivedInAnotherCountry() {
        return livedInAnotherCountry;
    }

    public void setLivedInAnotherCountry(String livedInAnotherCountry) {
        this.livedInAnotherCountry = livedInAnotherCountry;
    }

    public String getCountryHasLived() {
        return countryHasLived;
    }

    public void setCountryHasLived(String countryHasLived) {
        this.countryHasLived = countryHasLived;
    }

    public String getHowLongHasLiveInAnotherCountry() {
        return howLongHasLiveInAnotherCountry;
    }

    public void setHowLongHasLiveInAnotherCountry(String howLongHasLiveInAnotherCountry) {
        this.howLongHasLiveInAnotherCountry = howLongHasLiveInAnotherCountry;
    }

    public String getYearsLivedInAnotherCountry() {
        return yearsLivedInAnotherCountry;
    }

    public void setYearsLivedInAnotherCountry(String yearsLivedInAnotherCountry) {
        this.yearsLivedInAnotherCountry = yearsLivedInAnotherCountry;
    }

    public String getRelativesLivingInAnotherCountry() {
        return relativesLivingInAnotherCountry;
    }

    public void setRelativesLivingInAnotherCountry(String relativesLivingInAnotherCountry) {
        this.relativesLivingInAnotherCountry = relativesLivingInAnotherCountry;
    }

    public String getHasCommunicationWithThem() {
        return hasCommunicationWithThem;
    }

    public void setHasCommunicationWithThem(String hasCommunicationWithThem) {
        this.hasCommunicationWithThem = hasCommunicationWithThem;
    }

    public String getRelationshipWithPeopleLivingInAnotherCountry() {
        return relationshipWithPeopleLivingInAnotherCountry;
    }

    public void setRelationshipWithPeopleLivingInAnotherCountry(String relationshipWithPeopleLivingInAnotherCountry) {
        this.relationshipWithPeopleLivingInAnotherCountry = relationshipWithPeopleLivingInAnotherCountry;
    }


    public String summaryEvaluationDrugs() {
        String returnStr = "";

        if (lstDrug != null && lstDrug.size() > 0)
            for (ExcelDrugDto act : lstDrug) {
                if (act.getBlock().equals(true)) {
                    if (returnStr.isEmpty() == false)
                        returnStr += "\n";
                    if (act.getDrugType() != null && !act.getDrugType().equals(""))
                        returnStr += "," + act.getDrugType();
                    this.takeDrugs = "Sí";
                } else {
                    returnStr = "El imputado no consume sustancias.";
                    this.takeDrugs = "No";
                    break;
                }
            }
        return returnStr;
    }


//    public String getOnsetAgeEvaluationDrugs() {
//        String returnStr = "";
//
//        if (lstDrug != null && lstDrug.size() > 0)
//            for (ExcelDrugDto act : lstDrug) {
//                if (act.getBlock() == true) {
//                    if (returnStr != "")
//                        returnStr += "\n";
//                    if (act.getDrugType() != null && !act.getDrugType().equals(""))
//                        returnStr += "," + act.getOnsetAge();
//                    this.takeDrugs = "Sí";
//                } else {
//                    returnStr = "El imputado no consume sustancias.";
//                    this.takeDrugs = "No";
//                    break;
//                }
//            }
//        return returnStr;
//    }

    public List<ExcelActivitiesDto> getLstActivities() {
        return lstActivities;
    }

    public void setLstActivities(List<ExcelActivitiesDto> lstActivities) {
        this.lstActivities = lstActivities;
    }

    public String getActivitiesStr() {
        this.activitiesStr = "";


        if (this.lstActivities != null && lstActivities.size() > 0)

            for (int i = 0; i < lstActivities.size(); i++) {
                if (activitiesStr.isEmpty() == false) {
                    activitiesStr += " ";
                }
                if (lstActivities.get(i).getDescription() != null && !lstActivities.get(i).getDescription().trim().equals("")) {
                    activitiesStr += lstActivities.get(i).getNameAct() +  ": " + lstActivities.get(i).getDescription();
                }
                if (i < lstActivities.size() - 1) {
                    this.activitiesStr += ",";
                }

            }


        return activitiesStr;
    }

    public void setActivitiesStr(String activitiesStr) {
        this.activitiesStr = activitiesStr;
    }


    public List<ExcelImputedHomeDto> getLstHomes() {
        return lstHomes;
    }

    public void setLstHomes(List<ExcelImputedHomeDto> lstHomes) {
        this.lstHomes = lstHomes;
    }


    public String getHomesStr() {
        this.homesStr = "";

        if (this.lstHomes != null && this.lstHomes.size() > 0)


            for (ExcelImputedHomeDto act : this.lstHomes) {

                if (this.homesStr.isEmpty() == false)
                    this.homesStr += "\n";

                if (act.getAddress() != null && !act.getAddress().trim().equals(""))
                    this.homesStr += "," + act.getAddress();

                if (act.getRegType() != null && !act.getRegType().trim().equals(""))
                    this.homesStr += ", " + act.getRegType();

                if (act.getHomeType() != null && !act.getHomeType().trim().equals(""))
                    this.homesStr += ", " + act.getHomeType();

            }

        return homesStr;
    }

    public void setHomesStr(String homesStr) {
        this.homesStr = homesStr;
    }


    public String getImputedBirthdayStr() {
        return imputedBirthdayStr;
    }

    public void setImputedBirthdayStr(String imputedBirthdayStr) {
        this.imputedBirthdayStr = imputedBirthdayStr;
    }

    public List<ExcelSocialNetworkDto> getLstSN() {
        return lstSN;
    }

    public void setLstSN(List<ExcelSocialNetworkDto> lstSN) {
        this.lstSN = lstSN;
    }

    public String getSocialNetworkStr() {

        this.socialNetworkStr = "";

        if (this.lstSN != null && this.lstSN.size() > 0)

            for (ExcelSocialNetworkDto act : this.lstSN) {

                if (act.getBlock().equals(true)) {

                    if (this.socialNetworkStr.isEmpty() == false)
                        this.socialNetworkStr += "\n";

                    if (act.getName() != null && !act.getName().equals(""))
                        this.socialNetworkStr += "-" + act.getName();

                    if (act.getRelationship() != null && !act.getRelationship().equals(""))
                        this.socialNetworkStr += ", " + act.getRelationship();

                    if (act.getDocument() != null && !act.getDocument().equals(""))
                        this.socialNetworkStr += ", Identificación: " + act.getDocument();

                    if (act.getAge() != null)
                        this.socialNetworkStr += ", Edad: " + act.getAge();

                    if (act.getPhone() != null && !act.getPhone().equals(""))
                        this.socialNetworkStr += ", Tel.: " + act.getPhone();

                    if (act.getDependent() != null && !act.getDependent().equals(""))
                        this.socialNetworkStr += ", Dependiente: " + act.getDependent();

                    if (act.getAccompaniment() != null)
                        if (act.getAccompaniment().equals(true))
                            this.socialNetworkStr += ", Acompaña durante el proceso: Si";
                        else
                            this.socialNetworkStr += ", Acompaña durante el proceso: No";

                    if (act.getLivingWith() != null && !act.getLivingWith().equals(""))
                        this.socialNetworkStr += ", Vive con el imputado: " + act.getLivingWith();

                    if (act.getAddress() != null && !act.getAddress().equals(""))
                        this.socialNetworkStr += ", Dirección: " + act.getAddress();
                } else {
                    this.socialNetworkStr = "El imputado no cuenta con personas en su red social.";
                    break;
                }
            }

        return this.socialNetworkStr;
    }

    public void setSocialNetworkStr(String socialNetworkStr) {
        this.socialNetworkStr = socialNetworkStr;
    }

    public List<ExcelReferenceDto> getLstRef() {
        return lstRef;
    }

    public void setLstRef(List<ExcelReferenceDto> lstRef) {
        this.lstRef = lstRef;
    }


    public void setReferencesStr(String referencesStr) {
        this.referencesStr = referencesStr;
    }

    public String getReferencesStr() {

        referencesStr = "";
        if (this.lstRef != null && this.lstRef.size() > 0)
            for (ExcelReferenceDto act : this.lstRef) {

                if (act.getBlock().equals(true)) {
                    if (this.referencesStr.isEmpty() == false)
                        this.referencesStr += "\n";

                    if (act.getName() != null && !act.getName().equals(""))
                        this.referencesStr += "-" + act.getName();

                    if (act.getRelationship() != null && !act.getRelationship().equals(""))
                        this.referencesStr += ", " + act.getRelationship();

                    if (act.getDocument() != null && !act.getDocument().equals(""))
                        this.referencesStr += ", Identificación: " + act.getDocument();

                    if (act.getAge() != null)
                        this.referencesStr += ", Edad: " + act.getAge();

                    if (act.getPhone() != null && !act.getPhone().equals(""))
                        this.referencesStr += ", Tel.: " + act.getPhone();

                    if (act.getAccompaniment() != null)
                        if (act.getAccompaniment().equals(true))
                            this.referencesStr += ", Acompaña durante el proceso: Si";
                        else
                            this.referencesStr += ", Acompaña durante el proceso: No";

                    if (act.getAddress() != null && !act.getAddress().equals(""))
                        this.referencesStr += ", Dirección: " + act.getAddress();
                } else {
                    this.referencesStr = "El imputado no cuenta con referencias personales.";
                    break;
                }

            }

        return this.referencesStr;
    }

    public List<ExcelJobDto> getLstJob() {
        return lstJob;
    }

    public void setLstJob(List<ExcelJobDto> lstJob) {
        this.lstJob = lstJob;
    }

    public String getJobsStr() {
        jobsStr = "";

        if (this.lstJob != null && this.lstJob.size() > 0)
            for (ExcelJobDto act : this.lstJob) {
                if (act.getBlock().equals(true)) {
                    if (jobsStr.isEmpty() == false)
                        jobsStr += "\n";

                    if (act.getCompany() != null && !act.getCompany().equals(""))
                        jobsStr += "-" + act.getCompany();

                    if (act.getPost() != null && !act.getPost().equals(""))
                        jobsStr += ", Puesto: " + act.getPost();

                    if (act.getNameHead() != null && !act.getNameHead().equals(""))
                        jobsStr += ", Patrón: " + act.getNameHead();

                    if (act.getPhone() != null && !act.getPhone().equals(""))
                        jobsStr += ", Tel.: " + act.getPhone();

                    if (act.getAddress() != null && !act.getAddress().equals(""))
                        jobsStr += ", Dirección: " + act.getAddress();

                    if (act.getRegisterType() != null && !act.getRegisterType().equals(""))
                        jobsStr += ", Tipo: " + act.getRegisterType();

                    Long idType = act.getRegisterTypeId();

                    if (idType != null && (idType.equals(Constants.REGYSTER_TYPE_CURRENT) || idType.equals(Constants.REGYSTER_TYPE_SECONDARY))) {

                        jobsStr += ", Inicio: " + dateFormat.format(act.getStart());
                        jobsStr += ", Salario semanal: $" + act.getSalary();

                    } else if (idType != null && idType.equals(Constants.REGYSTER_TYPE_PREVIOUS)) {
                        jobsStr += ", Inicio: " + dateFormat.format(act.getStartPrev());
                        jobsStr += ", Fin: " + dateFormat.format(act.getEnd());
                        jobsStr += ", Motivo de cambio: " + act.getReasonChange();
                    }
                } else {
                    jobsStr = "El imputado no tiene trabajo actual";
                    break;
                }
            }

        return jobsStr;
    }

    public void setJobsStr(String jobsStr) {
        this.jobsStr = jobsStr;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }


    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public FramingMeetingInfo getFramingMeetingInfo() {
        return framingMeetingInfo;
    }

    public void setFramingMeetingInfo(FramingMeetingInfo framingMeetingInfo) {
        this.framingMeetingInfo = framingMeetingInfo;
    }

    public Long getIdMonP() {
        return idMonP;
    }

    public void setIdMonP(Long idMonP) {
        this.idMonP = idMonP;
    }

    public MonitoringPlanExcelInfo getMonitoringPlanExcelInfo() {
        return monitoringPlanExcelInfo;
    }

    public void setMonitoringPlanExcelInfo(MonitoringPlanExcelInfo monitoringPlanExcelInfo) {
        this.monitoringPlanExcelInfo = monitoringPlanExcelInfo;
    }

    public String getHomeType() {
        this.homeType = "";

        if (lstHomes != null) {
            for (int i = 0; i < lstHomes.size(); i++) {
                if (lstHomes.get(i).getHomeType() != null) {
                    this.homeType += lstHomes.get(i).getHomeType();
                    if (i < lstHomes.size() - 1) {
                        this.homeType += ",";
                    }
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

        if (lstHomes != null) {
            for (int i = 0; i < lstHomes.size(); i++) {
                if (lstHomes.get(i).getRegType() != null) {
                    this.regType += lstHomes.get(i).getRegType();
                    if (i < lstHomes.size() - 1) {
                        this.regType += ",";
                    }
                }

            }
        }
        return this.regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }


}

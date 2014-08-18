package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.Constants;

import java.net.Inet4Address;
import java.util.Date;

/**
 * Created by Vmware on 12/08/2014.
 */
public class CaseInfoDto {

    private Long idCase;
    private String idFolder;
    private String idMP;
    private Date createDate;
    private String statusCase;
    private String imputedName;
    private String imputedAlias;
    private Boolean imputedGender;
    private Date imputedBirthDate;
    private String imputedBirthCountry;
    private String imputedBirthState;
    private String imputedBirthMunicipality;
    private String imputedBirthLocation;
    private String imputedCelPhone;
    private String imputedMaritalStatus;
    private Integer imputedYearsMaritalStatus;
    private Integer imputedChildren;
    private Integer imputedChildrenDependant;
    private String imputedPhysicalCondition;
    private String imputedSchoolName;
    private String imputedSchoolPhone;
    private String imputedSchoolAddress;
    private String imputedSchoolLevel;
    private String imputedSchoolDegree;
    private String imputedLivedAnotherCountry;
    private String imputedLivedCountry;
    private String imputedLivedState;
    private String imputedLivedTimeAgo;
    private String imputedLivedChangeReason;
    private String imputedLivedAddress;
    private String imputedLivedRelativeAbroad;
    private String imputedLivedRelativeAbroadCommunication;
    private String imputedLivedRelativeAbroadMedia;
    private String imputedLegalBehaviorDetention;
    private String imputedLegalPlaceDetention;
    private String imputedLegalVictimName;
    private String imputedLegalVictimRelationship;
    private String imputedLegalVictimAddress;
    private String imputedLegalFirstProcess;
    private Integer imputedLegalOpenProcess;
    private Integer imputedLegalConvictions;
    private String imputedLegalAccomplishAssignedArrangement;
    private String imputedLegalAccomplishSCPP;
    private String imputedLegalAccomplishProcessAbove;
    private Long tecRevId;
    private Integer totalRisk;
    private String tecRevComments;
    private String subtotalsJson;
    private Long idVerification;

    private String imputedGenderStr;
    private String tecRevCommentsStr;

    public CaseInfoDto(Long idCase,
                       String idFolder,
                       String idMP,
                       Date createDate,
                       String statusCase,
                       String imputedName,
                       String imputedAlias,
                       Boolean imputedGender,
                       Date imputedBirthDate,
                       String imputedBirthCountry,
                       String imputedBirthState,
                       String imputedBirthMunicipality,
                       String imputedBirthLocation,
                       String imputedCelPhone,
                       String imputedMaritalStatus,
                       Integer imputedYearsMaritalStatus,
                       Integer imputedChildren,
                       Integer imputedChildrenDependant,
                       String imputedPhysicalCondition,
                       String imputedSchoolName,
                       String imputedSchoolPhone,
                       String imputedSchoolAddress,
                       String imputedSchoolLevel,
                       String imputedSchoolDegree,
                       String imputedLivedAnotherCountry,
                       String imputedLivedCountry,
                       String imputedLivedState,
                       String imputedLivedTimeAgo,
                       String imputedLivedChangeReason,
                       String imputedLivedAddress,
                       String imputedLivedRelativeAbroad,
                       String imputedLivedRelativeAbroadCommunication,
                       String imputedLivedRelativeAbroadMedia,
                       String imputedLegalBehaviorDetention,
                       String imputedLegalPlaceDetention,
                       String imputedLegalVictimName,
                       String imputedLegalVictimRelationship,
                       String imputedLegalVictimAddress,
                       String imputedLegalFirstProcess,
                       Integer imputedLegalOpenProcess,
                       Integer imputedLegalConvictions,
                       String imputedLegalAccomplishAssignedArrangement,
                       String imputedLegalAccomplishSCPP,
                       String imputedLegalAccomplishProcessAbove,
                       Long tecRevId,
                       Integer totalRisk,
                       String tecRevComments,
                       String subtotalsJson,
                       Long idVerification) {

        this.idCase = idCase;
        this.idFolder = idFolder;
        this.idMP = idMP;
        this.createDate = createDate;
        this.statusCase = statusCase;
        this.imputedName = imputedName;
        this.imputedAlias = imputedAlias;
        this.imputedGender = imputedGender;
        this.imputedBirthDate = imputedBirthDate;
        this.imputedBirthCountry = imputedBirthCountry;
        this.imputedBirthState = imputedBirthState;
        this.imputedBirthMunicipality = imputedBirthMunicipality;
        this.imputedBirthLocation = imputedBirthLocation;
        this.imputedCelPhone = imputedCelPhone;
        this.imputedMaritalStatus = imputedMaritalStatus;
        this.imputedYearsMaritalStatus = imputedYearsMaritalStatus;
        this.imputedChildren = imputedChildren;
        this.imputedChildrenDependant = imputedChildrenDependant;
        this.imputedPhysicalCondition = imputedPhysicalCondition;
        this.imputedSchoolName = imputedSchoolName;
        this.imputedSchoolPhone = imputedSchoolPhone;
        this.imputedSchoolAddress = imputedSchoolAddress;
        this.imputedSchoolLevel = imputedSchoolLevel;
        this.imputedSchoolDegree = imputedSchoolDegree;
        this.imputedLivedAnotherCountry = imputedLivedAnotherCountry;
        this.imputedLivedCountry = imputedLivedCountry;
        this.imputedLivedState = imputedLivedState;
        this.imputedLivedTimeAgo = imputedLivedTimeAgo;
        this.imputedLivedChangeReason = imputedLivedChangeReason;
        this.imputedLivedAddress = imputedLivedAddress;
        this.imputedLivedRelativeAbroad = imputedLivedRelativeAbroad;
        this.imputedLivedRelativeAbroadCommunication = imputedLivedRelativeAbroadCommunication;
        this.imputedLivedRelativeAbroadMedia = imputedLivedRelativeAbroadMedia;
        this.imputedLegalBehaviorDetention = imputedLegalBehaviorDetention;
        this.imputedLegalPlaceDetention = imputedLegalPlaceDetention;
        this.imputedLegalVictimName = imputedLegalVictimName;
        this.imputedLegalVictimRelationship = imputedLegalVictimRelationship;
        this.imputedLegalVictimAddress = imputedLegalVictimAddress;
        this.imputedLegalFirstProcess = imputedLegalFirstProcess;
        this.imputedLegalOpenProcess = imputedLegalOpenProcess;
        this.imputedLegalConvictions = imputedLegalConvictions;
        this.imputedLegalAccomplishAssignedArrangement = imputedLegalAccomplishAssignedArrangement;
        this.imputedLegalAccomplishSCPP = imputedLegalAccomplishSCPP;
        this.imputedLegalAccomplishProcessAbove = imputedLegalAccomplishProcessAbove;
        this.tecRevId = tecRevId;
        this.totalRisk = totalRisk;
        this.tecRevComments = tecRevComments;
        this.subtotalsJson = subtotalsJson;
        this.idVerification = idVerification;
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

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatusCase() {
        return statusCase;
    }

    public void setStatusCase(String statusCase) {
        this.statusCase = statusCase;
    }

    public String getImputedName() {
        return imputedName;
    }

    public void setImputedName(String imputedName) {
        this.imputedName = imputedName;
    }

    public Boolean getImputedGender() {
        return imputedGender;
    }

    public void setImputedGender(Boolean imputedGender) {
        this.imputedGender = imputedGender;
    }

    public String getImputedAlias() {
        return imputedAlias;
    }

    public void setImputedAlias(String imputedAlias) {
        this.imputedAlias = imputedAlias;
    }

    public Date getImputedBirthDate() {
        return imputedBirthDate;
    }

    public void setImputedBirthDate(Date imputedBirthDate) {
        this.imputedBirthDate = imputedBirthDate;
    }

    public String getImputedBirthCountry() {
        return imputedBirthCountry;
    }

    public void setImputedBirthCountry(String imputedBirthCountry) {
        this.imputedBirthCountry = imputedBirthCountry;
    }

    public String getImputedBirthState() {
        return imputedBirthState;
    }

    public void setImputedBirthState(String imputedBirthState) {
        this.imputedBirthState = imputedBirthState;
    }

    public String getImputedBirthMunicipality() {
        return imputedBirthMunicipality;
    }

    public void setImputedBirthMunicipality(String imputedBirthMunicipality) {
        this.imputedBirthMunicipality = imputedBirthMunicipality;
    }

    public String getImputedBirthLocation() {
        return imputedBirthLocation;
    }

    public void setImputedBirthLocation(String imputedBirthLocation) {
        this.imputedBirthLocation = imputedBirthLocation;
    }

    public String getImputedCelPhone() {
        return imputedCelPhone;
    }

    public void setImputedCelPhone(String imputedCelPhone) {
        this.imputedCelPhone = imputedCelPhone;
    }

    public String getImputedMaritalStatus() {
        return imputedMaritalStatus;
    }

    public void setImputedMaritalStatus(String imputedMaritalStatus) {
        this.imputedMaritalStatus = imputedMaritalStatus;
    }

    public Integer getImputedYearsMaritalStatus() {
        return imputedYearsMaritalStatus;
    }

    public void setImputedYearsMaritalStatus(Integer imputedYearsMaritalStatus) {
        this.imputedYearsMaritalStatus = imputedYearsMaritalStatus;
    }

    public Integer getImputedChildren() {
        return imputedChildren;
    }

    public void setImputedChildren(Integer imputedChildren) {
        this.imputedChildren = imputedChildren;
    }

    public Integer getImputedChildrenDependant() {
        return imputedChildrenDependant;
    }

    public void setImputedChildrenDependant(Integer imputedChildrenDependant) {
        this.imputedChildrenDependant = imputedChildrenDependant;
    }

    public String getImputedPhysicalCondition() {
        return imputedPhysicalCondition;
    }

    public void setImputedPhysicalCondition(String imputedPhysicalCondition) {
        this.imputedPhysicalCondition = imputedPhysicalCondition;
    }
/*
    public String getImputedActivities() {
        return imputedActivities;
    }

    public void setImputedActivities(String imputedActivities) {
        this.imputedActivities = imputedActivities;
    }

    public String getImputedHomes() {
        return imputedHomes;
    }

    public void setImputedHomes(String imputedHomes) {
        this.imputedHomes = imputedHomes;
    }

    public String getImputedSocialNetwork() {
        return imputedSocialNetwork;
    }

    public void setImputedSocialNetwork(String imputedSocialNetwork) {
        this.imputedSocialNetwork = imputedSocialNetwork;
    }

    public String getImputedReferences() {
        return imputedReferences;
    }

    public void setImputedReferences(String imputedReferences) {
        this.imputedReferences = imputedReferences;
    }

    public String getImputedJobs() {
        return imputedJobs;
    }

    public void setImputedJobs(String imputedJobs) {
        this.imputedJobs = imputedJobs;
    }*/

    public String getImputedSchoolName() {
        return imputedSchoolName;
    }

    public void setImputedSchoolName(String imputedSchoolName) {
        this.imputedSchoolName = imputedSchoolName;
    }

    public String getImputedSchoolPhone() {
        return imputedSchoolPhone;
    }

    public void setImputedSchoolPhone(String imputedSchoolPhone) {
        this.imputedSchoolPhone = imputedSchoolPhone;
    }

    public String getImputedSchoolAddress() {
        return imputedSchoolAddress;
    }

    public void setImputedSchoolAddress(String imputedSchoolAddress) {
        this.imputedSchoolAddress = imputedSchoolAddress;
    }

    public String getImputedSchoolLevel() {
        return imputedSchoolLevel;
    }

    public void setImputedSchoolLevel(String imputedSchoolLevel) {
        this.imputedSchoolLevel = imputedSchoolLevel;
    }

    public String getImputedSchoolDegree() {
        return imputedSchoolDegree;
    }

    public void setImputedSchoolDegree(String imputedSchoolDegree) {
        this.imputedSchoolDegree = imputedSchoolDegree;
    }

  /*  public String getImputedDrugs() {
        return imputedDrugs;
    }

    public void setImputedDrugs(String imputedDrugs) {
        this.imputedDrugs = imputedDrugs;
    }*/

    public String getImputedLivedAnotherCountry() {
        return imputedLivedAnotherCountry;
    }

    public void setImputedLivedAnotherCountry(String imputedLivedAnotherCountry) {
        this.imputedLivedAnotherCountry = imputedLivedAnotherCountry;
    }

    public String getImputedLivedCountry() {
        return imputedLivedCountry;
    }

    public void setImputedLivedCountry(String imputedLivedCountry) {
        this.imputedLivedCountry = imputedLivedCountry;
    }

    public String getImputedLivedState() {
        return imputedLivedState;
    }

    public void setImputedLivedState(String imputedLivedState) {
        this.imputedLivedState = imputedLivedState;
    }

    public String getImputedLivedTimeAgo() {
        return imputedLivedTimeAgo;
    }

    public void setImputedLivedTimeAgo(String imputedLivedTimeAgo) {
        this.imputedLivedTimeAgo = imputedLivedTimeAgo;
    }

    public String getImputedLivedChangeReason() {
        return imputedLivedChangeReason;
    }

    public void setImputedLivedChangeReason(String imputedLivedChangeReason) {
        this.imputedLivedChangeReason = imputedLivedChangeReason;
    }

    public String getImputedLivedAddress() {
        return imputedLivedAddress;
    }

    public void setImputedLivedAddress(String imputedLivedAddress) {
        this.imputedLivedAddress = imputedLivedAddress;
    }

    public String getImputedLivedRelativeAbroad() {
        return imputedLivedRelativeAbroad;
    }

    public void setImputedLivedRelativeAbroad(String imputedLivedRelativeAbroad) {
        this.imputedLivedRelativeAbroad = imputedLivedRelativeAbroad;
    }

    public String getImputedLivedRelativeAbroadCommunication() {
        return imputedLivedRelativeAbroadCommunication;
    }

    public void setImputedLivedRelativeAbroadCommunication(String imputedLivedRelativeAbroadCommunication) {
        this.imputedLivedRelativeAbroadCommunication = imputedLivedRelativeAbroadCommunication;
    }

    public String getImputedLivedRelativeAbroadMedia() {
        return imputedLivedRelativeAbroadMedia;
    }

    public void setImputedLivedRelativeAbroadMedia(String imputedLivedRelativeAbroadMedia) {
        this.imputedLivedRelativeAbroadMedia = imputedLivedRelativeAbroadMedia;
    }
/*
    public String getImputedLegalCrimes() {
        return imputedLegalCrimes;
    }

    public void setImputedLegalCrimes(String imputedLegalCrimes) {
        this.imputedLegalCrimes = imputedLegalCrimes;
    }

    public String getImputedLegalCoAccused() {
        return imputedLegalCoAccused;
    }

    public void setImputedLegalCoAccused(String imputedLegalCoAccused) {
        this.imputedLegalCoAccused = imputedLegalCoAccused;
    }*/

    public String getImputedLegalBehaviorDetention() {
        return imputedLegalBehaviorDetention;
    }

    public void setImputedLegalBehaviorDetention(String imputedLegalBehaviorDetention) {
        this.imputedLegalBehaviorDetention = imputedLegalBehaviorDetention;
    }

    public String getImputedLegalPlaceDetention() {
        return imputedLegalPlaceDetention;
    }

    public void setImputedLegalPlaceDetention(String imputedLegalPlaceDetention) {
        this.imputedLegalPlaceDetention = imputedLegalPlaceDetention;
    }

    public String getImputedLegalVictimName() {
        return imputedLegalVictimName;
    }

    public void setImputedLegalVictimName(String imputedLegalVictimName) {
        this.imputedLegalVictimName = imputedLegalVictimName;
    }

    public String getImputedLegalVictimRelationship() {
        return imputedLegalVictimRelationship;
    }

    public void setImputedLegalVictimRelationship(String imputedLegalVictimRelationship) {
        this.imputedLegalVictimRelationship = imputedLegalVictimRelationship;
    }

    public String getImputedLegalVictimAddress() {
        return imputedLegalVictimAddress;
    }

    public void setImputedLegalVictimAddress(String imputedLegalVictimAddress) {
        this.imputedLegalVictimAddress = imputedLegalVictimAddress;
    }

    public String getImputedLegalFirstProcess() {
        return imputedLegalFirstProcess;
    }

    public void setImputedLegalFirstProcess(String imputedLegalFirstProcess) {
        this.imputedLegalFirstProcess = imputedLegalFirstProcess;
    }

    public Integer getImputedLegalOpenProcess() {
        return imputedLegalOpenProcess;
    }

    public void setImputedLegalOpenProcess(Integer imputedLegalOpenProcess) {
        this.imputedLegalOpenProcess = imputedLegalOpenProcess;
    }

    public Integer getImputedLegalConvictions() {
        return imputedLegalConvictions;
    }

    public void setImputedLegalConvictions(Integer imputedLegalConvictions) {
        this.imputedLegalConvictions = imputedLegalConvictions;
    }

    public String getImputedLegalAccomplishAssignedArrangement() {
        return imputedLegalAccomplishAssignedArrangement;
    }

    public void setImputedLegalAccomplishAssignedArrangement(String imputedLegalAccomplishAssignedArrangement) {
        this.imputedLegalAccomplishAssignedArrangement = imputedLegalAccomplishAssignedArrangement;
    }

    public String getImputedLegalAccomplishSCPP() {
        return imputedLegalAccomplishSCPP;
    }

    public void setImputedLegalAccomplishSCPP(String imputedLegalAccomplishSCPP) {
        this.imputedLegalAccomplishSCPP = imputedLegalAccomplishSCPP;
    }

    public String getImputedLegalAccomplishProcessAbove() {
        return imputedLegalAccomplishProcessAbove;
    }

    public void setImputedLegalAccomplishProcessAbove(String imputedLegalAccomplishProcessAbove) {
        this.imputedLegalAccomplishProcessAbove = imputedLegalAccomplishProcessAbove;
    }

    public String getImputedGenderStr() {

        if (this.imputedGender == true)
            return "Femenino";
        else if (this.imputedGender == false)
            return "Masculino";

        return "";
    }

    public void setImputedGenderStr(String imputedGenderStr) {
        this.imputedGenderStr = imputedGenderStr;
    }

    public Long getTecRevId() {
        return tecRevId;
    }

    public void setTecRevId(Long tecRevId) {
        this.tecRevId = tecRevId;
    }

    public Integer getTotalRisk() {
        return totalRisk;
    }

    public void setTotalRisk(Integer totalRisk) {
        this.totalRisk = totalRisk;
    }

    public String getTecRevComments() {
        return tecRevComments;
    }

    public void setTecRevComments(String tecRevComments) {
        this.tecRevComments = tecRevComments;
    }

    public String getSubtotalsJson() {
        return subtotalsJson;
    }

    public void setSubtotalsJson(String subtotalsJson) {
        this.subtotalsJson = subtotalsJson;
    }

    public String getTecRevCommentsStr() {
        String totCom = "";
        if (this.totalRisk != null) {
            totCom += "-Total: " + this.totalRisk + "\n";

            if (this.totalRisk < -15)
                totCom += "-" + Constants.TEC_REV_HIGH_RISK + "\n";
            else if (this.totalRisk > -16 && this.totalRisk < 0)
                totCom += "-" + Constants.TEC_REV_MEDIUM_RISK + "\n";
            else if (this.totalRisk > -1 && this.totalRisk < 10)
                totCom += "-" + Constants.TEC_REV_LOW_RISK + "\n";
            else if (this.totalRisk > 9)
                totCom += "-" + Constants.TEC_REV_MINIMUM_RISK + "\n";

            totCom += "-Comentarios: " + this.tecRevComments;
        }

        return totCom;

    }

    public void setTecRevCommentsStr(String tecRevCommentsStr) {
        this.tecRevCommentsStr = tecRevCommentsStr;
    }

    public Long getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(Long idVerification) {
        this.idVerification = idVerification;
    }
}


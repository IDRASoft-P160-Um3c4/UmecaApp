package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vmware on 12/08/2014.
 */
public class ExcelCaseInfoDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

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
    private String officialDoc;
    private Long tecRevId;
    private Integer totalRisk;
    private String tecRevComments;
    private String subtotalsJson;
    private Long idVerification;
    private List<ExcelActivitiesDto> lstActivities;
    private String activitiesStr;
    private List<ExcelImputedHomeDto> lstHomes;
    private String homesStr;
    private List<ExcelSocialNetworkDto> lstSN;
    private String socialNetworkStr;
    private List<ExcelReferenceDto> lstRef;
    private String referencesStr;
    private List<ExcelJobDto> lstJob;
    private String jobsStr;
    private List<ExcelDrugDto> lstDrug;
    private String drugsStr;
    private List<ExcelCrimeDto> lstCrimes;
    private String crimesStr;
    private List<ExcelCoDefDto> lstCoDef;
    private String coDefStr;
    private List<ExcelTecRevSelQuestDto> lstSelQuest;
    private String selQuestStr;

    private String imputedGenderStr;
    private String tecRevCommentsStr;

    private List<HearingFormatInfo> formatsInfo;
    private FramingMeetingInfo framingMeetingInfo;
    private Long idMonP;
    private MonitoringPlanExcelInfo monitoringPlanExcelInfo;

    public ExcelCaseInfoDto(Long idCase,
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
//                            String imputedLegalVictimName,
//                            String imputedLegalVictimRelationship,
//                            String imputedLegalVictimAddress,
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
                            Long idVerification,
                            String officialDoc,
                            Long idMonP) {

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
        this.officialDoc = officialDoc;
        this.idMonP = idMonP;
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

    public String getOfficialDoc() {
        return officialDoc;
    }

    public void setOfficialDoc(String officialDoc) {
        this.officialDoc = officialDoc;
    }

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

    public List<ExcelActivitiesDto> getLstActivities() {
        return lstActivities;
    }

    public void setLstActivities(List<ExcelActivitiesDto> lstActivities) {
        this.lstActivities = lstActivities;
    }

    public String getActivitiesStr() {
        this.activitiesStr = "";

        if (this.lstActivities != null && lstActivities.size() > 0)
            for (ExcelActivitiesDto act : this.lstActivities) {
                if (activitiesStr != "")
                    activitiesStr += "\n ";

                if (act.getNameAct() != null && !act.getNameAct().trim().equals(""))
                    activitiesStr += "-" + act.getNameAct();

                if (act.getDescription() != null && !act.getDescription().trim().equals(""))
                    activitiesStr += ": " + act.getDescription();
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

                if (this.homesStr != "")
                    this.homesStr += "\n";

                if (act.getAddress() != null && !act.getAddress().trim().equals(""))
                    this.homesStr += "-" + act.getAddress();

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
                if (this.socialNetworkStr != "")
                    this.socialNetworkStr += "\n";

                if (act.getName() != null && !act.getName().equals(""))
                    this.socialNetworkStr += "-" + act.getName();

                if (act.getRelationship() != null && !act.getRelationship().equals(""))
                    this.socialNetworkStr += ", " + act.getRelationship();

                if (act.getDocument() != null && !act.getDocument().equals(""))
                    this.socialNetworkStr += ", Identificaci�n: " + act.getDocument();

                if (act.getAge() != null)
                    this.socialNetworkStr += ", Edad: " + act.getAge();

                if (act.getPhone() != null && !act.getPhone().equals(""))
                    this.socialNetworkStr += ", Tel.: " + act.getPhone();

                if (act.getDependent() != null && !act.getDependent().equals(""))
                    this.socialNetworkStr += ", Dependiente: " + act.getDependent();

                if (act.getAccompaniment() != null)
                    if (act.getAccompaniment().equals(true))
                        this.socialNetworkStr += ", Acompa�a durante el proceso: Si";
                    else
                        this.socialNetworkStr += ", Acompa�a durante el proceso: No";

                if (act.getLivingWith() != null && !act.getLivingWith().equals(""))
                    this.socialNetworkStr += ", Vive con el imputado: " + act.getLivingWith();

                if (act.getAddress() != null && !act.getAddress().equals(""))
                    this.socialNetworkStr += ", Direcci�n: " + act.getAddress();

            }

        return socialNetworkStr;
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

    public String getReferencesStr() {

        referencesStr = "";
        if (this.lstRef != null && this.lstRef.size() > 0)
            for (ExcelReferenceDto act : this.lstRef) {

                if (this.referencesStr != "")
                    this.referencesStr += "\n";

                if (act.getName() != null && !act.getName().equals(""))
                    this.referencesStr += "-" + act.getName();

                if (act.getRelationship() != null && !act.getRelationship().equals(""))
                    this.referencesStr += ", " + act.getRelationship();

                if (act.getDocument() != null && !act.getDocument().equals(""))
                    this.referencesStr += ", Identificaci�n: " + act.getDocument();

                if (act.getAge() != null)
                    this.referencesStr += ", Edad: " + act.getAge();

                if (act.getPhone() != null && !act.getPhone().equals(""))
                    this.referencesStr += ", Tel.: " + act.getPhone();

                if (act.getAccompaniment() != null)
                    if (act.getAccompaniment().equals(true))
                        this.referencesStr += ", Acompa�a durante el proceso: Si";
                    else
                        this.referencesStr += ", Acompa�a durante el proceso: No";

                if (act.getAddress() != null && !act.getAddress().equals(""))
                    this.referencesStr += ", Direcci�n: " + act.getAddress();

            }

        return referencesStr;
    }

    public void setReferencesStr(String referencesStr) {
        this.referencesStr = referencesStr;
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
                if (jobsStr != "")
                    jobsStr += "\n";

                if (act.getCompany() != null && !act.getCompany().equals(""))
                    jobsStr += "-" + act.getCompany();

                if (act.getPost() != null && !act.getPost().equals(""))
                    jobsStr += ", Puesto: " + act.getPost();

                if (act.getNameHead() != null && !act.getNameHead().equals(""))
                    jobsStr += ", Patr�n: " + act.getNameHead();

                if (act.getPhone() != null && !act.getPhone().equals(""))
                    jobsStr += ", Tel.: " + act.getPhone();

                if (act.getAddress() != null && !act.getAddress().equals(""))
                    jobsStr += ", Direcci�n: " + act.getAddress();

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
            }

        return jobsStr;
    }

    public void setJobsStr(String jobsStr) {
        this.jobsStr = jobsStr;
    }

    public List<ExcelDrugDto> getLstDrug() {
        return lstDrug;
    }

    public void setLstDrug(List<ExcelDrugDto> lstDrug) {
        this.lstDrug = lstDrug;
    }

    public String getDrugsStr() {

        drugsStr = "";

        if (lstDrug != null && lstDrug.size() > 0)
            for (ExcelDrugDto act : lstDrug) {
                if (drugsStr != "")
                    drugsStr += "\n";

                if (act.getDrugType() != null && !act.getDrugType().equals(""))
                    drugsStr += "-" + act.getDrugType();

                if (act.getPeriodicity() != null && !act.getPeriodicity().equals(""))
                    drugsStr += ", Periocidad: " + act.getPeriodicity();

                if (act.getSpecificationType() != null && !act.getSpecificationType().equals(""))
                    drugsStr += ", Especificaci�n: " + act.getSpecificationType();

                if (act.getQuantity() != null && !act.getQuantity().equals(""))
                    drugsStr += ", Cantidad: " + act.getQuantity();

                if (act.getLastUse() != null)
                    drugsStr += ", �tlimo consumo: " + dateFormat.format(act.getLastUse());
            }

        return drugsStr;
    }

    public void setDrugsStr(String drugsStr) {
        this.drugsStr = drugsStr;
    }

    public List<ExcelCrimeDto> getLstCrimes() {
        return lstCrimes;
    }

    public void setLstCrimes(List<ExcelCrimeDto> lstCrimes) {
        this.lstCrimes = lstCrimes;
    }

    public String getCrimesStr() {
        crimesStr = "";
        if (lstCrimes != null && lstCrimes.size() > 0)
            for (ExcelCrimeDto act : lstCrimes) {
                if (crimesStr != "")
                    crimesStr += "\n";

                if (act.getCrime() != null && !act.getCrime().equals(""))
                    crimesStr += "-" + act.getCrime();
                if (act.getArticle() != null && !act.getArticle().equals(""))
                    crimesStr += ", Art�culo: " + act.getArticle();
                if (act.getFederal() != null && !act.getFederal().equals(""))
                    crimesStr += ", Delito federal: " + act.getFederal();
            }

        return crimesStr;
    }

    public void setCrimesStr(String crimesStr) {
        this.crimesStr = crimesStr;
    }

    public List<ExcelCoDefDto> getLstCoDef() {
        return lstCoDef;
    }

    public void setLstCoDef(List<ExcelCoDefDto> lstCoDef) {
        this.lstCoDef = lstCoDef;
    }

    public String getCoDefStr() {

        coDefStr = "";
        if (this.lstCoDef != null && this.lstCoDef.size() > 0)
            for (ExcelCoDefDto act : this.lstCoDef) {
                if (coDefStr != "")
                    coDefStr += "\n";

                if (act.getName() != null && !act.getName().equals(""))
                    coDefStr += "-" + act.getName();
                if (act.getRelationship() != null && !act.getRelationship().equals(""))
                    coDefStr += ", " + act.getRelationship();
            }

        return coDefStr;
    }

    public void setCoDefStr(String coDefStr) {
        this.coDefStr = coDefStr;
    }

    public List<ExcelTecRevSelQuestDto> getLstSelQuest() {
        return lstSelQuest;
    }

    public void setLstSelQuest(List<ExcelTecRevSelQuestDto> lstSelQuest) {
        this.lstSelQuest = lstSelQuest;
    }

    public String getSelQuestStr() {
        return selQuestStr;
    }

    public void setSelQuestStr(String selQuestStr) {
        this.selQuestStr = selQuestStr;
    }

    public String getQuest(Integer idSect) {
        selQuestStr = "";
        String sCode = "";

        switch (idSect) {
            case 1:
                sCode = Constants.CODE_S1_TEC_REV;
                break;
            case 2:
                sCode = Constants.CODE_S2_TEC_REV;
                break;
            case 3:
                sCode = Constants.CODE_S3_TEC_REV;
                break;
            case 4:
                sCode = Constants.CODE_S4_TEC_REV;
                break;
            case 5:
                sCode = Constants.CODE_S5_TEC_REV;
                break;
        }

        List<ExcelSectDto> lstSubsect = new ArrayList<>();

        if (this.lstSelQuest != null && this.lstSelQuest.size() > 0) {

            for (ExcelTecRevSelQuestDto qAct : this.lstSelQuest) {
                if (qAct.getParentCode().equals(sCode)) {
                    ExcelSectDto subsec = new ExcelSectDto();
                    subsec.setSectCode(qAct.getCode());
                    subsec.setSectName(qAct.getSubSectName());

                    boolean band = false;
                    for (ExcelSectDto ssct : lstSubsect) {
                        if (ssct.getSectName().equals(subsec.getSectName()) && ssct.getSectCode().equals(subsec.getSectCode())) {
                            band = true;
                            break;
                        }
                    }

                    if (band == false)
                        lstSubsect.add(subsec);
                }
            }

            for (ExcelSectDto secAct : lstSubsect) {

                if (selQuestStr != "")
                    selQuestStr += "\n";

                selQuestStr += "- " + secAct.getSectName();

                for (ExcelTecRevSelQuestDto quAct : this.lstSelQuest) {

                    if (quAct.getCode().equals(secAct.getSectCode())) {
                        selQuestStr += "\n  ." + quAct.getQuestion();
                    }
                }
            }
        }

        return selQuestStr;
    }

    public List<HearingFormatInfo> getFormatsInfo() {
        return formatsInfo;
    }

    public void setFormatsInfo(List<HearingFormatInfo> formatsInfo) {
        this.formatsInfo = formatsInfo;
    }

    public FramingMeetingInfo getFramingMeetingInfo() {
        return framingMeetingInfo;
    }

    public void setFramingMeetingInfo(FramingMeetingInfo framingMeetingInfo) {
        this.framingMeetingInfo = framingMeetingInfo;
    }

    public MonitoringPlanExcelInfo getMonitoringPlanExcelInfo() {
        return monitoringPlanExcelInfo;
    }

    public void setMonitoringPlanExcelInfo(MonitoringPlanExcelInfo monitoringPlanExcelInfo) {
        this.monitoringPlanExcelInfo = monitoringPlanExcelInfo;
    }

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public Long getIdMonP() {
        return idMonP;
    }

    public void setIdMonP(Long idMonP) {
        this.idMonP = idMonP;
    }
}


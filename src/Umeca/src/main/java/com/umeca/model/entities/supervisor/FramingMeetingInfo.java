package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.catalog.dto.ScheduleDto;
import com.umeca.model.entities.reviewer.School;
import com.umeca.model.entities.reviewer.dto.JobDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FramingMeetingInfo {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    private Long idCase;
    private String idFolder;
    private String idMP;
    private Date dateCreate;

    private String imputedName;
    private String imputedLNP;
    private String imputedLNM;
    private String imputedFullName;
    private Integer imputedGender;
    private String imputedGenderStr;
    private Date imputedBirthDate;
    private String imputedBirthCountry;
    private String imputedBirthStateCmb;
    private String imputedBirthState;
    private String imputedMaritalStatus;
    private String imputedYearsMaritalStatus;
    private String imputedPhysicalCondition;

    private String imputedActivities;
    private String imputedOccupation;
    private String imputedOccupationPlace;
    private String imputedOccupationPhone;

    private Integer addictionTreatment;
    private String addictionTreatmentStr;
    private String addictionTreatmentInstitute;
    private Date addictionTreatmentDate;

    private Integer addictedAcquaintance;
    private String addictedAcquaintanceStr;
    private List<CatalogDto> addictedAcquaintances;

    private Integer relativeAbroad;
    private String relativeAbroadStr;
    private List<ObligationIssuesInfo> relativesAbroad;

    private Integer obligationIssue;
    private String obligationIssueStr;
    private List<ObligationIssuesInfo> obligationIssues;

    private String additionalQuestionsObs;

    private List<CatalogDto> links;
    private List<CatalogDto> risks;
    private List<CatalogDto> threats;
    private List<String> arrangements;
    private String specificationRelationship;

    private List<FramingReferenceInfo> references;
    private List<ExcelDrugDto> drugs;
    private List<CatalogDto> homes;

    private List<ExcelActivitiesDto> activities;
    private SchoolDto school;
    private List<ExcelJobDto> jobs;

    private List<CatalogDto> summaryHomes;

    public FramingMeetingInfo() {
    }

    public FramingMeetingInfo(Long idCase, String idFolder, String idMP, Date dateCreate, String imputedName, String imputedLNP, String imputedLNM,
                              Integer imputedGender, String imputedMaritalStatus, String imputedYearsMaritalStatus, String imputedBirthCountry,
                              String imputedBirthStateCmb, String imputedBirthState, Date imputedBirthDate, String imputedPhysicalCondition,
                              String imputedOccupation, String imputedOccupationPlace, String imputedOccupationPhone, String additionalQuestionsObs, Integer addictionTreatment, String addictionTreatmentInstitute,
                              Date addictionTreatmentDate, Integer addictedAcquaintance, Integer relativeAbroad, Integer obligationIssue) {


        this.idCase = idCase;
        this.idFolder = idFolder;
        this.idMP = idMP;
        this.dateCreate = dateCreate;
        this.imputedName = imputedName;
        this.imputedLNP = imputedLNP;
        this.imputedLNM = imputedLNM;
        this.imputedGender = imputedGender;
        this.imputedMaritalStatus = imputedMaritalStatus;
        this.imputedYearsMaritalStatus = imputedYearsMaritalStatus;
        this.imputedBirthCountry = imputedBirthCountry;
        this.imputedBirthStateCmb = imputedBirthStateCmb;
        this.imputedBirthState = imputedBirthState;
        this.imputedBirthDate = imputedBirthDate;
        this.imputedPhysicalCondition = imputedPhysicalCondition;
        this.imputedActivities = imputedActivities;
        this.imputedOccupation = imputedOccupation;
        this.imputedOccupationPlace = imputedOccupationPlace;
        this.imputedOccupationPhone = imputedOccupationPhone;
        this.additionalQuestionsObs = additionalQuestionsObs;
        this.addictionTreatment = addictionTreatment;
        this.addictionTreatmentInstitute = addictionTreatmentInstitute;
        this.addictionTreatmentDate = addictionTreatmentDate;
        this.addictedAcquaintance = addictedAcquaintance;
        this.relativeAbroad = relativeAbroad;
        this.obligationIssue = obligationIssue;
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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getImputedName() {
        return imputedName;
    }

    public void setImputedName(String imputedName) {
        this.imputedName = imputedName;
    }

    public String getImputedLNP() {
        return imputedLNP;
    }

    public void setImputedLNP(String imputedLNP) {
        this.imputedLNP = imputedLNP;
    }

    public String getImputedLNM() {
        return imputedLNM;
    }

    public void setImputedLNM(String imputedLNM) {
        this.imputedLNM = imputedLNM;
    }

    public Integer getImputedGender() {
        return imputedGender;
    }

    public void setImputedGender(Integer imputedGender) {
        this.imputedGender = imputedGender;
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

    public String getImputedBirthStateCmb() {
        if (imputedBirthStateCmb != null && imputedBirthStateCmb.trim() != "")
            return imputedBirthStateCmb;
        else
            return imputedBirthState;
    }

    public void setImputedBirthStateCmb(String imputedBirthStateCmb) {
        this.imputedBirthStateCmb = imputedBirthStateCmb;
    }

    public String getImputedBirthState() {
        return imputedBirthState;
    }

    public void setImputedBirthState(String imputedBirthState) {
        this.imputedBirthState = imputedBirthState;
    }

    public String getImputedMaritalStatus() {
        return imputedMaritalStatus;
    }

    public void setImputedMaritalStatus(String imputedMaritalStatus) {
        this.imputedMaritalStatus = imputedMaritalStatus;
    }

    public String getImputedYearsMaritalStatus() {
        return imputedYearsMaritalStatus;
    }

    public void setImputedYearsMaritalStatus(String imputedYearsMaritalStatus) {
        this.imputedYearsMaritalStatus = imputedYearsMaritalStatus;
    }

    public String getImputedPhysicalCondition() {
        return imputedPhysicalCondition;
    }

    public void setImputedPhysicalCondition(String imputedPhysicalCondition) {
        this.imputedPhysicalCondition = imputedPhysicalCondition;
    }

    public String getImputedActivities() {
        return imputedActivities;
    }

    public void setImputedActivities(String imputedActivities) {
        this.imputedActivities = imputedActivities;
    }

    public String getImputedOccupation() {
        return imputedOccupation;
    }

    public void setImputedOccupation(String imputedOccupation) {
        this.imputedOccupation = imputedOccupation;
    }

    public String getImputedOccupationPlace() {
        return imputedOccupationPlace;
    }

    public void setImputedOccupationPlace(String imputedOccupationPlace) {
        this.imputedOccupationPlace = imputedOccupationPlace;
    }

    public String getImputedOccupationPhone() {
        return imputedOccupationPhone;
    }

    public void setImputedOccupationPhone(String imputedOccupationPhone) {
        this.imputedOccupationPhone = imputedOccupationPhone;
    }

    public Integer getAddictionTreatment() {
        return addictionTreatment;
    }

    public void setAddictionTreatment(Integer addictionTreatment) {
        this.addictionTreatment = addictionTreatment;
    }

    public String getAddictionTreatmentInstitute() {
        return addictionTreatmentInstitute;
    }

    public void setAddictionTreatmentInstitute(String addictionTreatmentInstitute) {
        this.addictionTreatmentInstitute = addictionTreatmentInstitute;
    }

    public Date getAddictionTreatmentDate() {
        return addictionTreatmentDate;
    }

    public void setAddictionTreatmentDate(Date addictionTreatmentDate) {
        this.addictionTreatmentDate = addictionTreatmentDate;
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

    public String getAdditionalQuestionsObs() {
        return additionalQuestionsObs;
    }

    public void setAdditionalQuestionsObs(String additionalQuestionsObs) {
        this.additionalQuestionsObs = additionalQuestionsObs;
    }

    public List<FramingReferenceInfo> getReferences() {
        return references;
    }

    public void setReferences(List<FramingReferenceInfo> references) {
        this.references = references;
    }

    public String referencesToString() {
        String returnStr = "";
        try {
            if (this.references != null && this.references.size() > 0) {
                for (FramingReferenceInfo actRef : this.references) {

                    if (actRef.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {
                        if (returnStr != "") {
                            returnStr += "\n";
                        }

                        if (actRef.getHasInfo() == true) {
                            if (actRef.getIsAccompaniment() == true) {
                                returnStr += "-" + actRef.getName();
                                returnStr += "," + actRef.getGenderStr();
                                returnStr += ", " + actRef.getAge() + " años";
                                String relationship = actRef.getRelationship();
                                if (actRef.getSpecificationRelationship() != null && !actRef.getSpecificationRelationship().equals("")) {
                                    relationship += ": " + actRef.getSpecificationRelationship();
                                }
                                returnStr += ", Relación: " + relationship;
                                returnStr += ", Ocupación" + actRef.getOccupation();
                                returnStr += ", Lugar de ocupación: " + actRef.getOccupationPlace();
                                returnStr += ", Teléfono: " + actRef.getPhone();
                                returnStr += ", " + actRef.getAddressStr();
                                returnStr += ", Esta persona acompañara durante el proceso: " + actRef.getIsAccompanimentStr() + ".";
                            } else {
                                returnStr += "-" + actRef.getName();
                                returnStr += ", Teléfono: " + actRef.getPhone();
                                String relationship = actRef.getRelationship();
                                if (actRef.getSpecificationRelationship() != null && !actRef.getSpecificationRelationship().equals("")) {
                                    relationship += ": " + actRef.getSpecificationRelationship();
                                }
                                returnStr += ", Relación: " + relationship;
                                returnStr += ", Esta persona acompañara durante el proceso: " + actRef.getIsAccompanimentStr() + ".";
                            }
                        }
                    }
                }
            }
            if (returnStr == "")
                returnStr = "El imputado no cuenta con referencias personales.";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnStr;
    }

    public String housemateToString() {
        String returnStr = "";

        try {
            if (this.references != null && this.references.size() > 0) {
                for (FramingReferenceInfo actRef : this.references) {


                    if (actRef.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE)) {
                        if (returnStr != "") {
                            returnStr += "\n";
                        }

                        if (actRef.getHasInfo() == true) {
                            if (actRef.getIsAccompaniment() == true) {
                                returnStr += "-" + actRef.getName();
                                returnStr += "," + actRef.getGenderStr();
                                returnStr += ", " + actRef.getAge() + " años";
                                String relationship = actRef.getRelationship();
                                if (actRef.getSpecificationRelationship() != null && !actRef.getSpecificationRelationship().equals("")) {
                                    relationship += ": " + actRef.getSpecificationRelationship();
                                }
                                returnStr += ", Relación: " + relationship;
                                returnStr += ", Ocupación: " + actRef.getOccupation();
                                returnStr += ", Lugar de ocupación: " + actRef.getOccupationPlace();
                                returnStr += ", Teléfono: " + actRef.getPhone();
                                returnStr += ", " + actRef.getAddress();
                                returnStr += ", Escolaridad: " + actRef.getAcademicLvl() + ".";
                                returnStr += ", Esta persona acompañará durante el proceso: " + actRef.getIsAccompanimentStr() + ".";
                            } else {
                                returnStr += "-" + actRef.getName();
                                returnStr += ", " + actRef.getAge() + " años";
                                String relationship = actRef.getRelationship();
                                if (actRef.getSpecificationRelationship() != null && !actRef.getSpecificationRelationship().equals("")) {
                                    relationship += ": " + actRef.getSpecificationRelationship();
                                }
                                returnStr += ", Relación: " + relationship;
                                returnStr += ", Ocupación: " + actRef.getOccupation();
                                returnStr += ", " + actRef.getAddress();
                                returnStr += ", Esta persona acompañara durante el proceso: " + actRef.getIsAccompanimentStr() + ".";
                            }
                        }
                    }
                }
            }

            if (returnStr == "")
                returnStr = "El imputado vive solo.";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnStr;
    }

    public String victimsToString() {
        String returnStr = "";

        try {
            if (this.references != null && this.references.size() > 0) {
                for (FramingReferenceInfo actRef : this.references) {

                    if (actRef.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_VICTIM) || actRef.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_WITNESS)) {

                        if (returnStr != "") {
                            returnStr += "\n";
                        }

                        if (actRef.getHasInfo() == true) {
                            returnStr += "-" + actRef.getName();
                            returnStr += ", Edad: " + actRef.getAge();
                            if (actRef.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_WITNESS))
                                returnStr += ", Testigo";
                            else
                                returnStr += ", Víctima";
                            String relationship = actRef.getRelationship();
                            if (actRef.getSpecificationRelationship() != null && !actRef.getSpecificationRelationship().equals("")) {
                                relationship += ": " + actRef.getSpecificationRelationship();
                            }
                            returnStr += ", Relación: " + relationship;
                            returnStr += ", Teléfono: " + actRef.getPhone();
                            returnStr += ", " + actRef.getAddressStr();
                        }
                    }
                }
            }
            if (returnStr == "")
                returnStr = "No existe información de víctimas o testigos";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnStr;
    }


    public String homesToString() {
        String returnStr = "";

        if (this.homes != null && this.homes.size() > 0) {
            for (CatalogDto actHome : this.homes) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actHome.getName() != null)
                    returnStr += "-" + actHome.getName();
            }
        }

        return returnStr;
    }

    public String drugsToString() {

        String drugsStr = "";

        if (this.drugs != null && this.drugs.size() > 0)
            for (ExcelDrugDto act : this.drugs) {
                if (drugsStr != "")
                    drugsStr += "\n";

                if (act.getDrugType() != null && !act.getDrugType().equals(""))
                    drugsStr += "-" + act.getDrugType();

                if (act.getPeriodicity() != null && !act.getPeriodicity().equals(""))
                    drugsStr += ", Periocidad: " + act.getPeriodicity();

                if (act.getSpecificationType() != null && !act.getSpecificationType().equals(""))
                    drugsStr += ", Especificación: " + act.getSpecificationType();

                if (act.getQuantity() != null && !act.getQuantity().equals(""))
                    drugsStr += ", Cantidad: " + act.getQuantity();

                if (act.getLastUse() != null)
                    drugsStr += ", Útlimo consumo: " + dateFormat.format(act.getLastUse());
            }

        return drugsStr;
    }

    public String linksToString() {
        String returnStr = "";

        if (this.links != null && this.links.size() > 0) {
            for (CatalogDto actLink : this.links) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actLink.getName() != null)
                    returnStr += "-" + actLink.getName();
                if (actLink.getContent() != null)
                    returnStr += ", " + actLink.getContent();
                if (actLink.getCode() != null) {
                    if (actLink.getCode().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE))
                        returnStr += ", vive con el imputado";
                    else if (actLink.getCode().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE))
                        returnStr += ", referencia personal";
                    else if (actLink.getCode().equals(FramingMeetingConstants.PERSON_TYPE_VICTIM))
                        returnStr += ", víctima";
                    else if (actLink.getCode().equals(FramingMeetingConstants.PERSON_TYPE_WITNESS))
                        returnStr += ", testigo";
                }

            }
        }

        return returnStr;
    }

    public String arrangementToString() {
        String returnStr = "";

        if (this.arrangements != null && this.arrangements.size() > 0) {
            for (String actArr : this.arrangements) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actArr != null)
                    returnStr += "-" + actArr;
            }
        }

        return returnStr;
    }

    public String riskToString() {
        String returnStr = "";

        if (this.risks != null && this.risks.size() > 0) {
            for (CatalogDto actRisk : this.risks) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actRisk.getName() != null)
                    returnStr += "-" + actRisk.getName();
            }
        }

        return returnStr;
    }

    public String threatsToString() {
        String returnStr = "";

        if (this.threats != null && this.threats.size() > 0) {
            for (CatalogDto actThreat : this.threats) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actThreat.getName() != null)
                    returnStr += "-" + actThreat.getName();
            }
        }

        return returnStr;
    }

    public List<ObligationIssuesInfo> getRelativesAbroad() {
        return relativesAbroad;
    }

    public void setRelativesAbroad(List<ObligationIssuesInfo> relativesAbroad) {
        this.relativesAbroad = relativesAbroad;
    }

    public List<ObligationIssuesInfo> getObligationIssues() {
        return obligationIssues;
    }

    public void setObligationIssues(List<ObligationIssuesInfo> obligationIssues) {
        this.obligationIssues = obligationIssues;
    }

    public List<ExcelDrugDto> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<ExcelDrugDto> drugs) {
        this.drugs = drugs;
    }

    public List<CatalogDto> getHomes() {
        return homes;
    }

    public void setHomes(List<CatalogDto> homes) {
        this.homes = homes;
    }

    public List<CatalogDto> getAddictedAcquaintances() {
        return addictedAcquaintances;
    }

    public void setAddictedAcquaintances(List<CatalogDto> addictedAcquaintances) {
        this.addictedAcquaintances = addictedAcquaintances;
    }

    public List<CatalogDto> getLinks() {
        return links;
    }

    public void setLinks(List<CatalogDto> links) {
        this.links = links;
    }

    public List<CatalogDto> getRisks() {
        return risks;
    }

    public void setRisks(List<CatalogDto> risks) {
        this.risks = risks;
    }

    public List<CatalogDto> getThreats() {
        return threats;
    }

    public void setThreats(List<CatalogDto> threats) {
        this.threats = threats;
    }

    public List<String> getArrangements() {
        return arrangements;
    }

    public void setArrangements(List<String> arrangements) {
        this.arrangements = arrangements;
    }

    public String getImputedFullName() {
        if (imputedName == null)
            imputedName = "";
        if (imputedLNP == null)
            imputedLNP = "";
        if (imputedLNM == null)
            imputedLNM = "";
        return imputedName + " " + imputedLNP + " " + imputedLNM;
    }

    public void setImputedFullName(String imputedFullName) {
        this.imputedFullName = imputedFullName;
    }

    public String getAddictionTreatmentStr() {
        addictionTreatmentStr = "";
        if (this.addictionTreatment != null) {
            if (this.addictionTreatment == 1)
                addictionTreatmentStr = "Si";
            else
                addictionTreatmentStr = "No";
        }
        return addictionTreatmentStr;
    }

    public void setAddictionTreatmentStr(String addictionTreatmentStr) {
        this.addictionTreatmentStr = addictionTreatmentStr;
    }

    public String getAddictedAcquaintanceStr() {
        addictedAcquaintanceStr = "";
        if (this.addictedAcquaintance != null) {
            if (this.addictedAcquaintance == 1)
                addictedAcquaintanceStr = "Si";
            else
                addictedAcquaintanceStr = "No";
        }
        return addictedAcquaintanceStr;
    }

    public void setAddictedAcquaintanceStr(String addictedAcquaintanceStr) {
        this.addictedAcquaintanceStr = addictedAcquaintanceStr;
    }

    public String getRelativeAbroadStr() {
        relativeAbroadStr = "";
        if (this.relativeAbroad != null) {
            if (this.relativeAbroad == 1)
                relativeAbroadStr = "Si";
            else
                relativeAbroadStr = "No";
        }
        return relativeAbroadStr;
    }

    public void setRelativeAbroadStr(String relativeAbroadStr) {
        this.relativeAbroadStr = relativeAbroadStr;
    }

    public String getObligationIssueStr() {
        obligationIssueStr = "";
        if (this.obligationIssue != null) {
            if (this.obligationIssue == 1)
                obligationIssueStr = "Si";
            else
                obligationIssueStr = "No";
        }
        return obligationIssueStr;
    }

    public void setObligationIssueStr(String obligationIssueStr) {
        this.obligationIssueStr = obligationIssueStr;
    }

    public String addictedAcquaintancesToStr() {
        String returnStr = "";

        if (this.addictedAcquaintances != null && this.addictedAcquaintances.size() > 0) {
            for (CatalogDto actAdd : this.addictedAcquaintances) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actAdd.getName() != null)
                    returnStr += "-" + actAdd.getName();
            }
        }

        return returnStr;
    }

    public String relativesAbroadToStr() {
        String returnStr = "";

        if (this.relativesAbroad != null && this.relativesAbroad.size() > 0) {
            for (ObligationIssuesInfo actRA : this.relativesAbroad) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actRA.getName() != null && actRA.getCause() != null) {
                    returnStr += "-" + actRA.getName();
                    returnStr += ", Vive en: " + actRA.getCause();
                }
            }
        }

        return returnStr;
    }

    public String obligationIssuesToStr() {
        String returnStr = "";

        if (this.obligationIssues != null && this.obligationIssues.size() > 0) {
            for (ObligationIssuesInfo actOI : this.obligationIssues) {
                if (returnStr != "") {
                    returnStr += "\n";
                }
                if (actOI.getName() != null && actOI.getCause() != null) {
                    returnStr += "-" + actOI.getName();
                    returnStr += ", Causa: " + actOI.getCause();
                }
            }
        }

        return returnStr;
    }

    public String getImputedGenderStr() {
        imputedGenderStr = "";
        if (imputedGender != null && imputedGender == 1)
            imputedGenderStr = "Femenino";
        else if (imputedGender != null && imputedGender == 2)
            imputedGenderStr = "Masculino";

        return imputedGenderStr;
    }

    public void setImputedGenderStr(String imputedGenderStr) {
        this.imputedGenderStr = imputedGenderStr;
    }

    public List<ExcelActivitiesDto> getActivities() {
        return activities;
    }

    public void setActivities(List<ExcelActivitiesDto> activities) {
        this.activities = activities;
    }

    public String activitiesToString() {
        String returnStr = "";

        if (this.activities != null && this.activities.size() > 0) {
            for (ExcelActivitiesDto act : activities) {
                if (returnStr != "")
                    returnStr += "\n";
                if (act.getNameAct() != null)
                    returnStr += "-" + act.getNameAct();
                if (act.getDescription() != null && !act.getDescription().trim().equals(""))
                    returnStr += ": " + act.getDescription();
                if (act.getSchedule() != null)
                    returnStr += ", Disponibilidad: " + act.scheduleToStr();
            }
        }

        return returnStr;
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }

    public SchoolDto getSchool() {
        return school;
    }

    public void setSchool(SchoolDto school) {
        this.school = school;
    }

    public String schoolToStr() {
        String returnStr = "";

        if (this.school.getHasActualSchool() == true) {
            returnStr += "Nombre: " + this.school.getName();
            returnStr += ", Teléfono: " + this.school.getPhone();
            returnStr += ", Dirección: " + this.school.getAddress() + ", ";
        } else
            returnStr += "Sin estudios actuales. Último grado de estudios:\n ";

        returnStr += "Nivel: " + this.school.getAcademicLvlStr() + ", ";
        returnStr += " Grado: " + this.school.getDegreeStr();

        if (this.school.getHasActualSchool() == true && this.school.getLstSchedule() != null && this.school.getLstSchedule().size() > 0)
            returnStr += ", Disponibilidad: " + this.school.scheduleToStr();

        return returnStr;
    }

    public List<ExcelJobDto> getJobs() {
        return jobs;
    }

    public void setJobs(List<ExcelJobDto> jobs) {
        this.jobs = jobs;
    }

    public String jobsToString() {
        String returnStr = "";

        if (this.jobs != null && this.jobs.size() > 0) {
            for (ExcelJobDto act : jobs) {
                if (act.getBlock() == true) {
                    if (returnStr != "")
                        returnStr += "\n";

                    returnStr += "- " + act.getCompany();
                    returnStr += ", Puesto: " + act.getPost();
                    returnStr += ", Tel. " + act.getPhone();
                    returnStr += ", Patrón " + act.getNameHead();
                    returnStr += ", Dirección " + act.getAddress();
                    returnStr += ", " + act.getRegisterType();

                    if (act.getRegisterType().equals(FramingMeetingConstants.LOW_CASE_REGISTER_TYPE_PREV)) {
                        returnStr += ", Inicio: " + act.getStartPrevStr();
                        returnStr += ", Fin: " + act.getEndStr();
                        returnStr += ", Motivo de cambio: " + act.getReasonChange();
                    } else {
                        returnStr += ", Inicio: " + act.getStartStr();
                        returnStr += ", Salario Semanal: " + act.getSalary();
                        returnStr += ", Disponibilidad: " + act.scheduleToStr();
                    }
                } else {
                    returnStr += "El imputado no cuenta con trabajo actual";
                    break;
                }
            }
        }

        return returnStr;
    }

    public List<CatalogDto> getSummaryHomes() {
        return summaryHomes;
    }

    public void setSummaryHomes(List<CatalogDto> summaryHomes) {
        this.summaryHomes = summaryHomes;
    }

    public String summaryFramingHomes() {
        String returnStr = "";
        for (CatalogDto act : summaryHomes) {
            if (returnStr != "")
                returnStr += "\n";
            returnStr += "-" + act.getName();
        }
        return returnStr;
    }

    public String summaryFramingHousemates() {
        String returnStr = "";
        for (FramingReferenceInfo act : this.references) {
            if (returnStr != "")
                returnStr += "\n";
            if (act.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE))
                returnStr += "-" + act.getRelationship();
        }
        return returnStr;
    }

    public String summaryFramingReferences() {
        String returnStr = "";
        for (FramingReferenceInfo act : this.references) {
            if (returnStr != "")
                returnStr += "\n";
            if (act.getPersonType().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE))
                returnStr += "-" + act.getRelationship();
        }
        return returnStr;
    }

    public String summaryFramingActivities() {

        String returnStr = "";
        for (ExcelActivitiesDto act : this.activities) {
            if (returnStr != "")
                returnStr += "\n";
            returnStr += "-" + act.getNameAct();
        }
        return returnStr;
    }

    public String summaryFramingJobs() {

        Boolean hasACt = false;
        for (ExcelJobDto act : this.jobs) {
            if (act.getBlock() == true) {
                hasACt = true;
                break;
            } else {
                break;
            }
        }

        if (hasACt == true)
            return "Tiene trabajo actual";
        else
            return "No tiene trabajo actual";
    }

    public String summaryFramingDrugs() {
        String drugsStr = "";
        if (this.drugs != null && this.drugs.size() > 0)
            for (ExcelDrugDto act : this.drugs) {
                if (drugsStr != "")
                    drugsStr += "\n";
                if (act.getBlock() == true) {
                    if (act.getDrugType() != null && !act.getDrugType().equals(""))
                        drugsStr += "-" + act.getDrugType();
                } else {
                    drugsStr = "No consume sustancias.";
                }
            }
        return drugsStr;
    }

    public String summaryFramingLinks() {
        Boolean hasLinks = false;

        if (this.links != null && this.links.size() > 0) {
            for (CatalogDto actLink : this.links) {
                if (actLink.getCode() != null) {
                    if (actLink.getCode().equals(FramingMeetingConstants.PERSON_TYPE_HOUSEMATE) ||
                            actLink.getCode().equals(FramingMeetingConstants.PERSON_TYPE_REFERENCE)) {
                        hasLinks = true;
                        break;
                    }
                }
            }
        }

        if (hasLinks == true)
            return "El imputado cuenta con vínculos";
        else
            return "El imputado no cuenta con vínculos";
    }

    public String summaryFramingRiskThreats() {
        String returnStr = "";
        String riskStr = this.riskToString();
        String threatsStr = this.threatsToString();
        if (riskStr != "")
            returnStr = "Riesgos: " + riskStr + "\n";
        if (threatsStr != "")
            returnStr += "Amenzas: " + threatsStr;

        return returnStr;
    }

    public String summaryFramingAdditionalQuestions() {
        String returnStr = "";
        if (this.getAddictionTreatmentStr() != "")
            returnStr += "-¿Se encuentra en algún tipo de tratamiento de adicciones?: " + this.getAddictionTreatmentStr() + "\n";
        if (this.getRelativeAbroadStr() != "")
            returnStr += "-¿Cuenta con familiares en el extranjero?: " + this.getRelativeAbroadStr() + "\n";
        if (this.getObligationIssueStr() != "")
            returnStr += "-¿Consideras que alguna de las obligaciones impuestas será difícil de cumplir?: " + this.getObligationIssueStr();
        return returnStr;
    }

}

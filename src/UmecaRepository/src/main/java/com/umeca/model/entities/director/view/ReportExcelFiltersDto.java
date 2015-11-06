package com.umeca.model.entities.director.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ReportExcelFiltersDto {

    private String initDate;
    private String endDate;

    private Long idLoc;
    private Boolean homePlace;

    private String lstStatusCaseStr;
    private List<Long> lstStatusCase;

    private String lstStatusMeetingStr;
    private List<Long> lstStatusMeeting;

    private String lstStatusVerificationStr;
    private List<Long> lstStatusVerification;

    private String lstGenderBoolStr;
    private List<Boolean> lstGenderBool;

    private String lstMaritalStStr;
    private List<Long> lstMaritalSt;

    private String lstAcademicLvlStr;
    private List<Long> lstAcademicLvl;

    private String lstDrugsStr;
    private List<Long> lstDrugs;

    private String lstLvlRiskStr;
    private List<Integer> lstLvlRisk;

    private String lstHearingTypeStr;
    private List<Integer> lstHearingType;

    private String lstCrimeStr;
    private List<Long> lstCrime;

    private String lstArrangementStr;
    private List<Long> lstArrangement;

    private String lstActivitiesStr;
    private List<Long> lstActivities;

    private Boolean hasMonP;

    private Boolean hasJob;

    private static final Gson conv = new Gson();

    private static final Type longListType = new TypeToken<List<Long>>() {
    }.getType();

    private static final Type booleanListType = new TypeToken<List<Boolean>>() {
    }.getType();

    private static final Type integerListType = new TypeToken<List<Integer>>() {
    }.getType();

    public List<Long> getLstStatusCase() {
        lstStatusCase = conv.fromJson(lstStatusCaseStr, longListType);
        return lstStatusCase;
    }

    public List<Long> getLstStatusMeeting() {
        lstStatusMeeting = conv.fromJson(lstStatusMeetingStr, longListType);
        return lstStatusMeeting;
    }

    public List<Long> getLstStatusVerification() {
        lstStatusVerification = conv.fromJson(lstStatusVerificationStr, longListType);
        return lstStatusVerification;
    }

    public List<Boolean> getLstGenderBool() {
        lstGenderBool = conv.fromJson(lstGenderBoolStr, booleanListType);
        return lstGenderBool;
    }

    public List<Long> getLstMaritalSt() {
        lstMaritalSt = conv.fromJson(lstMaritalStStr, longListType);
        return lstMaritalSt;
    }

    public List<Long> getLstAcademicLvl() {
        lstAcademicLvl = conv.fromJson(lstAcademicLvlStr, longListType);
        return lstAcademicLvl;
    }

    public List<Long> getLstDrugs() {
        lstDrugs = conv.fromJson(lstDrugsStr, longListType);
        return lstDrugs;
    }

    public List<Integer> getLstLvlRisk() {
        lstLvlRisk = conv.fromJson(lstLvlRiskStr, integerListType);
        return lstLvlRisk;
    }

    public List<Integer> getLstHearingType() {
        lstHearingType = conv.fromJson(lstHearingTypeStr, integerListType);
        return lstHearingType;
    }

    public String getInitDate() {
        return initDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getLstStatusCaseStr() {
        return lstStatusCaseStr;
    }

    public String getLstStatusMeetingStr() {
        return lstStatusMeetingStr;
    }

    public String getLstStatusVerificationStr() {
        return lstStatusVerificationStr;
    }

    public String getLstGenderBoolStr() {
        return lstGenderBoolStr;
    }

    public String getLstMaritalStStr() {
        return lstMaritalStStr;
    }

    public String getLstAcademicLvlStr() {
        return lstAcademicLvlStr;
    }

    public String getLstDrugsStr() {
        return lstDrugsStr;
    }

    public String getLstLvlRiskStr() {
        return lstLvlRiskStr;
    }

    public String getLstHearingTypeStr() {
        return lstHearingTypeStr;
    }

    public Boolean getHasMonP() {
        return hasMonP;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setLstStatusCaseStr(String lstStatusCaseStr) {
        this.lstStatusCaseStr = lstStatusCaseStr;
    }

    public void setLstStatusCase(List<Long> lstStatusCase) {
        this.lstStatusCase = lstStatusCase;
    }

    public void setLstStatusMeetingStr(String lstStatusMeetingStr) {
        this.lstStatusMeetingStr = lstStatusMeetingStr;
    }

    public void setLstStatusMeeting(List<Long> lstStatusMeeting) {
        this.lstStatusMeeting = lstStatusMeeting;
    }

    public void setLstStatusVerificationStr(String lstStatusVerificationStr) {
        this.lstStatusVerificationStr = lstStatusVerificationStr;
    }

    public void setLstStatusVerification(List<Long> lstStatusVerification) {
        this.lstStatusVerification = lstStatusVerification;
    }

    public void setLstGenderBoolStr(String lstGenderBoolStr) {
        this.lstGenderBoolStr = lstGenderBoolStr;
    }

    public void setLstGenderBool(List<Boolean> lstGenderBool) {
        this.lstGenderBool = lstGenderBool;
    }

    public void setLstMaritalStStr(String lstMaritalStStr) {
        this.lstMaritalStStr = lstMaritalStStr;
    }

    public void setLstMaritalSt(List<Long> lstMaritalSt) {
        this.lstMaritalSt = lstMaritalSt;
    }

    public void setLstAcademicLvlStr(String lstAcademicLvlStr) {
        this.lstAcademicLvlStr = lstAcademicLvlStr;
    }

    public void setLstAcademicLvl(List<Long> lstAcademicLvl) {
        this.lstAcademicLvl = lstAcademicLvl;
    }

    public void setLstDrugsStr(String lstDrugsStr) {
        this.lstDrugsStr = lstDrugsStr;
    }

    public void setLstDrugs(List<Long> lstDrugs) {
        this.lstDrugs = lstDrugs;
    }

    public void setLstLvlRiskStr(String lstLvlRiskStr) {
        this.lstLvlRiskStr = lstLvlRiskStr;
    }

    public void setLstLvlRisk(List<Integer> lstLvlRisk) {
        this.lstLvlRisk = lstLvlRisk;
    }

    public void setLstHearingTypeStr(String lstHearingTypeStr) {
        this.lstHearingTypeStr = lstHearingTypeStr;
    }

    public void setLstHearingType(List<Integer> lstHearingType) {
        this.lstHearingType = lstHearingType;
    }

    public void setHasMonP(Boolean hasMonP) {
        this.hasMonP = hasMonP;
    }

    public Boolean getHasJob() {
        return hasJob;
    }

    public void setHasJob(Boolean hasJob) {
        this.hasJob = hasJob;
    }

    public Boolean getHomePlace() {
        return homePlace;
    }

    public void setHomePlace(Boolean homePlace) {
        this.homePlace = homePlace;
    }

    public Long getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(Long idLoc) {
        this.idLoc = idLoc;
    }

    public String getLstCrimeStr() {
        return lstCrimeStr;
    }

    public void setLstCrimeStr(String lstCrimeStr) {
        this.lstCrimeStr = lstCrimeStr;
    }

    public List<Long> getLstCrime() {
        lstCrime = conv.fromJson(lstCrimeStr, longListType);
        return lstCrime;
    }

    public void setLstCrime(List<Long> lstCrime) {
        this.lstCrime = lstCrime;
    }

    public String getLstArrangementStr() {
        return lstArrangementStr;
    }

    public void setLstArrangementStr(String lstArrangementStr) {
        this.lstArrangementStr = lstArrangementStr;
    }

    public List<Long> getLstArrangement() {
        lstArrangement = conv.fromJson(lstArrangementStr, longListType);
        return lstArrangement;
    }

    public void setLstArrangement(List<Long> lstArrangement) {
        this.lstArrangement = lstArrangement;
    }

    public String getLstActivitiesStr() {
        return lstActivitiesStr;
    }

    public void setLstActivitiesStr(String lstActivitiesStr) {
        this.lstActivitiesStr = lstActivitiesStr;
    }

    public List<Long> getLstActivities() {
        lstActivities = conv.fromJson(lstActivitiesStr, longListType);
        return lstActivities;
    }

    public void setLstActivities(List<Long> lstActivities) {
        this.lstActivities = lstActivities;
    }


}

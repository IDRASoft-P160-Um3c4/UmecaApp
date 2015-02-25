package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.SelectList;

import java.util.List;

public class ManagerSupExcelReportInfo {

    private String strInitDate;
    private String strEndDate;
    private String districtName;
    private Long totArrangementCases;
    private Long totDrugsCases;
    private Long totJobCases;
    private Long totClosedCases;
    private List<SelectList> lstCasesByArrangement;
    private List<SelectList> lstCasesByDrugs;
    private List<SelectList> lstCasesByJob;
    private List<SelectList> lstClosedCases;

    public List<SelectList> getLstCasesByArrangement() {
        return lstCasesByArrangement;
    }

    public void setLstCasesByArrangement(List<SelectList> lstCasesByArrangement) {
        this.lstCasesByArrangement = lstCasesByArrangement;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getTotArrangementCases() {
        return totArrangementCases;
    }

    public void setTotArrangementCases(Long totArrangementCases) {
        this.totArrangementCases = totArrangementCases;
    }

    public String getStrInitDate() {
        return strInitDate;
    }

    public void setStrInitDate(String strInitDate) {
        this.strInitDate = strInitDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    public Long getTotDrugsCases() {
        return totDrugsCases;
    }

    public void setTotDrugsCases(Long totDrugsCases) {
        this.totDrugsCases = totDrugsCases;
    }

    public List<SelectList> getLstCasesByDrugs() {
        return lstCasesByDrugs;
    }

    public void setLstCasesByDrugs(List<SelectList> lstCasesByDrugs) {
        this.lstCasesByDrugs = lstCasesByDrugs;
    }

    public Long getTotJobCases() {
        return totJobCases;
    }

    public void setTotJobCases(Long totJobCases) {
        this.totJobCases = totJobCases;
    }

    public List<SelectList> getLstCasesByJob() {
        return lstCasesByJob;
    }

    public void setLstCasesByJob(List<SelectList> lstCasesByJob) {
        this.lstCasesByJob = lstCasesByJob;
    }

    public Long getTotClosedCases() {
        return totClosedCases;
    }

    public void setTotClosedCases(Long totClosedCases) {
        this.totClosedCases = totClosedCases;
    }

    public List<SelectList> getLstClosedCases() {
        return lstClosedCases;
    }

    public void setLstClosedCases(List<SelectList> lstClosedCases) {
        this.lstClosedCases = lstClosedCases;
    }
}
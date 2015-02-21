package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.SelectList;

import java.util.List;

public class ManagerSupExcelReportInfo {

    private String strInitDate;
    private String strEndDate;
    private String districtName;
    private Long totArrangementCases;
    private List<SelectList> lstCasesByArrangement;

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
}
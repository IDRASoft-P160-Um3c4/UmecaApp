package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.SelectList;

import java.util.List;

public class ManagerSupExcelReportInfo {

    private List<SelectList> lstCasesByArrangement;

    public List<SelectList> getLstCasesByArrangement() {
        return lstCasesByArrangement;
    }

    public void setLstCasesByArrangement(List<SelectList> lstCasesByArrangement) {
        this.lstCasesByArrangement = lstCasesByArrangement;
    }
}
package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.SelectList;

import java.util.List;

/**
 * Created by Vmware on 15/01/2015.
 */
public class ManagerSupReportInfoDto {


    private Long totalCases;
    private Long activeCases;
    private Long casesMC;
    private Long casesMCDrugs;
    private Long casesMCJob;
    private List<SelectList> lstCasesByDrugsMC;
    private List<SelectList> lstCasesByArrangementMC;
    private Long casesSCPP;
    private Long casesSCPPDrugs;
    private Long casesSCPPJob;
    private List<SelectList> lstCasesByDrugsSCPP;
    private List<SelectList> lstCasesByArrangementSCPP;

    private Long casesDone;
    private Long casesNonFulfillment;
    private Long casesSubtracted;
    private Long casesHomeVisit;
    private Long casesCivOrg;
    private List<SelectList> lstCasesByOrgCiv;

    public Long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Long totalCases) {
        this.totalCases = totalCases;
    }

    public Long getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(Long activeCases) {
        this.activeCases = activeCases;
    }

    public Long getCasesMC() {
        return casesMC;
    }

    public void setCasesMC(Long casesMC) {
        this.casesMC = casesMC;
    }

    public Long getCasesMCDrugs() {
        return casesMCDrugs;
    }

    public void setCasesMCDrugs(Long casesMCDrugs) {
        this.casesMCDrugs = casesMCDrugs;
    }

    public Long getCasesMCJob() {
        return casesMCJob;
    }

    public void setCasesMCJob(Long casesMCJob) {
        this.casesMCJob = casesMCJob;
    }

    public List<SelectList> getLstCasesByDrugsMC() {
        return lstCasesByDrugsMC;
    }

    public void setLstCasesByDrugsMC(List<SelectList> lstCasesByDrugsMC) {
        this.lstCasesByDrugsMC = lstCasesByDrugsMC;
    }

    public List<SelectList> getLstCasesByArrangementMC() {
        return lstCasesByArrangementMC;
    }

    public void setLstCasesByArrangementMC(List<SelectList> lstCasesByArrangementMC) {
        this.lstCasesByArrangementMC = lstCasesByArrangementMC;
    }

    public Long getCasesSCPP() {
        return casesSCPP;
    }

    public void setCasesSCPP(Long casesSCPP) {
        this.casesSCPP = casesSCPP;
    }

    public Long getCasesSCPPDrugs() {
        return casesSCPPDrugs;
    }

    public void setCasesSCPPDrugs(Long casesSCPPDrugs) {
        this.casesSCPPDrugs = casesSCPPDrugs;
    }

    public Long getCasesSCPPJob() {
        return casesSCPPJob;
    }

    public void setCasesSCPPJob(Long casesSCPPJob) {
        this.casesSCPPJob = casesSCPPJob;
    }

    public List<SelectList> getLstCasesByDrugsSCPP() {
        return lstCasesByDrugsSCPP;
    }

    public void setLstCasesByDrugsSCPP(List<SelectList> lstCasesByDrugsSCPP) {
        this.lstCasesByDrugsSCPP = lstCasesByDrugsSCPP;
    }

    public List<SelectList> getLstCasesByArrangementSCPP() {
        return lstCasesByArrangementSCPP;
    }

    public void setLstCasesByArrangementSCPP(List<SelectList> lstCasesByArrangementSCPP) {
        this.lstCasesByArrangementSCPP = lstCasesByArrangementSCPP;
    }

    public Long getCasesDone() {
        return casesDone;
    }

    public Long getCasesNonFulfillment() {
        return casesNonFulfillment;
    }

    public Long getCasesSubtracted() {
        return casesSubtracted;
    }

    public Long getCasesHomeVisit() {
        return casesHomeVisit;
    }

    public Long getCasesCivOrg() {
        return casesCivOrg;
    }

    public List<SelectList> getLstCasesByOrgCiv() {
        return lstCasesByOrgCiv;
    }
}

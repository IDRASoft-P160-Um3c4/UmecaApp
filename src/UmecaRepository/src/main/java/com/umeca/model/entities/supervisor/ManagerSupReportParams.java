package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 15/01/2015.
 */
public class ManagerSupReportParams {

    private Boolean casesMC;
    private Boolean casesSCPP;
    private Boolean imputedDrugs;
    private Boolean casesDone;
    private Boolean casesPrison;
    private Boolean casesNonFulfillment;
    private Boolean casesSubtracted;
    private Boolean homeVisit;
    private Boolean civilOrg;
    private String initDate;
    private String endDate;

    public Boolean getCasesMC() {
        return casesMC;
    }

    public void setCasesMC(Boolean casesMC) {
        this.casesMC = casesMC;
    }

    public Boolean getCasesSCPP() {
        return casesSCPP;
    }

    public void setCasesSCPP(Boolean casesSCPP) {
        this.casesSCPP = casesSCPP;
    }

    public Boolean getImputedDrugs() {
        return imputedDrugs;
    }

    public void setImputedDrugs(Boolean imputedDrugs) {
        this.imputedDrugs = imputedDrugs;
    }

    public Boolean getCasesDone() {
        return casesDone;
    }

    public void setCasesDone(Boolean casesDone) {
        this.casesDone = casesDone;
    }

    public Boolean getCasesPrison() {
        return casesPrison;
    }

    public void setCasesPrison(Boolean casesPrison) {
        this.casesPrison = casesPrison;
    }

    public Boolean getCasesNonFulfillment() {
        return casesNonFulfillment;
    }

    public void setCasesNonFulfillment(Boolean casesNonFulfillment) {
        this.casesNonFulfillment = casesNonFulfillment;
    }

    public Boolean getCasesSubtracted() {
        return casesSubtracted;
    }

    public void setCasesSubtracted(Boolean casesSubtracted) {
        this.casesSubtracted = casesSubtracted;
    }

    public Boolean getHomeVisit() {
        return homeVisit;
    }

    public void setHomeVisit(Boolean homeVisit) {
        this.homeVisit = homeVisit;
    }

    public Boolean getCivilOrg() {
        return civilOrg;
    }

    public void setCivilOrg(Boolean civilOrg) {
        this.civilOrg = civilOrg;
    }

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

package com.umeca.model.entities.supervisorManager;

import java.util.Date;
import java.util.List;

public class ManagerSupChartParams {

    private Boolean mcCases;
    private Boolean totalMC;
    private Boolean partialMC;
    private Boolean scppCases;
    private Boolean totalSCPP;
    private Boolean partialSCPP;
    private Boolean arrangementChange;
    private Boolean closedCases;
    private Boolean substractedCases;
    private Boolean prisonCases;
    private Boolean visits;
    private Long districtId;
    private Long userId;
    private String initDate;
    private String endDate;
    private String chartTitle;
    private Date iDate;
    private Date eDate;

    private List<ManagerSupChartInfo> listaSujetos;

    public Boolean getMcCases() {
        return mcCases;
    }

    public void setMcCases(Boolean mcCases) {
        this.mcCases = mcCases;
    }

    public Boolean getTotalMC() {
        return totalMC;
    }

    public void setTotalMC(Boolean totalMC) {
        this.totalMC = totalMC;
    }

    public Boolean getPartialMC() {
        return partialMC;
    }

    public void setPartialMC(Boolean partialMC) {
        this.partialMC = partialMC;
    }

    public Boolean getScppCases() {
        return scppCases;
    }

    public void setScppCases(Boolean scppCases) {
        this.scppCases = scppCases;
    }

    public Boolean getTotalSCPP() {
        return totalSCPP;
    }

    public void setTotalSCPP(Boolean totalSCPP) {
        this.totalSCPP = totalSCPP;
    }

    public Boolean getPartialSCPP() {
        return partialSCPP;
    }

    public void setPartialSCPP(Boolean partialSCPP) {
        this.partialSCPP = partialSCPP;
    }

    public Boolean getArrangementChange() {
        return arrangementChange;
    }

    public void setArrangementChange(Boolean arrangementChange) {
        this.arrangementChange = arrangementChange;
    }

    public Boolean getClosedCases() {
        return closedCases;
    }

    public void setClosedCases(Boolean closedCases) {
        this.closedCases = closedCases;
    }

    public Boolean getSubstractedCases() {
        return substractedCases;
    }

    public void setSubstractedCases(Boolean substractedCases) {
        this.substractedCases = substractedCases;
    }

    public Boolean getPrisonCases() {
        return prisonCases;
    }

    public void setPrisonCases(Boolean prisonCases) {
        this.prisonCases = prisonCases;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean getVisits() {
        return visits;
    }

    public void setVisits(Boolean visits) {
        this.visits = visits;
    }

    public List<ManagerSupChartInfo> getListaSujetos() {
        return listaSujetos;
    }

    public void setListaSujetos(List<ManagerSupChartInfo> listaSujetos) {
        this.listaSujetos = listaSujetos;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public Date getiDate() {
        return iDate;
    }

    public void setiDate(Date iDate) {
        this.iDate = iDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }
}

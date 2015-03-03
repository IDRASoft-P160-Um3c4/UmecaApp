package com.umeca.model.entities.supervisorManager;

public class ManagerSupChartInfo {

    private Long mcCases;
    private Long totalMC;
    private Long partialMC;
    private Long scppCases;
    private Long totalSCPP;
    private Long partialSCPP;
    private Long arrangementChange;
    private Long closedCases;
    private Long substractedCases;
    private Long prisonCases;
    private Long visits;
    private String name;
    private Long userId;

    public ManagerSupChartInfo() {

    }

    public ManagerSupChartInfo(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Long getMcCases() {
        return mcCases;
    }

    public void setMcCases(Long mcCases) {
        this.mcCases = mcCases;
    }

    public Long getTotalMC() {
        return totalMC;
    }

    public void setTotalMC(Long totalMC) {
        this.totalMC = totalMC;
    }

    public Long getPartialMC() {
        return partialMC;
    }

    public void setPartialMC(Long partialMC) {
        this.partialMC = partialMC;
    }

    public Long getScppCases() {
        return scppCases;
    }

    public void setScppCases(Long scppCases) {
        this.scppCases = scppCases;
    }

    public Long getTotalSCPP() {
        return totalSCPP;
    }

    public void setTotalSCPP(Long totalSCPP) {
        this.totalSCPP = totalSCPP;
    }

    public Long getPartialSCPP() {
        return partialSCPP;
    }

    public void setPartialSCPP(Long partialSCPP) {
        this.partialSCPP = partialSCPP;
    }

    public Long getArrangementChange() {
        return arrangementChange;
    }

    public void setArrangementChange(Long arrangementChange) {
        this.arrangementChange = arrangementChange;
    }

    public Long getClosedCases() {
        return closedCases;
    }

    public void setClosedCases(Long closedCases) {
        this.closedCases = closedCases;
    }

    public Long getSubstractedCases() {
        return substractedCases;
    }

    public void setSubstractedCases(Long substractedCases) {
        this.substractedCases = substractedCases;
    }

    public Long getPrisonCases() {
        return prisonCases;
    }

    public void setPrisonCases(Long prisonCases) {
        this.prisonCases = prisonCases;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

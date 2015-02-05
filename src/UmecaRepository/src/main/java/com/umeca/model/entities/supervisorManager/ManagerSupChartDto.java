package com.umeca.model.entities.supervisorManager;

/**
 * Created by Vmware on 04/02/2015.
 */
public class ManagerSupChartDto {

    private String name;
    private Long mcCases;
    private Long totalNonFullfilmentMC;
    private Long partialNonFullfilmentMC;
    private Long scppCases;
    private Long totalNonFullfilmentSCPP;
    private Long partialNonFullfilmentSCPP;
    private Long arrangementChange;
    private Long closedCases;
    private Long substractedCases;
    private Long prisonCases;

    public ManagerSupChartDto(String name, Long mcCases, Long partialNonFullfilmentMC, Long totalNonFullfilmentMC) {
        this.name = name;
        this.mcCases = mcCases;
        this.totalNonFullfilmentMC = totalNonFullfilmentMC;
        this.partialNonFullfilmentMC = partialNonFullfilmentMC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMcCases() {
        return mcCases;
    }

    public void setMcCases(Long mcCases) {
        this.mcCases = mcCases;
    }

    public Long getTotalNonFullfilmentMC() {
        return totalNonFullfilmentMC;
    }

    public void setTotalNonFullfilmentMC(Long totalNonFullfilmentMC) {
        this.totalNonFullfilmentMC = totalNonFullfilmentMC;
    }

    public Long getPartialNonFullfilmentMC() {
        return partialNonFullfilmentMC;
    }

    public void setPartialNonFullfilmentMC(Long partialNonFullfilmentMC) {
        this.partialNonFullfilmentMC = partialNonFullfilmentMC;
    }

    public Long getTotalNonFullfilmentSCPP() {
        return totalNonFullfilmentSCPP;
    }

    public void setTotalNonFullfilmentSCPP(Long totalNonFullfilmentSCPP) {
        this.totalNonFullfilmentSCPP = totalNonFullfilmentSCPP;
    }

    public Long getPartialNonFullfilmentSCPP() {
        return partialNonFullfilmentSCPP;
    }

    public void setPartialNonFullfilmentSCPP(Long partialNonFullfilmentSCPP) {
        this.partialNonFullfilmentSCPP = partialNonFullfilmentSCPP;
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

    public Long getScppCases() {
        return scppCases;
    }

    public void setScppCases(Long scppCases) {
        this.scppCases = scppCases;
    }
}

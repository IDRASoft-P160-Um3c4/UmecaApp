package com.umeca.model.entities.supervisor;

import java.util.Date;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelDrugDto {

    private Long idCase;
    private String drugType;
    private String periodicity;
    private String quantity;
    private Date lastUse;
    private String specificationType;
    private String specificationPeriodicity;

    public ExcelDrugDto(Long idCase, String drugType, String periodicity, String quantity, Date lastUse, String specificationType, String specificationPeriodicity) {
        this.idCase = idCase;
        this.drugType = drugType;
        this.periodicity = periodicity;
        this.quantity = quantity;
        this.lastUse = lastUse;
        this.specificationType = specificationType;
        this.specificationPeriodicity = specificationPeriodicity;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Date getLastUse() {
        return lastUse;
    }

    public void setLastUse(Date lastUse) {
        this.lastUse = lastUse;
    }

    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType;
    }

    public String getSpecificationPeriodicity() {
        return specificationPeriodicity;
    }

    public void setSpecificationPeriodicity(String specificationPeriodicity) {
        this.specificationPeriodicity = specificationPeriodicity;
    }
}

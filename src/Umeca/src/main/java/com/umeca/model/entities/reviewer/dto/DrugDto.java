package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.entities.reviewer.Drug;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 27/06/14
 * Time: 05:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrugDto {

    private Long id;
    private Long drugTypeId;
    private Long periodicityId;
    private String quantity;
    private Date lastUse;
    private String specificationType;
    private String specificationPeriodicity;

    public DrugDto dtoDrug(Drug d){
        id= d.getId();
        if(d.getDrugType()!=null){
            drugTypeId=d.getDrugType().getId();
        }
        if(d.getPeriodicity()!=null){
            periodicityId = d.getPeriodicity().getId();
        }
        quantity = d.getQuantity();
        lastUse = d.getLastUse();
        specificationType = d.getSpecificationType();
        specificationPeriodicity =d.getSpecificationPeriodicity();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrugTypeId() {
        return drugTypeId;
    }

    public void setDrugTypeId(Long drugTypeId) {
        this.drugTypeId = drugTypeId;
    }

    public Long getPeriodicityId() {
        return periodicityId;
    }

    public void setPeriodicityId(Long periodicityId) {
        this.periodicityId = periodicityId;
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

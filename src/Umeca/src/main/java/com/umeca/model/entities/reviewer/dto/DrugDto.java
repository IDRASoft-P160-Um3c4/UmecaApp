package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.entities.reviewer.Drug;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private Long typeId;
    private Long perId;
    private String quantity;
    private String lastUse;
    private String specificationType;
    private String specificationPeriodicity;

    public DrugDto dtoDrug(Drug d){
        id= d.getId();
        if(d.getDrugType()!=null){
            typeId =d.getDrugType().getId();
        }
        if(d.getPeriodicity()!=null){
            perId = d.getPeriodicity().getId();
        }
        quantity = d.getQuantity();
        if(d.getLastUse()!=null){
            Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            date.setTime(d.getLastUse().getTime());
            this.lastUse = formatter.format(date);
        }
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

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLastUse() {
        return lastUse;
    }

    public void setLastUse(String lastUse) {
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

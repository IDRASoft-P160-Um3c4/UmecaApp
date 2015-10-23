package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletDrugTypeDto;
import com.umeca.model.dto.tablet.catalog.TabletPeriodicityDto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabletDrugDto {

    public TabletDrugDto() {
    }

    public TabletDrugDto(Long id, String quantity, Date lastUse, Boolean block, String specificationType, String specificationPeriodicity, String onsetAge,
                         Long idDT, String nameDT, Boolean specificationDT, Boolean isObsoleteDT,
                         Long idP, String nameP, Boolean isObsoleteP, Boolean specificationP) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.webId = id;
        this.quantity = quantity;
        this.lastUse = lastUse == null ? null : sdf.format(lastUse);
        this.block = block;
        this.specificationType = specificationType;
        this.specificationPeriodicity = specificationPeriodicity;
        this.onsetAge = onsetAge;

        if (idDT != null) {
            this.drugType = new TabletDrugTypeDto(idDT, nameDT, specificationDT, isObsoleteDT);
        }

        if (idP != null) {
            this.periodicity = new TabletPeriodicityDto(idP, nameP, isObsoleteP, specificationP);
        }
    }

    private Long webId;
    private Long id;
    private String quantity;
    private String lastUse;
    private Boolean block;
    private String specificationType;
    private String specificationPeriodicity;
    private String onsetAge;
    private TabletDrugTypeDto drugType;
    private TabletPeriodicityDto periodicity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
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

    public String getOnsetAge() {
        return onsetAge;
    }

    public void setOnsetAge(String onsetAge) {
        this.onsetAge = onsetAge;
    }

    public TabletDrugTypeDto getDrugType() {
        return drugType;
    }

    public void setDrugType(TabletDrugTypeDto drugType) {
        this.drugType = drugType;
    }

    public TabletPeriodicityDto getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(TabletPeriodicityDto periodicity) {
        this.periodicity = periodicity;
    }

    public Long getWebId() {
        return webId;
    }

    public void setWebId(Long webId) {
        this.webId = webId;
    }
}

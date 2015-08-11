package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.LongExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class ChannelingView implements EntityGrid {

    private Long id;
    private Long consecutive;
    private Boolean canDrop;
    private String consecutiveTx;
    private String channelingType;
    private String name;
    private String institutionType;
    private String institutionName;
    private String isFulfilledTx;

    public ChannelingView(Long id, Long consecutive, Boolean isAuthorizeToDrop, String channelingType, String name,
                          String institutionType, String institutionName, Boolean isFulfilled) {
        this.id = id;
        this.consecutive = consecutive;
        this.canDrop = isAuthorizeToDrop == null ? true : false;
        this.channelingType = channelingType;
        this.name = name;
        this.institutionType = institutionType;
        this.institutionName = institutionName;
        this.isFulfilledTx = isFulfilled == null ? "NA" : (isFulfilled ? "Si" : "No");
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsecutive() {
        return consecutive;
    }

    public void setConsecutive(Long consecutive) {
        this.consecutive = consecutive;
    }

    public Boolean getCanDrop() {
        return canDrop;
    }

    public void setCanDrop(Boolean canDrop) {
        this.canDrop = canDrop;
    }

    public void setConsecutiveTx(String consecutiveTx) {
        this.consecutiveTx = consecutiveTx;
    }

    public String getChannelingType() {
        return channelingType;
    }

    public void setChannelingType(String channelingType) {
        this.channelingType = channelingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getConsecutiveTx() {
        return LongExt.paddingLeft("0", "4", consecutive);
    }

    public String getIsFulfilledTx() {
        return isFulfilledTx;
    }

    public void setIsFulfilledTx(String isFulfilledTx) {
        this.isFulfilledTx = isFulfilledTx;
    }
}

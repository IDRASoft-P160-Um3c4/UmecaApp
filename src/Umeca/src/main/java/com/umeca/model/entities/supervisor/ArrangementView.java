package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 09/06/2014.
 */
public class ArrangementView {

    public Long id;

    public String name;

    public String description;

    public Boolean selVal;

    public Boolean isDefault;

    public Boolean isExclusive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSelVal() {
        return selVal;
    }

    public void setSelVal(Boolean selVal) {
        this.selVal = selVal;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsExclusive() {
        return isExclusive;
    }

    public void setIsExclusive(Boolean isExclusive) {
        this.isExclusive = isExclusive;
    }
}

package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 09/06/2014.
 */
public class ArrangementView {

    private Long id;

    private String name;

    private String description;

    private Boolean selVal;

    private Boolean isDefault;

    private Boolean isExclusive;

    //para el reporteador

    private Boolean isNational;
    private Integer type;

    public ArrangementView() {

    }

    public ArrangementView(Long id, String description, Boolean isNational, Integer type) {
        this.id = id;
        this.description = description;
        this.isNational = isNational;
        this.type = type;
    }

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

    public Boolean getIsNational() {
        return isNational;
    }

    public void setIsNational(Boolean isNational) {
        this.isNational = isNational;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

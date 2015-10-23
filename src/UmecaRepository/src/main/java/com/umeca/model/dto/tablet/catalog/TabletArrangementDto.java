package com.umeca.model.dto.tablet.catalog;

public class TabletArrangementDto {

    public TabletArrangementDto(){}

    public TabletArrangementDto(Long id, String description, Integer type, Boolean isNational, Integer index, Boolean isObsolete, Boolean isDefault, Boolean isExclusive) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.isNational = isNational;
        this.index = index;
        this.isObsolete = isObsolete;
        this.isDefault = isDefault;
        this.isExclusive = isExclusive;
    }

    private Long id;
    private String description;
    private Integer type;
    private Boolean isNational;
    private Integer index;
    private Boolean isObsolete;
    private Boolean isDefault;
    private Boolean isExclusive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsNational() {
        return isNational;
    }

    public void setIsNational(Boolean isNational) {
        this.isNational = isNational;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
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

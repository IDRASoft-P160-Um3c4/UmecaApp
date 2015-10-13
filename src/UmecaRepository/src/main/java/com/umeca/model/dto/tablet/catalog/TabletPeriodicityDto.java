package com.umeca.model.dto.tablet.catalog;

public class TabletPeriodicityDto {

    public TabletPeriodicityDto(){}

    public TabletPeriodicityDto(Long id, String name, Boolean isObsolete, Boolean specification) {
        this.id = id;
        this.name = name;
        this.isObsolete = isObsolete;
        this.specification = specification;
    }

    private Long id;
    private String name;
    private Boolean isObsolete;
    private Boolean specification;

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

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }
}

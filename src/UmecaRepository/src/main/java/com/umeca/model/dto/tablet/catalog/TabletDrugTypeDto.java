package com.umeca.model.dto.tablet.catalog;

public class TabletDrugTypeDto {

    public TabletDrugTypeDto(){}

    public TabletDrugTypeDto(Long id, String name, Boolean specification, Boolean isObsolete) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.isObsolete = isObsolete;
    }

    private Long id;
    private String name;
    private Boolean specification;
    private Boolean isObsolete;

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

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
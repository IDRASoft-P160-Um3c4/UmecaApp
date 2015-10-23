package com.umeca.model.dto.tablet.catalog;

public class TabletCrimeCatalogDto {

    public TabletCrimeCatalogDto(){}

    public TabletCrimeCatalogDto(Long id, String name, String description, Boolean obsolete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.obsolete = obsolete;
    }

    private Long id;
    private String name;
    private String description;
    private Boolean obsolete;

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

    public Boolean getObsolete() {
        return obsolete;
    }

    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }
}

package com.umeca.model.dto.tablet.catalog;

public class TabletImmigrationDocumentDto {

    public TabletImmigrationDocumentDto(){}

    public TabletImmigrationDocumentDto(Long id, String name, Boolean specification, Boolean obsolete) {
        this.id = id;
        this.name = name;
        this.specification = specification;
        this.obsolete = obsolete;
    }

    private Long id;
    private String name;
    private Boolean specification;
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

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }

    public Boolean getObsolete() {
        return obsolete;
    }

    public void setObsolete(Boolean obsolete) {
        this.obsolete = obsolete;
    }
}

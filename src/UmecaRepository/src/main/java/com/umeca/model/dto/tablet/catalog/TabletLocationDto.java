package com.umeca.model.dto.tablet.catalog;

import com.umeca.model.catalog.Location;

public class TabletLocationDto {

    public TabletLocationDto(){}

    public TabletLocationDto(Long id, String name, String abbreviation, String description, String zipCode) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.description = description;
        this.zipCode = zipCode;
    }

    public TabletLocationDto(Location location){
        this.id = id;
        this.name = location.getName();
        this.abbreviation = location.getAbbreviation();
        this.description = location.getDescription();
        this.zipCode = location.getZipCode();
    }

    private Long id;
    private TabletMunicipalityDto municipality;
    private String name;
    private String abbreviation;
    private String description;
    private String zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TabletMunicipalityDto getMunicipality() {
        return municipality;
    }

    public void setMunicipality(TabletMunicipalityDto municipality) {
        this.municipality = municipality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

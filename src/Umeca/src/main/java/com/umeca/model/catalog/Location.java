package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 04:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "CatLocation")
public class Location {
    @Id
    @GeneratedValue
    @Column(name="LocationId")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MunicipalityId", nullable = true)
    private Municipality municipality;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "Abbreviation", nullable = false, length = 100)
    private String abbreviation;

    @Column(name = "Description", nullable = false, length = 100)
    private String description;

    @Column(name="ZipCode", nullable = false, length = 10)
    private String zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
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

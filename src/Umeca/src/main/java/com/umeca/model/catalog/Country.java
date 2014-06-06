package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 04:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="CatCountry")
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "CountryId")
    private Long id;

    @Column(name="Name", nullable = false, length = 50)
    private String name;

    @Column(name="Abbreviation", nullable = false, length = 100)
    private String abbreviation;

    @Column(name="Description", nullable = false, length = 100)
    private String description;

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
}

package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 04:03 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="CatState")
public class State {

    @Id
    @GeneratedValue
    @Column(name = "StateId")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "CountryId", nullable =false)
    private Country country;

    @Column(name = "Name", length = 50, nullable = false)
    private String name;

    @Column(name = "Abbreviation", length = 100, nullable = false)
    private String abbreviation;

    @Column(name = "Description", length = 100, nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

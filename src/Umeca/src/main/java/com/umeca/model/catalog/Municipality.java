package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "CatMunicipality")
public class Municipality {
    @Id
    @GeneratedValue
    @Column(name="MunicipalityId")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "StateId", nullable = false)
    private State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

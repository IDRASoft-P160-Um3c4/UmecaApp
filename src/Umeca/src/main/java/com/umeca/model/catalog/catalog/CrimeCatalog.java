package com.umeca.model.catalog.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 31/10/14
 * Time: 04:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "cat_crime")
public class CrimeCatalog {
    @Id
    @GeneratedValue
    @Column(name = "id_crime_cat")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id_group", nullable =false)
    private GroupCrime groupCrime;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @Column(name = "is_obsolete")
    private Boolean obsolete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupCrime getGroupCrime() {
        return groupCrime;
    }

    public void setGroupCrime(GroupCrime groupCrime) {
        this.groupCrime = groupCrime;
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

package com.umeca.model.catalog.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 31/10/14
 * Time: 04:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_group_crime")
public class GroupCrime {

    @Id
    @GeneratedValue
    @Column(name = "id_group")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "is_obsolete")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }
}

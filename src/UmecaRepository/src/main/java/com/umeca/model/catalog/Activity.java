package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 01:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_activity")
public class Activity {
    @Id
    @Column(name="id_activity")
    private Long id;

    @Column(name="activity", length=255, nullable=false)
    private String name;

    @Column(name="specification")
    private Boolean specification;

    @Column(name="is_obsolete", nullable = false)
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

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }
}

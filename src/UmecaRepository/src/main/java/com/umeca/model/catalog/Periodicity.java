package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 12:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_periodicity")
public class Periodicity {
    @Id
    @Column(name="id_periodicity")
    private Long id;

    @Column(name="periodicity", length=255, nullable=false)
    private String name;

    @Column(name="is_obsolete")
    private Boolean isObsolete;

    @Column(name="specification")
    private Boolean specification;

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

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }
}
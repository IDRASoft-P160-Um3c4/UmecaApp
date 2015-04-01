package com.umeca.model.catalog;

import javax.persistence.*;

@Entity
@Table(name = "cat_area")
public class Area {
    @Id
    @Column(name = "id_area")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_obsolete")
    private Boolean isObsolete;

    @Column(name = "specification")
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
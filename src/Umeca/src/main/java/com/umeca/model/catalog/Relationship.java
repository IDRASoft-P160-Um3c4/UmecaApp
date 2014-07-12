package com.umeca.model.catalog;

import javax.persistence.*;

@Entity
@Table(name="cat_relationship")
public class Relationship {
    @Id
    @Column(name="id_relationship")
    private Long id;

    @Column(name="relationship", length=255, nullable=false)
    private String name;

    @Column(name="is_obsolete")
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

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }
}
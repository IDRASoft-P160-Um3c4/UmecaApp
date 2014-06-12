package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name = "aid_source")
public class AidSource {

    @Id
    @GeneratedValue
    @Column(name = "id_aid_source")
    private Long id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "description", length = 1500, nullable = false)
    private String description;

    @Column(name = "is_obsolete", nullable = false)
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

    public Boolean getObsolete() {
        return isObsolete;
    }

    public void setObsolete(Boolean obsolete) {
        isObsolete = obsolete;
    }
}

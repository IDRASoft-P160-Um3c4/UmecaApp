package com.umeca.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "cat_event")
public class EventType {
    @Id
    @Column(name="id_event")
    private Long id;

    @Column(name="event", length=255, nullable=false)
    private String name;

    @Column(name="description", length=255, nullable=false)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}

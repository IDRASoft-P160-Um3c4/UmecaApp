package com.umeca.model.catalog.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_request_type")
public class RequestType {
    @Id
    @Column(name="id_request_type")
    private Long id;

    @Column(name="code", length=255, nullable=false)
    private String name;

    @Column(name="request_type", length=255, nullable=false)
    private String description;

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

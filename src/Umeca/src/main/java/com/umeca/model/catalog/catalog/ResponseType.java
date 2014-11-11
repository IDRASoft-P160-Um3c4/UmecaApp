package com.umeca.model.catalog.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 28/08/14
 * Time: 04:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "cat_response_type")
public class ResponseType {
    @Id
    @Column(name="id_response_type")
    private Long id;

    @Column(name="response_type", length=255, nullable=false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

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
}

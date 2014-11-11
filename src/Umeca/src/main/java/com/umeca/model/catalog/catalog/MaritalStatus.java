package com.umeca.model.catalog.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 22/05/14
 * Time: 10:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_marital_status")
public class MaritalStatus {

    @Id
    @Column(name="id_marital_status")
    private Long id;

    @Column(name="marital_status", length=255, nullable=false)
    private String name;

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
}

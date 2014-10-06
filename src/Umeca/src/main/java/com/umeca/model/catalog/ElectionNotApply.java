package com.umeca.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_election_not_apply")
public class ElectionNotApply {
    public ElectionNotApply() {
    }

    public ElectionNotApply(Long id) {
        this.id = id;
    }

    @Id
    @Column(name="id_election")
    private Long id;

    @Column(name="election", length=255, nullable=false)
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


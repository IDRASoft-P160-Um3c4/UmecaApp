package com.umeca.model.catalogs;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 12:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="periodicity")
public class Periodicity {
    @Id
    @Column(name="id_periodicity")
    private Long id;

    @Column(name="periodicity", length=255, nullable=false)
    private String name;

    @Transient
    private String value;

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

    public String getValue() {

        if(id == null)
            return null;

        value = id.toString();

        return value;
    }


    public void setValue(String value) {

        // id = Convert.ToLong(value);

        this.value = value;
    }
}

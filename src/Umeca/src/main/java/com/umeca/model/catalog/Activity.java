package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 01:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="activity")
public class Activity {
    @Id
    @Column(name="id_activity")
    private Long id;

    @Column(name="activity", length=255, nullable=false)
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

package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/05/14
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_day_week")
public class DayWeek {
    @Id
    @Column(name="id_day")
    private Long id;

    @Column(name="day", length=255, nullable=false)
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

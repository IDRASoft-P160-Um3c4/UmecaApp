package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 06:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "cat_status_meeting")
public class StatusMeeting {
    @Id
    @Column(name="id_status")
    private Long id;

    @Column(name="status", length=255, nullable=false)
    private String name;

    @Column(name="description", length=255, nullable=false)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

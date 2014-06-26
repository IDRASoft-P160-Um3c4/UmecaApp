package com.umeca.model.entities.supervisor;

import javax.persistence.*;

/**
 * Created by Vmware on 13/06/2014.
 */
@Entity
@Table(name="framing_occupation")
public class Occupation {

    @Id
    @GeneratedValue
    @Column(name="id_framing_occupation")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="place")
    private String place;

    @Column(name="phone")
    private String phone;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

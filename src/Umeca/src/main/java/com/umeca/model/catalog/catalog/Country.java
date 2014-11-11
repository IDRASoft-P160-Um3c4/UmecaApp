package com.umeca.model.catalog.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 04:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="cat_country")
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "id_country")
    private Long id;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @Column(name="alpha2", nullable = false, length = 2)
    private String alpha2;

    @Column(name="alpha3", nullable = false, length = 3)
    private String alpha3;

    @Column(name="latitude")
    private Long latitude;

    @Column(name="longitude")
    private Long longitude;

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

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}

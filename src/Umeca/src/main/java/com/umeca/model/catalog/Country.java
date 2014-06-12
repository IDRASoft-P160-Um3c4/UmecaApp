package com.umeca.model.catalog;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 5/06/14
 * Time: 04:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="CatCountry")
public class Country {
    @Id
    @GeneratedValue
    @Column(name = "CountryId")
    private Long id;

    @Column(name="Name", nullable = false, length = 50)
    private String name;

    @Column(name="Alpha2", nullable = false, length = 2)
    private String alpha2;

    @Column(name="Alpha3", nullable = false, length = 3)
    private String alpha3;

    @Column(name="Latitude")
    private Long latitude;

    @Column(name="Longitude")
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

package com.umeca.model.dto.tablet.catalog;

public class TabletCountryDto {

    public TabletCountryDto(){}

    public TabletCountryDto(Long id, String name, String alpha2, String alpha3, Long latitude, Long longitude) {
        this.id = id;
        this.name = name;
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Long id;
    private String name;
    private String alpha2;
    private String alpha3;
    private Long latitude;
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

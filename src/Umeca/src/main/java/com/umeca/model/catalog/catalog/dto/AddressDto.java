package com.umeca.model.catalog.catalog.dto;

import com.umeca.model.entities.reviewer.Address;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddressDto {

    private Long id;
    private String street;
    private String outNum;
    private String innNum;
    private String zipCode;
    private String lat;
    private String lng;
    private Long idCase;
    private LocationDto location;
    private String addressRef;

    public AddressDto addressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.outNum = address.getOutNum();
        this.innNum = address.getInnNum();
        this.lat = address.getLat();
        this.lng = address.getLng();
        if (address.getLocation() != null) {
            this.zipCode = address.getLocation().getZipCode();
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public String getInnNum() {
        return innNum;
    }

    public void setInnNum(String innNum) {
        this.innNum = innNum;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = addressRef;
    }
}

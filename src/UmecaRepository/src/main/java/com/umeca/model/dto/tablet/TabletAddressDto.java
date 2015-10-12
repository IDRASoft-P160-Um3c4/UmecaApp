package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletLocationDto;
import com.umeca.model.entities.reviewer.Address;

public class TabletAddressDto{

    public TabletAddressDto(){}

    public TabletAddressDto(Long id, String street, String outNum, String innNum, String lat, String lng, String addressString,
                            Long idL, String nameL, String abbreviationL, String descriptionL, String zipCodeL){
        this.id = id;
        this.street = street;
        this.outNum = outNum;
        this.innNum = innNum;
        this.lat = lat;
        this.lng = lng;
        this.addressString = addressString;

        if(idL!=null){
            this.location = new TabletLocationDto(idL,nameL,abbreviationL,descriptionL,zipCodeL);
        }
    }

    public TabletAddressDto(Address address){
        this.id = address.getId();
        this.street = address.getStreet();
        this.outNum = address.getOutNum();
        this.innNum = address.getInnNum();
        this.lat = address.getLat();
        this.lng = address.getLng();
        this.addressString = address.getAddressString();

        this.location = new TabletLocationDto(address.getLocation());
    }

    private Long id;
    private String street;
    private String outNum;
    private String innNum;
    private String lat;
    private String lng;
    private String addressString;
    private TabletLocationDto location;

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

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public TabletLocationDto getLocation() {
        return location;
    }

    public void setLocation(TabletLocationDto location) {
        this.location = location;
    }
}

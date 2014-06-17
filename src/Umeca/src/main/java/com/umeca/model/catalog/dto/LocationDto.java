package com.umeca.model.catalog.dto;

import com.umeca.model.catalog.Location;

/**
 * Created by Vmware on 10/06/2014.
 */
public class LocationDto {

    Long id;
    String name;
    String zipCode;
    Long munId;
    Long stateId;

    public LocationDto locationDto(Location location){
        this.id = location.getId();
        this.name = location.getName();
        this.zipCode = location.getZipCode();
        if(location.getMunicipality()!=null){
            this.munId = location.getMunicipality().getId();
            if(location.getMunicipality().getState()!=null){
                this.stateId = location.getMunicipality().getState().getId();
            }
        }
        return this;
    }

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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getMunId() {
        return munId;
    }

    public void setMunId(Long munId) {
        this.munId = munId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}

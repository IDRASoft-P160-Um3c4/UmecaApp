package com.umeca.model.catalog.dto;

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

    public AddressDto addressDto(Address address){
        this.id = address.getId();
        this.street = address.getStreet();
        this.outNum = address.getOutNum();
        this.innNum = address.getInnNum();
        if(address.getLocation()!=null){
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
}

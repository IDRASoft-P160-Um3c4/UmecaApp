package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.ImputedHome;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 30/06/14
 * Time: 04:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImputedHomeDto {
    private Long id;

    private Long addressId;
    private String street;
    private String outNum;
    private String innNum;
    private Long locationId;
    private String addressString;
    private Long typeId;
    private String timeLive;
    private Long belongId;
    private String reasonChange;
    private String description;
    private String schedule;
    private String phone;
    private String reasonSecondary;

    public ImputedHomeDto dtoImputedHome(ImputedHome ih, String schedule){
        this.id=ih.getId();
        if(ih.getAddress()!=null){
            Address a = ih.getAddress();
            this.addressId = a.getId();
            this.street = a.getStreet();
            this.outNum  = a.getOutNum();
            this.innNum = a.getInnNum();
            if(a.getLocation()!=null){
                this.locationId = a.getLocation().getId();
            }
            this.addressString = a.getAddressString();
        }
        this.timeLive=ih.getTimeLive();
        if(ih.getHomeType()!=null){
            this.belongId = ih.getHomeType().getId();
        }
        this.reasonChange = ih.getReasonChange();
        this.description = ih.getDescription();
        this.phone = ih.getPhone();
        this.schedule = schedule;
        this.reasonSecondary = ih.getReasonSecondary();
        if(ih.getRegisterType()!=null){
            this.typeId = ih.getRegisterType().getId();
        }

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(String timeLive) {
        this.timeLive = timeLive;
    }

    public Long getBelongId() {
        return belongId;
    }

    public void setBelongId(Long belongId) {
        this.belongId = belongId;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReasonSecondary() {
        return reasonSecondary;
    }

    public void setReasonSecondary(String reasonSecondary) {
        this.reasonSecondary = reasonSecondary;
    }
}

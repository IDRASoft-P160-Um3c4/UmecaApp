package com.umeca.model.entities.supervisor;

public class ContactDataView {

    private String name;
    private String phone;
    private String address;
    private Boolean liveWith;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getLiveWith() {
        return liveWith;
    }

    public void setLiveWith(Boolean liveWith) {
        this.liveWith = liveWith;
    }
}
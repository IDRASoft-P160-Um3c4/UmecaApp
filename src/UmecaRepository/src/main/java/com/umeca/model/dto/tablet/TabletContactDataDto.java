package com.umeca.model.dto.tablet;

public class TabletContactDataDto {

    public TabletContactDataDto() {
    }

    public TabletContactDataDto(Long id, String nameTxt, String phoneTxt, String addressTxt) {
        this.id = id;
        this.nameTxt = nameTxt;
        this.phoneTxt = phoneTxt;
        this.addressTxt = addressTxt;
    }

    private Long id;
    private String nameTxt;
    private String phoneTxt;
    private String addressTxt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt = nameTxt;
    }

    public String getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(String phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public String getAddressTxt() {
        return addressTxt;
    }

    public void setAddressTxt(String addressTxt) {
        this.addressTxt = addressTxt;
    }
}

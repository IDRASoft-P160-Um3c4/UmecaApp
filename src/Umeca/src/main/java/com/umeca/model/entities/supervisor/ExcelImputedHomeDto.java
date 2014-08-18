package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelImputedHomeDto {

    private Long idCase;
    private  String address;
    private String homeType;
    private String regType;

    public ExcelImputedHomeDto(Long idCase, String address, String homeType, String regType) {
        this.idCase = idCase;
        this.address = address;
        this.homeType = homeType;
        this.regType = regType;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }
}

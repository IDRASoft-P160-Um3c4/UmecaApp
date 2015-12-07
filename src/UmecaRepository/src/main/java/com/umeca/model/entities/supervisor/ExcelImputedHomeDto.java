package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Municipality;

/**
 * Created by Vmware on 18/08/2014.
 */
public class ExcelImputedHomeDto {

    private Long idCase;
    private String address;
    private String homeType;
    private String regType;
    private String summaryStr;
    private String isHomelessStr;
    private String zip;
    private String state;
    private String municipality;


    public ExcelImputedHomeDto(Long idCase, String address, String homeType, String regType, String summaryStr,Boolean isHomeless) {
        this.idCase = idCase;
        this.address = address;
        this.homeType = homeType;
        this.regType = regType;
        this.summaryStr = summaryStr;

        if(isHomeless == null || isHomeless.equals(false)){
            this.isHomelessStr = "No";
        }
        else {
            this.isHomelessStr = "Sí";
        }

    }

    public ExcelImputedHomeDto(Long idCase,Boolean isHomeless,String zip, String state,String municipality,String homeType, String regType ) {
        this.idCase = idCase;
        this.homeType = homeType;
        this.regType = regType;
        this.zip = zip;
        this.state = state;
        this.municipality = municipality;

        if(isHomeless == null || isHomeless.equals(false)){
            this.isHomelessStr = "No";
        }
        else {
            this.isHomelessStr = "Sí";
        }

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

    public String getSummaryStr() {
        return summaryStr;
    }

    public void setSummaryStr(String summaryStr) {
        this.summaryStr = summaryStr;
    }

    public String getIsHomelessStr() {
        return isHomelessStr;
    }

    public void setIsHomelessStr(String isHomelessStr) {
        this.isHomelessStr = isHomelessStr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
}

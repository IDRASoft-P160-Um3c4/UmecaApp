package com.umeca.model.entities.supervisor;

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

    public ExcelImputedHomeDto(Long idCase, String address, String homeType, String regType, String summaryStr,Boolean isHomeless) {
        this.idCase = idCase;
        this.address = address;
        this.homeType = homeType;
        this.regType = regType;
        this.summaryStr = summaryStr;

        if(isHomeless == null || isHomeless == false){
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



}

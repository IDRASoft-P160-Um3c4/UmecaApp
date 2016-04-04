package com.umeca.model.dto.humanResources;

import java.util.List;

/**
 * Created by Administrator on 3/29/2016.
 */
public class ImputedDto {

    public String enrollNumber;
    public String name;
    public List<ImputedFingerPrintDto> fingerPrints;

    public ImputedDto(Long enrollNumber, String name, String namep, String namem){
        this.enrollNumber = "" + enrollNumber;
        this.name = name + " " + namep + " " + namem;
    }

    public String getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ImputedFingerPrintDto> getFingerPrints() {
        return fingerPrints;
    }

    public void setFingerPrints(List<ImputedFingerPrintDto> fingerPrints) {
        this.fingerPrints = fingerPrints;
    }
}
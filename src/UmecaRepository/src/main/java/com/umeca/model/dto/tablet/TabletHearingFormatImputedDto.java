package com.umeca.model.dto.tablet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TabletHearingFormatImputedDto {

    TabletHearingFormatImputedDto(){

    }
    public TabletHearingFormatImputedDto(Long id, String name, String lastNameP, String lastNameM, Date birthDate, String imputeTel,
                                         Long idA, String streetA, String outNumA, String innNumA, String latA, String lngA, String addressStringA,
                                         Long idL, String nameL, String abbreviationL, String descriptionL, String zipCodeL) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.birthDate = birthDate == null ? null : sdf.format(birthDate);
        this.imputeTel = imputeTel;
        if (idA != null) {
            this.address = new TabletAddressDto(idA, streetA, outNumA, innNumA, latA, lngA, addressStringA, idL, nameL, abbreviationL, descriptionL, zipCodeL);
        }
    }

    private Long id;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String birthDate;
    private String imputeTel;
    private TabletAddressDto address;

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

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getImputeTel() {
        return imputeTel;
    }

    public void setImputeTel(String imputeTel) {
        this.imputeTel = imputeTel;
    }

    public TabletAddressDto getAddress() {
        return address;
    }

    public void setAddress(TabletAddressDto address) {
        this.address = address;
    }
}

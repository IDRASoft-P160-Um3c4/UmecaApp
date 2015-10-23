package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletFieldVerificationDto;
import com.umeca.model.dto.tablet.catalog.TabletStatusFieldVerificationDto;

public class TabletFieldMeetingSourceDto {

    public TabletFieldMeetingSourceDto(Long id, String value, String jsonValue, Boolean isFinal, Long idFieldList, String reason,
                                       Long idSFV, String nameSFV, String descriptionSFV,
                                       Long idFV, String codeFV, String sectionFV, Integer sectionCodeFV, String fieldNameFV, Integer indexFieldFV, Boolean isObsoleteFV, Integer idSubsectionFV, String typeFV) {
        this.id = id;
        this.value = value;
        this.jsonValue = jsonValue;
        this.isFinal = isFinal;
        this.idFieldList = idFieldList;
        this.reason = reason;

        if(idSFV!=null){
            this.statusFieldVerification=new TabletStatusFieldVerificationDto(idSFV,nameSFV,descriptionSFV);
        }

        if(idFV!=null){
            this.fieldVerification=new TabletFieldVerificationDto(idFV,codeFV,sectionFV,sectionCodeFV,fieldNameFV,indexFieldFV,isObsoleteFV,idSubsectionFV,typeFV);
        }
    }

    private Long id;
    private String value;
    private String jsonValue;
    private Boolean isFinal;
    private Long idFieldList;
    private String reason;
    private TabletStatusFieldVerificationDto statusFieldVerification;
    private TabletFieldVerificationDto fieldVerification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public Boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }

    public Long getIdFieldList() {
        return idFieldList;
    }

    public void setIdFieldList(Long idFieldList) {
        this.idFieldList = idFieldList;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TabletStatusFieldVerificationDto getStatusFieldVerification() {
        return statusFieldVerification;
    }

    public void setStatusFieldVerification(TabletStatusFieldVerificationDto statusFieldVerification) {
        this.statusFieldVerification = statusFieldVerification;
    }

    public TabletFieldVerificationDto getFieldVerification() {
        return fieldVerification;
    }

    public void setFieldVerification(TabletFieldVerificationDto fieldVerification) {
        this.fieldVerification = fieldVerification;
    }
}

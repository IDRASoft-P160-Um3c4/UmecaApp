package com.umeca.model.entities.supervisor;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by DeveloperII on 05/11/2015.
 */
public class ExcelChannelingDto {
    private Long idCase;
    private Boolean channelingRegistered;
    private String channelingType;
    private Boolean isVolunteer;
    private String institutionName;
    private String institutionType;



    public ExcelChannelingDto(Long idCase, String channelingType, Boolean isVolunteer, String institutionName, String institutionType){
        this.idCase = idCase;
        this.channelingType = channelingType;
        this.isVolunteer = isVolunteer;
        this.institutionName = institutionName;
        this.institutionType = institutionType;

    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Boolean getChannelingRegistered() {
        return channelingRegistered;
    }

    public void setChannelingRegistered(Boolean channelingRegistered) {
        this.channelingRegistered = channelingRegistered;
    }

    public String getChannelingType() {
        return channelingType;
    }

    public void setChannelingType(String channelingType) {
        this.channelingType = channelingType;
    }

    public Boolean getIsVolunteer() {
        return isVolunteer;
    }

    public void setIsVolunteer(Boolean isVolunteer) {
        this.isVolunteer = isVolunteer;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }
}

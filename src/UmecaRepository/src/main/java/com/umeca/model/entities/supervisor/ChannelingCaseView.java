package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class ChannelingCaseView implements EntityGrid {

    //Case Id
    private Long id;
    private String idMP;
    private String imputed;
    private String district;
    private String supervisor;

    public ChannelingCaseView(Long id, String idMP, String name, String lastNameA, String lastNameB, String district, String supervisor) {
        this.id = id;
        this.idMP = idMP;
        this.imputed = name + " " + lastNameA + " " + lastNameB;
        this.district = district;
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}



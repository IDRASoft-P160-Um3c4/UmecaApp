package com.umeca.model.entities.supervisor;

public class RelativeAbroadView {
    private Long idRelationship;
    private String name;
    private String address;
    private Boolean selVal;

    public Long getIdRelationship() {
        return idRelationship;
    }

    public void setIdRelationship(Long idRelationship) {
        this.idRelationship = idRelationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSelVal() {
        return selVal;
    }

    public void setSelVal(Boolean selVal) {
        this.selVal = selVal;
    }
}

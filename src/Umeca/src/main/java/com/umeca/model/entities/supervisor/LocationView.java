package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 10/06/2014.
 */
public class LocationView {

    Long id;
    String name;
    MunicipalityView municipality;

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

    public MunicipalityView getMunicipality() {
        return municipality;
    }

    public void setMunicipality(MunicipalityView municipality) {
        this.municipality = municipality;
    }
}

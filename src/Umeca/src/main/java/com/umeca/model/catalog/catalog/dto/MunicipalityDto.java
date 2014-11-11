package com.umeca.model.catalog.catalog.dto;

import com.umeca.model.catalog.catalog.Municipality;

/**
 * Created by Vmware on 10/06/2014.
 */
public class MunicipalityDto {

    Long id;
    String name;

    public MunicipalityDto munDto(Municipality municipality) {
        this.id = municipality.getId();
        this.name = municipality.getName();

        return this;
    }

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
}

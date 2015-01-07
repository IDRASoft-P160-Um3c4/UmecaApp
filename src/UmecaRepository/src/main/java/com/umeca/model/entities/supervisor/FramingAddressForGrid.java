package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class FramingAddressForGrid implements EntityGrid{

    private Long id;
    private String fullAddress;

    public FramingAddressForGrid(Long id, String fullAddress) {
        this.id = id;
        this.fullAddress = fullAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}

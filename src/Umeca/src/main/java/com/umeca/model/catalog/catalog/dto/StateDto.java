package com.umeca.model.catalog.catalog.dto;

import com.umeca.model.catalog.catalog.State;

/**
 * Created by Vmware on 10/06/2014.
 */
public class StateDto {

    Long id;
    String name;


    public StateDto stateDto(State state) {
        this.id = state.getId();
        this.name = state.getName();
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

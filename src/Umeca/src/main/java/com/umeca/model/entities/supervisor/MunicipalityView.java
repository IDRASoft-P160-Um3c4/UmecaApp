package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 10/06/2014.
 */
public class MunicipalityView {

    String name;
    StateView state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateView getState() {
        return state;
    }

    public void setState(StateView state) {
        this.state = state;
    }
}

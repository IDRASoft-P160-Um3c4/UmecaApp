package com.umeca.model.entities.supervisorManager;

import com.umeca.model.shared.OptionListSimple;

import java.util.ArrayList;
import java.util.List;

public class AuthRejMonActivitiesRequest {

    private List<OptionListSimple> lstAutRejActMon = new ArrayList<>();
    private String ps;
    private String comments;

    public List<OptionListSimple> getLstAutRejActMon() {
        return lstAutRejActMon;
    }

    public void setLstAutRejActMon(List<OptionListSimple> lstAutRejActMon) {
        this.lstAutRejActMon = lstAutRejActMon;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

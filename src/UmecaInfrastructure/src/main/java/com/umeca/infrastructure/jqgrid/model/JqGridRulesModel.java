package com.umeca.infrastructure.jqgrid.model;

import java.util.Calendar;
import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 4:57 PM
 */
public class JqGridRulesModel {
    public String field;
    public String op;
    public String data;
    public Boolean bData;
    public List<String> lstInOp;
    public Calendar dtIni;
    public Calendar dtEnd;

    public JqGridRulesModel(){

    }

    public JqGridRulesModel(String field, String data, String op) {
        this.field = field;
        this.data = data;
        this.op = op;
    }

    public JqGridRulesModel(String field, Boolean bData, String op) {
        this.field = field;
        this.bData = bData;
        this.op = op;
    }

    public JqGridRulesModel(String field, List<String> lstInOp, String op) {
        this.field = field;
        this.lstInOp = lstInOp;
        this.op = op;
    }

    public JqGridRulesModel(String field, Calendar dtIni, Calendar dtEnd, String op) {
        this.field = field;
        this.dtIni = dtIni;
        this.dtEnd = dtEnd;
        this.op = op;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getbData() {
        return bData;
    }

    public void setbData(Boolean bData) {
        this.bData = bData;
    }

    public Calendar getDtIni() {
        return dtIni;
    }

    public void setDtIni(Calendar dtIni) {
        this.dtIni = dtIni;
    }

    public Calendar getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(Calendar dtEnd) {
        this.dtEnd = dtEnd;
    }
}

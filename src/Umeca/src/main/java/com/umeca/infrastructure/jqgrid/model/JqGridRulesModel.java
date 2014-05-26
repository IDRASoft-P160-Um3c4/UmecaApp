package com.umeca.infrastructure.jqgrid.model;

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

    public JqGridRulesModel(String field, String data, String op) {
        this.field = field;
        this.data = data;
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
}

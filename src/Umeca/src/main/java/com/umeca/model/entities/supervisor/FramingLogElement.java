package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 28/11/2014.
 */
public class FramingLogElement {
    private String fieldName;
    private String value;
    private Boolean newRow;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getNewRow() {
        return newRow;
    }

    public void setNewRow(Boolean newRow) {
        this.newRow = newRow;
    }
}

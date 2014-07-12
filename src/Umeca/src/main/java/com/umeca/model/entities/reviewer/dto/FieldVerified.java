package com.umeca.model.entities.reviewer.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/07/14
 * Time: 04:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldVerified {
    String name;
    String value;
    Long idList;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getIdList() {
        return idList;
    }

    public void setIdList(Long idList) {
        this.idList = idList;
    }
}

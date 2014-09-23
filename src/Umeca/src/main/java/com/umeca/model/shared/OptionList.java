package com.umeca.model.shared;


public class OptionList {

    private Long id;
    private String value;
    private String name;
    private String description;
    private Integer intValue;


    public OptionList(Long id, String name, String description, Integer intValue){
        this.id = id;
        this.name = name;
        this.description = description;
        this.intValue = intValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }
}

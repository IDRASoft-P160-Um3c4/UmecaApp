package com.umeca.model.shared;

public class SelectOptsList {
    private Long id;
    private String name;
    private String optionA;
    private String optionB;
    private String optionC;
    private Boolean hasSpec;

    public SelectOptsList(Long id, String name, String optionA, String optionB) {
        this.id = id;
        this.name = name;
        this.optionA = optionA;
        this.optionB = optionB;
    }

    public SelectOptsList(Long id, String name, Long idSec) {
        this.id = id;
        this.name = name;
        this.optionA = idSec.toString();
    }

    public SelectOptsList(Long id, String name, Boolean hasSpec) {
        this.id = id;
        this.name = name;
        this.hasSpec = hasSpec;
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

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
}

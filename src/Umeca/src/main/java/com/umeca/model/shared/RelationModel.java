package com.umeca.model.shared;

public class RelationModel {
    private Long firstId;
    private Long secondId;
    private String name;

    public RelationModel(Long firstId, Long secondId) {
        this.firstId = firstId;
        this.secondId = secondId;
    }

    public RelationModel(Long firstId, Long secondId, String name) {
        this.firstId = firstId;
        this.secondId = secondId;
        this.name = name;
    }

    public Long getFirstId() {
        return firstId;
    }

    public void setFirstId(Long firstId) {
        this.firstId = firstId;
    }

    public Long getSecondId() {
        return secondId;
    }

    public void setSecondId(Long secondId) {
        this.secondId = secondId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

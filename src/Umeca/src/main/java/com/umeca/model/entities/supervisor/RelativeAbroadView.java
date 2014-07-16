package com.umeca.model.entities.supervisor;

public class RelativeAbroadView {
    private Long relationshipId;
    private String name;
    private String description;
    private Boolean selVal;

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
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

    public Boolean getSelVal() {
        return selVal;
    }

    public void setSelVal(Boolean selVal) {
        this.selVal = selVal;
    }
}

package com.umeca.model.entities.supervisor;

public class FramingReferenceForView {

    private Long id;
    private String description;
    private Boolean valSel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getValSel() {
        return valSel;
    }

    public void setValSel(Boolean valSel) {
        this.valSel = valSel;
    }
}

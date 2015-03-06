package com.umeca.model.entities.supervisor;

public class FramingReferenceForView {

    private Long id;
    private String description;
    private Boolean valSel;
    private Boolean disabled;

    public FramingReferenceForView(Long id, String nameRef, String nameRel) {
        this.id = id;
        this.description = nameRef + ", " + nameRel;
        this.valSel = false;
    }

    public FramingReferenceForView() {
    }

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

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}

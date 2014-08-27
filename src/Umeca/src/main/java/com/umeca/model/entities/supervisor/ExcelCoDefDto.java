package com.umeca.model.entities.supervisor;

/**
 * Created by Vmware on 19/08/2014.
 */
public class ExcelCoDefDto {

    private Long idCase;
    private String name;
    private String relationship;

    public ExcelCoDefDto(Long idCase, String name, String relationship) {
        this.idCase = idCase;
        this.name = name;
        this.relationship = relationship;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}

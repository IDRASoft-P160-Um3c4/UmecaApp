package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.dto.CatalogDto;
import com.umeca.model.entities.reviewer.CoDefendant;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;

import javax.persistence.*;

public class CoDefendantDto {

    private String fullName;
    private CatalogDto relationship;

    public CoDefendantDto dtoCoDefendant(CoDefendant c){
       fullName = c.getFullName();
        CatalogDto aux = new CatalogDto();
        aux.setId(c.getRelationship().getId());
        aux.setName(c.getRelationship().getName());
        relationship = aux;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public CatalogDto getRelationship() {
        return relationship;
    }

    public void setRelationship(CatalogDto relationship) {
        this.relationship = relationship;
    }
}

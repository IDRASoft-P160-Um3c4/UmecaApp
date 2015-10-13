package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletArrangementDto;

public class TabletAssignedArrangementDto {

    public TabletAssignedArrangementDto(Long id, String description,
                                        Long idA, String descriptionA, Integer typeA, Boolean isNationalA, Integer indexA, Boolean isObsoleteA, Boolean isDefaultA, Boolean isExclusiveA) {
        this.id = id;
        this.description = description;

        if(idA!=null){
            this.arrangement = new TabletArrangementDto(idA, descriptionA, typeA, isNationalA, indexA, isObsoleteA, isDefaultA, isExclusiveA);
        }
    }

    private Long id;
    private String description;
    private TabletArrangementDto arrangement;

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

    public TabletArrangementDto getArrangement() {
        return arrangement;
    }

    public void setArrangement(TabletArrangementDto arrangement) {
        this.arrangement = arrangement;
    }
}

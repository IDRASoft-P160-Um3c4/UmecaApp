package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletActivityDto;

public class TabletRelSocialEnvironmentActivityDto {

    public TabletRelSocialEnvironmentActivityDto(Long id, String specification,Long idAct, String nameAct, Boolean specificationAct, Boolean isObsoleteAct) {
        this.id = id;
        this.specification = specification;
        activity = new TabletActivityDto(idAct,nameAct,specificationAct,isObsoleteAct);
    }

    private Long id;
    private TabletActivityDto activity;
    private String specification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TabletActivityDto getActivity() {
        return activity;
    }

    public void setActivity(TabletActivityDto activity) {
        this.activity = activity;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}

package com.umeca.model.dto.tablet;

import com.umeca.model.entities.reviewer.Meeting;

import java.util.List;

public class TabletSocialEnvironmentDto  {

    public TabletSocialEnvironmentDto(){}

    public TabletSocialEnvironmentDto(Long id, String physicalCondition, String comment) {
        this.id = id;
        this.physicalCondition = physicalCondition;
        this.comment = comment;
    }

    private Long id;
    private String physicalCondition;
    private String comment;
    private List<TabletRelSocialEnvironmentActivityDto> relSocialEnvironmentActivities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TabletRelSocialEnvironmentActivityDto> getRelSocialEnvironmentActivities() {
        return relSocialEnvironmentActivities;
    }

    public void setRelSocialEnvironmentActivities(List<TabletRelSocialEnvironmentActivityDto> relSocialEnvironmentActivities) {
        this.relSocialEnvironmentActivities = relSocialEnvironmentActivities;
    }
}

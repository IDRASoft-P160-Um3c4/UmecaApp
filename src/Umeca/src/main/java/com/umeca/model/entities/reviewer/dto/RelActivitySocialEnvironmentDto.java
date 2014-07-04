package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.entities.reviewer.RelSocialEnvironmentActivity;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 23/06/14
 * Time: 06:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class RelActivitySocialEnvironmentDto {
    private Long id;
    private String specification;

    public RelActivitySocialEnvironmentDto relDto(RelSocialEnvironmentActivity a){
        this.specification = a.getSpecification();
        this.id = a.getActivity().getId();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}

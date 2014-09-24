package com.umeca.model.catalog.dto;

import com.umeca.model.catalog.Election;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CatalogDto {
    private Long id;
    private String name;
    private Boolean specification;

    public CatalogDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CatalogDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSpecification() {
        return specification;
    }

    public void setSpecification(Boolean specification) {
        this.specification = specification;
    }
}

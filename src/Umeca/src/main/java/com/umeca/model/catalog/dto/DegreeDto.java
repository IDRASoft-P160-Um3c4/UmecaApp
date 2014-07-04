package com.umeca.model.catalog.dto;

import com.umeca.model.catalog.Degree;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class DegreeDto {
    private Long id;
    private String name;

    public DegreeDto dtoGrade(Degree degree){
        this.id = degree.getId();
        this.name= degree.getName();
        return this;
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
}

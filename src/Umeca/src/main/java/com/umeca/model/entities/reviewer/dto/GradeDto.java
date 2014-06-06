package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.catalog.Grade;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class GradeDto {
    private Long id;
    private String name;

    public GradeDto dtoGrade(Grade grade){
        this.id = grade.getId();
        this.name=grade.getName();
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

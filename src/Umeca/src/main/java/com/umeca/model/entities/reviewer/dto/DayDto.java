package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.catalog.DayWeek;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class DayDto {
    private Long id;
    private String name;

    public DayDto dtoDay(DayWeek dayWeek){
        this.id= dayWeek.getId();
        this.name = dayWeek.getName();
      return  this;
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

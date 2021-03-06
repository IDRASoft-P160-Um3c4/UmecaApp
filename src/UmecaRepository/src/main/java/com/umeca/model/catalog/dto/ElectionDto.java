package com.umeca.model.catalog.dto;

import com.umeca.model.catalog.DayWeek;
import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.ElectionNotApply;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ElectionDto {
    private Long id;
    private String name;

    public ElectionDto dtoElection(Election election){
        this.id= election.getId();
        this.name = election.getName();
      return  this;
    }

    public ElectionDto dtoElection(ElectionNotApply election){
        this.id= election.getId();
        this.name = election.getName();
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

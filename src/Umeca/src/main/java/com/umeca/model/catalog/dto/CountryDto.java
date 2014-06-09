package com.umeca.model.catalog.dto;

import com.umeca.model.catalog.Country;
import com.umeca.model.catalog.DayWeek;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountryDto {
    private Long id;
    private String name;

    public CountryDto dtoCountry(Country country){
        this.id= country.getId();
        this.name = country.getName();
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

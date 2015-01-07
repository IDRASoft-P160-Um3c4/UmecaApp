package com.umeca.model.catalog.dto;

import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.catalog.Degree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 10:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class AcademicLevelDto {
    Long id;
    String name;
    List<DegreeDto> degrees;

    public AcademicLevelDto doDto(AcademicLevel s){

             this.id = s.getId();
             this.name = s.getName();
             if (s.getDegrees()!=null){
                 degrees = new ArrayList<DegreeDto>();
                     for(Degree g: s.getDegrees()){
                         DegreeDto degreeDto = new DegreeDto();
                     degrees.add(degreeDto.dtoGrade(g));
                 }
             }

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

    public List<DegreeDto> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<DegreeDto> degrees) {
        this.degrees = degrees;
    }
}

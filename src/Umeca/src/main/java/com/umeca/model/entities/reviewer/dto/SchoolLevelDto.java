package com.umeca.model.entities.reviewer.dto;

import com.google.gson.Gson;
import com.umeca.model.catalog.Grade;
import com.umeca.model.catalog.SchoolLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 10:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class SchoolLevelDto {
    Long id;
    String name;
    List<GradeDto> grades;

    public SchoolLevelDto doDto(SchoolLevel s){

             this.id = s.getId();
             this.name = s.getName();
             if (s.getGrades()!=null){
                 grades = new ArrayList<GradeDto>();
                     for(Grade g: s.getGrades()){
                         GradeDto gradeDto = new GradeDto();
                     grades.add(gradeDto.dtoGrade(g));
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

    public List<GradeDto> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeDto> grades) {
        this.grades = grades;
    }
}

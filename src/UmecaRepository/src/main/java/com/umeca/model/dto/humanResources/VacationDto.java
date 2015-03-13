package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.util.Date;

public class VacationDto implements EntityGrid {

    private Long id;
    private Long idEmployee;
    private String name;
    private String start;
    private String end;
    private String comments;

    public VacationDto() {
    }

    //grid
    public VacationDto(Long id, String name, String start, String end, String comments) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.comments = comments;
    }

    //upsert
    public VacationDto(Long id, Long idEmployee, String name, String start, String end, String comments) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.name = name;
        this.start = start;
        this.end = end;
        this.comments = comments;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

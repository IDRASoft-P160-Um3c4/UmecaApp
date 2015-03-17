package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public VacationDto(Long id, String name, Date start, Date end, String comments) {
        this.id = id;
        this.name = name;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
        this.comments = comments;
    }

    //upsert
    public VacationDto(Long id, Long idEmployee, String name, Date start, Date end, String comments) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.name = name;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
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

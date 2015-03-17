package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncapacityDto implements EntityGrid {

    private Long id;
    private Long idEmployee;
    private String docName;
    private String description;
    private String start;
    private String end;
    private String comments;

    public IncapacityDto() {

    }

    //upsert
    public IncapacityDto(Long id, Long idEmployee, String docName, String description, Date start, Date end, String comments) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.docName = docName;
        this.description = description;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
        this.comments = comments;
    }


    //grid
    public IncapacityDto(Long id, String description, Date start, Date end, String comments) {
        this.id = id;
        this.description = description;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

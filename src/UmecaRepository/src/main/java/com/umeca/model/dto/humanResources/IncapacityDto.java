package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncapacityDto implements EntityGrid {

    private Long id;
    private Long idEmployee;
    private String docName;
    private String descriptionIn;
    private String start;
    private String end;
    private String comments;
    private Long fileId;

    public IncapacityDto() {

    }

    //upsert
    public IncapacityDto(Long id, Long idEmployee, String docName, String description, Date start, Date end, String comments, Long fileId) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.docName = docName;
        this.descriptionIn = description;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
        this.comments = comments;
        this.fileId = fileId;
    }


    //grid
    public IncapacityDto(Long id, String description, Date start, Date end, String comments, Long fileId) {
        this.id = id;
        this.descriptionIn = description;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (start != null) {
            this.start = formatter.format(start);
        }
        if (end != null) {
            this.end = formatter.format(end);
        }
        this.comments = comments;
        this.fileId = fileId;
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

    public String getDescriptionIn() {
        return descriptionIn;
    }

    public void setDescriptionIn(String descriptionIn) {
        this.descriptionIn = descriptionIn;
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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}

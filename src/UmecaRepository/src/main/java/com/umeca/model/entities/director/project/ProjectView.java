package com.umeca.model.entities.director.project;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ProjectView implements EntityGrid {

    private Long id;

    private Calendar creationDate;

    private String stCreationDate;

    private String name;

    private String status;

    public ProjectView(Long id, String name, Calendar creationDate, String status) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public String getStCreationDate() {
        if(creationDate == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.FORMAT_CALENDAR_III);
        return sdf.format(creationDate.getTime());
    }

    public void setStCreationDate(String stCreationDate) {
        this.stCreationDate = stCreationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

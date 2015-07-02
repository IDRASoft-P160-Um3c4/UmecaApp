package com.umeca.model.entities.director.project;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ProjectActivityView implements EntityGrid {

    private Long id;

    private Calendar creationDate;

    private String stCreationDate;

    private String name;

    private String description;

    private String activityName;

    private String activityDescription;

    private String status;

    public ProjectActivityView(Long id, Calendar creationDate, String name, String description) {
        this.id = id;
        this.creationDate = creationDate;
        this.name = name;
        this.description = description;
    }

    public ProjectActivityView(Long id, String name, String activityName, String activityDescription, Calendar creationDate, String status) {
        this.id = id;
        this.name = name;
        this.activityName = activityName;
        this.activityDescription = activityDescription;
        this.creationDate = creationDate;
        this.status = status;
    }

    @Override
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

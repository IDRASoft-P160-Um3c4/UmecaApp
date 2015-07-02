package com.umeca.model.entities.director.activityReport;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class ActivityReportDirectorView implements EntityGrid {

    private Long id;
    private Calendar creationDate;
    private String stCreationDate;
    private String reportName;
    private String description;

    public ActivityReportDirectorView(Long id, Calendar creationDate, String reportName, String description) {
        this.id = id;
        this.creationDate = creationDate;
        this.reportName = reportName;
        this.description = description;
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

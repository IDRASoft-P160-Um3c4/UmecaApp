package com.umeca.model.entities.shared.activityReport;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class ActivityReportView implements EntityGrid {

    private Long id;
    private Calendar creationDate;
    private String stCreationDate;
    private String reportName;
    private Integer reportFor;
    private String isForManager;
    private String description;

    public ActivityReportView(Long id, Calendar creationDate, String reportName, Integer reportFor) {
        this.id = id;
        this.creationDate = creationDate;
        this.reportName = reportName;
        this.reportFor = reportFor;
    }


    public ActivityReportView(Long id, String reportName, String description, Calendar creationDate) {
        this.id = id;
        this.reportName = reportName;
        this.description = description;
        this.creationDate = creationDate;
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

    public Integer getReportFor() {
        return reportFor;
    }

    public void setReportFor(Integer reportFor) {
        this.reportFor = reportFor;
    }

    public String getStCreationDate() {
        if(creationDate == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(creationDate.getTime());
    }

    public String getIsForManager() {
        if(Objects.equals(reportFor, Constants.ACT_REPORT_FOR_DIRECTOR)){
            return "SI";
        }
        return "NO";
    }

    public void setIsForManager(String isForManager) {
        this.isForManager = isForManager;
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

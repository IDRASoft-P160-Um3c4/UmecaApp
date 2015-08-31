package com.umeca.model.entities.director.activityReport;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class WizardActivityReportView implements EntityGrid {

    private Long id;
    private Calendar creationDate;
    private String stCreationDate;
    private Calendar reportDate;
    private String stReportDate;
    private String reportName;
    private String reportDescription;
    private String creatorUserName;

    public WizardActivityReportView(Long id, Calendar reportDate, String reportName, String reportDescription, Calendar creationDate, String creatorUserName) {
        this.id = id;
        this.reportDate = reportDate;
        stReportDate = CalendarExt.calendarToFormatString(reportDate, Constants.FORMAT_CALENDAR_II);
        this.reportName = reportName;
        this.reportDescription = reportDescription;
        this.creationDate = creationDate;
        stCreationDate = CalendarExt.calendarToFormatString(creationDate, Constants.FORMAT_CALENDAR_II);
        this.creatorUserName = creatorUserName;
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
        return stCreationDate;
    }

    public void setStCreationDate(String stCreationDate) {
        this.stCreationDate = stCreationDate;
    }

    public Calendar getReportDate() {
        return reportDate;
    }

    public void setReportDate(Calendar reportDate) {
        this.reportDate = reportDate;
    }

    public String getStReportDate() {
        return stReportDate;
    }

    public void setStReportDate(String stReportDate) {
        this.stReportDate = stReportDate;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

}

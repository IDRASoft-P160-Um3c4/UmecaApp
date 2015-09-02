package com.umeca.model.entities.shared.activityReport;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.UploadFileGeneric;
import com.umeca.model.shared.Constants;

import javax.persistence.*;
import java.util.Calendar;


public class ActivityReportDto {

    private String creationDateTx;
    private String reportName;
    private String description;
    private String creatorUser;
    private String path;
    private String realFileName;
    private String fileName;

    public ActivityReportDto(Calendar creationDate, String reportName, String description, String creatorUser, String path, String realFileName, String fileName) {
        this.creationDateTx = CalendarExt.calendarToFormatString(creationDate, Constants.FORMAT_CALENDAR_I);
        this.reportName = reportName;
        this.description = description;
        this.creatorUser = creatorUser;
        this.path = path;
        this.realFileName = realFileName;
        this.fileName = fileName;
    }

    public String getCreationDateTx() {
        return creationDateTx;
    }

    public void setCreationDateTx(String creationDateTx) {
        this.creationDateTx = creationDateTx;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRealFileName() {
        return realFileName;
    }

    public void setRealFileName(String realFileName) {
        this.realFileName = realFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

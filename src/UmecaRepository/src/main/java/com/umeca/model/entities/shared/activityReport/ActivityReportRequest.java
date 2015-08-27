package com.umeca.model.entities.shared.activityReport;

public class ActivityReportRequest {

    private String reportName;
    private String description;
    private Integer reportFor;
    private Long fileUploadGenericId;

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

    public Integer getReportFor() {
        return reportFor;
    }

    public void setReportFor(Integer reportFor) {
        this.reportFor = reportFor;
    }

    public Long getFileUploadGenericId() {
        return fileUploadGenericId;
    }

    public void setFileUploadGenericId(Long fileUploadGenericId) {
        this.fileUploadGenericId = fileUploadGenericId;
    }
}

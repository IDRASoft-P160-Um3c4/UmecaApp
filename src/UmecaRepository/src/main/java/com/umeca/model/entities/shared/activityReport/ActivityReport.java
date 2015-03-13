package com.umeca.model.entities.shared.activityReport;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;
import java.util.Calendar;


@Entity
@Table(name = "activity_report")
public class ActivityReport {

    @Id
    @GeneratedValue
    @Column(name = "id_activity_report", nullable = false)
    private Long id;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @Column(name = "report_name", length = 100, nullable = false)
    private String reportName;

    @Column(name="description", length = 500, nullable = false)
    private String description;

    @Column(name="report_for", nullable = false)
    private Integer reportFor;

    @Column(name="report_role", length = 100, nullable = false)
    private String reportRole;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator_user", nullable = false)
    private User creatorUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_updater_user", nullable = true)
    private User updaterUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_upload_file_generic", nullable = false)
    UploadFileGeneric uploadFileGeneric;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

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

    public String getReportRole() {
        return reportRole;
    }

    public void setReportRole(String reportRole) {
        this.reportRole = reportRole;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public User getUpdaterUser() {
        return updaterUser;
    }

    public void setUpdaterUser(User updaterUser) {
        this.updaterUser = updaterUser;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public UploadFileGeneric getUploadFileGeneric() {
        return uploadFileGeneric;
    }

    public void setUploadFileGeneric(UploadFileGeneric uploadFileGeneric) {
        this.uploadFileGeneric = uploadFileGeneric;
    }
}

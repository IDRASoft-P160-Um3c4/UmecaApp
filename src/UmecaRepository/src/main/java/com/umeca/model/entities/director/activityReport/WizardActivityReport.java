package com.umeca.model.entities.director.activityReport;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "wizard_activity_report")
public class WizardActivityReport {

    @Id
    @GeneratedValue
    @Column(name = "id_wizard_activity_report", nullable = false)
    private Long id;

    @Column(name="report_date", nullable = false)
    private Calendar reportDate;

    @Column(name = "report_name", length = 100, nullable = false)
    private String reportName;

    @Column(name="report_description", length = 500, nullable = false)
    private String reportDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator_user", nullable = false)
    private User creatorUser;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @Lob
    @Column(name="activity", nullable = true)
    private String activity;

    @Lob
    @Column(name="supervision", nullable = true)
    private String supervision;

    @Lob
    @Column(name="evaluation", nullable = true)
    private String evaluation;

    @Lob
    @Column(name="management", nullable = true)
    private String management;

    @Lob
    @Column(name="project", nullable = true)
    private String project;

    @Lob
    @Column(name="channeling", nullable = true)
    private String channeling;

    @Lob
    @Column(name="minute", nullable = true)
    private String minute;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_delete_user", nullable = true)
    private User deleteUser;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getReportDate() {
        return reportDate;
    }

    public void setReportDate(Calendar reportDate) {
        this.reportDate = reportDate;
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

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSupervision() {
        return supervision;
    }

    public void setSupervision(String supervision) {
        this.supervision = supervision;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getChanneling() {
        return channeling;
    }

    public void setChanneling(String channeling) {
        this.channeling = channeling;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public User getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(User deleteUser) {
        this.deleteUser = deleteUser;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}

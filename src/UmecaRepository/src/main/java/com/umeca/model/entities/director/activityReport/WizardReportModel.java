package com.umeca.model.entities.director.activityReport;

import java.util.List;

public class WizardReportModel {

    public WizardReportInfo report;
    public List<Long> activity;
    public List<Long> supervision;
    public List<Long> evaluation;
    public List<Long> management;
    public List<Long> project;
    public List<Long> channeling;
    public List<Long> minute;

    public WizardReportInfo getReport() {
        return report;
    }

    public void setReport(WizardReportInfo report) {
        this.report = report;
    }

    public List<Long> getSupervision() {
        return supervision;
    }

    public void setSupervision(List<Long> supervision) {
        this.supervision = supervision;
    }

    public List<Long> getActivity() {
        return activity;
    }

    public void setActivity(List<Long> activity) {
        this.activity = activity;
    }

    public List<Long> getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(List<Long> evaluation) {
        this.evaluation = evaluation;
    }

    public List<Long> getManagement() {
        return management;
    }

    public void setManagement(List<Long> management) {
        this.management = management;
    }

    public List<Long> getProject() {
        return project;
    }

    public void setProject(List<Long> project) {
        this.project = project;
    }

    public List<Long> getChanneling() {
        return channeling;
    }

    public void setChanneling(List<Long> channeling) {
        this.channeling = channeling;
    }

    public List<Long> getMinute() {
        return minute;
    }

    public void setMinute(List<Long> minute) {
        this.minute = minute;
    }
}

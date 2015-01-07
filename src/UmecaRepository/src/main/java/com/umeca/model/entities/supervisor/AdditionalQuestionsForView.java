package com.umeca.model.entities.supervisor;

import java.util.Date;

public class AdditionalQuestionsForView {

    private String observations;
    private Integer addictionTreatment;
    private String addictionTreatmentInstitute;
    private Date addictionTreatmentDate;
    private Integer addictedAcquaintance;
    private String selectedAddictedAcquaintances;
    private Integer relativeAbroad;
    private String selectedRelativesAbroad;
    private Integer obligationIssue;
    private String selectedObligationIssues;

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Integer getAddictionTreatment() {
        return addictionTreatment;
    }

    public void setAddictionTreatment(Integer addictionTreatment) {
        this.addictionTreatment = addictionTreatment;
    }

    public String getAddictionTreatmentInstitute() {
        return addictionTreatmentInstitute;
    }

    public void setAddictionTreatmentInstitute(String addictionTreatmentInstitute) {
        this.addictionTreatmentInstitute = addictionTreatmentInstitute;
    }

    public Date getAddictionTreatmentDate() {
        return addictionTreatmentDate;
    }

    public void setAddictionTreatmentDate(Date addictionTreatmentDate) {
        this.addictionTreatmentDate = addictionTreatmentDate;
    }

    public Integer getAddictedAcquaintance() {
        return addictedAcquaintance;
    }

    public void setAddictedAcquaintance(Integer addictedAcquaintance) {
        this.addictedAcquaintance = addictedAcquaintance;
    }

    public String getSelectedAddictedAcquaintances() {
        return selectedAddictedAcquaintances;
    }

    public void setSelectedAddictedAcquaintances(String selectedAddictedAcquaintances) {
        this.selectedAddictedAcquaintances = selectedAddictedAcquaintances;
    }

    public Integer getRelativeAbroad() {
        return relativeAbroad;
    }

    public void setRelativeAbroad(Integer relativeAbroad) {
        this.relativeAbroad = relativeAbroad;
    }

    public String getSelectedRelativesAbroad() {
        return selectedRelativesAbroad;
    }

    public void setSelectedRelativesAbroad(String selectedRelativesAbroad) {
        this.selectedRelativesAbroad = selectedRelativesAbroad;
    }

    public Integer getObligationIssue() {
        return obligationIssue;
    }

    public void setObligationIssue(Integer obligationIssue) {
        this.obligationIssue = obligationIssue;
    }

    public String getSelectedObligationIssues() {
        return selectedObligationIssues;
    }

    public void setSelectedObligationIssues(String selectedObligationIssues) {
        this.selectedObligationIssues = selectedObligationIssues;
    }
}

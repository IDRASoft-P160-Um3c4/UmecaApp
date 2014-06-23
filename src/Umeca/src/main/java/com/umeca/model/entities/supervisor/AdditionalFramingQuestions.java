package com.umeca.model.entities.supervisor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "additional_framing_questions")
public class AdditionalFramingQuestions {

    @Id
    @GeneratedValue
    @Column(name = "id_additional_framing_questions")
    private Long id;

    @Column(name = "activities", length = 5000)
    private String activities;

    @Column(name = "observations", length = 5000)
    private String observations;

    @Column(name = "addiction_treatment")
    private Integer addictionTreatment;

    @Column(name = "addiction_treatment_institute")
    private String addictionTreatmentInstitute;

    @Column(name = "addiction_treatment_date")
    private Date addictionTreatmentDate;

    @Column(name = "addicted_acquaintance")
    private Integer addictedAcquaintance;

    @OneToMany(mappedBy = "additionalFramingQuestions", cascade = CascadeType.ALL)
    private List<AddictedAcquaintanceRel> addictedAcquaintancesRel;

    @Column(name = "relative_abroad")
    private Integer relativeAbroad;

    @OneToMany(mappedBy = "additionalFramingQuestions", cascade = CascadeType.ALL)
    private List<RelativesAbroadRel> relativesAbroadRel;

    @Column(name = "obligation_issues")
    private Integer obligationIssue;

    @OneToMany(mappedBy = "additionalFramingQuestions", cascade = CascadeType.ALL)
    private List<ObligationIssues> obligationIssues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

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

    public List<AddictedAcquaintanceRel> getAddictedAcquaintancesRel() {
        return addictedAcquaintancesRel;
    }

    public void setAddictedAcquaintancesRel(List<AddictedAcquaintanceRel> addictedAcquaintancesRel) {
        this.addictedAcquaintancesRel = addictedAcquaintancesRel;
    }

    public Integer getRelativeAbroad() {
        return relativeAbroad;
    }

    public void setRelativeAbroad(Integer relativeAbroad) {
        this.relativeAbroad = relativeAbroad;
    }

    public List<RelativesAbroadRel> getRelativesAbroadRel() {
        return relativesAbroadRel;
    }

    public void setRelativesAbroadRel(List<RelativesAbroadRel> relativesAbroadRel) {
        this.relativesAbroadRel = relativesAbroadRel;
    }

    public Integer getObligationIssue() {
        return obligationIssue;
    }

    public void setObligationIssue(Integer obligationIssue) {
        this.obligationIssue = obligationIssue;
    }

    public List<ObligationIssues> getObligationIssues() {
        return obligationIssues;
    }

    public void setObligationIssues(List<ObligationIssues> obligationIssues) {
        this.obligationIssues = obligationIssues;
    }
}
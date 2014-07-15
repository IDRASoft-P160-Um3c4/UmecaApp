package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Drug;
import com.umeca.model.entities.reviewer.ImputedHome;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="framing_meeting")
public class FramingMeeting {

    @Id
    @GeneratedValue
    @Column(name="id_framing_meeting")
    private Long id;

    @Column(name="activities")
    private String activities;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_framing_imputed_personal_data")
    private FramingImputedPersonalData personalData;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_framing_occupation")
    private Occupation occupation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_additional_framing_questions")
    private AdditionalFramingQuestions additionalFramingQuestions;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingAddress> framingAddresses;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingReference> references;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Drug> drugs;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingSelectedSourceRel> selectedSourcesRel;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingSelectedRiskRel> selectedRisksRel;

    @OneToMany(mappedBy = "framingMeeting",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingSelectedThreatRel> selectedThreatsRel;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_process_accompaniment")
    private ProcessAccompaniment processAccompaniment;

    @OneToOne
    @JoinColumn(name = "id_case")
    private Case caseDetention;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public AdditionalFramingQuestions getAdditionalFramingQuestions() {
        return additionalFramingQuestions;
    }

    public void setAdditionalFramingQuestions(AdditionalFramingQuestions additionalFramingQuestions) {
        this.additionalFramingQuestions = additionalFramingQuestions;
    }

    public List<FramingAddress> getFramingAddresses() {
        return framingAddresses;
    }

    public void setFramingAddresses(List<FramingAddress> framingAddresses) {
        this.framingAddresses = framingAddresses;
    }

    public List<FramingReference> getReferences() {
        return references;
    }

    public void setReferences(List<FramingReference> references) {
        this.references = references;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public ProcessAccompaniment getProcessAccompaniment() {
        return processAccompaniment;
    }

    public void setProcessAccompaniment(ProcessAccompaniment processAccompaniment) {
        this.processAccompaniment = processAccompaniment;
    }

    public List<FramingSelectedSourceRel> getSelectedSourcesRel() {
        return selectedSourcesRel;
    }

    public void setSelectedSourcesRel(List<FramingSelectedSourceRel> selectedSourcesRel) {
        this.selectedSourcesRel = selectedSourcesRel;
    }

    public List<FramingSelectedRiskRel> getSelectedRisksRel() {
        return selectedRisksRel;
    }

    public void setSelectedRisksRel(List<FramingSelectedRiskRel> selectedRisksRel) {
        this.selectedRisksRel = selectedRisksRel;
    }

    public List<FramingSelectedThreatRel> getSelectedThreatsRel() {
        return selectedThreatsRel;
    }

    public void setSelectedThreatsRel(List<FramingSelectedThreatRel> selectedThreatsRel) {
        this.selectedThreatsRel = selectedThreatsRel;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public FramingImputedPersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(FramingImputedPersonalData personalData) {
        this.personalData = personalData;
    }
}

package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "framing_meeting")
public class FramingMeeting {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_meeting")
    private Long id;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RelFramingMeetingActivity> relFramingMeetingActivities;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_framing_imputed_personal_data")
    private FramingImputedPersonalData personalData;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_framing_occupation")
    private Occupation occupation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_additional_framing_questions")
    private AdditionalFramingQuestions additionalFramingQuestions;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingAddress> framingAddresses;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingReference> references;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Drug> drugs;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingSelectedSourceRel> selectedSourcesRel;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingSelectedRiskRel> selectedRisksRel;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingSelectedThreatRel> selectedThreatsRel;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingSelectedSafetyFactorRel> selectedSafetyFactorsRel;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_process_accompaniment")
    private ProcessAccompaniment processAccompaniment;

    @OneToOne
    @JoinColumn(name = "id_case")
    private Case caseDetention;

    @Column(name = "is_terminated")
    private Boolean isTerminated;

    @Column(name = "init_date")
    private Date initDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User supervisor;

    @Column(name = "environment_comments", length = 1000)
    private String environmentComments;

    @Column(name = "address_comments", length = 1000)
    private String addressComments;

    @Column(name = "housemate_comments", length = 1000)
    private String housemateComments;

    @Column(name = "references_comments", length = 1000)
    private String referencesComments;

    @Column(name = "drugs_comments", length = 1000)
    private String drugsComments;

    @Column(name = "activities_comments", length = 1000)
    private String activitiesComments;

    @Column(name = "job_comments", length = 1000)
    private String jobComments;

    @Column(name = "school_comments", length = 1000)
    private String schoolComments;

    @Column(name = "victim_comments", length = 1000)
    private String victimComments;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingActivity> activities;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Job> jobs;

    @OneToOne(mappedBy = "framingMeeting", cascade = CascadeType.ALL)
    private School school;

    @OneToMany(mappedBy = "framingMeeting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FramingMeetingLog> framingMeetingLogs;

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

    public List<RelFramingMeetingActivity> getRelFramingMeetingActivities() {
        return relFramingMeetingActivities;
    }

    public void setRelFramingMeetingActivities(List<RelFramingMeetingActivity> relFramingMeetingActivities) {
        this.relFramingMeetingActivities = relFramingMeetingActivities;
    }

    public FramingImputedPersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(FramingImputedPersonalData personalData) {
        this.personalData = personalData;
    }

    public Boolean getIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(Boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getActivitiesComments() {
        return activitiesComments;
    }

    public void setActivitiesComments(String activitiesComments) {
        this.activitiesComments = activitiesComments;
    }

    public String getEnvironmentComments() {
        return environmentComments;
    }

    public void setEnvironmentComments(String environmentComments) {
        this.environmentComments = environmentComments;
    }

    public String getAddressComments() {
        return addressComments;
    }

    public void setAddressComments(String addressComments) {
        this.addressComments = addressComments;
    }

    public String getHousemateComments() {
        return housemateComments;
    }

    public void setHousemateComments(String housemateComments) {
        this.housemateComments = housemateComments;
    }

    public String getReferencesComments() {
        return referencesComments;
    }

    public void setReferencesComments(String referencesComments) {
        this.referencesComments = referencesComments;
    }

    public String getDrugsComments() {
        return drugsComments;
    }

    public void setDrugsComments(String drugsComments) {
        this.drugsComments = drugsComments;
    }

    public String getJobComments() {
        return jobComments;
    }

    public void setJobComments(String jobComments) {
        this.jobComments = jobComments;
    }

    public List<FramingActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<FramingActivity> activities) {
        this.activities = activities;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getSchoolComments() {
        return schoolComments;
    }

    public void setSchoolComments(String schoolComments) {
        this.schoolComments = schoolComments;
    }

    public String getVictimComments() {
        return victimComments;
    }

    public void setVictimComments(String victimComments) {
        this.victimComments = victimComments;
    }

    public List<FramingMeetingLog> getFramingMeetingLogs() {
        return framingMeetingLogs;
    }

    public void setFramingMeetingLogs(List<FramingMeetingLog> framingMeetingLogs) {
        this.framingMeetingLogs = framingMeetingLogs;
    }

    public List<FramingSelectedSafetyFactorRel> getSelectedSafetyFactorsRel() {
        return selectedSafetyFactorsRel;
    }

    public void setSelectedSafetyFactorsRel(List<FramingSelectedSafetyFactorRel> selectedSafetyFactorsRel) {
        this.selectedSafetyFactorsRel = selectedSafetyFactorsRel;
    }
}

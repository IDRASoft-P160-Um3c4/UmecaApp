package com.umeca.model.dto.tablet;

import com.umeca.model.dto.tablet.catalog.TabletStatusMeetingDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TabletMeetingDto {

    public TabletMeetingDto() {
    }

    public TabletMeetingDto(Long id, Integer meetingType, String commentReference, String commentJob, String commentSchool, String commentCountry, String commentHome, String commentDrug, Date dateCreate, Date dateTerminate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        this.id = id;
        this.meetingType = meetingType;
        this.commentReference = commentReference;
        this.commentJob = commentJob;
        this.commentSchool = commentSchool;
        this.commentCountry = commentCountry;
        this.commentHome = commentHome;
        this.commentDrug = commentDrug;
        this.dateCreate = dateCreate == null ? null : sdf.format(dateCreate);
        this.dateTerminate = dateTerminate == null ? null : sdf.format(dateTerminate);
    }

    private Long id;
    private Integer meetingType;
    private String commentReference;
    private String commentJob;
    private String commentSchool;
    private String commentCountry;
    private String commentHome;
    private String commentDrug;
    private String dateCreate;
    private String dateTerminate;
    private TabletStatusMeetingDto status;
    private TabletUserDto reviewer;
    private TabletImputedDto imputed;
    private TabletSocialNetworkDto socialNetwork;
    private TabletSchoolDto school;
    private TabletSocialEnvironmentDto socialEnvironment;
    private TabletLeaveCountryDto leaveCountry;
    private List<TabletReferenceDto> references;
    private List<TabletImputedHomeDto> imputedHomes;
    private List<TabletJobDto> jobs;
    private List<TabletDrugDto> drugs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(Integer meetingType) {
        this.meetingType = meetingType;
    }

    public String getCommentReference() {
        return commentReference;
    }

    public void setCommentReference(String commentReference) {
        this.commentReference = commentReference;
    }

    public String getCommentJob() {
        return commentJob;
    }

    public void setCommentJob(String commentJob) {
        this.commentJob = commentJob;
    }

    public String getCommentSchool() {
        return commentSchool;
    }

    public void setCommentSchool(String commentSchool) {
        this.commentSchool = commentSchool;
    }

    public String getCommentCountry() {
        return commentCountry;
    }

    public void setCommentCountry(String commentCountry) {
        this.commentCountry = commentCountry;
    }

    public String getCommentHome() {
        return commentHome;
    }

    public void setCommentHome(String commentHome) {
        this.commentHome = commentHome;
    }

    public String getCommentDrug() {
        return commentDrug;
    }

    public void setCommentDrug(String commentDrug) {
        this.commentDrug = commentDrug;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateTerminate() {
        return dateTerminate;
    }

    public void setDateTerminate(String dateTerminate) {
        this.dateTerminate = dateTerminate;
    }

    public TabletUserDto getReviewer() {
        return reviewer;
    }

    public void setReviewer(TabletUserDto reviewer) {
        this.reviewer = reviewer;
    }

    public TabletImputedDto getImputed() {
        return imputed;
    }

    public void setImputed(TabletImputedDto imputed) {
        this.imputed = imputed;
    }

    public TabletSocialNetworkDto getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(TabletSocialNetworkDto socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public TabletStatusMeetingDto getStatus() {
        return status;
    }

    public void setStatus(TabletStatusMeetingDto status) {
        this.status = status;
    }

    public TabletSchoolDto getSchool() {
        return school;
    }

    public void setSchool(TabletSchoolDto school) {
        this.school = school;
    }

    public TabletSocialEnvironmentDto getSocialEnvironment() {
        return socialEnvironment;
    }

    public void setSocialEnvironment(TabletSocialEnvironmentDto socialEnvironment) {
        this.socialEnvironment = socialEnvironment;
    }

    public TabletLeaveCountryDto getLeaveCountry() {
        return leaveCountry;
    }

    public void setLeaveCountry(TabletLeaveCountryDto leaveCountry) {
        this.leaveCountry = leaveCountry;
    }

    public List<TabletReferenceDto> getReferences() {
        return references;
    }

    public void setReferences(List<TabletReferenceDto> references) {
        this.references = references;
    }

    public List<TabletImputedHomeDto> getImputedHomes() {
        return imputedHomes;
    }

    public void setImputedHomes(List<TabletImputedHomeDto> imputedHomes) {
        this.imputedHomes = imputedHomes;
    }

    public List<TabletJobDto> getJobs() {
        return jobs;
    }

    public void setJobs(List<TabletJobDto> jobs) {
        this.jobs = jobs;
    }

    public List<TabletDrugDto> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<TabletDrugDto> drugs) {
        this.drugs = drugs;
    }
}

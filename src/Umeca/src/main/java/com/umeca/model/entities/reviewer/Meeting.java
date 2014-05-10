package com.umeca.model.entities.reviewer;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="meeting")
public class Meeting {

    @Id
    @GeneratedValue
    @Column(name="id_meeting")
     private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_reviewer", nullable = true)
    private User reviewer;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_case")
    private Case caseDetention;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private Imputed imputed;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<SocialNetwork> socialNetwork;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<Reference> references;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<Domicile> domiciles;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<Job> jobs;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<School> schools;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<Drug> drugs;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private SocialEnvironment socialEnvironment;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private LeaveCountry leaveCountry;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private CurrentCriminalProceeding currentCriminalProceeding;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private  PreviousCriminalProceeding previousCriminalProceeding;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public Imputed getImputed() {
        return imputed;
    }

    public void setImputed(Imputed imputed) {
        this.imputed = imputed;
    }

    public List<SocialNetwork> getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(List<SocialNetwork> socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<Domicile> getDomiciles() {
        return domiciles;
    }

    public void setDomiciles(List<Domicile> domiciles) {
        this.domiciles = domiciles;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public SocialEnvironment getSocialEnvironment() {
        return socialEnvironment;
    }

    public void setSocialEnvironment(SocialEnvironment socialEnvironment) {
        this.socialEnvironment = socialEnvironment;
    }

    public LeaveCountry getLeaveCountry() {
        return leaveCountry;
    }

    public void setLeaveCountry(LeaveCountry leaveCountry) {
        this.leaveCountry = leaveCountry;
    }

    public CurrentCriminalProceeding getCurrentCriminalProceeding() {
        return currentCriminalProceeding;
    }

    public void setCurrentCriminalProceeding(CurrentCriminalProceeding currentCriminalProceeding) {
        this.currentCriminalProceeding = currentCriminalProceeding;
    }

    public PreviousCriminalProceeding getPreviousCriminalProceeding() {
        return previousCriminalProceeding;
    }

    public void setPreviousCriminalProceeding(PreviousCriminalProceeding previousCriminalProceeding) {
        this.previousCriminalProceeding = previousCriminalProceeding;
    }
}

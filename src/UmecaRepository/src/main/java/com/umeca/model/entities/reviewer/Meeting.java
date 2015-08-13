package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.StatusMeeting;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.dto.GroupMessageMeetingDto;
import com.umeca.model.entities.reviewer.dto.TerminateMeetingMessageDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @JoinColumn(name="id_case",nullable = true)
    private Case caseDetention;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private Imputed imputed;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private ImputedInitial imputedInitial;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private SocialNetwork socialNetwork;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<Reference> references;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<ImputedHome> imputedHomes;

    @OneToMany(mappedBy="meeting", cascade={CascadeType.ALL})
    private List<Job> jobs;

    @OneToOne(mappedBy="meeting", cascade={CascadeType.ALL})
    private School school;

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

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="id_status", nullable = false)
    private StatusMeeting status;

    @Column(name="decline_reason", nullable = true, length = 500)
    private String declineReason;

    @Column(name="meeting_type")
    private Integer meetingType;

    @Column(name="comment_reference", nullable = true, length = 500)
    private String commentReference;

    @Column(name="comment_job", nullable = true, length = 500)
    private String commentJob;

    @Column(name="comment_school", nullable = true, length = 500)
    private String commentSchool;

    @Column(name="comment_country", nullable = true, length = 500)
    private String commentCountry;

    @Column(name="comment_home", nullable = true, length = 500)
    private String commentHome;

    @Column(name="comment_drug", nullable = true, length = 500)
    private String commentDrug;

    @Basic(optional = false)
    @Column(name = "date_create", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name="date_terminate")
    private Date dateTerminate;

    @Column(name="date_terminate_legal")
    private Date dateTerminateLegal;

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

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<ImputedHome> getImputedHomes() {
        return imputedHomes;
    }

    public void setImputedHomes(List<ImputedHome> imputedHomes) {
        this.imputedHomes = imputedHomes;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
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

    public StatusMeeting getStatus() {
        return status;
    }

    public void setStatus(StatusMeeting status) {
        this.status = status;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Integer getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(Integer meetingType) {
        this.meetingType = meetingType;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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

    public Date getDateTerminate() {
        return dateTerminate;
    }

    public void setDateTerminate(Date dateTerminate) {
        this.dateTerminate = dateTerminate;
    }

    public void validateMeeting(TerminateMeetingMessageDto t){
        List<ImputedHome> imputedHomeList = getImputedHomes();
        if(imputedHomeList== null || (imputedHomeList !=null && imputedHomeList.size()==0)){
            List<String> result = new ArrayList<>();
            result.add("Debe registrar al menos un domicilio del imputado.");
            t.getGroupMessage().add(new GroupMessageMeetingDto("imputedHome",result));
        }
        List<PersonSocialNetwork> listPS = getSocialNetwork()==null? new ArrayList<PersonSocialNetwork>() :getSocialNetwork().getPeopleSocialNetwork();
        List<Reference> referenceList = getReferences();
        List<String> listMessSN = new ArrayList<>();
        if ((referenceList==null || (referenceList != null && referenceList.size() == 0))) {
            List<String> listMess = new ArrayList<>();
            listMess.add("Para terminar la entrevista debe agragar al menos una referencia personal.");
            t.getGroupMessage().add(new GroupMessageMeetingDto("reference",listMess));
        }

        if(socialNetwork==null|| (socialNetwork.getComment()==null || (socialNetwork.getComment()!=null && socialNetwork.getComment().equals("")))){
            listMessSN.add(t.template.replace("entity","Las observaciones"));
        }

        if((listPS== null ||( listPS!= null && listPS.size()==0))){
            listMessSN.add("Para terminar la entrevista debe agregar al menos una persona en su red social.");
        }

        if(listMessSN.size()>0){
            t.getGroupMessage().add(new GroupMessageMeetingDto("socialNetwork",listMessSN));
        }
        List<Job> jobList = getJobs();
        if(jobList==null || (jobList!=null && jobList.size()==0)){
            List<String> result = new ArrayList<>();
            result.add("Debe agregar al menos un empleo del imputado.");
            t.getGroupMessage().add(new GroupMessageMeetingDto("job",result));
        }
        List<Drug> drugsList= getDrugs();
        if(drugsList==null || (drugsList!=null && drugsList.size()==0)){
          List<String> result = new ArrayList<>();
          result.add("Debe agregar al menos una sustancia que consume el imputado. (En caso de no consumir sustancias seleccione otro y especifique ninguna)");
          t.getGroupMessage().add(new GroupMessageMeetingDto("drug",result));
        }
    }

    public Date getDateTerminateLegal() {
        return dateTerminateLegal;
    }

    public void setDateTerminateLegal(Date dateTerminateLegal) {
        this.dateTerminateLegal = dateTerminateLegal;
    }

    public ImputedInitial getImputedInitial() {
        return imputedInitial;
    }

    public void setImputedInitial(ImputedInitial imputedInitial) {
        this.imputedInitial = imputedInitial;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }
}

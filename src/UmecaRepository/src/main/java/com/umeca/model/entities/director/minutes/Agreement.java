package com.umeca.model.entities.director.minutes;

import com.umeca.model.catalog.Area;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.RequestAgreement;
import com.umeca.model.entities.shared.Observation;
import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue
    @Column(name = "id_agreement")
    private Long id;

    @Column(name = "title",length = 750)
    private String title;

    @Column(name = "theme",length = 750)
    private String theme;

    @Column(name = "agreement_date")
    private Date agreementDate;

    @Column(name = "comments",length = 750)
    private String comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area")
    private Area area;

    @Column(name = "spec_area",length = 750)
    private String specArea;

    @Column(name = "is_done")
    private Boolean isDone;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "finished_comment",length = 750)
    private String finishedComment;

    @Column(name = "finish_date")
    private Date finishDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User finishUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_minute", nullable = false)
    private Minute minute;

    @OneToMany(mappedBy = "agreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Observation> observations;

    @Column(name = "st_code_agreement")
    private String stCode;

    @OneToMany(mappedBy = "agreement", cascade = CascadeType.ALL)
    private List<RequestAgreement> requestsAgreement;

    @OneToMany
    @JoinTable(name = "agreement_file_rel",
            joinColumns = {@JoinColumn(name = "id_agreement", referencedColumnName = "id_agreement")},
            inverseJoinColumns = {@JoinColumn(name = "id_upload_file_generic", referencedColumnName = "id_upload_file_generic", unique = true)})
    private List<UploadFileGeneric> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getAgreementDate() {
        return agreementDate;
    }

    public void setAgreementDate(Date agreementDate) {
        this.agreementDate = agreementDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public User getFinishUser() {
        return finishUser;
    }

    public void setFinishUser(User finishUser) {
        this.finishUser = finishUser;
    }

    public Minute getMinute() {
        return minute;
    }

    public void setMinute(Minute minute) {
        this.minute = minute;
    }

    public String getFinishedComment() {
        return finishedComment;
    }

    public void setFinishedComment(String finishedComment) {
        this.finishedComment = finishedComment;
    }

    public String getSpecArea() {
        return specArea;
    }

    public void setSpecArea(String specArea) {
        this.specArea = specArea;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public String getStCode() {
        return stCode;
    }

    public void setStCode(String stCode) {
        this.stCode = stCode;
    }

    public List<RequestAgreement> getRequestsAgreement() {
        return requestsAgreement;
    }

    public void setRequestsAgreement(List<RequestAgreement> requestsAgreement) {
        this.requestsAgreement = requestsAgreement;
    }
}
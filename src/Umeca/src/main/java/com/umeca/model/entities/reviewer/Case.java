package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.supervisor.HearingFormat;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "case_detention")
public class Case {
    @Id
    @GeneratedValue
    @Column(name = "id_case")
    private Long id;

    @Column(name = "id_folder", length = 15, nullable = false, unique = true)
    private String idFolder;

    @Column(name = "id_mp", length = 15, nullable = true)
    private String idMP;

    @Column(name = "recidivist", nullable = false)
    private Boolean recidivist;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private Meeting meeting;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private Meeting conditionalMeeting;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_status", nullable = false)
    private StatusCase status;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private TechnicalReview technicalReview;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private HearingFormat hearingFormat;


    @Transient
    private String idString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Boolean getRecidivist() {
        return recidivist;
    }

    public void setRecidivist(Boolean recidivist) {
        this.recidivist = recidivist;
    }

    public String getIdString() {
        this.idString = String.format("%010d", id);
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public StatusCase getStatus() {
        return status;
    }

    public void setStatus(StatusCase status) {
        this.status = status;
    }

    public TechnicalReview getTechnicalReview() {
        return technicalReview;
    }

    public void setTechnicalReview(TechnicalReview technicalReview) {
        this.technicalReview = technicalReview;
    }

    public HearingFormat getHearingFormat() {
        return hearingFormat;
    }

    public void setHearingFormat(HearingFormat hearingFormat) {
        this.hearingFormat = hearingFormat;
    }

    public Meeting getConditionalMeeting() {
        return conditionalMeeting;
    }

    public void setConditionalMeeting(Meeting conditionalMeeting) {
        this.conditionalMeeting = conditionalMeeting;
    }
}

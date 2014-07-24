package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.supervisor.FolderConditionalReprieve;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.MonitoringPlan;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "id_folder", length = 15, nullable = false)
    private String idFolder;

    @Column(name = "id_mp", length = 15, nullable = true)
    private String idMP;

    @Column(name = "recidivist", nullable = false)
    private Boolean recidivist;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private Meeting meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private StatusCase status;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private TechnicalReview technicalReview;

    @OneToMany(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private List<HearingFormat> hearingFormats;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private FramingMeeting framingMeeting;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private FolderConditionalReprieve folderConditionalReprieve;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private Verification verification;

    public MonitoringPlan getMonitoringPlan() {
        return monitoringPlan;
    }

    public void setMonitoringPlan(MonitoringPlan monitoringPlan) {
        this.monitoringPlan = monitoringPlan;
    }

    @OneToOne(mappedBy = "caseDetention")
    private MonitoringPlan monitoringPlan;

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

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public FolderConditionalReprieve getFolderConditionalReprieve() {
        return folderConditionalReprieve;
    }

    public void setFolderConditionalReprieve(FolderConditionalReprieve folderConditionalReprieve) {
        this.folderConditionalReprieve = folderConditionalReprieve;
    }

    public List<HearingFormat> getHearingFormats() {
        return hearingFormats;
    }

    public void setHearingFormats(List<HearingFormat> hearingFormats) {
        this.hearingFormats = hearingFormats;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }
}

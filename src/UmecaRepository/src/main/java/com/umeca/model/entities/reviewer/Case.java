package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.CloseCause;
import com.umeca.model.catalog.District;
import com.umeca.model.catalog.StatusCase;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.supervisor.FolderConditionalReprieve;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.entities.supervisor.HearingFormat;
import com.umeca.model.entities.supervisor.MonitoringPlan;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "case_detention")
public class Case {

    @Id
    @GeneratedValue
    @Column(name = "id_case")
    private Long id;

    @Column(name = "id_folder", length = 35, nullable = false)
    private String idFolder;

    @Column(name = "id_mp", length = 35, nullable = true)
    private String idMP;

    @Column(name = "recidivist", nullable = false)
    private Boolean recidivist;

    @OneToOne(mappedBy = "caseDetention", cascade = {CascadeType.ALL})
    private Meeting meeting;

    @Column(name = "date_not_prosecute", nullable = true)
    private Date dateNotProsecute;

    @Column(name = "date_obsolete", nullable = true)
    private Date dateObsolete;

    @Column(name = "close_date", nullable = true)
    private Date closeDate;

    @Column(name = "reopen_date", nullable = true)
    private Date reopenDate;

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

    @Basic(optional = false)
    @Column(name = "date_create", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Column(name = "date_change_arrangement_type")
    private Date dateChangeArrangementType;

    @Column(name = "is_substracted")
    private Boolean isSubstracted;

    @Column(name = "date_substracted")
    private Date dateSubstracted;

    @Column(name = "date_prison")
    private Date datePrison;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private District district;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_supervisor_hf")
    private User lastSupervisorHF;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_umeca_supervisor")
    private User umecaSupervisor;

    @OneToOne(mappedBy = "caseDetention")
    private MonitoringPlan monitoringPlan;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_close_cause")
    private CloseCause closeCause;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user")
    private User creatorUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closer_user")
    private User closerUser;

    @Column(name = "has_hearing_format", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean hasHearingFormat;

    @Column(name = "has_negation", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean hasNegation;

    @Column(name = "date_opinion", nullable = true)
    private Date dateOpinion;

    @Transient
    private String idString;

    public MonitoringPlan getMonitoringPlan() {
        return monitoringPlan;
    }

    public void setMonitoringPlan(MonitoringPlan monitoringPlan) {
        this.monitoringPlan = monitoringPlan;
    }

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

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateNotProsecute() {
        return dateNotProsecute;
    }

    public void setDateNotProsecute(Date dateNotProsecute) {
        this.dateNotProsecute = dateNotProsecute;
    }

    public Date getDateObsolete() {
        return dateObsolete;
    }

    public void setDateObsolete(Date dateObsolete) {
        this.dateObsolete = dateObsolete;
    }

    public Boolean getIsSubstracted() {
        return isSubstracted;
    }

    public void setIsSubstracted(Boolean isSubstracted) {
        this.isSubstracted = isSubstracted;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Date getReopenDate() {
        return reopenDate;
    }

    public void setReopenDate(Date reopenDate) {
        this.reopenDate = reopenDate;
    }

    public Date getDateChangeArrangementType() {
        return dateChangeArrangementType;
    }

    public void setDateChangeArrangementType(Date dateChangeArrangementType) {
        this.dateChangeArrangementType = dateChangeArrangementType;
    }

    public User getLastSupervisorHF() {
        return lastSupervisorHF;
    }

    public void setLastSupervisorHF(User lastSupervisorHF) {
        this.lastSupervisorHF = lastSupervisorHF;
    }

    public User getUmecaSupervisor() {
        return umecaSupervisor;
    }

    public void setUmecaSupervisor(User umecaSupervisor) {
        this.umecaSupervisor = umecaSupervisor;
    }

    public Date getDateSubstracted() {
        return dateSubstracted;
    }

    public void setDateSubstracted(Date dateSubstracted) {
        this.dateSubstracted = dateSubstracted;
    }

    public Date getDatePrison() {
        return datePrison;
    }

    public void setDatePrison(Date datePrison) {
        this.datePrison = datePrison;
    }

    public CloseCause getCloseCause() {
        return closeCause;
    }

    public void setCloseCause(CloseCause closeCause) {
        this.closeCause = closeCause;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public User getCloserUser() {
        return closerUser;
    }

    public void setCloserUser(User closerUser) {
        this.closerUser = closerUser;
    }

    public boolean isHasHearingFormat() {
        return hasHearingFormat;
    }

    public void setHasHearingFormat(boolean hasHearingFormat) {
        this.hasHearingFormat = hasHearingFormat;
    }

    public boolean isHasNegation() {
        return hasNegation;
    }

    public void setHasNegation(boolean hasNegation) {
        this.hasNegation = hasNegation;
    }

    public Date getDateOpinion() {
        return dateOpinion;
    }

    public void setDateOpinion(Date dateOpinion) {
        this.dateOpinion = dateOpinion;
    }
}
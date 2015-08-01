package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.District;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;
import com.umeca.model.entities.reviewer.Crime;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.sql.Time;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hearing_format")
public class HearingFormat {

    @Id
    @GeneratedValue
    @Column(name = "id_hearing_format")
    private Long id;

    @Column(name = "register_timestamp")
    private Calendar registerTime;

    @Column(name = "id_folder")
    private String idFolder;

    @Column(name = "id_judicial")
    private String idJudicial;

//    @Column(name = "room")
//    private String room;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "init_time")
    private Time initTime;

    @Column(name = "end_time")
    private Time endTime;
    @Column(name = "judge_name")
    private String judgeName;

    @Column(name = "mp_name")
    private String mpName;

    @Column(name = "defender_name")
    private String defenderName;

    @Column(name = "terms", length = 1000)
    private String terms;

    @Column(name = "confirm_comment", length = 1000)
    private String confirmComment;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "comments")
    private String comments;

    @Column(name = "umeca_date")
    private Date umecaDate;

    @Column(name = "umeca_time")
    private Time umecaTime;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_user_umeca")
//    private User umecaSupervisor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hearing_type")
    private HearingType hearingType;

    @Column(name = "hearing_type_spec")
    private String hearingTypeSpecification;

    @Column(name = "imputed_presence")
    private Integer imputedPresence;

    @Column(name = "hearing_result")
    private String hearingResult;

    @Column(name = "previous_hearing")
    private Integer previousHearing;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_format_specs")
    private HearingFormatSpecs hearingFormatSpecs;

    @OneToMany(mappedBy = "hearingFormat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AssignedArrangement> assignedArrangements;

    @OneToMany(mappedBy = "hearingFormat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContactData> contacts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case")
    private Case caseDetention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User supervisor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_hearing_format_imputed")
    private HearingFormatImputed hearingImputed;

    @OneToMany(mappedBy="hearingFormat", cascade={CascadeType.ALL})
    private List<Crime> crimeList;

    @Column(name="show_notification")
    private Boolean showNotification;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private District district;

    @Column(name="is_homeless")
    private Boolean isHomeless;

    @Column(name="time_ago")
    private String timeAgo;

    @Column(name="location_place")
    private String locationPlace;

    @Transient
    private Boolean isSubstracted;

    @Transient
    public static final Comparator<HearingFormat> hearingFormatComparator = new Comparator<HearingFormat>() {
        @Override
        public int compare(HearingFormat h1, HearingFormat h2) {
            return h1.getId().compareTo(h2.getId());
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Calendar registerTime) {
        this.registerTime = registerTime;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdJudicial() {
        return idJudicial;
    }

    public void setIdJudicial(String idJudicial) {
        this.idJudicial = idJudicial;
    }

//    public String getRoom() {
//        return room;
//    }
//
//    public void setRoom(String room) {
//        this.room = room;
//    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getInitTime() {
        return initTime;
    }

    public void setInitTime(Time initTime) {
        this.initTime = initTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getMpName() {
        return mpName;
    }

    public void setMpName(String mpName) {
        this.mpName = mpName;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public void setDefenderName(String defenderName) {
        this.defenderName = defenderName;
    }

    public List<Crime> getCrimeList() {
        return crimeList;
    }

    public void setCrimeList(List<Crime> crimeList) {
        this.crimeList = crimeList;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public HearingFormatSpecs getHearingFormatSpecs() {
        return hearingFormatSpecs;
    }

    public void setHearingFormatSpecs(HearingFormatSpecs hearingFormatSpecs) {
        this.hearingFormatSpecs = hearingFormatSpecs;
    }

    public List<AssignedArrangement> getAssignedArrangements() {
        return assignedArrangements;
    }

    public void setAssignedArrangements(List<AssignedArrangement> assignedArrangements) {
        this.assignedArrangements = assignedArrangements;
    }

    public List<ContactData> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactData> contacts) {
        this.contacts = contacts;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public HearingFormatImputed getHearingImputed() {
        return hearingImputed;
    }

    public void setHearingImputed(HearingFormatImputed hearingImputed) {
        this.hearingImputed = hearingImputed;
    }

    public String getConfirmComment() {
        return confirmComment;
    }

    public void setConfirmComment(String confirmComment) {
        this.confirmComment = confirmComment;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getUmecaDate() {
        return umecaDate;
    }

    public void setUmecaDate(Date umecaDate) {
        this.umecaDate = umecaDate;
    }

    public Time getUmecaTime() {
        return umecaTime;
    }

    public void setUmecaTime(Time umecaTime) {
        this.umecaTime = umecaTime;
    }

//    public User getUmecaSupervisor() {
//        return umecaSupervisor;
//    }
//
//    public void setUmecaSupervisor(User umecaSupervisor) {
//        this.umecaSupervisor = umecaSupervisor;
//    }

    public HearingType getHearingType() {
        return hearingType;
    }

    public void setHearingType(HearingType hearingType) {
        this.hearingType = hearingType;
    }

    public String getHearingTypeSpecification() {
        return hearingTypeSpecification;
    }

    public void setHearingTypeSpecification(String hearingTypeSpecification) {
        this.hearingTypeSpecification = hearingTypeSpecification;
    }

    public Integer getImputedPresence() {
        return imputedPresence;
    }

    public void setImputedPresence(Integer imputedPresence) {
        this.imputedPresence = imputedPresence;
    }

    public String getHearingResult() {
        return hearingResult;
    }

    public void setHearingResult(String hearingResult) {
        this.hearingResult = hearingResult;
    }

    public Integer getPreviousHearing() {
        return previousHearing;
    }

    public void setPreviousHearing(Integer previousHearing) {
        this.previousHearing = previousHearing;
    }

    public Boolean getShowNotification() {
        return showNotification;
    }

    public void setShowNotification(Boolean showNotification) {
        this.showNotification = showNotification;
    }

    public Boolean getIsSubstracted() {
        return isSubstracted;
    }

    public void setIsSubstracted(Boolean isSubstracted) {
        this.isSubstracted = isSubstracted;
    }

//    public Long getDistrictId() {
//        return districtId;
//    }
//
//    public void setDistrictId(Long districtId) {
//        this.districtId = districtId;
//    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Boolean getIsHomeless() {
        return isHomeless;
    }

    public void setIsHomeless(Boolean isHomeless) {
        this.isHomeless = isHomeless;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getLocationPlace() {
        return locationPlace;
    }

    public void setLocationPlace(String locationPlace) {
        this.locationPlace = locationPlace;
    }

}

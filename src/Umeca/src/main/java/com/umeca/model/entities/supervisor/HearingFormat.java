package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hearing_format")
public class HearingFormat {

    @Id
    @GeneratedValue
    @Column(name = "id_hearing_format")
    private Long id;

    @Column(name = "appointment_date", nullable = false)
    private Date appointmentDate;

    @Column(name = "register_timestamp", nullable = false)
    private Timestamp registerTimestamp;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "init_time", nullable = false)
    private Time initTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    @Column(name = "judge_name", nullable = false)
    private String judgeName;

    @Column(name = "mp_name", nullable = false)
    private String mpName;

    @Column(name = "defender_name", nullable = false)
    private String defenderName;

    @Column(name = "crimes", length = 5000, nullable = false)
    private String crimes;

    @Column(name = "additional_data", length = 5000, nullable = false)
    private String additionalData;

    @Column(name = "terms", length = 1000, nullable = false)
    private String terms;

    @Column(name = "origin_type", length = 1000, nullable = false)
    private Integer originType;

    @OneToOne(mappedBy = "hearingFormat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HearingFormatSpecs hearingFormatSpecs;

    @OneToMany(mappedBy = "hearingFormat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AssignedArrangement> assignedArrangements;

    @OneToMany(mappedBy = "hearingFormat", fetch = FetchType.LAZY)
    private List<ContactData> contacts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case", nullable = false)
    private Case caseDetention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User supervisor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_hearing_format_imputed")
    private HearingFormatImputed hearingImputed;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Timestamp getRegisterTimestamp() {
        return registerTimestamp;
    }

    public void setRegisterTimestamp(Timestamp registerTimestamp) {
        this.registerTimestamp = registerTimestamp;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    public String getCrimes() {
        return crimes;
    }

    public void setCrimes(String crimes) {
        this.crimes = crimes;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Integer getOriginType() {
        return originType;
    }

    public void setOriginType(Integer originType) {
        this.originType = originType;
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
}

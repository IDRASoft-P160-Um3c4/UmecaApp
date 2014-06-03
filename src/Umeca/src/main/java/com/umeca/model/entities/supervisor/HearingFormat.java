package com.umeca.model.entities.supervisor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "hearing_format")
public class HearingFormat {

    @Id
    @GeneratedValue
    @Column(name = "id_hearing_format")
    private Long id;

    @Column(name = "no_date", length = 255, nullable = false)
    private String noDate;

    @Column(name = "register_time", nullable = false)
    private Timestamp registerTime;

    @Column(name = "hearing_date", nullable = false)
    private Date hearingDate;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "init_time", nullable = false)
    private Timestamp initTime;

    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @Column(name = "judge_name", nullable = false)
    private String judgeName;

    @Column(name = "mp_name", nullable = false)
    private String mpName;

    @Column(name = "defender_name", nullable = false)
    private String defenderName;

    @Column(name = "additional_data", nullable = false)
    private String additionalData;

    @OneToOne(mappedBy = "hearingFormat",cascade = CascadeType.ALL)
    private HearingFormatSpecs hearingFormatSpecs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoDate() {
        return noDate;
    }

    public void setNoDate(String noDate) {
        this.noDate = noDate;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Date getHearingDate() {
        return hearingDate;
    }

    public void setHearingDate(Date hearingDate) {
        this.hearingDate = hearingDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Timestamp getInitTime() {
        return initTime;
    }

    public void setInitTime(Timestamp initTime) {
        this.initTime = initTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }

    public HearingFormatSpecs getHearingFormatSpecs() {
        return hearingFormatSpecs;
    }

    public void setHearingFormatSpecs(HearingFormatSpecs hearingFormatSpecs) {
        this.hearingFormatSpecs = hearingFormatSpecs;
    }
}

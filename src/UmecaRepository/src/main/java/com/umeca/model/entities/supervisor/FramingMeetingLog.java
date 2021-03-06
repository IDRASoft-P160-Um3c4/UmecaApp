package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "framing_meeting_log")
public class FramingMeetingLog {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_meeting_goal")
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "final_value", length = 2500)
    private String finalValue;

    @Column(name = "log_type")
    private String logType;

    @Column(name = "log_index")
    private Integer logIndex;

    @Column(name = "log_date")
    private Calendar logDate;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User supervisor;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting")
    private FramingMeeting framingMeeting;

    @Transient
    List<FramingLogElement> lstElements;

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

    public String getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(String finalValue) {
        this.finalValue = finalValue;
    }

    public Calendar getLogDate() {
        return logDate;
    }

    public void setLogDate(Calendar logDate) {
        this.logDate = logDate;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Integer getLogIndex() {
        return logIndex;
    }

    public void setLogIndex(Integer logIndex) {
        this.logIndex = logIndex;
    }

    public List<FramingLogElement> getLstElements() {
        return lstElements;
    }

    public void setLstElements(List<FramingLogElement> lstElements) {
        this.lstElements = lstElements;
    }
}


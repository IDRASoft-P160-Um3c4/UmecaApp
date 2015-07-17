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
    @Column(name = "id_framing_meeting_goal", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String title;

    @Lob
    @Column(name = "final_value", nullable = false)
    private String finalValue;

    @Column(name = "log_type", nullable = false)
    private String logType;

    @Column(name = "log_index", nullable = false)
    private Integer logIndex;

    @Column(name = "log_date", nullable = false)
    private Calendar logDate;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User supervisor;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting", nullable = false)
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


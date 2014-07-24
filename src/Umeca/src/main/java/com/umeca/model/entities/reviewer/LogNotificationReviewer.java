package com.umeca.model.entities.reviewer;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "log_notification_reviewer")
public class LogNotificationReviewer {

    @Id
    @GeneratedValue
    @Column(name = "id_log_notification_reviewer", nullable = false)
    private Long id;

    @Column(name = "subject", length = 100)
    private String subject;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "message", nullable = false)
    private String message;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sender_user", nullable = false)
    private User senderUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receive_user", nullable = false)
    private User receiveUser;

    @Column(name = "timestamp", nullable = false)
    private Calendar timestamp;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_set_obsolete_user", nullable = true)
    private User obsoleteUser;

    @Column(name = "timestamp_obsolete", nullable = true)
    private Calendar timestampObsolete;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;


    public LogNotificationReviewer() {
        this.isObsolete = false;
        this.timestamp = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public User getObsoleteUser() {
        return obsoleteUser;
    }

    public void setObsoleteUser(User obsoleteUser) {
        this.obsoleteUser = obsoleteUser;
    }

    public Calendar getTimestampObsolete() {
        return timestampObsolete;
    }

    public void setTimestampObsolete(Calendar timestampObsolete) {
        this.timestampObsolete = timestampObsolete;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }
}
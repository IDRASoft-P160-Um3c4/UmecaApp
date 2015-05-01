package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="message")
public class Message {

    @Id
    @GeneratedValue
    @Column(name="id_message", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case", nullable = true)
    private Case caseDetention;

    @OneToMany (mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RelMessageUserReceiver> messageUserReceivers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_sender", nullable = false)
    private User sender;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="body", nullable = false)
    private String body;

    @Column(name="title", length = 500, nullable = false)
    private String title;

    @Column(name="footer", length = 500, nullable = true)
    private String footer;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public List<RelMessageUserReceiver> getMessageUserReceivers() {
        return messageUserReceivers;
    }

    public void setMessageUserReceivers(List<RelMessageUserReceiver> messageUserReceivers) {
        this.messageUserReceivers = messageUserReceivers;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}

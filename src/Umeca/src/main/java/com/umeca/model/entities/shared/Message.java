package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/08/14
 * Time: 04:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="message")
public class Message {

    @Id
    @GeneratedValue
    @Column(name="id_message")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case", nullable = false)
    private Case caseDetention;

    @OneToMany (mappedBy = "message", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RelMessageUserReceiver> messageUserReceivers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_sender", nullable = false)
    private User sender;

    @Column(name="text", length = 1000, nullable = false)
    private String text;

    @Column(name="creation_date")
    private Date creationDate;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

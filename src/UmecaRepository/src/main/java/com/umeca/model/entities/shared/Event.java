    package com.umeca.model.entities.shared;

import com.umeca.model.catalog.EventType;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="event")
public class Event {


    public Event(){

    }

    public Event(Long id, Case caseDetention){
        this.id = id;
        this.caseDetention = caseDetention;
    }

    public Event(Long id, Integer dateId,String name,Case caseDetention){
        this.eventType = new EventType();
        this.id = id;
        this.dateId = dateId;
        this.eventType.setName(name);
        this.caseDetention = caseDetention;
    }

    @Id
    @GeneratedValue
    @Column(name="id_event", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user", nullable = false)
    private User user;

    @Column(name="date", nullable = false)
    private Date date;

    @Column(name="date_id", nullable = false)
    private Integer dateId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id", nullable = false)
    private EventType eventType;

    @Column(name="comments",length=765, nullable=true)
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}

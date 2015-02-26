package com.umeca.model.entities.director.agenda;

import com.umeca.model.catalog.CatPriority;
import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "activity_agenda")
public class ActivityAgenda {

    @Id
    @GeneratedValue
    @Column(name = "id_activity_agenda", nullable = false)
    private Long id;

    @Column(name = "start", nullable = false)
    private Calendar start;

    @Column(name = "search_start", nullable = false)
    private int searchStart;

    @Column(name = "end", nullable = false)
    private Calendar end;

    @Column(name = "search_end", nullable = false)
    private int searchEnd;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_priority", nullable = false)
    private CatPriority priority;

    @Column(name = "place", length = 500, nullable = false)
    private String place;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_owner", nullable = false)
    private User userOwner;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_update", nullable = true)
    private User userUpdate;

    @Column(name = "creation_time", nullable = false)
    private Calendar creationTime;

    @Column(name = "update_time", nullable = true)
    private Calendar updateTime;

    @Column(name = "done_time", nullable = true)
    private Calendar doneTime;

    @Column(name = "isDone", nullable = true)
    private Boolean isDone;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "comments", nullable = true)
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public int getSearchStart() {
        return searchStart;
    }

    public void setSearchStart(int searchStart) {
        this.searchStart = searchStart;
    }

    public int getSearchEnd() {
        return searchEnd;
    }

    public void setSearchEnd(int searchEnd) {
        this.searchEnd = searchEnd;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public Calendar getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Calendar doneTime) {
        this.doneTime = doneTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CatPriority getPriority() {
        return priority;
    }

    public void setPriority(CatPriority priority) {
        this.priority = priority;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(User userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean isDone) {
        this.isDone = isDone;
    }
}

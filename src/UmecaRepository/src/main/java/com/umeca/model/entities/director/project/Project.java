package com.umeca.model.entities.director.project;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue
    @Column(name = "id_project", nullable = false)
    private Long id;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @Column(name="end_date", nullable = true)
    private Calendar endDate;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name="description", length = 500, nullable = false)
    private String description;

    @Column(name = "status", length = 50, nullable = false)
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator_user", nullable = false)
    private User creatorUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_updater_user", nullable = true)
    private User updaterUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_delete_user", nullable = true)
    private User deleteUser;

    @Column(name="is_obsolete", nullable = false)
    private Boolean isObsolete;

    @Column(name="comments", length = 500, nullable = true)
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public User getUpdaterUser() {
        return updaterUser;
    }

    public void setUpdaterUser(User updaterUser) {
        this.updaterUser = updaterUser;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public User getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(User deleteUser) {
        this.deleteUser = deleteUser;
    }
}

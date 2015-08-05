package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "channeling_drop_info")
public class ChannelingDropInfo {

    @Id
    @GeneratedValue
    @Column(name = "id_channeling_drop_info", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_channeling", nullable = false)
    private Channeling channeling;

    @Column(name = "creator_comments", length = 100, nullable = false)
    private String creatorComments;

    @Column(name="creation_date", nullable = false)
    private Calendar creationDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creator_user", nullable = false)
    private User creatorUser;

    @Column(name = "authorizer_comments", length = 100, nullable = true)
    private String authorizerComments;

    @Column(name="authorization_reject_date", nullable = true)
    private Calendar authRejDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_authorizer_rejecter_user", nullable = true)
    private User authorizerRejecterUser;

    @Column(name="is_authorized", nullable = true)
    private Boolean isAuthorized;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Channeling getChanneling() {
        return channeling;
    }

    public void setChanneling(Channeling channeling) {
        this.channeling = channeling;
    }

    public String getCreatorComments() {
        return creatorComments;
    }

    public void setCreatorComments(String creatorComments) {
        this.creatorComments = creatorComments;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getAuthorizerComments() {
        return authorizerComments;
    }

    public void setAuthorizerComments(String authorizerComments) {
        this.authorizerComments = authorizerComments;
    }

    public Calendar getAuthRejDate() {
        return authRejDate;
    }

    public void setAuthRejDate(Calendar authRejDate) {
        this.authRejDate = authRejDate;
    }

    public User getAuthorizerRejecterUser() {
        return authorizerRejecterUser;
    }

    public void setAuthorizerRejecterUser(User authorizerRejecterUser) {
        this.authorizerRejecterUser = authorizerRejecterUser;
    }

    public Boolean getIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(Boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }
}



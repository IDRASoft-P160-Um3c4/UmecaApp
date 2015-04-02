package com.umeca.model.entities.humanReources;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.minutes.Agreement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_agreement")
public class RequestAgreement {

    @Id
    @GeneratedValue
    @Column(name = "id_request_agreement")
    private Long id;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "request_comment")
    private String requestComment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_request_user")
    private User requestUser;

    @Column(name = "response_type")
    private String responseType;

    @Column(name = "response_date")
    private Date responseDate;

    @Column(name = "response_comment")
    private String responseComment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_response_user")
    private User responseUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agreement")
    private Agreement agreement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestComment) {
        this.requestComment = requestComment;
    }

    public User getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(User requestUser) {
        this.requestUser = requestUser;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getResponseComment() {
        return responseComment;
    }

    public void setResponseComment(String responseComment) {
        this.responseComment = responseComment;
    }

    public User getResponseUser() {
        return responseUser;
    }

    public void setResponseUser(User responseUser) {
        this.responseUser = responseUser;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
}

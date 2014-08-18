package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.dto.RequestType;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.shared.Message;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 15/08/14
 * Time: 06:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="case_request")
public class CaseRequest {

    @Id
    @GeneratedValue
    @Column(name="id_request")
    private Long id;

    @Column(name="state_before", length = 100)
    private String stateBefore;

    @ManyToOne
    @JoinColumn(name="id_request_type", nullable = false)
    private RequestType requestType;

    @Column(name="text", nullable = false, length = 1000)
    private String text;

    @ManyToOne
    @JoinColumn(name="id_receiver", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name="id_sender", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name="id_case_detention", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_response_message")
    private Message responseMessage;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_request_message")
    private Message requestMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateBefore() {
        return stateBefore;
    }

    public void setStateBefore(String stateBefore) {
        this.stateBefore = stateBefore;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public Message getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(Message responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Message getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(Message requestMessage) {
        this.requestMessage = requestMessage;
    }
}

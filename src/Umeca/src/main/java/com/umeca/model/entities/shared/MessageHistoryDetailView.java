package com.umeca.model.entities.shared;

import com.umeca.model.shared.EntityGrid;

/**
 * Created by dcortesr on 26/09/14.
 */
public class MessageHistoryDetailView implements EntityGrid {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    public String sender;
    public String requestType;
    public String requestMessage;
    public String responseType;
    public String response;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String responseMessage;

    public MessageHistoryDetailView(Long id, String sender, String requestType, String requestMessage, String responseType, String responseMessage, String response){
        this.id = id;
        this.sender = sender;
        this.requestType = requestType;
        this.requestMessage = requestMessage;
        this.responseType = responseType;
        this.responseMessage = responseMessage;
        this.response = response;

    }
}

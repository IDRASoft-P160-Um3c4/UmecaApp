package com.umeca.model.entities.shared;

import com.umeca.model.shared.EntityGrid;

/**
 * Created by dcortesr on 17/09/14.
 */
public class MessageHistoryView implements EntityGrid {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    private String idFolder;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    private String requestType;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    private String responseType;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    private String sender;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public MessageHistoryView(Long id, String idFolder, String requestType, String responseType, String sender, String message){
        this.id = id;
        this.idFolder = idFolder;
        this.requestType = requestType;
        this.responseType = responseType;
        this.sender = sender;
        this.message = message;
    }
}

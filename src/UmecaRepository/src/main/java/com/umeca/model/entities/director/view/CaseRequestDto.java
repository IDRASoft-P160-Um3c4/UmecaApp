package com.umeca.model.entities.director.view;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 26/08/14
 * Time: 02:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class CaseRequestDto {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String status;
    String typeRequest;
    String messageRequest;
    String messageResponse;
    Date dateRequest;
    Date dateResponse;
    String dateRequestString;
    String dateResponseString;
    String userRequest;
    String userResponse;
    String response;

    public CaseRequestDto() {
    }

    public CaseRequestDto(String status,
                          String typeRequest,
                          String messageRequest,
                          String messageResponse,
                          Date dateRequest,
                          Date dateResponse,
                          String userRequest,
                          String userResponse, String response) {
        this.status = status;
        this.typeRequest = typeRequest;
        this.messageRequest = messageRequest;
        this.messageResponse = messageResponse;
        this.dateRequest = dateRequest;
        this.dateRequestString =  dateFormat.format(this.dateRequest);
        this.dateResponse = dateResponse;
        if(this.dateResponse!=null){
            this.dateResponseString =  dateFormat.format(this.dateResponse);
        }
        this.userRequest = userRequest;
        this.userResponse = userResponse;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getMessageRequest() {
        return messageRequest;
    }

    public void setMessageRequest(String messageRequest) {
        this.messageRequest = messageRequest;
    }

    public String getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Date getDateResponse() {
        return dateResponse;
    }

    public void setDateResponse(Date dateResponse) {
        this.dateResponse = dateResponse;
    }

    public String getDateRequestString() {
        return dateRequestString;
    }

    public void setDateRequestString(String dateRequestString) {
        this.dateRequestString = dateRequestString;
    }

    public String getDateResponseString() {
        return dateResponseString;
    }

    public void setDateResponseString(String dateResponseString) {
        this.dateResponseString = dateResponseString;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }

    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

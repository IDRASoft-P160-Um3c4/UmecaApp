package com.umeca.model.entities.reviewer.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 18/08/14
 * Time: 05:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestDto {
    private Long caseId;
    private String requestType;
    private String reason;
    private String sourcesId;
    private String password;
    private String response;
    private Long idRequest;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSourcesId() {
        return sourcesId;
    }

    public void setSourcesId(String sourcesId) {
        this.sourcesId = sourcesId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }
}

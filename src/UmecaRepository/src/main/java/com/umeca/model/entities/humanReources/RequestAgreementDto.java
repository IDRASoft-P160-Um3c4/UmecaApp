package com.umeca.model.entities.humanReources;

import com.umeca.model.shared.Constants;

import java.text.SimpleDateFormat;

public class RequestAgreementDto {

    private Long id;
    private Boolean authorize;
    private String comments;
    private String password;

    private String requestUser;
    private String requestDate;
    private String requestType;
    private String requestComment;
    private String status;

    private String responseUser;
    private String responseDate;
    private String responseType;
    private String responseComment;

    public RequestAgreementDto() {
    }

    public RequestAgreementDto(RequestAgreement requestAgreement, Boolean hasResponse) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.requestUser = requestAgreement.getRequestUser().getFullname();
        this.requestDate = sdf.format(requestAgreement.getRequestDate());
        if (requestAgreement.getRequestType().equals(Constants.REQUEST_AGREEMENT_TYPE_FINISH)) {
            this.requestType = "Concluir acuerdo";
        }
        this.requestComment = requestAgreement.getRequestComment();
        if (requestAgreement.getAgreement().getIsDone() == true)
            this.status = Constants.AGREEMENT_IS_DONE;
        else
            this.status = Constants.AGREEMENT_IS_NOT_DONE;

        if (hasResponse != null && hasResponse == true) {
            this.responseUser = requestAgreement.getResponseUser().getFullname();
            this.responseDate = sdf.format(requestAgreement.getResponseDate());
            if (requestAgreement.getResponseType().equals(Constants.RESPONSE_AGREEMENT_TYPE_FINISH_REJECT)) {
                this.responseType = "Rechazado";
            } else if (requestAgreement.getResponseType().equals(Constants.RESPONSE_AGREEMENT_TYPE_FINISH_AUTH)) {
                this.responseType = "Autorizado";
            }
            this.responseComment = requestAgreement.getResponseComment();
        }
    }

    public RequestAgreementDto(RequestMinute requestAgreement, Boolean hasResponse) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.requestUser = requestAgreement.getRequestUser().getFullname();
        this.requestDate = sdf.format(requestAgreement.getRequestDate());
        if (requestAgreement.getRequestType().equals(Constants.REQUEST_MINUTE_TYPE_FINISH)) {
            this.requestType = "Concluir minuta";
        }
        this.requestComment = requestAgreement.getRequestComment();

        if (hasResponse != null && hasResponse == true) {
            this.responseUser = requestAgreement.getResponseUser().getFullname();
            this.responseDate = sdf.format(requestAgreement.getResponseDate());
            if (requestAgreement.getResponseType().equals(Constants.RESPONSE_MINUTE_TYPE_FINISH_REJECT)) {
                this.responseType = "Rechazado";
            } else if (requestAgreement.getResponseType().equals(Constants.RESPONSE_MINUTE_TYPE_FINISH_AUTH)) {
                this.responseType = "Autorizado";
            }
            this.responseComment = requestAgreement.getResponseComment();
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAuthorize() {
        return authorize;
    }

    public void setAuthorize(Boolean authorize) {
        this.authorize = authorize;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestComment) {
        this.requestComment = requestComment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseUser() {
        return responseUser;
    }

    public void setResponseUser(String responseUser) {
        this.responseUser = responseUser;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseComment() {
        return responseComment;
    }

    public void setResponseComment(String responseComment) {
        this.responseComment = responseComment;
    }
}

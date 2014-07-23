package com.umeca.model.entities.supervisorManager;

public class AuthorizeRejectMonPlan {
    private Long caseId;
    private Long monPlanId;
    private Integer authorized;
    private String comments;
    private String password;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getMonPlanId() {
        return monPlanId;
    }

    public void setMonPlanId(Long monPlanId) {
        this.monPlanId = monPlanId;
    }

    public Integer getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Integer authorized) {
        this.authorized = authorized;
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
}

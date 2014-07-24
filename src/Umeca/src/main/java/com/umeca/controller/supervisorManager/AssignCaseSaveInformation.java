package com.umeca.controller.supervisorManager;

/**
 * Created by dcortesr on 24/07/14.
 */
public class AssignCaseSaveInformation {
    private Long caseId;

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    private Long supervisorId;
    private String comments;
}
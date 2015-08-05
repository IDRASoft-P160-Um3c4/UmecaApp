package com.umeca.model.entities.supervisor;

public class ChannelingDropModel {

    private Long channelingId;
    private Long caseId;

    private Long channelingDropTypeId;
    private String comments;

    public ChannelingDropModel() {
    }

    public Long getChannelingId() {
        return channelingId;
    }

    public void setChannelingId(Long channelingId) {
        this.channelingId = channelingId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getChannelingDropTypeId() {
        return channelingDropTypeId;
    }

    public void setChannelingDropTypeId(Long channelingDropTypeId) {
        this.channelingDropTypeId = channelingDropTypeId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}



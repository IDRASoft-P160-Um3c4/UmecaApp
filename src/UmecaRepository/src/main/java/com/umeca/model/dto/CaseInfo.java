package com.umeca.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CaseInfo {

    private Long caseId;
    private String mpId;
    private String folderId;
    private String personName;
    private String status;
    private String stringNotProsecute;
    private Date dateNotProsecute;

    public CaseInfo(Long caseId, String mpId, String folderId, String firstName, String lastNameP, String lastNameM, String status) {
        this.caseId = caseId;
        this.mpId = mpId;
        this.folderId = folderId;
        this.personName = firstName + " " + lastNameP + " " + lastNameM;
        this.status = status;
    }

    public CaseInfo(Long caseId, String mpId, String folderId, String firstName, String lastNameP, String lastNameM, String status,Date dateNotProsecute) {
        this.caseId = caseId;
        this.mpId = mpId;
        this.folderId = folderId;
        this.personName = firstName + " " + lastNameP + " " + lastNameM;
        this.status = status;
        this.dateNotProsecute = dateNotProsecute;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        stringNotProsecute = sdf.format(dateNotProsecute);
    }



    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

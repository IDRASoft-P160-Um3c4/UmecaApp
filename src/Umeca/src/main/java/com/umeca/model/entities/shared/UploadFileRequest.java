package com.umeca.model.entities.shared;

public class UploadFileRequest {

    private Long caseId;
    private String description;
    private Long typeId;
    private Boolean closeUploadFile;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Boolean getCloseUploadFile() {
        return closeUploadFile;
    }

    public void setCloseUploadFile(Boolean closeUploadFile) {
        this.closeUploadFile = closeUploadFile;
    }
}

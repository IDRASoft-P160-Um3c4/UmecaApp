package com.umeca.model.dto;

public class CaseInfo {

    private Long idCase;
    private String idMp;
    private String idFolder;
    private String personName;
    private String status;

    public CaseInfo(Long idCase, String idMp, String idFolder, String firstName, String lastNameP, String lastNameM, String status) {
        this.idCase = idCase;
        this.idMp = idMp;
        this.idFolder = idFolder;
        this.personName = firstName + " " + lastNameP + " " + lastNameM;
        this.status = status;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getIdMp() {
        return idMp;
    }

    public void setIdMp(String idMp) {
        this.idMp = idMp;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
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

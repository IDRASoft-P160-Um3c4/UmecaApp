package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.EntityGrid;

public class ForCasesHFGrid implements EntityGrid {

    private Long id;
    private String status;
    private String statusDesc;
    private String idFolder;
    private String idMP;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;

    private StringBuilder sb;


    public ForCasesHFGrid(Long id, String status, String idMP, String name, String lastNameP, String lastNameM) {
        this.id = id;
        this.status = status;
        this.idMP = idMP;
        this.name = name;
        this.lastNameM = lastNameM;
        this.lastNameP = lastNameP;

        sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" ");
        sb.append(this.lastNameP);
        sb.append(" ");
        sb.append(this.lastNameM);
        this.fullName = sb.toString();
    }

    public ForCasesHFGrid(Long id, String status, String statusDesc, String idFolder, String idMP, String name, String lastNameP, String lastNameM) {
        this(id, status, idMP, name, lastNameP, lastNameM);
        this.statusDesc = statusDesc;
        this.idFolder = idFolder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
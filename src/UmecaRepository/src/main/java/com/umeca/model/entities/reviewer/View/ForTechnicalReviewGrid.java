package com.umeca.model.entities.reviewer.View;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created by Vmware on 22/05/2014.
 */
public class ForTechnicalReviewGrid implements EntityGrid {

    private Long id;
    private String status;
    private String idFolder;
    private String idMP;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;
    private String statusName;

    public ForTechnicalReviewGrid(Long id, String status, String idFolder, String idMP, String name, String lastNameP, String lastNameM, String statusName) {
        this.id = id;
        this.status = status;
        this.idFolder = idFolder;
        this.idMP = idMP;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.fullName = name + " " + lastNameP + " " + lastNameM;
        this.statusName = statusName;
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.EntityGrid;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 4:36 PM
 */
public class MonitoringPlanView implements EntityGrid{

    private Long id;
    private String idFolder;
    private String idMP;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;
    private Date creationTime;
    private String stCreationTime;
    private String status;
    private String supervisor;


    public MonitoringPlanView(Long id, String idFolder, String idMP, String name, String lastNameP, String lastNameM, Date creationTime, String status, String supervisor) {
        this.id = id;
        this.idFolder = idFolder;
        this.idMP = idMP;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.fullName = name + " " + lastNameP + " " + lastNameM;
        this.creationTime = creationTime;
        this.status = status;
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getStCreationTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(creationTime);
    }

    public void setStCreationTime(String stCreationTime) {
        this.stCreationTime = stCreationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}

package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.EntityGrid;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 4:36 PM
 */
public class MonitoringPlanView implements EntityGrid{

    private Long id;
    private Long caseId;
    private String idMP;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;
    private Calendar creationTime;
    private String stCreationTime;
    private Calendar generationTime;
    private String stGenerationTime;
    private Calendar authorizationTime;
    private String stAuthorizationTime;
    private String status;
    private String supervisor;
    private String statusLog;


    public MonitoringPlanView(Long id, Long caseId, String idMP, String name, String lastNameP, String lastNameM, Calendar creationTime,
                              Calendar generationTime, Calendar authorizationTime, String status, String supervisor) {
        this.id = id;
        this.caseId = caseId;
        this.idMP = idMP;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.fullName = name + " " + lastNameP + " " + lastNameM;
        this.creationTime = creationTime;
        this.generationTime = generationTime;
        this.authorizationTime = authorizationTime;
        this.status = status;
        this.supervisor = supervisor;
    }

    public MonitoringPlanView(Long id, Long caseId, String idMP, String name, String lastNameP, String lastNameM, Calendar creationTime,
                              Calendar generationTime, Calendar authorizationTime, String status, String supervisor, String statusLog) {
        this(id, caseId, idMP, name, lastNameP, lastNameM, creationTime, generationTime, authorizationTime, status, supervisor);
        this.statusLog = statusLog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
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

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public String getStCreationTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String sValue = sdf.format(creationTime.getTime());
        return sValue;
    }

    public void setStCreationTime(String stCreationTime) {
        this.stCreationTime = stCreationTime;
    }

    public Calendar getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(Calendar generationTime) {
        this.generationTime = generationTime;
    }

    public String getStGenerationTime() {
        if(generationTime == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(generationTime.getTime());
    }

    public void setStGenerationTime(String stGenerationTime) {
        this.stGenerationTime = stGenerationTime;
    }

    public Calendar getAuthorizationTime() {
        return authorizationTime;
    }

    public void setAuthorizationTime(Calendar authorizationTime) {
        this.authorizationTime = authorizationTime;
    }

    public String getStAuthorizationTime() {
        if(authorizationTime == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(authorizationTime.getTime());
    }

    public void setStAuthorizationTime(String stAuthorizationTime) {
        this.stAuthorizationTime = stAuthorizationTime;
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

    public String getStatusLog() {
        return statusLog;
    }

    public void setStatusLog(String statusLog) {
        this.statusLog = statusLog;
    }
}

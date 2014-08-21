package com.umeca.model.entities.managereval;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.EntityGrid;

/**
 * Created by dcortesr on 30/06/14.
 */
public class CaseEvaluationView implements EntityGrid {
    private Long id;
    private String idFolder;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String statusMeeting;
    private String statusVerification;
    private Long idTec;
    private String fullname;
    private String statusString;
    private Integer status;

    public CaseEvaluationView(Long id, String idFolder, String name, String lastNameP, String lastNameM, String statusMeeting, String statusVerification, Long idTec) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.statusMeeting = statusMeeting;
        this.statusVerification = statusVerification;
        this.idTec = idTec;
        this.fullname = this.name+" "+this.lastNameP+" "+this.lastNameM;
        status = 0;
        if(statusMeeting.equals(Constants.S_MEETING_INCOMPLETE)){
            statusString ="Entrevista de riesgos procesales incompleta";
        }else {
            if(statusMeeting.equals(Constants.S_MEETING_INCOMPLETE_LEGAL)){
                status++;
                statusString = "Por agregar informaci&oacute;n legal";
            }else if(statusMeeting.equals(Constants.S_MEETING_COMPLETE)){
                status+=2;
                statusString = "Entrevista completa";
            }
            if(statusVerification!=null && statusVerification.equals(Constants.VERIFICATION_STATUS_COMPLETE)){
                statusString = "Verificaci&oacute;n  terminada";
                status++;
            }
            if(idTec!=null){
                statusString = "Opini&oacute;n t&eacute;cnica terminada";
                status++;
            }
        }

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

    public String getStatusMeeting() {
        return statusMeeting;
    }

    public void setStatusMeeting(String statusMeeting) {
        this.statusMeeting = statusMeeting;
    }

    public String getStatusVerification() {
        return statusVerification;
    }

    public void setStatusVerification(String statusVerification) {
        this.statusVerification = statusVerification;
    }

    public Long getIdTec() {
        return idTec;
    }

    public void setIdTec(Long idTec) {
        this.idTec = idTec;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
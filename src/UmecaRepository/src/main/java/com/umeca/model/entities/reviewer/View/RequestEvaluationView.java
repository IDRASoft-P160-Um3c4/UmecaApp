package com.umeca.model.entities.reviewer.View;

import com.umeca.model.shared.Constants;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created by dcortesr on 30/06/14.
 */
public class RequestEvaluationView implements EntityGrid {
    private Long id;
    private String idFolder;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String statusMeeting;
    private String statusVerification;
    private String statusCase;
    private String fullName;
    private String statusString;
    private String status;
    private String description;

    public RequestEvaluationView() {
    }

    public RequestEvaluationView(Long id, String idFolder, String name, String lastNameP, String lastNameM) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.fullName = name + " " +lastNameP + " " + lastNameM;
    }

    /*codigos de evaluacion
             A= edit meeting
             B=edit legal information
             C=change status source
             D= edit technical review
             E = delete meeting(obsolete)
             G = case not prosecute
            */
    public RequestEvaluationView(Long id, String idFolder, String name, String lastNameP, String lastNameM, String statusMeeting, String statusVerification, String statusCase) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.statusMeeting = statusMeeting;
        this.statusVerification = statusVerification;
        this.fullName = this.name+" "+this.lastNameP+" "+this.lastNameM;
        this.statusCase = statusCase;
        status= "";
        if(!this.statusCase.equals(Constants.CASE_STATUS_REQUEST)){
            status =".G.";
        }
        if(this.statusCase.equals(Constants.CASE_STATUS_MEETING)){
            if(this.statusMeeting.equals(Constants.S_MEETING_INCOMPLETE_LEGAL)){
                description="Por agregar informaci&oacute;n legal";
                status+= ".A.";
            }else if(this.statusMeeting.equals(Constants.S_MEETING_INCOMPLETE)){
                status += ".E.";
                description="Entrevista de riesgos procesales incompleta";
            }
        }else if(this.statusCase.equals(Constants.CASE_STATUS_SOURCE_VALIDATION)){
            description="Autorizaci&oacute; de fuentes pendiente";
            status+=".B.";
        }
        else if(this.statusCase.equals(Constants.CASE_STATUS_VERIFICATION)) {
            if(this.statusVerification.equals(Constants.VERIFICATION_STATUS_NEW_SOURCE)){
                description="Autorizaci&oacute; de fuentes pendiente";
                status+=".B.";
            }
            if(this.statusVerification.equals(Constants.VERIFICATION_STATUS_AUTHORIZED)){
                description="En verificaci&oacute;n";
                status +=".C.";
            }
        }else if(this.statusCase.equals(Constants.CASE_STATUS_TECHNICAL_REVIEW)){
            description="Con opini&oacute;n t&eacute;cnica generada";
            status +=".D.";
        }
        if(this.statusCase.equals(Constants.CASE_STATUS_REQUEST)){
            description="Pendiente por autorizaci&oacute;n";
            status +=".F.";

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

    public String getStatusCase() {
        return statusCase;
    }

    public void setStatusCase(String statusCase) {
        this.statusCase = statusCase;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
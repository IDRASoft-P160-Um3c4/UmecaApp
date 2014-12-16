package com.umeca.model.entities.managereval;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.EntityGrid;
import com.umeca.model.shared.HearingFormatConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dcortesr on 30/06/14.
 */
public class CaseEvaluationView implements EntityGrid {
    private Long id;
    private Long idVerif;
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
    private String userName;
    private String reviewer;
    private String date;

    private Long idHF;
    private Long idFM;
    private Long idMonP;
    private Boolean fmTerminated;

    private String resolutionStr;


    public CaseEvaluationView(Long id, Long idVerif, String idFolder, String name, String lastNameP, String lastNameM, String statusMeeting, String statusVerification, Long idTec, String userName) {
        this.id = id;
        this.idVerif = idVerif;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.statusMeeting = statusMeeting;
        this.statusVerification = statusVerification;
        this.idTec = idTec;
        this.fullname = this.name + " " + this.lastNameP + " " + this.lastNameM;
        this.userName = userName;
        status = 0;
        if (statusMeeting.equals(Constants.S_MEETING_INCOMPLETE)) {
            statusString = "Entrevista de riesgos procesales incompleta";
        } else {
            if (statusMeeting.equals(Constants.S_MEETING_INCOMPLETE_LEGAL)) {
                status++;
                statusString = "Por agregar informaci&oacute;n legal";
            } else if (statusMeeting.equals(Constants.S_MEETING_COMPLETE)) {
                status += 2;
                statusString = "Entrevista completa";
            }
            if (statusVerification != null && statusVerification.equals(Constants.VERIFICATION_STATUS_COMPLETE)) {
                statusString = "Verificaci&oacute;n  terminada";
                status++;
            }
            if (idTec != null) {
                statusString = "Opini&oacute;n t&eacute;cnica terminada";
                status++;
            }
        }
    }



    public CaseEvaluationView(Long id, String idFolder, String name, String lastNameP, String lastNameM, Long idFM, Long idHF, Long idMonP, Long idTec, Boolean fmTerminated, String userName) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.idFM = idFM;
        this.idHF = idHF;
        this.idMonP = idMonP;
        this.fullname = this.name + " " + this.lastNameP + " " + this.lastNameM;
        this.idTec = idTec;
        this.fmTerminated = fmTerminated;
        this.userName = userName;
    }

    public CaseEvaluationView(Long id, String idFolder, String name, String lastNameP, String lastNameM, Long idFM, Long idHF, Long idMonP, Long idTec, Boolean fmTerminated, String userName, Integer resolution) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.idFM = idFM;
        this.idHF = idHF;
        this.idMonP = idMonP;
        this.fullname = this.name + " " + this.lastNameP + " " + this.lastNameM;
        this.idTec = idTec;
        this.fmTerminated = fmTerminated;
        this.userName = userName;
        if (resolution != null && resolution == HearingFormatConstants.HEARING_TYPE_MC)
            resolutionStr = "MC";
        else if (resolution != null && resolution == HearingFormatConstants.HEARING_TYPE_SCP)
            resolutionStr = "SCPP";
    }

    public CaseEvaluationView(Long id, String idFolder, String name, String lastNameP, String lastNameM, String reviewer, Date date) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.fullname = this.name+" "+this.lastNameP+" "+this.lastNameM;
        this.reviewer = reviewer;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date = formatter.format(date);
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

    public Long getIdVerif() {
        return idVerif;
    }

    public void setIdVerif(Long idVerif) {
        this.idVerif = idVerif;
    }

    public Long getIdHF() {
        return idHF;
    }

    public void setIdHF(Long idHF) {
        this.idHF = idHF;
    }

    public Long getIdFM() {
        return idFM;
    }

    public void setIdFM(Long idFM) {
        this.idFM = idFM;
    }

    public Long getIdMonP() {
        return idMonP;
    }

    public void setIdMonP(Long idMonP) {
        this.idMonP = idMonP;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getFmTerminated() {
        return fmTerminated;
    }

    public void setFmTerminated(Boolean fmTerminated) {
        this.fmTerminated = fmTerminated;
    }

    public String getResolutionStr() {
        return resolutionStr;
    }

    public void setResolutionStr(String resolutionStr) {
        this.resolutionStr = resolutionStr;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
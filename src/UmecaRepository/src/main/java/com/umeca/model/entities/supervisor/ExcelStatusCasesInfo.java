package com.umeca.model.entities.supervisor;

import java.util.Date;

public class ExcelStatusCasesInfo {

    private Long idCase;
    private Long idMeeting;
    private Date dateMeetingFinished;
    private Long idVerification;
    private Date dateVerificationFinished;
    private Long idTecRev;
    private Boolean tecRevFinished;

    private Long idFramingMeeting;
    private Boolean framingMeetingFinished;
    private Long idMonitoringPlan;
    private Long idFirstFormatFinished;

    public ExcelStatusCasesInfo(Long idCase, Long idMeeting, Date dateMeetingFinished, Long idVerification, Date dateVerificationFinished, Long idTecRev, Boolean tecRevIsFinished, Long idFramingMeeting, Boolean framingMeetingFinished, Long idMonitoringPlan) {
        this.idCase = idCase;
        this.idMeeting = idMeeting;
        this.dateMeetingFinished = dateMeetingFinished;
        this.idVerification = idVerification;
        this.dateVerificationFinished = dateVerificationFinished;
        this.idTecRev = idTecRev;
        this.tecRevFinished = tecRevIsFinished;
        this.idFramingMeeting = idFramingMeeting;
        this.framingMeetingFinished = framingMeetingFinished;
        this.idMonitoringPlan = idMonitoringPlan;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public Long getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(Long idMeeting) {
        this.idMeeting = idMeeting;
    }

    public Date getDateMeetingFinished() {
        return dateMeetingFinished;
    }

    public void setDateMeetingFinished(Date dateMeetingFinished) {
        this.dateMeetingFinished = dateMeetingFinished;
    }

    public Long getIdVerification() {
        return idVerification;
    }

    public void setIdVerification(Long idVerification) {
        this.idVerification = idVerification;
    }

    public Date getDateVerificationFinished() {
        return dateVerificationFinished;
    }

    public void setDateVerificationFinished(Date dateVerificationFinished) {
        this.dateVerificationFinished = dateVerificationFinished;
    }

    public Long getIdTecRev() {
        return idTecRev;
    }

    public void setIdTecRev(Long idTecRev) {
        this.idTecRev = idTecRev;
    }

    public Boolean getTecRevFinished() {
        return tecRevFinished;
    }

    public void setTecRevFinished(Boolean tecRevFinished) {
        this.tecRevFinished = tecRevFinished;
    }

    public Long getIdFramingMeeting() {
        return idFramingMeeting;
    }

    public void setIdFramingMeeting(Long idFramingMeeting) {
        this.idFramingMeeting = idFramingMeeting;
    }

    public Boolean getFramingMeetingFinished() {
        return framingMeetingFinished;
    }

    public void setFramingMeetingFinished(Boolean framingMeetingFinished) {
        this.framingMeetingFinished = framingMeetingFinished;
    }

    public Long getIdMonitoringPlan() {
        return idMonitoringPlan;
    }

    public void setIdMonitoringPlan(Long idMonitoringPlan) {
        this.idMonitoringPlan = idMonitoringPlan;
    }

    public Long getIdFirstFormatFinished() {
        return idFirstFormatFinished;
    }

    public void setIdFirstFormatFinished(Long idFirstFormatFinished) {
        this.idFirstFormatFinished = idFirstFormatFinished;
    }
}

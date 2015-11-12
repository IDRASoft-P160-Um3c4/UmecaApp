package com.umeca.model.entities.supervisor;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.HearingFormatConstants;

import java.util.Calendar;

public class ChannelingTrackView implements EntityGrid {

    private Long id;
    private String idMP;
    private String imputed;
    private String channelingName;
    private String channelingTypeName;
    private String absenceDate;
    private String resolutionTxt;
    private Boolean isJustified;
    private Long rescheduleAppointmentId;

    public ChannelingTrackView(Long id, String idMP, Integer resolution,  String name, String lastNameP, String lastNameM, String channelingName,
                               String channelingTypeName, Calendar start, Calendar end, Boolean isJustified, Long rescheduleAppointmentId) {
        this.id = id;
        this.idMP = idMP;
        this.resolutionTxt = resolution.equals(HearingFormatConstants.HEARING_TYPE_SCP) ? HearingFormatConstants.HEARING_TYPE_SCP_TXT : HearingFormatConstants.HEARING_TYPE_MC_TXT;
        this.imputed = name + " " + lastNameP + " " + lastNameM;
        this.channelingName = channelingName;
        this.channelingTypeName = channelingTypeName;
        this.absenceDate = CalendarExt.calendarToDateString(start) + " al " + CalendarExt.calendarToDateString(end);
        this.isJustified = isJustified;
        this.rescheduleAppointmentId = rescheduleAppointmentId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }

    public String getChannelingName() {
        return channelingName;
    }

    public void setChannelingName(String channelingName) {
        this.channelingName = channelingName;
    }

    public String getChannelingTypeName() {
        return channelingTypeName;
    }

    public void setChannelingTypeName(String channelingTypeName) {
        this.channelingTypeName = channelingTypeName;
    }

    public String getAbsenceDate() {
        return absenceDate;
    }

    public void setAbsenceDate(String absenceDate) {
        this.absenceDate = absenceDate;
    }

    public Boolean getIsJustified() {
        return isJustified;
    }

    public void setIsJustified(Boolean isJustified) {
        this.isJustified = isJustified;
    }

    public String getResolutionTxt() {
        return resolutionTxt;
    }

    public void setResolutionTxt(String resolutionTxt) {
        this.resolutionTxt = resolutionTxt;
    }

    public Long getRescheduleAppointmentId() {
        return rescheduleAppointmentId;
    }

    public void setRescheduleAppointmentId(Long rescheduleAppointmentId) {
        this.rescheduleAppointmentId = rescheduleAppointmentId;
    }
}

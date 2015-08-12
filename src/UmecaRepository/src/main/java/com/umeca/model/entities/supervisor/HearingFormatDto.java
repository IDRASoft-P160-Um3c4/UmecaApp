package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.entities.account.User;
import com.umeca.model.shared.HearingFormatConstants;
import com.umeca.model.shared.SelectList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HearingFormatDto {
    private String room;
    private Date appointmentDate;
    private String date;
    private String time;
    private String mpName;
    private String hearingType;
    private Integer imputedPresence;
    private String imputedPres;
    private Integer ip;
    private String vincProcess;
    private Integer linkageProcces;
    private Boolean nationalArrangement;
    private String arrangement;
    private Integer arrangementTypeInt;
    private String arrangementType;

    public String toString(String crimes, List<SelectList> arrangementAssigned, User umecaSupervisor) {
        String result = "";
        if (room != null)
            result += "<strong>Distrito judicial: </strong>" + StringEscape.escapeText(room) + "<br/>";
        if (appointmentDate != null) {
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            date = format1.format(appointmentDate);
            result += "<strong>Fecha y hora de audiencia: </strong>" + date + "<br/>";
        }
        if (mpName != null)
            result += "<strong>Ministerio p&uacute;blico: </strong>" + StringEscape.escapeText(mpName) + "<br/>";
        if (hearingType != null)
            result += "<strong>Tipo de audiencia: </strong>" + hearingType + "<br/>";
        result += (ip != null && ip.equals(HearingFormatConstants.IMP_FORM_NO) ? "<strong>No</strong> se present&oacute; el imputado<br/>" : "<strong>Si</strong> se present&oacute; el imputado<br/>");
        result += crimes;
        if (linkageProcces != null) {
            result += "<strong>Vinculaci&oacute;n a proceso: </strong>";
            if (linkageProcces.equals(HearingFormatConstants.PROCESS_VINC_NO)) {
                result += "No<br/>";
            } else if (linkageProcces.equals(HearingFormatConstants.PROCESS_VINC_YES)) {
                result += "Si<br/>";
            } else {
                result += "Sin registro<br/>";
            }
        }
        if (arrangementTypeInt != null && nationalArrangement != null) {
            result += "<strong>Audiencia:</strong> ";
            if (arrangementTypeInt.equals(HearingFormatConstants.HEARING_TYPE_SCP)) {
                result += "SCP ";
            } else {
                result += "MC ";
            }
            if (nationalArrangement.equals(HearingFormatConstants.ID_PRISON_ARRANGEMENT_LOC)) {
                result += "- Local <br/>";
            } else {
                result += "- Nacional <br/>";
            }

        }
        if (arrangementAssigned != null && arrangementAssigned.size() > 0) {
            result += "<ul>";
            for (SelectList aux : arrangementAssigned) {
                result += "<li>" + aux.getName() + "/" + StringEscape.escapeText(aux.getDescription()) + "</li>";
            }
            result += "</ul>";
        }

        if (umecaSupervisor != null) {
            result += "<strong>Supervisor preasignado:  </strong>";
            result += umecaSupervisor.getFullname();
        }

        return result;
    }

    public HearingFormatDto() {
    }

    public HearingFormatDto(//String room,
                            Date appointmentDate, String mpName, Integer ip, String hearingType, Integer arrangementTypeInt, Integer linkageProcces, Boolean nationalArrangement) {
        //this.room = room;
        this.appointmentDate = appointmentDate;
        this.mpName = mpName;
        this.ip = ip;
        this.hearingType = hearingType;
        this.arrangementTypeInt = arrangementTypeInt;
        this.linkageProcces = linkageProcces;
        this.nationalArrangement = nationalArrangement;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMpName() {
        return mpName;
    }

    public void setMpName(String mpName) {
        this.mpName = mpName;
    }

    public String getHearingType() {
        return hearingType;
    }

    public void setHearingType(String hearingType) {
        this.hearingType = hearingType;
    }


    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }

    public String getVincProcess() {
        return vincProcess;
    }

    public void setVincProcess(String vincProcess) {
        this.vincProcess = vincProcess;
    }

    public Integer getLinkageProcces() {
        return linkageProcces;
    }

    public void setLinkageProcces(Integer linkageProcces) {
        this.linkageProcces = linkageProcces;
    }

    public Boolean getNationalArrangement() {
        return nationalArrangement;
    }

    public void setNationalArrangement(Boolean nationalArrangement) {
        this.nationalArrangement = nationalArrangement;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public Integer getArrangementTypeInt() {
        return arrangementTypeInt;
    }

    public void setArrangementTypeInt(Integer arrangementTypeInt) {
        this.arrangementTypeInt = arrangementTypeInt;
    }

    public String getArrangementType() {
        return arrangementType;
    }

    public void setArrangementType(String arrangementType) {
        this.arrangementType = arrangementType;
    }
}

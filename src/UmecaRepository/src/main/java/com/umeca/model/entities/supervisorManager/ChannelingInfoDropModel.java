package com.umeca.model.entities.supervisorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class ChannelingInfoDropModel implements EntityGrid {
    private Long id;
    private String creationDateTx;
    private String channelingName;
    private String channelingTypeName;
    private String idMp;
    private String imputed;
    private String district;
    private String supervisor;


    public ChannelingInfoDropModel(Long id, Calendar creationDate, String channelingName,
                                   String channelingTypeName, String idMp,
                                   String name, String lastNameP, String lastNameM,
                                   String district, String supervisor) {
        this.id = id;
        this.creationDateTx = CalendarExt.calendarToFormatString(creationDate, Constants.FORMAT_CALENDAR_I);
        this.channelingName = channelingName;
        this.channelingTypeName = channelingTypeName;
        this.idMp = idMp;
        this.imputed = name + " " + lastNameP + " " + lastNameM;
        this.district = district;
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreationDateTx() {
        return creationDateTx;
    }

    public void setCreationDateTx(String creationDateTx) {
        this.creationDateTx = creationDateTx;
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

    public String getIdMp() {
        return idMp;
    }

    public void setIdMp(String idMp) {
        this.idMp = idMp;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}

package com.umeca.model.entities.supervisorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.entities.supervisor.Channeling;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class ChannelingInfoDropModel implements EntityGrid {
    private Long id;
    private String commentsCreator;
    private String channelingDropType;
    private String userCreator;
    private String creationDateTx;
    private String channelingName;
    private String channelingTypeName;
    private String idMp;
    private String imputed;
    private String district;
    private String supervisor;
    private String commentsAuthorizer;
    private String password;
    private Boolean authRejValue;

    public ChannelingInfoDropModel(){

    }

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


    public ChannelingInfoDropModel(Long id, Calendar creationDate, String commentsCreator,
                                   String channelingDropType, String userCreator, String idMp,
                                   String name, String lastNameP, String lastNameM,
                                   String channelingName, String district) {
        this.id = id;
        this.commentsCreator = commentsCreator;
        this.channelingDropType = channelingDropType;
        this.userCreator = userCreator;
        this.creationDateTx = CalendarExt.calendarToFormatString(creationDate, Constants.FORMAT_CALENDAR_I);
        this.channelingName = channelingName;
        this.idMp = idMp;
        this.imputed = name + " " + lastNameP + " " + lastNameM;
        this.district = district;
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

    public String getCommentsCreator() {
        return commentsCreator;
    }

    public void setCommentsCreator(String commentsCreator) {
        this.commentsCreator = commentsCreator;
    }

    public String getChannelingDropType() {
        return channelingDropType;
    }

    public void setChannelingDropType(String channelingDropType) {
        this.channelingDropType = channelingDropType;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public String getCommentsAuthorizer() {
        return commentsAuthorizer;
    }

    public void setCommentsAuthorizer(String commentsAuthorizer) {
        this.commentsAuthorizer = commentsAuthorizer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAuthRejValue() {
        return authRejValue;
    }

    public void setAuthRejValue(Boolean authRejValue) {
        this.authRejValue = authRejValue;
    }
}

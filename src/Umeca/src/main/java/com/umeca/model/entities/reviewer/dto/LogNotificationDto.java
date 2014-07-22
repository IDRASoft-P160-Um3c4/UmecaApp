package com.umeca.model.entities.reviewer.dto;

import com.umeca.model.shared.Constants;

import javax.persistence.Transient;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class LogNotificationDto {

    private Long notificationId;
    private String idFolder;
    private String imputedName;
    private String senderUser;
    private String title;
    private String message;
    private Date orderDate;
    private Calendar dateNotif;

    public LogNotificationDto() {
    }

    public LogNotificationDto(Long notificationId, String title, String message, Calendar dateNotif) {
        this.notificationId = notificationId;
        this.title = title;
        this.message = message;
        this.dateNotif=dateNotif;
    }

    public LogNotificationDto(String idFolder, String imputedName, String status, Date orderDate) {

        this.idFolder=idFolder;
        this.imputedName=imputedName;
        this.orderDate = orderDate;

        if(status.equals(Constants.S_MEETING_INCOMPLETE)) {
            title="Entrevista incompleta.";
            message="Debe completar la entrevista de riesgos procesales del caso con carpeta de investigación "+this.idFolder+" para el imputado "+this.imputedName+".";
        }

        if(status.equals(Constants.S_MEETING_INCOMPLETE_LEGAL)) {
            title="Información legal incompleta.";
            message="Debe completar la información legal de la entrevista de riesgos procesales del caso con carpeta de investigación "+this.idFolder+" para el imputado "+this.imputedName+".";
        }

        if(status.equals(Constants.VERIFICATION_STATUS_AUTHORIZED)) {
            title="Verificación auotorizada.";
            message="Se han autorizado las entrevistas a las fuentes para el caso con carpeta de investigación "+this.idFolder+" para el imputado "+this.imputedName+".";
        }


        if(status.equals("NO_SOURCES")) {
            title="Fuentes no disponibles.";
            message="Debe agregar fuentes para realizar la verificación de la entrevista de riesgos procesales del caso con carpeta de investigación "+this.idFolder+" para el imputado "+this.imputedName+".";
        }

        if(status.equals("SOURCES_NO_MEETING")) {
            title="Fuentes disponibles.";
            message="Debe realizar las entrevistas a las fuentes para la entrevista de riesgos procesales del caso con carpeta de investigación "+this.idFolder+" para el imputado "+this.imputedName+".";
        }

    }

    public static final Comparator<LogNotificationDto> dateSorter= new Comparator<LogNotificationDto>() {
        @Override
        public int compare(LogNotificationDto h1, LogNotificationDto h2) {
            return  h1.getOrderDate().compareTo(h2.getOrderDate());
        }
    };

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getImputedName() {
        return imputedName;
    }

    public void setImputedName(String imputedName) {
        this.imputedName = imputedName;
    }

    public String getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(String senderUser) {
        this.senderUser = senderUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Calendar getDateNotif() {
        return dateNotif;
    }

    public void setDateNotif(Calendar dateNotif) {
        this.dateNotif = dateNotif;
    }

    public static Comparator<LogNotificationDto> getDateSorter() {
        return dateSorter;
    }
}
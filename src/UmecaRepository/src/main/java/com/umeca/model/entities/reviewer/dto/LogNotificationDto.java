package com.umeca.model.entities.reviewer.dto;

import com.umeca.infrastructure.security.StringEscape;
import com.umeca.model.shared.Constants;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class LogNotificationDto {

    private Long id;
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
        this.id = notificationId;
        this.title = title;
        this.message = StringEscape.escapeText(message);
        this.dateNotif=dateNotif;
    }

    public LogNotificationDto(String idFolder, String imputedName, String status, Date orderDate) {

        this.idFolder=idFolder;
        this.imputedName=imputedName;
        this.orderDate = orderDate;

        if(status.equals(Constants.S_MEETING_INCOMPLETE)) {
            title="Entrevista incompleta.";
            message="Debe completar la entrevista de riesgos procesales del caso con carpeta de investigaci&oacute;n "+ StringEscape.escapeText(this.idFolder)+" para el imputado "+StringEscape.escapeText(this.imputedName)+".";
        }
        else
        if(status.equals(Constants.S_MEETING_INCOMPLETE_LEGAL)) {
            title="Informaci&oacute;n legal incompleta.";
            message="Debe completar la informaci&oacute;n legal de la entrevista de riesgos procesales del caso con carpeta de investigaci&oacute;n "+StringEscape.escapeText(this.idFolder)+" para el imputado "+StringEscape.escapeText(this.imputedName)+".";
        }
        else
        if(status.equals(Constants.VERIFICATION_STATUS_AUTHORIZED)) {
            title="Autorizaci&oacute;n de fuentes terminada.";
            message="Ha finalizado autorizaci&oacute;n de fuentes para el caso con carpeta de investigaci&oacute;n "+StringEscape.escapeText(this.idFolder)+" para el imputado "+StringEscape.escapeText(this.imputedName)+".";
        }
        else
        if(status.equals(Constants.VERIFICATION_STATUS_NEW_SOURCE)) {
            title="Autorizaci&oacute;n de fuentes.";
            message="Se ha completado el registro de datos legales. Debe autorizar las fuentes para el caso con carpeta de investigaci&oacute;n "+StringEscape.escapeText(this.idFolder)+" para el imputado "+StringEscape.escapeText(this.imputedName)+".";
        }
        else
        if(status.equals("NO_SOURCES")) {
            title="Fuentes no disponibles.";
            message="Debe agregar fuentes para realizar la verificaci&oacute;n de la entrevista de riesgos procesales del caso con carpeta de investigaci&oacute;n "+StringEscape.escapeText(this.idFolder)+" para el imputado "+StringEscape.escapeText(this.imputedName)+".";
        }
        else
        if(status.equals("SOURCES_NO_MEETING")) {
            title="Fuentes disponibles.";
            message="Debe realizar las entrevistas a las fuentes para la entrevista de riesgos procesales del caso con carpeta de investigaci&oacute;n "+StringEscape.escapeText(this.idFolder)+" para el imputado "+StringEscape.escapeText(this.imputedName)+".";
        }

    }

    public static final Comparator<LogNotificationDto> dateSorter= new Comparator<LogNotificationDto>() {
        @Override
        public int compare(LogNotificationDto h1, LogNotificationDto h2) {
            return  h1.getOrderDate().compareTo(h2.getOrderDate());
        }
    };

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
package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.util.Calendar;

public class DocumentDto implements EntityGrid {

    public DocumentDto() {

    }

    public DocumentDto(Long id, Calendar documentDate, String numberDocument, String sender, String receiver, String criminalCause, String subject, String turnedOver, String finalAction) {
        this.id = id;
        this.documentDate = CalendarExt.calendarToFormatString(documentDate, "yyyy/MM/dd");
        this.numberDocument = numberDocument;
        this.sender = sender;
        this.receiver = receiver;
        this.criminalCause = criminalCause;
        this.subject = subject;
        this.turnedOver = turnedOver;
        this.finalAction = finalAction;
    }

    private Long id;
    private String documentDate;
    private String numberDocument;
    private String sender;
    private String receiver;
    private String criminalCause;
    private String subject;
    private String turnedOver;
    private String finalAction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getNumberDocument() {
        return numberDocument;
    }

    public void setNumberDocument(String numberDocument) {
        this.numberDocument = numberDocument;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCriminalCause() {
        return criminalCause;
    }

    public void setCriminalCause(String criminalCause) {
        this.criminalCause = criminalCause;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTurnedOver() {
        return turnedOver;
    }

    public void setTurnedOver(String turnedOver) {
        this.turnedOver = turnedOver;
    }

    public String getFinalAction() {
        return finalAction;
    }

    public void setFinalAction(String finalAction) {
        this.finalAction = finalAction;
    }
}

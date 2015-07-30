package com.umeca.model.entities.humanReources;

import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "id_document", nullable = false)
    private Long id;

    @Column(name = "document_date", nullable = false)
    private Calendar documentDate;

    @Column(name = "number_document", nullable = false)
    private String numberDocument;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "receiver", nullable = false)
    private String receiver;

    @Column(name = "criminal_cause", nullable = false)
    private String criminalCause;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "turned_over", nullable = false)
    private String turnedOver;

    @Column(name = "final_action", nullable = false)
    private String finalAction;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

    @OneToMany
    @JoinTable(name = "rel_document_generic_file",
            joinColumns = {@JoinColumn(name = "id_document", referencedColumnName = "id_document")},
            inverseJoinColumns = {@JoinColumn(name = "id_upload_file_generic", referencedColumnName = "id_upload_file_generic", unique = true)})
    private List<UploadFileGeneric> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Calendar documentDate) {
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

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public List<UploadFileGeneric> getFiles() {
        return files;
    }

    public void setFiles(List<UploadFileGeneric> files) {
        this.files = files;
    }
}

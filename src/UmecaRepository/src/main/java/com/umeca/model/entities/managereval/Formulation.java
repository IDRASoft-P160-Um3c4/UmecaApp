package com.umeca.model.entities.managereval;

import org.apache.taglibs.standard.lang.jstl.Evaluator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by DeveloperII on 07/08/2015.
 */

@Entity
@Table(name="formulation")
public class Formulation {

    @Id
    @GeneratedValue
    @Column(name = "id_formulation")
    private long id;


    @Column(name = "registration_formulation_date", nullable = false)
    private Date registrationFormulationDate;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "certificate_notification", nullable = false)
    private String certificateNotification;


    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "lastname_p", length = 50, nullable = false)
    private String lastNameP;

    @Column(name = "lastname_m", length = 50, nullable = false)
    private String lastNameM;


    @Column(name = "umeca_interview_date", nullable = false)
    private Date umecaInterviewDate;


    @Column(name = "hearing_date", nullable = false)
    private Date hearingDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRegistrationFormulationDate() {
        return registrationFormulationDate;
    }

    public void setRegistrationFormulationDate(Date registrationFormulationDate) {
        this.registrationFormulationDate = registrationFormulationDate;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCertificateNotification() {
        return certificateNotification;
    }

    public void setCertificateNotification(String certificateNotification) {
        this.certificateNotification = certificateNotification;
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

    public Date getUmecaInterviewDate() {
        return umecaInterviewDate;
    }

    public void setUmecaInterviewDate(Date umecaInterviewDate) {
        this.umecaInterviewDate = umecaInterviewDate;
    }

    public Date getHearingDate() {
        return hearingDate;
    }

    public void setHearingDate(Date hearingDate) {
        this.hearingDate = hearingDate;
    }
}

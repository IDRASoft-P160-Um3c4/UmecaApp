package com.umeca.model.entities.managereval;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.entities.account.User;
import org.apache.taglibs.standard.lang.jstl.Evaluator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DeveloperII on 07/08/2015.
 */

@Entity
@Table(name = "formulation")
public class Formulation implements EntityGrid {

    public Formulation(){

    }

    public Formulation(Long id, Date registrationFormulationDate, String document, String certificateNotification, String firstname, String lastNameP, String lastNameM, Date umecaInterviewDate, Date hearingDate, Boolean presence, String fullname, Boolean informationDelivered) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.document = document;
        this.certificateNotification = certificateNotification;
        this.imputedFullname = firstname + " " + lastNameP + " " + lastNameM;
        this.umecaInterviewDateStr = umecaInterviewDate == null ? "" : sdf.format(umecaInterviewDate);
        this.hearingDateStr = hearingDate == null ? "" : sdf.format(hearingDate);
        this.registrationFormulationDateStr = registrationFormulationDate == null ? "" : sdf.format(umecaInterviewDate);
        this.presenceStr = presence == null ? "Pendiente" : presence == true ? "Si" : "No";
        this.attended = umecaInterviewDate.after(new Date());
        this.reviewerFullname = fullname;
        this.informationDelivered = informationDelivered;
        this.informationDeliveredStr = informationDelivered == null ? "" : informationDelivered == true ? "Si" : "";
    }

    @Id
    @GeneratedValue
    @Column(name = "id_formulation")
    private Long id;


    @Column(name = "registration_formulation_date", nullable = false)
    private Date registrationFormulationDate;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "certificate_notification", nullable = false)
    private String certificateNotification;


    @Column(name = "imputed_firstname", length = 50, nullable = false)
    private String firstName;

    @Column(name = "imputed_lastname_p", length = 50, nullable = false)
    private String lastNameP;

    @Column(name = "imputed_lastname_m", length = 50, nullable = false)
    private String lastNameM;

    @Column(name = "umeca_interview_date", nullable = false)
    private Date umecaInterviewDate;

    @Column(name = "hearing_date", nullable = false)
    private Date hearingDate;

    @Column(name = "is_obsolete", nullable = false)
    private Boolean isObsolete;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reviewer", nullable = false)
    private User reviewer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_managereval", nullable = false)
    private User managereval;

    @Column(name = "comments", length = 300, nullable = true)
    private String comments;

    @Column(name = "presence", nullable = true)
    private Boolean presence;

    @Column(name = "information_delivered",nullable = true)
    private Boolean informationDelivered;

    @Transient
    private String registrationFormulationDateStr;
    @Transient
    private String umecaInterviewDateStr;
    @Transient
    private String hearingDateStr;
    @Transient
    private String imputedFullname;
    @Transient
    private String reviewerFullname;

    @Transient
    private Long reviewerId;

    @Transient
    private String presenceStr;

    @Transient
    private Boolean attended;

    @Transient
    private String informationDeliveredStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }


    public Boolean getPresence() {
        return presence;
    }

    public void setPresence(Boolean presence) {
        this.presence = presence;
    }

    public String getRegistrationFormulationDateStr() {
        return registrationFormulationDateStr;
    }

    public void setRegistrationFormulationDateStr(String registrationFormulationDateStr) {
        this.registrationFormulationDateStr = registrationFormulationDateStr;
    }

    public String getPresenceStr() {
        return presenceStr;
    }

    public void setPresenceStr(String presenceStr) {
        this.presenceStr = presenceStr;
    }

    public String getUmecaInterviewDateStr() {
        return umecaInterviewDateStr;
    }

    public void setUmecaInterviewDateStr(String umecaInterviewDateStr) {
        this.umecaInterviewDateStr = umecaInterviewDateStr;
    }

    public String getHearingDateStr() {
        return hearingDateStr;
    }

    public void setHearingDateStr(String hearingDateStr) {
        this.hearingDateStr = hearingDateStr;
    }

    public String getImputedFullname() {
        return imputedFullname;
    }

    public void setImputedFullname(String imputedFullname) {
        this.imputedFullname = imputedFullname;
    }

    public String getReviewerFullname() {
        return reviewerFullname;
    }

    public void setReviewerFullname(String reviewerFullname) {
        this.reviewerFullname = reviewerFullname;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public User getManagereval() {
        return managereval;
    }

    public void setManagereval(User managereval) {
        this.managereval = managereval;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public Boolean getInformationDelivered() {
        return informationDelivered;
    }

    public void setInformationDelivered(Boolean informationDelivered) {
        this.informationDelivered = informationDelivered;
    }

    public String getInformationDeliveredStr() {
        return informationDeliveredStr;
    }

    public void setInformationDeliveredStr(String informationDeliveredStr) {
        this.informationDeliveredStr = informationDeliveredStr;
    }
}


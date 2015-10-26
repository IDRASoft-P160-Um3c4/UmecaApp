package com.umeca.model.entities.reviewer.View;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CaseReportView implements EntityGrid {

    public CaseReportView(Long id, Long idVerif, String statusCode,  String idFolder, String name, String lastNameP, String lastNameM,
                          Date dateBirth, Boolean gender, String description, Long reviewerId, String reviewerName,
                          String statusCase, String eventString, String eventCode, Long eventId) {
        this.id = id;
        this.idVerif = idVerif;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.description = description;
        this.statusCode = statusCode;
        this.reviewerId = reviewerId;
        this.reviewerName = reviewerName;
        this.eventString = eventString;
        this.eventCode = eventCode;
        this.eventId = eventId;

        //this.statusCase = statusCase;
        createFullname();
        if(gender!=null){
            if(gender.equals(Constants.GENDER_FEMALE))
                this.genderString = "Femenino";
            else
                this.genderString = "Masculino";
        }else{
            this.genderString="Sin proporcionar";
        }
        Date date = Calendar.getInstance().getTime();
        date.setTime(dateBirth.getTime());
//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.dateBirthString = formatter.format(date);
        this.statusCase = statusCase;
    }


    public CaseReportView(Long id, String name, String lastNameP, String lastNameM, String reason, Date reportDate) {
        this.id = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.reportDate = reportDate;
        this.reason = reason;

        createFullname();

        Date date = Calendar.getInstance().getTime();
        date.setTime(reportDate.getTime());
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.stringDate = formatter.format(date);
    }

    private Long id;

    private String idFolder;

    private String name;

    private String lastNameP;

    private String lastNameM;

    private String fullname;

    private Date dateBirth;

    private Boolean gender;

    private String description;

    private String genderString;

    private String dateBirthString;

    private Long reviewerId;

    private String reviewerName;

    private String statusCase;

    private String statusCode;

    private String reason;

    private String eventString;

    private String eventCode;

    private Long eventId;

    private Date reportDate;

    private String stringDate;

    private Long idVerif;

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        createFullname();
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
        createFullname();
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
        createFullname();
    }

    public String getGenderString() {
        return genderString;
    }

    public void setGenderString(String genderString) {
        this.genderString = genderString;
    }

    public String getDateBirthString() {
        return dateBirthString;
    }

    public void setDateBirthString(String dateBirthString) {
        this.dateBirthString = dateBirthString;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getStatusCase() {
        return statusCase;
    }

    public void setStatusCase(String statusCase) {
        this.statusCase = statusCase;
    }

    private void createFullname(){
        this.fullname="";
        if(this.name!=null )
            this.fullname+= this.name+" ";
        if(this.lastNameP!=null)
            this.fullname+=this.lastNameP+" ";
        if(lastNameM!=null)
            this.fullname+=this.lastNameM;
    };

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEventString() {
        return eventString;
    }

    public void setEventString(String eventString) {
        this.eventString = eventString;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }


    public Long getIdVerif() {
        return idVerif;
    }

    public void setIdVerif(Long idVerif) {
        this.idVerif = idVerif;
    }
}
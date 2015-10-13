package com.umeca.model.entities.reviewer.View;

import com.umeca.model.shared.Constants;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class MeetingView implements EntityGrid {

    public MeetingView(Long id,String statusCode,  String idFolder, String name, String lastNameP, String lastNameM, Date dateBirth, Boolean gender, String description, Long reviewerId, String reviewerName, String statusCase) {
        this.id = id;
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

    public MeetingView(Long id, String name, String lastNameP, String lastNameM, Date negationDate, String reason) {
        this.id = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.negationDate = negationDate;
        this.reason = reason;

        createFullname();

        Date date = Calendar.getInstance().getTime();
        date.setTime(negationDate.getTime());
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        this.negationDateString = formatter.format(date);
    }

    public MeetingView(Long id,String statusCode,  String idFolder, String name, String lastNameP, String lastNameM, Date dateBirth, Boolean gender, String description, Long reviewerId, String statusCase) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.description = description;
        this.statusCode = statusCode;
        this.reviewerId = reviewerId;
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

    public MeetingView(Long id, String idFolder, String name, String lastNameP, String lastNameM, Date dateBirth, Boolean gender, String description) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.idFolder = idFolder;
        this.fullname = name + " " + lastNameP + " " + lastNameM;
        this.dateBirth = dateBirth;
        if (gender != null) {
            if (gender.equals(Constants.GENDER_FEMALE))
                this.genderString = "Femenino";
            else
                this.genderString = "Masculino";
        } else {
            this.genderString = "Sin proporcionar";
        }

        this.dateBirthString = dateBirth == null ? "" : sdf.format(dateBirth);
        this.description = description;
    }

    private Long id;

    private String idFolder;

    private String name;

    private String lastNameP;

    private String lastNameM;

    private String fullname;

    private Date dateBirth;

    private Date negationDate;

    private Boolean gender;

    private String description;

    private String genderString;

    private String dateBirthString;

    private String negationDateString;

    private Long reviewerId;

    private String reviewerName;

    private String statusCase;

    private String statusCode;

    private String reason;

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

    public Date getNegationDate() {
        return negationDate;
    }

    public void setNegationDate(Date negationDate) {
        this.negationDate = negationDate;
    }

    public String getNegationDateString() {
        return negationDateString;
    }

    public void setNegationDateString(String negationDateString) {
        this.negationDateString = negationDateString;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

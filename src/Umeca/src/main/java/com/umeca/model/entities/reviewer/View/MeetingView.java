package com.umeca.model.entities.reviewer.View;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.EntityGrid;

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

    public MeetingView(Long id,String statusCode,  String idFolder, String name, String lastNameP, String lastNameM, Date dateBirth, Boolean gender, String description,Long reviewerId,String statusCase) {
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

    private String statusCase;

    private String statusCode;
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

}

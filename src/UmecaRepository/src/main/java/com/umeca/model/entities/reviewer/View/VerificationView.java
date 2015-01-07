package com.umeca.model.entities.reviewer.View;

import com.umeca.model.shared.Constants;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 19/05/14
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class VerificationView implements EntityGrid {


    private Long id;

    private String idFolder;

    private String name;

    private String lastNameP;

    private String lastNameM;

    private String fullname;

    private Boolean gender;

    private String genderString;

    private String statusDescription;

    private String statusCode;

    private String statusCodeCase;

    private Long reviewerId;

    public VerificationView() {
    }

    public VerificationView(Long id, String idFolder, String name, String lastNameP, String lastNameM,
                            Boolean gender, String statusDescription, String statusCode, String statusCodeCase, Long reviewerId) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.gender = gender;
        this.statusDescription = statusDescription;
        this.statusCode = statusCode;
        createFullname();
        if(gender!=null){
            if(gender.equals(Constants.GENDER_FEMALE))
                this.genderString = "Femenino";
            else
                this.genderString = "Masculino";
        }else{
            this.genderString="Sin proporcionar";
        }
        this.statusCodeCase = statusCodeCase;
        this.reviewerId = reviewerId;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getGenderString() {
        return genderString;
    }

    public void setGenderString(String genderString) {
        this.genderString = genderString;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCodeCase() {
        return statusCodeCase;
    }

    public void setStatusCodeCase(String statusCodeCase) {
        this.statusCodeCase = statusCodeCase;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
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

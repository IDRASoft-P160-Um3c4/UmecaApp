package com.umeca.model.entities.director.view;

import com.umeca.infrastructure.jqgrid.model.JqGridFilterModel;
import com.umeca.model.shared.EntityGrid;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 26/08/14
 * Time: 12:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class CaseView implements EntityGrid {

    private Long id;

    private String idFolder;

    private String name;

    private String lastNameP;

    private String lastNameM;

    private String fullname;

    private Boolean gender;

    private String genderString;

    private String statusString;

    public CaseView(Long id,
                    String idFolder,
                    String name,
                    String lastNameP,
                    String lastNameM,
                    Boolean gender,
                    String statusString) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.gender = gender;
        this.statusString = statusString;
        this.fullname = this.name +" "+this.lastNameP+" "+this.lastNameM;
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

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }
}

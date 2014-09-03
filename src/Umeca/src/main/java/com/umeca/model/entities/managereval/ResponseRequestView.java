package com.umeca.model.entities.managereval;

import com.umeca.model.shared.Constants;
import com.umeca.model.shared.EntityGrid;

/**
 * Created by dcortesr on 30/06/14.
 */
public class ResponseRequestView implements EntityGrid {
    private Long id;
    private String idFolder;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String typeRequest;
    private String fullName;
    private String fullNameUser;
    private String responseType;

    public ResponseRequestView(Long id, String idFolder, String name, String lastNameP, String lastNameM, String typeRequest, String fullNameUser, String responseType) {
        this.id = id;
        this.idFolder = idFolder;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.typeRequest = typeRequest;
        this.fullNameUser=fullNameUser;
        this.fullName = name + " " + lastNameP + " "+ lastNameM;
        this.responseType = responseType;
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

    public String getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameUser() {
        return fullNameUser;
    }

    public void setFullNameUser(String fullNameUser) {
        this.fullNameUser = fullNameUser;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
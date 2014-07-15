package com.umeca.model.managereval;

import com.umeca.model.shared.EntityGrid;
import com.umeca.model.entities.reviewer.Crime;
import java.util.List;

/**
 * Created by dcortesr on 30/06/14.
 */
public class ManagerevalView implements EntityGrid {
    private Long id;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullname;
    private String idFolder;
    private String crime;

    public ManagerevalView(Long id, String idFolder, String name, String lastNameP, String lastNameM, String crime){
        this.id = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.idFolder = idFolder;
        this.crime = crime;
        createFullname();
    }

    public String getCrime(){
        return crime;
    }

    public void setCrime(String crime){
        this.crime = crime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private void createFullname() {
        this.fullname = "";
        if (this.name != null )
            this.fullname += this.name + " ";
        if (this.lastNameP != null)
            this.fullname += this.lastNameP + " ";
        if (lastNameM != null)
            this.fullname += this.lastNameM;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
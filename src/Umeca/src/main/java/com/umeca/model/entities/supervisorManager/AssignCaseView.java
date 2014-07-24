package com.umeca.model.entities.supervisorManager;

import com.umeca.model.shared.*;
/**
 * Created by dcortesr on 22/07/14.
 */
public class AssignCaseView implements EntityGrid {
    private Long id;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullname;
    private String idFolder;
    private String status;

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    private Long supervisor;

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

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AssignCaseView(Long id, String idFolder, String name, String lastNameP, String lastNameM, String status, Long supervisor){
        this.id = id;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;
        this.idFolder = idFolder;
        this.status = status;
        this.supervisor = supervisor;
        createFullname();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id){
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
}

package com.umeca.model.entities.shared;

import com.umeca.model.shared.EntityGrid;

public class MessageHistoryView implements EntityGrid {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    private String idFolder;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String fullName;

    public MessageHistoryView(Long id, String idFolder, String name, String lastNameP, String lastNameM){
        this.id = id;
        this.idFolder = idFolder;
        this.fullName = lastNameP + "," + lastNameM + "/" + name;
    }
}

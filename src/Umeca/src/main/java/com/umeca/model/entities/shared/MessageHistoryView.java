package com.umeca.model.entities.shared;

import com.umeca.model.shared.EntityGrid;

/**
 * Created by dcortesr on 17/09/14.
 */
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


    public MessageHistoryView(Long id, String idFolder){
        this.id = id;
        this.idFolder = idFolder;
    }
}

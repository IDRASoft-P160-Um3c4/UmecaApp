package com.umeca.model.entities.shared;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadFileView implements EntityGrid{

    private Long id;
    private String filename;
    private String description;
    private Long size;
    private String fullname;
    private Calendar creationTime;
    private String stCreationTime;
    private Long caseId;
    private String typeName;

    public UploadFileView(Long id, String filename, String description, Long size, String fullname, Calendar creationTime, Long caseId, String typeName) {
        this.id = id;
        this.filename = filename;
        this.description = description;
        this.size = size;
        this.fullname = fullname;
        this.creationTime = creationTime;
        this.caseId = caseId;
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public String getStCreationTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String sValue = sdf.format(creationTime.getTime());
        return sValue;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

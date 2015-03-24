package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AttachmentDto implements EntityGrid {

    private Long id;
    private Long fileId;
    private Long oldFileId;
    private Long idEmployee;
    private String attachmentName;
    private String attachmentDescription;
    private String creationTime;


    public AttachmentDto() {
    }

    //upsert
    public AttachmentDto(Long id, Long fileId, Long idEmployee, String attachmentName, String attachmentDescription) {
        this.id = id;
        this.fileId = fileId;
        this.idEmployee = idEmployee;
        this.attachmentName = attachmentName;
        this.attachmentDescription = attachmentDescription;
    }

    //grid
    public AttachmentDto(Long id, Long fileId, String attachmentName, Calendar upDate, String attachmentDescription) {
        this.id = id;
        this.fileId = fileId;
        this.attachmentName = attachmentName;
        if (upDate != null) {
            this.creationTime = CalendarExt.calendarToFormatString(upDate, "yyyy/MM/dd");
        }
        this.attachmentDescription = attachmentDescription;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getOldFileId() {
        return oldFileId;
    }

    public void setOldFileId(Long oldFileId) {
        this.oldFileId = oldFileId;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentDescription() {
        return attachmentDescription;
    }

    public void setAttachmentDescription(String attachmentDescription) {
        this.attachmentDescription = attachmentDescription;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}


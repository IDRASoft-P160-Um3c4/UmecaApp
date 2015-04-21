package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IncidentDto implements EntityGrid {

    private Long id;
    private Long idEmployee;
    private String comments;
    private String reason;
    private String incidentDate;
    private String specIncidentType;
    private String incidentType;
    private Long idIncidentType;
    private Long fileId;


    public IncidentDto() {
    }

    //grid
    public IncidentDto(Long id, String incidentType, String reason, Date incidentDate, String comments, Long fileId) {
        this.id = id;
        this.incidentType = incidentType;
        this.reason = reason;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (incidentDate != null) {
            this.incidentDate = formatter.format(incidentDate);
        }
        this.comments = comments;
        this.fileId = fileId;
    }

    //upsert
    public IncidentDto(Long id, Long idEmployee, Long idIncidentType, String specIncidentType, String comments, String reason, Date incidentDate, Long fileId) {
        this.id = id;
        this.idEmployee = idEmployee;
        this.idIncidentType = idIncidentType;
        this.specIncidentType = specIncidentType;
        this.comments = comments;
        this.reason = reason;
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (incidentDate != null) {
            this.incidentDate = formatter.format(incidentDate);
        }
        this.fileId = fileId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getSpecIncidentType() {
        return specIncidentType;
    }

    public void setSpecIncidentType(String specIncidentType) {
        this.specIncidentType = specIncidentType;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public Long getIdIncidentType() {
        return idIncidentType;
    }

    public void setIdIncidentType(Long idIncidentType) {
        this.idIncidentType = idIncidentType;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
}

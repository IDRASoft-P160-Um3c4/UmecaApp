package com.umeca.model.entities.humanReources;

import com.umeca.model.catalog.IncidentType;
import com.umeca.model.entities.shared.UploadFileGeneric;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue
    @Column(name = "id_incident")
    private Long id;

    @Column(name = "comments")
    private String comments;

    @Column(name = "reason")
    private String reason;

    @Column(name = "incident_date")
    private Date incidentDate;

    @Column(name = "spec_incident_type")
    private String specIncidentType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_incident_type")
    private IncidentType incidentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_generic_file")
    private UploadFileGeneric file;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getSpecIncidentType() {
        return specIncidentType;
    }

    public void setSpecIncidentType(String specIncidentType) {
        this.specIncidentType = specIncidentType;
    }

    public IncidentType getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(IncidentType incidentType) {
        this.incidentType = incidentType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public UploadFileGeneric getFile() {
        return file;
    }

    public void setFile(UploadFileGeneric file) {
        this.file = file;
    }
}
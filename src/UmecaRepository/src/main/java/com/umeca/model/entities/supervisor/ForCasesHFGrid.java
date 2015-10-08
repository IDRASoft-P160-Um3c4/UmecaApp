package com.umeca.model.entities.supervisor;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.entities.shared.SystemSetting;
import com.umeca.model.entities.shared.SystemSettingValues;
import com.umeca.model.shared.Constants;
import com.umeca.repository.shared.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ForCasesHFGrid implements EntityGrid {
    private Long id;
    private String status;
    private String statusDesc;
    private String idFolder;
    private String idMP;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;
    private Long framingMeetingId;
    private Long idTR;
    private StringBuilder sb;
    private boolean hasHF;
    private String assignedSupervisorName;
    private Date dateOpinion;
    private Boolean opinionDateExpired;
    private Long opinionDateExpiredMil;
    private String description;



    public ForCasesHFGrid(Long id, String status, String idMP, String name, String lastNameP, String lastNameM) {
        this.id = id;
        this.status = status;
        this.idMP = idMP;
        this.name = name;
        this.lastNameM = lastNameM;
        this.lastNameP = lastNameP;

        sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" ");
        sb.append(this.lastNameP);
        sb.append(" ");
        sb.append(this.lastNameM);
        this.fullName = sb.toString();


    }

    public ForCasesHFGrid(Long id, String status, String description, String idFolder,String idMP,String name, String lastNameP, String lastNameM ){
        this.id = id;
        this.status = status;
        this.statusDesc = description;
        this.idFolder = idFolder;
        this.idMP = idMP;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;

        sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" ");
        sb.append(this.lastNameP);
        sb.append(" ");
        sb.append(this.lastNameM);
        this.fullName = sb.toString();

    }

    public ForCasesHFGrid(Long id, String status, String statusDesc, String idFolder, String idMP, String name, String lastNameP,
                          String lastNameM, Long framingMeetingId, Long idTR, boolean hasHF, String assignedSupervisorName,Date dateOpinion) {
        this(id, status, idMP, name, lastNameP, lastNameM);
        this.statusDesc = statusDesc;
        this.idFolder = idFolder;
        this.framingMeetingId = framingMeetingId;
        this.idTR = idTR;
        this.hasHF = hasHF;
        this.assignedSupervisorName = assignedSupervisorName;



        if(dateOpinion != null) {
            this.opinionDateExpiredMil = dateOpinion.getTime();
        }
    }

    public ForCasesHFGrid(Long id, String status, String statusDesc, String idFolder, String idMP, String name, String lastNameP,
                          String lastNameM, Date dateOpinion) {
        this(id, status, idMP, name, lastNameP, lastNameM);
        this.statusDesc = statusDesc;
        this.idFolder = idFolder;
        this.idMP = idMP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(String idFolder) {
        this.idFolder = idFolder;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getFramingMeetingId() {
        return framingMeetingId;
    }

    public void setFramingMeetingId(Long framingMeetingId) {
        this.framingMeetingId = framingMeetingId;
    }

    public Long getIdTR() {
        return idTR;
    }

    public void setIdTR(Long idTR) {
        this.idTR = idTR;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public void setSb(StringBuilder sb) {
        this.sb = sb;
    }

    public boolean isHasHF() {
        return hasHF;
    }

    public void setHasHF(boolean hasHF) {
        this.hasHF = hasHF;
    }

    public String getAssignedSupervisorName() {
        return assignedSupervisorName;
    }

    public void setAssignedSupervisorName(String assignedSupervisorName) {
        this.assignedSupervisorName = assignedSupervisorName;
    }

    public Date getDateOpinion() {
        return dateOpinion;
    }

    public void setDateOpinion(Date dateOpinion) {
        this.dateOpinion = dateOpinion;
    }

    public Boolean getOpinionDateExpired() {
        return opinionDateExpired;
    }

    public void setOpinionDateExpired(Boolean opinionDateExpired) {
        this.opinionDateExpired = opinionDateExpired;
    }

    public Long getOpinionDateExpiredMil() {
        return opinionDateExpiredMil;
    }

    public void setOpinionDateExpiredMil(Long opinionDateExpiredMil) {
        this.opinionDateExpiredMil = opinionDateExpiredMil;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
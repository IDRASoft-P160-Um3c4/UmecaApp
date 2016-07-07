package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.entities.shared.MonitoringPlanCommons;
import com.umeca.model.shared.MonitoringConstants;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
@Subselect("select mp.id_monitoring_plan,\n" +
        "cd.id_case,\n" +
        "cd.id_mp,\n" +
        "concat(i.name,' ',i.lastname_p,' ',i.lastname_m) as imputed_name,\n" +
        "date_format(mp.creation_time,'%d-%m-%Y %H:%i') creation_time, \n" +
        "date_format(mp.generation_time,'%d-%m-%Y %H:%i') st_generation_time,\n" +
        "date_format(mp.authorization_time,'%d-%m-%Y %H:%i') st_authorization_time, \n" +
        "mp.authorization_time authorization_time, \n" +
        "mp.generation_time, \n"+
        "mp.status, \n" +
        "usr.id_user,\n" +
        "usr.fullname,\n" +
        "mp.pos_authorization_change_time,\n" +
        "false as has_act_preauth,\n" +
        "false as is_mon_plan_suspended\n" +
        "from monitoring_plan mp \n" +
        "join case_detention cd on mp.id_case = cd.id_case\n" +
        "join meeting m on m.id_case = cd.id_case\n" +
        "join imputed i on i.id_meeting = m.id_meeting\n" +
        "join user usr on usr.id_user = mp.id_user_supervisor\n" +
        "where mp.status not in ('" +
        MonitoringConstants.STATUS_END+"','"+
        MonitoringConstants.STATUS_PENDING_END+"','"+
        MonitoringConstants.STATUS_PENDING_AUTHORIZATION+"')")

        public class GenerateMonitoringPlanCasesView implements EntityGrid {

    @Id
    @Column(name = "id_monitoring_plan")
    private Long id;

    @Column(name = "id_case")
    private Long caseId;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_mp")
    private String idMP;

    @Column(name = "imputed_name")
    private String fullName;

    @Column(name = "creation_time")
    private String stCreationTime;

    @Column(name = "generation_time")
    private Calendar generationTime;

    @Column(name = "st_generation_time")
    private String stGenerationTime;

    @Column(name = "authorization_time")
    private Calendar authorizationTime;

    @Column(name = "st_authorization_time")
    private String stAuthorizationTime;

    @Column(name = "status")
    private String status;

    @Column(name = "fullname")
    private String supervisor;

    @Column(name = "pos_authorization_change_time")
    private Calendar posAuthorizationChangeTime;

    @Column(name = "has_act_preauth")
    private boolean hasActPreAuth;

    @Column(name = "is_mon_plan_suspended")
    private boolean isMonPlanSuspended;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStCreationTime() {
        return stCreationTime;
    }

    public void setStCreationTime(String stCreationTime) {
        this.stCreationTime = stCreationTime;
    }

    public String getStGenerationTime() {
        return stGenerationTime;
    }

    public void setStGenerationTime(String stGenerationTime) {
        this.stGenerationTime = stGenerationTime;
    }

    public String getStAuthorizationTime() {
        return stAuthorizationTime;
    }

    public void setStAuthorizationTime(String stAuthorizationTime) {
        this.stAuthorizationTime = stAuthorizationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Calendar getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(Calendar generationTime) {
        this.generationTime = generationTime;
    }

    public Calendar getAuthorizationTime() {
        return authorizationTime;
    }

    public void setAuthorizationTime(Calendar authorizationTime) {
        this.authorizationTime = authorizationTime;
    }

    public Calendar getPosAuthorizationChangeTime() {
        return posAuthorizationChangeTime;
    }

    public void setPosAuthorizationChangeTime(Calendar posAuthorizationChangeTime) {
        this.posAuthorizationChangeTime = posAuthorizationChangeTime;
    }

    public boolean isHasActPreAuth() {
        return hasActPreAuth;
    }

    public void setHasActPreAuth(boolean hasActPreAuth) {
        this.hasActPreAuth = MonitoringPlanCommons.calculateHasActPreAuth(authorizationTime, posAuthorizationChangeTime);;
    }

    public boolean isMonPlanSuspended() {
        this.isMonPlanSuspended = MonitoringPlanCommons.calculateIsMonPlanSuspended(generationTime, authorizationTime, posAuthorizationChangeTime);
        return isMonPlanSuspended;
    }

    public void setMonPlanSuspended(boolean isMonPlanSuspended) {
        this.isMonPlanSuspended = isMonPlanSuspended;
    }



    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}

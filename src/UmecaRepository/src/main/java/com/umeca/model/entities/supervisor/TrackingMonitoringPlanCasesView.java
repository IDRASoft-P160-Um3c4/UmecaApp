package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.shared.MonitoringConstants;
import com.umeca.model.shared.SharedSystemSetting;
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
        "where mp.status not in ('TERMINADO','EN PROCESO DE TERMINAR','EN PROCESO DE AUTORIZAR')")

public class TrackingMonitoringPlanCasesView implements EntityGrid {

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
        this.hasActPreAuth = calculateHasActPreAuth(authorizationTime, posAuthorizationChangeTime);;
    }

    public boolean isMonPlanSuspended() {
        this.isMonPlanSuspended = calculateIsMonPlanSuspended(generationTime, authorizationTime, posAuthorizationChangeTime);
        return isMonPlanSuspended;
    }

    public void setMonPlanSuspended(boolean isMonPlanSuspended) {
        this.isMonPlanSuspended = isMonPlanSuspended;
    }

    public static boolean calculateHasActPreAuth(Calendar authorizationTime, Calendar posAuthorizationChangeTime) {
        return authorizationTime != null && posAuthorizationChangeTime != null;
    }

    public static boolean calculateIsMonPlanSuspended(Calendar generationTime, Calendar authorizationTime, Calendar posAuthorizationChangeTime) {
        if(typeIsMonPlanSuspended(generationTime, authorizationTime, posAuthorizationChangeTime) == MonitoringConstants.AUTHORIZATION_OK)
            return false;
        return true;
    }

    public static int typeIsMonPlanSuspended(Calendar generationTime, Calendar authorizationTime, Calendar posAuthorizationChangeTime) {
        //Primero revisar si es por autorización del plan o por autorización de las nuevas actividades
        if(generationTime != null && authorizationTime == null){
            long timeDifDays = (Calendar.getInstance().getTimeInMillis() - generationTime.getTimeInMillis()) / (SharedSystemSetting.MILISECONDS_PER_HOUR);
            if(timeDifDays >= SharedSystemSetting.MonPlanHoursToAuthorize){
                return MonitoringConstants.AUTHORIZATION_MONPLAN;
            }

        }else if(calculateHasActPreAuth(authorizationTime, posAuthorizationChangeTime)){
            long timeDifDays = (Calendar.getInstance().getTimeInMillis() - posAuthorizationChangeTime.getTimeInMillis()) / (SharedSystemSetting.MILISECONDS_PER_HOUR);
            if(timeDifDays >= SharedSystemSetting.MonPlanHoursToAuthorize){
                return MonitoringConstants.AUTHORIZATION_ACTMONPLAN;
            }
        }
        return MonitoringConstants.AUTHORIZATION_OK;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}

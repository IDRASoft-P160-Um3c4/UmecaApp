package com.umeca.model.entities.supervisor;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/12/14
 * Time: 11:27 AM
 */
public class MonitoringPlanInfo {
    private Long idMonitoringPlan;
    private Long idCase;
    private String idMP;
    private String personName;
    private String monStatus;

    public MonitoringPlanInfo(Long idMonitoringPlan, Long idCase, String idMP, String name, String lastNameP, String lastNameM, String status){
        this.idMonitoringPlan = idMonitoringPlan;
        this.idCase = idCase;
        this.idMP = idMP;
        this.personName = name + " " + lastNameP + " " + lastNameM;
        this.monStatus = status;
    }

    public Long getIdMonitoringPlan() {
        return idMonitoringPlan;
    }

    public void setIdMonitoringPlan(Long idMonitoringPlan) {
        this.idMonitoringPlan = idMonitoringPlan;
    }

    public Long getIdCase() {
        return idCase;
    }

    public void setIdCase(Long idCase) {
        this.idCase = idCase;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getMonStatus() {
        return monStatus;
    }

    public void setMonStatus(String monStatus) {
        this.monStatus = monStatus;
    }
}

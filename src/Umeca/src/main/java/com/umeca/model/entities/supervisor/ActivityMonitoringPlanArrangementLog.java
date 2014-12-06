package com.umeca.model.entities.supervisor;

import com.umeca.model.shared.MonitoringConstants;

import java.util.List;

/**
 * Project: Umeca
 * User: Israel
 * Date: 7/9/14
 * Time: 4:11 PM
 */
public class ActivityMonitoringPlanArrangementLog {
    private Long id;
    private Long actMonPlanId;
    private Long assignedArrangementId;
    private Integer status;
    private String statusSt;
    private String arrangementName;

    public ActivityMonitoringPlanArrangementLog() {
    }

    public ActivityMonitoringPlanArrangementLog(Long id, Long actMonPlanId, Long assignedArrangementId, Integer status){
        this.id = id;
        this.actMonPlanId = actMonPlanId;
        this.assignedArrangementId = assignedArrangementId;
        this.status = status;
    }

    public ActivityMonitoringPlanArrangementLog(Integer status, String arrangementName) {
        this.status = status;
        this.arrangementName = arrangementName;
        this.setStatusString();
    }

    public void  setStatusString(){
        switch (status){
            case MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED:
                this.statusSt = MonitoringConstants.ACTIVITY_ARRANGEMENT_UNDEFINED_STRING;
                break;
            case MonitoringConstants.ACTIVITY_ARRANGEMENT_FAILED:
                this.statusSt = MonitoringConstants.ACTIVITY_ARRANGEMENT_FAILED_STRING;
                break;
            case MonitoringConstants.ACTIVITY_ARRANGEMENT_DONE:
                this.statusSt = MonitoringConstants.ACTIVITY_ARRANGEMENT_DONE_STRING;
                break;
        }
    }

    public String getArrangementName() {
        return arrangementName;
    }

    public void setArrangementName(String arrangementName) {
        this.arrangementName = arrangementName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActMonPlanId() {
        return actMonPlanId;
    }

    public void setActMonPlanId(Long actMonPlanId) {
        this.actMonPlanId = actMonPlanId;
    }

    public Long getAssignedArrangementId() {
        return assignedArrangementId;
    }

    public void setAssignedArrangementId(Long assignedArrangementId) {
        this.assignedArrangementId = assignedArrangementId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusSt() {
        return statusSt;
    }

    public void setStatusSt(String statusSt) {
        this.statusSt = statusSt;
    }

     public String stringToLogCase(List<ActivityMonitoringPlanArrangementLog> list){
         String result="<ul>";
         for(ActivityMonitoringPlanArrangementLog am: list){
             result+="<li>"+am.getArrangementName()+" - "+am.getStatusSt()+"</li>";
         }
         result+="</ul>";
         return result;
     }
}

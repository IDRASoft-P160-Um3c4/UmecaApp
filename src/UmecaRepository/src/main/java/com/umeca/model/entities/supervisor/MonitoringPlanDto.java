package com.umeca.model.entities.supervisor;

import java.util.Calendar;

public class MonitoringPlanDto {
    private Long MonPlanId;
    private Calendar generationTime;
    private Calendar authorizationTime;
    private Calendar posAuthorizationChangeTime;
    private boolean hasActPreAuth;
    private boolean isMonPlanSuspended;

    public MonitoringPlanDto(Long monPlanId, Calendar generationTime, Calendar authorizationTime, Calendar posAuthorizationChangeTime) {
        MonPlanId = monPlanId;
        this.generationTime = generationTime;
        this.authorizationTime = authorizationTime;
        this.posAuthorizationChangeTime = posAuthorizationChangeTime;
        this.hasActPreAuth = MonitoringPlanView.calculateHasActPreAuth(authorizationTime, posAuthorizationChangeTime);
        this.isMonPlanSuspended = MonitoringPlanView.calculateIsMonPlanSuspended(generationTime, authorizationTime, posAuthorizationChangeTime);
    }

    public Long getMonPlanId() {
        return MonPlanId;
    }

    public void setMonPlanId(Long monPlanId) {
        MonPlanId = monPlanId;
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

    public boolean getHasActPreAuth() {
        return hasActPreAuth;
    }

    public void setHasActPreAuth(boolean hasActPreAuth) {
        this.hasActPreAuth = hasActPreAuth;
    }

    public boolean getMonPlanSuspended() {
        return isMonPlanSuspended;
    }

    public void setMonPlanSuspended(boolean monPlanSuspended) {
        isMonPlanSuspended = monPlanSuspended;
    }


}

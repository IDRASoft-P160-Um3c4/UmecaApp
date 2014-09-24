package com.umeca.model.entities.supervisorManager;

import com.umeca.model.entities.supervisor.ActivityMonitoringPlanInfo;

public class PreActivityMonitoringPlan {
    private ActivityMonitoringPlanInfo newActMonPlanInfo;
    private ActivityMonitoringPlanInfo oldActMonPlanInfo;

    public ActivityMonitoringPlanInfo getNewActMonPlanInfo() {
        return newActMonPlanInfo;
    }

    public void setNewActMonPlanInfo(ActivityMonitoringPlanInfo newActMonPlanInfo) {
        this.newActMonPlanInfo = newActMonPlanInfo;
    }

    public ActivityMonitoringPlanInfo getOldActMonPlanInfo() {
        return oldActMonPlanInfo;
    }

    public void setOldActMonPlanInfo(ActivityMonitoringPlanInfo oldActMonPlanInfo) {
        this.oldActMonPlanInfo = oldActMonPlanInfo;
    }
}

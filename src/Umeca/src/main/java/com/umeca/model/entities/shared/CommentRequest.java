package com.umeca.model.entities.shared;

import com.umeca.model.entities.supervisorManager.LogCommentMonitoringPlan;

public class CommentRequest {
    private Long id;
    private LogCommentMonitoringPlan logCommentMonitoringPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LogCommentMonitoringPlan getLogCommentMonitoringPlan() {
        return logCommentMonitoringPlan;
    }

    public void setLogCommentMonitoringPlan(LogCommentMonitoringPlan logCommentMonitoringPlan) {
        this.logCommentMonitoringPlan = logCommentMonitoringPlan;
    }
}

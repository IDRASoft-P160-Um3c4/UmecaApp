package com.umeca.model.entities.supervisorManager;

public class ChangeSupervisor {
    private Long monPlanId;
    private Long supervisorId;
    private String comments;
    private String password;

    public Long getMonPlanId() {
        return monPlanId;
    }

    public void setMonPlanId(Long monPlanId) {
        this.monPlanId = monPlanId;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

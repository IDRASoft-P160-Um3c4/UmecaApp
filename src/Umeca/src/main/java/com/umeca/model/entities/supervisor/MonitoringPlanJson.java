package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;

public class MonitoringPlanJson {

    private Long id;
    private Long caseId;
    private Long userId;
    private Calendar creationTime;
    private Long generatorId;
    private Calendar generationTime;
    private Long authorizerId;
    private Calendar authorizationTime;
    private String status;

    private MonitoringPlanJson(Long id, Long caseId, Long userId, Calendar creationTime, Long generatorId, Calendar generationTime, Long authorizerId,
                               Calendar authorizationTime, String status){

        this.id = id;
        this.caseId = caseId;
        this.userId = userId;
        this.creationTime = creationTime;
        this.generatorId = generatorId;
        this.generationTime = generationTime;
        this.authorizerId = authorizerId;
        this.authorizationTime = authorizationTime;
        this.status = status;

    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public Long getGeneratorId() {
        return generatorId;
    }

    public void setGeneratorId(Long generatorId) {
        this.generatorId = generatorId;
    }

    public Calendar getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(Calendar generationTime) {
        this.generationTime = generationTime;
    }

    public Long getAuthorizerId() {
        return authorizerId;
    }

    public void setAuthorizerId(Long authorizerId) {
        this.authorizerId = authorizerId;
    }

    public Calendar getAuthorizationTime() {
        return authorizationTime;
    }

    public void setAuthorizationTime(Calendar authorizationTime) {
        this.authorizationTime = authorizationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static MonitoringPlanJson convertToJson(MonitoringPlan monPlan) {
        Long id = monPlan.getId();
        Long caseId = monPlan.getCaseDetention().getId();
        Long userId = monPlan.getSupervisor().getId();
        Calendar creationTime = monPlan.getCreationTime();
        User user = monPlan.getGenerator();
        Long generatorId = (user == null ? null : user.getId());
        Calendar generationTime = monPlan.getGenerationTime();
        user = monPlan.getAuthorizer();
        Long authorizerId = (user == null ? null : user.getId());
        Calendar authorizationTime = monPlan.getAuthorizationTime();
        String status = monPlan.getStatus();
        return new MonitoringPlanJson(id, caseId, userId, creationTime, generatorId, generationTime, authorizerId, authorizationTime, status);
    }


}

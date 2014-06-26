package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/3/14
 * Time: 3:13 PM
 */

@Entity
@Table(name = "monitoring_plan")
public class MonitoringPlan {

    @Id
    @GeneratedValue
    @Column(name = "id_monitoring_plan")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_case", nullable = false)
    private Case caseDetention;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_supervisor", nullable = false)
    private User supervisor;

    @Column(name = "creation_time", nullable = false)
    private Calendar creationTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_generator", nullable = true)
    private User generator;

    @Column(name = "generation_time", nullable = true)
    private Calendar generationTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_authorizer", nullable = true)
    private User authorizer;

    @Column(name = "authorization_time", nullable = true)
    private Calendar authorizationTime;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getCaseDetention() {
        return caseDetention;
    }

    public void setCaseDetention(Case caseDetention) {
        this.caseDetention = caseDetention;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public User getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(User authorizer) {
        this.authorizer = authorizer;
    }

    public User getGenerator() {
        return generator;
    }

    public void setGenerator(User generator) {
        this.generator = generator;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.sql.Timestamp;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_hearing_format", nullable = false)
    private HearingFormat hearingFormat;

    @Column(name = "creation_time", nullable = false)
    private Timestamp creationTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_user_authorizer", nullable = true)
    private User authorizer;

    @Column(name = "status", nullable = false)
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

    public HearingFormat getHearingFormat() {
        return hearingFormat;
    }

    public void setHearingFormat(HearingFormat hearingFormat) {
        this.hearingFormat = hearingFormat;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public User getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(User authorizer) {
        this.authorizer = authorizer;
    }
}

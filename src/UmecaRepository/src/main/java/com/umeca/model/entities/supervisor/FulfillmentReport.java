package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.FulfillmentReportType;
import com.umeca.model.dto.supervisorManager.RelFulfillmentReportArrangement;
import com.umeca.model.entities.account.User;
import com.umeca.model.entities.reviewer.Case;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="fulfillment_report")
public class FulfillmentReport {

    @Id
    @GeneratedValue
    @Column(name="id_fulfillment_report")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_case", nullable = false)
    private Case caseDetention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_monitoring_plan", nullable = false)
    private MonitoringPlan monitoringPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fulfillment_report_type", nullable = false)
    private FulfillmentReportType fulfillmentReportType;

    @Column(name = "timestamp", nullable = false)
    private Calendar timestamp;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_request", nullable = false)
    private User userRequest;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fulfillmentReport", cascade = CascadeType.ALL)
    private List<RelFulfillmentReportArrangement> relFulfillmentReportArrangements;

    @Column(name = "fulfilment_date", nullable = false)
    private Calendar fulfillmentDate;

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

    public MonitoringPlan getMonitoringPlan() {
        return monitoringPlan;
    }

    public void setMonitoringPlan(MonitoringPlan monitoringPlan) {
        this.monitoringPlan = monitoringPlan;
    }

    public FulfillmentReportType getFulfillmentReportType() {
        return fulfillmentReportType;
    }

    public void setFulfillmentReportType(FulfillmentReportType fulfillmentReportType) {
        this.fulfillmentReportType = fulfillmentReportType;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public User getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(User userRequest) {
        this.userRequest = userRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RelFulfillmentReportArrangement> getRelFulfillmentReportArrangements() {
        return relFulfillmentReportArrangements;
    }

    public void setRelFulfillmentReportArrangements(List<RelFulfillmentReportArrangement> relFulfillmentReportArrangements) {
        this.relFulfillmentReportArrangements = relFulfillmentReportArrangements;
    }

    public Calendar getFulfillmentDate() {
        return fulfillmentDate;
    }

    public void setFulfillmentDate(Calendar fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }
}


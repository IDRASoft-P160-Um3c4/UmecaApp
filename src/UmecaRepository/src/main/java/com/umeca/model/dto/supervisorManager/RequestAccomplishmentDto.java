package com.umeca.model.dto.supervisorManager;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.entities.supervisorManager.RolActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RequestAccomplishmentDto {
    private Long id;
    private Long fulfillmentReportId;
    private String lstArrangement;
    private String comment;
    private String accomplishmentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFulfillmentReportId() {
        return fulfillmentReportId;
    }

    public void setFulfillmentReportId(Long fulfillmentReportId) {
        this.fulfillmentReportId = fulfillmentReportId;
    }

    public String getLstArrangement() {
        return lstArrangement;
    }

    public void setLstArrangement(String lstArrangement) {
        this.lstArrangement = lstArrangement;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAccomplishmentDate() {
        return accomplishmentDate;
    }

    public void setAccomplishmentDate(String accomplishmentDate) {
        this.accomplishmentDate = accomplishmentDate;
    }
}

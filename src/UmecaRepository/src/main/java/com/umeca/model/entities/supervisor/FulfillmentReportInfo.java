package com.umeca.model.entities.supervisor;

import java.util.Calendar;

/**
 * Project: Umeca
 * User: Israel
 * Date: 12/6/14
 * Time: 9:38 AM
 */
public class FulfillmentReportInfo {

    private Long id;
    private String type;
    private Calendar timestamp;
    private String comment;
    private Calendar fulfillmentDate;

    public FulfillmentReportInfo(Long id, String type, Calendar timestamp, String comment, Calendar fulfillmentDate) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.comment = comment;
        this.fulfillmentDate = fulfillmentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Calendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Calendar timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Calendar getFulfillmentDate() {
        return fulfillmentDate;
    }

    public void setFulfillmentDate(Calendar fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }
}

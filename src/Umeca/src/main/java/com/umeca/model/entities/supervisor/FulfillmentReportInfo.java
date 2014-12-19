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

    public FulfillmentReportInfo(Long id, String type, Calendar timestamp) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
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
}

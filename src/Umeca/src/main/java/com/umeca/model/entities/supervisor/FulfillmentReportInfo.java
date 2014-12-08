package com.umeca.model.entities.supervisor;

import java.util.Calendar;

/**
 * Project: Umeca
 * User: Israel
 * Date: 12/6/14
 * Time: 9:38 AM
 */
public class FulfillmentReportInfo {

    private String type;
    private Calendar timestamp;

    public FulfillmentReportInfo(String type, Calendar timestamp) {
        this.type = type;
        this.timestamp = timestamp;
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

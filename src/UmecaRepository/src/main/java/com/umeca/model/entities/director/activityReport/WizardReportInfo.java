package com.umeca.model.entities.director.activityReport;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class WizardReportInfo {
    public String startDate;
    public Calendar clStartDate;
    public String reportName;
    public String reportDesc;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public Calendar getClStartDate() {
        return CalendarExt.stringToCalendarExc(startDate, Constants.FORMAT_CALENDAR_II);
    }
}

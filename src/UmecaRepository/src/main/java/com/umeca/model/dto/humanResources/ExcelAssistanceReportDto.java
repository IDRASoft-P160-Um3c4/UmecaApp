package com.umeca.model.dto.humanResources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 13/11/2015.
 */
public class ExcelAssistanceReportDto {

    private List<String> dates;
    private List<EmployeeExcelDto> employees;
    private Date initDate;
    private String initDateStr;
    private Date endDate;
    private String endDateStr;


    public ExcelAssistanceReportDto() {
    }

    public ExcelAssistanceReportDto(Date initDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        this.initDateStr = sdf.format(initDate);
        this.endDateStr = sdf.format(endDate);
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<EmployeeExcelDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeExcelDto> employees) {
        this.employees = employees;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public String getInitDateStr() {
        return initDateStr;
    }

    public void setInitDateStr(String initDateStr) {
        this.initDateStr = initDateStr;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }


}

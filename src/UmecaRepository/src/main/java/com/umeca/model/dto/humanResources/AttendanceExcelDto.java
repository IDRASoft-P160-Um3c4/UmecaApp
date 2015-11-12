package com.umeca.model.dto.humanResources;


import java.util.Calendar;
import java.util.Date;

public class AttendanceExcelDto {

    private Long attendanceId;
    private Calendar eventTime;
    private Short workCode;
    private String employeeName;
    private Long employeeId;
    private Long absenceId;
    private Boolean approved;
    private Date absenceDate;
    private Integer absenceType;


    public AttendanceExcelDto() {

    }


    public AttendanceExcelDto(Long attendanceId, Calendar eventTime, Short workCode, String employeeName, Long employeeId, Long absenceId, Boolean approved, Date absenceDate, Integer absenceType) {
        this.attendanceId = attendanceId;
        this.eventTime = eventTime;
        this.workCode = workCode;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.absenceId = absenceId;
        this.approved = approved;
        this.absenceDate = absenceDate;
        this.absenceType = absenceType;
    }

    public AttendanceExcelDto(Long attendanceId, Calendar eventTime, Short workCode, String employeeName, Long employeeId) {
        this.attendanceId = attendanceId;
        this.eventTime = eventTime;
        this.workCode = workCode;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Calendar getEventTime() {
        return eventTime;
    }

    public void setEventTime(Calendar eventTime) {
        this.eventTime = eventTime;
    }

    public Short getWorkCode() {
        return workCode;
    }

    public void setWorkCode(Short workCode) {
        this.workCode = workCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(Long absenceId) {
        this.absenceId = absenceId;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Date getAbsenceDate() {
        return absenceDate;
    }

    public void setAbsenceDate(Date absenceDate) {
        this.absenceDate = absenceDate;
    }

    public Integer getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(Integer absenceType) {
        this.absenceType = absenceType;
    }
}
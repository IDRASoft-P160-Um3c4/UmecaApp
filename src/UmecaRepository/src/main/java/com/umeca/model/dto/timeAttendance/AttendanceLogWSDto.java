package com.umeca.model.dto.timeAttendance;


public class AttendanceLogWSDto {


    private String EnrollNumber;
    private int VerifyMode;
    private int InOutMode;
    private String Date;
    private int WorkCode;

    public String getEnrollNumber() {
        return EnrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
        EnrollNumber = enrollNumber;
    }

    public int getVerifyMode() {
        return VerifyMode;
    }

    public void setVerifyMode(int verifyMode) {
        VerifyMode = verifyMode;
    }

    public int getInOutMode() {
        return InOutMode;
    }

    public void setInOutMode(int inOutMode) {
        InOutMode = inOutMode;
    }

    public int getWorkCode() {
        return WorkCode;
    }

    public void setWorkCode(int workCode) {
        WorkCode = workCode;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

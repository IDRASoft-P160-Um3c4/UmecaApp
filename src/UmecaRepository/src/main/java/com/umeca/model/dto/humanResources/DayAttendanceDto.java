package com.umeca.model.dto.humanResources;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by DeveloperII on 17/11/2015.
 */
public class DayAttendanceDto {

    private Calendar day;
    private List<AttendanceExcelDto> lstAttendance;


    public DayAttendanceDto(Calendar day) {
        this.day = day;
    }


    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public List<AttendanceExcelDto> getLstAttendance() {
        return lstAttendance;
    }

    public void setLstAttendance(List<AttendanceExcelDto> lstAttendance) {
        this.lstAttendance = lstAttendance;
    }

    public String checkIn() {
        String checkInStr = "";
        for (int i = 0; i < this.getLstAttendance().size(); i++) {


            if (this.getLstAttendance().get(i).getWorkCode() == 0) {
                return this.getLstAttendance().get(i).getEventTimeStr();
            }
        }

        return checkInStr;
    }


    public String checkOut() {
        String checkOutStr = "";
        for (int i = 0; i < this.getLstAttendance().size(); i++) {

            if (this.getLstAttendance().get(i).getWorkCode() == 1) {
                return this.getLstAttendance().get(i).getEventTimeStr();
            }
        }
        return checkOutStr;
    }

    public String absence() {
        String absenceStr = "";
        for (int i = 0; i < this.getLstAttendance().size(); i++) {
            if (this.getLstAttendance().get(i).getAbsenceType() != null && this.getLstAttendance().get(i).getApproved() == true) {
                absenceStr = "FJ";
                return absenceStr;
            } else if (this.getLstAttendance().get(i).getAbsenceType() != null && this.getLstAttendance().get(i).getApproved() == false) {
                absenceStr = "FNJ";
                return absenceStr;
            } else if (this.getLstAttendance().get(i).getAbsenceType() != null) {
                absenceStr = "F";
                return absenceStr;
            }
        }
        return absenceStr;
    }

}

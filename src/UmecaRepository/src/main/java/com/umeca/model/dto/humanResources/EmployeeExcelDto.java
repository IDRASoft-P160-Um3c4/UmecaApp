package com.umeca.model.dto.humanResources;

import java.util.List;

/**
 * Created by DeveloperII on 10/11/2015.
 */
public class EmployeeExcelDto {
    Long idEmployee;
    String name;
    String lastNameP;
    String lastNameM;
    String fullName;



    List<AttendanceExcelDto> lstAttendance;

    List<ScheduleDayDto> lstScheduleDay;

    public EmployeeExcelDto(Long idEmployee,
                            String name,
                            String lastNameP,
                            String lastNameM
    ) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.lastNameP = lastNameP;
        this.lastNameM = lastNameM;

        this.fullName = this.name + " " + this.lastNameP + " " + this.lastNameM;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public List<AttendanceExcelDto> getLstAttendance() {
        return lstAttendance;
    }

    public void setLstAttendance(List<AttendanceExcelDto> lstAttendance) {
        this.lstAttendance = lstAttendance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<ScheduleDayDto> getLstScheduleDay() {
        return lstScheduleDay;
    }

    public void setLstScheduleDay(List<ScheduleDayDto> lstScheduleDay) {
        this.lstScheduleDay = lstScheduleDay;
    }
}

package com.umeca.model.dto.humanResources;

import java.util.List;


public class EmployeeExcelDto {
    private Long idEmployee;
    private String name;
    private String lastNameP;
    private String lastNameM;
    private String fullName;



    List<AttendanceExcelDto> lstAttendance;

    List<ScheduleDayDto> lstScheduleDay;

    List<DayAttendanceDto> lstDayAttendance;

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


    public List<DayAttendanceDto> getLstDayAttendance() {
        return lstDayAttendance;
    }

    public void setLstDayAttendance(List<DayAttendanceDto> lstDayAttendance) {
        this.lstDayAttendance = lstDayAttendance;
    }


    public String initSchedule(){
        String initSchedule = "";
        for(ScheduleDayDto scheduleDay : this.lstScheduleDay){
            if(scheduleDay.getDayId() == 1){
                initSchedule += "L:" + scheduleDay.getStart() + " ";
            }
            if(scheduleDay.getDayId() == 2){
                initSchedule += "M:" + scheduleDay.getStart() + " ";
            }
            if(scheduleDay.getDayId() == 3){
                initSchedule += "Mi:" + scheduleDay.getStart() + " ";
            }
            if(scheduleDay.getDayId() == 4){
                initSchedule += "J:" + scheduleDay.getStart() + " ";

            }if(scheduleDay.getDayId() == 5){
                initSchedule += "V:" + scheduleDay.getStart() + " ";

            }if(scheduleDay.getDayId() == 6){
                initSchedule += "S:" + scheduleDay.getStart() + " ";

            }if(scheduleDay.getDayId() == 7){
                initSchedule += "D:" + scheduleDay.getStart() + " ";
            }
        }
        return initSchedule;
    }


    public String endSchedule(){
        String endSchedule = "";

        for(ScheduleDayDto scheduleDay : this.lstScheduleDay){
            if(scheduleDay.getDayId() == 1){
                endSchedule += "L:" + scheduleDay.getEnd();
            }
            if(scheduleDay.getDayId() == 2){
                endSchedule += "M:" + scheduleDay.getEnd() + " ";
            }
            if(scheduleDay.getDayId() == 3){
                endSchedule += "Mi:" + scheduleDay.getEnd() + " ";
            }
            if(scheduleDay.getDayId() == 4){
                endSchedule += "J:" + scheduleDay.getEnd() + " ";

            }if(scheduleDay.getDayId() == 5){
                endSchedule += "V:" + scheduleDay.getEnd() + " ";

            }if(scheduleDay.getDayId() == 6){
                endSchedule += "S:" + scheduleDay.getEnd() + " ";

            }if(scheduleDay.getDayId() == 7){
                endSchedule += "D:" + scheduleDay.getEnd() + " ";
            }
        }

        return endSchedule;
    }
}

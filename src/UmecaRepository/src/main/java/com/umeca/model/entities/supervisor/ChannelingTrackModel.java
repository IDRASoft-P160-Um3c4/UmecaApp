package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.CalendarExt;
import com.umeca.model.shared.Constants;

import java.util.Calendar;

public class ChannelingTrackModel {

    private Long actMonPlanId;
    private String idMP;
    private String imputed;
    private String channelingName;
    private String channelingTypeName;
    private String absenceDateStart;
    private String absenceDateEnd;
    private Boolean isJustified;
    private String comment;
    private String password;
    private Long rescheduleAppointmentId;
    private String dateStart;
    private String timeStart;
    private String timeEnd;
    private Calendar start;
    private Calendar end;

    public ChannelingTrackModel(){

    }

    public ChannelingTrackModel(Long actMonPlanId, String idMP, String name, String lastNameP, String lastNameM,
                                String channelingName, String channelingTypeName,
                                Calendar start, Calendar end, Boolean isJustified, String comment, Long rescheduleAppointmentId,
                                Calendar rescheduleStart, Calendar rescheduleEnd) {
        this.actMonPlanId = actMonPlanId;
        this.idMP = idMP;
        this.comment = comment;
        this.imputed = name + " " + lastNameP + " " + lastNameM;
        this.channelingName = channelingName;
        this.channelingTypeName = channelingTypeName;
        this.absenceDateStart = CalendarExt.calendarToDateString(start);
        this.absenceDateEnd = CalendarExt.calendarToDateString(end);
        this.isJustified = isJustified;
        this.dateStart = CalendarExt.calendarToFormatString(Calendar.getInstance(),Constants.FORMAT_CALENDAR_II);

        this.rescheduleAppointmentId = rescheduleAppointmentId;
        if(rescheduleAppointmentId != null){
            this.dateStart = CalendarExt.calendarToFormatString(rescheduleStart,Constants.FORMAT_CALENDAR_II);
            this.timeStart = CalendarExt.calendarToFormatString(rescheduleStart,Constants.FORMAT_TIME_I);
            this.timeEnd = CalendarExt.calendarToFormatString(rescheduleEnd,Constants.FORMAT_TIME_I);
        }
    }

    public Long getActMonPlanId() {
        return actMonPlanId;
    }

    public void setActMonPlanId(Long actMonPlanId) {
        this.actMonPlanId = actMonPlanId;
    }

    public String getIdMP() {
        return idMP;
    }

    public void setIdMP(String idMP) {
        this.idMP = idMP;
    }

    public String getImputed() {
        return imputed;
    }

    public void setImputed(String imputed) {
        this.imputed = imputed;
    }

    public String getChannelingName() {
        return channelingName;
    }

    public void setChannelingName(String channelingName) {
        this.channelingName = channelingName;
    }

    public String getChannelingTypeName() {
        return channelingTypeName;
    }

    public void setChannelingTypeName(String channelingTypeName) {
        this.channelingTypeName = channelingTypeName;
    }

    public String getAbsenceDateStart() {
        return absenceDateStart;
    }

    public void setAbsenceDateStart(String absenceDateStart) {
        this.absenceDateStart = absenceDateStart;
    }

    public String getAbsenceDateEnd() {
        return absenceDateEnd;
    }

    public void setAbsenceDateEnd(String absenceDateEnd) {
        this.absenceDateEnd = absenceDateEnd;
    }

    public Boolean getIsJustified() {
        return isJustified;
    }

    public void setIsJustified(Boolean isJustified) {
        this.isJustified = isJustified;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRescheduleAppointmentId() {
        return rescheduleAppointmentId;
    }

    public void setRescheduleAppointmentId(Long rescheduleAppointmentId) {
        this.rescheduleAppointmentId = rescheduleAppointmentId;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public boolean IsValidRescheduleDates(){
        if(dateStart == null || timeStart == null || timeEnd == null)
            return false;

        try{
            start = CalendarExt.stringToCalendar(dateStart, Constants.FORMAT_CALENDAR_II);
            end = (Calendar)start.clone();
            Calendar today = CalendarExt.getToday();

            String[] split = timeStart.split(":");
            start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(split[0]));
            start.set(Calendar.MINUTE, Integer.parseInt(split[1]));
            start.set(Calendar.SECOND, 0);
            start.set(Calendar.MILLISECOND, 0);

            split = timeEnd.split(":");
            end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(split[0]));
            end.set(Calendar.MINUTE, Integer.parseInt(split[1]));
            end.set(Calendar.SECOND, 0);
            end.set(Calendar.MILLISECOND, 0);

            if(today.compareTo(start) > 0)
                return false;

            if(start.compareTo(end) > 0)
                return false;

            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
}



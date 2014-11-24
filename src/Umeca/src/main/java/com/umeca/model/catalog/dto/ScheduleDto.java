package com.umeca.model.catalog.dto;

import com.umeca.model.entities.reviewer.Schedule;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 4/06/14
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScheduleDto {
    private Long id;
    private String day;
    private String start;
    private String end;

    public ScheduleDto() {
    }

    public ScheduleDto(Long id, String day, String start, String end) {
        this.id = id;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public ScheduleDto dtoSchedule (Schedule schedule){
        this.id=schedule.getId();
        this.day = schedule.getDay();
        this.start = schedule.getStart();
        this.end = schedule.getEnd();
        return this;
    }

    public String scheduleString(Schedule schedule){
        String r = "";
        r = schedule.getDay()+" "+schedule.getStart()+"-"+schedule.getEnd();
        return  r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

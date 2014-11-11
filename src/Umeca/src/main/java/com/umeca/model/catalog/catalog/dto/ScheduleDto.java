package com.umeca.model.catalog.catalog.dto;

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

    public ScheduleDto dtoSchedule (Schedule schedule){
        this.id=schedule.getId();
        this.day = schedule.getDay();
        this.start = schedule.getStart();
        this.end = schedule.getEnd();
        return this;
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

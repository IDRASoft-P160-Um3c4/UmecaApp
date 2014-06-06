package com.umeca.model.entities.reviewer.dto;

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
    private DayDto day;
    private String start;
    private String end;

    public ScheduleDto dtoSchedule (Schedule schedule){
        this.id=schedule.getId();
        DayDto dto= new DayDto();
        this.day = dto.dtoDay(schedule.getDay());
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

    public DayDto getDay() {
        return day;
    }

    public void setDay(DayDto day) {
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

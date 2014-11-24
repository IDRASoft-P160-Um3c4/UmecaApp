package com.umeca.model.catalog.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 24/11/14
 * Time: 01:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScheduleLogDto {
    private String name;
    private List<ScheduleDto> sch;

    public ScheduleLogDto(String name, List<ScheduleDto> sch) {
        this.name = name;
        this.sch = sch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduleDto> getSch() {
        return sch;
    }

    public void setSch(List<ScheduleDto> sch) {
        this.sch = sch;
    }
}

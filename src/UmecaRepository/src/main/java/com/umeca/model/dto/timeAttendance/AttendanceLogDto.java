package com.umeca.model.dto.timeAttendance;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

public class AttendanceLogDto implements EntityGrid {

    @Override
    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    private Long id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String name;

    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    private String eventDate;

    public boolean isJustified() {
        return justified;
    }

    public void setJustified(boolean justified) {
        this.justified = justified;
    }

    private boolean justified;

    public AttendanceLogDto(Long id, String eventDate, boolean justified, String name){
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.justified = justified;
    }
}
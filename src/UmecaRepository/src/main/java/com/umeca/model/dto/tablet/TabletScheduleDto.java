package com.umeca.model.dto.tablet;

public class TabletScheduleDto {

    public TabletScheduleDto() {}

    public TabletScheduleDto(Long id, String day, String start, String end) {
        this.id = id;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    private Long id;
    private String day;
    private String start;
    private String end;

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

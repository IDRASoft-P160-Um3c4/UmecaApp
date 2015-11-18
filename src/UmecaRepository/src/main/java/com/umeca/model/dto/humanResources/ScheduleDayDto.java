package com.umeca.model.dto.humanResources;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;
import com.umeca.model.entities.humanReources.Incapacity;

import java.util.Date;

public class ScheduleDayDto implements EntityGrid {

    private Long id;
    private String name;
    private Boolean isSel;
    private Integer dayId;
    private String start;
    private String end;
    private Integer startI;
    private Integer endI;
    private Long idEmployee;

    public ScheduleDayDto() {

    }

    //upsert
    public ScheduleDayDto(Long id, String name) {
        this.id = id;
        this.dayId = new Integer(id.intValue());
        this.name = name;
    }

    public ScheduleDayDto(Long id, Integer dayId, Integer start, Integer end) {
        this.id = id;
        this.dayId = dayId;

        if (start != null) {
            this.start = convIntToStr(start);
        }

        if (end != null) {
            this.end = convIntToStr(end);
        }
    }


    public ScheduleDayDto(Long id, Integer dayId, Integer start, Integer end, Long idEmployee){
        this.id = id;
        this.dayId = dayId;
        if (start != null) {
            this.start = convIntToStr(start);
        }

        if (end != null) {
            this.end = convIntToStr(end);
        }

        this.idEmployee  = idEmployee;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
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

    public Integer getStartI() {
        return startI;
    }

    public void setStartI(Integer startI) {
        this.startI = startI;
    }

    public Integer getEndI() {
        return endI;
    }

    public void setEndI(Integer endI) {
        this.endI = endI;
    }

    public Integer convStrToInt(String time) {
        Integer iTime = 0;

        String[] arr = time.split(":");
        iTime += Integer.valueOf(arr[0]) * 3600; //hora
        iTime += Integer.valueOf(arr[1]) * 60; //min
        iTime += Integer.valueOf(arr[2]); //seg

        return iTime;
    }

    public String convIntToStr(Integer time) {
        String sTime = "";

        //calcula hora
        int hour = time / 3600;
        time -= (hour * 3600);
        //calcula minutp
        int minute = time / 60;
        time = time - (minute * 60);
        //calcula seg
        int sec = time;

        String h = "";
        String m = "";
        String s = "";

        if (hour < 10 && hour > 0)
            h = "0" + Integer.toString(hour);
        else if (!(hour > 1))
            h = "00";
        else
            h = Integer.toString(hour);

        if (minute < 10 && minute > 0)
            m = "0" + Integer.toString(minute);
        else if (!(minute > 1))
            m = "00";
        else
            m = Integer.toString(minute);

        if (sec < 10 && sec > 0)
            s = "0" + Integer.toString(sec);
        else if (!(sec > 1))
            s = "00";
        else
            s = Integer.toString(sec);

        sTime = h + ":" + m + ":" + s;

        return sTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsSel() {
        return isSel;
    }

    public void setIsSel(Boolean isSel) {
        this.isSel = isSel;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }
}

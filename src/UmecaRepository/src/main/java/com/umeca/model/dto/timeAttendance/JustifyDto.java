package com.umeca.model.dto.timeAttendance;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created by Administrator on 10/13/2015.
 */
public class JustifyDto implements EntityGrid {
    private Long id;
    private String name;
    private String time;
    private String date;
    private Boolean justified;
    private String comment;
    private String password;
    private Long idAttendanceLog;

    public void setId(Long id) { this.id = id; }
    @Override
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getJustified() {
        return justified;
    }

    public void setJustified(Boolean justified) {
        this.justified = justified;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Long getIdAttendanceLog() { return idAttendanceLog; }

    public void setIdAttendanceLog(Long idAttendanceLog) { this.idAttendanceLog = idAttendanceLog; }

    public JustifyDto(Long id, String name, String time, String date, Boolean justified){
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.justified = justified;
    }

    public JustifyDto() { }
}

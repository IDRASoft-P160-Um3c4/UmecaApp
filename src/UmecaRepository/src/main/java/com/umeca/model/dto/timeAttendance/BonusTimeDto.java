package com.umeca.model.dto.timeAttendance;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created by Administrator on 10/21/2015.
 */
public class BonusTimeDto implements EntityGrid {
    @Override
    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }


    private Long id;
    private Long idAttendancelog;
    private String name;
    private String eventDate;
    private String eventTime;
    private double bonusTime;
    private boolean approved;
    private String password;
    private String comment;
    private Long idUser;

    public BonusTimeDto(){}

    public BonusTimeDto(Long id, String name, String eventDate, String eventTime, double bonusTime, boolean approved){
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.bonusTime = bonusTime;
        this.approved = approved;
    }

    public Long getIdAttendancelog() {
        return idAttendancelog;
    }

    public void setIdAttendancelog(Long idAttendancelog) {
        this.idAttendancelog = idAttendancelog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public double getBonusTime() {
        return bonusTime;
    }

    public void setBonusTime(double bonusTime) {
        this.bonusTime = bonusTime;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}

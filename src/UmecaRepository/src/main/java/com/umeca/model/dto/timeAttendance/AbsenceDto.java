package com.umeca.model.dto.timeAttendance;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created by Administrator on 10/29/2015.
 */
public class AbsenceDto implements EntityGrid {

    private Long id;
    private String name;
    private String date;
    private String type;
    private String reason;
    private double value;
    private boolean approved;
    private String comment;
    private String password;
    private Long idUser;
    private Long idEmployee;

    public AbsenceDto(){}
    public AbsenceDto(Long id, String name, String date, String type, String reason, double value){
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.reason = reason;
        this.value = value;
    }

    @Override
    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }
}

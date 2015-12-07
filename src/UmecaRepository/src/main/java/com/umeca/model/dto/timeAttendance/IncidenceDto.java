package com.umeca.model.dto.timeAttendance;

import com.umeca.infrastructure.jqgrid.model.EntityGrid;

/**
 * Created by Administrator on 10/29/2015.
 */
public class IncidenceDto implements EntityGrid {

    private Long id;
    private String name;
    private String date;
    private String reason;
    private String commentReason;
    private String comment;
    private boolean approved;
    private String password;
    private Long idUser;
    private Long idEmployee;

    public IncidenceDto(){}

    public IncidenceDto(Long id, String name, String date, String reason){
        this.id = id;
        this.name = name;
        this.date = date;
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCommentReason() {
        return commentReason;
    }

    public void setCommentReason(String commentReason) {
        this.commentReason = commentReason;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

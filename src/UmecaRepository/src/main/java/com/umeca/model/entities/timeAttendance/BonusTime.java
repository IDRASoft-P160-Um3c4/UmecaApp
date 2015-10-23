package com.umeca.model.entities.timeAttendance;

import com.umeca.model.entities.account.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 10/21/2015.
 */
@Entity
@Table(name = "bonustime")
public class BonusTime implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_bonustime", nullable = false)
    private Long id;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "Comment")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User responsible;

    @Column(name = "id_attendancelog")
    private Long idAttendanceLog;

    @OneToOne
    @JoinColumn(name = "id_attendancelog", insertable = false, updatable = false)
    private AttendanceLog attendanceLog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public Long getIdAttendanceLog() {
        return idAttendanceLog;
    }

    public void setIdAttendanceLog(Long idAttendanceLog) {
        this.idAttendanceLog = idAttendanceLog;
    }

    public AttendanceLog getAttendanceLog() {
        return attendanceLog;
    }

    public void setAttendanceLog(AttendanceLog attendanceLog) {
        this.attendanceLog = attendanceLog;
    }
}


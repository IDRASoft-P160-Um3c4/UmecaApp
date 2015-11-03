package com.umeca.model.entities.timeAttendance;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.Employee;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 10/29/2015.
 */
@Entity
@Table(name = "Incidence")
public class Incidence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_incidence", nullable = false)
    private Long id;

    @Column(name = "incidence_date")
    private Date eventDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "commentReason")
    private String commentReason;

    @Column(name = "comment")
    private String comment;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "is_closed")
    private boolean close;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User responsible;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }
}

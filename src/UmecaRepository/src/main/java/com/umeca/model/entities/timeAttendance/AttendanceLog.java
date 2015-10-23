package com.umeca.model.entities.timeAttendance;

import com.umeca.model.entities.humanReources.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Administrator on 4/22/2015.
 */
@Entity
@Table(name = "attendancelog")
public class AttendanceLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_attendancelog", nullable = false)
    private long id;

    @OneToOne( cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "attendanceLog")
    private DelayJustification delayJustification;

    @Column(name = "eventtime", nullable = false)
    private Calendar eventTime;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;
    @Column(name = "workcode", nullable = false)
    private short workCode;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DelayJustification getDelayJustification() {
        return delayJustification;
    }

    public void setDelayJustification(DelayJustification delayJustification) {
        this.delayJustification = delayJustification;
    }

    public Calendar getEventTime() {
        return eventTime;
    }

    public void setEventTime(Calendar eventTime) {
        this.eventTime = eventTime;
    }

    public short getWorkCode() {
        return workCode;
    }

    public void setWorkCode(short workCode) {
        this.workCode = workCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
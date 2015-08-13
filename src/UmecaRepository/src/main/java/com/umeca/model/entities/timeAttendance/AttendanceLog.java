package com.umeca.model.entities.timeAttendance;

import com.umeca.model.entities.humanReources.Employee;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 4/22/2015.
 */
@Entity
@Table(name = "attendancelog")
public class AttendanceLog {

    @Id
    @Column(name = "id_attendancelog", nullable = false)
    private long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @Column(name = "workcode", nullable = false)
    private short workCode;

    @Column(name = "eventtime", nullable = false)
    private Date eventTime;
}
package com.umeca.model.entities.timeAttendance;

import javax.persistence.*;

/**
 * Created by Administrator on 10/28/2015.
 */
@Entity
@Table(name = "absence_detail")
public class AbsenceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_absence_detail", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_attendancelog", nullable = false)
    private AttendanceLog attendanceLog;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_absence", nullable = false)
    private Absence absence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttendanceLog getAttendanceLog() {
        return attendanceLog;
    }

    public void setAttendanceLog(AttendanceLog attendanceLog) {
        this.attendanceLog = attendanceLog;
    }

    public Absence getAbsence() {
        return absence;
    }

    public void setAbsence(Absence absence) {
        this.absence = absence;
    }
}

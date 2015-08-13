package com.umeca.model.entities.timeAttendance;

import com.umeca.model.entities.humanReources.Employee;

import javax.persistence.*;

/**
 * Created by Administrator on 4/22/2015.
 */
@Entity
@Table(name = "fingerprints")
public class FingerPrint {
    @Id
    @GeneratedValue
    @Column(name = "id_fingerprint", nullable = false)
    private long id;

    @Column(name = "finger", nullable = false)
    private short finger;

    @Column(name = "data", nullable = false, length = 2048)
    private String data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getFinger() {
        return finger;
    }

    public void setFinger(short finger) {
        this.finger = finger;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
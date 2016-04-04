package com.umeca.model.entities.humanReources;

import javax.persistence.*;

/**
 * Created by Administrator on 10/23/2015.
 */
@Entity
@Table(name = "employee_fingerprint")
public class EmployeeFingerPrint {
    @Id
    @GeneratedValue
    @Column(name = "id_employee_fingerprint")
    private Long id;

    @Column(name = "finger")
    private short finger;

    @Column(name = "data", length = 2048)
    private String data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setData(String fingerprint) {
        this.data = fingerprint;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

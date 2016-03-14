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
    private int finger;

    @Column(name = "fingerprint", length = 2048)
    private String fingerprint;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFinger() {
        return finger;
    }

    public void setFinger(int finger) {
        this.finger = finger;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

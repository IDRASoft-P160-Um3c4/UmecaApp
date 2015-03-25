package com.umeca.model.entities.director.minutes;

import com.umeca.model.entities.humanReources.Employee;

import javax.persistence.*;

@Entity
@Table(name = "assistant")
public class Assistant {

    @Id
    @GeneratedValue
    @Column(name = "id_assistant")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_minute")
    private Minute minute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Minute getMinute() {
        return minute;
    }

    public void setMinute(Minute minute) {
        this.minute = minute;
    }
}

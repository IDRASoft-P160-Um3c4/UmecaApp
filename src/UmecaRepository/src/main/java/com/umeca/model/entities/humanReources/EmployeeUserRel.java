package com.umeca.model.entities.humanReources;

import com.umeca.model.entities.account.User;

import javax.persistence.*;

@Entity
@Table(name = "employee_user_rel")
public class EmployeeUserRel {

    @Id
    @GeneratedValue
    @Column(name = "id_employee_user_rel")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

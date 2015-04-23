package com.umeca.model.entities.humanReources;

import com.umeca.model.catalog.District;
import com.umeca.model.catalog.RegisterType;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.catalog.UmecaPost;
import com.umeca.model.entities.account.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "umeca_job")
public class UmecaJob {

    @Id
    @GeneratedValue
    @Column(name = "id_umeca_job")
    private Long id;

    @Column(name = "name_head")
    private String nameHead;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_umeca_post")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_district")
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameHead() {
        return nameHead;
    }

    public void setNameHead(String nameHead) {
        this.nameHead = nameHead;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

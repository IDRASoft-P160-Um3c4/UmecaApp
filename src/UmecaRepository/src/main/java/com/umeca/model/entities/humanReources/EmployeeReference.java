package com.umeca.model.entities.humanReources;

import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

@Entity
@Table(name = "employee_reference")
public class EmployeeReference {
    @Id
    @GeneratedValue
    @Column(name = "id_employee_reference")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "phone")
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_relationship")
    private Relationship relationship;

    @Column(name = "spec_relationship")
    private String specRelationship;

    @Column(name = "time_ago")
    private String timeAgo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getSpecRelationship() {
        return specRelationship;
    }

    public void setSpecRelationship(String specRelationship) {
        this.specRelationship = specRelationship;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

package com.umeca.model.entities.humanReources;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee_schedule")
public class EmployeeSchedule {

    @Id
    @GeneratedValue
    @Column(name = "id_employee_schedule")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_obsolete")
    private Boolean isObsolete;

    @OneToMany(mappedBy = "employeeSchedule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ScheduleDay> days;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(Boolean isObsolete) {
        this.isObsolete = isObsolete;
    }

    public List<ScheduleDay> getDays() {
        return days;
    }

    public void setDays(List<ScheduleDay> days) {
        this.days = days;
    }
}

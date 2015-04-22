package com.umeca.model.entities.humanReources;

import javax.persistence.*;

@Entity
@Table(name = "schedule_days")
public class ScheduleDay {

    @Id
    @GeneratedValue
    @Column(name = "id_schedule_day")
    private Long id;

    @Column(name = "day_id")
    private Integer dayId;

    @Column(name = "name")
    private String name;

    //los valores de hora de entrada y hora de salida se guardaran como enteros con valores desde 0 a 86400 (60eg * 60min * 24hrs)
    @Column(name = "start")
    private Integer start;

    @Column(name = "end")
    private Integer end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee_schedule")
    private EmployeeSchedule employeeSchedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public EmployeeSchedule getEmployeeSchedule() {
        return employeeSchedule;
    }

    public void setEmployeeSchedule(EmployeeSchedule employeeSchedule) {
        this.employeeSchedule = employeeSchedule;
    }
}

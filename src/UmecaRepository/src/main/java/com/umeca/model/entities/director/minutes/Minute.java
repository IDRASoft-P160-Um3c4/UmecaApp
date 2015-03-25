package com.umeca.model.entities.director.minutes;

import com.umeca.model.entities.humanReources.Employee;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "minute")
public class Minute {

    @Id
    @GeneratedValue
    @Column(name = "id_minute")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "agenda")
    private String agenda;

    @Column(name = "minute_date")
    private Date minuteDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee attendant;

    @Column(name = "place")
    private String place;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @OneToMany(mappedBy = "employee")
    private List<Assistant> assistants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Date getMinuteDate() {
        return minuteDate;
    }

    public void setMinuteDate(Date minuteDate) {
        this.minuteDate = minuteDate;
    }

    public Employee getAttendant() {
        return attendant;
    }

    public void setAttendant(Employee attendant) {
        this.attendant = attendant;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(List<Assistant> assistants) {
        this.assistants = assistants;
    }
}

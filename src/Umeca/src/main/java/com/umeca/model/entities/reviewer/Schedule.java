package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.DayWeek;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue
    @Column(name="id_schedule")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_day", nullable = false)
    private DayWeek day;

    @Column(name="start", nullable = true)
    private Time start;

    @Column(name="end", nullable = true)
    private Time end;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_domicile", nullable = true)
    private Domicile domicile;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_job", nullable = true)
    private Job job;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_school", nullable = true)
    private School school;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayWeek getDay() {
        return day;
    }

    public void setDay(DayWeek day) {
        this.day = day;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public Domicile getDomicile() {
        return domicile;
    }

    public void setDomicile(Domicile domicile) {
        this.domicile = domicile;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}

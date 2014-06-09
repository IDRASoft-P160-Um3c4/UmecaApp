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

    public Schedule() {
    }

    @Id
    @GeneratedValue
    @Column(name="id_schedule")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_day", nullable = false)
    private DayWeek day;

    @Column(name="start", nullable = true, length = 5)
    private String start;

    @Column(name="end", nullable = true, length = 5)
    private String end;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_domicile", nullable = true)
    private Domicile domicile;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_job", nullable = true)
    private Job job;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_school", nullable = true)
    private School school;

    @Transient
    private String $$hashKey;

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

    public String get$$hashKey() {
        return $$hashKey;
    }

    public void set$$hashKey(String $$hashKey) {
        this.$$hashKey = $$hashKey;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.DayWeek;
import com.umeca.model.entities.supervisor.FramingActivity;
import com.umeca.model.entities.supervisor.FramingAddress;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 10:33 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "schedule")
public class Schedule {

    public Schedule() {
    }

    public Schedule(String day, String start, String end) {
        this.day=day;
        this.start=start;
        this.end=end;
    }

    @Id
    @GeneratedValue
    @Column(name = "id_schedule")
    private Long id;

    @Column(name = "day", nullable = false, length = 50)
    private String day;

    @Column(name = "start", nullable = true, length = 5)
    private String start;

    @Column(name = "end", nullable = true, length = 5)
    private String end;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_imputed_home", nullable = true)
    private ImputedHome imputedHome;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_job", nullable = true)
    private Job job;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_school", nullable = true)
    private School school;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_framing_activity", nullable = true)
    private FramingActivity framingActivity;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_framing_address", nullable = true)
    private FramingAddress framingAddress;

    @Transient
    private String $$hashKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ImputedHome getImputedHome() {
        return imputedHome;
    }

    public void setImputedHome(ImputedHome imputedHome) {
        this.imputedHome = imputedHome;
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

    public FramingActivity getFramingActivity() {
        return framingActivity;
    }

    public void setFramingActivity(FramingActivity framingActivity) {
        this.framingActivity = framingActivity;
    }

    public FramingAddress getFramingAddress() {
        return framingAddress;
    }

    public void setFramingAddress(FramingAddress framingAddress) {
        this.framingAddress = framingAddress;
    }
}

package com.umeca.model.entities.reviewer;

import com.umeca.model.Catalog.AcademicYear;
import com.umeca.model.Catalog.RegisterType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="school")
public class School {

    @Id
    @GeneratedValue
    @Column(name="id_school")
    private Long id;

    @Column(name="name", length = 150, nullable = false)
    private String name;

    @Column(name="phone", length = 25, nullable = true)
    private String phone;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_academic_year", nullable = false)
    private AcademicYear academicYear;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_register_type", nullable = false)
    private RegisterType registerType;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_schedule", nullable = false)
    private Schedule schedule;

    @Column(name="start", nullable = false)
    private Date start;

    @Column(name="end", nullable = true)
    private Date end;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_domicile", nullable = true)
    private Domicile domicile;

    @Column(name="reason_change", length = 1000, nullable = true)
    private String reasonChange;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public void setRegisterType(RegisterType registerType) {
        this.registerType = registerType;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Domicile getDomicile() {
        return domicile;
    }

    public void setDomicile(Domicile domicile) {
        this.domicile = domicile;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}

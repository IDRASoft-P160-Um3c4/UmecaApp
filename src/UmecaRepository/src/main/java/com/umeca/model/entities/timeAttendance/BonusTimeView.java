package com.umeca.model.entities.timeAttendance;

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 10/21/2015.
 */
@Entity
@Subselect("select\n" +
        "\tid_attendancelog,\n" +
        "\tname,\n" +
        "\teventtime,\n" +
        "\tcheckout,\n" +
        "\touttime,\n" +
        "\ttolerance,\n" +
        "\tovertime approved,\n" +
        "\tconcat('', sec_to_time(outtime)) textOut,\n" +
        "\tconcat('', time(eventtime)) textOu,\n" +
        "\tconcat('', date(eventtime)) textDt,\n" +
        "\tfloor((checkout - outtime) / (60 * 60)) bonustime,\n" +
        "\tid_employee,\n" +
        "\tworkcode\n" +
        "from (\n" +
        "\tselect \n" +
        "\t\ta.id_attendancelog,\n" +
        "\t\tconcat(e.name, ' ', coalesce(concat(e.last_name_p,' '), ''), coalesce(concat('', e.last_name_m), '')) \"name\",\n" +
        "\t\ta.eventtime,\n" +
        "\t\ttime_to_sec(eventtime) checkout,\n" +
        "\t\tcoalesce((select sd.end from schedule_days sd where sd.day_id = weekday(a.eventtime) + 1 and sd.id_employee_schedule = e.id_employee_schedule), 23 * 60 * 60 + 59 * 60 + 59) \"outtime\",\n" +
        "\t\tcast(time_to_sec(coalesce((select value_setting from system_setting where group_setting = 'ATTENDANCE' and key_setting = 'ArrivalTolerance'), '00:00:00')) as int) tolerance,\n" +
        "\t\t(select count(*) > 0 from bonustime bt where bt.id_attendancelog = a.id_attendancelog) overtime,\n" +
        "\t\ta.id_employee,\n" +
        "\t\ta.workcode\n" +
        "\tfrom \n" +
        "\t\tattendancelog a\n" +
        "\t\tinner join employee e\n" +
        "\t\ton a.id_employee = e.id_employee\n" +
        "\t\tand a.workcode = 1\n" +
        "\t) attendancelogview where floor((checkout - outtime) / (60 * 60)) > 0 and overtime = 0")

//0—Check-In (default value) 1—Check-Out 2—Break-Out 3—Break-In 4—OT-In 5—OT-Out
public class BonusTimeView {
    @Id
    @Column(name = "id_attendancelog")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "eventdate")
    private Date eventtime;

    @Column(name = "checkoout")
    private int checkout;

    @Column(name = "outtime")
    private int outtime;

    @Column(name = "tolerance")
    private int tolerance;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "textOut")
    private String departureText;

    @Column(name = "textDt")
    private String eventDate;

    @Column(name = "textOu")
    private String eventTime;

    @Column(name = "bonustime")
    private Double bonusTime;

    @Column(name = "id_ employee")
    private Long idEmployee;

    @Column(name = "workcode")
    private int workCode;

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

    public Date getEventtime() {
        return eventtime;
    }

    public void setEventtime(Date eventtime) {
        this.eventtime = eventtime;
    }

    public int getCheckout() {
        return checkout;
    }

    public void setCheckout(int checkout) {
        this.checkout = checkout;
    }

    public int getOuttime() {
        return outtime;
    }

    public void setOuttime(int outtime) {
        this.outtime = outtime;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getDepartureText() {
        return departureText;
    }

    public void setDepartureText(String departureText) {
        this.departureText = departureText;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Double getBonusTime() {
        return bonusTime;
    }

    public void setBonusTime(Double bonusTime) {
        this.bonusTime = bonusTime;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getWorkCode() {
        return workCode;
    }

    public void setWorkCode(int workCode) {
        this.workCode = workCode;
    }
}

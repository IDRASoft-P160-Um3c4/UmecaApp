package com.umeca.model.entities.timeAttendance;

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Subselect("select\n" +
        "\tid_attendancelog,\n" +
        "\tname,\n" +
        "\tconcat('', eventtime) eventtime,\n" +
        "\tcheckin,\n" +
        "\tentrance,\n" +
        "\ttolerance,\n" +
        "\tabsence,\n" +
        "\tjustified,\n" +
        "\tconcat('', sec_to_time(entrance)) textCh,\n" +
        "\tconcat('', time(eventtime)) textIn,\n" +
        "\tconcat('', date(eventtime)) textDt,\n" +
        "\tid_employee,\n" +
        "\tworkcode\n" +
        "from (\n" +
        "\tselect \n" +
        "\t\ta.id_attendancelog,\n" +
        "\t\tconcat(e.name, ' ', coalesce(concat(e.last_name_p,' '), ''), coalesce(concat('', e.last_name_m), '')) \"name\",\n" +
        "\t\ta.eventtime,\n" +
        "\t\ttime_to_sec(eventtime) checkin,\n" +
        "\t\tcoalesce((select sd.start from schedule_days sd where sd.day_id = weekday(a.eventtime) + 1 and sd.id_employee_schedule = e.id_employee_schedule), 23 * 60 * 60 + 59 * 60 + 59) \"entrance\",\n" +
        "\t\tcast(time_to_sec(coalesce((select value_setting from system_setting where group_setting = 'ATTENDANCE' and key_setting = 'ArrivalTolerance'), '00:00:00')) as signed) tolerance,\n" +
        "\t\tcast(time_to_sec(coalesce((select value_setting from system_setting where group_setting = 'ATTENDANCE' and key_setting = 'AbsenceTime'), '00:00:00')) as signed) absence,\n" +
        "\t\t(select count(*) > 0 from delay_justification dj where dj.id_attendancelog = a.id_attendancelog) justified,\n" +
        "\t\ta.id_employee,\n" +
        "\t\ta.workcode\n" +
        "\tfrom \n" +
        "\t\tattendancelog a\n" +
        "\t\tinner join employee e\n" +
        "\t\ton a.id_employee = e.id_employee\n" +
        "\t\tand a.workcode = 0\n" +
        ") attendancelogview where justified = 0 and checkin > (entrance + tolerance)")// and checkin <= (entrance + absence)")
public class DelayJustificationView implements Serializable {
    @Id
    @Column(name = "id_attendancelog")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "eventtime")
    private String eventDate;

    @Column(name = "checkin")
    private int checkIn;

    @Column(name = "entrance")
    private int entrance;

    @Column(name = "tolerance")
    private int tolerance;

    @Column(name = "justified")
    private boolean justified;

    @Column(name = "textCh")
    private String textCh;

    @Column(name="textIn")
    private String textIn;

    @Column(name = "textDt")
    private String textDt;

    @Column(name = "workcode")
    private int workCode;

    @Column(name = "id_employee")
    private Long idEmployee;

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isJustified() {
        return justified;
    }

    public void setJustified(boolean justified) {
        this.justified = justified;
    }

    public int getWorkCode() {
        return workCode;
    }

    public void setWorkCode(int workCode) {
        this.workCode = workCode;
    }

    public String getTextCh() { return textCh; }

    public void setTextCh(String textCh) { this.textCh = textCh; }

    public String getTextIn() { return textIn; }

    public void setTextIn(String textIn) { this.textIn = textIn; }

    public String getTextDt() { return textDt; }

    public void setTextDt(String textDt) { this.textDt = textDt; }
}

package com.umeca.model.entities.timeAttendance;

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 10/29/2015.
 */
@Entity
@Subselect("select\n" +
        "\ta.id_absence,\n" +
        "\tconcat(e.name, ' ', coalesce(concat(e.last_name_p,' '), ''), coalesce(concat('', e.last_name_m), '')) \"name\",\n" +
        "\tconcat('', a.absence_date) date,\n" +
        "\tcase a.`type` when 0 then 'Generada' else 'Registrada' end `type`,\n" +
        "\ta.reason,\n" +
        "\ta.value\n" +
        "from \n" +
        "\tabsence a inner join employee e on a.id_employee = e.id_employee\n" +
        "where\n" +
        "\ta.isClosed = 0")
public class AbsenceView {
    @Id
    @Column(name = "id_absence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;

    @Column(name = "type")
    private String type;

    @Column(name = "reason")
    private String reason;

    @Column(name = "value")
    private double value;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

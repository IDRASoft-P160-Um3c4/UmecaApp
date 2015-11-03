package com.umeca.model.entities.timeAttendance;

/**
 * Created by Administrator on 10/30/2015.
 */

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Subselect("select \n" +
        "\tid_incidence,\n" +
        "\tconcat(e.name, ' ', coalesce(concat(e.last_name_p,' '), ''), coalesce(concat('', e.last_name_m), '')) name,\n" +
        "\tconcat('', date(i.incidence_date)) incidence_time,\n" +
        "\ti.reason\n" +
        "from \n" +
        "\tincidence i \n" +
        "\tinner join employee e \n" +
        "\ton i.id_employee = e.id_employee\n" +
        "where\n" +
        "\ti.is_closed = 0")
public class IncidenceView {
    @Id
    @Column(name = "id_incidence")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "incidence_time")
    private String date;

    @Column(name = "reason")
    private String reason;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

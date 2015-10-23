package com.umeca.model.entities.timeAttendance;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.humanReources.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 10/23/2015.
 */
@Entity
@Table(name = "absence")
public class Absence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_absence", nullable = false)
    private Long id;

    @Column(name = "absence_date")
    private Date eventDate;

    @Column(name = "comment")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User responsible;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;
}

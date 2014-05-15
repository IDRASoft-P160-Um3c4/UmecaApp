package com.umeca.model.entities.reviewer;

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

    @Column(name="monday", nullable = false)
    private Boolean monday;

    @Column(name="monday_start",  nullable = true)
    private Time mondayStart;

    @Column(name="monday_end", nullable = true)
    private Time mondayEnd;

    @Column(name="tuesday", nullable = false)
    private Boolean tuesday;

    @Column(name="tuesday_start", nullable = true)
    private Time tuesdayStart;

    @Column(name="tuesdayEnd", nullable = true)
    private Time tuesdayEnd;

    @Column(name="wednesday", nullable = false)
    private Boolean wednesday;

    @Column(name="wednesday_start", nullable = true)
    private Time wednesdayStart;

    @Column(name="wednesday_end", nullable = true)
    private Time wednesdayEnd;

    @Column(name="thursday", nullable = false)
    private Boolean thursday;

    @Column(name="thursday_start", nullable = true)
    private Time thursdayStart;

    @Column(name="thursday_end", nullable = true)
    private Time thursdayEnd;

    @Column(name="friday", nullable = false)
    private Boolean friday;

    @Column(name="friday_start", nullable = true)
    private Time fridayStart;

    @Column(name="friay_end", nullable = true)
    private Time fridayEnd;

    @Column(name="saturday", nullable = false)
    private Boolean saturday;

    @Column(name="saturday_start", nullable = true)
    private Time saturdayStart;

    @Column(name="saturday_end", nullable = true)
    private Time saturdayEnd;

    @Column(name="sunday", nullable = false)
    private Boolean sunday;

    @Column(name="sunday_start", nullable = true)
    private Time sundayStart;

    @Column(name="sunday_end", nullable = true)
    private Time sundayEnd;

}

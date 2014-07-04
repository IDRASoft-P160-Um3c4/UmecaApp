package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Election;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 03:43 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "previous_criminal_proceeding")
public class PreviousCriminalProceeding {

    @Id
    @GeneratedValue
    @Column(name = "id_previous_criminal_proceeding")
    private Long id;

    @Column(name = "first_proceeding", length = 255, nullable = false)
    private String firstProceeding;

    @Column(name = "open_process_number", nullable = false)
    private Integer openProcessNumber;

    @Column(name = "number_convictions", nullable = false)
    private Integer numberConvictions;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_comply_measures", nullable = false)
    private Election complyPM;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_comply_scpp", nullable = false)
    private Election complyCSPP;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_comply_process_above", nullable = false)
    private Election complyProcessAbove;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = true)
    private Meeting meeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstProceeding() {
        return firstProceeding;
    }

    public void setFirstProceeding(String firstProceeding) {
        this.firstProceeding = firstProceeding;
    }

    public Integer getOpenProcessNumber() {
        return openProcessNumber;
    }

    public void setOpenProcessNumber(Integer openProcessNumber) {
        this.openProcessNumber = openProcessNumber;
    }

    public Integer getNumberConvictions() {
        return numberConvictions;
    }

    public void setNumberConvictions(Integer numberConvictions) {
        this.numberConvictions = numberConvictions;
    }

    public Election getComplyPM() {
        return complyPM;
    }

    public void setComplyPM(Election complyPM) {
        this.complyPM = complyPM;
    }

    public Election getComplyCSPP() {
        return complyCSPP;
    }

    public void setComplyCSPP(Election complyCSPP) {
        this.complyCSPP = complyCSPP;
    }

    public Election getComplyProcessAbove() {
        return complyProcessAbove;
    }

    public void setComplyProcessAbove(Election complyProcessAbove) {
        this.complyProcessAbove = complyProcessAbove;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}

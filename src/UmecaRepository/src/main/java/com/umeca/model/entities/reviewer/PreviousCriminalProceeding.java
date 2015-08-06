package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.ElectionNotApply;

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

    @Column(name = "first_proceeding", length = 255, nullable = true)
    private String firstProceeding;

    @Column(name = "open_process_number", nullable = true)
    private Integer openProcessNumber;

    @Column(name = "number_convictions", nullable = true)
    private Integer numberConvictions;

    @Column(name="specification_open_process", length = 255, nullable = true)
    private String specificationOpenProcess;

    @Column(name="specification_number_convictions", length = 255, nullable = true)
    private String specificationNumberConvictions;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_comply_measures", nullable = true)
    private ElectionNotApply complyPM;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_comply_scpp", nullable = true)
    private ElectionNotApply complyCSPP;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_comply_process_above", nullable = true)
    private ElectionNotApply complyProcessAbove;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = true)
    private Meeting meeting;

    @Column(name="warrant", length = 255, nullable = true)
    private String warrant;

    @Column(name="platform_mexico", length = 255, nullable = true)
    private String platformMexico;

    @Column(name="afis", length = 255, nullable = true)
    private String afis;

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

    public ElectionNotApply getComplyPM() {
        return complyPM;
    }

    public void setComplyPM(ElectionNotApply complyPM) {
        this.complyPM = complyPM;
    }

    public ElectionNotApply getComplyCSPP() {
        return complyCSPP;
    }

    public void setComplyCSPP(ElectionNotApply complyCSPP) {
        this.complyCSPP = complyCSPP;
    }

    public ElectionNotApply getComplyProcessAbove() {
        return complyProcessAbove;
    }

    public void setComplyProcessAbove(ElectionNotApply complyProcessAbove) {
        this.complyProcessAbove = complyProcessAbove;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getSpecificationOpenProcess() {
        return specificationOpenProcess;
    }

    public void setSpecificationOpenProcess(String specificationOpenProcess) {
        this.specificationOpenProcess = specificationOpenProcess;
    }

    public String getSpecificationNumberConvictions() {
        return specificationNumberConvictions;
    }

    public void setSpecificationNumberConvictions(String specificationNumberConvictions) {
        this.specificationNumberConvictions = specificationNumberConvictions;
    }

    public String getWarrant() {
        return warrant;
    }

    public void setWarrant(String warrant) {
        this.warrant = warrant;
    }

    public String getPlatformMexico() {
        return platformMexico;
    }

    public void setPlatformMexico(String platformMexico) {
        this.platformMexico = platformMexico;
    }

    public String getAfis() {
        return afis;
    }

    public void setAfis(String afis) {
        this.afis = afis;
    }
}

package com.umeca.model.entities.reviewer;

import com.umeca.model.entities.shared.Victim;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 03:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "current_criminal_proceeding")
public class CurrentCriminalProceeding {

    @Id
    @GeneratedValue
    @Column(name = "id_current_criminal_proceeding")
    private Long id;

    @OneToMany(mappedBy = "criminalProceeding", cascade = {CascadeType.ALL})
    private List<Crime> crimeList;

    @OneToMany(mappedBy = "criminalProceeding", cascade = {CascadeType.ALL})
    private List<CoDefendant> coDefendantList;

    @Column(name = "place_detention", length = 500, nullable = true)
    private String placeDetention;

    @Column(name = "behavior_detention", nullable = true, length = 255)
    private String behaviorDetention;

    @Column(name = "additional_info", length = 1000, nullable = true)
    private String additionalInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_meeting", nullable = true)
    private Meeting meeting;

    @OneToMany(mappedBy = "criminalProceeding", cascade = {CascadeType.ALL})
    private List<Victim> victims;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getPlaceDetention() {
        return placeDetention;
    }

    public void setPlaceDetention(String placeDetention) {
        this.placeDetention = placeDetention;
    }


    public String getBehaviorDetention() {
        return behaviorDetention;
    }

    public void setBehaviorDetention(String behaviorDetention) {
        this.behaviorDetention = behaviorDetention;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public List<Crime> getCrimeList() {
        return crimeList;
    }

    public void setCrimeList(List<Crime> crimeList) {
        this.crimeList = crimeList;
    }

    public List<CoDefendant> getCoDefendantList() {
        return coDefendantList;
    }

    public void setCoDefendantList(List<CoDefendant> coDefendantList) {
        this.coDefendantList = coDefendantList;
    }

    public List<Victim> getVictims() {
        return victims;
    }

    public void setVictims(List<Victim> victims) {
        this.victims = victims;
    }
}

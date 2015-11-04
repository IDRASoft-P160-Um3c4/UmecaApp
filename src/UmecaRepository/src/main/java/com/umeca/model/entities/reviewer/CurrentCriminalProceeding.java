package com.umeca.model.entities.reviewer;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.umeca.model.catalog.Location;
import com.umeca.model.entities.shared.Victim;

import javax.persistence.*;
import java.util.Date;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location")
    private Location locationDetention;

    @Column(name = "behavior_detention", nullable = true, length = 255)
    private String behaviorDetention;

    @Column(name = "additional_info", length = 1000, nullable = true)
    private String additionalInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_meeting", nullable = false, unique = true)
    private Meeting meeting;

    @OneToMany(mappedBy = "criminalProceeding", cascade = {CascadeType.ALL})
    private List<Victim> victims;

    @Column(name = "is_folder_access")
    private Boolean isFolderAccess;

    @Column(name = "handing_over_date")
    private Date handingOverDate;

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

    public Location getLocationDetention() {
        return locationDetention;
    }

    public void setLocationDetention(Location locationDetention) {
        this.locationDetention = locationDetention;
    }

    public Boolean getIsFolderAccess() {
        return isFolderAccess;
    }

    public void setIsFolderAccess(Boolean isFolderAccess) {
        this.isFolderAccess = isFolderAccess;
    }

    public Date getHandingOverDate() {
        return handingOverDate;
    }

    public void setHandingOverDate(Date handingOverDate) {
        this.handingOverDate = handingOverDate;
    }
}

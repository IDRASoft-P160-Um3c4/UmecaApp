package com.umeca.model.entities.reviewer;

import com.umeca.model.Catalog.Election;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 9/05/14
 * Time: 01:47 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="leave_country")
public class LeaveCountry {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_official_documentation", nullable = false)
    private Election officialDocumentation;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_lived_country", nullable = false)
    private Election livedCountry;

    @Column(name = "duration", length = 25, nullable = true)
    private String duration;

    @Column(name="reason", length = 1000, nullable = true)
    private String reason;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_family_another_country", nullable = false)
    private Election familyAnotherCountry;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_communication_family", nullable = false)
    private Election communicationFamily;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Election getOfficialDocumentation() {
        return officialDocumentation;
    }

    public void setOfficialDocumentation(Election officialDocumentation) {
        this.officialDocumentation = officialDocumentation;
    }

    public Election getLivedCountry() {
        return livedCountry;
    }

    public void setLivedCountry(Election livedCountry) {
        this.livedCountry = livedCountry;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Election getFamilyAnotherCountry() {
        return familyAnotherCountry;
    }

    public void setFamilyAnotherCountry(Election familyAnotherCountry) {
        this.familyAnotherCountry = familyAnotherCountry;
    }

    public Election getCommunicationFamily() {
        return communicationFamily;
    }

    public void setCommunicationFamily(Election communicationFamily) {
        this.communicationFamily = communicationFamily;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}

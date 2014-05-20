package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.RegisterType;
import com.umeca.model.catalog.Town;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 04:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="domicile")
public class Domicile {

    @Id
    @GeneratedValue
    @Column(name="id_domicile")
    private Long id;

    @Column(name="street", length = 100, nullable = false)
    private String street;

    @Column(name="no_outside", length = 10, nullable = true)
    private String noOut;

    @Column(name="no_inside", length = 10, nullable = false)
    private String noIn;

    @Column(name="city", length = 150, nullable = false)
    private String city;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_town", nullable = false)
    private Town town;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_register_type", nullable = false)
    private RegisterType registerType;

    @Column(name="time_live")
    private Integer timeLive;

    @Column(name="reason_change", length = 1000, nullable = true)
    private String reasonChange;

    @Column(name="description", length = 1000, nullable = true)
    private String description;


    @OneToMany(mappedBy="domicile", cascade={CascadeType.ALL})
    private List<Schedule> schedule;

    @Column(name="cp", length = 5)
    private String cp;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNoOut() {
        return noOut;
    }

    public void setNoOut(String noOut) {
        this.noOut = noOut;
    }

    public String getNoIn() {
        return noIn;
    }

    public void setNoIn(String noIn) {
        this.noIn = noIn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public void setRegisterType(RegisterType registerType) {
        this.registerType = registerType;
    }

    public Integer getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(Integer timeLive) {
        this.timeLive = timeLive;
    }

    public String getReasonChange() {
        return reasonChange;
    }

    public void setReasonChange(String reasonChange) {
        this.reasonChange = reasonChange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }
}

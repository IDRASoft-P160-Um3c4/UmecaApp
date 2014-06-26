package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.Location;
import com.umeca.model.catalog.RegisterType;
import com.umeca.model.entities.supervisor.FramingMeeting;
import com.umeca.model.shared.EntityGrid;

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
@Table(name="imputed_home")
public class ImputedHome implements EntityGrid{

    public ImputedHome() {
    }

    public ImputedHome(Long id, String addressString, String timeLive, String registerTypeString, String belongString) {
        this.id=id;
        this.addressString = addressString;
        this.timeLive = timeLive;
        this.registerTypeString = registerTypeString;
        this.belongString = belongString;
    }

    @Id
    @GeneratedValue
    @Column(name="id_imputed_home")
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_address", nullable = false)
    private Address address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_register_type", nullable = false)
    private RegisterType registerType;

    @Column(name="time_live", nullable = true, length = 30)
    private String timeLive;

    @ManyToOne
    @JoinColumn(name="id_belong", nullable = false)
    private Election belong;

    @Column(name="reason_change", length = 500, nullable = true)
    private String reasonChange;

    @Column(name="description", length = 500, nullable = true)
    private String description;

    @OneToMany(mappedBy="imputedHome", cascade={CascadeType.ALL})
    private List<Schedule> schedule;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    @Transient
    private String addressString;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting")
    FramingMeeting framingMeeting;

    @Transient
    private String registerTypeString;

    @Transient
    private String belongString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RegisterType getRegisterType() {
        return registerType;
    }

    public void setRegisterType(RegisterType registerType) {
        this.registerType = registerType;
    }

    public String getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(String timeLive) {
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

    public Election getBelong() {
        return belong;
    }

    public void setBelong(Election belong) {
        this.belong = belong;
    }

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public String getRegisterTypeString() {
        return registerTypeString;
    }

    public void setRegisterTypeString(String registerTypeString) {
        this.registerTypeString = registerTypeString;
    }

    public String getBelongString() {
        return belongString;
    }

    public void setBelongString(String belongString) {
        this.belongString = belongString;
    }
}

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
@Table(name="domicile")
public class Domicile implements EntityGrid{

    public Domicile() {
    }

    public Domicile(Long id,String domicile, String timeLive, String registerTypeString, String belongString) {
        this.id=id;
        this.domicile = domicile;
        this.timeLive = timeLive;
        this.registerTypeString = registerTypeString;
        this.belongString = belongString;
    }

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_location", nullable = false)
    private Location location;

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

    @OneToMany(mappedBy="domicile", cascade={CascadeType.ALL})
    private List<Schedule> schedule;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    @Column(name="domicile_string", nullable = false, length = 500)
    private String domicile;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
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

    @Override
    public String toString() {
        String result = "";
        result = "Calle: "+street+" No Ext: "+noOut;
        if(noIn != null && !noIn.equals("")){
            result= result + " No Int:"+ noIn;
        }
        if(location!=null){
            result = result + ","+location.getName()+". CP: "+location.getZipCode()+". "+location.getMunicipality().getName()+", "+location.getMunicipality().getState().getName()+".";
        }
        return  result;
  }
}

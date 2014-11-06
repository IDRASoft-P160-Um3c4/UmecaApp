package com.umeca.model.entities.supervisor;

import com.google.gson.annotations.Expose;
import com.umeca.model.catalog.Activity;
import com.umeca.model.entities.reviewer.Schedule;

import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Vmware on 04/11/2014.
 */
@Entity
@Table(name = "framing_activity")
public class FramingActivity {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_activity")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_activity")
    private Activity activity;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "framingActivity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Schedule> listSchedule;

    @ManyToOne
    @JoinColumn(name = "id_framing_meeting")
    private FramingMeeting framingMeeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public List<Schedule> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(List<Schedule> listSchedule) {
        this.listSchedule = listSchedule;
    }
}
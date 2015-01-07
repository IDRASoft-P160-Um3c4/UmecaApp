package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name="framing_selected_threat_rel")
public class FramingSelectedThreatRel {

    @Id
    @GeneratedValue
    @Column(name="id_framing_selected_threat_rel")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_framing_meeting")
    private FramingMeeting framingMeeting;

    @OneToOne
    @JoinColumn(name="id_framing_threat")
    private FramingThreat framingThreat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }

    public FramingThreat getFramingThreat() {
        return framingThreat;
    }

    public void setFramingThreat(FramingThreat framingThreat) {
        this.framingThreat = framingThreat;
    }
}

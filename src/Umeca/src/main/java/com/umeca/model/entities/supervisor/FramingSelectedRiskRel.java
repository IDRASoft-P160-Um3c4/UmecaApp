package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name="framing_selected_risk_rel")
public class FramingSelectedRiskRel {

    @Id
    @GeneratedValue
    @Column(name="id_framing_selected_risk_rel")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_framing_meeting")
    private FramingMeeting framingMeeting;

    @OneToOne
    @JoinColumn(name="id_framing_risk")
    private FramingRisk framingRisk;

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

    public FramingRisk getFramingRisk() {
        return framingRisk;
    }

    public void setFramingRisk(FramingRisk framingRisk) {
        this.framingRisk = framingRisk;
    }
}

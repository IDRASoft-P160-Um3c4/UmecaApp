package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name="framing_selected_safety_factor_rel")
public class FramingSelectedSafetyFactorRel {

    @Id
    @GeneratedValue
    @Column(name="id_framing_selected_safety_factor_rel")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_framing_meeting")
    private FramingMeeting framingMeeting;

    @OneToOne
    @JoinColumn(name="id_framing_safety_factor")
    private FramingSafetyFactor framingSafetyFactor;

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

    public FramingSafetyFactor getFramingSafetyFactor() {
        return framingSafetyFactor;
    }

    public void setFramingSafetyFactor(FramingSafetyFactor framingSafetyFactor) {
        this.framingSafetyFactor = framingSafetyFactor;
    }
}

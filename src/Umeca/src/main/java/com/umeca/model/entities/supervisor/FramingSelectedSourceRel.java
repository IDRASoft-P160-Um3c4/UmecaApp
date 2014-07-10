package com.umeca.model.entities.supervisor;

import javax.persistence.*;

@Entity
@Table(name="framing_selected_source_rel")
public class FramingSelectedSourceRel {

    @Id
    @GeneratedValue
    @Column(name="id_framing_selected_sources_rel")
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_framing_meeting")
    private FramingMeeting framingMeeting;

    @OneToOne
    @JoinColumn(name="id_framing_reference")
    private FramingReference framingReference;

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

    public FramingReference getFramingReference() {
        return framingReference;
    }

    public void setFramingReference(FramingReference framingReference) {
        this.framingReference = framingReference;
    }
}

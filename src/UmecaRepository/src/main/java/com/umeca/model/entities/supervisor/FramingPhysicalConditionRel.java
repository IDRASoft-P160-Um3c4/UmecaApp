package com.umeca.model.entities.supervisor;


import javax.persistence.*;

/**
 * Created by Vmware on 14/06/2014.
 */
@Entity
@Table(name="framing_physical_condition_rel")
public class FramingPhysicalConditionRel {

    @Id
    @GeneratedValue
    @Column(name="id_framing_physical_condition_rel")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_framing_meeting")
    private FramingMeeting framingMeeting;

    @Transient
    private String physicalCondition;


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

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }
}

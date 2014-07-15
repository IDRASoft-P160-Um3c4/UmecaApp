package com.umeca.model.entities.supervisor;

import com.umeca.model.entities.reviewer.Address;

import javax.persistence.*;

@Entity
@Table(name = "framing_address")
public class FramingAddress {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_address")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_framing_meeting")
    private FramingMeeting framingMeeting;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_address")
    private Address address;


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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}


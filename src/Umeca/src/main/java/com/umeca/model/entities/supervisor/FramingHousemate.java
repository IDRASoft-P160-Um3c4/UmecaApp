package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

@Entity
@Table(name="framing_housemate")
public class FramingHousemate {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_housemate")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_relationship")
    private Relationship relationship;

    @Column(name = "occupation")
    private String occupation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_framing_meeting")
    private FramingMeeting framingMeeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }
}
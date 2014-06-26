package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.Relationship;
import com.umeca.model.entities.reviewer.ImputedHome;

import javax.persistence.*;

@Entity
@Table(name="framing_reference")
public class FramingReference {

    @Id
    @GeneratedValue
    @Column(name = "id_framing_reference")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_relationship")
    private Relationship relationship;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_domicile")
    private ImputedHome imputedHome;

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

    public ImputedHome getImputedHome() {
        return imputedHome;
    }

    public void setImputedHome(ImputedHome imputedHome) {
        this.imputedHome = imputedHome;
    }

    public FramingMeeting getFramingMeeting() {
        return framingMeeting;
    }

    public void setFramingMeeting(FramingMeeting framingMeeting) {
        this.framingMeeting = framingMeeting;
    }
}

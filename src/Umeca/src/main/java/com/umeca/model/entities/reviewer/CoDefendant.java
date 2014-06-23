package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 12/06/14
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="co_defendant")
public class CoDefendant {

    @Id
    @GeneratedValue
    @Column(name="id_co_defendant")
    private Long id;

    @Column(name="full_name", nullable = false, length = 300)
    private String fullName;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_relationship", nullable = false)
    private Relationship relationship;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_criminal_proceeding", nullable = false)
    private CurrentCriminalProceeding criminalProceeding;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public CurrentCriminalProceeding getCriminalProceeding() {
        return criminalProceeding;
    }

    public void setCriminalProceeding(CurrentCriminalProceeding criminalProceeding) {
        this.criminalProceeding = criminalProceeding;
    }
}

package com.umeca.model.entities.shared;

import com.umeca.model.catalog.Relationship;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;
import com.umeca.model.entities.reviewer.Job;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 29/10/14
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Victim {

    @Id
    @GeneratedValue
    @Column(name="id_victim")
    private Long id;

    @Column(name = "name_victim", nullable = false)
    private String nameVictim;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_relationship_victim", nullable = false)
    private Relationship relationshipVictim;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_domicile_victim", nullable = false)
    private Address domicileVictim;

    @OneToOne(fetch=FetchType.LAZY, cascade ={CascadeType.ALL})
    @JoinColumn(name="id_legal", nullable = true)
    private CurrentCriminalProceeding criminalProceeding;

    @Column(name="age")
    private Integer age;

    @Column(name="phone", length = 500, nullable = true)
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameVictim() {
        return nameVictim;
    }

    public void setNameVictim(String nameVictim) {
        this.nameVictim = nameVictim;
    }

    public Relationship getRelationshipVictim() {
        return relationshipVictim;
    }

    public void setRelationshipVictim(Relationship relationshipVictim) {
        this.relationshipVictim = relationshipVictim;
    }

    public Address getDomicileVictim() {
        return domicileVictim;
    }

    public void setDomicileVictim(Address domicileVictim) {
        this.domicileVictim = domicileVictim;
    }

    public CurrentCriminalProceeding getCriminalProceeding() {
        return criminalProceeding;
    }

    public void setCriminalProceeding(CurrentCriminalProceeding criminalProceeding) {
        this.criminalProceeding = criminalProceeding;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

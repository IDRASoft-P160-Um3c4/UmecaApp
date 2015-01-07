package com.umeca.model.entities.supervisor;

import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.entities.reviewer.Address;

import javax.persistence.*;

@Entity
@Table(name = "accompaniment_info")
public class AccompanimentInfo {

    @Id
    @GeneratedValue
    @Column(name = "id_accompaniment_info")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "occupation_place")
    private String occupationPlace;

    @OneToOne
    @JoinColumn(name = "id_academmic_level")
    private AcademicLevel academicLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getOccupationPlace() {
        return occupationPlace;
    }

    public void setOccupationPlace(String occupationPlace) {
        this.occupationPlace = occupationPlace;
    }

    public AcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }
}

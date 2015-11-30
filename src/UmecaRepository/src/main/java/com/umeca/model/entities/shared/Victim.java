package com.umeca.model.entities.shared;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.catalog.InformationAvailability;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.CurrentCriminalProceeding;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 29/10/14
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "victim")
public class Victim {

    @Id
    @GeneratedValue
    @Column(name="id_victim")
    private Long id;

    @Column(name = "name_victim", nullable = false)
    private String fullname;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_relationship", nullable = false)
    private Relationship relationship;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_information_availability", nullable = true)
    private InformationAvailability infoAddress;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_address", nullable = false)
    private Address address;

    @Column(name="specification", nullable = true, length = 255)
    private String specification;

    @OneToOne(fetch=FetchType.LAZY, cascade ={CascadeType.ALL})
    @JoinColumn(name="id_legal", nullable = true)
    private CurrentCriminalProceeding criminalProceeding;

    @Column(name="age")
    private Integer age;

    @Column(name="phone", length = 500, nullable = true)
    private String phone;

    @Transient
    private Long infoAddressId;

    @Transient
    private Boolean hasInfoAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = StringExt.substringMax(fullname, 254);
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        this.phone = StringExt.substringMax(phone, 254);
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = StringExt.substringMax(specification, 254);
    }

    public InformationAvailability getInfoAddress() {
        return infoAddress;
    }

    public void setInfoAddress(InformationAvailability infoAddress) {
        this.infoAddress = infoAddress;
    }

    public Long getInfoAddressId() {
        return infoAddressId;
    }

    public void setInfoAddressId(Long infoAddressId) {
        this.infoAddressId = infoAddressId;
    }

    public Boolean getHasInfoAddress() {
        return hasInfoAddress;
    }

    public void setHasInfoAddress(Boolean hasInfoAddress) {
        this.hasInfoAddress = hasInfoAddress;
    }
}

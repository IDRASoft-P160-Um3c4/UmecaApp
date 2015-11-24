package com.umeca.model.entities.supervisor;

import com.umeca.infrastructure.extensions.StringExt;
import com.umeca.model.catalog.AcademicLevel;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.entities.reviewer.Address;
import com.umeca.model.entities.reviewer.Imputed;
import com.umeca.model.entities.reviewer.ImputedHome;
import com.umeca.model.shared.Constants;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="process_accompaniment")
public class ProcessAccompaniment {

    @Id
    @GeneratedValue
    @Column(name="id_process_accompaniment")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="last_name_p")
    private String lastNameP;

    @Column(name="last_name_m")
    private String lastNameM;

    @Column(name="gender")
    private Integer gender;

    @Column(name="age")
    private String age;

    @Column(name="phone")
    private String phone;

    @Column(name="cel_phone")
    private String celphone;

    @Column(name="degree")
    private String degree;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_framing_occupation")
    private Occupation occupation;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_addres")
    private Address address;

    @OneToOne
    @JoinColumn(name = "id_relationship")
    private Relationship relationship;

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
        this.name = StringExt.substringMax(name, Constants.DEFAULT_LEN_STRING);
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = StringExt.substringMax(lastNameP, Constants.DEFAULT_LEN_STRING);
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = StringExt.substringMax(lastNameM, Constants.DEFAULT_LEN_STRING);
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = StringExt.substringMax(age, Constants.DEFAULT_LEN_STRING);
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = StringExt.substringMax(phone, Constants.DEFAULT_LEN_STRING);
    }

    public String getCelphone() {
        return celphone;
    }

    public void setCelphone(String celphone) {
        this.celphone = StringExt.substringMax(celphone, Constants.DEFAULT_LEN_STRING);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = StringExt.substringMax(degree, Constants.DEFAULT_LEN_STRING);
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
}

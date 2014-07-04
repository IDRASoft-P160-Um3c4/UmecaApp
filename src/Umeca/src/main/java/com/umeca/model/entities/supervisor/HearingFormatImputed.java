package com.umeca.model.entities.supervisor;


import com.umeca.model.entities.reviewer.Address;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="hearing_format_imputed")
public class HearingFormatImputed {

    @Id
    @GeneratedValue
    @Column(name="id_hearing_format_imputed")
    private Long id;

    @Column(name="name")
    private String name ;

    @Column(name="last_name_p")
    private String lastNameP;

    @Column(name="last_name_m")
    private String lastNameM;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="imputed_tel")
    private String imputeTel;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_addres")
    private Address address;

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

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameP) {
        this.lastNameP = lastNameP;
    }

    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getImputeTel() {
        return imputeTel;
    }

    public void setImputeTel(String imputeTel) {
        this.imputeTel = imputeTel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

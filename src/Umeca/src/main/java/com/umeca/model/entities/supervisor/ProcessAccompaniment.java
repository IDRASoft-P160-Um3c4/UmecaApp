package com.umeca.model.entities.supervisor;


import com.umeca.model.catalog.SchoolLevel;
import com.umeca.model.entities.reviewer.Domicile;
import com.umeca.model.entities.reviewer.Imputed;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_framing_occupation")
    private Occupation occupation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_domicile")
    private Domicile domicile;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_school_level")
    private SchoolLevel schoolLevel;

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
        this.age = age;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Domicile getDomicile() {
        return domicile;
    }

    public void setDomicile(Domicile domicile) {
        this.domicile = domicile;
    }

    public SchoolLevel getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(SchoolLevel schoolLevel) {
        this.schoolLevel = schoolLevel;
    }
}

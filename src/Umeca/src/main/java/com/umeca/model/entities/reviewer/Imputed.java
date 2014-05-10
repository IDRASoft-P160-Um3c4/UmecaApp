package com.umeca.model.entities.reviewer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 01:05 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="imputed")
public class Imputed {
    @Id
    @GeneratedValue
    @Column(name="id_imputed")
    private Long id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="lastname_p", length = 50, nullable = false)
    private String lastNameP;

    @Column(name="lastname_m", length = 50, nullable = false)
    private String lastNameM;

    @Column(name="nickName", length = 50, nullable = true)
    private String nickName;

    @Column(name="gender", nullable = false)
    private Boolean gender;

    @Column(name="date_birth", nullable = false)
    private Date dateBirth;

    @Column(name="cel_phone", length = 10, nullable = true)
    private String celPhone;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getCelPhone() {
        return celPhone;
    }

    public void setCelPhone(String celPhone) {
        this.celPhone = celPhone;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }


}



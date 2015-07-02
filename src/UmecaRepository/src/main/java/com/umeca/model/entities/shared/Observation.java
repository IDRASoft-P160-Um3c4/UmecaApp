package com.umeca.model.entities.shared;

import com.umeca.model.entities.account.User;
import com.umeca.model.entities.director.minutes.Agreement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @GeneratedValue
    @Column(name = "id_observation")
    private Long id;

    @Column(name = "comment",length = 750)
    private String comment;

    @Column(name = "register_date")
    private Date registerDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User registerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agreement")
    private Agreement agreement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public User getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(User registerUser) {
        this.registerUser = registerUser;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public void setAgreement(Agreement agreement) {
        this.agreement = agreement;
    }
}

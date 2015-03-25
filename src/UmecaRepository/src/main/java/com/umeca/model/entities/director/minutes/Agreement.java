package com.umeca.model.entities.director.minutes;

import javax.persistence.*;

@Entity
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue
    @Column(name = "id_agreement")
    private Long id;

    //TODO
}

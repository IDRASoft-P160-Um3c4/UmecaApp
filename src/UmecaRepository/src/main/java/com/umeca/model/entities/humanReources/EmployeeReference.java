package com.umeca.model.entities.humanReources;

import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

public class EmployeeReference {
    @Id
    @GeneratedValue
    @Column(name = "id_framing_reference")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;


    @Column(name = "time_ago")
    private String timeAgo;

    @Column(name = "address")
    private String address;

    @Column(name = "address_ref")
    private String addressRef;

    @Column(name = "age")
    private String age;

    @Column(name = "occupation")
    private String occupation;

    @OneToOne
    @JoinColumn(name = "id_relationship")
    private Relationship relationship;

    @Column(name = "specification_relationship", length = 255, nullable = true)
    private String specificationRelationship;
}

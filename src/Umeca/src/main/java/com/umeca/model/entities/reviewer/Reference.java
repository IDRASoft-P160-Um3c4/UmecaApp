package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.Relationship;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 01:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="reference")
public class Reference {
    @Id
    @GeneratedValue
    @Column(name="id_reference")
    private Long id;

    @Column(name="name", length = 150, nullable = false)
    private String name;

    @Column(name="age", nullable = false)
    private Integer age;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_relationship", nullable = false)
    private Relationship relationship;

    @Column(name="address", length = 250, nullable = true)
    private String address;

    @Column(name="phone", length = 10, nullable =true)
    private String phone;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_document_type", nullable = false)
    private DocumentType documentType;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }
}

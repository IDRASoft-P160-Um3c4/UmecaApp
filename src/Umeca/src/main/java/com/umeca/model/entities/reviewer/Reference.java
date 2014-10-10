package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.shared.EntityGrid;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 8/05/14
 * Time: 01:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="reference")
public class Reference implements EntityGrid{

    public Reference() {
    }

    public Reference(Long id,String fullName, String relName,Integer age, String phone,Boolean isAccompaniment, String specificationRelationship) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.relName = relName;
        if(!specificationRelationship.equals("")){
            this.relName += ": "+specificationRelationship;
        }
        this.isAccompanimentString = isAccompaniment ? "Si": "No";
    }

    @Id
    @GeneratedValue
    @Column(name="id_reference")
    private Long id;

    @Column(name="name", length = 150, nullable = false)
    private String fullName;

    @Column(name="age", nullable = false)
    private Integer age;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_relationship", nullable = false)
    private Relationship relationship;

    @Column(name="address", length = 250, nullable = false)
    private String address;

    @Column(name="phone", length = 200, nullable =false)
    private String phone;

    @Column(name="specification_document_type", length = 250, nullable = true)
    private String specification;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_document_type", nullable = false)
    private DocumentType documentType;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_meeting", nullable = false)
    private Meeting meeting;

    @Column(name = "is_accompaniment", nullable = false)
    private Boolean isAccompaniment;

    @Column(name="specification_relationship", nullable =  true, length = 255)
    private String specificationRelationship;

    @Transient
    private String isAccompanimentString;

    @Transient
    private String relName;

    @Transient
    private Long idAux;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @JsonIgnore
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
    @JsonIgnore
    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @JsonIgnore
    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Long getIdAux() {
        return idAux;
    }

    public void setIdAux(Long idAux) {
        this.idAux = idAux;
    }

    public Boolean getIsAccompaniment() {
        return isAccompaniment;
    }

    public void setIsAccompaniment(Boolean accompaniment) {
        isAccompaniment = accompaniment;
    }

    public Boolean getAccompaniment() {
        return isAccompaniment;
    }

    public void setAccompaniment(Boolean accompaniment) {
        isAccompaniment = accompaniment;
    }

    public String getAccompanimentString() {
        return isAccompanimentString;
    }

    public void setAccompanimentString(String accompanimentString) {
        isAccompanimentString = accompanimentString;
    }

    public String getSpecificationRelationship() {
        return specificationRelationship;
    }

    public void setSpecificationRelationship(String specificationRelationship) {
        this.specificationRelationship = specificationRelationship;
    }
}

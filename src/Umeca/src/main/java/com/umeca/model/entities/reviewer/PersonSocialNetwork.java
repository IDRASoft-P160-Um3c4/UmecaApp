package com.umeca.model.entities.reviewer;

import com.umeca.model.catalog.DocumentType;
import com.umeca.model.catalog.Election;
import com.umeca.model.catalog.Relationship;
import com.umeca.model.shared.EntityGrid;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Desarrollo
 * Date: 13/05/14
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "person_social_network")
public class PersonSocialNetwork{


    @Id
    @GeneratedValue
    @Column(name = "id_person_social")
    private Long id;

    @Column(name="name", length = 150, nullable = false)
    private String name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_relationship", nullable = false)
    private Relationship relationship;

    @Column(name="age", nullable = false)
    private Integer age;

    @Column(name="phone", nullable = false, length = 200)
    private String phone;

    @Column(name="address", nullable = true, length = 500)
    private String address;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_document_type", nullable = false)
    private DocumentType documentType;

    @Column(name="specification_document_type", length = 250, nullable = true)
    private String specification;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_dependent", nullable = false)
    private Election dependent;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_living_with", nullable = false)
    private Election livingWith;

    @OneToOne(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name="id_social_network", nullable = false)
    private SocialNetwork socialNetwork;

    @Column(name = "is_accompaniment", nullable = false)
    private Boolean isAccompaniment;

    @Transient
    private Long idAux;

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

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
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
        this.phone = phone;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public Election getDependent() {
        return dependent;
    }

    public void setDependent(Election dependent) {
        this.dependent = dependent;
    }

    public Election getLivingWith() {
        return livingWith;
    }

    public void setLivingWith(Election livingWith) {
        this.livingWith = livingWith;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
